/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package $packageName$.internal.guice;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import org.eclipse.emf.parsley.ui.provider.FormPropertyDescriptionProvider;

public class $prefixClassesname$FeatureLabelProvider extends FeatureLabelProvider{
	
	/**
	 * 
	 * @param feature
	 * @return
	 */
	public String text_$prefixClassesname$_code(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return "Code: ";
	}
	
	/**
	 * 
	 * @param parent
	 * @param feature
	 * @return
	 */
	public Label label_$prefixClassesname$_description(Composite parent, EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return new Label(parent, SWT.NONE);
	}

	
}
