/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.edit.EMFEditPlugin
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry

import static org.eclipse.emf.parsley.tests.ui.util.TestImageHelper.*

abstract class AbstractImageBasedTest extends AbstractShellBasedTest {

	val protected TEST_IMAGE = "test_image.png"

	def protected getDelegateLabelProvider() {
		getOrCreateInjector.getInstance(AdapterFactoryLabelProvider)
	}

	protected def loadTestImage() {
		loadImageFromLocalTest(TEST_IMAGE)
	}

	protected def getDefaultEMFImageForClassForControls() {
		getEMFImageFromObject(getEMFImage(testFactory.createClassForControls))
	}

	protected def getDefaultEMFImageForClassForFeatureMapEntry1() {
		getEMFImageFromObject(getEMFImage(testFactory.createClassForFeatureMapEntry1))
	}

	protected def getEMFImageFromObject(Object object) {
		return ExtendedImageRegistry.INSTANCE.getImage(object);
	}

	protected def getEMFImage(EObject eObject) {
		val eClass = eObject.eClass();
		return URI.createURI(getEMFResourceLocator().getImage("full/obj16/Item").toString() + "#" + eClass.getName());
	}

	protected def getEMFResourceLocator() {
		return EMFEditPlugin.INSTANCE;
	}
}
