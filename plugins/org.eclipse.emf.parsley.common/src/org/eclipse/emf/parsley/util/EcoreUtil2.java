/**
 * 
 */
package org.eclipse.emf.parsley.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class EcoreUtil2 {

	/**
	 * Clones the given EObject without resolving any proxies.
	 */
	public static <T extends EObject> T clone(T original) {
		return EcoreUtil.copy(original);
	}

	public static EObjectState copyState(EObject o) {
		return new EObjectState(o);
	}
}
