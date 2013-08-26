package org.eclipse.emf.parsley.examples.cdo.treeform.customizations;

import library.business.CommonBusiness;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.cdo.CDOSessionManager;

public class TreeformCDOSessionManager extends CDOSessionManager {

	@Override
	public CDOSession getSession(EObject obj) {
		
		return CommonBusiness.getSessionForTest("localhost");
	}

	@Override
	public CDOResource getTransactionalResourceFor(EClass eReferenceType) {
		// TODO Auto-generated method stub
		return null;
	}

}
