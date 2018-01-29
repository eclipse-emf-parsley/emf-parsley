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
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.IViewPart;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.inject.Injector;

/**
 * Servlet for handling generation of JSON output to represent a list of EMF
 * objects
 * 
 * @author Vincenzo Caselli
 * 
 */
public class JsonTableServlet extends JsonParsleyServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Injector injector = Application.getInstance(request).getInjector();
		Configurator configurator = Application.getInstance(request).getConfigurator();

		PrintWriter out = prepareOutput(response);
		out.write("[");

		String s = request.getParameter(SWITCH_PARAMETER);
		System.out.println(s);

		IViewPart viewpartClass;
		try {
			String partQN = getPartQN(s);
			viewpartClass = (IViewPart) Class.forName(partQN).newInstance();

			ResourceLoader loader = injector.getInstance(ResourceLoader.class);

			URI uri = configurator.createResourceURI(viewpartClass);
			AdapterFactoryEditingDomain ed = injector.getProvider(AdapterFactoryEditingDomain.class).get();
			// ResourceSet resourceSet = ed.getResourceSet();
			// resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
			// new XMIResourceFactoryImpl());
			// // resourceSet.getPackageRegistry().put(ProductsPackage.eNS_URI,
			// ProductsPackage.eINSTANCE);
			// resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("dummy",
			// new XMIResourceFactoryImpl());
			// Resource resource = loader.getResource(resourceSet, uri);
			Resource resource = loader.getResource(ed, uri).getResource();

			ResourceLoader resourceLoader = Application.getInstance(request).getResourceLoader();
			System.out.println(resourceLoader);
			Resource resource1 = resourceLoader.getResource(ed, uri).getResource();
			System.out.println(resource1);

			FeatureCaptionProvider featureCaptionProvider = injector.getInstance(FeatureCaptionProvider.class);

			EClass clazz = configurator.getEClass(viewpartClass);

			ViewerLabelProvider labelProvider = (ViewerLabelProvider) injector.getInstance(ILabelProvider.class);
			TableViewerContentProvider tvcp = injector.getInstance(ViewerFactory.class)
					.createTableViewerContentProvider(clazz);

			Object[] contents = tvcp.getElements(resource);

			boolean first = true;
			for (Object object : contents) {
				EObject eObject = (EObject) object;
				if (eObject.eClass().equals(clazz)) {
					String id = Application.getInstance(request).put(eObject);
					if (!first) {
						out.write(",");
					}
					List<EStructuralFeature> features = injector.getInstance(TableFeaturesProvider.class)
							.getFeatures(eObject.eClass());
					String outString = "{\"$" + OBJECT_ID_PARAMETER + "\" : \"" + id + "\", ";
					for (EStructuralFeature eStructuralFeature : features) {
						String featureCaption = featureCaptionProvider.getText(clazz, eStructuralFeature);
						outString += "\"" + featureCaption + "\": \""
								+ labelProvider.getText(eObject.eGet(eStructuralFeature)) + "\",";
					}
					outString = outString.substring(0, outString.length() - 1);
					outString += "}";
					out.write(outString);
					first = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Cannot find viewPart with id=\"" + s+ "\" in plugin.xml file.");
		}

		out.write("]");
		closeOutput(out);
	}

	private String getPartQN(String viewId) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// NOTE: plugin.xml must be on the classpath, e.g. into /src folder
		Document doc = db.parse(getClass().getResourceAsStream("/plugin.xml"));
		NodeList viewNodes = doc.getElementsByTagName("view");
		for (int i = 0; i < viewNodes.getLength(); i++) {
			Node viewNode = viewNodes.item(i);
			NamedNodeMap attrs = viewNode.getAttributes();
			if (viewId.equals(attrs.getNamedItem("id").getNodeValue())) {
				return attrs.getNamedItem("class").getNodeValue().split(":")[1];
			}
		}
		return null;
	}

}
