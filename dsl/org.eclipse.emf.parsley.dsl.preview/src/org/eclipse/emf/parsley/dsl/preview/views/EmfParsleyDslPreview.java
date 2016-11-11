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
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.viewers.ILabelProvider;
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
		lblNewLabel_1.setText("activator");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
				Button btnNewButton = new Button(parent, SWT.NONE);
				btnNewButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						parent.getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								loadInjector();
							}
						});
					}
				});
				btnNewButton.setText("Preview");

	}

	@Override
	public void setFocus() {

	}

	private Class<?> loadClass(String fullyQualifiedName) throws CoreException {
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
		ClassLoader parentClassLoader = javaProject.getClass().getClassLoader();
		URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
		URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
		return classLoader;
	}

	private void loadInjector() {
		String injectorProviderClassName = text.getText();
//						String activatorClassName = text_1.getText();
		try {
			Class<?> injectorProviderClass = loadClass(injectorProviderClassName);
			System.out.println("class loaded: " + injectorProviderClass);
//							Class<?> activatorClass = loadClass(activatorClassName);
//							System.out.println("class loaded: " + activatorClass);
//							Object activator = activatorClass.newInstance();
//							System.out.println("activator: " + activator);
			Method[] methods = injectorProviderClass.getMethods();
			Method createInjectorMethod = null;
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (method.getName().equals("createInjector")) {
					createInjectorMethod = method;
				}
			}
			// Does not work: probably because of AbastractUIPlugin loaded by different class loaders
			// Method method = injectorProviderClass.getMethod("createInjector", new Class<?>[] {AbstractUIPlugin.class});
			System.out.println("method: " + createInjectorMethod);
			Object injector = createInjectorMethod.invoke(null);
			System.out.println("injector: " + injector);
			System.out.println("injector class: " + injector.getClass());
			System.out.println("injector class: " + Injector.class.isAssignableFrom(injector.getClass()));
			Method getInstance = injector.getClass().getDeclaredMethod("getInstance", Class.class);
			System.out.println("getInstance method: " + getInstance);
			getInstance.setAccessible(true);
			Class<?> labelProviderClass = loadClass(ILabelProvider.class.getCanonicalName());
			Object result = getInstance.invoke(injector, labelProviderClass);
			System.out.println("result: " + result);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
