package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.mail.Folder;
import org.eclipse.emf.parsley.examples.mail.accountsview.ui.provider.AccountsviewLabelProvider;
import org.eclipse.emf.parsley.examples.mail.accountsview.unifiedfolders.UnifiedFolderContainer;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class UnifiedfoldersviewLabelProvider extends AccountsviewLabelProvider {
  @Inject
  public UnifiedfoldersviewLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final UnifiedFolderContainer it) {
    String _containerName = it.getContainerName();
    return _containerName;
  }
  
  public String text(final Folder it) {
    String _text = this.getText(it.eContainer());
    return _text;
  }
  
  public Object image(final UnifiedFolderContainer it) {
    String _firstLower = StringExtensions.toFirstLower(it.getContainerName());
    String _plus = (_firstLower + ".gif");
    return _plus;
  }
  
  public Object image(final Folder it) {
    return "account.gif";
  }
}
