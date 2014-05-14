package org.eclipse.emf.parsley.examples.cdo.company.handler;

import org.eclipse.emf.parsley.examples.cdo.company.Company;
import org.eclipse.emf.parsley.examples.cdo.company.Category;
import org.eclipse.emf.parsley.examples.cdo.company.Product;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyFactory;

public class AddNewProductHandler extends AbstractAddCompanyChildrenHandler{

	@Override
	protected void populateChildren(Company rcpVisionCompany) {
		if(rcpVisionCompany.getCategories().size()==0){
			Category defaultCategory=CompanyFactory.eINSTANCE.createCategory();
			defaultCategory.setName("default");
			rcpVisionCompany.getCategories().add(defaultCategory);
		}
		Product product= CompanyFactory.eINSTANCE.createProduct();
		product.setName("new product");
		rcpVisionCompany.getCategories().get(0).getProducts().add(product);
	}


}
