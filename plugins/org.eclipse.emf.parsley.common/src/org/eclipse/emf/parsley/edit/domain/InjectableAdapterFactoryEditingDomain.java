/**
 * 
 */
package org.eclipse.emf.parsley.edit.domain;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini
 * 
 *         Injectable {@link AdapterFactoryEditingDomain}
 */
public class InjectableAdapterFactoryEditingDomain extends
		AdapterFactoryEditingDomain {

	@Inject
	public InjectableAdapterFactoryEditingDomain(AdapterFactory adapterFactory) {
		super(adapterFactory, new BasicCommandStack(),
				new HashMap<Resource, Boolean>());

		getResourceSet().getURIConverter().getURIMap()
				.putAll(EcorePlugin.computePlatformURIMap());
	}
}
