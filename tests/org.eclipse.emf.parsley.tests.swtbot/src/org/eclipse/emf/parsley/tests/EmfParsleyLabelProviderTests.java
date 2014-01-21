/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyLabelProviderTests extends
		EmfParsleyCustomLibraryAbstractTests {

	@Test
	public void testFeatureMapEntryLabelProvider() {
		ILabelProvider labelProvider = createLabelProvider();

		Library library = createTestLibraryWithPeople();
		Entry person = library.getPeople().get(0);

		assertEquals("Writer My Writer", labelProvider.getText(person));
	}

	protected ILabelProvider createLabelProvider() {
		return getInjector().getInstance(ILabelProvider.class);
	}

	protected Library createTestLibraryWithPeople() {
		Library library = EXTLibraryFactory.eINSTANCE.createLibrary();
		library.setName("My Library");
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		writer.setName("My Writer");
		library.getWriters().add(writer);
		return library;
	}
}
