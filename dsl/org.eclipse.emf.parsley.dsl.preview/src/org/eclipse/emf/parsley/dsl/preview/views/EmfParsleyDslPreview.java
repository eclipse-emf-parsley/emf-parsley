package org.eclipse.emf.parsley.dsl.preview.views;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.composite.FormFactory;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.emf.parsley.composite.TreeFormFactory;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.viewers.IViewerMouseListener;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.layout.TreeColumnLayout;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class EmfParsleyDslPreview extends ViewPart {
	public EmfParsleyDslPreview() {
	}

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.emf.parsley.dsl.preview.views.EmfParsleyDslPreview";
	private Text text;
	private Text text_1;
	private Composite composite;
	private Composite parsleyComponentParent;

	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("injector provider");

		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(parent, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("resource");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
				Button btnNewButton = new Button(parent, SWT.NONE);
				btnNewButton.setText("Preview");
				
				composite = new Composite(parent, SWT.NONE);
				composite.setLayout(new GridLayout(1, false));
				GridData gridData = new GridData();
				gridData.horizontalSpan = 3;
				gridData.horizontalAlignment = SWT.FILL;
				gridData.grabExcessHorizontalSpace = true;
				gridData.verticalAlignment = SWT.FILL;
				gridData.grabExcessVerticalSpace = true;
				composite.setLayoutData(gridData);
				
				addParsleyComponent();
				
				new Label(parent, SWT.NONE);
				new Label(parent, SWT.NONE);
				new Label(parent, SWT.NONE);

				btnNewButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						parent.getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								loadInjector(composite);
							}
						});
					}
				});
	}

	private void addParsleyComponent() {
		GridData gridData;
		parsleyComponentParent = new Composite(composite, SWT.NONE);
		parsleyComponentParent.setLayout(new GridLayout(1, false));
		
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		parsleyComponentParent.setLayoutData(gridData);
		
		TreeViewer treeViewer = new TreeViewer(parsleyComponentParent, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		tree.setLayoutData(gridData);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
	}

	@Override
	public void setFocus() {

	}

	private Class<?> tmploadClass(String fullyQualifiedName) throws CoreException {
		List<URLClassLoader> loaders = getClassLoaders();
		for (URLClassLoader loader : loaders) {
			try {
				Class<?> clazz = loader.loadClass(fullyQualifiedName);
				return clazz;
			} catch (ClassNotFoundException e) {
			}
		}
		return null;
	}

	private URLClassLoader getURLClassLoader(ClassLoader parentClassLoader) throws CoreException {
		List<IJavaProject> javaProjects = new ArrayList<IJavaProject>();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (IProject project : projects) {
			if (project.isOpen() && JavaProject.hasJavaNature(project)) {
				IJavaProject javaProject = JavaCore.create(project);
				javaProjects.add(javaProject);
				System.out.println("project: " + project.getName());
			}
		}
		List<URL> urlList = new ArrayList<URL>();
		for (IJavaProject javaProject : javaProjects) {
			urlList.addAll(getProjectClassPathEntries(javaProject));
		}
		URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
		URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
		return classLoader;
	}

	private List<URLClassLoader> getClassLoaders() throws CoreException {
		List<IJavaProject> javaProjects = new ArrayList<IJavaProject>();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (IProject project : projects) {
			if (project.isOpen() && JavaProject.hasJavaNature(project)) {
				IJavaProject javaProject = JavaCore.create(project);
				javaProjects.add(javaProject);
				System.out.println("project: " + project.getName());
			}
		}
		ArrayList<URLClassLoader> loaders = new ArrayList<URLClassLoader>();
		for (IJavaProject javaProject : javaProjects) {
			loaders.add(getProjectClassLoader(javaProject));
		}
		return loaders;
	}

	private URLClassLoader getProjectClassLoader(IJavaProject javaProject) throws CoreException {
		List<URL> urlList = getProjectClassPathEntries(javaProject);
//		ClassLoader parentClassLoader = javaProject.getClass().getClassLoader();
		URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
//		URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
		URLClassLoader classLoader = new URLClassLoader(urls);
		return classLoader;
	}

	private List<URL> getProjectClassPathEntries(IJavaProject javaProject) throws CoreException {
		String[] classPathEntries = JavaRuntime.computeDefaultRuntimeClassPath(javaProject);
		List<URL> urlList = new ArrayList<URL>();
		for (int i = 0; i < classPathEntries.length; i++) {
			String entry = classPathEntries[i];
			IPath path = new Path(entry);
			URL url;
			try {
				url = path.toFile().toURI().toURL();
				urlList.add(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return urlList;
	}

	private void loadInjector(Composite viewerParent) {
		String injectorProviderClassName = text.getText(); //"myparsleyproject.MyparsleyprojectCustomModule";// text.getText();
//						String activatorClassName = text_1.getText();
		try {
			URLClassLoader loader = getURLClassLoader(getClass().getClassLoader());
			Class<?> injectorProviderClass = loader.loadClass(injectorProviderClassName);
			Method createInjectorMethod = injectorProviderClass.getMethod("getInjector");
			Object injector = createInjectorMethod.invoke(null);
			Injector myInjector = (Injector) injector;
			ILabelProvider labelProvider = myInjector.getInstance(ILabelProvider.class);
			System.out.println("label provider: " + labelProvider);
//			initializeTreeViewer(myInjector, viewerParent);
			initializeTreeForm(myInjector, viewerParent);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void initializeTreeViewer(Injector myInjector, Composite viewerParent) {
		ViewerFactory viewerFactory = myInjector.getInstance(ViewerFactory.class);
		TreeViewer treeViewer = new TreeViewer(viewerParent);
		ResourceLoader resourceLoader = myInjector.getInstance(ResourceLoader.class);
		EditingDomain editingDomain = myInjector.getInstance(EditingDomain.class);
		Resource resource = resourceLoader.getResource(editingDomain, URI.createURI(text_1.getText())).getResource();
		viewerFactory.initialize(treeViewer, resource);
	}

	private void initializeTreeForm(Injector myInjector, Composite viewerParent) {
		TreeFormFactory treeFormFactory = myInjector.getInstance(TreeFormFactory.class);
//		ResourceLoader resourceLoader = myInjector.getInstance(ResourceLoader.class);
		EditingDomain editingDomain = myInjector.getInstance(EditingDomain.class);
		ResourceManager resourceManager = myInjector.getInstance(ResourceManager.class);
		ViewerContextMenuHelper contextMenuHelper = myInjector.getInstance(ViewerContextMenuHelper.class);
//		ViewerDragAndDropHelper dragAndDropHelper = myInjector.getInstance(ViewerDragAndDropHelper.class);
		IViewerMouseListener viewerMouseListener = myInjector.getInstance(IViewerMouseListener.class);
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource resource = new ResourceImpl();
		// an URI is required for making context menus and other
		// adapter factory related stuff works correctly
		resource.setURI(URI.createFileURI(System.getProperty("java.io.tmpdir") + "My.res"));
		resourceSet.getResources().add(resource);
//		Resource resource = resourceLoader.getResource(editingDomain, URI.createURI(text_1.getText())).getResource();
		if (resource.getContents().isEmpty()) {
			resourceManager.initialize(resource);
		}

		GridData gridData;
		parsleyComponentParent.dispose();
		parsleyComponentParent = new Composite(composite, SWT.NONE);
		parsleyComponentParent.setLayout(new GridLayout(1, false));
		
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		parsleyComponentParent.setLayoutData(gridData);
		
		TreeFormComposite treeForm = treeFormFactory.createTreeFormComposite(parsleyComponentParent, SWT.BORDER);
		treeForm.update(resource);
		StructuredViewer viewer = treeForm.getViewer();
		contextMenuHelper.addViewerContextMenu(viewer, editingDomain);
//		dragAndDropHelper.addDragAndDrop(viewer, editingDomain);
		viewer.getControl().addMouseListener(viewerMouseListener);
		
//		TreeViewer treeViewer = new TreeViewer(parsleyComponentParent, SWT.BORDER);
//		Tree tree = treeViewer.getTree();
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		treeForm.setLayoutData(gridData);
//		tree.setLayoutData(gridData);
//		tree.setHeaderVisible(true);
//		tree.setLinesVisible(true);
		
		composite.layout();
		parsleyComponentParent.layout();
	}

	private Method getMethod(Class<?> clazz, String name, String parameterName) {
		Method[] injectorMethods = clazz.getDeclaredMethods();
		Method getInstanceMethod = null;
		for (int i = 0; i < injectorMethods.length; i++) {
			Method injectorMethod = injectorMethods[i];
			if (injectorMethod.getName().equals(name)) {
				System.out.println(injectorMethod);
				if (injectorMethod.getParameterTypes()[0].getName().equals(parameterName)) {
					getInstanceMethod = injectorMethod;
					getInstanceMethod.setAccessible(true);
					break;
				}
			}
		}
		return getInstanceMethod;
	}
}
