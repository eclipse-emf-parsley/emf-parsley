/**
 * 
 */
package org.eclipse.emf.parsley.editors.outline;

import org.eclipse.emf.parsley.editors.EmfAbstractEditor;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;


import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author bettini
 * 
 */
public class EmfEditorContentOutlineFactory {

	@Inject
	protected Provider<ViewerInitializer> emfViewerManagerProvider;
	
	@Inject
	protected Provider<EmfEditorContentOutlinePage> outlinePageProvider;

	public EmfEditorContentOutlinePage create(EmfAbstractEditor editor) {
		EmfEditorContentOutlinePage outlinePage = outlinePageProvider.get();
		outlinePage.init(editor);
		return outlinePage;
	}
}
