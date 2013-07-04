/**
 * 
 */
package org.eclipse.emf.parsley.tests.providers;

import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.*;

import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

/**
 * @author bettini
 * 
 */
public class LibraryEStructuralFeaturesProvider extends
		FeaturesProvider {

	@Override
	protected void buildMap(EClassToEStructuralFeatureMap map) {
		super.buildMap(map);
		map.mapTo(LIBRARY,
				LIBRARY__NAME, ADDRESSABLE__ADDRESS);
		map.mapTo(PERSON,
				PERSON__FIRST_NAME, PERSON__LAST_NAME,
				ADDRESSABLE__ADDRESS);
		map.mapTo(WRITER,
				PERSON__FIRST_NAME, PERSON__LAST_NAME,
				WRITER__BOOKS);
	}

}
