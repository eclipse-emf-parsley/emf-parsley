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
package org.eclipse.emf.parsley.inject;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation for methods that will be called only after the injection has fully
 * finished: constructors, fields and methods have been completely injected,
 * also in subclasses; methods annotated with this annotation must have no
 * parameter, and must be in a class annotated with {@link EmfParsleyLifecycle};
 * methods can be private.
 * 
 * This is different from standard Guice Inject methods: super types methods are
 * called before subtypes fields and methods, thus injected fields in subclasses
 * are not yet injected.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@Target({ METHOD })
@Retention(RUNTIME)
public @interface AfterInject {

}