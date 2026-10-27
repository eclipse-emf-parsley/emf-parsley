package org.eclipse.emf.parsley.examples.testing.maven;

import java.util.List;

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.junit.Test;

/**
 * Demonstrates the inherited assertions for complete JFace viewer contents.
 * Both viewers are ordinary SWT/JFace components, independent of EMF Parsley.
 */
public class ViewerAssertionsTest extends AbstractEmfParsleyShellBasedTest {

	@Test
	public void assertTableChecksAllRenderedRows() {
		var viewer = syncExec(() -> {
			var result = new TableViewer(getShell(), SWT.BORDER);
			result.setContentProvider(ArrayContentProvider.getInstance());
			result.setLabelProvider(new LabelProvider());
			result.setInput(List.of("Ada", "Grace"));
			return result;
		});

		// assertTable compares the first-column text of every SWT TableItem.
		syncExecVoid(() -> assertTable(viewer, "Ada\nGrace"));

		syncExecVoid(() -> viewer.setInput(List.of("Ada", "Grace", "Linus")));
		syncExecVoid(() -> assertTable(viewer, "Ada\nGrace\nLinus"));
	}

	@Test
	public void assertAllLabelsChecksAnExpandedTreeHierarchy() {
		var root = new TreeNode("Root");
		var first = new TreeNode("First");
		var second = new TreeNode("Second");
		root.setChildren(new TreeNode[] { first, second });
		first.setParent(root);
		second.setParent(root);

		var viewer = syncExec(() -> {
			var result = new TreeViewer(getShell(), SWT.BORDER);
			result.setContentProvider(new TreeNodeContentProvider());
			result.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return ((TreeNode) element).getValue().toString();
				}
			});
			// The input array is a container; it is not itself a tree element.
			result.setInput(new TreeNode[] { root });
			result.expandAll();
			return result;
		});

		// The helper checks the complete tree, including indentation by depth.
		syncExecVoid(() -> assertAllLabels(viewer, "Root\n  First\n  Second"));
	}
}
