/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.DialogDetailComposite;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.swt.SWT;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author Lorenzo Bettini
 */
public class DialogDetailCompositeTest extends AbstractEmfParsleyControlBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	private EditingDomain editingDomain;

	private DialogDetailComposite dialogDetailComposite;

	@Before
	public void setupTestCase() {
		Injector injector = getOrCreateInjector();
		injector.injectMembers(this);
		dialogDetailComposite = new DialogDetailComposite(getShell(), SWT.NONE);
		injector.injectMembers(dialogDetailComposite);
	}

	@Test
	public void testInitWithEditingDomain() {
		ClassWithName o = fixtures.getTestFactory().createClassWithName();
		o.setName("Test");
		dialogDetailComposite.init(o, editingDomain);
	}

	@Test
	public void testInitWithoutEditingDomain() {
		ClassWithName o = fixtures.getTestFactory().createClassWithName();
		o.setName("Test");
		dialogDetailComposite.init(o);
	}

}
