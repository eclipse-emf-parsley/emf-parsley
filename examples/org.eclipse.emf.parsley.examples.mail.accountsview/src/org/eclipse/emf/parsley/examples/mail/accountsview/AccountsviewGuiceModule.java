package org.eclipse.emf.parsley.examples.mail.accountsview;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.domain.GlobalAdapterFactoryEditingDomainProvider;
import org.eclipse.emf.parsley.examples.mail.accountsview.custom.MailEmptyResourceInitializer;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Provider;


public class AccountsviewGuiceModule extends EmfParsleyGuiceModuleGen {

	public AccountsviewGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return MailEmptyResourceInitializer.class;
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
