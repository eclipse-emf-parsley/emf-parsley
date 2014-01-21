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
package org.eclipse.emf.parsley.dsl.tests.util;

/**
 * Utility class to specify expectations from the compiler.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class GeneratorExpectedResults {
	public CharSequence expectedModule;
	public CharSequence expectedLabelProvider;
	public CharSequence expectedFeatureCaptionProvider;
	public CharSequence expectedFormFeatureCaptionProvider;
	public CharSequence expectedDialogFeatureCaptionProvider;
	public CharSequence expectedFeatureProvider;
	public CharSequence expectedFormControlFactory;
	public CharSequence expectedDialogControlFactory;
	public CharSequence expectedViewerContentProvider;
	public CharSequence expectedProposalCreator;
	public CharSequence expectedPluginXmlGen;
}
