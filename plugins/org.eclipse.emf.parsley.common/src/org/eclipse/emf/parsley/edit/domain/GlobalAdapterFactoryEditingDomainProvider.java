/**
 * 
 */
package org.eclipse.emf.parsley.edit.domain;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

/**
 * This ensures that only one instance of {@link AdapterFactoryEditingDomain} will
 * be used.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class GlobalAdapterFactoryEditingDomainProvider extends DefaultAdapterFactoryEditingDomainProvider {

	protected static AdapterFactoryEditingDomain singleton;
	
	public AdapterFactoryEditingDomain get() {
		if (singleton == null)
			singleton = super.get();
		return singleton;
	}

}
