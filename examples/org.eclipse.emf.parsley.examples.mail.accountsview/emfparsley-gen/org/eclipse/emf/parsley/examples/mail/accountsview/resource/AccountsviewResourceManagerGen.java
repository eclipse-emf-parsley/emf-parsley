package org.eclipse.emf.parsley.examples.mail.accountsview.resource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.examples.mail.Account;
import org.eclipse.emf.parsley.examples.mail.accountsview.resource.MailResourceManager;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class AccountsviewResourceManagerGen extends MailResourceManager {
  @Override
  public void initialize(final Resource it) {
    Account _createAccount = this.createAccount("Lorenzo", "lorenzo@foobar");
    final Procedure1<Account> _function = new Procedure1<Account>() {
      @Override
      public void apply(final Account account) {
        EList<EObject> _contents = it.getContents();
        _contents.add(account);
        AccountsviewResourceManagerGen.this.createDefaultFolders(account);
      }
    };
    ObjectExtensions.<Account>operator_doubleArrow(_createAccount, _function);
    Account _createAccount_1 = this.createAccount("Francesco", "francesco@foobar");
    final Procedure1<Account> _function_1 = new Procedure1<Account>() {
      @Override
      public void apply(final Account account) {
        EList<EObject> _contents = it.getContents();
        _contents.add(account);
        AccountsviewResourceManagerGen.this.createDefaultFolders(account);
      }
    };
    ObjectExtensions.<Account>operator_doubleArrow(_createAccount_1, _function_1);
    Account _createAccount_2 = this.createAccount("Vicenzo", "vincenzo@foobar");
    final Procedure1<Account> _function_2 = new Procedure1<Account>() {
      @Override
      public void apply(final Account account) {
        EList<EObject> _contents = it.getContents();
        _contents.add(account);
        AccountsviewResourceManagerGen.this.createDefaultFolders(account);
      }
    };
    ObjectExtensions.<Account>operator_doubleArrow(_createAccount_2, _function_2);
  }
}
