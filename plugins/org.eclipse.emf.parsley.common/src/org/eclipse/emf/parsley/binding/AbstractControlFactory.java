/**
 * Copyright (c) 2008, 2013 itemis AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   itemis AG - Initial API and implementation
 *   Lorenzo Bettini - refactoring for EmfParsley
 *
 */
package org.eclipse.emf.parsley.binding;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EEnumImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.TextUndoRedo;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

/**
 * 
 * Creates Control for an {@link EStructuralFeature}
 * 
 * @author Dennis Huebner initial code
 * @author Lorenzo Bettini refactoring for Emf Parsley
 * 
 */
public abstract class AbstractControlFactory extends AbstractWidgetFactory {
	@Inject
	@Named(EmfParsleyConstants.CONTENT_ASSIST_SHORTCUT)
	private String contentAssistShortcut;
	
	@Inject
	private Provider<ILabelProvider> labelProviderProvider;

	@Inject
	private ProposalCreator proposalCreator;

	protected EObject owner;
	protected Resource resource;
	protected EditingDomain domain;
	protected EMFDataBindingContext edbc;
	
	protected boolean readonly = false;
	
	public static final String EOBJECT_KEY = EcorePackage.Literals.EOBJECT
			.getName();
	public static final String ESTRUCTURALFEATURE_KEY = EcorePackage.Literals.ESTRUCTURAL_FEATURE
			.getName();

	public AbstractControlFactory() {

	}

	public Provider<ILabelProvider> getLabelProviderProvider() {
		return labelProviderProvider;
	}

	protected ILabelProvider createLabelProvider() {
		return getLabelProviderProvider().get();
	}
	
	public ProposalCreator getProposalCreator() {
		return proposalCreator;
	}

	public void setProposalCreator(ProposalCreator proposalCreator) {
		this.proposalCreator = proposalCreator;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	/**
	 * Initializes this factory for creating {@link Control}s with
	 * Data Binding.
	 * 
	 * The passed {@link EditingDomain} can be null; in that case
	 * Data Binding will be implemented through {@link EMFProperties}, instead
	 * of {@link EMFEditProperties}.  If the {@link EditingDomain} is null
	 * views and editors will not be notified about changes to the passed
	 * {@link EObject}.  This is useful when you want to create {@link Control}s
	 * that act on a copy of the original object (see also {@link IEditingStrategy}).
	 * 
	 * @param domain
	 * @param owner
	 * @param parent
	 * @see IEditingStrategy
	 */
	public void init(EditingDomain domain, EObject owner, Composite parent) {
		super.init(parent);
		this.edbc = new EMFDataBindingContext();
		this.domain = domain;
		this.owner = owner;
	}

	protected Resource getResource() {
		if (resource == null) {
			resource = owner.eResource();
		}
		return resource;
	}

	public Control create(EStructuralFeature feature) {
		Control control = null;

		control = polymorphicCreateControl(feature);
		if (control == null) {
			if (feature.isMany()) {
				control = bindList(feature);
			} else {
				control = bindValue(feature);
			}
			setupControl(feature, control);
		}
		
		registerUndo(control);
		
		return control;
	}

	protected void registerUndo(Control control) {
		if (control instanceof Text) {
			Text text = (Text) control;
			new TextUndoRedo(text);
		}
	}

	protected Control bindList(final EStructuralFeature feature) {
		IObservableValue source = createFeatureObserveable(feature);

		ControlObservablePair retValAndTargetPair = createControlForList(feature);
		Control retVal = retValAndTargetPair.getControl();
		IObservableValue target = retValAndTargetPair.getObservableValue();

		Binding binding = edbc.bindValue(target, source);
		binding.updateModelToTarget();
		return retVal;
	}

	private IObservableValue createFeatureObserveable(final EStructuralFeature feature) {
		IObservableValue source=polymorphicCreateObserveable(domain, owner, feature);
		if(source==null){	
			if (domain != null){
				source = EMFEditProperties.value(domain, feature).observe(owner);
			}else{
				source = EMFProperties.value(feature).observe(owner);
			}
		}
		return source;
	}

	protected ControlObservablePair createControlForList(
			final EStructuralFeature feature) {
		ControlObservablePair result = polymorphicGetObservableControl(feature);
		if (result != null) {
			return result;
		}

		MultipleFeatureControl mfc = new MultipleFeatureControl(getParent(),
				this, labelProviderProvider.get(), owner,
				feature, getProposalCreator(), isReadonly());
		IObservableValue target = new MultipleFeatureControlObservable(mfc);
		return new ControlObservablePair(mfc, target);
	}

	private Control bindValue(EStructuralFeature feature) {
		IObservableValue featureObservable = createFeatureObserveable(feature);
		
		Control control = polymorphicCreateControl(feature, featureObservable);
		if (control != null) {
			return control;
		}

		ControlObservablePair retValAndTargetPair = createControlAndObservableValue(feature);
		Control retVal = retValAndTargetPair.getControl();
		IObservableValue controlObservable = retValAndTargetPair
				.getObservableValue();

		if (controlObservable != null) {
			edbc.bindValue(controlObservable, featureObservable, null, null);
		}
		
		return retVal;
	}

	protected ControlObservablePair createControlAndObservableValue(
			EStructuralFeature feature) {
		ControlObservablePair result = polymorphicGetObservableControl(feature);
		if (result != null) {
			return result;
		}

		if (isBooleanFeature(feature)) {
			return createControlAndObservableValueForBoolean();
		} else {
			return createControlAndObservableValueForNonBooleanFeature(feature);
		}
	}

	protected boolean isBooleanFeature(EStructuralFeature feature) {
		return feature.getEType().equals(EcorePackage.Literals.EBOOLEAN)
				|| feature.getEType().equals(
						EcorePackage.Literals.EBOOLEAN_OBJECT)
				|| (feature.getEType() instanceof EDataType && (feature
						.getEType().getInstanceClass() == Boolean.class || feature
						.getEType().getInstanceClass() == Boolean.TYPE));
	}

	protected ControlObservablePair createControlAndObservableValueForBoolean() {
		ControlObservablePair retValAndTargetPair = new ControlObservablePair();
		Button b = createButton("", SWT.CHECK);
		b.setEnabled(!isReadonly());
		retValAndTargetPair.setControl(b);
		retValAndTargetPair.setObservableValue(SWTObservables
				.observeSelection(b));
		return retValAndTargetPair;
	}

	protected boolean hasPredefinedProposals(EStructuralFeature feature) {
		return feature instanceof EReference
				|| feature.getEType() instanceof EEnumImpl;
	}

	protected ControlObservablePair createControlAndObservableValueForNonBooleanFeature(
			EStructuralFeature feature) {
		List<?> proposals = null;
		if (!isReadonly()) {
			proposals = createProposals(feature);
		}
		if (hasPredefinedProposals(feature) && !isReadonly()) {
			return createControlAndObservableWithPredefinedProposals(proposals);
		} else {
			if (isReadonly() && feature instanceof EReference) {
				return createControlAndObservableForEObjectReadOnly();
			}
			return createControlAndObservableWithoutPredefinedProposals(proposals);
		}
	}

	public List<Object> createProposals(EStructuralFeature feature) {
		getProposalCreator().setResource(getResource());
		return getProposalCreator().proposals(owner, feature);
	}

	protected ControlObservablePair createControlAndObservableWithPredefinedProposals(
			List<?> proposals) {
		ComboViewer combo = createComboViewer(SWT.READ_ONLY);
		combo.setContentProvider(new ArrayContentProvider());
		combo.setLabelProvider(createLabelProvider());
		combo.setInput(proposals);
		ControlObservablePair retValAndTargetPair = new ControlObservablePair();
		retValAndTargetPair.setControl(combo.getCombo());
		retValAndTargetPair.setObservableValue(ViewersObservables
				.observeSingleSelection(combo));
		return retValAndTargetPair;
	}

	protected ControlObservablePair createControlAndObservableWithoutPredefinedProposals(
			List<?> proposals) {
		ControlObservablePair retValAndTargetPair = new ControlObservablePair();
		Text t = createText("");
		t.setEditable(!isReadonly());
		addContentProposalAdapter(t, proposals);
		retValAndTargetPair.setControl(t);
		retValAndTargetPair.setObservableValue(SWTObservables.observeText(t,
				SWT.Modify));

		return retValAndTargetPair;
	}

	protected ControlObservablePair createControlAndObservableForEObjectReadOnly() {
		ControlObservablePair retValAndTargetPair = new ControlObservablePair();
		Text t = createText("");
		t.setEditable(false);
		retValAndTargetPair.setControl(t);
		retValAndTargetPair.setObservableValue(new EObjectTextObservable(
				createLabelProvider(), t));

		return retValAndTargetPair;
	}

	protected void addContentProposalAdapter(Text t, List<?> proposals) {
		if (proposals != null && !proposals.isEmpty()) {
			Iterable<String> filteredNotNullToString = Iterables.transform(
					Iterables.filter(proposals, Predicates.notNull()),
					new Function<Object, String>() {

						public String apply(Object input) {
							return input.toString();
						}

					});
			ControlDecoration field = new ControlDecoration(t, SWT.BORDER);
			FieldDecoration requiredFieldIndicator = FieldDecorationRegistry
					.getDefault().getFieldDecoration(
							FieldDecorationRegistry.DEC_CONTENT_PROPOSAL);
			field.setImage(requiredFieldIndicator.getImage());
			field.setDescriptionText(requiredFieldIndicator.getDescription());
			KeyStroke keyStroke = null;
			try {
				keyStroke = KeyStroke.getInstance(contentAssistShortcut);
			} catch (ParseException e) {
				EmfParsleyActivator
						.logError("Error while parsing keystroke: " + contentAssistShortcut, e);
			}
			new ContentProposalAdapter(t, new TextContentAdapter(),
					new SimpleContentProposalProvider(
							Iterables.toArray(filteredNotNullToString, String.class)), 
							keyStroke,
					null);
		}
	}

	private void setupControl(EStructuralFeature f, Control c) {
		// disable unchangeable and unserializable
		if (c != null) {
			// don't override readonly behavior
			if (c.isEnabled()) {
				c.setEnabled(f.isChangeable()
					&& (!(f.getEType() instanceof EDataType && !((EDataType) f
							.getEType()).isSerializable())));
			}
			c.setData(AbstractControlFactory.ESTRUCTURALFEATURE_KEY, f);
			c.setData(AbstractControlFactory.EOBJECT_KEY, owner);
			c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
	}

	public void dispose() {
		edbc.dispose();
	}

	private ControlObservablePair polymorphicGetObservableControl(EStructuralFeature element) {
		return this.<ControlObservablePair>createPolymorphicDispatcherForCreateControl(element, 1).
					invoke(element);
	}

	/**
	 * Polymorphically invokes a create_EClass_feature(DataBindingContext, IObservableValue)
	 * @param element
	 * @param featureObservable
	 * @return
	 */
	private Control polymorphicCreateControl(EStructuralFeature element,
			IObservableValue featureObservable) {
		return this.<Control>createPolymorphicDispatcherForCreateControl(element, 2).
				invoke(edbc, featureObservable);
	}

	/**
	 * Polymorphically invokes a create_EClass_feature(EObject)
	 * @param element
	 * @return
	 */
	private Control polymorphicCreateControl(EStructuralFeature element) {
		return this.<Control>createPolymorphicDispatcherForCreateControl(element, 1).
				invoke(owner);
	}

	private <T> PolymorphicDispatcher<T> createPolymorphicDispatcherForCreateControl(
			EStructuralFeature element, int numOfParams) {
		return PolymorphicDispatcherExtensions.createPolymorphicDispatcherBasedOnFeature(
				this, element.getEContainingClass(), element, "control_", numOfParams);
	}

	private IObservableValue polymorphicCreateObserveable(EditingDomain domain, EObject element,
			EStructuralFeature feature) {
		return PolymorphicDispatcherExtensions.
				<IObservableValue>createPolymorphicDispatcherBasedOnFeature(
						this, feature.getEContainingClass(), feature, "observe_", 2).
					invoke(domain, element);
	}

}
