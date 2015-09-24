/**
 * Copyright (c) 2008, 2013 itemis AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   itemis AG - Initial API and implementation
 *   Lorenzo Bettini, Francesco Guidieri - refactoring for EmfParsley
 *
 */
package org.eclipse.emf.parsley.composite;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.TextUndoRedo;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;
import org.eclipse.emf.parsley.ui.provider.FeatureLabelCaptionProvider;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.emf.parsley.util.FeatureHelper;
import org.eclipse.emf.parsley.widgets.IWidgetFactory;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
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
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
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
public abstract class AbstractControlFactory implements IWidgetFactory {

	private static final String OBSERVE_PREFIX = "observe_";

	private static final String CONTROL_PREFIX = "control_";

	@Inject
	@Named(EmfParsleyConstants.CONTENT_ASSIST_SHORTCUT)
	private String contentAssistShortcut;

	@Inject
	private Provider<ILabelProvider> labelProviderProvider;

	@Inject
	private FeatureHelper featureHelper;

	@Inject
	private ProposalCreator proposalCreator;

	protected EObject owner;
	protected Resource resource;
	protected EditingDomain domain;
	protected EMFDataBindingContext edbc;

	/**
	 * This will be created by the abstract method {@link #createWidgetFactory()}
	 */
	protected IWidgetFactory widgetFactory;

	/**
	 * This will be created by the abstract method
	 * {@link #createFeatureLabelCaptionProvider()}
	 */
	protected FeatureLabelCaptionProvider featureLabelCaptionProvider;

	protected boolean readonly = false;

	public static final String EOBJECT_KEY = EcorePackage.Literals.EOBJECT
			.getName();
	public static final String ESTRUCTURALFEATURE_KEY = EcorePackage.Literals.ESTRUCTURAL_FEATURE
			.getName();

	public AbstractControlFactory() {

	}

	/**
	 * Concrete implementation should create a {@link IWidgetFactory} according
	 * to the specific widgets (e.g., for dialogs or forms).
	 * 
	 * @return the concrete implementation
	 */
	protected abstract IWidgetFactory createWidgetFactory();

	/**
	 * Concrete implementation should create a
	 * {@link FeatureLabelCaptionProvider} according to the specific widgets
	 * (e.g., for dialogs or forms).
	 * 
	 * @return the concrete implementation
	 */
	protected abstract FeatureLabelCaptionProvider createFeatureLabelCaptionProvider();

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
		widgetFactory = createWidgetFactory();
		init(parent);
		featureLabelCaptionProvider = createFeatureLabelCaptionProvider();
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

	/**
	 * Creates a caption label and a {@link Control} for the passed {@link EStructuralFeature}
	 * of the {@link EObject} handled by this factory.
	 * 
	 * @param feature the {@link EStructuralFeature} for the creation
	 */
	public void createEditingField(EStructuralFeature feature) {
		featureLabelCaptionProvider.getLabel(getParent(), owner, feature);
		create(feature);
	}

	/**
	 * Creates a {@link Control} for the passed {@link EStructuralFeature}
	 * of the {@link EObject} handled by this factory.
	 * 
	 * @param feature the {@link EStructuralFeature} for the creation of control
	 * @return a {@link Control}
	 */
	public Control create(EStructuralFeature feature) {
		Control control = null;

		control = polymorphicCreateControl(feature);
		if (control == null) {
			if (feature.isMany()) {
				control = createAndBindList(feature);
			} else {
				control = createAndBindValue(feature);
			}
			setupControl(feature, control);
		}

		registerUndoRedo(control);

		return control;
	}

	protected KeyListener registerUndoRedo(Control control) {
		if (control instanceof Text) {
			return new TextUndoRedo((Text) control);
		}
		return null;
	}

	protected Control createAndBindList(final EStructuralFeature feature) {
		IObservableValue source = createFeatureObserveable(feature);

		ControlObservablePair retValAndTargetPair = createControlForList(feature);
		Control retVal = retValAndTargetPair.getControl();
		IObservableValue target = retValAndTargetPair.getObservableValue();

		Binding binding = edbc.bindValue(target, source);
		binding.updateModelToTarget();
		return retVal;
	}

	protected IObservableValue createFeatureObserveable(final EStructuralFeature feature) {
		IObservableValue source = polymorphicCreateObserveable(domain, feature);
		if (source == null) {
			if (domain != null) {
				source = EMFEditProperties.value(domain, feature).observe(owner);
			} else {
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

	Control createAndBindValue(EStructuralFeature feature) {
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

		if (featureHelper.isBooleanFeature(feature)) {
			return createControlAndObservableValueForBoolean();
		} else {
			return createControlAndObservableValueForNonBooleanFeature(feature);
		}
	}

	protected ControlObservablePair createControlAndObservableValueForBoolean() {
		ControlObservablePair retValAndTargetPair = new ControlObservablePair();
		Button b = createButton("", SWT.CHECK);
		b.setEnabled(!isReadonly());
		retValAndTargetPair.setControl(b);
		retValAndTargetPair.setObservableValue(DatabindingUtil.observeSelection(b));
		return retValAndTargetPair;
	}

	protected ControlObservablePair createControlAndObservableValueForNonBooleanFeature(
			EStructuralFeature feature) {
		List<?> proposals = null;
		if (!isReadonly()) {
			proposals = createProposals(feature);
		}
		if (featureHelper.hasPredefinedProposals(feature) && !isReadonly()) {
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
		retValAndTargetPair.setObservableValue(DatabindingUtil.observeText(t, SWT.Modify));
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

	protected ContentProposalAdapter addContentProposalAdapter(Text t, List<?> proposals) {
		if (proposals != null && !proposals.isEmpty()) {
			Iterable<String> filteredNotNullToString = Iterables.transform(
					Iterables.filter(proposals, Predicates.notNull()),
					new Function<Object, String>() {

						@Override
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
			return new ContentProposalAdapter(t, new TextContentAdapter(),
					new SimpleContentProposalProvider(
							Iterables.toArray(filteredNotNullToString, String.class)), 
							keyStroke,
					null);
		}
		return null;
	}

	private void setupControl(EStructuralFeature f, Control c) {
		// disable unchangeable and unserializable
		if (c != null) {
			// don't override readonly behavior
			if (c.isEnabled()) {
				c.setEnabled(
					featureHelper.isEditable(f));
			}
			c.setData(AbstractControlFactory.ESTRUCTURALFEATURE_KEY, f);
			c.setData(AbstractControlFactory.EOBJECT_KEY, owner);
			// set default layout data if not already set by a custom
			// polymorphic implementation or from the DSL
			if (c.getLayoutData()==null) {
				c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			}
		}
	}

	public void dispose() {
		edbc.dispose();
	}

	private ControlObservablePair polymorphicGetObservableControl(
			EStructuralFeature feature) {
		return PolymorphicDispatcherExtensions
				.<ControlObservablePair> polymorphicInvokeBasedOnFeature(this,
						owner.eClass(), feature, CONTROL_PREFIX, feature);
	}

	/**
	 * Polymorphically invokes a create_EClass_feature(DataBindingContext,
	 * IObservableValue)
	 * 
	 * @param feature
	 * @param featureObservable
	 * @return
	 */
	private Control polymorphicCreateControl(EStructuralFeature feature,
			IObservableValue featureObservable) {
		return PolymorphicDispatcherExtensions
				.<Control> polymorphicInvokeBasedOnFeature(this,
						owner.eClass(), feature, CONTROL_PREFIX, edbc,
						featureObservable);
	}

	/**
	 * Polymorphically invokes a create_EClass_feature(EObject)
	 * 
	 * @param feature
	 * @return
	 */
	private Control polymorphicCreateControl(EStructuralFeature feature) {
		return PolymorphicDispatcherExtensions
				.<Control> polymorphicInvokeBasedOnFeature(this,
						owner.eClass(), feature, CONTROL_PREFIX, owner);
	}

	private IObservableValue polymorphicCreateObserveable(EditingDomain domain,
			EStructuralFeature feature) {
		return PolymorphicDispatcherExtensions
				.<IObservableValue> polymorphicInvokeBasedOnFeature(this,
						owner.eClass(), feature, OBSERVE_PREFIX, domain, owner);
	}

	@Override
	public Label createLabel(String text) {
		return widgetFactory.createLabel(text);
	}

	@Override
	public Label createLabel(Composite parent, String text) {
		return widgetFactory.createLabel(parent, text);
	}

	@Override
	public Button createButton(String text, int... styles) {
		return widgetFactory.createButton(text, styles);
	}

	@Override
	public Button createButton(Composite parent, String text, int style) {
		return widgetFactory.createButton(parent, text, style);
	}

	@Override
	public Text createText(String text) {
		return widgetFactory.createText(text);
	}

	@Override
	public Text createText(String text, int... styles) {
		return widgetFactory.createText(text, styles);
	}

	@Override
	public Text createText(Composite parent, String text) {
		return widgetFactory.createText(parent, text);
	}

	@Override
	public Text createText(Composite parent, String text, int style) {
		return widgetFactory.createText(parent, text, style);
	}

	@Override
	public ComboViewer createComboViewer(int... styles) {
		return widgetFactory.createComboViewer(styles);
	}

	@Override
	public ComboViewer createComboViewer(Composite parent, int style) {
		return widgetFactory.createComboViewer(parent, style);
	}

	@Override
	public DateTime createDateTime() {
		return widgetFactory.createDateTime();
	}

	@Override
	public DateTime createDateTime(int... styles) {
		return widgetFactory.createDateTime(styles);
	}

	@Override
	public DateTime createDateTime(Composite parent) {
		return widgetFactory.createDateTime(parent);
	}

	@Override
	public DateTime createDateTime(Composite parent, int style) {
		return widgetFactory.createDateTime(parent, style);
	}

	@Override
	public Composite getParent() {
		return widgetFactory.getParent();
	}

}
