package org.eclipse.emf.parsley.examples.maven.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

@SuppressWarnings("all")
public class MavenLabelProvider extends ViewerLabelProvider {
  @Inject
  public MavenLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final EClass it) {
    return it.getInstanceClassName();
  }
  
  public Object image(final EObject it) {
    return "test.png";
  }
  
  public Font font(final EObject it) {
    return null;
  }
  
  public Color foreground(final EObject it) {
    return null;
  }
  
  public Color background(final EObject it) {
    return null;
  }
}
