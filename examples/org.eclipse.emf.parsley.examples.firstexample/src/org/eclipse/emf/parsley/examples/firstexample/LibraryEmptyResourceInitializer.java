/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - initial API and implementation
 *******************************************************************************/
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


