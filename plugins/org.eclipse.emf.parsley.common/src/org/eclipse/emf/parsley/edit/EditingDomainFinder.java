/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Retrieves the EditingDomain for an EObject. The default implementation simply
 * uses {@link AdapterFactoryEditingDomain} getEditingDomainFor.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EditingDomainFinder {

	public EditingDomain getEditingDomainFor(EObject object) {
		return AdapterFactoryEditingDomain.getEditingDomainFor(object);
	}
}
