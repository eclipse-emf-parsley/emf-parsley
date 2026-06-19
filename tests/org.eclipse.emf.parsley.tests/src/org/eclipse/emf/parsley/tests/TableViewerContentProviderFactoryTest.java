/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProviderFactory;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.jface.viewers.ILabelProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;

public class TableViewerContentProviderFactoryTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private static final String CLASS_FOR_CONTROLS_LABEL = "Class For Controls";

	@Inject
	private TableViewerContentProviderFactory contentProviderFactory;

	/**
	 * We need this only to render the contents returned by the content provider
	 */
	@Inject
	private ILabelProvider labelProvider;

	public static class CustomTableViewerContentProvider extends TableViewerContentProvider {

		@Inject
		public CustomTableViewerContentProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}

		public Object[] elements(TestContainer e) {
			// don't return classesWithName
			return e.getClassesForControls().toArray();
		}
	}

	@Before
	public void setupContentProvider() {
		injectMembers(this);
	}

	@Test
	public void testDefaultGetElements() {
		assertArray("Class With Name Test",
			contentProviderFactory.createTableViewerContentProvider(fixtures.getTestPackage().getClassWithName()).
				getElements(fillTestContainer())
		);
	}

	@Test
	public void testCustomGetElementsWithEObjectAsType() {
		// this checks that the factory uses possible custom table viewer content provider
		// implementations
		var injector = createInjector(new EmfParsleyGuiceModuleForTesting() {
			
			@Override
			public Class<? extends TableViewerContentProvider> bindTableViewerContentProvider() {
				return CustomTableViewerContentProvider.class;
			}
			
		});

		// even if we filter with EObject, our custom implementation
		// has the precedence
		assertArray(CLASS_FOR_CONTROLS_LABEL,
			injector.getInstance(TableViewerContentProviderFactory.class).
			createTableViewerContentProvider(EcorePackage.eINSTANCE.getEObject()).
			getElements(fillTestContainer())
		);
	}

	private TestContainer fillTestContainer() {
		var testContainer = fixtures.createTestContainerInResource();
		testContainer.getClassesForControls().add(fixtures.getClassForControlsInstance());
		testContainer.getClassesWithName().add(fixtures.createClassWithName("Test"));
		fixtures.setTestContainer(testContainer);
		return testContainer;
	}

	private void assertArray(CharSequence expected, Object[] a) {
		assertEquals(expected.toString(),
			Arrays.stream(a).map(o -> labelProvider.getText(o)).collect(Collectors.joining(", "))
		);
	}
}
