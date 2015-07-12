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
package org.eclipse.emf.parsley.ui.provider;

/**
 * Provides labels for EStructuralFeatures for dialogs. With respect to the
 * superclass {@link FeatureCaptionProvider} you can also specify the Label,
 * besides its text. If a custom PropertyDescriptionProvider is provided
 * (through injection) then it tries to get the text also from that one, before
 * using the default text.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class DialogFeatureCaptionProvider extends FeatureLabelCaptionProvider {

}
