/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.composite;

import java.util.ArrayDeque;
import java.util.Deque;

import org.eclipse.emf.parsley.composite.CompositeParameters;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * This is the Guice {@link Provider} that we internally use to correctly
 * inject {@link CompositeParameters} into {@link Composite} instances.
 * 
 * FOR INTERNAL USE ONLY!
 * 
 * This assumes that, before creating an instance via injection that requires
 * {@link CompositeParameters}, an instance of {@link CompositeParameters} is first
 * pushed in the internal stack using {@link #insertForLaterProvide(CompositeParameters)}.
 * 
 * @author Lorenzo Bettini
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
@Singleton
public class CompositeParametersProvider implements Provider<CompositeParameters> {

	private Deque<CompositeParameters> stack = new ArrayDeque<CompositeParameters>();

	public void insertForLaterProvide(CompositeParameters params) {
		stack.push(params);
	}

	@Override
	public CompositeParameters get() {
		return stack.pop();
	}

}