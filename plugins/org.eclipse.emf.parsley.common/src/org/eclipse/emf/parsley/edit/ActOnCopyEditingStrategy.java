/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.util.EcoreUtil2;

import com.google.inject.Inject;

/**
 * The edited object is a copy of the original one; IMPORTANT: the copy is not
 * part of any {@link Resource}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class ActOnCopyEditingStrategy implements IEditingStrategy {

	@Inject
	private EditingDomainFinder editingDomainFinder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.parsley.edit.IEditingStrategy#prepare(org.eclipse.emf
	 * .ecore.EObject)
	 */
	public EObject prepare(EObject original) {
		return EcoreUtil2.clone(original);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.parsley.edit.IEditingStrategy#update(org.eclipse.emf.
	 * ecore.EObject, org.eclipse.emf.ecore.EObject)
	 */
	public void update(EObject original, EObject edited) {
		EditingDomain domain = editingDomainFinder
				.getEditingDomainFor(original);
		if (domain == null) {
			EcoreUtil.replace(original, edited);
			// this would not make dirty state work
		} else {
			EObject eContainer = original.eContainer();
			EStructuralFeature eContainingFeature = original
					.eContainingFeature();
			Command command = null;

			if (eContainer != null)
				command = ReplaceCommand.create(domain, eContainer,
						eContainingFeature, original,
						Collections.singleton(edited));
			else
				command = new ReplaceCommand(domain, original.eResource()
						.getContents(), original, Collections.singleton(edited));

			domain.getCommandStack().execute(command);
		}
	}

}
