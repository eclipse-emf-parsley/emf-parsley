/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.ecore.EObject;

/**
 * With this strategy the edited {@link EObject} is the original
 * object itself, thus every update will be immediate.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class OnTheFlyEditingStrategy implements IEditingStrategy {

	/* (non-Javadoc)
	 * @see org.eclipse.emf.parsley.edit.IEditingStrategy#prepare(org.eclipse.emf.ecore.EObject)
	 */
	public EObject prepare(EObject original) {
		return original;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.parsley.edit.IEditingStrategy#update(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 */
	public void update(EObject original, EObject edited) {
		// Nothing, it's already updated
	}

}
