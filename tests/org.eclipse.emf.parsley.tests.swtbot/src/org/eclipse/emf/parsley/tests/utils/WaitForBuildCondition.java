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
package org.eclipse.emf.parsley.tests.utils;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

public class WaitForBuildCondition extends DefaultCondition {
	boolean done = false;
	private IWorkspace workspace;
	private MyResourceChangeListener myResourceChangeListener;

	public WaitForBuildCondition() {
		myResourceChangeListener = new MyResourceChangeListener(this);
		this.workspace = ResourcesPlugin.getWorkspace();
	}

	@Override
	public boolean test() {
		return done;
	}

	void done() {
		this.done = true;
	}

	public void removeListener() {
		workspace.removeResourceChangeListener(myResourceChangeListener);
	}

	public void addListener() {
		workspace.addResourceChangeListener(myResourceChangeListener);
	}

	@Override
	public String getFailureMessage() {
		return "failed waiting for building";
	}
}

class MyResourceChangeListener implements IResourceChangeListener {
	private WaitForBuildCondition condition;

	MyResourceChangeListener(WaitForBuildCondition condition) {
		this.condition = condition;
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getBuildKind() == IResourceChangeEvent.POST_BUILD)
			condition.done();
	}
}
