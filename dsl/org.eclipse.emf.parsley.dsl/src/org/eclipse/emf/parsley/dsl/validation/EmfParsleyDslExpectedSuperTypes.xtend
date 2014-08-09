/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.validation

import java.util.HashMap
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.binding.DialogControlFactory
import org.eclipse.emf.parsley.binding.FormControlFactory
import org.eclipse.emf.parsley.binding.ProposalCreator
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.ui.IViewPart

import static org.eclipse.emf.parsley.dsl.model.ModelPackage.Literals.*
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.factories.TreeFormFactory
import com.google.inject.Singleton

/**
 * Utility class that associates to each DSL element that can 'extends'
 * the expected supertype.
 * 
 * IMPORTANT: the expected supertypes must be consistent with the default classes of the
 * guice module.  For example, 
 * 
 * <pre>
 * 	public Class&lt;? extends ILabelProvider&gt; bindILabelProvider() {
 * 		return ViewerLabelProvider.class;
 * 	}
 * </pre>
 * 
 * The expected supertype for the DSL LabelProvider EClass must be
 * ViewerLabelProvider NOT ILabelProvider
 * because our model inferrer is based on that assumption
 * (not to mention that we need classes not interfaces as supertypes).
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
@Singleton
class EmfParsleyDslExpectedSuperTypes {

	val expected = new HashMap<EClass, Class<?>>

	new() {
		expected.put(MODULE, EmfParsleyGuiceModule)
		expected.put(VIEW_SPECIFICATION, IViewPart)
		expected.put(FEATURE_SPECIFICATION, EObject)
		expected.put(FEATURE_ASSOCIATED_EXPRESSION, EObject)
		expected.put(CONTROL_FACTORY_SPECIFICATION, EObject)
		expected.put(LABEL_PROVIDER, ViewerLabelProvider)
		expected.put(TABLE_LABEL_PROVIDER, TableColumnLabelProvider)
		expected.put(FEATURE_CAPTION_PROVIDER, FeatureCaptionProvider)
		expected.put(FORM_FEATURE_CAPTION_PROVIDER, FormFeatureCaptionProvider)
		expected.put(DIALOG_FEATURE_CAPTION_PROVIDER, DialogFeatureCaptionProvider)
		expected.put(FEATURES_PROVIDER, FeaturesProvider)
		expected.put(TABLE_FEATURES_PROVIDER, TableFeaturesProvider)
		expected.put(FORM_CONTROL_FACTORY, FormControlFactory)
		expected.put(DIALOG_CONTROL_FACTORY, DialogControlFactory)
		expected.put(PROPOSAL_CREATOR, ProposalCreator)
		expected.put(VIEWER_CONTENT_PROVIDER, ViewerContentProvider)
		expected.put(TREE_FORM_FACTORY, TreeFormFactory)
	}

	def getExpectedSupertype(EObject element) {
		return expected.get(element.eClass)
	}

	def getExpectedSupertype(EClass eClass) {
		return expected.get(eClass)
	}
}
