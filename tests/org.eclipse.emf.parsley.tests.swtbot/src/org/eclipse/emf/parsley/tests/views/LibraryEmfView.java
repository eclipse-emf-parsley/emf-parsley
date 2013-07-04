/**
 * 
 */
package org.eclipse.emf.parsley.tests.views;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.views.OnSelectionTreeView;
import org.eclipse.swt.widgets.Composite;

/**
 * OnSelectionTreeView with hardcoded resource URI
 * 
 * @author bettini
 * 
 */
public class LibraryEmfView extends OnSelectionTreeView {

	public static final String resourceUri = "platform:/plugin/org.eclipse.emf.parsley.tests.swtbot/models/My.extlibrary";

	public LibraryEmfView() {

	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		showEmfViewer();
		init(URI.createURI(resourceUri));
	}

}
