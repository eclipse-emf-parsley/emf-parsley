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

import org.eclipse.emf.parsley.views.OnSelectionFormView
import org.eclipse.emf.parsley.views.OnSelectionTableFormView
import org.eclipse.emf.parsley.views.OnSelectionTableView
import org.eclipse.emf.parsley.views.OnSelectionTreeFormView
import org.eclipse.emf.parsley.views.OnSelectionTreeView
import org.eclipse.emf.parsley.views.SaveableTableFormView
import org.eclipse.emf.parsley.views.SaveableTableView
import org.eclipse.emf.parsley.views.SaveableTreeFormView
import org.eclipse.emf.parsley.views.SaveableTreeView
import org.eclipse.emf.parsley.views.SaveableTreeWithColumnsView


/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
class TemplateWizardConfigurationsFactory {

	static class TemplateWizardConfigurationForOnSelectionTableView extends TemplateWizardConfiguration {
	
		new(String label, CharSequence description, Class<?> superClass) {
			super(label, description, superClass)
		}

		override getConfiguratorElements(String projectName) {
			getProjectFilesGenerator.
				genEClass(getPartClassName(projectName)).
					toString
		}
	}

	static class TemplateWizardConfigurationForSaveableView extends TemplateWizardConfiguration {
	
		new(String label, CharSequence description, Class<?> superClass) {
			super(label, description, superClass)
		}

		override getResourceManager() {
			'''
			
			«getProjectFilesGenerator.
				genResourceManager(
					getProjectFilesGenerator.genInitializeResource
				)»
			'''
			.toString
		}
		
	}

	static class TemplateWizardConfigurationForSaveableTableView extends TemplateWizardConfigurationForSaveableView {
	
		new(String label, CharSequence description, Class<?> superClass) {
			super(label, description, superClass)
		}

		override getConfiguratorElements(String projectName) {
			val partClassName = getPartClassName(projectName)
			'''
			«getProjectFilesGenerator.
				genEClass(partClassName)»
			«getProjectFilesGenerator.
				genResourceURI(partClassName)»
			'''
		}
	}

	static class TemplateWizardConfigurationForSaveableTreeWithColumnsView extends TemplateWizardConfigurationForSaveableView {
	
		new(String label, CharSequence description, Class<?> superClass) {
			super(label, description, superClass)
		}

		override getConfiguratorElements(String projectName) {
			val partClassName = getPartClassName(projectName)
			'''
			«getProjectFilesGenerator.
				genFeaturesEClass(partClassName)»
			«getProjectFilesGenerator.
				genResourceURI(partClassName)»
			'''
		}
	}

	static class TemplateWizardConfigurationForSaveableTreeView extends TemplateWizardConfigurationForSaveableView {
	
		new(String label, CharSequence description, Class<?> superClass) {
			super(label, description, superClass)
		}

		override getConfiguratorElements(String projectName) {
			getProjectFilesGenerator.
				genResourceURI(getPartClassName(projectName)).
					toString
		}
	}

	def createTemplateWizardConfigurations() {
		val reactOnSelection = "The view reacts on selection from other components."
		val saveableView = "This view reads the content from a resource and can save changes."
		val noUserChange = "<p><b>No user change is needed to run the project</b></p>"
		val theUserMustSpecify = "<p><b>The user must specify:</b></p>"
		val resourceURI = "<li>the resource URI</li>"
		val eclassToRepresent = "<li>the EClass of objects to be shown</li>"
		val eclassForFeatures = "<li>the EClass with the features to be shown</li>"

		newArrayList(
			new TemplateWizardConfiguration(
				"On selection Tree View",
				createDescription("tree", reactOnSelection, noUserChange),
				OnSelectionTreeView
			),
			new TemplateWizardConfiguration(
				"On selection Form View",
				createDescription("form", reactOnSelection, noUserChange),
				OnSelectionFormView
			),
			new TemplateWizardConfigurationForOnSelectionTableView(
				"On selection Table Form View",
				createDescription("table and a form", reactOnSelection,
					'''
					«theUserMustSpecify»
					«eclassToRepresent»
					'''
				),
				OnSelectionTableFormView
			),
			new TemplateWizardConfigurationForOnSelectionTableView(
				"On selection Table View",
				createDescription("table", reactOnSelection,
					'''
					«theUserMustSpecify»
					«eclassToRepresent»
					'''
				),
				OnSelectionTableView
			),
			new TemplateWizardConfiguration(
				"On selection Tree Form View",
				createDescription("tree and a form", reactOnSelection, noUserChange),
				OnSelectionTreeFormView
			),
			new TemplateWizardConfigurationForSaveableTableView(
				"Saveable Table Form View",
				createDescription("table and a form", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					«eclassToRepresent»
					'''
				),
				SaveableTableFormView
			),
			new TemplateWizardConfigurationForSaveableTableView(
				"Saveable Table View",
				createDescription("table", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					«eclassToRepresent»
					'''
				),
				SaveableTableView
			),
			new TemplateWizardConfigurationForSaveableTreeView(
				"Saveable Tree Form View",
				createDescription("tree and a form", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					'''
				),
				SaveableTreeFormView
			),
			new TemplateWizardConfigurationForSaveableTreeView(
				"Saveable Tree View",
				createDescription("tree", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					'''
				),
				SaveableTreeView
			),
			new TemplateWizardConfigurationForSaveableTreeWithColumnsView(
				"Saveable Tree With Columns View",
				createDescription("tree with columns", saveableView, 
					'''
					«theUserMustSpecify»
					«resourceURI»
					«eclassForFeatures»
					'''
				),
				SaveableTreeWithColumnsView
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
