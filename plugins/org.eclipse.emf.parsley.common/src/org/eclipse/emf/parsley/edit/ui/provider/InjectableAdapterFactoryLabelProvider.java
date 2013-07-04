/**
 * 
 */
package org.eclipse.emf.parsley.edit.ui.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini
 * 
 *         based on the homonymous class in xtext.ui
 * 
 */
public class InjectableAdapterFactoryLabelProvider extends
		AdapterFactoryLabelProvider {

	@Inject
	public InjectableAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory
				.adapt(object, IItemLabelProvider.class);
		return itemLabelProvider != null ? itemLabelProvider.getText(object)
				: object == null ? "" : object.toString();
	}
}
