/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.views;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.tests.EmfParsleyAbstractTests;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView;

public class TestSaveableTreeView extends AbstractSaveableTreeView {

	@Override
	protected URI createResourceURI() {
		return URI.createPlatformResourceURI(
				EmfParsleyAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}

}
