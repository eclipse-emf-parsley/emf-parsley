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
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.parsley.annotation.CompositeParent;
import org.eclipse.emf.parsley.annotation.CompositeStyle;
import org.eclipse.emf.parsley.runtime.util.InjectorUtil;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * A generic factory for {@link Composite} that injects arguments for the
 * constructor's parent and style parameters, which must be annotated with
 * {@link CompositeParent} and {@link CompositeStyle}, respectively.
 * 
 * For example, you can create by injections instances of classes with the given
 * constructor (note the {@link Inject} annotation and the
 * {@link CompositeParent} and {@link CompositeStyle} parameter annotations):
 * 
 * <pre>
 * class MyComposite extends Composite {
 * 	&#64;Inject private IContentProvider contentProvider;
 * 
 * 	&#64;Inject
 * 	public MyComposite(&#64;CompositeParent Composite parent, &#64;CompositeStyle int style, ILabelProvider labelProvider) {
 * 		super(parent, style);
 * 		...
 * 	}
 * }
 * </pre>
 * 
 * Just inject this factory
 * 
 * <pre>
 * &#64;Inject
 * GenericCompositeFactory compositeFactory;
 * </pre>
 * 
 * and then create instances of your composite via injection:
 * 
 * <pre>
 * MyComposite composite = compositeFactory.create(MyComposite.class, parent, style)
 * </pre>
 * 
 * Note that the other parameters of the constructor will be provided by
 * injection (implicitly). Moreover, other fields will be automatically injected
 * as well.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 */
public class GenericCompositeFactory {

	@Inject
	private Injector parentInjector;

	public <T extends Composite> T create(Class<T> type, final Composite parent, final int style) {
		Module override = InjectorUtil.overrideModuleFromInjector(parentInjector, new AbstractModule() {
			@Override
			protected void configure() {
				bind(Composite.class)
					.annotatedWith(CompositeParent.class)
					.toInstance(parent);
				bind(Integer.class)
					.annotatedWith(CompositeStyle.class)
					.toInstance(style);
			}
		});
		return Guice.createInjector(override).getInstance(type);
	}

}
