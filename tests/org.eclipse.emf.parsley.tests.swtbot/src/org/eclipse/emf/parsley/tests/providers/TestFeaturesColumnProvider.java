/**
 * 
 */
package org.eclipse.emf.parsley.tests.providers;

import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM;
import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__DAMAGED;

import org.eclipse.emf.parsley.examples.library.BookOnTape;
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.FeaturesColumnProvider;


/**
 * @author bettini
 * 
 */
public class TestFeaturesColumnProvider extends FeaturesColumnProvider {

	@Override
	protected void buildMap(EClassToEStructuralFeatureMap map) {
		super.buildMap(map);
		map.mapTo(AUDIO_VISUAL_ITEM, AUDIO_VISUAL_ITEM__DAMAGED);
	}

	@Override
	protected void buildStringMap(
			EClassToEStructuralFeatureAsStringsMap stringMap) {
		super.buildStringMap(stringMap);
		stringMap.mapTo(BookOnTape.class.getName(), "reader");
	}
}
