/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.eclipse4.parsleypart.ui.provider;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;

@SuppressWarnings("all")
public class FeatureCaptionProviderGen extends FeatureCaptionProvider {
  public String text_TrimmedWindowImpl_elementId(final EStructuralFeature it) {
    return "Wrote by:";
  }
  
  public String text_TrimmedWindowImpl_tags(final EStructuralFeature it) {
    return "Tags:";
  }
}
