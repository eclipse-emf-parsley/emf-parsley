package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.binding.ProposalCreator;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.emf.parsley.tests.binding.CustomLibraryFormControlFactory;
import org.eclipse.emf.parsley.tests.binding.CustomLibraryProposalCreator;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryFeatureCaptionProvider;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryFormFeatureCaptionProvider;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryLabelProvider;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryTableColumnLabelProvider;
import org.eclipse.emf.parsley.tests.providers.TestFeaturesColumnProvider;
import org.eclipse.emf.parsley.tests.providers.TestFeaturesProvider;
import org.eclipse.emf.parsley.tests.resource.TestEmptyLibraryResourceInitializer;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesColumnProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class CustomLibraryModule extends EmfParsleyGuiceModule {
	public CustomLibraryModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends ILabelProvider> bindILabelProvider() {
		return CustomLibraryLabelProvider.class;
	}

	@Override
	public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
		return CustomLibraryFeatureCaptionProvider.class;
	}

	@Override
	public Class<? extends FormFeatureCaptionProvider> bindFormFeatureCaptionProvider() {
		return CustomLibraryFormFeatureCaptionProvider.class;
	}

	@Override
	public Class<? extends FormControlFactory> bindFormControlFactory() {
		return CustomLibraryFormControlFactory.class;
	}

	@Override
	public Class<? extends ProposalCreator> bindProposalCreator() {
		return CustomLibraryProposalCreator.class;
	}

	@Override
	public Class<? extends TableColumnLabelProvider> bindTableColumnLabelProvider() {
		return CustomLibraryTableColumnLabelProvider.class;
	}

	@Override
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return TestEmptyLibraryResourceInitializer.class;
	}

	@Override
	public Class<? extends FeaturesProvider> bindFeaturesProvider() {
		return TestFeaturesProvider.class;
	}

	@Override
	public Class<? extends FeaturesColumnProvider> bindFeaturesColumnProvider() {
		return TestFeaturesColumnProvider.class;
	}
}