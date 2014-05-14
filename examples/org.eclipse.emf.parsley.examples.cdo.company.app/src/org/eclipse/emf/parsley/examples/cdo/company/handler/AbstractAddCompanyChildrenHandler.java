package org.eclipse.emf.parsley.examples.cdo.company.handler;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.examples.cdo.company.Company;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyFactory;
import org.eclipse.emf.parsley.examples.cdo.company.app.Activator;
import org.eclipse.emf.parsley.resource.ResourceLoader;

import com.google.inject.Injector;

public abstract class AbstractAddCompanyChildrenHandler extends AbstractHandler {

	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//Using Injector from Parsley
		Injector injector = Activator.getDefault().getInjector();
		ResourceLoader reourceLoader=injector.getInstance(ResourceLoader.class);
		AdapterFactoryEditingDomain editingDomain=injector.getInstance(AdapterFactoryEditingDomain.class);
		//Getting resource
		Resource resource = reourceLoader.getResource(editingDomain, URI.createURI("cdo://localhost/repo1/testResource")).getResource();
		Company rcpVisionCompany=null;
		if(resource.getContents().size()==0){
			//Creating a new Company as main container
			rcpVisionCompany= CompanyFactory.eINSTANCE.createCompany();
			rcpVisionCompany.setName("RCP Vision");
			resource.getContents().add(rcpVisionCompany);
		}else{
			rcpVisionCompany=(Company) resource.getContents().get(0);
		}
		
		populateChildren(rcpVisionCompany);
		
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	protected abstract void populateChildren(Company rcpVisionCompany);
}
