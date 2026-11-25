package org.eclipse.emf.parsley.examples.mail.accountsview.ui.provider;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.examples.mail.Account;
import org.eclipse.emf.parsley.examples.mail.Folder;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

@SuppressWarnings("all")
public class AccountsviewLabelProvider extends ViewerLabelProvider {
  @Inject
  public AccountsviewLabelProvider(final AdapterFactoryLabelProvider delegate) {
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
    String _xblockexpression = null;
    {
      String _name = it.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        return "folder.gif";
      }
      String _switchResult = null;
      String _name_1 = it.getName();
      if (_name_1 != null) {
        switch (_name_1) {
          case "Inbox":
            _switchResult = "inbox.gif";
            break;
          case "Sent":
            _switchResult = "sent.gif";
            break;
          case "Trash":
            _switchResult = "trash.gif";
            break;
          default:
            _switchResult = "folder.gif";
            break;
        }
      } else {
        _switchResult = "folder.gif";
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
}
