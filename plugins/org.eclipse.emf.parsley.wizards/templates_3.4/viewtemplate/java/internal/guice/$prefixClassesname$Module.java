package $packageName$.internal.guice;


import $packageName$.$prefixClassesname$ViewConfigurator;
import $packageName$.$prefixClassesname$CDOSessionManager;
import org.eclipse.emf.parsley.cdo.CDOEmfParsleyModule;
import org.eclipse.emf.parsley.cdo.CDOSessionManager;
import org.eclipse.emf.parsley.view.masterdetail.ViewConfigurator;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.ui.provider.FormPropertyDescriptionProvider;

import org.eclipse.ui.plugin.AbstractUIPlugin;

public class $prefixClassesname$Module extends CDOEmfParsleyModule {

	public $prefixClassesname$Module(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public Class<? extends ViewConfigurator> bindViewConfigurator(){
		return $prefixClassesname$ViewConfigurator.class;
	}
	
	public Class<? extends CDOSessionManager> bindCDOSessionManager(){
		return $prefixClassesname$CDOSessionManager.class;
	}
	
	@Override
	public Class<? extends FeatureLabelProvider> bindFormFeatureLabelProvider() {
		return $prefixClassesname$FeatureLabelProvider.class;
	}
	
	@Override
	public Class<? extends EmfSwtBindingFactory> bindEmfSwtBindingFactory() {
		return $prefixClassesname$BindingFactory.class;
	}
	
}
