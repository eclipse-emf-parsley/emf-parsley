/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.views;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.factories.FormFactory;
import org.eclipse.emf.parsley.widgets.FormDetailComposite;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * A View that visualizes the list of elements of an emf selected resource (it
 * also acts as a selection provider).
 * 
 * @author Lorenzo Bettini
 * 
 */
public class OnSelectionFormView extends AbstractOnSelectionView {

	@Inject
	protected FormFactory formFactory;

	protected Composite parent;

	protected FormDetailComposite formComposite;
	
	public OnSelectionFormView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		this.parent = parent;
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection) {

		EObject eObject = getFirstSelectedEObject(selection);
		if (eObject != null) {
			resetFormComposite();
			
			formComposite = createFormDetailComposite();
			formComposite.init(eObject);
			
			parent.layout(true, true);
		}
	}

	protected FormDetailComposite createFormDetailComposite() {
		return formFactory.createFormDetailComposite(
				parent, SWT.NONE);
	}

	protected void resetFormComposite() {
		if (formComposite != null) {
			formComposite.dispose();
			parent.layout(true, true);
		}
	}

	@Override
	public void setFocus() {
		if (formComposite != null)
			formComposite.setFocus();
		else
			parent.setFocus();
	}

}
