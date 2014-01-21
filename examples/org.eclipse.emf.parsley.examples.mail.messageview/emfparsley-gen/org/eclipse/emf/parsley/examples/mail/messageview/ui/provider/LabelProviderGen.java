/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.mail.messageview.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.mail.Mail;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Mail it) {
    String _subject = it.getSubject();
    return _subject;
  }
  
  public Object image(final Mail it) {
    return "email.png";
  }
}
