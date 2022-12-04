/*******************************************************************************
 * Copyright (c) 2022 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.utils;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

public class WaitForBuildCondition extends DefaultCondition {
	boolean done = false;
	private IWorkspace workspace;
	private IResourceChangeListener myResourceChangeListener;
	private SWTWorkbenchBot bot;

	public WaitForBuildCondition(SWTWorkbenchBot bot) {
		this.bot = bot;
		this.workspace = ResourcesPlugin.getWorkspace();
	}

	public void waitForBuild() {
		try {
			bot.waitUntil(this);
		} finally {
			removeListener();
		}
	}

	@Override
	public boolean test() {
		return done;
	}

	void done() {
		this.done = true;
	}

	public void startListenForBuild() {
		myResourceChangeListener = new IResourceChangeListener() {
			@Override
			public void resourceChanged(IResourceChangeEvent event) {
				if (event.getBuildKind() == IResourceChangeEvent.POST_BUILD)
					WaitForBuildCondition.this.done();
			}
		};
		workspace.addResourceChangeListener(myResourceChangeListener);
	}

	public void removeListener() {
		workspace.removeResourceChangeListener(myResourceChangeListener);
		myResourceChangeListener = null;
	}

	@Override
	public String getFailureMessage() {
		return "failed waiting for building";
	}
}
