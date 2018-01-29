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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.inject.EClassCompositeParameters;
import org.eclipse.emf.parsley.inject.GenericCompositeFactory;
import org.eclipse.emf.parsley.internal.inject.GenericFactory;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * A factory for creating EMF Parsley Composite by injection; if you have custom
 * Composite components you need to create by injection, you should use
 * {@link GenericCompositeFactory}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 */
@Singleton
public class CompositeFactory {

	@Inject
	private GenericCompositeFactory genericCompositeFactory;

	@Inject
	private GenericFactory<Composite, EClassCompositeParameters> genericCompositeFactoryWithEClass;

	public FormDetailComposite createFormDetailComposite(Composite parent, int style) {
		return genericCompositeFactory.create(FormDetailComposite.class, parent, style);
	}

	public FormDetailReadOnlyComposite createFormDetailReadOnlyComposite(Composite parent, int style) {
		return genericCompositeFactory.create(FormDetailReadOnlyComposite.class, parent, style);
	}

	public DialogDetailComposite createDialogDetailComposite(
			Composite parent, int style) {
		return genericCompositeFactory.create(DialogDetailComposite.class, parent, style);
	}

	public TreeFormComposite createTreeFormComposite(Composite parent, int style) {
		return genericCompositeFactory.create(TreeFormComposite.class, parent, style);
	}

	public TableFormComposite createTableFormComposite(Composite parent, int style, EClass type) {
		return genericCompositeFactoryWithEClass.createInstance(TableFormComposite.class,
				new EClassCompositeParameters(parent, style, type));
	}

}
