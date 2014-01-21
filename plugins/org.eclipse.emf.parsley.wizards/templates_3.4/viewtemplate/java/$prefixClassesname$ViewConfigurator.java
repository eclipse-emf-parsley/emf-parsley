/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package $packageName$;

import org.eclipse.emf.parsley.view.masterdetail.ViewConfigurator;

import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class $prefixClassesname$ViewConfigurator extends ViewConfigurator {
	
	@Override
	public EObject getContainer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public EReference getListFeature() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boolean isEditable(){
%if editableView
		 return true;
%else
		 return false;
%endif
	}
	
}