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
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.composite.CompositeFactory
import org.eclipse.emf.parsley.composite.TableFormComposite
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.viewers.ViewerFactory
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.SWT
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
class TableFormCompositeTest extends AbstractEmfParsleyShellBasedTest {

	var TableFormComposite tableFormComposite

	var VerifyViewerFactory viewerFactory

	val type = EcorePackage.eINSTANCE.EObject

	/**
	 * Mockito does not seem to mock/spy ViewerFactory, so we have to
	 * do that manually
	 * (probably related to https://bugs.eclipse.org/bugs/show_bug.cgi?id=349164)
	 */
	@Singleton
	static class VerifyViewerFactory extends ViewerFactory {
		public var TableViewer viewer
		public var EClass type = null
		override buildColumns(TableViewer viewer, EClass type) {
			this.viewer = viewer
			this.type = type
			super.buildColumns(viewer, type)
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
		tableFormComposite = injector
			.getInstance(CompositeFactory)
			.createTableFormComposite(shell, SWT.NONE, type)
		type.assertSame(viewerFactory.type)
	}

	@Test def void testUpdateWithNullObject() {
		tableFormComposite.update(null)
		viewerFactory.viewer.input.assertNull
	}

	@Test def void testUpdateWithObject() {
		val arg = new Object
		tableFormComposite.update(arg)
		arg.assertSame(viewerFactory.viewer.input)
	}
}
