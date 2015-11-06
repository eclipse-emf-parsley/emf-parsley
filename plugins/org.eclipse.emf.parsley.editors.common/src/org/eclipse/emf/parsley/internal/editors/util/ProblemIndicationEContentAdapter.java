/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.editors.util;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;

/**
 * Redefines a few methods.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class ProblemIndicationEContentAdapter extends EContentAdapter {

	@Override
	protected void setTarget(Resource target) {
		basicSetTarget(target);
	}

	@Override
	protected void unsetTarget(Resource target) {
		basicUnsetTarget(target);
	}
}
