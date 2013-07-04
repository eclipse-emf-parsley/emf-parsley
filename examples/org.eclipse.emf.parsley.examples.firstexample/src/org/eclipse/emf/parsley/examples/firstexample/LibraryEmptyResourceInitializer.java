package org.eclipse.emf.parsley.examples.firstexample;

import org.eclipse.emf.ecore.resource.Resource; 
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory; 
import org.eclipse.emf.examples.extlibrary.Library; 
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;

public class LibraryEmptyResourceInitializer  extends EmptyResourceInitializer {  
     
   public void initialize(Resource resource) {  
       super.initialize(resource);  
       Library library = EXTLibraryFactory.eINSTANCE.createLibrary();  
       resource.getContents().add(library);  
   }  

}  


