/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.action;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.ValidateAction;

public class EditingDomainValidateAction extends ValidateAction{
	
	  public void setEditingDomain(EditingDomain domain){
		  this.domain=domain;
	  }
  }