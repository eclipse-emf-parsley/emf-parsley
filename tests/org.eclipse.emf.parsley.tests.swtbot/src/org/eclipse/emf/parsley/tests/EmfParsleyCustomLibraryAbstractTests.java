/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.emf.parsley.tests.factories.CustomLibraryExecutableExtensionFactory;
import org.eclipse.jface.viewers.ILabelProvider;

import com.google.inject.Injector;

/**
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyCustomLibraryAbstractTests extends
		EmfParsleyAbstractTests {

	protected Injector getInjector() {
		return EmfParsleyTestsActivator.getDefault().getInjector(
				CustomLibraryExecutableExtensionFactory.class);
	}

	protected void assertLabels(String expected, Object[] elements) {
		ILabelProvider labelProvider = getInjector().getInstance(
				ILabelProvider.class);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; i++) {
			buffer.append(labelProvider.getText(elements[i]) + "; ");
		}
		assertEquals(expected, buffer.toString());
	}

	protected void assertListLabels(String expected, List<?> elements) {
		ILabelProvider labelProvider = getInjector().getInstance(
				ILabelProvider.class);
		StringBuffer buffer = new StringBuffer();
		for (Object object : elements) {
			buffer.append(labelProvider.getText(object) + "; ");
		}
		assertEquals(expected, buffer.toString());
	}

}
