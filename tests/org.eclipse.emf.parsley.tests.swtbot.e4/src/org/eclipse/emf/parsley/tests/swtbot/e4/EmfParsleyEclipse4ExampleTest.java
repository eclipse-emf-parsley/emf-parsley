/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.e4;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swtbot.e4.finder.widgets.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyEclipse4ExampleTest {

	private SWTWorkbenchBot bot;

	@Before
	public void init() {
		bot = new SWTWorkbenchBot(getEclipseContext());
	}

	@Test
	public void testExampleE4Part() {
		/*
		SWTBotShell[] shells = bot.shells();
		for (SWTBotShell shell : shells) {
			System.out.println("### Shell: " + shell);
		}
		SWTBotShell shell = bot.shell("org.eclipse.emf.parsley.examples.eclipse4");
		System.out.println("### Clicking file menu");
		shell
			.menu()
			.menu("File").click();
		System.out.println("### Clicking our menu");
		shell
			.menu()
			.menu("Parsley")
			.menu("Show Parsley Part").click();
			*/
		bot.cTabItem("Eclipse4 Model").activate();
		bot.tree().getTreeItem("Trimmed Window").contextMenu("New Child").menu("Children Part");
	}

	/**
	 * IMPORTANT: for this to work and avoid a NullPointerException,
	 * we must have
	 * <pre>Bundle-ActivationPolicy: lazy</pre> in the
	 * <code>MANIFEST.MF</code>
	 * 
	 * @return
	 */
	protected static IEclipseContext getEclipseContext() {
		final IEclipseContext serviceContext =
			EclipseContextFactory
			.getServiceContext(
				FrameworkUtil.getBundle(Activator.class)
					.getBundleContext());
		return serviceContext.get(IWorkbench.class)
				.getApplication().getContext();
	}
}
