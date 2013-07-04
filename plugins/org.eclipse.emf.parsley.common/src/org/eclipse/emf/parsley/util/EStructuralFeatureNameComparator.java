package org.eclipse.emf.parsley.util;

import java.util.Comparator;

import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureNameComparator implements
		Comparator<EStructuralFeature> {
	public int compare(EStructuralFeature o1, EStructuralFeature o2) {
		return nullSafe(o1).compareTo(nullSafe(o2));
	}

	private String nullSafe(EStructuralFeature o) {
		String name = o.getName();
		return name != null ? name : "";
	}
}