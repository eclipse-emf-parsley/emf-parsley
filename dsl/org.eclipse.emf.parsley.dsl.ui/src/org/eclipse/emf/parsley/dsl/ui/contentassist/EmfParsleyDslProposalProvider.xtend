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
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess
import org.eclipse.emf.parsley.dsl.model.LabelSpecification
import org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslExpectedSuperTypes
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.common.types.TypesPackage
import org.eclipse.xtext.common.types.access.IJvmTypeProvider
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor

/**
 * see http://www.eclipse.org/Xtext/documentation.html#contentAssist on how to customize content assistant
 */
class EmfParsleyDslProposalProvider extends AbstractEmfParsleyDslProposalProvider {
	@Inject ITypesProposalProvider typeProposalProvider;

	@Inject IJvmTypeProvider.Factory typeProviderFactory;
	
	@Inject extension EmfParsleyDslExpectedSuperTypes

	override void completeViewSpecification_Type(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		showOnlySubtypesOf(model, context, acceptor, model.expectedSupertype);
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
		showOnlySubtypesOf(model, context, acceptor, model.expectedSupertype);
	}

	def protected void showOnlySubtypesOf(EObject model,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			Class<?> superType) {

		val jvmTypeProvider = typeProviderFactory
				.createTypeProvider(model.eResource().getResourceSet());
		val interfaceToImplement = jvmTypeProvider
				.findTypeByName(superType.getName());
		typeProposalProvider.createSubTypeProposals(interfaceToImplement, this,
				context,
				TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE,
				acceptor);
	}


	override void completeXFeatureCall_Feature(EObject model,
			Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		if (model instanceof EmfFeatureAccess) {
			createLocalVariableAndImplicitProposals(model, context, acceptor);
			return;
		}

		if (model instanceof LabelSpecification) {
			createLocalVariableAndImplicitProposals(context.getPreviousModel(),
					context, acceptor);
			return;
		}

		super.completeXFeatureCall_Feature(model, assignment, context, acceptor);
	}
}
