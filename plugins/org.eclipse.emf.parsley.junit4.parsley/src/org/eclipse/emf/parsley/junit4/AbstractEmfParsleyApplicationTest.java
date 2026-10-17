/*******************************************************************************
 * Copyright (c) 2026 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.junit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.AbstractControlFactory;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.composite.MultipleFeatureControl;
import org.eclipse.emf.parsley.junit4.util.TestDefaultRealm;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.After;
import org.junit.Before;

/**
 * Base class for Junit tests concerning {@link Control}, in particular using an
 * {@link AbstractControlFactory}, specific for EMF Parsley applications.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractEmfParsleyApplicationTest extends
		AbstractEmfParsleyControlBasedTest {

}
