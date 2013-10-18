/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;
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
		enableNotifications(original, false);
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
		enableNotifications(edited, true);
		EditingDomain domain = editingDomainFinder.getEditingDomainFor(edited);
		EditCommand editCommand = new EditCommand(
				domain, "Edit "
						+ edited.eClass().getName(), edited, state);
		domain.getCommandStack().execute(editCommand);
		edited.eNotify(new ViewerNotification(
			new ENotificationImpl(
				(InternalEObject) edited, Notification.SET, 
					edited.eClass().getEAllStructuralFeatures().get(0).getFeatureID(), null, edited), 
			edited, false, true));
	}

	public void rollback(EObject edited) {
		state.copyStateTo(edited);
		enableNotifications(edited, true);
	}

	protected void enableNotifications(EObject edited, boolean deliver) {
		edited.eSetDeliver(deliver);
	}
}
