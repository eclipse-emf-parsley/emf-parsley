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
package org.eclipse.emf.parsley.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;
import com.google.inject.Injector;

/**
 * Annotates members of your SWT Composite class constructor's parameters into
 * which the {@link Injector} should inject the value for the Composite's SWT style.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 * 
 * @since 2.0
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
@BindingAnnotation
public @interface CompositeStyle {

}
