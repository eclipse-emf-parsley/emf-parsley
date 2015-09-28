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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.google.inject.Injector;

/**
 * Servlet for handling generation of JSON output to represent the detail of a given EMF object
 * 
 * @author Vincenzo Caselli
 * 
 */
public class JsonDetailsServlet extends JsonParsleyServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Injector injector = Application.getInstance(request).getInjector();
        Configurator configurator = Application.getInstance(request).getConfigurator();
        final PrintWriter out = prepareOutput(response);

        String id = request.getParameter(OBJECT_ID_PARAMETER);
        String entity = request.getParameter(SWITCH_PARAMETER);

        if (id != null) {
            out.write("{ ");
            Object element = Application.getInstance(request).get(id);
            EObject eObject = (EObject) element;

            ViewerLabelProvider labelProvider = (ViewerLabelProvider) injector.getInstance(ILabelProvider.class);

            final DialogControlFactory dialogControlFactory = injector.getInstance(DialogControlFactory.class);
            FormFeatureCaptionProvider formFeatureCaptionProvider = (FormFeatureCaptionProvider) injector.getInstance(FormFeatureCaptionProvider.class);
            PolymorphicDispatcher<Object> imageDispatcher = PolymorphicDispatcher.createForSingleTarget("image", 1, 1, labelProvider);

            EClass clazz;
            List<EStructuralFeature> features;
            String imagePath;
            String label;
            if (eObject != null) {
                clazz = eObject.eClass();
                imagePath = (String) imageDispatcher.invoke(element);
                label = labelProvider.getText(eObject);
            } else {
                clazz = configurator.getEClass(entity);
                imagePath = "";
                label = "";
            }
            features = injector.getInstance(FeaturesProvider.class).getFeatures(clazz);
            out.write("\"icon\" : \"" + (imagePath != null ? imagePath : "") + "\" ");
            out.write(", \"label\" : \"" + label + "\" ");

            if (eObject == null) {
                // Case NEW: TODO refactor and avoid repetition of this
                // block in JsonSaveServlet!
                eObject = EcoreUtil.create(clazz);
                Resource resource = Application.getInstance(request).getResource(entity);
                resource.getContents().add(eObject);
                id = Application.getInstance(request).put(eObject);
            }

            final EObject eObject1 = eObject;
            // Application.getInstance(request).getDisplay().syncExec(new Runnable() {
            // @Override
            // public void run() {
            // }
            // });
                    new DefaultRealm();
                    dialogControlFactory.init(null, eObject1, Application.getInstance(request).getShell());

            out.write(", \"" + OBJECT_ID_PARAMETER + "\" : \"" + id + "\" ");
            for (final EStructuralFeature eStructuralFeature : features) {
                out.write(",");
                String featureName = eStructuralFeature.getName(); // not used
                                                                   // since
                                                                   // FeatureCaptionProvider
                                                                   // override
                                                                   // it
                String featureCaption = formFeatureCaptionProvider.getText(clazz, eStructuralFeature);
                out.write("\"" + featureName + "\" : {" + "\"caption\": \"" + featureCaption + "\",");
                // final Wrapper<Control> wrapper = Wrapper.forType(Control.class);

                final String value = labelProvider.getText(eObject.eGet(eStructuralFeature));
                // Application.getInstance(request).getDisplay().syncExec(new Runnable() {
                // @Override
                // public void run() {}
                // });

                        new DefaultRealm();
                        Control control = dialogControlFactory.create(eStructuralFeature);
                        // wrapper.set(control);
                        // System.out.println(control);
                        // Control control = wrapper.get();
                        if (control instanceof Text) {
                            Text text = (Text) control;
                            // value = text.getText();
                            boolean multiLine = (text.getStyle() & SWT.MULTI) > 0;
                            String type = multiLine ? "textarea" : "text";
                            out.write(" \"type\": \"" + type + "\",");
                        } else if (control instanceof Combo) {
                            String proposalsList = "";
                            Combo combo = (Combo) control;
                            String[] proposals = combo.getItems();
                            // value = combo.getText();
                            boolean first = true;
                            for (Object proposal : proposals) {
                                if (!first) {
                                    proposalsList += ",";
                                }
                                proposalsList += "\"" + proposal + "\"";
                                first = false;
                            }
                            out.write(" \"type\": \"combo\",");
                            out.write(" \"proposals\": [" + proposalsList + "],");
                        } else if (control instanceof Button) {
                            Button button = (Button) control;
                            // value = "" + button.getSelection();
                            out.write(" \"type\": \"button\",");
                        }
                        out.write(" \"value\": \"" + (value == null ? "" : value) + "\"" + "} ");

            }
            out.write("} ");
        }

        closeOutput(out);
    }

}
