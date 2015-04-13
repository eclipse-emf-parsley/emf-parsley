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
