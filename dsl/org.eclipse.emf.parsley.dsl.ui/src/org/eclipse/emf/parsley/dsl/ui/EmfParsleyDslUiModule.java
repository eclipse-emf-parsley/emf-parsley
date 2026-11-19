/**
 * Copyright (c) 2023 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.ui;

import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.template.DefaultTemplateProjectCreator;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
public class EmfParsleyDslUiModule extends AbstractEmfParsleyDslUiModule {

	public EmfParsleyDslUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends IProjectCreator> bindIProjectCreator() {
		return DefaultTemplateProjectCreator.class;
	}
}
