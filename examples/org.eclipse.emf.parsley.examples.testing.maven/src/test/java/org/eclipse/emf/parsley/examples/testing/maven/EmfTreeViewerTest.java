package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.widgets.Display;
import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.junit.Rule;
import org.junit.Test;

/**
 * Shows how an ordinary JFace TreeViewer can follow dynamic EMF model changes.
 * No EMF Parsley application components, generated model, or workbench are
 * needed.
 */
public class EmfTreeViewerTest {

	@Rule
	public DisplayHelperTestRule displayHelper = new DisplayHelperTestRule();

	private record Model(EClass nodeType, EAttribute name, EReference children) {
		EObject newNode(String label) {
			EObject node = EcoreUtil.create(nodeType);
			node.eSet(name, label);
			return node;
		}

		@SuppressWarnings("unchecked")
		EList<EObject> childrenOf(EObject node) {
			return (EList<EObject>) node.eGet(children);
		}
	}

	private static Model createModel() {
		var factory = EcoreFactory.eINSTANCE;
		var ePackage = factory.createEPackage();
		ePackage.setName("nodes");
		ePackage.setNsPrefix("nodes");
		ePackage.setNsURI("https://example.org/nodes");
		ePackage.setEFactoryInstance(factory.createEFactory());

		var node = factory.createEClass();
		node.setName("Node");
		ePackage.getEClassifiers().add(node);

		var name = factory.createEAttribute();
		name.setName("name");
		name.setEType(EcorePackage.Literals.ESTRING);
		node.getEStructuralFeatures().add(name);

		var children = factory.createEReference();
		children.setName("children");
		children.setEType(node);
		children.setContainment(true);
		children.setUpperBound(-1);
		node.getEStructuralFeatures().add(children);
		return new Model(node, name, children);
	}

	/**
	 * Minimal bridge from EMF notifications to a JFace TreeViewer. This small
	 * example handles child additions and label changes, not every possible EMF
	 * notification (removals, moves, bulk changes, etc.).
	 *
	 * The viewer is updated asynchronously to avoid modifying SWT tree items
	 * reentrantly while the EMF notification is being delivered.
	 */
	private static class ModelContentProvider implements ITreeContentProvider {
		private final EAttribute name;
		private final EReference children;
		private final Display display;
		private TreeViewer viewer;
		private EObject input;

		private final EContentAdapter adapter = new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				// EContentAdapter propagates itself to newly contained EObjects.
				super.notifyChanged(notification);

				if (notification.getFeature() == children && notification.getEventType() == Notification.ADD
						&& notification.getNotifier() instanceof EObject parent
						&& notification.getNewValue() instanceof EObject child) {
					// Incremental insertion avoids refreshing/rebuilding the tree.
					scheduleUiUpdate(() -> viewer.add(parent, child));
				} else if (notification.getFeature() == name && notification.getNotifier() instanceof EObject element) {
					scheduleUiUpdate(() -> viewer.update(element, null));
				}
			}
		};

		ModelContentProvider(EAttribute name, EReference children, Display display) {
			this.name = name;
			this.children = children;
			this.display = display;
		}

		private void scheduleUiUpdate(Runnable action) {
			// asyncExec is safe to call from either the SWT or a worker thread.
			// All SWT widget access is performed later on the Display thread.
			display.asyncExec(() -> {
				if (viewer != null && !viewer.getControl().isDisposed()) {
					action.run();
				}
			});
		}

		@Override
		public Object[] getElements(Object element) {
			// In this example, the input is an invisible container of root elements.
			// Returning the input itself as a displayed element would create a
			// recursive traversal when the viewer expands the hierarchy.
			if (element instanceof Object[] array) {
				return array;
			}
			return new Object[0];
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return ((EObject) parentElement).eContents().toArray();
		}

		@Override
		public Object getParent(Object element) {
			return ((EObject) element).eContainer();
		}

		@Override
		public boolean hasChildren(Object element) {
			return !((EObject) element).eContents().isEmpty();
		}

		@Override
		public void inputChanged(Viewer newViewer, Object oldInput, Object newInput) {
			if (input != null) {
				input.eAdapters().remove(adapter);
			}
			viewer = (TreeViewer) newViewer;
			input = extractRoot(newInput);
			if (input != null) {
				input.eAdapters().add(adapter);
			}
		}

		private static EObject extractRoot(Object newInput) {
			if (newInput instanceof Object[] array
					&& array.length > 0
					&& array[0] instanceof EObject root) {
				return root;
			}
			return null;
		}

		@Override
		public void dispose() {
			if (input != null) {
				input.eAdapters().remove(adapter);
				input = null;
			}
			viewer = null;
		}
	}

	@Test
	public void treeReflectsEmfContainmentAndAttributeNotifications() {
		var model = createModel();
		var root = model.newNode("Root");
		var first = model.newNode("First");
		model.childrenOf(root).add(first);

		var viewer = new TreeViewer(displayHelper.getShell(), SWT.BORDER);
		viewer.setContentProvider(new ModelContentProvider(model.name(), model.children(), displayHelper.getDisplay()));
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return (String) ((EObject) element).eGet(model.name());
			}
		});
		viewer.setInput(new Object[] { root });
		viewer.expandAll();

		var tree = viewer.getTree();
		assertEquals(1, tree.getItemCount());
		assertEquals("Root", tree.getItem(0).getText());
		assertEquals(1, tree.getItem(0).getItemCount());
		assertEquals("First", tree.getItem(0).getItem(0).getText());

		// The provider reacts to EMF notifications by posting UI updates.
		// Flush the display queue before inspecting the SWT widgets.
		model.childrenOf(root).add(model.newNode("Second"));
		displayHelper.flushPendingEvents();
		assertEquals(2, tree.getItem(0).getItemCount());
		assertEquals("Second", tree.getItem(0).getItem(1).getText());

		first.eSet(model.name(), "Updated first");
		displayHelper.flushPendingEvents();
		assertEquals("Updated first", tree.getItem(0).getItem(0).getText());
	}
}
