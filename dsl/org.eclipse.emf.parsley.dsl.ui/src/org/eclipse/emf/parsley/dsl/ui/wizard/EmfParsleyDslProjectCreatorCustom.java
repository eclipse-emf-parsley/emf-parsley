/**
 * 
 */
package org.eclipse.emf.parsley.dsl.ui.wizard;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator;
import org.eclipse.emf.parsley.views.EmfParsleyViewsActivator;
import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyDslProjectCreatorCustom extends
		EmfParsleyDslProjectCreator {
	static EmfParsleyDslNewProjectFiles filesGenerator = new EmfParsleyDslNewProjectFiles();

	static EmfParsleyProjectFilesGenerator projectFilesGenerator = new EmfParsleyProjectFilesGenerator();
		@Override
	protected List<String> getAllFolders() {
		return ImmutableList.of(SRC_ROOT,
				EmfParsleyDslOutputConfigurationProvider.EMFPARSLEY_GEN);
	}

	/**
	 * @return the names of the bundles that a new project requires. May not be
	 *         <code>null</code>
	 */
	protected List<String> getRequiredBundles() {
		return Lists.newArrayList(
				"org.eclipse.core.runtime",
				"org.eclipse.ui",
				EmfParsleyViewsActivator.PLUGIN_ID,
				"org.eclipse.xtext.xbase.lib");
		// don't add the components.dsl project dep
		// otherwise the plugin will depend on xtext stuff
		// which are not used by the generated code
		// DSL_GENERATOR_PROJECT_NAME);
	}
	
	@Override
	protected String getActivatorClassName() {
		return getProjectInfo().getProjectName()
				+ "."
				+ projectFilesGenerator.activatorName(
						getProjectInfo().getProjectName()).toString();
	}

	@Override
	protected void enhanceProject(final IProject project,
			final IProgressMonitor monitor) throws CoreException {
		String projectName = getProjectInfo().getProjectName();

		String srcFolder = "src";
		String projectPackagePath = srcFolder + "/"
				+ projectName.replaceAll("\\.", "/");

		String[] paths = { projectPackagePath };
		NewEmfParsleyProjectSupport.addToProjectStructure(project, paths,
				monitor);

		NewEmfParsleyProjectSupport.createActivator(project, projectName,
				projectPackagePath, monitor);
		NewEmfParsleyProjectSupport.createExecutableExtensionFactory(
				project, projectName, projectPackagePath, monitor);
		NewEmfParsleyProjectSupport.createModule(project, projectName,
				projectPackagePath, "EmfParsleyGuiceModuleGen", monitor);
		
		String dslFile = "";
		if(getProjectInfo().getSelectedTemplate()!=null){
			String superclassViewID=getProjectInfo().getSelectedTemplate().
						 getOrGenerateViewClass(project,
								projectName, projectPackagePath, monitor);
			dslFile = filesGenerator.dslFileWithView(projectName,superclassViewID).toString();	
		}else{
			dslFile = filesGenerator.exampleDslFile(projectName).toString();
		}
		
		NewEmfParsleyProjectSupport.createProjectFile(project,
				projectPackagePath + "/module.parsley", dslFile,
				NewEmfParsleyProjectSupport
						.createSubProgressMonitor(monitor));

		project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
	}
}
