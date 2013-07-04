/**
 * 
 */
package org.eclipse.emf.parsley.edit.provider;

import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini
 * 
 *         based on the homonymous class in xtext.ui
 * 
 */
public class InjectableAdapterFactory extends ComposedAdapterFactory {

	@Inject
	public InjectableAdapterFactory(Registry adapterFactoryDescriptorRegistry) {
		super(adapterFactoryDescriptorRegistry);

		addAdapterFactory(new ResourceItemProviderAdapterFactory());
		addAdapterFactory(new EcoreItemProviderAdapterFactory());
		addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

}
