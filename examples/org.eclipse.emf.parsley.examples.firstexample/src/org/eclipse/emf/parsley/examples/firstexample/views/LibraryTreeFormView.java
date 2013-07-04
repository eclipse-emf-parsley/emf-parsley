package org.eclipse.emf.parsley.examples.firstexample.views;

import org.eclipse.emf.common.util.URI; 
import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;

public class LibraryTreeFormView extends AbstractSaveableTreeFormView { 

    protected URI createResourceURI() { 
        return URI.createFileURI( System.getProperty("user.home") + "/MyLibrary.library" ); 
    } 

} 

