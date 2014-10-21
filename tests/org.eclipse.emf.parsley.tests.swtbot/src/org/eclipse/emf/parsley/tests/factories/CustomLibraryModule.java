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
package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.binding.ProposalCreator;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.emf.parsley.tests.EmfParsleyGuiceModuleWithConfigurator;
import org.eclipse.emf.parsley.tests.binding.CustomLibraryFormControlFactory;
import org.eclipse.emf.parsley.tests.binding.CustomLibraryProposalCreator;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryFeatureCaptionProvider;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryFormFeatureCaptionProvider;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryLabelProvider;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryTableColumnLabelProvider;
import org.eclipse.emf.parsley.tests.providers.TestFeaturesProvider;
import org.eclipse.emf.parsley.tests.providers.TestTableFeaturesProvider;
import org.eclipse.emf.parsley.tests.resource.TestEmptyLibraryResourceInitializer;
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
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return TestEmptyLibraryResourceInitializer.class;
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