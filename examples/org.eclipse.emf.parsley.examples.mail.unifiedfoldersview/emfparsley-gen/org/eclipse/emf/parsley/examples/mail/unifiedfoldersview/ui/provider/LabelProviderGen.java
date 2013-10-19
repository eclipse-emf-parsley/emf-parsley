package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.mail.Account;
import org.eclipse.emf.parsley.examples.mail.Folder;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.UnifiedFolderContainer;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final UnifiedFolderContainer it) {
    String _containerName = it.getContainerName();
    return _containerName;
  }
  
  public String text(final Folder it) {
    EObject _eContainer = it.eContainer();
    String _email = ((Account) _eContainer).getEmail();
    return _email;
  }
  
  public Object image(final UnifiedFolderContainer it) {
    String _containerName = it.getContainerName();
    String _firstLower = StringExtensions.toFirstLower(_containerName);
    String _plus = (_firstLower + ".gif");
    return _plus;
  }
  
  public Object image(final Folder it) {
    return "account.gif";
  }
}
