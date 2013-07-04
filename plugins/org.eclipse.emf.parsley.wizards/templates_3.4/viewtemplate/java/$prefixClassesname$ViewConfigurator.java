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