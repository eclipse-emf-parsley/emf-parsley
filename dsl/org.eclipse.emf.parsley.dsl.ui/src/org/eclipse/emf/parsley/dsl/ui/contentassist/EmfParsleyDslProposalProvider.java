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
package org.eclipse.emf.parsley.dsl.ui.contentassist;

import static org.eclipse.emf.parsley.dsl.model.ModelPackage.Literals.FEATURE_ASSOCIATED_EXPRESSION;
import static org.eclipse.emf.parsley.dsl.model.ModelPackage.Literals.VIEW_SPECIFICATION;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dsl.typing.EmfParsleyDslTypeSystem;
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper;
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil;
import org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslExpectedSuperTypes;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.ui.editor.contentassist.PrefixMatcher;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.ui.contentassist.ImportOrganizingProposal;
import org.eclipse.xtext.xbase.ui.contentassist.ReplacingAppendable;

import com.google.inject.Inject;

/**
 * see http://www.eclipse.org/Xtext/documentation.html#contentAssist on how to customize content assistant
 */
public class EmfParsleyDslProposalProvider extends AbstractEmfParsleyDslProposalProvider {

	@Inject
	private ITypesProposalProvider typeProposalProvider;

	@Inject
	private IJvmTypeProvider.Factory typeProviderFactory;

	@Inject
	private EmfParsleyDslExpectedSuperTypes expectedSuperTypes;

	@Inject
	private EmfParsleyDslGuiceModuleHelper guiceModuleHelper;

	@Inject
	private ReplacingAppendable.Factory appendableFactory;

	@Inject
	private EmfParsleyDslTypeSystem typeSystem;

	@Override
	public void completeViewSpecification_Type(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		// this is called also with model as PartsSpecifications
		// so we need to make the expectedSupertype explicit on ViewSpecification
		showOnlySubtypesOf(model, context, acceptor, expectedSuperTypes.getExpectedSupertype(VIEW_SPECIFICATION));
	}

	@Override
	public void completeFeatureAssociatedExpression_ParameterType(
			EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		showSubtypesOfEObjectForEmfFeatureAccess(model, context, acceptor);
	}

	@Override
	public void completeFeatureSpecification_ParameterType(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		showSubtypesOfEObjectForEmfFeatureAccess(model, context, acceptor);
	}

	@Override
	public void completeControlFactorySpecification_ParameterType(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		showSubtypesOfEObjectForEmfFeatureAccess(model, context, acceptor);
	}

	@Override
	public void completeExtendsClause_SuperType(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		// we must take the expected supertype of the container of the extends clause object
		showOnlySubtypesOf(model, context, acceptor, expectedSuperTypes.getExpectedSupertype(model.eContainer()));
	}

	protected void showSubtypesOfEObjectForEmfFeatureAccess(EObject model,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		showOnlySubtypesOf(model, context, acceptor, expectedSuperTypes.getExpectedSupertype(FEATURE_ASSOCIATED_EXPRESSION));
	}

	protected void showOnlySubtypesOf(EObject model,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			Class<?> superType) {

		if (superType == null) {
			return;
		}

		var jvmTypeProvider = typeProviderFactory
				.createTypeProvider(model.eResource().getResourceSet());
		JvmType interfaceToImplement = jvmTypeProvider
				.findTypeByName(superType.getName());
		typeProposalProvider.createSubTypeProposals(interfaceToImplement, this,
				context,
				TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE,
				acceptor);
	}

	@Override
	public void completeBinding_TypeDecl(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createStandardJavaTypesProposals(context, acceptor);
		// the completion for existing bindings will appear first
		var containingModule = EmfParsleyDslModelUtil.containingModule(model);
		createBindingProposals(model, guiceModuleHelper.getAllGuiceValueBindingsMethodsInSuperclass(containingModule), context, acceptor,
			(appendable, op) -> {
				// if the original method was MyType valueName(...) the proposal will be
				// MyType Name
				appendable.append(toLightweightTypeReference(op.getReturnType(), model));
				appendable.append(" ");
				appendable.append(op.getSimpleName().substring("value".length()));
			});
	}

	@Override
	public void completeBinding_TypeToBind(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		var containingModule = EmfParsleyDslModelUtil.containingModule(model);
		completeTypeOrProvideBinding(model, guiceModuleHelper.getAllGuiceTypeBindingsMethodsInSuperclass(containingModule),
			// if the original method was Class<? extends MyType> bindName(...) the proposal will be
			// MyType
			this::extractWildcardUpperBound,
			context, acceptor
		);
	}

	@Override
	public void completeBinding_Type(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		var containingModule = EmfParsleyDslModelUtil.containingModule(model);
		completeTypeOrProvideBinding(model, guiceModuleHelper.getAllGuiceProviderBindingsMethodsInSuperclass(containingModule),
			op -> {
				// if the original method was Class<? extends Provider<MyType>> provideName(...) the proposal will be
				// MyType

				// this will return Provider<MyType>
				var providerType = (JvmParameterizedTypeReference) extractWildcardUpperBound(op);
				// and this will return MyType
				return providerType.getArguments().get(0);
			},
			context, acceptor
		);
	}

	private JvmTypeReference extractWildcardUpperBound(JvmOperation op) {
		var returnType = (JvmParameterizedTypeReference) op.getReturnType();
		var argument = (JvmWildcardTypeReference) returnType.getArguments().get(0);
		return argument.getConstraints().get(0).getTypeReference();
	}

	private void completeTypeOrProvideBinding(EObject model, Iterable<JvmOperation> superClassValueBindings,
		Function<JvmOperation, JvmTypeReference> typeExtractor,
		ContentAssistContext context, ICompletionProposalAcceptor acceptor
	) {
		createStandardJavaTypesProposals(context, acceptor);
		// the completion for existing bindings will appear first
		createBindingProposals(model, superClassValueBindings, context, acceptor,
			(appendable, op) -> {
				var typeReference = typeExtractor.apply(op);
				// Methods have already been filtered and the return type is of the shape Class<? extends MyType>
				appendable.append(toLightweightTypeReference(typeReference, model));
			});
	}

	/**
	 * show the standard Java type completions
	 */
	private void createStandardJavaTypesProposals(ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeJavaTypes(context, TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE, true, getQualifiedNameValueConverter(), createVisibilityFilter(context), acceptor);
	}

	private void createBindingProposals(EObject model, Iterable<JvmOperation> superClassValueBindings, ContentAssistContext context,
		ICompletionProposalAcceptor acceptor, BiConsumer<ReplacingAppendable, JvmOperation> proposalTextStrategy
	) {
		for (var op : superClassValueBindings) {
			createProposals(model, op, context, acceptor, proposalTextStrategy);
		}
	}

	private LightweightTypeReference toLightweightTypeReference(JvmTypeReference typeRef, EObject model) {
		return typeSystem.toLightweightTypeReference(typeRef, model);
	}

	private void createProposals(EObject model, JvmOperation op, ContentAssistContext context,
		ICompletionProposalAcceptor acceptor, BiConsumer<ReplacingAppendable, JvmOperation> proposalTextStrategy
	) {
		var document = context.getDocument();
		var resource = (XtextResource) model.eResource();
		var offset = context.getReplaceRegion().getOffset();
		var appendable = appendableFactory.create(document, resource, offset, context.getReplaceRegion().getLength());

		proposalTextStrategy.accept(appendable, op);

		Image image = getImage(op);
		var typeConverter = getTypeConverter(context.getResource());
		StyledString displayString = getStyledDisplayString(op, false, 0, op.getQualifiedName(), op.getSimpleName(), typeConverter);

		var completionProposal = createCompletionProposal(appendable, context.getReplaceRegion(),
				displayString, image);

		// the high priority will make these proposals appear before
		// the standard proposals for Java types
		completionProposal.setPriority(1500);
		completionProposal.setMatcher(new PrefixMatcher() {
			@Override
			public boolean isCandidateMatchingPrefix(String name, String prefix) {
				var delegate = context.getMatcher();
				return delegate.isCandidateMatchingPrefix(op.getSimpleName(), prefix);
			}
		});

		// the following two settings will make the Javadoc of the original method appear on the right
		completionProposal.setAdditionalProposalInfo(op);
		completionProposal.setHover(getHover());

		acceptor.accept(completionProposal);
	}

	private ImportOrganizingProposal createCompletionProposal(ReplacingAppendable appendable, Region replaceRegion,
			StyledString displayString, Image image) {
		return new ImportOrganizingProposal(appendable, replaceRegion.getOffset(), replaceRegion.getLength(),
				replaceRegion.getOffset(), image, displayString);
	}

}
