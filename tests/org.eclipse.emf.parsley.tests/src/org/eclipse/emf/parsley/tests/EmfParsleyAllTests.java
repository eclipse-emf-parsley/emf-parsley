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
package org.eclipse.emf.parsley.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Lorenzo Bettini
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ProtectedConstructorTest.class,
	PolymorphicDispatcherTest.class,
	EmfParsleyUtilTest.class,
	EmfSelectionHelperTest.class,
	EmfEventHelperTest.class,
	ResourceLoaderTest.class,
	ResourceSaveStrategyTest.class,
	EditingDomainTest.class,
	EditingDomainFinderTest.class,
	EditingDomainStrategyTest.class,
	AdapterFactoryHelperTest.class,
	ViewerContentProviderTest.class,
	TableViewerContentProviderTest.class,
	TableViewerContentProviderFactoryTest.class,
	FeaturesProviderTest.class,
	TableFeaturesProviderTest.class,
	FeatureCaptionProviderTest.class,
	ProposalCreatorTest.class,
	DialogFeatureCaptionProviderTest.class,
	FormFeatureCaptionProviderTest.class,
	InjectableAdapterFactoryLabelProviderTest.class,
	ViewerLabelProviderTest.class,
	DelegatingColumnLabelProviderTest.class,
	TableColumnLabelProviderTest.class,
	FeatureHelperTest.class,
	DialogWidgetFactoryTest.class,
	FormWidgetFactoryTest.class,
	TextUndoRedoTest.class,
	DialogControlFactoryAsWidgetFactoryTest.class,
	DialogControlFactoryTest.class,
	CustomDialogControlFactoryTest.class,
	FormControlFactoryTest.class,
	FormControlFactoryWithEditingDomainTest.class,
	LayoutHelperTest.class,
	TableViewerBuilderTest.class,
	TableViewerColumnBuilderTest.class,
	TableViewerEditingSupportTest.class,
	TableViewerCellEditorFactoryTest.class,
	TreeFormFactoryTest.class,
	FeatureNamePathTest.class,
	FeatureResolverTest.class,
	ConfiguratorTest.class,
	NotificationBufferTest.class,
	EcoreUtil2Test.class,
	EditingMenuBuilderTest.class,
	ClassLoaderImageHelperTest.class,
	ShellBasedTest.class,
	ControlBasedTest.class,
	ValidationRunnerTest.class,
	AsyncCommandStackListenerTest.class
})
public class EmfParsleyAllTests {

}
