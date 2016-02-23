package org.eclipse.emf.parsley.examples.mail.messageview.ui.provider;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;

@SuppressWarnings("all")
public class MessageviewFeatureCaptionProvider extends FeatureCaptionProvider {
  public String text_Mail_recipients(final EStructuralFeature it) {
    return "to";
  }
}
