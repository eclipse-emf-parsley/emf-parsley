package org.eclipse.emf.parsley.examples.cdo.company.ui.products;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.edit.domain.GlobalAdapterFactoryEditingDomainProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Provider;


public class ProductsGuiceModule extends EmfParsleyGuiceModuleGen {

	public ProductsGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

}
