/**
 * <copyright> 
 *
 * Copyright (c) 2008 itemis AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   itemis AG - Initial API and implementation
 *
 * </copyright>
 *
 */
package org.eclipse.emf.parsley.binding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Widget that opens the {@link FeatureEditorDialog} to select some feature values.<br>
 * Shows values comma separated as label.
 * 
 * @author Dennis Huebner
 * @author Lorenzo Bettini (modifications)
 * 
 */
public class MultipleFeatureControl extends Composite {

	private FeatureEditorDialog dialog;
	private final ILabelProvider labelProvider;
	private ISelectionProvider inernalProvider = new InternalSelectionProvider();

	private Label label;

	private Button button;

	private boolean beQuiet;

	public MultipleFeatureControl(final Composite parent, FormToolkit toolkit, final ILabelProvider labelProvider,
			final EObject object, final EStructuralFeature feature, final ProposalCreator proposalcreator,
			boolean readonly) {
		super(parent, SWT.NONE);
		this.labelProvider = labelProvider;
		toolkit.adapt(this);
		setLayout(new GridLayout(2, false));
		label = toolkit.createLabel(this, "");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				MultipleFeatureControl.this.setFocus();
			}
		});
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		button = toolkit.createButton(this, "...", SWT.PUSH);
		button.setLayoutData(new GridData());
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> oldValue = unwrapSelection();
				//TODO (dennis) load choice of values in a runnable with status bar
				List<Object> proposals = proposalcreator.proposals(object, feature);
				dialog = new FeatureEditorDialog(parent.getShell(), new CachedLabelProvider(labelProvider), object,
						feature.getEType(), oldValue, "Select", proposals, false,
						feature.isOrdered(), proposals != null);
				dialog.setBlockOnOpen(true);
				if (dialog.open() == Window.OK) {
					setSelection(new StructuredSelection(dialog.getResult().toArray()));
					// propertyChangeListener
					// .propertyChange(new PropertyChangeEvent(this,
					// feature.getName(), oldValue, currentValue));
				}
			}
		});

		button.setVisible(!readonly);
	}

	protected void setSelection(ISelection structuredSelection) {
		this.inernalProvider.setSelection(structuredSelection);
	}

	private List<?> unwrapSelection() {
		List<Object> l = new ArrayList<Object>();
		if (getSelection() != null && !getSelection().isEmpty() && getSelection() instanceof IStructuredSelection) {
			for (Iterator<?> iterator = ((IStructuredSelection) getSelection()).iterator(); iterator.hasNext();) {
				Object object = iterator.next();
				l.add(object);

			}

		}
		return l;

	}

	private ISelection getSelection() {
		return this.inernalProvider.getSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.widgets.Control#setMenu(org.eclipse.swt.widgets.Menu)
	 */
	@Override
	public void setMenu(Menu menu) {
		if (label != null && !label.isDisposed())
			label.setMenu(menu);
		if (button != null && !button.isDisposed())
			button.setMenu(menu);

	}

	@Override
	public boolean setFocus() {
		if (button != null)
			return button.setFocus();
		return super.setFocus();
	}

	@Override
	public void addFocusListener(FocusListener listener) {
		button.addFocusListener(listener);
	}

	@Override
	public void removeFocusListener(FocusListener listener) {
		button.removeFocusListener(listener);
	}

	/**
	 * @param selection
	 */
	private void recalculateLabelText() {
		StringBuffer buffy = new StringBuffer();
		List<?> currSelection = unwrapSelection();
		if (currSelection != null && !currSelection.isEmpty())
			for (Iterator<?> iterator = currSelection.iterator(); iterator.hasNext();) {
				Object sel = iterator.next();
				buffy.append(labelProvider.getText(sel));
				if (iterator.hasNext())
					buffy.append(", ");
			}
		label.setText(buffy.toString());
	}

	class InternalSelectionProvider implements ISelectionProvider {

		public void setSelection(ISelection selection) {
			this.selection = selection;
			recalculateLabelText();
			if (!beQuiet) {
				// notify
				// SelectionProviderMultipleSelectionObservableList$SelectionListener
				for (ISelectionChangedListener currListener : listeners) {
					currListener.selectionChanged(new SelectionChangedEvent(this, this.selection));
				}
			}
		}

		private java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

		public void addSelectionChangedListener(ISelectionChangedListener listener) {
			listeners.add(listener);
		}

		public void removeSelectionChangedListener(ISelectionChangedListener listener) {
			listeners.remove(listener);
		}

		public ISelection getSelection() {
			return selection;
		}

		private ISelection selection;

	}

	public ISelectionProvider getInternalSelectionProvider() {
		return inernalProvider;
	}

	public void quietClearSelection() {
		beQuiet = true;
		try {
			this.setSelection(new StructuredSelection());
		}
		finally {
			beQuiet = false;
		}
	}

	public void setValue(Object newValue) {
		setSelection(new StructuredSelection((List<?>) newValue));
	}

	public Object getValue() {
		return ((StructuredSelection) getSelection()).toList();
	}
}
