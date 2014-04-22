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


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.widgets.TableFormComposite;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author Lorenzo Bettini
 * 
 */
public class TableFormFactory {

	@Inject
	protected MembersInjector<TableFormComposite> tableFormCompositeMembersInjector;

	@Inject
	public TableFormFactory() {

	}

	public TableFormComposite createTableFormMasterDetailComposite(
			Composite parent, int style, EClass type) {
		TableFormComposite tableFormComposite = new TableFormComposite(parent,style);
		tableFormCompositeMembersInjector.injectMembers(tableFormComposite);
		tableFormComposite.buildTable(type);
		return tableFormComposite;
	}
	
}
