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
package org.eclipse.emf.parsley.composite;


import java.util.List;

import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;
import com.google.inject.name.Named;

/**
 * @author Lorenzo Bettini
 *
 */
public class TreeFormFactory {

	@Inject
	protected MembersInjector<TreeFormComposite> treeFormCompositeMembersInjector;

	@Inject
	@Named(EmfParsleyConstants.TREE_FORM_SASH_STYLE)
	private int sashStyle;

	@Inject
	@Named(EmfParsleyConstants.TREE_FORM_SASH_WEIGHTS)
	private List<Integer> weights;

	@Inject
	public TreeFormFactory() {

	}

	/**
	 * This method is not intended to be extended. Use {link {@link #createComposite(Composite, int)} to customize the creation of the {@link TreeFormComposite}.
	 * @return the TreeFormComposite instance
	 */
	public TreeFormComposite createTreeFormComposite(Composite parent, int style) {
		TreeFormComposite treeFormComposite = createComposite(parent, style);
		treeFormCompositeMembersInjector.injectMembers(treeFormComposite);
		return treeFormComposite;
	}

	protected TreeFormComposite createComposite(Composite parent, int style) {
		return new TreeFormComposite(parent, style, sashStyle, EmfParsleyUtil.toIntArray(weights));
	}
}
