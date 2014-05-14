package org.eclipse.emf.parsley.examples.cdo.company;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.cdo.CDOEmfParsleyModule;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.domain.GlobalAdapterFactoryEditingDomainProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Provider;


public class CompanyGuiceModule extends CDOEmfParsleyModule {

	public CompanyGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends Provider<AdapterFactoryEditingDomain>> provideAdapterFactoryEditingDomain() {
		return GlobalAdapterFactoryEditingDomainProvider.class;
	}
	
	@Override
	public Class<? extends IEditingStrategy> bindIEditingStrategy() {
		return UndoableEditingStrategy.class;
	}
}
