/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.factories;


import org.eclipse.emf.parsley.widgets.TreeFormComposite;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author Lorenzo Bettini
 * 
 */
public class TreeFormFactory {

	@Inject
	protected MembersInjector<TreeFormComposite> treeFormCompositeMembersInjector;

	@Inject
	public TreeFormFactory() {

	}

	public TreeFormComposite createTreeFormMasterDetailComposite(
			Composite parent, int style) {
		TreeFormComposite treeFormComposite = new TreeFormComposite(parent,
				style);
		treeFormCompositeMembersInjector.injectMembers(treeFormComposite);
		return treeFormComposite;
	}
}
