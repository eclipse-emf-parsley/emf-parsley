/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.google.inject.Inject;

/**
 * With this strategy the edited {@link EObject} is the original
 * object itself, thus every update will be immediate.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class OnTheFlyEditingStrategy implements IEditingStrategy {
	
	@Inject
	private EditingDomainFinder editingDomainFinder;

	public void prepare(EObject original) {

	}

	public void update(EObject edited) {
		// Nothing, it's already updated
	}

	public EditingDomain getEditingDomain(EObject edited) {
		return editingDomainFinder.getEditingDomainFor(edited);
	}

	public void rollback(EObject edited) {
	}

}
