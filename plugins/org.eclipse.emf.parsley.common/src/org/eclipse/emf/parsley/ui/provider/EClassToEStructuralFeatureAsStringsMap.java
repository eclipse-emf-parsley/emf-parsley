package org.eclipse.emf.parsley.ui.provider;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

public class EClassToEStructuralFeatureAsStringsMap extends
		HashMap<String, List<String>> {

	private static final long serialVersionUID = -5838485782229839444L;

	public void mapTo(String eClass, String... features) {
		put(eClass, Lists.newArrayList(features));
	}

//	put(eClass, Lists.newArrayList(Iterables.transform(
//			Lists.newArrayList(features),
//			new Function<String, FeatureNamePath>() {
//
//				public FeatureNamePath apply(String input) {
//					return new FeatureNameSinglePath(input);
//				}
//			})));
}