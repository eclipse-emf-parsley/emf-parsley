package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests a JFace TreeViewer backed by generated EMF.Edit item providers.
 * The providers come from Ecore's own EMF.Edit bundle; no generated application
 * model, EMF Parsley application classes, or Eclipse workbench are needed.
 */
public class EmfEditTreeViewerTest {

	@Rule
	public DisplayHelperTestRule displayHelper = new DisplayHelperTestRule();

	/**
	 * A small JFace bridge that delegates structure and parent lookup to EMF.Edit.
	 * The Object[] input is an invisible container, not a tree element.
	 */
	private static class EditContentProvider implements ITreeContentProvider {
		private final EcoreItemProviderAdapterFactory adapterFactory;

		EditContentProvider(EcoreItemProviderAdapterFactory adapterFactory) {
			this.adapterFactory = adapterFactory;
		}

		private ITreeItemContentProvider provider(Object element) {
			return (ITreeItemContentProvider) adapterFactory.adapt(element, ITreeItemContentProvider.class);
		}

		@Override
		public Object[] getElements(Object input) {
			return input instanceof Object[] roots ? roots : new Object[0];
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return provider(parentElement).getChildren(parentElement).toArray();
		}

		@Override
		public Object getParent(Object element) {
			return provider(element).getParent(element);
		}

		@Override
		public boolean hasChildren(Object element) {
			return provider(element).hasChildren(element);
		}
	}

	@Test
	public void treeReflectsChangesThroughEmfEditItemProviders() {
		var ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName("example");
		ePackage.setNsPrefix("example");
		ePackage.setNsURI("https://example.org/ecore-edit");

		var person = EcoreFactory.eINSTANCE.createEClass();
		person.setName("Person");
		ePackage.getEClassifiers().add(person);

		// Ecore already supplies generated EMF.Edit item providers.
		var adapterFactory = new EcoreItemProviderAdapterFactory();
		var viewer = new TreeViewer(displayHelper.getShell(), SWT.BORDER);
		viewer.setContentProvider(new EditContentProvider(adapterFactory));
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				var provider = (IItemLabelProvider) adapterFactory.adapt(element, IItemLabelProvider.class);
				return provider.getText(element);
			}
		});

		// Forward EMF.Edit notifications to the viewer on the SWT UI thread.
		// Queueing the refresh avoids updating the tree inside model notifications.
		adapterFactory.addListener(notification -> {
			var display = displayHelper.getDisplay();
			if (!display.isDisposed()) {
				display.asyncExec(() -> {
					if (!viewer.getControl().isDisposed()) {
						viewer.refresh();
					}
				});
			}
		});

		try {
			viewer.setInput(new Object[] { ePackage });
			viewer.expandAll();

			var tree = viewer.getTree();
			assertEquals(1, tree.getItemCount());
			TreeItem rootItem = tree.getItem(0);
			assertSame(ePackage, rootItem.getData());
			assertEquals(1, rootItem.getItemCount());
			assertSame(person, rootItem.getItem(0).getData());
			assertTrue(rootItem.getItem(0).getText().contains("Person"));

			// A name change produces a label update from Ecore's item provider.
			person.setName("RenamedPerson");
			displayHelper.flushPendingEvents();
			viewer.expandAll();
			rootItem = tree.getItem(0);
			assertEquals(1, rootItem.getItemCount());
			assertTrue(rootItem.getItem(0).getText().contains("RenamedPerson"));

			// A containment change produces a structural update.
			EClass address = EcoreFactory.eINSTANCE.createEClass();
			address.setName("Address");
			ePackage.getEClassifiers().add(address);
			displayHelper.flushPendingEvents();
			viewer.expandAll();
			rootItem = tree.getItem(0);
			assertEquals(2, rootItem.getItemCount());
			assertSame(address, rootItem.getItem(1).getData());
			assertTrue(rootItem.getItem(1).getText().contains("Address"));
		} finally {
			viewer.getControl().dispose();
			adapterFactory.dispose();
		}
	}
}
