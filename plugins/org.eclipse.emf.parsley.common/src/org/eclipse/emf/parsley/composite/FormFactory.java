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


import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class FormFactory {

	@Inject
	protected MembersInjector<FormDetailComposite> formDetailCompositeMembersInjector;
	
	@Inject
	protected MembersInjector<FormDetailReadOnlyComposite> formDetailReadOnlyCompositeMembersInjector;

	@Inject
	public FormFactory() {

	}

	public FormDetailComposite createFormDetailComposite(Composite parent,
			int style) {
		FormDetailComposite formDetailComposite = new FormDetailComposite(parent, style);
		formDetailCompositeMembersInjector.injectMembers(formDetailComposite);
		return formDetailComposite;
	}
	
	public FormDetailReadOnlyComposite createFormDetailReadOnlyComposite(Composite parent,
			int style) {
		FormDetailReadOnlyComposite formDetailComposite = new FormDetailReadOnlyComposite(parent, style);
		formDetailReadOnlyCompositeMembersInjector.injectMembers(formDetailComposite);
		return formDetailComposite;
	}

}
