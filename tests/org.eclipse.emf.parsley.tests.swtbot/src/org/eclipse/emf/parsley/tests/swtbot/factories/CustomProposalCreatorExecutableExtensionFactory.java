/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.factories;


import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.tests.swtbot.activator.EmfParsleySwtBotTestsActivator;
import org.eclipse.emf.parsley.tests.swtbot.binding.CustomProposalCreator;

/**
 * Uses a custom proposals provider
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomProposalCreatorExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(EmfParsleySwtBotTestsActivator.getDefault()) {

			@Override
			public Class<? extends ProposalCreator> bindProposalCreator() {
				return CustomProposalCreator.class;
			}

		};
	}

}
