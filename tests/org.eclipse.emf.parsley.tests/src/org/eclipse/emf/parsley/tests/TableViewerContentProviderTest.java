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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.NonStructuredViewer;
import org.eclipse.emf.parsley.tests.util.ResourceAndEObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class TableViewerContentProviderTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private static final String CLASS_FOR_CONTROLS_LABEL = "Class For Controls";

	private static final String CLASS_WITH_NAME_TEST = "Class With Name Test";

	@Inject
	private Provider<TableViewerContentProvider> contentProviderProvider;

	/**
	 * We need this only to render the contents returned by the content provider
	 */
	@Inject
	private ILabelProvider labelProvider;

	@Before
	public void setupContentProvider() {
		injectMembers(this);
	}

	@Test
	public void testGetElementsNull() {
		assertEquals(0, contentProvider().getElements(null).length);
	}

	@Test
	public void testGetChildrenNull() {
		assertEquals(0, contentProvider().getChildren(null).length);
	}

	@Test
	public void testHasChildrenNull() {
		assertFalse(contentProvider().hasChildren(null));
	}

	@Test
	public void testDefaultGetElements() {
		assertArray("Class With Name Test",
			contentProvider(fixtures.getTestPackage().getClassWithName()).getElements(fillTestContainer())
		);
	}

	@Test
	public void testDefaultGetElementsSettingTypeAfterConstruction() {
		var cp = contentProvider();
		cp.setEClass(fixtures.getTestPackage().getClassWithName());
		assertArray("Class With Name Test", cp.getElements(fillTestContainer()));
	}

	@Test
	public void testDefaultGetElements2() {
		assertArray("Class For Controls",
			contentProvider(fixtures.getTestPackage().getClassForControls()).getElements(fillTestContainer())
		);
	}

	@Test
	public void testDefaultGetElementsFromResource() {
		assertArray("Class With Name Test",
			contentProvider(fixtures.getTestPackage().getClassWithName()).getElements(fillTestContainer().eResource())
		);
	}

	@Test
	public void testDefaultEObjectGetElementsFromResource() {
		// EObject as a filter
		assertArray("Test Container, Class With Name Test, Class For Controls",
			contentProvider(EcorePackage.eINSTANCE.getEObject()).getElements(fillTestContainer().eResource())
		);
	}

	@Test
	public void testDefaultEObjectGetElementsFromContainer() {
		// EObject as a filter
		// the container object is not returned
		assertArray("Class With Name Test, Class For Controls",
			contentProvider(EcorePackage.eINSTANCE.getEObject()).getElements(fillTestContainer())
		);
	}

	@Test
	public void testCustomGetElements() {
		assertArray(CLASS_FOR_CONTROLS_LABEL,
			injectMembers(new TableViewerContentProvider(fixtures.getTestPackage().getClassForControls()) {
				public Object[] elements(TestContainer e) {
					// don't return classesWithName
					return e.getClassesForControls().toArray();
				}
			}).getElements(fillTestContainer())
		);
	}

	@Test
	public void testCustomGetElementsWithEObjectAsType() {
		// even if we filter with EObject, our custom implementation
		// has the precedence
		assertArray(CLASS_FOR_CONTROLS_LABEL,
			injectMembers(new TableViewerContentProvider(EcorePackage.eINSTANCE.getEObject()) {
				public Object[] elements(TestContainer e) {
					// don't return classesWithName
					return e.getClassesForControls().toArray();
				}
			}).getElements(fillTestContainer())
		);
	}

	@Test
	public void testCustomGetElementsIgnoredWithDifferentInput() {
		// we pass resource as input, we customize for the container
		// so our custom implementation is ignored
		// and we filter with EObject so we get all the contents
		assertArray("Test Container, Class With Name Test, Class For Controls",
			injectMembers(new TableViewerContentProvider(EcorePackage.eINSTANCE.getEObject()) {
				public Object[] elements(TestContainer e) {
					// don't return classesWithName
					return e.getClassesForControls().toArray();
				}
			}).getElements(fillTestContainer().eResource())
		);
	}

	@Test
	public void testGetParentWithoutResource() {
		assertNull(contentProvider().getParent(fixtures.getTestContainer()));
	}

	@Test
	public void testGetParentWithResource() {
		var res = fixtures.createResourceInResouceSet();
		res.getContents().add(fixtures.getTestContainer());
		assertEquals(res, contentProvider().getParent(fixtures.getTestContainer()));
	}

	@Test
	public void testGetParentAvoidsLoopReturningNull() {
		// this creates a cycle
		fixtures.getTestContainer().setContained(fixtures.getTestContainer());
		assertSame(fixtures.getTestContainer(), fixtures.getTestContainer().eContainer());
		assertNull(contentProvider().getParent(fixtures.getTestContainer()));
	}

	/**
	 * This only tests elements containing null, then the setup of the viewer
	 * will fail due to the null element in the viewer.
	 */
	@Test(expected = AssertionFailedException.class)
	public void smokeTestCustomGetElementsContainsNull() {
		fixtures.setTestContainer(fixtures.createTestContainerInResource());
		setupTableViewer(fixtures.getTestContainer().eResource(),
			injectMembers(new TableViewerContentProvider(fixtures.getTestPackage().getClassForControls()) {
				public Object[] elements(Resource resource) {
					var list = new ArrayList<>();
					list.add(null);
					return list.toArray();
				}
			}));
	}

	/**
	 * This only tests that we gracefully handle refresh also on
	 * non structured viewers.
	 */
	@Test
	public void smokeTestCustomGetElementsForNonStructuredViewer() { // NOSONAR: we just ensure it doesn't throw
		fillTestContainer();
		var viewer = new NonStructuredViewer(getShell());
		viewer.setContentProvider(getContentProviderWithCustomGetElements(fixtures.getTestPackage().getClassForControls()));
		viewer.setInput(fixtures.getTestContainer().eResource());
		execAndFlushPendingEvents(() -> 
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls())
		);
	}

	@Test
	public void testElementsForTableViewer() {
		// this tests the default behavior, when getElements
		// is not customized
		fillTestContainer();
		var tableViewer = setupTableViewer(fixtures.getTestContainer().eResource(),
			fixtures.getTestPackage().getClassWithName());
		assertTable(tableViewer, CLASS_WITH_NAME_TEST);
	}

	@Test
	public void testElementsForTableViewerWithResourceAndEObject() {
		// this tests the case of a Resource which is also an EObject
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=479417
		var resource = new ResourceAndEObject();
		resource.getContents().add(fixtures.createClassWithName("Test"));
		var tableViewer = setupTableViewer(resource,
			fixtures.getTestPackage().getClassWithName());
		assertTable(tableViewer, CLASS_WITH_NAME_TEST);
	}

	@Test
	public void testElementsForTableViewerWithInputNotEObject() {
		var tableViewer = setupTableViewer("input",
			fixtures.getTestPackage().getClassWithName());
		assertTable(tableViewer, "");
	}

	@Test
	public void testCustomElementsForTableViewer() {
		// this tests the default behavior, when getElements
		// is not customized
		fillTestContainer();
		var tableViewer = setupTableViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements(fixtures.getTestPackage().getClassForControls()));
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL);
	}

	@Test
	public void testTableElementsAreRefreshedWhenNewElementsAreAddedAndThenRemoved() {
		fillTestContainer();
		var tableViewer = setupTableViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements(fixtures.getTestPackage().getClassForControls()));
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() -> {
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls());
			return fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls());
		});
		assertTable(tableViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
		execAndFlushPendingEvents(() -> 
			fixtures.getTestContainer().getClassesForControls().remove(fixtures.getTestContainer().getClassesForControls().get(0))
		);
		assertTable(tableViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
		execAndFlushPendingEvents(() -> 
			fixtures.getTestContainer().getClassesForControls().remove(fixtures.getTestContainer().getClassesForControls().get(0))
		);
		assertTable(tableViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
	}

	@Test
	public void testTableElementsAreRefreshedWhenExistingElementIsRemoved() {
		fillTestContainer();
		var tableViewer = setupTableViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements(fixtures.getTestPackage().getClassForControls()));
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() -> 
			fixtures.getTestContainer().getClassesForControls().remove(fixtures.getTestContainer().getClassesForControls().get(0))
		);
		assertTable(tableViewer, "");
	}

	@Test
	public void testTableElementsAreRefreshedWhenContentsAreCleared() {
		fillTestContainer();
		var tableViewer = setupTableViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements(fixtures.getTestPackage().getClassForControls()));
		assertTable(tableViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() -> {
			fixtures.getTestContainer().getClassesForControls().clear();
			return null;
		});
		assertTable(tableViewer, "");
	}

	private TableViewerContentProvider getContentProviderWithCustomGetElements(EClass type) {
		return injectMembers(new TableViewerContentProvider(type) {
			public Object[] elements(Resource resource) {
				// don't return classesWithName
				Iterable<TestContainer> containers = () -> 
					StreamSupport.stream(
						((Iterable<EObject>) () -> resource.getAllContents()).spliterator(), false)
					.filter(TestContainer.class::isInstance)
					.map(TestContainer.class::cast)
					.iterator();
				return StreamSupport.stream(containers.spliterator(), false)
					.flatMap(c -> c.getClassesForControls().stream())
					.toArray();
			}
		});
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

	/**
	 * In order to make the tests reliable for viewer refreshing, it is crucial
	 * to use a Resource as input, not an EObject; here we're not interested in columns,
	 * just in the rows, so we set a label provider which is usually not needed for
	 * table viewers (since we have column label providers).
	 */
	private TableViewer setupTableViewer(Resource resource, TableViewerContentProvider contentProvider) {
		var viewer = new TableViewer(getShell());
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(resource);
		return viewer;
	}

	/**
	 * In order to make the tests reliable for viewer refreshing, it is crucial
	 * to use a Resource as input, not an EObject; here we're not interested in columns,
	 * just in the rows, so we set a label provider which is usually not needed for
	 * table viewers (since we have column label providers).
	 */
	private TableViewer setupTableViewer(Resource resource, EClass type) {
		var viewer = new TableViewer(getShell());
		viewer.setContentProvider(contentProvider(type));
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(resource);
		return viewer;
	}

	private TableViewer setupTableViewer(Object input, EClass type) {
		var viewer = new TableViewer(getShell());
		viewer.setContentProvider(contentProvider(type));
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(input);
		return viewer;
	}

	private TableViewerContentProvider contentProvider() {
		return contentProvider(null);
	}

	private TableViewerContentProvider contentProvider(EClass type) {
		var cp = contentProviderProvider.get();
		cp.setEClass(type);
		return cp;
	}
}
