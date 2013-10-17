/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.util.EObjectState;

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

	private EObjectState state;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.parsley.edit.IEditingStrategy#prepare(org.eclipse.emf
	 * .ecore.EObject)
	 */
	public void prepare(EObject original) {
		state = new EObjectState(original);
	}

	/**
	 * This returns null because an {@link EditingDomain} must not be used,
	 * otherwise modifications to the object are reflected in other views and
	 * editors.
	 */
	public EditingDomain getEditingDomain(EObject edited) {
		return null;
	}

	public void update(EObject edited) {
		EditingDomain domain = editingDomainFinder.getEditingDomainFor(edited);
		EditCommand editCommand = new EditCommand(
				domain, "Edit "
						+ edited.eClass().getName(), edited, state);
		domain.getCommandStack().execute(editCommand);
	}

	public void rollback(EObject edited) {
		state.copyStateTo(edited);
	}

}
