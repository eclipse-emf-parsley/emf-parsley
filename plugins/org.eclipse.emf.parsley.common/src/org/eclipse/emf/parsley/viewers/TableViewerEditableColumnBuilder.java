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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor.EDataTypeCellEditor;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ILabelProvider;
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

	@Inject
	private ILabelProvider labelProvider;

	@Override
	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer,
			Layout layout, EClass eClass, EStructuralFeature eStructuralFeature,
			int weight) {
		TableViewerColumn viewerColumn= super.buildTableViewerColumn(tableViewer, layout,
				eClass, eStructuralFeature,
				weight);

		if(eStructuralFeature instanceof EAttribute){
			viewerColumn.setEditingSupport(new TableEditingSupport(tableViewer, eStructuralFeature, editingDomainFinder, labelProvider));
		}
		return viewerColumn;
	}

	static class TableEditingSupport extends EditingSupport {

		private EStructuralFeature eStructuralFeature;
		private EditingDomainFinder editingDomainFinder;
		private ILabelProvider labelProvider;

		public TableEditingSupport(ColumnViewer viewer, EStructuralFeature eStructuralFeature, EditingDomainFinder editingDomainFinder, ILabelProvider labelProvider) {
			super(viewer);
			this.eStructuralFeature=eStructuralFeature;
			this.editingDomainFinder = editingDomainFinder;
			this.labelProvider = labelProvider;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			final EClassifier eType = eStructuralFeature.getEType();
			final Composite composite = (Composite) getViewer().getControl();
			final Class<?> instanceClass = eType.getInstanceClass();

			if (eType instanceof EDataType) {
				if (instanceClass == Boolean.class) {
					return getCellEditorForBoolean(composite, new Object[] { null, Boolean.FALSE, Boolean.TRUE });
				} else if (instanceClass == Boolean.TYPE) {
					return getCellEditorForBoolean(composite, new Object[] { Boolean.FALSE, Boolean.TRUE });
				} else if (eType instanceof EEnum) {
					EEnum eEnum = (EEnum) eType;
					List<Enumerator> enumerators = new ArrayList<Enumerator>();
					for (EEnumLiteral eEnumLiteral : eEnum.getELiterals()) {
						enumerators.add(eEnumLiteral.getInstance());
					}
					return new ExtendedComboBoxCellEditor(composite, enumerators, getEditLabelProvider(), false);
				}

				EDataType eDataType = (EDataType) eType;
				return new EDataTypeCellEditor(eDataType, composite);
			}

			return new TextCellEditor(composite);	
		}

		protected ExtendedComboBoxCellEditor getCellEditorForBoolean(final Composite composite, Object[] values) {
			return new ExtendedComboBoxCellEditor(composite, Arrays.asList(values), getEditLabelProvider(), false);
		}

		private ILabelProvider getEditLabelProvider() {
			return labelProvider;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			EObject eObject = (EObject) element;
			return eObject.eGet(eStructuralFeature);
		}

		@Override
		protected void setValue(Object element, Object value) {
			EObject eObject = (EObject) element;
			if(eObject.eGet(eStructuralFeature)==null || !eObject.eGet(eStructuralFeature).equals(value)){
				EditingDomain domain=editingDomainFinder.getEditingDomainFor(eObject);
				Command setCommand=new SetCommand(domain,eObject,eStructuralFeature,value);
				domain.getCommandStack().execute(setCommand);
			}
		}
	}

}
