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
package org.eclipse.emf.parsley.web.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.ServerPort;
import org.osgi.framework.Bundle;

/**
* Utility class for the wizard
* 
* @author Vincenzo Caselli
* 
*/
public final class Utils {

    public static IFolder getFolder(final IProject pj, String folderPath) {
        final IVirtualComponent vc = ComponentCore.createComponent(pj);
        final IVirtualFolder vf = vc.getRootFolder().getFolder(folderPath);
        return (IFolder) vf.getUnderlyingFolder();
    }

    public static String getLocalAddress() {
        try {
            Enumeration<NetworkInterface> net = NetworkInterface.getNetworkInterfaces();
            while (net.hasMoreElements()) {
                for (InterfaceAddress ia : net.nextElement().getInterfaceAddresses()) {
                    if (ia.getAddress().isSiteLocalAddress())
                        return ia.getAddress().getHostAddress();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDefaultServerHttpPort() {
        IServer[] servers = ServerCore.getServers();
        for (IServer iServer : servers) {
            ServerPort[] ports = iServer.getServerPorts(null);
            for (ServerPort serverPort : ports) {
                if ("HTTP".equals(serverPort.getProtocol())) {
                    return serverPort.getPort();
                }
            }
        }
        return 8080;
    }

    public static void copyFile(final IProject iProject, IProgressMonitor monitor, String originFilePath, IFile iFile, Properties replaceStrings) throws CoreException {
        try {
            final InputStream in = FileLocator.openStream(Activator.getDefault().getBundle(), new Path(originFilePath), false);
            BufferedReader rdr = new BufferedReader(new InputStreamReader(in));
            StringBuffer out = new StringBuffer();
            String line = null;
            while ((line = rdr.readLine()) != null) {
                Set<String> originStringSet = replaceStrings.stringPropertyNames();
                for (String originString : originStringSet) {
                    String targetString = replaceStrings.getProperty(originString);
                    line = line.replaceAll(originString, targetString);
                }
                out.append(line + System.getProperty("line.separator"));
            }
            InputStream in2 = new ByteArrayInputStream(out.toString().getBytes("UTF-8"));
            iFile.create(in2, true, null);
        } catch (IOException e) {
            throw new CoreException(Activator.createErrorStatus(e.getMessage(), e));
        }
    }

    public static void copyFromPluginToWorkspace(final Bundle bundle, final IPath src, final IFile dest) throws CoreException {
        try {
            final InputStream in = FileLocator.openStream(bundle, src, false);
            dest.create(in, true, null);
        } catch (IOException e) {
            throw new CoreException(Activator.createErrorStatus(e.getMessage(), e));
        }
    }

    public static void copyFileToWorkspace(final File file, final IFile dest) throws CoreException {
        try {
            final InputStream in = new FileInputStream(file);
            dest.create(in, true, null);
        } catch (IOException e) {
            throw new CoreException(Activator.createErrorStatus(e.getMessage(), e));
        }
    }

    public static void registerServlet(final IProject pj, WebApp webAppRoot, final String servletName, final String servletClassName, final String urlPattern) {
        final Servlet servlet = WebapplicationFactory.eINSTANCE.createServlet();
        final ServletType servletType = WebapplicationFactory.eINSTANCE.createServletType();
        servletType.setClassName(servletClassName);
        servlet.setWebType(servletType);
        servlet.setServletName(servletName);
        webAppRoot.getServlets().add(servlet);

        final ServletMapping mapping = WebapplicationFactory.eINSTANCE.createServletMapping();
        mapping.setServlet(servlet);
        mapping.setUrlPattern(urlPattern);
        webAppRoot.getServletMappings().add(mapping);
    }

    public static void registerFilter(IProject iProject, WebApp webAppRoot, String filterName, String filterClassName, String urlPattern) {
        Filter filter = WebapplicationFactory.eINSTANCE.createFilter();
        filter.setName(filterName);
        filter.setFilterClassName(filterClassName);
        webAppRoot.getFilters().add(filter);

        FilterMapping filterMapping = WebapplicationFactory.eINSTANCE.createFilterMapping();
        filterMapping.setFilter(filter);
        filterMapping.setUrlPattern(urlPattern);
        webAppRoot.getFilterMappings().add(filterMapping);
    }

}
