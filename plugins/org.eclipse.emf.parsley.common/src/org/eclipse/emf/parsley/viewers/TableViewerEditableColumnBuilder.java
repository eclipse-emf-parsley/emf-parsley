/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 * Lorenzo Bettini - https://bugs.eclipse.org/bugs/show_bug.cgi?id=479683
 *******************************************************************************/
package org.eclipse.emf.parsley.viewers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

import com.google.inject.Inject;


/**
 * This assumes that the objects of the table are {@link EObject}s.
 * 
 * @author Francesco Guidieri - initial API and implementation
 *
 */
public class TableViewerEditableColumnBuilder extends TableViewerColumnBuilder {
	@Inject
	private EditingDomainFinder editingDomainFinder;

	@Override
	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer,
			Layout layout, EClass eClass, EStructuralFeature eStructuralFeature,
			int weight) {
		TableViewerColumn viewerColumn= super.buildTableViewerColumn(tableViewer, layout,
				eClass, eStructuralFeature,
				weight);

		if(eStructuralFeature instanceof EAttribute){
			if(isPredefinedValueEditing((EAttribute)eStructuralFeature)){
				viewerColumn.setEditingSupport(new ComboEditingSupport(tableViewer, eStructuralFeature, editingDomainFinder));	
			}else{
				viewerColumn.setEditingSupport(new TableEditingSupport(tableViewer, eStructuralFeature, editingDomainFinder));
			}
		}
		return viewerColumn;
	}

	/**
	 * Custom and reusable implementation of {@link TextCellEditor#doSetValue(Object)}
	 * that converts the value to String.
	 * 
	 * @author Lorenzo Bettini
	 *
	 */
	private static class TextCellEditorCommon extends TextCellEditor {
		public TextCellEditorCommon(Composite parent) {
			super(parent);
		}

		@Override
		protected void doSetValue(Object value) {
			super.doSetValue("" + value);
		}

	}

	static class TableEditingSupport extends EditingSupport {

		private ColumnViewer viewer;
		private EStructuralFeature eStructuralFeature;
		private EditingDomainFinder editingDomainFinder;

		public TableEditingSupport(ColumnViewer viewer, EStructuralFeature eStructuralFeature, EditingDomainFinder editingDomainFinder) {
			super(viewer);
			this.viewer=viewer;
			this.eStructuralFeature=eStructuralFeature;
			this.editingDomainFinder = editingDomainFinder;
		}
		
		@Override
		protected CellEditor getCellEditor(Object element) {
			final Class<?> instanceClass = eStructuralFeature.getEType().getInstanceClass();
			final Composite control = (Composite) getViewer().getControl();
			if (BigDecimal.class.equals(instanceClass)) {
				return new TextCellEditorCommon(control) {
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new BigDecimal(stringValue);
					}
				};
			}
			if (BigInteger.class.equals(instanceClass)) {
				return new TextCellEditorCommon(control) {
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new BigInteger(stringValue);
					}
				};
			}
			if (double.class.equals(instanceClass) || Double.class.equals(instanceClass)) {
				return new TextCellEditorCommon(control) {
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new Double(stringValue);
					}
				};
			}
			if (int.class.equals(instanceClass) || Integer.class.equals(instanceClass)) {
				return new TextCellEditorCommon(control) {
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return Integer.valueOf(stringValue);
					}
				};
			}
			if (float.class.equals(instanceClass) || Float.class.equals(instanceClass)) {
				return new TextCellEditorCommon(control) {
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return new Float(stringValue);
					}
				};
			}
			if (short.class.equals(instanceClass) || Short.class.equals(instanceClass)) {
				return new TextCellEditorCommon(control) {
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return Short.valueOf(stringValue);
					}
				};
			}
			if (byte.class.equals(instanceClass) || Byte.class.equals(instanceClass)) {
				return new TextCellEditorCommon(control) {
					@Override
					protected Object doGetValue() {
						String stringValue = (String) super.doGetValue();
						return Byte.valueOf(stringValue);
					}
				};
			}
			if (boolean.class.equals(instanceClass) || Boolean.class.equals(instanceClass)) {
				return new CheckboxCellEditor(control) {
					@Override
					protected void doSetValue(Object value) {
						// it requires the passed value to be a Boolean
						super.doSetValue(Boolean.valueOf(""+value));
					}
					
					@Override
					protected Object doGetValue() {
						Boolean boolValue = (Boolean) super.doGetValue();
						return boolValue;
					}
				};
			}
			if (Date.class.equals(instanceClass)) {
				return new TextCellEditor(control) {
					@Override
					protected Object doGetValue() {
						String date = (String) super.doGetValue();
						try {
							return new SimpleDateFormat("yyyy-MM-DD").parse(date);
						} catch (ParseException e) {
							return date;
						}
					}
				};
			}
			return new TextCellEditor(control);	
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			EObject eObject = (EObject) element;
			final Object eGet = eObject.eGet(eStructuralFeature);
			return eGet != null ? eGet : "";
		}

		@Override
		protected void setValue(Object element, Object value) {
			EObject eObject = (EObject) element;
			if(eObject.eGet(eStructuralFeature)==null || !eObject.eGet(eStructuralFeature).equals(value)){
				EditingDomain domain=editingDomainFinder.getEditingDomainFor(eObject);
				Command setCommand=new SetCommand(domain,eObject,eStructuralFeature,value);
				domain.getCommandStack().execute(setCommand);
				viewer.refresh();
			}
		}
	}

	static class ComboEditingSupport extends EditingSupport {

		private ColumnViewer viewer;
		private EStructuralFeature feature;
		private List<EEnumLiteral> literals;
		private EditingDomainFinder editingDomainFinder;

		public ComboEditingSupport(ColumnViewer viewer, EStructuralFeature eStructuralFeature, EditingDomainFinder editingDomainFinder) {
			super(viewer);
			this.viewer=viewer;
			this.feature=eStructuralFeature;
			this.editingDomainFinder = editingDomainFinder;
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
			return  -1;
		}

		@Override
		protected void setValue(Object element, Object value) {
			Integer i=(Integer)value;
			EObject eObject=(EObject) element;
			if(eObject.eGet(feature) instanceof Enumerator && i>=0 && i<literals.size()){
				Enumerator typeEnum=(Enumerator) eObject.eGet(feature);
				if(typeEnum!=null && !typeEnum.getLiteral().equals(literals.get(i))){
					EditingDomain domain=editingDomainFinder.getEditingDomainFor(eObject);
					Command setCommand=new SetCommand(domain,eObject,feature,literals.get(i).getInstance());
					domain.getCommandStack().execute(setCommand);
					viewer.refresh();
				}
			}
		}
	}
	
	private boolean isPredefinedValueEditing(EAttribute attribute) {
		return attribute.getEAttributeType() instanceof EEnum;
	}

}
