/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.contentassist

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.dsl.typing.EmfParsleyDslTypeSystem
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper
import org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslExpectedSuperTypes
import org.eclipse.jface.text.Region
import org.eclipse.jface.viewers.StyledString
import org.eclipse.swt.graphics.Image
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.JvmWildcardTypeReference
import org.eclipse.xtext.common.types.TypesPackage
import org.eclipse.xtext.common.types.access.IJvmTypeProvider
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import org.eclipse.xtext.xbase.ui.contentassist.ImportOrganizingProposal
import org.eclipse.xtext.xbase.ui.contentassist.ReplacingAppendable

import static org.eclipse.emf.parsley.dsl.model.ModelPackage.Literals.*

import static extension org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil.*

/**
 * see http://www.eclipse.org/Xtext/documentation.html#contentAssist on how to customize content assistant
 */
class EmfParsleyDslProposalProvider extends AbstractEmfParsleyDslProposalProvider {
	@Inject ITypesProposalProvider typeProposalProvider;

	@Inject IJvmTypeProvider.Factory typeProviderFactory;
	
	@Inject extension EmfParsleyDslExpectedSuperTypes
	
	@Inject extension EmfParsleyDslGuiceModuleHelper
	
	@Inject
	private ReplacingAppendable.Factory appendableFactory;

	@Inject
	private EmfParsleyDslTypeSystem typeSystem;

	override void completeViewSpecification_Type(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		// this is called also with model as PartsSpecifications
		// so we need to make the expectedSupertype explicit on ViewSpecification
		showOnlySubtypesOf(model, context, acceptor, VIEW_SPECIFICATION.expectedSupertype);
	}

	override void completeFeatureAssociatedExpression_ParameterType(
			EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		showSubtypesOfEObjectForEmfFeatureAccess(model, context, acceptor);
	}

	override void completeFeatureSpecification_ParameterType(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		showSubtypesOfEObjectForEmfFeatureAccess(model, context, acceptor);
	}

	override void completeControlFactorySpecification_ParameterType(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		showSubtypesOfEObjectForEmfFeatureAccess(model, context, acceptor);
	}

	override void completeExtendsClause_SuperType(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		// we must take the expected supertype of the container of the extends clause object
		showOnlySubtypesOf(model, context, acceptor, model.eContainer.expectedSupertype);
	}

	def protected void showSubtypesOfEObjectForEmfFeatureAccess(EObject model,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		showOnlySubtypesOf(model, context, acceptor, FEATURE_ASSOCIATED_EXPRESSION.expectedSupertype);
	}

	def protected void showOnlySubtypesOf(EObject model,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			Class<?> superType) {

		if (superType == null) {
			return
		}

		val jvmTypeProvider = typeProviderFactory
				.createTypeProvider(model.eResource().getResourceSet());
		val interfaceToImplement = jvmTypeProvider
				.findTypeByName(superType.getName());
		typeProposalProvider.createSubTypeProposals(interfaceToImplement, this,
				context,
				TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE,
				acceptor);
	}

	override completeBinding_TypeDecl(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createStandardJavaTypesProposals(context, acceptor)
		// the completion for existing bindings will appear first
		createBindingProposals(model, model.containingModule.allGuiceValueBindingsMethodsInSuperclass, context, acceptor) [
			appendable, op |
			// if the original method was MyType valueName(...) the proposal will be
			// MyType Name
			appendable.append(toLightweightTypeReference(op.returnType, model))
			appendable.append(" ")
			appendable.append(op.simpleName.substring("value".length))
		]
	}

	override completeBinding_TypeToBind(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeTypeOrProvideBinding(model, model.containingModule.allGuiceTypeBindingsMethodsInSuperclass, 
			[
				op |
				// if the original method was Class<? extends MyType> bindName(...) the proposal will be
				// MyType
				op.extractWildcardUpperBound
			],
			assignment, context, acceptor
		)
	}

	override completeBinding_Type(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeTypeOrProvideBinding(model, model.containingModule.allGuiceProviderBindingsMethodsInSuperclass, 
			[
				op |
				// if the original method was Class<? extends Provider<MyType>> provideName(...) the proposal will be
				// MyType
				
				// this will return Provider<MyType>
				val providerType = op.extractWildcardUpperBound as JvmParameterizedTypeReference
				// and this will return MyType
				providerType.arguments.head
			],
			assignment, context, acceptor
		)
	}

	def private extractWildcardUpperBound(JvmOperation op) {
		val returnType = op.returnType as JvmParameterizedTypeReference
		val argument = returnType.arguments.head as JvmWildcardTypeReference
		argument.constraints.head.typeReference
	}

	def private completeTypeOrProvideBinding(EObject model, Iterable<JvmOperation> superClassValueBindings, 
		(JvmOperation)=>JvmTypeReference typeExtractor,
		Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor
	) {
		createStandardJavaTypesProposals(context, acceptor)
		// the completion for existing bindings will appear first
		createBindingProposals(model, superClassValueBindings, context, acceptor) [
			appendable, op |
			val typeReference = typeExtractor.apply(op)
			// Methods have already been filtered and the return type is of the shape Class<? extends MyType>
			appendable.append(toLightweightTypeReference(typeReference, model))
		]
	}

	/**
	 * show the standard Java type completions
	 */
	def private createStandardJavaTypesProposals(ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeJavaTypes(context, TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE, true, qualifiedNameValueConverter, createVisibilityFilter(context), acceptor)
	}

	def private createBindingProposals(EObject model, Iterable<JvmOperation> superClassValueBindings, ContentAssistContext context, 
		ICompletionProposalAcceptor acceptor, (ReplacingAppendable, JvmOperation)=>void proposalTextStrategy
	) {
		for (op : superClassValueBindings) {
			createProposals(model, op, context, acceptor, proposalTextStrategy)
		}
	}

	def private toLightweightTypeReference(JvmTypeReference typeRef, EObject model) {
		typeSystem.toLightweightTypeReference(typeRef, model)
	}

	def private createProposals(EObject model, JvmOperation op, ContentAssistContext context, 
		ICompletionProposalAcceptor acceptor, (ReplacingAppendable, JvmOperation)=>void proposalTextStrategy
	) {
		val document = context.getDocument();
		val resource = model.eResource() as XtextResource;
		val offset = context.getReplaceRegion().getOffset();
		val appendable = appendableFactory.create(document, resource, offset, context.getReplaceRegion().getLength());
		
		proposalTextStrategy.apply(appendable, op)
		
		val image = getImage(op);
		val typeConverter = getTypeConverter(context.getResource());
		val displayString = getStyledDisplayString(op, false, 0, op.getQualifiedName(), op.getSimpleName(), typeConverter);
		
		val completionProposal = createCompletionProposal(appendable, context.getReplaceRegion(),
				displayString, image)
		
		// the high priority will make these proposals appear before
		// the standard proposals for Java types
		completionProposal.priority = 1500
		completionProposal.setMatcher[name, prefix |
			val delegate = context.getMatcher();
			delegate.isCandidateMatchingPrefix(op.simpleName, prefix);
		]

		// the following two settings will make the Javadoc of the original method appear on the right
		completionProposal.setAdditionalProposalInfo(op);
		completionProposal.setHover(getHover());
		
		acceptor.accept(completionProposal)
	}

	def private ImportOrganizingProposal createCompletionProposal(ReplacingAppendable appendable, Region replaceRegion,
			StyledString displayString, Image image) {
		return new ImportOrganizingProposal(appendable, replaceRegion.getOffset(), replaceRegion.getLength(),
				replaceRegion.getOffset(), image, displayString);
	}
	
}
