/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Injector;

/**
 * A class for handling singletons at JEE application level
 * 
 * @author Vincenzo Caselli
 * 
 */
public class Application {
    // private static final String HTTP_SESSION = "HTTP_SESSION";
    private static Application application;

    private Map<String, Object> id2objectMap;
    private Map<Object, String> objec2idtMap;

    private Injector injector;
    private ResourceSet resourceSet;
    private Display display;
    private Shell shell;

    @Inject
    private Configurator configurator;
    @Inject
    private ResourceLoader resourceLoader;

    private Application() {}

    public static synchronized Application getInstance(HttpServletRequest request) {
        // Application application = (Application)
        // session.getAttribute(HTTP_SESSION);
        if (application == null) {
            // Create and init application object
            application = new Application();
            application.id2objectMap = new HashMap<String, Object>();
            application.objec2idtMap = new HashMap<Object, String>();

            application.injector = (Injector) request.getServletContext().getAttribute(Injector.class.getName());
            // application.injector = Guice.createInjector(new ParsleyWebGuiceModule(null));
            application.injector.injectMembers(application);
            application.resourceSet = new ResourceSetImpl();

            application.display = new Display();
            application.shell = new Shell(application.display);

            // final CyclicBarrier barrier = new CyclicBarrier(2);
            // Thread thread = new Thread() {
            // @Override
            // public void run() {
            // try {
            // application.display = Display.getDefault();
            // application.shell = new Shell(application.display);
            // barrier.await();
            // while (!application.shell.isDisposed()) {
            // if (!application.display.readAndDispatch()) {
            // application.display.sleep();
            // }
            // }
            // } catch (Exception e) {
            // e.printStackTrace();
            // }
            // }
            // };
            // thread.setDaemon(true);
            // thread.start();
            // try {
            // barrier.await();
            // } catch (Exception e) {
            // e.printStackTrace();
            // }
            //
            // Composite composite = new Composite();
            //
            // Text text = new Text(composite, 0);
            //
            // System.out.println("creato Text");

            // ServletContext servletContext = request.getServletContext();
            // ApplicationContextImpl applicationContext =
            // ApplicationContextImpl.getFrom(servletContext);
            // ServiceContext context = new ServiceContext(request, null, applicationContext);
            // context.setServiceStore(new ServiceStore());
            // ContextProvider.setContext(context);
            // application.display = new Display();
            // application.shell = new Shell(application.display);

        }
        return application;
    }

    public synchronized Resource getResource(String entity) throws IOException {
        URI uri = configurator.createResourceURI(entity);
        if (uri != null) {
            Resource resource = resourceLoader.getResource(resourceSet, uri);
            return resource;
        } else {
            return null;
        }
    }

    public synchronized String put(Object object) {
        String id = objec2idtMap.get(object);
        if (id == null) {
            UUID uid = UUID.randomUUID();
            id = uid.toString();
            id2objectMap.put(id, object);
            objec2idtMap.put(object, id);
        }
        return id;
    }

    public synchronized Object get(String id) {
        return id2objectMap.get(id);
    }

    // public ParsleyWebGuiceModule getModule() {
    // return module;
    // }

    public Injector getInjector() {
        return injector;
    }

    public Configurator getConfigurator() {
        return configurator;
    }

    public synchronized ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public synchronized ResourceSet getResourceSet() {
        return resourceSet;
    }

    // public AdapterFactoryEditingDomain getAdapterFactoryEditingDomain() {
    // return adapterFactoryEditingDomain;
    // }

    public Shell getShell() {
        return shell;
    }

    public Display getDisplay() {
        return display;
    }

    // public void syncExec(Runnable runnable) {
    // runnable.run();
    // }

}
