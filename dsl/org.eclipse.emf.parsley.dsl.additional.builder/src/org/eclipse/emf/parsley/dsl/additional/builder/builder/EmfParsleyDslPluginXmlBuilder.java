package org.eclipse.emf.parsley.dsl.additional.builder.builder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.parsley.dsl.additional.builder.Activator;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.dsl.pluginxml.PluginXmlLoader;
import org.eclipse.xtext.util.StringInputStream;

import com.google.common.io.CharStreams;

public class EmfParsleyDslPluginXmlBuilder extends IncrementalProjectBuilder {

	class EmfParsleyDslPluginXmlBuilderDeltaVisitor implements IResourceDeltaVisitor {
		@Override
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			if (delta.getKind() != IResourceDelta.REMOVED) {
				// handle changed resource
				copyFromGeneratedPluginXml(resource);
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class EmfParsleyDslPluginXmlBuilderResourceVisitor implements IResourceVisitor {
		@Override
		public boolean visit(IResource resource) throws CoreException {
			copyFromGeneratedPluginXml(resource);
			//return true to continue visiting children.
			return true;
		}
	}

	public static final String BUILDER_ID = "org.eclipse.emf.parsley.dsl.additional.builder.emfParsleyDslPluginXmlBuilder";

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		IResourceDelta delta = getDelta(getProject());
		if (delta == null) {
			fullBuild(monitor);
		} else {
			incrementalBuild(delta, monitor);
		}
		return null;
	}

	void copyFromGeneratedPluginXml(IResource resource) throws CoreException {
		if (resource instanceof IFile && resource.getName()
				.endsWith(EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN_EXTENSION)) {
			IFile file = (IFile) resource;

			IFile pluginXml = getProject().getFile("/plugin.xml");
			PluginXmlLoader pluginXmlLoader = null;
			if (!pluginXml.exists()) {
				pluginXmlLoader = new PluginXmlLoader("");
				pluginXmlLoader.copyFromPluginXml(loadFromResource(file));
				pluginXml.create(new StringInputStream(pluginXmlLoader.getContentsAsString()), true, new NullProgressMonitor());
			} else {
				pluginXmlLoader = new PluginXmlLoader(loadFromResource(pluginXml));
				pluginXmlLoader.load();
				pluginXmlLoader.copyFromPluginXml(loadFromResource(file));
				pluginXml.setContents(new StringInputStream(pluginXmlLoader.getContentsAsString()), true, true, new NullProgressMonitor());
			}
		}
	}

	protected String loadFromResource(IFile file) throws CoreException {
		return loadFromResource(new InputStreamReader(file.getContents()), file.getFullPath().toString());
	}

	protected String loadFromResource(InputStreamReader reader, String information) throws CoreException {
		try {
			return CharStreams.toString(reader);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "while reading " + information, e));
		}
	}

	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		getProject().accept(new EmfParsleyDslPluginXmlBuilderResourceVisitor());
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new EmfParsleyDslPluginXmlBuilderDeltaVisitor());
	}
}
