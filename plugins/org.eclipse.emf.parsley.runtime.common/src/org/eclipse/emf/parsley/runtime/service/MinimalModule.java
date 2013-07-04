/**
 * 
 */
package org.eclipse.emf.parsley.runtime.service;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Binder;

/**
 * @author bettini
 * 
 */
public class MinimalModule extends AbstractGenericModule {

	private final AbstractUIPlugin plugin;

	public MinimalModule(AbstractUIPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(AbstractUIPlugin.class).toInstance(plugin);
		binder.bind(IDialogSettings.class).toInstance(
				plugin.getDialogSettings());
	}
}
