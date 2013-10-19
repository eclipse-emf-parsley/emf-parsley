/**
 * 
 */
package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.UnifiedFolderContainer;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui.provider.ViewerContentProviderGen;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * @author bettini
 * 
 */
public class UnifiedFoldersContentProvider extends ViewerContentProviderGen {

	@Inject
	public UnifiedFoldersContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object[] getElements(Object object) {
		Resource resource = (Resource) object;
		return Lists.newArrayList(
				new UnifiedFolderContainer("Inbox", resource),
				new UnifiedFolderContainer("Sent", resource),
				new UnifiedFolderContainer("Trash", resource)).toArray();
	}
}
