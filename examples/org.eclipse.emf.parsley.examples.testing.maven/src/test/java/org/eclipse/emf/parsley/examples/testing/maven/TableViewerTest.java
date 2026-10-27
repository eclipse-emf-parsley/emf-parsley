package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.junit.Rule;
import org.junit.Test;

/**
 * Exercises a real JFace TableViewer without launching an Eclipse workbench.
 */
public class TableViewerTest {

	@Rule
	public DisplayHelperTestRule displayHelper = new DisplayHelperTestRule();

	private record Person(String name, String role) {
	}

	@Test
	public void tableRendersColumnsAndExposesSelection() {
		var people = List.of(new Person("Ada", "Engineer"), new Person("Grace", "Scientist"));

		var viewer = new TableViewer(displayHelper.getShell(), SWT.BORDER | SWT.FULL_SELECTION);
		var table = viewer.getTable();
		table.setHeaderVisible(true);

		var nameColumn = new TableViewerColumn(viewer, SWT.NONE);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(140);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Person) element).name();
			}
		});

		var roleColumn = new TableViewerColumn(viewer, SWT.NONE);
		roleColumn.getColumn().setText("Role");
		roleColumn.getColumn().setWidth(140);
		roleColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Person) element).role();
			}
		});

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(people);

		assertEquals(2, table.getColumnCount());
		assertEquals(2, table.getItemCount());
		assertEquals("Ada", table.getItem(0).getText(0));
		assertEquals("Engineer", table.getItem(0).getText(1));
		assertEquals("Grace", table.getItem(1).getText(0));
		assertEquals("Scientist", table.getItem(1).getText(1));

		viewer.setSelection(new StructuredSelection(people.get(1)));
		var selection = (IStructuredSelection) viewer.getSelection();
		assertEquals(people.get(1), selection.getFirstElement());
	}
}
