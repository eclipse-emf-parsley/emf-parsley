/**
 * Copyright (c) 2026 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.parsley.dsl.ui.wizard

import java.util.Locale
import org.eclipse.emf.parsley.dsl.additional.builder.builder.EmfParsleyDslPluginXmlBuilder
import org.eclipse.emf.parsley.dsl.additional.builder.builder.EmfParsleyDslPluginXmlNature
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
import org.eclipse.xtext.ui.XtextProjectHelper
import org.eclipse.xtext.ui.util.PluginProjectFactory
import org.eclipse.xtext.ui.util.TextFileContributor
import org.eclipse.xtext.ui.wizard.IExtendedProjectInfo
import org.eclipse.xtext.ui.wizard.template.IProjectGenerator
import org.eclipse.xtext.ui.wizard.template.IProjectTemplateProvider
import org.eclipse.xtext.ui.wizard.template.ProjectTemplate

class EmfParsleyDslProjectTemplateProvider implements IProjectTemplateProvider {
	override getProjectTemplates() {
		#[new EmptyParsleyProjectTemplate,
			new OnSelectionTreeProjectTemplate, new OnSelectionFormProjectTemplate,
			new OnSelectionTableFormProjectTemplate, new OnSelectionTableProjectTemplate,
			new OnSelectionTreeFormProjectTemplate, new SaveableTableFormProjectTemplate,
			new SaveableTableProjectTemplate, new SaveableTreeFormProjectTemplate,
			new SaveableTreeProjectTemplate, new SaveableTreeWithColumnsProjectTemplate]
	}
}

@ProjectTemplate(label="Empty EMF Parsley Project", icon="sample.gif",
	description="<p>Create a plug-in project with an empty EMF Parsley DSL module.</p>")
final class EmptyParsleyProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, null, rap.value)
	}
}

@ProjectTemplate(label="On selection Tree View", icon="sample.gif",
	description="<p>A tree view that reacts to selection from other components.</p>")
final class OnSelectionTreeProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, OnSelectionTreeView, rap.value)
	}
}

@ProjectTemplate(label="On selection Form View", icon="sample.gif",
	description="<p>A form view that reacts to selection from other components.</p>")
final class OnSelectionFormProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, OnSelectionFormView, rap.value)
	}
}

@ProjectTemplate(label="On selection Table Form View", icon="sample.gif",
	description="<p>A table and form view that reacts to selection. Specify the EClass to show.</p>")
final class OnSelectionTableFormProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, OnSelectionTableFormView, rap.value)
	}
}

@ProjectTemplate(label="On selection Table View", icon="sample.gif",
	description="<p>A table view that reacts to selection. Specify the EClass to show.</p>")
final class OnSelectionTableProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, OnSelectionTableView, rap.value)
	}
}

@ProjectTemplate(label="On selection Tree Form View", icon="sample.gif",
	description="<p>A tree and form view that reacts to selection from other components.</p>")
final class OnSelectionTreeFormProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, OnSelectionTreeFormView, rap.value)
	}
}

@ProjectTemplate(label="Saveable Table Form View", icon="sample.gif",
	description="<p>A table and form view backed by a resource. Specify the resource URI and EClass.</p>")
final class SaveableTableFormProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, SaveableTableFormView, rap.value)
	}
}

@ProjectTemplate(label="Saveable Table View", icon="sample.gif",
	description="<p>A table view backed by a resource. Specify the resource URI and EClass.</p>")
final class SaveableTableProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, SaveableTableView, rap.value)
	}
}

@ProjectTemplate(label="Saveable Tree Form View", icon="sample.gif",
	description="<p>A tree and form view backed by a resource. Specify the resource URI.</p>")
final class SaveableTreeFormProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, SaveableTreeFormView, rap.value)
	}
}

@ProjectTemplate(label="Saveable Tree View", icon="sample.gif",
	description="<p>A tree view backed by a resource. Specify the resource URI.</p>")
final class SaveableTreeProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, SaveableTreeView, rap.value)
	}
}

@ProjectTemplate(label="Saveable Tree With Columns View", icon="sample.gif",
	description="<p>A tree with columns backed by a resource. Specify the resource URI and EClass.</p>")
final class SaveableTreeWithColumnsProjectTemplate {
	val rap = check("Prepare for Remote Application Platform (RAP)", false)
	override generateProjects(IProjectGenerator generator) {
		ParsleyProjectFiles.generate(generator, projectInfo, SaveableTreeWithColumnsView, rap.value)
	}
}

/** All generated project files and project settings live here. */
class ParsleyProjectFiles {
	// A marker keeps indented blank lines in Xtend rich strings until file creation.
	private static val BLANK_LINE = "__PARSLEY_BLANK_LINE__"

	static def generate(IProjectGenerator generator, IExtendedProjectInfo info, Class<?> viewType, boolean rap) {
		val name = info.projectName.replaceAll("[^a-zA-Z0-9\\._]", "_").toLowerCase(Locale.ENGLISH)
		val simpleName = name.substring(name.lastIndexOf('.') + 1).toFirstUpper
		val sourcePath = "src/" + name.replace('.', '/') + "/"
		val factory = new PluginProjectFactory => [
			projectName = info.projectName
			location = info.locationPath
			withPluginXml = viewType !== null
			activatorClassName = name + "." + simpleName + "Activator"
			projectNatures += #["org.eclipse.jdt.core.javanature", "org.eclipse.pde.PluginNature",
				XtextProjectHelper.NATURE_ID, EmfParsleyDslPluginXmlNature.NATURE_ID]
			builderIds += #["org.eclipse.jdt.core.javabuilder", "org.eclipse.pde.ManifestBuilder",
				"org.eclipse.pde.SchemaBuilder", XtextProjectHelper.BUILDER_ID,
				EmfParsleyDslPluginXmlBuilder.BUILDER_ID]
			folders += #["src", "emfparsley-gen"]
			requiredBundles += "org.eclipse.core.runtime"
			if (rap) {
				requiredBundles += #["org.eclipse.ui;resolution:=optional", "org.eclipse.rap.ui;resolution:=optional",
					"org.eclipse.emf.parsley.views;resolution:=optional", "org.eclipse.emf.parsley.rap.views;resolution:=optional"]
			} else {
				requiredBundles += #["org.eclipse.ui", "org.eclipse.emf.parsley.views"]
			}
			requiredBundles += "org.eclipse.xtext.xbase.lib"
			importedPackages += #["org.apache.log4j", "org.apache.commons.logging"]
		]
		addFile(factory, sourcePath + simpleName + "Activator.java", activator(name, simpleName))
		if (viewType !== null) {
			val partName = simpleName + viewType.simpleName
			addFile(factory, sourcePath + partName + ".java", viewClass(name, partName, viewType.name))
		}
		addFile(factory, sourcePath + simpleName + ".parsley", module(name, simpleName, viewType))
		addFile(factory, ".settings/org.eclipse.core.resources.prefs", '''
			eclipse.preferences.version=1
			encoding/<project>=UTF-8
		''')
		generator.generate(factory)
	}

	private static def addFile(PluginProjectFactory factory, CharSequence path, CharSequence contents) {
		factory.addContributor(new TextFileContributor(path, contents.toString.replace(BLANK_LINE, "")))
	}

	private static def activator(String name, String simpleName) '''
		package «name»;
		«BLANK_LINE»
		import org.osgi.framework.BundleContext;
		import org.eclipse.ui.plugin.AbstractUIPlugin;
		«BLANK_LINE»
		/**
		 * The activator class controls the plug-in life cycle
		 */
		public class «simpleName»Activator extends AbstractUIPlugin {
		«BLANK_LINE»
			// The plug-in ID
			public static final String PLUGIN_ID = "«name»"; //$NON-NLS-1$
		«BLANK_LINE»
			// The shared instance
			private static «simpleName»Activator plugin;
		«BLANK_LINE»
			public «simpleName»Activator() {
			}
		«BLANK_LINE»
			/*
			 * (non-Javadoc)
			 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
			 */
			public void start(BundleContext context) throws Exception {
				super.start(context);
				plugin = this;
			}
		«BLANK_LINE»
			/*
			 * (non-Javadoc)
			 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
			 */
			public void stop(BundleContext context) throws Exception {
				plugin = null;
				super.stop(context);
			}
		«BLANK_LINE»
			/**
			 * Returns the shared instance
			 *
			 * @return the shared instance
			 */
			public static «simpleName»Activator getDefault() {
				return plugin;
			}
		«BLANK_LINE»
		}
	'''

	private static def viewClass(String name, String partName, String superClass) '''
		package «name»;
		«BLANK_LINE»
		public class «partName» extends «superClass» {
			«BLANK_LINE»
		}
	'''

	private static def module(String name, String simpleName, Class<?> viewType) {
		if (viewType === null)
			return '''
				/* «name» EMF Parsley Dsl Module file */
				module «name» {
					«BLANK_LINE»
				}
			'''
		val partName = simpleName + viewType.simpleName
		val configurator = configurator(partName, viewType)
		val resourceManager = if (isSaveable(viewType)) "\n" + resourceManager else ""
		'''
			import «name».«partName»
			«BLANK_LINE»
			/* «name» EMF Parsley Dsl Module file */
			module «name» {
				«BLANK_LINE»
				parts {
					viewpart «name» {
						viewname "«simpleName»"
						viewclass «partName»
					}
				}
				«BLANK_LINE»
				«configurator»
				«resourceManager»
			}
		'''
	}

	private static def configurator(String partName, Class<?> viewType) {
		val eClass = if (viewType == OnSelectionTableView || viewType == OnSelectionTableFormView ||
			viewType == SaveableTableView || viewType == SaveableTableFormView)
			genEClass(partName, false)
		else if (viewType == SaveableTreeWithColumnsView)
			genEClass(partName, true)
		else ""
		val uri = if (isSaveable(viewType)) genResourceURI(partName) else ""
		if (eClass.empty && uri.empty) return ""
		return '''
			configurator {
				«eClass»
				«uri»
			}
		'''
	}

	private static def boolean isSaveable(Class<?> viewType) {
		viewType == SaveableTableFormView || viewType == SaveableTableView ||
			viewType == SaveableTreeFormView || viewType == SaveableTreeView ||
			viewType == SaveableTreeWithColumnsView
	}

	private static def genEClass(String partName, boolean features) '''
		eClass {
			«partName» -> {
				// TODO return the EClass «IF features»with the features to be shown«ELSE»of objects to be shown«ENDIF»
			}
		}
	'''

	private static def genResourceURI(String partName) '''
		resourceURI {
			«partName» -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
	'''

	private static def resourceManager() '''
		resourceManager {
			initializeResource {
				// Optional: initialize an empty Resource
				// 'it' is of type Resource
				// e.g., it.getContents += myFactory.createMyClass
			}
		}
	'''
}
