/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.firstexample.views;

import org.eclipse.emf.common.util.URI; 
import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;

public class LibraryTreeFormView extends AbstractSaveableTreeFormView { 

    protected URI createResourceURI() { 
        return URI.createFileURI( System.getProperty("user.home") + "/MyLibrary.library" ); 
    } 

} 

