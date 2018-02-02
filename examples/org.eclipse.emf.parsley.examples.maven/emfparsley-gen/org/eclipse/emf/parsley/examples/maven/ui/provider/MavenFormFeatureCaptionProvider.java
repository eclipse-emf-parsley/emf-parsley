package org.eclipse.emf.parsley.examples.maven.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;

@SuppressWarnings("all")
public class MavenFormFeatureCaptionProvider extends FormFeatureCaptionProvider {
  @Inject
  public MavenFormFeatureCaptionProvider(final FormToolkitParameter formToolkitParameter) {
    super(formToolkitParameter);
  }
}
