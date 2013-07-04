package org.eclipse.emf.parsley.examples.mail.accountsview;

import org.eclipse.emf.parsley.examples.mail.accountsview.EmfComponentsGuiceModuleGen;

import org.eclipse.emf.parsley.examples.mail.accountsview.custom.MailEmptyResourceInitializer;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class AccountsviewGuiceModule extends EmfComponentsGuiceModuleGen {

	public AccountsviewGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return MailEmptyResourceInitializer.class;
	}
}
