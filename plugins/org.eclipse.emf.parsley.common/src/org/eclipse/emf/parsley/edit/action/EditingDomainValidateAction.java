package org.eclipse.emf.parsley.edit.action;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.ValidateAction;

public class EditingDomainValidateAction extends ValidateAction{
	
	  public void setEditingDomain(EditingDomain domain){
		  this.domain=domain;
	  }
  }