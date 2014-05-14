package org.eclipse.emf.parsley.examples.cdo.company.handler;

import org.eclipse.emf.parsley.examples.cdo.company.Company;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyFactory;
import org.eclipse.emf.parsley.examples.cdo.company.Customer;

public class AddNewCustomerHandler extends AbstractAddCompanyChildrenHandler{

	@Override
	protected void populateChildren(Company rcpVisionCompany) {
		Customer customer= CompanyFactory.eINSTANCE.createCustomer();
		customer.setName("new customer");
		rcpVisionCompany.getCustomers().add(customer);
	}


}
