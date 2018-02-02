/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter
import org.eclipse.emf.parsley.widgets.FormWidgetFactory

class FormWidgetFactoryTest extends DialogWidgetFactoryTest {

	override void setupWidgetFactory() {
		factory = new FormWidgetFactory(compositeParameter, new FormToolkitParameter(formToolkit)).injectMembers
	}

}
