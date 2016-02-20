package org.eclipse.emf.parsley.examples.firstexample.ui.provider;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;

@SuppressWarnings("all")
public class FirstexampleFeatureCaptionProviderGen extends FeatureCaptionProvider {
  public String text_Book_author(final EStructuralFeature it) {
    return "Written by:";
  }
  
  public String text_Writer_name(final EStructuralFeature it) {
    return "Name:";
  }
}
