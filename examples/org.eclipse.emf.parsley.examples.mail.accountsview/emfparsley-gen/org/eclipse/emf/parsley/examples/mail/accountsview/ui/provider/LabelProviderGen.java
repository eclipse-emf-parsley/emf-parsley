package org.eclipse.emf.parsley.examples.mail.accountsview.ui.provider;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.mail.Account;
import org.eclipse.emf.parsley.examples.mail.Folder;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class LabelProviderGen extends ViewerLabelProvider {
  @Inject
  public LabelProviderGen(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Account it) {
    String _email = it.getEmail();
    return _email;
  }
  
  public String text(final Folder it) {
    String _name = it.getName();
    return _name;
  }
  
  public Object image(final Account it) {
    return "account.gif";
  }
  
  public Object image(final Folder it) {
    String _switchResult = null;
    String _name = it.getName();
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(_name, "Inbox")) {
        _matched=true;
        _switchResult = "inbox.gif";
      }
    }
    if (!_matched) {
      if (Objects.equal(_name, "Sent")) {
        _matched=true;
        _switchResult = "sent.gif";
      }
    }
    if (!_matched) {
      if (Objects.equal(_name, "Trash")) {
        _matched=true;
        _switchResult = "trash.gif";
      }
    }
    if (!_matched) {
      _switchResult = "folder.gif";
    }
    return _switchResult;
  }
}
