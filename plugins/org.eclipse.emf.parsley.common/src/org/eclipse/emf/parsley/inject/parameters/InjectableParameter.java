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
package org.eclipse.emf.parsley.inject.parameters;

/**
 * Interface for parameters that we can inject into constructors with our own
 * specific factories; class implementing this interface should also be
 * annotated with {@link FactoryParameter} and must have a no-arg constructor.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 * @since 2.0
 *
 */
public interface InjectableParameter {

}
