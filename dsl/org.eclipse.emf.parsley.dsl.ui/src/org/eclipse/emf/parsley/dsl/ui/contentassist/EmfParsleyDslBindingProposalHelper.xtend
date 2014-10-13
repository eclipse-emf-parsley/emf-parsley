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
import org.eclipse.emf.parsley.dsl.model.ValueBinding
import org.eclipse.emf.parsley.dsl.typing.EmfParsleyDslTypeSystem
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper
import org.eclipse.jface.text.Region
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.StyledString
import org.eclipse.swt.graphics.Image
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import org.eclipse.xtext.xbase.ui.contentassist.ImportOrganizingProposal
import org.eclipse.xtext.xbase.ui.contentassist.ReplacingAppendable

import static extension org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil.*

/**
 * @author Lorenzo Bettini - initial API and implementation
 */
class EmfParsleyDslBindingProposalHelper {
	
	@Inject extension EmfParsleyDslGuiceModuleHelper
	
	@Inject
	private ILabelProvider labelProvider
	
	@Inject
	private ReplacingAppendable.Factory appendableFactory;

	@Inject
	private EmfParsleyDslTypeSystem typeSystem;
	
	def createBindingProposals(EObject model, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if (model instanceof ValueBinding) {
			createValueBindingProposals(model, context, acceptor)
		}
	}

	def private createValueBindingProposals(ValueBinding binding, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		val module = binding.containingModule
		
		// These are all the value bindings in the superclass
		val superClassValueBindings = module.allGuiceValueBindingsMethodsInSuperclass

		for (op : superClassValueBindings) {
			createProposals(binding, op, context, acceptor)
		}
	}

	def private createProposals(EObject model, JvmOperation op, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		val document = context.getDocument();
		val resource = model.eResource() as XtextResource;
		val offset = context.getReplaceRegion().getOffset();
		val appendable = appendableFactory.create(document, resource, offset, context.getReplaceRegion().getLength());
		
		appendable.append(typeSystem.toLightweightTypeReference(op.returnType, model))
		appendable.append(" ")
		appendable.append(op.simpleName.substring("value".length))
		
		val completionProposal = createCompletionProposal(appendable, context.getReplaceRegion(),
				new StyledString(op.simpleName).append(
					" - Override method from " + op.declaringType.simpleName,
					StyledString.QUALIFIER_STYLER), labelProvider.getImage(op))
		
		completionProposal.setMatcher[name, prefix |
			val delegate = context.getMatcher();
			delegate.isCandidateMatchingPrefix(op.simpleName, prefix);
		]
		
		acceptor.accept(completionProposal)
	}

	def protected ImportOrganizingProposal createCompletionProposal(ReplacingAppendable appendable, Region replaceRegion,
			StyledString displayString, Image image) {
		return new ImportOrganizingProposal(appendable, replaceRegion.getOffset(), replaceRegion.getLength(),
				replaceRegion.getOffset(), image, displayString);
	}
}
