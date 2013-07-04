package org.eclipse.emf.parsley.examples.firstexample.ui.provider;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.PropertyDescriptionProvider;

@SuppressWarnings("all")
public class PropertyDescriptionProviderGen extends PropertyDescriptionProvider {
  public String text_Book_author(final EStructuralFeature it) {
    return "Wrote by:";
  }
  
  public String text_Writer_name(final EStructuralFeature it) {
    return "Name:";
  }
}
