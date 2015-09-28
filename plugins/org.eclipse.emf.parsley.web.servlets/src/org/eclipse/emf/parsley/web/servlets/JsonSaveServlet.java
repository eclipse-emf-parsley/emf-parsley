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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.emf.parsley.util.FeatureHelper;
import org.eclipse.jface.viewers.ILabelProvider;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.inject.Injector;

/**
 * Servlet for handling the submission of a JSON object that will be re-mapped into an EMF object and saved into the EMF Resource when available
 * 
 * @author Vincenzo Caselli
 * 
 */
public class JsonSaveServlet extends JsonParsleyServlet {
    private static final long serialVersionUID = 1L;

    protected void doPut(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Injector injector = Application.getInstance(request).getInjector();
        Configurator configurator = Application.getInstance(request).getConfigurator();
        final DialogControlFactory dialogControlFactory = injector.getInstance(DialogControlFactory.class);
        final FeatureHelper featureHelper = injector.getInstance(FeatureHelper.class);

        PrintWriter out = prepareOutput(response);
        // Read from request
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println(data);
        final String entity = request.getParameter(SWITCH_PARAMETER);

        try {
            final JSONObject jsonEntity = (JSONObject) new JSONParser().parse(data);
            String id = jsonEntity.get(OBJECT_ID_PARAMETER).toString();
            System.out.println(id);
            EObject eObject = (EObject) Application.getInstance(request).get(id);
            System.out.println(eObject);

            final ViewerLabelProvider labelProvider = (ViewerLabelProvider) injector.getInstance(ILabelProvider.class);

            if (eObject == null) {
                EClass clazz = configurator.getEClass(entity);
                eObject = EcoreUtil.create(clazz);
            }

            final EObject eObject1 = eObject;
            // Application.getInstance(request).getDisplay().syncExec(new Runnable() {
            // @Override
            // public void run() {
            // new DefaultRealm();
            // dialogControlFactory.init(null, eObject1,
            // Application.getInstance(request).getShell());
            // List<EStructuralFeature> features =
            // injector.getInstance(FeaturesProvider.class).getEObjectFeatures(eObject1);
            // for (final EStructuralFeature eStructuralFeature : features) {
            // JSONObject featureObject = (JSONObject) jsonEntity.get(eStructuralFeature.getName());
            // System.out.println(featureObject);
            // final String newValue = featureObject.get("value").toString();
            // System.out.println(newValue);
            // if (eStructuralFeature instanceof EAttribute) {
            // if (featureHelper.isBooleanFeature(eStructuralFeature)) {
            // eObject1.eSet(eStructuralFeature, newValue.equals("true"));
            // } else {
            // eObject1.eSet(eStructuralFeature, newValue);
            // }
            // } else if (eStructuralFeature instanceof EReference) {
            // Control control = dialogControlFactory.create(eStructuralFeature);
            // List<Object> proposalObjects =
            // dialogControlFactory.getProposalCreator().proposals(eObject1, eStructuralFeature);
            // if (control instanceof Combo) {
            // // Combo combo = (Combo) control;
            // // String[] proposals = combo.getItems();
            // for (Object proposal : proposalObjects) {
            // if (labelProvider.getText(proposal).equals(newValue)) {
            // eObject1.eSet(eStructuralFeature, proposal);
            // break;
            // }
            // }
            // }
            // }
            // }
            //
            // try {
            // Resource resource = Application.getInstance(request).getResource(entity);
            // resource.save(null);
            // } catch (IOException e) {
            // e.printStackTrace();
            // }
            // }
            // });


        } catch (Exception e) {
            e.printStackTrace();
        }

        closeOutput(out);
    }
}
