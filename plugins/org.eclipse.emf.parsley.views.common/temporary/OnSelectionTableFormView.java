/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.TableFormFactory;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.composite.TableFormComposite;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * @author Francesco Guidieri - Initial contribution and API
 *
 */
public class OnSelectionTableFormView extends AbstractOnSelectionView {

	@Inject
	protected TableFormFactory tableFormFactory;
	
	@Inject
	protected ILabelProvider labelProvider;
	
	@Inject
	protected FeaturesProvider featuresProvider;

	protected TableFormComposite tableFormDetailComposite;

	private Label featureSelectionlabel;

	private Composite featureSelectionContainer;

	private EObject currentEObject;

	private ComboViewer featuresViewer;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout());
		createFeatureSelectionPart(parent);
		super.createPartControl(parent);
		tableFormDetailComposite = tableFormFactory.createTableFormMasterDetailComposite(parent,
				SWT.BORDER);
		tableFormDetailComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void createFeatureSelectionPart(Composite parent) {
		featureSelectionContainer=new Composite(parent, SWT.NONE);
		featureSelectionContainer.setLayout(new GridLayout(2,false));
		featureSelectionContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		featureSelectionlabel=new Label(featureSelectionContainer,SWT.NONE); 
		featuresViewer=new ComboViewer(featureSelectionContainer);
		featuresViewer.setLabelProvider(labelProvider);
		featuresViewer.setContentProvider(new ArrayContentProvider());
		featuresViewer.getControl().setLayoutData(new GridData(300,-1));
		featuresViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			public void selectionChanged(SelectionChangedEvent event) {
				if(!event.getSelection().isEmpty() && ((IStructuredSelection)event.getSelection()).getFirstElement()instanceof EStructuralFeature){
					EStructuralFeature feature=(EStructuralFeature) ((IStructuredSelection)event.getSelection()).getFirstElement();
					List<?> elements= (List<?>) currentEObject.eGet(feature);
					if(elements.size()>0){
						tableFormDetailComposite.update(elements);						
					}else{
						tableFormDetailComposite.buildTable((EClass)feature.getEType());
					}
				}else{
					tableFormDetailComposite.update(null);
				}
			}
		});
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection) {
		if(getFirstSelectedElement(selection)instanceof EObject){
			currentEObject=(EObject) getFirstSelectedElement(selection);
			List<EStructuralFeature> allowedFeatures = selectNotEmptyReferences(extractAllowedReferences(),currentEObject);
			featuresViewer.setInput(allowedFeatures);
			if(allowedFeatures.size()>1){
				featureSelectionlabel.setText("Select a feature for object "+labelProvider.getText(currentEObject));
				featureSelectionContainer.setVisible(true);
				featureSelectionContainer.layout(true,true);
			}else{
				featureSelectionlabel.setText("");
				featureSelectionContainer.setVisible(false);
				featureSelectionContainer.layout(true,true);
			}
			
		}else{
			currentEObject=null;
			featureSelectionlabel.setText("");
			featureSelectionContainer.setVisible(false);
			featureSelectionContainer.layout(true,true);
		}
	}

	private List<EStructuralFeature> selectNotEmptyReferences(List<EStructuralFeature> references,
			EObject eobj) {
		List<EStructuralFeature> notEmptyReferences=new ArrayList<EStructuralFeature>();
		for (EStructuralFeature eStructuralFeature : references) {
			if(((List<?>) eobj.eGet(eStructuralFeature)).size()>0){
				notEmptyReferences.add(eStructuralFeature);
			}
		}
		return notEmptyReferences;
	}

	private List<EStructuralFeature> extractAllowedReferences() {
		List<EStructuralFeature> allowedFeatures= featuresProvider.getFeatures(currentEObject);
		List<EStructuralFeature> contaimnedReferences=new ArrayList<EStructuralFeature>();
		for (EStructuralFeature eStructuralFeature : allowedFeatures) {
			if(eStructuralFeature instanceof EReference && ((EReference)eStructuralFeature).isContainment()){
				contaimnedReferences.add(eStructuralFeature);
			}
		}
		return contaimnedReferences;
	}


	@Override
	public void setFocus() {
		tableFormDetailComposite.setFocus();
	}

}
