/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.providers;

import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM;
import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__DAMAGED;

import org.eclipse.emf.parsley.examples.library.BookOnTape;
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;


/**
 * @author Lorenzo Bettini
 * 
 */
public class TestTableFeaturesProvider extends TableFeaturesProvider {

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
