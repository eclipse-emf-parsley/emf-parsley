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
package org.eclipse.emf.parsley.tests.swtbot.examples;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyMailExampleTest {

	protected static SWTWorkbenchBot bot;

	@BeforeClass
	public static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot();
	}

	@Test
	public void testExampleMail() {
		bot.waitUntil(new DefaultCondition() {
			@Override
			public boolean test() throws Exception {
				System.out.println("*** expanding mail tree...");
				try {
					bot.tree().getTreeItem("lorenzo@foobar").expand().getNode("Inbox").select();
				} catch (WidgetNotFoundException e) {
					System.out.println("### " + e.getMessage());
					return false;
				}
				return true;
			}
			@Override
			public String getFailureMessage() {
				return "Could not find tree";
			}
		});
		bot.table().select(0);
		SWTFormsBot formbot = new SWTFormsBot(bot.viewByTitle("Mail Message View").getWidget());
		formbot.label("From");
	}

}
