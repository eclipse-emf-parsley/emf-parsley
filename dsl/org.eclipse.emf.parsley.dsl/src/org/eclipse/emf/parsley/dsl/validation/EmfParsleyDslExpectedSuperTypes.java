/**
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.validation;

import com.google.inject.Singleton;
import java.util.HashMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.TableViewerContentProvider;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.ui.IViewPart;

/**
 * Utility class that associates to each DSL element that can 'extends' the
 * expected supertype.
 * 
 * IMPORTANT: the expected supertypes must be consistent with the default
 * classes of the guice module. For example,
 * 
 * <pre>
 * public Class&lt;? extends ILabelProvider&gt; bindILabelProvider() {
 * 	return ViewerLabelProvider.class;
 * }
 * </pre>
 * 
 * The expected supertype for the DSL LabelProvider EClass must be
 * ViewerLabelProvider NOT ILabelProvider because our model inferrer is based on
 * that assumption (not to mention that we need classes not interfaces as
 * supertypes).
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
@Singleton
public class EmfParsleyDslExpectedSuperTypes {
	private final HashMap<EClass, Class<?>> expected = new HashMap<>();

	public EmfParsleyDslExpectedSuperTypes() {
		expected.put(ModelPackage.Literals.MODULE, EmfParsleyJavaGuiceModule.class);
		expected.put(ModelPackage.Literals.VIEW_SPECIFICATION, IViewPart.class);
		expected.put(ModelPackage.Literals.FEATURE_SPECIFICATION, EObject.class);
		expected.put(ModelPackage.Literals.FEATURE_ASSOCIATED_EXPRESSION, EObject.class);
		expected.put(ModelPackage.Literals.CONTROL_FACTORY_SPECIFICATION, EObject.class);
		expected.put(ModelPackage.Literals.LABEL_PROVIDER, ViewerLabelProvider.class);
		expected.put(ModelPackage.Literals.TABLE_LABEL_PROVIDER, TableColumnLabelProvider.class);
		expected.put(ModelPackage.Literals.FEATURE_CAPTION_PROVIDER, FeatureCaptionProvider.class);
		expected.put(ModelPackage.Literals.FORM_FEATURE_CAPTION_PROVIDER, FormFeatureCaptionProvider.class);
		expected.put(ModelPackage.Literals.DIALOG_FEATURE_CAPTION_PROVIDER, DialogFeatureCaptionProvider.class);
		expected.put(ModelPackage.Literals.FEATURES_PROVIDER, FeaturesProvider.class);
		expected.put(ModelPackage.Literals.TABLE_FEATURES_PROVIDER, TableFeaturesProvider.class);
		expected.put(ModelPackage.Literals.FORM_CONTROL_FACTORY, FormControlFactory.class);
		expected.put(ModelPackage.Literals.DIALOG_CONTROL_FACTORY, DialogControlFactory.class);
		expected.put(ModelPackage.Literals.PROPOSAL_CREATOR, ProposalCreator.class);
		expected.put(ModelPackage.Literals.MENU_BUILDER, EditingMenuBuilder.class);
		expected.put(ModelPackage.Literals.CONFIGURATOR, Configurator.class);
		expected.put(ModelPackage.Literals.RESOURCE_MANAGER, ResourceManager.class);
		expected.put(ModelPackage.Literals.VIEWER_CONTENT_PROVIDER, ViewerContentProvider.class);
		expected.put(ModelPackage.Literals.TABLE_VIEWER_CONTENT_PROVIDER, TableViewerContentProvider.class);
	}

	public Class<?> getExpectedSupertype(final EObject element) {
		return expected.get(element.eClass());
	}

	public Class<?> getExpectedSupertype(final EClass eClass) {
		return expected.get(eClass);
	}
}
