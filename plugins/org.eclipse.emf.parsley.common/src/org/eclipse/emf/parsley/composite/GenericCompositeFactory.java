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

import org.eclipse.emf.parsley.internal.composite.CompositeParametersProvider;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * A generic factory for {@link Composite} that injects arguments for the
 * constructor's parent and style parameters, which are wrapped in a
 * {@link CompositeParameters} object.
 * 
 * For example, you can create by injections instances of classes with the given
 * constructor (note the {@link Inject} annotation:
 * 
 * <pre>
 * class MyComposite extends Composite {
 * 	&#64;Inject private IContentProvider contentProvider;
 * 
 * 	&#64;Inject
 * 	public MyComposite(CompositeParameters params, ILabelProvider labelProvider) {
 * 		super(params.getParent(), params.getStyle());
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
 * You may want to make your Composite extend our facility class
 * {@link InjectableComposite}, that automatically unwraps the parent and the
 * style. For example, the MyComposite above could have been written as follows:
 * 
 * <pre>
 * class MyComposite extends InjectableComposite {
 * 	&#64;Inject private IContentProvider contentProvider;
 * 
 * 	&#64;Inject
 * 	public MyComposite(CompositeParameters params, ILabelProvider labelProvider) {
 * 		super(params);
 * 		...
 * 	}
 * }
 * </pre>
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 */
@Singleton
public class GenericCompositeFactory {

	@Inject
	private CompositeParametersProvider provider;

	@Inject
	private Injector injector;

	synchronized public <T extends Composite> T create(Class<T> type, final Composite parent, final int style) {
		provider.insertForLaterProvide(new CompositeParameters(parent, style));
		return injector.getInstance(type);
	}

}
