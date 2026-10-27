package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TreeViewerTest {
	@Rule
	public DisplayHelperTestRule displayHelper = new DisplayHelperTestRule();

	@Test
	public void testTreeViewer() {
		var root = new TreeNode("Root");
		var first = new TreeNode("First");
		var second = new TreeNode("Second");
		root.setChildren(new TreeNode[] { first, second });
		first.setParent(root);
		second.setParent(root);

		var viewer = new TreeViewer(displayHelper.getShell(), SWT.NONE);
		viewer.setContentProvider(new TreeNodeContentProvider());
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((TreeNode) element).getValue().toString();
			}
		});
		viewer.setInput(new TreeNode[] { root });
		viewer.expandAll();

		var tree = viewer.getTree();
		assertEquals(1, tree.getItemCount());
		var rootItem = tree.getItem(0);
		assertEquals("Root", rootItem.getText());
		assertEquals(2, rootItem.getItemCount());
		assertEquals("First", rootItem.getItem(0).getText());
		assertEquals("Second", rootItem.getItem(1).getText());
	}
}
