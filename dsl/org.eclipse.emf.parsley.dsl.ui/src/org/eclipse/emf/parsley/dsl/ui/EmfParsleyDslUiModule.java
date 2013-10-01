/*
 * generated by Xtext
 */
package org.eclipse.emf.parsley.dsl.ui;

import org.eclipse.emf.parsley.dsl.ui.builder.EmfParsleyDslDerivedResourceMarkers;
import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslNewProjectWithPredefinedViewWizard;
import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslNewProjectWizard;
import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslProjectCreatorCustom;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.generator.IDerivedResourceMarkers;
import org.eclipse.xtext.ui.wizard.IProjectCreator;

/**
 * Use this class to register components to be used within the IDE.
 */
public class EmfParsleyDslUiModule extends org.eclipse.emf.parsley.dsl.ui.AbstractEmfParsleyDslUiModule {
	public EmfParsleyDslUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public Class<? extends IProjectCreator> bindIProjectCreator() {
		return EmfParsleyDslProjectCreatorCustom.class;
	}
	
	public Class<? extends IDerivedResourceMarkers> bindIDerivedResourceMarkers() {
		return EmfParsleyDslDerivedResourceMarkers.class;
	}
	
	public Class<? extends EmfParsleyDslNewProjectWizard> bindEmfParsleyDslNewProjectWizard() {
		return EmfParsleyDslNewProjectWithPredefinedViewWizard.class;
	}
}
