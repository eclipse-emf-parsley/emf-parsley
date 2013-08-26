package library.business;

import library.Book;
import library.Library;
import library.LibraryFactory;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.ecore.resource.Resource;

public class ModelBusiness {
	private static final String LIBRARY_RESOURCE_NAME = "/Library";
	static Library model;
	
	public synchronized static Library getOrCreateModel() {
		if(model==null){
			model=load();
			try{
				if(model==null){
					create();
					//reload a non-transactional object
					model=load();
				}
			}catch (CommitException e) {
				e.printStackTrace();
			}
		}
		return model;
	}

	private static Library load() {
		CDOView view=CommonBusiness.session.openView();
		try{
			CDOResource resource = view.getResource(LIBRARY_RESOURCE_NAME);
			if(resource!=null){
				view.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.ALL);
				if(!resource.getContents().isEmpty()){
					return (Library) resource.getContents().get(0);
				}
			}
		}catch (Exception e) {
			// Resource does not exsist: ignored
		}
		return null;
	}

	
	private static Library create() throws CommitException {
		CDOTransaction transaction=CommonBusiness.session.openTransaction();
		CDOResource resource = transaction.getOrCreateResource(LIBRARY_RESOURCE_NAME);
		Library model = initializeResource(resource);
		transaction.commit();
		return model;
	}

	protected static Library initializeResource(Resource resource) {
		Library library=LibraryFactory.eINSTANCE.createLibrary();
		resource.getContents().add(library);
		Book book = LibraryFactory.eINSTANCE.createBook();
		library.getBooks().add(book);
		return library;
	}

	
	
}
