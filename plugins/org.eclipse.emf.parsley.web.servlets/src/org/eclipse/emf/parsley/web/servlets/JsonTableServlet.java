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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;

import com.google.common.collect.Lists;
import com.google.inject.Injector;



/**
 * Servlet for handling generation of JSON output to represent a list of EMF objects
 * 
 * @author Vincenzo Caselli
 * 
 */
public class JsonTableServlet extends JsonParsleyServlet {
    private static final long serialVersionUID = 1L;


    @SuppressWarnings("unchecked")
    public static Collection<Object> ensureCollection(Object contents) {
        if (contents == null) {
            return Collections.emptyList();
        }
        if (contents instanceof Collection<?>) {
            return (Collection<Object>) contents;
        } else if (contents instanceof Iterable<?>) {
            return Lists.newArrayList((Iterable<?>) contents);
        } else if (contents instanceof Iterator<?>) {
            return Lists.newArrayList((Iterator<?>) contents);
        } else if (contents.getClass().isArray()) {
            return Arrays.asList((Object[]) contents);
        } else {
            return Collections.singleton(contents);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // CountervisionPackage pkg = CountervisionPackage.eINSTANCE; //Needed
        // to avoid "Generated packages locally not available: CDOPackageUnit",
        // see
        // https://www.eclipse.org/forums/index.php?t=msg&th=203918&goto=652285&#msg_652285
        // LibraryPackage lp = LibraryPackage.eINSTANCE; //Needed to avoid
        // "Generated packages locally not available: CDOPackageUnit", see
        // https://www.eclipse.org/forums/index.php?t=msg&th=203918&goto=652285&#msg_652285

        Injector injector = Application.getInstance(request).getInjector();
        Configurator configurator = Application.getInstance(request).getConfigurator();

        PrintWriter out = prepareOutput(response);
        out.write("[");

        String entity = request.getParameter(SWITCH_PARAMETER);

        Resource resource = Application.getInstance(request).getResource(entity);

        FeatureCaptionProvider featureCaptionProvider = injector.getInstance(FeatureCaptionProvider.class);

        EClass clazz = configurator.getEClass(entity);

        /*
         * This should be done by Parsley in CDOResourceLoader.getResource(ResourceSet resourceSet,
         * URI resourceURI) Now it is only done in
         * CDOResourceLoader.getResource(AdapterFactoryEditingDomain editingDomain, URI resourceURI)
         * when resource is null (but not when it's size == 0)
         */
        // if (resource == null || resource.getContents().size() == 0) {
        // CustomEmptyResourceInitializer emptyResourceInitializer =
        // injector.getInstance(CustomEmptyResourceInitializer.class);
        // // resource = ((CDOResourceLoader)
        // // resourceLoader).createResource(resource.getResourceSet(),
        // uri);
        // emptyResourceInitializer.initialize(resource);
        // try {
        // ((CDOTransaction) ((CDOResource) resource).cdoView()).commit();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // emptyResourceInitializer.initialize(resource);
        // }
        // *************************************************************************************************/

        ViewerContentProvider contentProvider = (ViewerContentProvider) injector.getInstance(IContentProvider.class);
        ViewerLabelProvider labelProvider = (ViewerLabelProvider) injector.getInstance(ILabelProvider.class);

        Collection<Object> content = null;
        Object contents = null;
        if (resource != null) {
            contents = resource.getContents();
        } else {
            contents = contentProvider.getElements(entity);
        }
        content = ensureCollection(contents);
        // System.out.println(contents);
        // if (contents instanceof EObjectContainmentEList<?>) {
        // // Contents is a collection
        // content = (EObjectContainmentEList<Object>) contents;
        // } else if (contents instanceof EStoreEObjectImpl.BasicEStoreEList<?>) {
        // // Contents is a collection
        // content = (EStoreEObjectImpl.BasicEStoreEList<Object>) contents;
        // } else if (contents instanceof HibernatePersistableEList<?>) {
        // content = (HibernatePersistableEList<Object>) contents;
        // } else if (contents instanceof EList) {
        // content = (EList) contents;
        // } else {
        // // Contents is an array of objects
        // Object[] objectList = contentProvider.getElements(configurator.getContents(entity,
        // resource));
        // content = Arrays.asList(objectList);
        // }

        boolean first = true;
        for (Object object : content) {
            EObject eObject = (EObject) object;
            if (eObject.eClass().equals(clazz)) {
                // System.out.println(eObject);
                String id = Application.getInstance(request).put(eObject);
                if (!first) {
                    out.write(",");
                }
                List<EStructuralFeature> features = injector.getInstance(TableFeaturesProvider.class).getFeatures(eObject.eClass());
                String outString = "{\"$" + OBJECT_ID_PARAMETER + "\" : \"" + id + "\", ";
                for (EStructuralFeature eStructuralFeature : features) {
                    // String featureName = eStructuralFeature.getName(); //not
                    // used
                    // since FeatureCaptionProvider override it
                    String featureCaption = featureCaptionProvider.getText(clazz, eStructuralFeature);
                    // String featureValue = "" +
                    // eObject.eGet(eStructuralFeature);
                    outString += "\"" + featureCaption + "\": \"" + labelProvider.getText(eObject.eGet(eStructuralFeature)) + "\",";
                }
                outString = outString.substring(0, outString.length() - 1);
                outString += "}";
                out.write(outString);
                first = false;
            }
        }

        out.write("]");
        closeOutput(out);
    }

}
