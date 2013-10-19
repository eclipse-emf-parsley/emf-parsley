/**
 * 
 */
package org.eclipse.emf.parsley.edit.domain;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class DefaultAdapterFactoryEditingDomainProvider implements Provider<AdapterFactoryEditingDomain> {

	@Inject
	protected Provider<AdapterFactory> adapterFactoryProvider;
	
	public AdapterFactoryEditingDomain get() {
		return new InjectableAdapterFactoryEditingDomain(adapterFactoryProvider.get());
	}

}
