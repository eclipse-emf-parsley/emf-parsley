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
 
package org.eclipse.emf.parsley.examples.eclipse4.parsleypart.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ShowParsleyPartHandler {

	@Inject
	private EPartService partService;
	
	@Execute
	public void execute(MApplication application) {
		MPart part = partService.findPart("org.eclipse.emf.parsley.examples.eclipse4.parsleypart.part.0");
		part.setVisible(true);
		partService.showPart(part, EPartService.PartState.VISIBLE);
	}

}