/**
 * 
 */
package org.eclipse.emf.parsley.tests.providers;

import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.*;

import org.eclipse.emf.parsley.examples.library.Periodical;
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;


/**
 * @author bettini
 * 
 */
public class TestFeaturesProvider extends FeaturesProvider {

	@Override
	protected void buildMap(EClassToEStructuralFeatureMap map) {
		super.buildMap(map);
		map.mapTo(LENDABLE, LENDABLE__COPIES);
	}

	@Override
	protected void buildStringMap(
			EClassToEStructuralFeatureAsStringsMap stringMap) {
		super.buildStringMap(stringMap);
		stringMap.mapTo(Periodical.class.getName(), "title");
	}
}
