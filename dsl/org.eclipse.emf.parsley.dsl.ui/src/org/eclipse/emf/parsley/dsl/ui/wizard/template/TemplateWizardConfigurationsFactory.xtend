/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.emf.parsley.generator.common.EmfParsleyViewFilesGenerator

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class TemplateWizardConfigurationsFactory {

	protected static final String ABSTRACT_SAVEABLE = "AbstractSaveable";

	protected static final EmfParsleyViewFilesGenerator viewFilesGenerator = new EmfParsleyViewFilesGenerator();

	def createTemplateWizardConfigurations() {
		val reactOnSelection = "The view reacts on selection from other components."
		val saveableView = "This view reads the content from a resource and can save changes."
		val noUserChange = "<p><b>No user change is needed to run the project</b></p>"
		val theUserMustSpecify = "<p><b>The user must specify:</b></p>"
		val structuralFeature = "<li>the EStructuralFeature for getting the contents from the resource</li>"
		val resourceURI = "<li>the resource URI</li>"
		val eclassToRepresentAndHowToReachResource = "<li>how to reach the contents from the resource</li>" +
					"<li>the EClass to be represented</li>"
		
		newArrayList(
			new TemplateWizardConfiguration(
				"On selection Form View",
				"OnSelectionTreeView",
				"",
				[ projectName, className |
					viewFilesGenerator.generateConcreteForOnSelectionTableView(projectName, className).toString()
				],
				createDescription("form", reactOnSelection, noUserChange)
			),
			new TemplateWizardConfiguration(
				"On selection Table Form View",
				"OnSelectionTableFormView",
				"",
				[ projectName, className |
					viewFilesGenerator.generateConcreteForOnSelectionTableFormView(projectName, className).toString()
				],
				createDescription("table and a form", reactOnSelection,
					'''
					«theUserMustSpecify»
					«structuralFeature»
					'''
				)
			),
			new TemplateWizardConfiguration(
				"On selection Table View",
				"OnSelectionTableView",
				"",
				[ projectName, className |
					viewFilesGenerator.generateConcreteForOnSelectionTableView(projectName, className).toString()
				],
				createDescription("table", reactOnSelection,
					'''
					«theUserMustSpecify»
					«structuralFeature»
					'''
				)
			),
			new TemplateWizardConfiguration(
				"On selection Tree Form View",
				"OnSelectionTreeFormView",
				"",
				[ projectName, className |
					viewFilesGenerator.generateConcreteForOnSelectionTableView(projectName, className).toString()
				],
				createDescription("tree and a form", reactOnSelection, noUserChange)
			),
			new TemplateWizardConfiguration(
				"Saveable Table Form View",
				"",
				"TableFormView",
				[ projectName, className |
					viewFilesGenerator.generateConcreteForResourceTableView(projectName, className,
						ABSTRACT_SAVEABLE + "TableFormView").toString()
				],
				createDescription("table and a form", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					«eclassToRepresentAndHowToReachResource»
					'''
				)
			),
			new TemplateWizardConfiguration(
				"Saveable Table View",
				"",
				"TableView",
				[ projectName, className |
					viewFilesGenerator.generateConcreteForResourceTableView(projectName, className,
						ABSTRACT_SAVEABLE + "TableView").toString()
				],
				createDescription("table", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					«eclassToRepresentAndHowToReachResource»
					'''
				)
			),
			new TemplateWizardConfiguration(
				"Saveable Tree Form View",
				"",
				"TreeFormView",
				[ projectName, className |
					viewFilesGenerator.generateConcreteForResourceTreeView(projectName, className,
						ABSTRACT_SAVEABLE + "TreeView").toString()
				],
				createDescription("tree and a form", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					'''
				)
			),
			new TemplateWizardConfiguration(
				"Saveable Tree View",
				"",
				"TreeView",
				[ projectName, className |
					viewFilesGenerator.generateConcreteForResourceTreeView(projectName, className,
						ABSTRACT_SAVEABLE + "TreeView").toString()
				],
				createDescription("tree", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					'''
				)
			)
		)
	}

	def private createDescription(CharSequence component, CharSequence componentDescription, CharSequence userMustSpecify) {
		'''
		<p>This wizard creates a plug-in project that contains a view with a <b>«component»</b>.</p>
		<p>«componentDescription»</p>
		«userMustSpecify»
		'''
	}
}
