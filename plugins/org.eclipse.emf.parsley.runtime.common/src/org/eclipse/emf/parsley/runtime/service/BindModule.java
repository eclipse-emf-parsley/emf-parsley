/*******************************************************************************
 * Copyright (c) 2009, 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * itemis AG - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.service;

import java.lang.reflect.Method;

public class BindModule extends MethodBasedModule {

	public BindModule(Method method, Object owner) {
		super(method, owner);
	}

}