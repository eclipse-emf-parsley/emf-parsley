/**
 * 
 */
package org.eclipse.emf.parsley.tests.providers;

import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.*;

import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

/**
 * @author bettini
 * 
 */
public class LibraryEStructuralFeaturesAsStringsProvider extends
		FeaturesProvider {

	@Override
	protected void buildMap(EClassToEStructuralFeatureMap map) {
		super.buildMap(map);
		map.mapTo(LIBRARY, LIBRARY__NAME, ADDRESSABLE__ADDRESS);
	}

	@Override
	protected void buildStringMap(
			EClassToEStructuralFeatureAsStringsMap stringMap) {
		super.buildStringMap(stringMap);
		stringMap.mapTo(PERSON.getInstanceClassName(), "firstName", "lastName",
				"address");
		stringMap.mapTo(WRITER.getInstanceClassName(), "firstName", "lastName",
				"books");

	}

}
