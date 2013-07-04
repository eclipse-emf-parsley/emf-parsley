package org.eclipse.emf.parsley.tests.labeling;

import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;


public class CustomLibraryTableColumnLabelProvider extends
		TableColumnLabelProvider {

	public String text_Writer_name(Object element) {
		if (element instanceof Writer) {
			Writer writer = (Writer) element;
			return "Writer " + writer.getName();
		}
		return null;
	}

}
