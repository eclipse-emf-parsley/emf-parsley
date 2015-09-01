/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.labeling

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.emf.parsley.dsl.model.BindingsSpecification
import org.eclipse.emf.parsley.dsl.model.Configurator
import org.eclipse.emf.parsley.dsl.model.DialogControlFactory
import org.eclipse.emf.parsley.dsl.model.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.dsl.model.FeatureCaptionProvider
import org.eclipse.emf.parsley.dsl.model.FeaturesProvider
import org.eclipse.emf.parsley.dsl.model.FormControlFactory
import org.eclipse.emf.parsley.dsl.model.FormFeatureCaptionProvider
import org.eclipse.emf.parsley.dsl.model.LabelProvider
import org.eclipse.emf.parsley.dsl.model.MenuBuilder
import org.eclipse.emf.parsley.dsl.model.PartsSpecifications
import org.eclipse.emf.parsley.dsl.model.ProposalCreator
import org.eclipse.emf.parsley.dsl.model.ResourceManager
import org.eclipse.emf.parsley.dsl.model.TableFeaturesProvider
import org.eclipse.emf.parsley.dsl.model.TableLabelProvider
import org.eclipse.emf.parsley.dsl.model.ViewerContentProvider
import org.eclipse.emf.parsley.dsl.services.EmfParsleyDslGrammarAccess
import org.eclipse.xtext.xbase.ui.labeling.XbaseImages2
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider
import org.eclipse.xtext.common.types.JvmVisibility

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#labelProvider
 */
class EmfParsleyDslLabelProvider extends XbaseLabelProvider {

	@Inject EmfParsleyDslGrammarAccess g

	@Inject XbaseImages2 images

	@Inject
	new(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	def text(BindingsSpecification e) {
		g.bindingsSpecificationAccess.bindingsKeyword_1.value
	}

	def text(LabelProvider e) {
		g.labelProviderAccess.labelProviderKeyword_1.value
	}

	def text(TableLabelProvider e) {
		g.tableLabelProviderAccess.tableLabelProviderKeyword_1.value
	}

	def text(FeatureCaptionProvider e) {
		g.featureCaptionProviderAccess.featureCaptionProviderKeyword_1.value
	}

	def text(FormFeatureCaptionProvider e) {
		g.formFeatureCaptionProviderAccess.formFeatureCaptionProviderKeyword_1.value
	}

	def text(DialogFeatureCaptionProvider e) {
		g.dialogFeatureCaptionProviderAccess.dialogFeatureCaptionProviderKeyword_1.value
	}

	def text(FeaturesProvider e) {
		g.featuresProviderAccess.featuresProviderKeyword_1.value
	}

	def text(TableFeaturesProvider e) {
		g.tableFeaturesProviderAccess.tableFeaturesProviderKeyword_1.value
	}

	def text(FormControlFactory e) {
		g.formControlFactoryAccess.formControlFactoryKeyword_1.value
	}

	def text(DialogControlFactory e) {
		g.dialogControlFactoryAccess.dialogControlFactoryKeyword_1.value
	}

	def text(ProposalCreator e) {
		g.proposalCreatorAccess.proposalsKeyword_1.value
	}

	def text(MenuBuilder e) {
		g.menuBuilderAccess.menuBuilderKeyword_1.value
	}

	def text(Configurator e) {
		g.configuratorAccess.configuratorKeyword_1.value
	}

	def text(ViewerContentProvider e) {
		g.viewerContentProviderAccess.viewerContentProviderKeyword_1.value
	}

	def text(PartsSpecifications e) {
		g.partsSpecificationsAccess.partsKeyword_1.value
	}

	def text(ResourceManager e) {
		g.resourceManagerAccess.resourceManagerKeyword_1.value
	}

	def image(EObject e) {
		// all the elements we show in the outline will correspond
		// to classes in the generated code
		images.forClass(JvmVisibility.PUBLIC, 0)
	}
}
