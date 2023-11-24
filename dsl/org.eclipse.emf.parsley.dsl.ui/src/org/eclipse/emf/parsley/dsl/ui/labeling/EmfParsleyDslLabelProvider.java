/**
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.ui.labeling;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.dsl.model.BindingsSpecification;
import org.eclipse.emf.parsley.dsl.model.Configurator;
import org.eclipse.emf.parsley.dsl.model.DialogControlFactory;
import org.eclipse.emf.parsley.dsl.model.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.dsl.model.FeatureCaptionProvider;
import org.eclipse.emf.parsley.dsl.model.FeaturesProvider;
import org.eclipse.emf.parsley.dsl.model.FormControlFactory;
import org.eclipse.emf.parsley.dsl.model.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.dsl.model.LabelProvider;
import org.eclipse.emf.parsley.dsl.model.MenuBuilder;
import org.eclipse.emf.parsley.dsl.model.PartsSpecifications;
import org.eclipse.emf.parsley.dsl.model.ProposalCreator;
import org.eclipse.emf.parsley.dsl.model.ResourceManager;
import org.eclipse.emf.parsley.dsl.model.TableFeaturesProvider;
import org.eclipse.emf.parsley.dsl.model.TableLabelProvider;
import org.eclipse.emf.parsley.dsl.model.TableViewerContentProvider;
import org.eclipse.emf.parsley.dsl.model.ViewerContentProvider;
import org.eclipse.emf.parsley.dsl.services.EmfParsleyDslGrammarAccess;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.xbase.ui.labeling.XbaseImages2;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#labelProvider
 */
public class EmfParsleyDslLabelProvider extends XbaseLabelProvider {
	@Inject
	private EmfParsleyDslGrammarAccess g;

	@Inject
	private XbaseImages2 images;

	@Inject
	public EmfParsleyDslLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(BindingsSpecification e) {
		return g.getBindingsSpecificationAccess().getBindingsKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(LabelProvider e) {
		return g.getLabelProviderAccess().getLabelProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(TableLabelProvider e) {
		return g.getTableLabelProviderAccess().getTableLabelProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(FeatureCaptionProvider e) {
		return g.getFeatureCaptionProviderAccess().getFeatureCaptionProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(FormFeatureCaptionProvider e) {
		return g.getFormFeatureCaptionProviderAccess().getFormFeatureCaptionProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(DialogFeatureCaptionProvider e) {
		return g.getDialogFeatureCaptionProviderAccess().getDialogFeatureCaptionProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(FeaturesProvider e) {
		return g.getFeaturesProviderAccess().getFeaturesProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(TableFeaturesProvider e) {
		return g.getTableFeaturesProviderAccess().getTableFeaturesProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(FormControlFactory e) {
		return g.getFormControlFactoryAccess().getFormControlFactoryKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(DialogControlFactory e) {
		return g.getDialogControlFactoryAccess().getDialogControlFactoryKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(ProposalCreator e) {
		return g.getProposalCreatorAccess().getProposalsKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(MenuBuilder e) {
		return g.getMenuBuilderAccess().getMenuBuilderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(Configurator e) {
		return g.getConfiguratorAccess().getConfiguratorKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(ViewerContentProvider e) {
		return g.getViewerContentProviderAccess().getViewerContentProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(TableViewerContentProvider e) {
		return g.getTableViewerContentProviderAccess().getTableViewerContentProviderKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(PartsSpecifications e) {
		return g.getPartsSpecificationsAccess().getPartsKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public String text(ResourceManager e) {
		return g.getResourceManagerAccess().getResourceManagerKeyword_1().getValue();
	}

	/**
	 * @param e the object for which we return the label
	 * @return
	 */
	public ImageDescriptor image(EObject e) {
		// all the elements we show in the outline will correspond
		// to classes in the generated code
		return images.forClass(JvmVisibility.PUBLIC, 0);
	}
}
