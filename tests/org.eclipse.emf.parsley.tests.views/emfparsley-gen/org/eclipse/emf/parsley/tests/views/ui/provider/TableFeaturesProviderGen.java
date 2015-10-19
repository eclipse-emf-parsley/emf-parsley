package org.eclipse.emf.parsley.tests.views.ui.provider;

import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;

@SuppressWarnings("all")
public class TableFeaturesProviderGen extends TableFeaturesProvider {
  @Override
  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
    super.buildStringMap(stringMap);
    
    stringMap.mapTo("org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls",
      "booleanFeature", "booleanObjectFeature", "booleanDataTypeFeature", "booleanPrimitiveDataTypeFeature", "enumFeature", "stringFeature", "stringDataTypeFeature", "stringFeatureWithDefault", "bigDecimalFeature", "bigIntegerFeature", "doubleFeature", "doubleObjectFeature", "intFeature", "intObjectFeature", "floatFeature", "floatObjectFeature", "shortFeature", "shortObjectFeature", "byteFeature", "byteObjectFeature", "dateFeature");
  }
}
