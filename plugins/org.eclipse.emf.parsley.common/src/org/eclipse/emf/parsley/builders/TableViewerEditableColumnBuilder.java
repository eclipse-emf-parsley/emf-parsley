/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.builders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;


public class TableViewerEditableColumnBuilder extends TableViewerColumnBuilder {

	
	@Override
	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer,
			TableLayout layout, EStructuralFeature eStructuralFeature,
			IStructuredContentProvider contentProvider, int weight) {
		TableViewerColumn viewerColumn= super.buildTableViewerColumn(tableViewer, layout, eStructuralFeature,
				contentProvider, weight);

		if(eStructuralFeature instanceof EAttribute){
			if(isPredefinedValueEditing((EAttribute)eStructuralFeature)){
				viewerColumn.setEditingSupport(new ComboEditingSupport(tableViewer, eStructuralFeature));	
			}else{
				viewerColumn.setEditingSupport(new TableEditingSupport(tableViewer, eStructuralFeature));
			}
		}
		return viewerColumn;
	}
	
	class TableEditingSupport extends EditingSupport {

		private ColumnViewer viewer;
		private EStructuralFeature eStructuralFeature;

		public TableEditingSupport(ColumnViewer viewer, EStructuralFeature eStructuralFeature) {
			super(viewer);
			this.viewer=viewer;
			this.eStructuralFeature=eStructuralFeature;
		}
		
		@Override
		protected CellEditor getCellEditor(Object element) {
			if (BigDecimal.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new TextCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue("" + value);
					}
					
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new BigDecimal(stringValue);
					}
				};
			}
			if (BigInteger.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new TextCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue("" + value);
					}
					
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new BigInteger(stringValue);
					}
				};
			}
			if (double.class.equals(eStructuralFeature.getEType().getInstanceClass()) || Double.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new TextCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue("" + value);
					}
					
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new Double(stringValue);
					}
				};
			}
			if (int.class.equals(eStructuralFeature.getEType().getInstanceClass()) || Integer.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new TextCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue("" + value);
					}
					
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new Integer(stringValue);
					}
				};
			}
			if (float.class.equals(eStructuralFeature.getEType().getInstanceClass()) || Float.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new TextCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue("" + value);
					}
					
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new Float(stringValue);
					}
				};
			}
			if (short.class.equals(eStructuralFeature.getEType().getInstanceClass()) || Short.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new TextCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue("" + value);
					}
					
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new Short(stringValue);
					}
				};
			}
			if (byte.class.equals(eStructuralFeature.getEType().getInstanceClass()) || Byte.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new TextCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue("" + value);
					}
					
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new Byte(stringValue);
					}
				};
			}
			if (boolean.class.equals(eStructuralFeature.getEType().getInstanceClass()) || Boolean.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new CheckboxCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue(value);
					}
					
					@Override
					protected Object doGetValue() {
						Boolean boolValue = (Boolean) super.doGetValue();
						return boolValue;
					}
				};
			}
			if (Date.class.equals(eStructuralFeature.getEType().getInstanceClass())) {
				return new TextCellEditor((Composite) getViewer().getControl()) {
					@Override
					protected void doSetValue(Object value) {
						super.doSetValue(value);
					}
					
					@Override
					protected Object doGetValue() {
						String date = (String) super.doGetValue();
						return date;
					}
				};
			}
			return new TextCellEditor((Composite)getViewer().getControl());	
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			EObject eObjiect=(EObject) element;
			return  eObjiect.eGet(eStructuralFeature)!=null?eObjiect.eGet(eStructuralFeature):"";
		}

		@Override
		protected void setValue(Object element, Object value) {
			if(element instanceof EObject){
				EObject eObject = (EObject) element;
				if(eObject.eGet(eStructuralFeature)!=null && !eObject.eGet(eStructuralFeature).equals(value)){
					EditingDomain domain=AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
					Command setCommand=new SetCommand(domain,eObject,eStructuralFeature,value);
					domain.getCommandStack().execute(setCommand);
					viewer.refresh();
				}
			}
		}
	}

	class ComboEditingSupport extends EditingSupport {

		private ColumnViewer viewer;
		private EStructuralFeature feature;
		private List<EEnumLiteral> literals;

		public ComboEditingSupport(ColumnViewer viewer, EStructuralFeature eStructuralFeature) {
			super(viewer);
			this.viewer=viewer;
			this.feature=eStructuralFeature;
		}
		
		@Override
		protected CellEditor getCellEditor(Object element) {
			
			EEnum eEnum=(EEnum) ((EAttribute)feature).getEAttributeType();
			literals=eEnum.getELiterals();
			String[] descriptions = new String[literals.size()];
			int i=0;
			for (EEnumLiteral literal: literals) {
				descriptions[i++]=literal.getName();
			}
			return new ComboBoxCellEditor((Composite)getViewer().getControl(), descriptions);
			
		}

		

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			if(element!=null && element instanceof EObject){
				EObject eObject=(EObject) element;
				if(eObject.eGet(feature) instanceof Enumerator){
					Enumerator typeEnum=(Enumerator) eObject.eGet(feature);
					for (int i = 0; i < literals.size(); i++) {
						EEnumLiteral literal= literals.get(i);
						if(literal!=null && literal.getLiteral().equals(typeEnum.getLiteral())){
							return i;
						}
					}
				}
			}
			return  -1;
		}

		@Override
		protected void setValue(Object element, Object value) {
			Integer i=(Integer)value;
			EObject eObject=(EObject) element;
			if(eObject.eGet(feature) instanceof Enumerator && i>=0 && i<literals.size()){
				Enumerator typeEnum=(Enumerator) eObject.eGet(feature);
				if(typeEnum!=null && !typeEnum.getLiteral().equals(literals.get(i))){
					EditingDomain domain=AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
					Command setCommand=new SetCommand(domain,eObject,feature,literals.get(i).getInstance());
					domain.getCommandStack().execute(setCommand);
					viewer.refresh();
				}
			}
		}
	}
	
	private boolean isPredefinedValueEditing(EAttribute eStructuralFeature) {
		return ((EAttribute)eStructuralFeature).getEAttributeType() instanceof EEnum;
	}

}
