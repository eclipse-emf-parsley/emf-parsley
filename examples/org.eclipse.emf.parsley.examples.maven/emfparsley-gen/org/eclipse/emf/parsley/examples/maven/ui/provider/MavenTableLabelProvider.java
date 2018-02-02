package org.eclipse.emf.parsley.examples.maven.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.parsley.inject.parameters.EStructuralFeatureParameter;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;

@SuppressWarnings("all")
public class MavenTableLabelProvider extends TableColumnLabelProvider {
  @Inject
  public MavenTableLabelProvider(final EStructuralFeatureParameter eStructuralFeatureParameter) {
    super(eStructuralFeatureParameter);
  }
}
