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

import java.util.ArrayList;
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
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.TextUndoRedo;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.internal.databinding.DataBindingHelper;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;
import org.eclipse.emf.parsley.ui.provider.ComboViewerLabelProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureLabelCaptionProvider;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.emf.parsley.util.FeatureHelper;
import org.eclipse.emf.parsley.widgets.IWidgetFactory;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
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
 * @author Lorenzo Bettini refactoring for EMF Parsley
 * @author Francesco Guidieri added validation support
 * 
 */
public abstract class AbstractControlFactory implements IWidgetFactory {

	private static final int GRID_DATA_HORIZONTAL_INDENT = 10;

	private static final String OBSERVE_PREFIX = "observe_";

	private static final String CONTROL_PREFIX = "control_";

	@Inject
	@Named(EmfParsleyConstants.CONTENT_ASSIST_SHORTCUT)
	private String contentAssistShortcut;

	@Inject
	private Provider<ILabelProvider> labelProviderProvider;

	@Inject
	private Provider<ComboViewerLabelProvider> comboViewerLabelProviderProvider;

	@Inject
	private FeatureHelper featureHelper;

	@Inject
	private ProposalCreator proposalCreator;

	@Inject
	private DataBindingHelper dataBindingHelper;

	private EObject owner;
	private Resource resource;
	private EditingDomain domain;
	private EMFDataBindingContext edbc;

	/**
	 * This will be created by the abstract method {@link #createWidgetFactory()}
	 */
	private IWidgetFactory widgetFactory;

	/**
	 * This will be created by the abstract method
	 * {@link #createFeatureLabelCaptionProvider()}
	 */
	private FeatureLabelCaptionProvider featureLabelCaptionProvider;

	private boolean readonly = false;

	public static final String EOBJECT_KEY = EcorePackage.Literals.EOBJECT
			.getName();
	public static final String ESTRUCTURALFEATURE_KEY = EcorePackage.Literals.ESTRUCTURAL_FEATURE
			.getName();

	/**
	 * The passed {@link EditingDomain} in {@link EObjectParameter} can be null; in
	 * that case Data Binding will be implemented through {@link EMFProperties},
	 * instead of {@link EMFEditProperties}. If the {@link EditingDomain} is null
	 * views and editors will not be notified about changes to the passed
	 * {@link EObject}. This is useful when you want to create {@link Control}s that
	 * act on a copy of the original object (see also {@link IEditingStrategy}).
	 * 
	 * @since 2.0
	 */
	@Inject
	protected AbstractControlFactory(EObjectParameter eObjectParameter) {
		this.owner = eObjectParameter.getObject();
		this.domain = eObjectParameter.getEditingDomain();
		this.edbc = new EMFDataBindingContext();
	}

	/**
	 * This will be called after construction.
	 * 
	 * @param compositeFactory
	 */
	@Inject
	private void createAdditionals(CompositeFactory compositeFactory) {
		widgetFactory = createWidgetFactory(compositeFactory);
		featureLabelCaptionProvider = createFeatureLabelCaptionProvider(compositeFactory);
	}

	/**
	 * Concrete implementation should create a {@link IWidgetFactory} according
	 * to the specific widgets (e.g., for dialogs or forms).
	 * 
	 * @param factory 
	 * 
	 * @return the concrete implementation
	 * @since 2.0
	 */
	protected abstract IWidgetFactory createWidgetFactory(CompositeFactory factory);

	/**
	 * Concrete implementation should create a
	 * {@link FeatureLabelCaptionProvider} according to the specific widgets
	 * (e.g., for dialogs or forms).
	 * 
	 * @param compositeFactory 
	 * 
	 * @return the concrete implementation
	 * @since 2.0
	 */
	protected abstract FeatureLabelCaptionProvider createFeatureLabelCaptionProvider(CompositeFactory compositeFactory);

	protected EObject getOwner() {
		return owner;
	}

	protected EditingDomain getEditingDomain() {
		return domain;
	}

	protected EMFDataBindingContext getDataBindingContext() {
		return edbc;
	}

	protected Resource getResource() {
		if (resource == null) {
			resource = owner.eResource();
		}
		return resource;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	private ILabelProvider createLabelProvider() {
		return labelProviderProvider.get();
	}

	private ILabelProvider createComboViewerLabelProvider() {
		return comboViewerLabelProviderProvider.get();
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
	 * of the {@link EObject} handled by this factory, using polymorphic dispatch.
	 * 
	 * @param feature the {@link EStructuralFeature} for the creation of control
	 * @return a {@link Control}
	 */
	public Control create(EStructuralFeature feature) {
		return create(feature, true);
	}

	/**
	 * Creates a {@link Control} for the passed {@link EStructuralFeature}
	 * of the {@link EObject} handled by this factory, using polymorphic dispatch, if
	 * specified in the argument withPolymorphicDispatch.
	 * 
	 * @param feature
	 * @param withPolymorphicDispatch
	 * @return
	 */
	public Control create(EStructuralFeature feature, boolean withPolymorphicDispatch) {
		Control control = null;

		if (withPolymorphicDispatch) {
			control = polymorphicCreateControl(feature);
		}

		if (control == null) {
			if (feature.isMany()) {
				control = createAndBindList(feature, withPolymorphicDispatch);
			} else {
				control = createAndBindValue(feature, withPolymorphicDispatch);
			}
			setupControl(feature, control);
		}

		registerUndoRedo(control);

		return control;
	}

	/**
	 * Creates a {@link Control} for the passed {@link EStructuralFeature}
	 * of the {@link EObject} handled by this factory, using the default
	 * implementation, that is, without using polymorphic dispatch.
	 * 
	 * @param feature the {@link EStructuralFeature} for the creation of control
	 * @return a {@link Control}
	 */
	public Control createDefaultControl(EStructuralFeature feature) {
		return create(feature, false);
	}

	protected KeyListener registerUndoRedo(Control control) {
		if (control instanceof Text) {
			return new TextUndoRedo((Text) control);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	protected Control createAndBindList(final EStructuralFeature feature, boolean withPolymorphicDispatch) {
		IObservableValue source = createFeatureObserveable(feature, withPolymorphicDispatch);

		ControlObservablePair retValAndTargetPair = createControlForList(feature, withPolymorphicDispatch);
		Control retVal = retValAndTargetPair.getControl();
		IObservableValue target = retValAndTargetPair.getObservableValue();

		Binding binding = bindValue(feature, target, source);
		binding.updateModelToTarget();
		return retVal;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected IObservableValue createFeatureObserveable(final EStructuralFeature feature, boolean withPolymorphicDispatch) {
		if (withPolymorphicDispatch) {
			IObservableValue source = polymorphicCreateObserveable(domain, feature);
			if (source != null) {
				return source;
			}
		}
		if (domain != null) {
			return EMFEditProperties.value(domain, feature).observe(owner);
		} else {
			return EMFProperties.value(feature).observe(owner);
		}
	}

	@SuppressWarnings("rawtypes")
	protected ControlObservablePair createControlForList(
			final EStructuralFeature feature, boolean withPolymorphicDispatch) {
		if (withPolymorphicDispatch) {
			ControlObservablePair result = polymorphicGetObservableControl(feature);
			if (result != null) {
				return result;
			}
		}

		MultipleFeatureControl mfc = new MultipleFeatureControl(getParent(),
				this, labelProviderProvider.get(), owner,
				feature, proposalCreator, isReadonly());
		IObservableValue target = new MultipleFeatureControlObservable(mfc);
		return new ControlObservablePair(mfc, target);
	}

	@SuppressWarnings("rawtypes")
	protected Control createAndBindValue(EStructuralFeature feature, boolean withPolymorphicDispatch) {
		IObservableValue featureObservable = createFeatureObserveable(feature, withPolymorphicDispatch);

		if (withPolymorphicDispatch) {
			Control control = polymorphicCreateControl(feature, featureObservable);
			if (control != null) {
				return control;
			}
		}

		ControlObservablePair retValAndTargetPair = createControlAndObservableValue(feature, withPolymorphicDispatch);
		Control retVal = retValAndTargetPair.getControl();
		IObservableValue controlObservable = retValAndTargetPair
				.getObservableValue();

		if (controlObservable != null) {
			bindValue(feature, controlObservable, featureObservable);
		}

		return retVal;
	}

	/**
	 * @since 1.1
	 */
	@SuppressWarnings("rawtypes")
	protected Binding bindValue(EStructuralFeature feature, IObservableValue target,
			IObservableValue source) {
		return dataBindingHelper.bindValue(feature, target, source, owner, edbc);
	}

	protected ControlObservablePair createControlAndObservableValue(
			EStructuralFeature feature, boolean withPolymorphicDispatch) {
		if (withPolymorphicDispatch) {
			ControlObservablePair result = polymorphicGetObservableControl(feature);
			if (result != null) {
				return result;
			}
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
		List<Object> proposals = new ArrayList<>();
		if (!isReadonly()) {
			proposals = createProposals(feature);
		}
		if (featureHelper.hasPredefinedProposals(feature) && !isReadonly()) {
			if (!featureHelper.isEnum(feature)) {
				// empty item for setting reference to null
				// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=490463
				proposals.add(0, SetCommand.UNSET_VALUE);
			}
			return createControlAndObservableWithPredefinedProposals(proposals);
		} else {
			if (isReadonly() && feature instanceof EReference) {
				return createControlAndObservableForEObjectReadOnly();
			}
			return createControlAndObservableWithoutPredefinedProposals(proposals);
		}
	}

	protected List<Object> createProposals(EStructuralFeature feature) {
		proposalCreator.setResource(getResource());
		return proposalCreator.proposals(owner, feature);
	}

	protected ControlObservablePair createControlAndObservableWithPredefinedProposals(
			List<?> proposals) {
		ComboViewer comboViewer = createComboViewer(SWT.READ_ONLY);
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setLabelProvider(createComboViewerLabelProvider());
		comboViewer.setInput(proposals);
		ControlObservablePair retValAndTargetPair = new ControlObservablePair();
		retValAndTargetPair.setControl(comboViewer.getCombo());
		retValAndTargetPair.setObservableValue(ViewerProperties.singleSelection()
				.observe(comboViewer));
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
					Object::toString);
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
				GridData deafultLayout = new GridData(GridData.FILL_HORIZONTAL);
				deafultLayout.horizontalIndent=GRID_DATA_HORIZONTAL_INDENT;
				c.setLayoutData(deafultLayout);
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
	 * IObservableValue), trying to get the new version with validation support.
	 * If not found, the old version of the method is searched, for backward compatibility. 
	 * 
	 * @param feature
	 * @param featureObservable
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Control polymorphicCreateControl(EStructuralFeature feature,
			IObservableValue featureObservable) {
		Control polymorphicInvokeNewVersion = PolymorphicDispatcherExtensions
				.<Control> polymorphicInvokeBasedOnFeature(this,
						owner.eClass(), feature, CONTROL_PREFIX,
						featureObservable, feature);
		if(polymorphicInvokeNewVersion!=null){
			return polymorphicInvokeNewVersion;
		}
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

	@SuppressWarnings("rawtypes")
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
	public Text createText(Composite parent, int... styles) {
		return widgetFactory.createText(parent, styles);
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

}
