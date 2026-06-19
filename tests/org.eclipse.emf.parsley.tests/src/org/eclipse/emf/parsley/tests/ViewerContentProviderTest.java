/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.NonStructuredViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ViewerContentProviderTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private static final String CLASS_FOR_CONTROLS_LABEL = "Class For Controls";

	private static final String CLASS_WITH_NAME_TEST = "Class With Name Test";

	private static final String TEST_CONTAINER = "Test Container";

	private static final String IN_ANOTHER_CONTAINER = "another container";

	private ViewerContentProvider contentProvider;
	
	/**
	 * We need this only to render the contents returned by the content provider
	 */
	private ILabelProvider labelProvider;
	
	@Before
	public void setupContentProvider() {
		contentProvider = getOrCreateInjector().getInstance(ViewerContentProvider.class);
		labelProvider = getOrCreateInjector().getInstance(ILabelProvider.class);
	}

	@Test
	public void testGetElementsNull() {
		assertEquals(0, contentProvider.getElements(null).length);
	}

	@Test
	public void testGetChildrenNull() {
		assertEquals(0, contentProvider.getChildren(null).length);
	}

	@Test
	public void testHasChildrenNull() {
		assertFalse(contentProvider.hasChildren(null));
	}

	@Test
	public void testDefaultGetElements() {
		assertArray("Class With Name Test, Class For Controls",
			contentProvider.getElements(fillTestContainer())
		);
	}

	@Test
	public void testDefaultGetChildren() {
		assertArray("Class With Name Test, Class For Controls",
			contentProvider.getChildren(fillTestContainer())
		);
	}

	@Test
	public void testDefaultHasChildren() {
		assertFalse(contentProvider.hasChildren(fixtures.getTestContainer()));
		assertTrue(contentProvider.hasChildren(fillTestContainer()));
	}

	@Test
	public void testCustomGetElements() {
		ViewerContentProvider customProvider = new ViewerContentProvider(getAdapterFactory()) {
			public Object elements(TestContainer e) {
				// don't return classesWithName
				return e.getClassesForControls();
			}
		};
		injectMembers(customProvider);
		assertArray(CLASS_FOR_CONTROLS_LABEL,
			customProvider.getElements(fillTestContainer())
		);
	}

	@Test
	public void testCustomGetElementsFromResource() {
		assertArray(CLASS_FOR_CONTROLS_LABEL,
			getContentProviderWithCustomGetElements()
				.getElements(fillTestContainer().eResource())
		);
	}

	@Test
	public void testCustomGetChildren() {
		ViewerContentProvider customProvider = new ViewerContentProvider(getAdapterFactory()) {
			public Object children(TestContainer e) {
				// don't return classesWithName
				return e.getClassesForControls();
			}
		};
		injectMembers(customProvider);
		assertArray(CLASS_FOR_CONTROLS_LABEL,
			customProvider.getChildren(fillTestContainer())
		);
	}

	@Test
	public void testCustomHasChildrenUsesCustomGetChildren() {
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("Test"));
		// although it contains a ClassWithName, the custom getChildren
		// does not return such elements as children, so hasChildren
		// returns false
		ViewerContentProvider customProvider = new ViewerContentProvider(getAdapterFactory()) {
			public Object children(TestContainer e) {
				// don't return classesWithName
				return e.getClassesForControls();
			}
		};
		injectMembers(customProvider);
		assertFalse(customProvider.hasChildren(fixtures.getTestContainer()));
	}

	@Test
	public void testGetParentWithoutResource() {
		assertNull(contentProvider.getParent(fixtures.getTestContainer()));
	}

	@Test
	public void testGetParentWithResource() {
		Resource res = fixtures.createResource();
		res.getContents().add(fixtures.getTestContainer());
		assertEquals(res, contentProvider.getParent(fixtures.getTestContainer()));
	}

	@Test
	public void testGetParentAvoidsLoopReturningNull() {
		// this creates a cycle
		fixtures.getTestContainer().setContained(fixtures.getTestContainer());
		assertSame(fixtures.getTestContainer(), fixtures.getTestContainer().eContainer());
		assertNull(contentProvider.getParent(fixtures.getTestContainer()));
	}

	@Test
	public void testElementsAreRefreshedWhenNewElementIsAdded() {
		// this tests the default behavior, when getElements
		// is not customized
		fillTestContainer();
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			contentProvider);
		treeViewer.expandAll();
		assertAllLabels(treeViewer,
			TEST_CONTAINER + "\n" +
			"  " + CLASS_WITH_NAME_TEST + "\n" +
			"  " + CLASS_FOR_CONTROLS_LABEL + "\n"
		);
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls())
		);
		assertAllLabels(treeViewer,
			TEST_CONTAINER + "\n" +
			"  " + CLASS_WITH_NAME_TEST + "\n" +
			"  " + CLASS_FOR_CONTROLS_LABEL + "\n" +
			"  " + CLASS_FOR_CONTROLS_LABEL + "\n"
		);
	}

	/**
	 * This only tests elements containing null, then the setup of the tree viewer
	 * will fail due to the null element in the viewer.
	 */
	@Test(expected=AssertionFailedException.class)
	public void smokeTestCustomGetElementsContainsNull() {
		fixtures.setTestContainer(fixtures.createTestContainerInResource());
		ViewerContentProvider customProvider = new ViewerContentProvider(getAdapterFactory()) {
			public Object elements(Resource resource) {
				ArrayList<Object> list = new ArrayList<>();
				list.add(null);
				return list;
			}
		};
		injectMembers(customProvider);
		setupTreeViewer(fixtures.getTestContainer().eResource(), customProvider);
	}

	/**
	 * This only tests that we gracefully handle refresh also on
	 * non structured viewers.
	 */
	@Test
	public void smokeTestCustomGetElementsForNonStructuredViewer() { // NOSONAR: we just ensure it doesn't throw
		fillTestContainer();
		NonStructuredViewer viewer = new NonStructuredViewer(getShell());
		viewer.setContentProvider(getContentProviderWithCustomGetElements());
		viewer.setInput(fixtures.getTestContainer().eResource());
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls())
		);
	}

	@Test
	public void testRootElementsAreRefreshedWhenNewElementIsAdded() {
		fillTestContainer();
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls())
		);
		assertAllLabels(treeViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
	}

	@Test
	public void testRootElementsAreRefreshedWhenNewElementIsAdded2() {
		fillTestContainer();
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() -> {
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls());
			return fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls());
		});
		assertAllLabels(treeViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
	}

	@Test
	public void testRootElementsAreRefreshedWhenNewElementIsAddedToAnAddedContainer() {
		fillTestContainer();
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() -> {
			TestContainer newContainer = fixtures.createTestContainer(fixtures.getTestContainer().eResource(), IN_ANOTHER_CONTAINER);
			newContainer.getClassesForControls().add(fixtures.createClassForControls());
			newContainer.getClassesForControls().get(0).setStringFeature(((TestContainer) newContainer.getClassesForControls().get(0).eContainer()).getName());
			return null;
		});
		assertAllLabels(treeViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + " " + IN_ANOTHER_CONTAINER + "\n"
		);
	}

	@Test
	public void testRootElementsAreRefreshedWhenNewElementIsAddedToAnEmptyContainer() {
		fixtures.setTestContainer(fixtures.createTestContainerInResource());
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, "");
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls())
		);
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
	}

	@Test
	public void testRootElementsAreRefreshedWhenNewElementIsAddedToAnEmptyResource() {
		Resource resource = fixtures.createResourceInResouceSet();
		TreeViewer treeViewer = setupTreeViewer(resource,
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, "");
		execAndFlushPendingEvents(() ->
			fixtures.createTestContainer(resource).getClassesForControls().add(fixtures.createClassForControls())
		);
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
	}

	@Test
	public void testRootElementsAreRefreshedWhenNewElementIsAddedAndAddedAgain() {
		fillTestContainer();
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls())
		);
		assertAllLabels(treeViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls()
		));
		assertAllLabels(treeViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
	}

	@Test
	public void testRootElementsAreRefreshedWhenExistingElementIsRemoved() {
		fillTestContainer();
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().remove(
				fixtures.getTestContainer().getClassesForControls().get(0))
		);
		assertAllLabels(treeViewer, "");
	}

	@Test
	public void testRootElementsAreRefreshedWhenContentsAreCleared() {
		fillTestContainer();
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() -> {
			fixtures.getTestContainer().getClassesForControls().clear();
			return null;
		});
		assertAllLabels(treeViewer, "");
	}

	@Test
	public void testRootElementsAreRefreshedWhenNewElementsAreAddedAndThenRemoved() {
		fillTestContainer();
		TreeViewer treeViewer = setupTreeViewer(fixtures.getTestContainer().eResource(),
			getContentProviderWithCustomGetElements());
		assertAllLabels(treeViewer, CLASS_FOR_CONTROLS_LABEL);
		execAndFlushPendingEvents(() -> {
			fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls());
			return fixtures.getTestContainer().getClassesForControls().add(fixtures.createClassForControls());
		});
		assertAllLabels(treeViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().remove(
				fixtures.getTestContainer().getClassesForControls().get(0))
		);
		assertAllLabels(treeViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n" +
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
		execAndFlushPendingEvents(() ->
			fixtures.getTestContainer().getClassesForControls().remove(
				fixtures.getTestContainer().getClassesForControls().get(0))
		);
		assertAllLabels(treeViewer,
			CLASS_FOR_CONTROLS_LABEL + "\n"
		);
	}

	private ViewerContentProvider getContentProviderWithCustomGetElements() {
		return new ViewerContentProvider(getAdapterFactory()) {
			public Object elements(Resource resource) {
				// don't return classesWithName
				TreeIterator<EObject> allContents = resource.getAllContents();
				Iterable<EObject> iterable = () -> allContents;
				return StreamSupport.stream(iterable.spliterator(), false)
					.filter(TestContainer.class::isInstance)
					.map(e -> (TestContainer) e)
					.flatMap(c -> c.getClassesForControls().stream())
					.toList();
			}
		};
	}

	private TestContainer fillTestContainer() {
		fixtures.setTestContainer(fixtures.createTestContainerInResource());
		fixtures.getTestContainer().getClassesForControls().add(fixtures.getClassForControlsInstance());
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("Test"));
		return fixtures.getTestContainer();
	}

	private void assertArray(String expected, Object[] a) {
		assertEquals(expected,
			Arrays.stream(a)
				.map(e -> labelProvider.getText(e))
				.collect(Collectors.joining(", "))
		);
	}

	/**
	 * In order to make the tests reliable for viewer refreshing, it is crucial
	 * to use a Resource as input, not an EObject
	 */
	private TreeViewer setupTreeViewer(Resource resource, IContentProvider contentProvider) {
		TreeViewer treeViewer = new TreeViewer(getShell());
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setInput(resource);
		return treeViewer;
	}

}
