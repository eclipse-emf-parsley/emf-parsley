package org.eclipse.emf.parsley.examples.mail.accountsview.edit.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.mail.Folder;

@SuppressWarnings("all")
public class AccountsviewViewerContentProvider extends ViewerContentProvider {
  @Inject
  public AccountsviewViewerContentProvider(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  public Object children(final Folder it) {
    EList<Folder> _subfolders = it.getSubfolders();
    return _subfolders;
  }
}
