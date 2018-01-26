/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import com.google.inject.Singleton
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.composite.CompositeFactory
import org.eclipse.emf.parsley.composite.TreeFormComposite
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.viewers.ViewerFactory
import org.eclipse.jface.viewers.StructuredViewer
import org.eclipse.swt.SWT
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
class TreeFormCompositeTest extends AbstractEmfParsleyShellBasedTest {

	var TreeFormComposite treeFormComposite

	var VerifyViewerFactory viewerFactory

	/**
	 * Mockito does not seem to mock/spy ViewerFactory, so we have to
	 * do that manually
	 * (probably related to https://bugs.eclipse.org/bugs/show_bug.cgi?id=349164)
	 */
	@Singleton
	static class VerifyViewerFactory extends ViewerFactory {
		public var Object param = null
		override initialize(StructuredViewer viewer, Object object) {
			param = object
		}
	}

	@Before
	def void setupFactory() {
		val injector = createInjector(
				new EmfParsleyJavaGuiceModule() {
					def Class<? extends ViewerFactory> bindViewerFactory() {
						return VerifyViewerFactory
					}
				}
			)
		viewerFactory = injector.getInstance(ViewerFactory) as VerifyViewerFactory
		treeFormComposite = injector
			.getInstance(CompositeFactory)
			.createTreeFormComposite(shell, SWT.NONE)
	}

	@Test def void testUpdateWithNullObject() {
		treeFormComposite.update(null)
		viewerFactory.param.assertNull
	}

	@Test def void testUpdateWithObject() {
		val arg = new Object
		treeFormComposite.update(arg)
		arg.assertSame(viewerFactory.param)
	}
}
