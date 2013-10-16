/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.ecore.EObject;

/**
 * Represents the strategy for preparing an {@link EObject} for editing
 * and for updating it.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public interface IEditingStrategy {

	EObject prepare(EObject original);
	
	void update(EObject original, EObject edited);
}
