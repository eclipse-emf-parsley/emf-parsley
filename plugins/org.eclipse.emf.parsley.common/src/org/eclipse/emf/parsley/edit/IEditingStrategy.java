/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Represents the strategy for preparing an {@link EObject} for editing
 * and for updating it.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public interface IEditingStrategy {

	void prepare(EObject original);
	
	void update(EObject edited);
	
	void rollback(EObject edited);
	
	EditingDomain getEditingDomain(EObject edited);
}
