/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.factories;


import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.tests.swtbot.EmfParsleyGuiceModuleWithConfigurator;
import org.eclipse.emf.parsley.tests.swtbot.binding.CustomLibraryFormControlFactory;
import org.eclipse.emf.parsley.tests.swtbot.binding.CustomLibraryProposalCreator;
import org.eclipse.emf.parsley.tests.swtbot.labeling.CustomLibraryFeatureCaptionProvider;
import org.eclipse.emf.parsley.tests.swtbot.labeling.CustomLibraryFormFeatureCaptionProvider;
import org.eclipse.emf.parsley.tests.swtbot.labeling.CustomLibraryLabelProvider;
import org.eclipse.emf.parsley.tests.swtbot.labeling.CustomLibraryTableColumnLabelProvider;
import org.eclipse.emf.parsley.tests.swtbot.providers.TestFeaturesProvider;
import org.eclipse.emf.parsley.tests.swtbot.providers.TestTableFeaturesProvider;
import org.eclipse.emf.parsley.tests.swtbot.resource.TestLibraryResourceManager;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class CustomLibraryModule extends EmfParsleyGuiceModuleWithConfigurator {
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
	public Class<? extends ResourceManager> bindResourceManager() {
		return TestLibraryResourceManager.class;
	}

	@Override
	public Class<? extends FeaturesProvider> bindFeaturesProvider() {
		return TestFeaturesProvider.class;
	}

	@Override
	public Class<? extends TableFeaturesProvider> bindTableFeaturesProvider() {
		return TestTableFeaturesProvider.class;
	}
}