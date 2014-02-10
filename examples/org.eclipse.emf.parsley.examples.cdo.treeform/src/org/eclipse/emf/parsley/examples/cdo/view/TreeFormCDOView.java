/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.cdo.view;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;

public class TreeFormCDOView extends AbstractSaveableTreeFormView {

	public static final String ID = "org.eclipse.emf.parsley.examples.cdo.treeformview";
	
	@Override
	protected URI createResourceURI() {
		return URI.createURI("cdo://localhost:2037/demo/myResource/");
	}

}
