/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.firstexample.ui.provider;

import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

@SuppressWarnings("all")
public class FeaturesProviderGen extends FeaturesProvider {
  @Override
  public void buildStringMap(final EClassToEStructuralFeatureAsStringsMap stringMap) {
    super.buildStringMap(stringMap);
    
    stringMap.mapTo("org.eclipse.emf.examples.extlibrary.Library",
      "name");
    stringMap.mapTo("org.eclipse.emf.examples.extlibrary.Writer",
      "name", "address", "books");
    stringMap.mapTo("org.eclipse.emf.examples.extlibrary.Book",
      "author", "title", "category");
  }
}
