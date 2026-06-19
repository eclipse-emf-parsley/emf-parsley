/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.domain.EditingDomainFinderStrategy;
import org.eclipse.emf.parsley.edit.domain.EditingDomainPresetStrategy;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Injector;

public class EditingDomainStrategyTest extends AbstractEmfParsleyTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private Injector injector;

	private EditingDomain resourceEditingDomain;

	@Before
	public void setupInjector() {
		injector = getOrCreateInjector();
		createResourceForTest();
	}

	@Test
	public void testEditingDomainFinderStrategy() {
		final var strategy = injector.getInstance(EditingDomainFinderStrategy.class);
		assertNull(strategy.getEditingDomain());
		strategy.updateEditingDomain(fixtures.getLibrary());
		assertSame(resourceEditingDomain, strategy.getEditingDomain());
	}

	@Test
	public void testEditingDomainPresetStrategy() {
		final var strategy = injector.getInstance(EditingDomainPresetStrategy.class);
		assertNull(strategy.getEditingDomain());
		final var preset = injector.getInstance(EditingDomain.class);
		strategy.setEditingDomain(preset);
		strategy.updateEditingDomain(fixtures.getLibrary());
		// still use the preset one
		assertNotSame(resourceEditingDomain, strategy.getEditingDomain());
		assertSame(preset, strategy.getEditingDomain());
	}

	private void createResourceForTest() {
		resourceEditingDomain = fixtures.createEditingDomain(injector);
		fixtures.createTestLibrayResourceAndInitialize(
			fixtures.setupResouceFactory(resourceEditingDomain.getResourceSet())
		);
	}

}
