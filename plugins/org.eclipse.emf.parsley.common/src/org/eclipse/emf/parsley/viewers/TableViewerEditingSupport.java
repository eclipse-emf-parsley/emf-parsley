/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.viewers;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * {@link EditingSupport} for the columns of our table viewers.
 *
 * It delegates the creation of {@link CellEditor} to an injected
 * {@link TableViewerCellEditorFactory} and updates the model using our
 * {@link EditingDomainFinder}.
 *
 * IMPORTANT: this relies on injection, thus, after calling the constructor, you
 * need to inject members with a members injector. Even better, use the
 * corresponding table viewer editing support factory to create instances of
 * this class.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class TableViewerEditingSupport extends EditingSupport {

	/**
	 * This is the feature we create a cell editor for.
	 */
	private EStructuralFeature eStructuralFeature;

	/**
	 * The initial value of the cell editor, which we will use to tell whether
	 * we need to update the model.
	 */
	private Object initialCellValue;

	@Inject
	private EditingDomainFinder editingDomainFinder;

	@Inject
	private TableViewerCellEditorFactory cellEditorFactory;

	public TableViewerEditingSupport(ColumnViewer viewer, EStructuralFeature eStructuralFeature) {
		super(viewer);
		this.eStructuralFeature = eStructuralFeature;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return cellEditorFactory.createCellEditor((Composite) getViewer().getControl(), element, eStructuralFeature);
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected void initializeCellEditorValue(CellEditor cellEditor, ViewerCell cell) {
		super.initializeCellEditorValue(cellEditor, cell);
		initialCellValue = cellEditor.getValue();
	}

	@Override
	protected Object getValue(Object element) {
		EObject eObject = (EObject) element;
		return eObject.eGet(eStructuralFeature);
	}

	@Override
	protected void setValue(Object element, Object value) {
		EObject eObject = (EObject) element;

		boolean modelNeedsToBeUpdated = false;
		if (initialCellValue == null) {
			modelNeedsToBeUpdated = value != null;
		} else {
			modelNeedsToBeUpdated = !initialCellValue.equals(value);
		}

		if (modelNeedsToBeUpdated) {
			EditingDomain domain = editingDomainFinder.getEditingDomainFor(eObject);
			Command setCommand = new SetCommand(domain, eObject, eStructuralFeature, value);
			domain.getCommandStack().execute(setCommand);
		}
	}
}