package org.eclipse.emf.parsley.examples.library;


import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.PropertyDescriptionProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class EmfParsleyGuiceModule extends org.eclipse.emf.parsley.EmfParsleyGuiceModule {

	public EmfParsleyGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends ILabelProvider> bindILabelProvider() {
		return CustomLabelProvider.class;
	}

	@Override
	public Class<? extends PropertyDescriptionProvider> bindPropertyDescriptionProvider() {
		return CustomFeatureLabelProvider.class;
	}

	@Override
	public Class<? extends FeaturesProvider> bindFeaturesProvider() {
		return CustomEStructuralFeaturesProvider.class;
	}

}
