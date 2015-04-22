/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.generator.common

class EmfParsleyViewFilesGenerator {

	def generateViewClass(String projectName, String className, String extendsClass)
'''
package «projectName»;

import org.eclipse.emf.parsley.views.«extendsClass»;

public class «className» extends «extendsClass» {
	
}
'''

	def generateConcreteForResourceTreeView(String projectName, String className, String extendsClass)
'''
package «projectName»;

import org.eclipse.emf.parsley.views.«extendsClass»;

import org.eclipse.emf.common.util.URI;

public class «className» extends «extendsClass» {

	@Override
	protected URI createResourceURI() {
		// TODO Insert here the resource URI
		return null;
	}
	
}
'''

	def generateConcreteForResourceTableView(String projectName, String simpleName, String extendsClass)
'''
package «projectName»;

import org.eclipse.emf.parsley.views.«extendsClass»;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;

public class «simpleName» extends «extendsClass» {

	@Override
	protected Object getContents(Resource resource) {
		// TODO  How to reach the contents from the resource
		return null;
	}

	@Override
	protected EClass getEClass() {
		// TODO  Insert here the EClass to be represented 
		return null;
	}

	@Override
	protected URI createResourceURI() {
		// TODO  Insert here the resource URI
		return null;
	}
}
'''

	def generateConcreteForOnSelectionTableView(String projectName, String simpleName)
'''
package «projectName»;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.views.AbstractOnSelectionTableView;

public class «simpleName» extends AbstractOnSelectionTableView {

	@Override
	protected EStructuralFeature getEStructuralFeature() {
		// TODO How to reach the contents from the selected object
		return null;
	}
	

}
'''


	def generateConcreteForOnSelectionTableFormView(String projectName, String simpleName)
'''
package «projectName»;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.views.AbstractOnSelectionTableFormView;

public class «simpleName» extends AbstractOnSelectionTableFormView {

	@Override
	protected EStructuralFeature getEStructuralFeature() {
		// TODO How to reach the contents from the selected object
		return null;
	}
	

}
'''



	def generateTreeFormView(String projectName, String simpleName)
'''
package «projectName»;

import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView;

import org.eclipse.emf.common.util.URI;

public class «simpleName»TreeFormView extends AbstractSaveableTreeFormView {

	@Override
	protected URI createResourceURI() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
'''

	def generateTableView(String projectName, String simpleName)
'''
package «projectName»;

import org.eclipse.emf.parsley.views.AbstractSaveableTableView;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;

public class «simpleName»TableView extends AbstractSaveableTableView {

	@Override
	protected Object getContents(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EClass getEClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected URI createResourceURI() {
		// TODO Auto-generated method stub
		return null;
	}
}
'''

	def generatePluginXml(String factoryClass, String qualifiedNameView)
'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
           <view
               class="«factoryClass»:«qualifiedNameView»"
               id="«qualifiedNameView»"
               name="Form View"
               restorable="true"/>
   </extension>
   <extension
         point="org.eclipse.ui.perspectiveExtensions">
      <perspectiveExtension
            targetID="*">
         <view
               id="«qualifiedNameView»"
               minimized="false"
               ratio="100"
               relationship="stack"
               relative="org.eclipse.ui.editorss"
               visible="true">
         </view>
      </perspectiveExtension>
   </extension>
</plugin>
'''
}