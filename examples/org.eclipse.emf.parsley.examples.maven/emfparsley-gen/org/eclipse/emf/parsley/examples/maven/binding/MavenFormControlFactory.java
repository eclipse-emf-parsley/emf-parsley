package org.eclipse.emf.parsley.examples.maven.binding;

import com.google.inject.Inject;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;

@SuppressWarnings("all")
public class MavenFormControlFactory extends FormControlFactory {
  @Inject
  public MavenFormControlFactory(final CompositeParameter compositeParameter, final EObjectParameter eObjectParameter, final FormToolkitParameter formToolkitParameter) {
    super(compositeParameter, eObjectParameter, formToolkitParameter);
  }
}
