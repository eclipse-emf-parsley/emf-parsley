/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.composite.ControlObservablePair;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.eclipse.emf.parsley.tests.models.testmodels.BaseClass;
import org.eclipse.emf.parsley.tests.util.CustomDialogControlFactoryForTests;
import org.eclipse.emf.parsley.tests.util.CustomDialogControlFactoryForTestsWithCallToBindValude;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Injector;

public class CustomDialogControlFactoryTest extends AbstractControlFactoryTest {

	@Rule
	public final LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator.class);

	/**
	 * Test the polymorphic method pattern
	 * 
	 * <pre>
	 * Control control_ClassName_FeatureName(ClassName e)
	 * </pre>
	 */
	@Test
	public void testCustomControlPolymorphic() {
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(createBaseClassObject())) {
			public Control control_BaseClass_baseClassFeature(BaseClass e) {
				return createText("Foo");
			}
		};
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertTextEditable(control, true);
		assertText(control, "Foo");
	}

	/**
	 * Test the protected getters from the superclass
	 * 
	 * <pre>
	 * Control control_ClassName_FeatureName(ClassName e)
	 * </pre>
	 */
	@Test
	public void testCustomControlPolymorphicGetters() {
		final BaseClass obj = createBaseClassObject();
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(obj)) {
			public Control control_BaseClass_baseClassFeature(BaseClass e) {
				assertSame(obj, getOwner());
				assertNotNull(getDataBindingContext());
				// in this scenario the editing domain is null
				assertNull(getEditingDomain());
				return createText("Foo");
			}
		};
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertTextEditable(control, true);
		assertText(control, "Foo");
	}

	/**
	 * Test the polymorphic method pattern
	 * 
	 * <pre>
	 * Control control_ClassName_FeatureName(EMFDataBindingContext edbc, IObservableValue modelObservableValue)
	 * </pre>
	 * 
	 * The programmer is responsible of creating and returning the Control
	 * after having set the target observable value
	 */
	@Test
	public void testCustomControlWithDatabindingContextPolymorphic() {
		BaseClass o1 = createBaseClassObject();
		CustomDialogControlFactoryForTests factory = new CustomDialogControlFactoryForTests(getCompositeParameter(), getEObjectParameter(o1));
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertTextEditable(control, false);
		assertText(control, "");
		o1.setBaseClassFeature("Foo");
		assertText(control, "Foo");
	}

	/**
	 * Test the polymorphic method pattern
	 * 
	 * <pre>
	 * ControlObservablePair control_ClassName_FeatureName(EStructuralFeature)
	 * </pre>
	 * 
	 * The programmer is responsible of creating and returning the Control
	 * and the target observable value into a ControlObservablePair
	 */
	@Test
	public void testCustomControlWithControlObservablePair() {
		final BaseClass o1 = createBaseClassObject();
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(o1)) {
			public ControlObservablePair control_BaseClass_baseClassFeature(EStructuralFeature f) {
				var text = createText("");
				// by default the editable is true, thus setting it to false
				// gives us evidence that this method was called
				text.setEditable(false);
				return new ControlObservablePair(text, DatabindingUtil.observeText(text, SWT.Modify));
			}
		};
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertTextEditable(control, false);
		assertText(control, "");
		o1.setBaseClassFeature("Foo");
		assertText(control, "Foo");
	}

	/**
	 * As above, but for a multi feature
	 */
	@Test
	public void testCustomControlWithControlObservablePairForMulti() {
		final BaseClass o1 = createBaseClassObject();
		o1.getBaseMultiReferenceFeature().add(fixtures.createClassWithName("Foo"));
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(o1)) {
			public ControlObservablePair control_BaseClass_baseMultiReferenceFeature(EStructuralFeature f) {
				var text = createText("");
				return new ControlObservablePair(text, DatabindingUtil.observeText(text, SWT.Modify));
			}
		};
		injectMembers(factory);
		// we create a Text for a multi feature so the text will stay empty anyway
		// but we test that our custom method is actually polymorphically invoked
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseMultiReferenceFeature());
		assertTextEditable(control, true);
		assertText(control, "");
		o1.getBaseMultiReferenceFeature().add(fixtures.createClassWithName("Bar"));
		assertText(control, "");
	}

	/**
	 * As above, but for a single feature without specifying SWT.Modify
	 */
	@Test
	public void testCustomControlWithControlObservablePair2() {
		final BaseClass o1 = createBaseClassObject();
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(o1)) {
			public ControlObservablePair control_BaseClass_baseClassFeature(EStructuralFeature f) {
				var text = createText("");
				// by default the editable is true, thus setting it to false
				// gives us evidence that this method was called
				text.setEditable(false);
				return new ControlObservablePair(text, DatabindingUtil.observeText(text));
			}
		};
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertTextEditable(control, false);
		assertText(control, "");
		o1.setBaseClassFeature("Foo");
		assertText(control, "Foo");
	}

	/**
	 * As above, but using the two args signature
	 */
	@Test
	public void testCustomControlWithFeatureAndObservableValue() {
		BaseClass o1 = createBaseClassObject();
		CustomDialogControlFactoryForTestsWithCallToBindValude factory = new CustomDialogControlFactoryForTestsWithCallToBindValude(getCompositeParameter(), getEObjectParameter(o1));
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertTextEditable(control, false);
		assertText(control, "");
		o1.setBaseClassFeature("Foo");
		assertText(control, "Foo");
	}

	@Test
	public void testCustomControlWithControlObservablePairAndLayout() {
		// check that the layout data explicitly set is not overwritten
		// by the default control setup
		final BaseClass o1 = createBaseClassObject();
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(o1)) {
			public ControlObservablePair control_BaseClass_baseClassFeature(EStructuralFeature f) {
				var text = createText("");
				text.setLayoutData(new FillLayout());
				return new ControlObservablePair(text, DatabindingUtil.observeText(text));
			}
		};
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertText(control, "");
		syncExecVoid(() -> {
			assertTrue("expected FillLayout but was " + control.getLayoutData().getClass().getSimpleName(),
					control.getLayoutData() instanceof FillLayout);
		});
	}

	/**
	 * The Control in the ControlObservablePair is set to null
	 */
	@Test
	public void testCustomControlNull() {
		final BaseClass o1 = createBaseClassObject();
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(o1)) {
			public ControlObservablePair control_BaseClass_baseClassFeature(EStructuralFeature f) {
				return new ControlObservablePair(null, null);
			}
		};
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertNull(control);
	}

	/**
	 * Test the polymorphic method pattern
	 * 
	 * <pre>
	 * observe_ClassName_FeatureName(EditingDomain, ClassName)
	 * </pre>
	 * 
	 * The programmer can customize which feature of which object to observe.
	 * In this test we create a Text on an EObject for a feature but we
	 * observe the same feature on another EObject (of the same type).
	 */
	@Test
	public void testCustomObservePolymorphic() {
		final BaseClass o1 = createBaseClassObject();
		final BaseClass o2 = createBaseClassObject();

		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(o1)) {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public org.eclipse.core.databinding.observable.value.IObservableValue observe_BaseClass_baseClassFeature(
					EditingDomain d, BaseClass e) {
				// observe the change for the feature on the second object
				return EMFProperties.value(fixtures.getTestPackage().getBaseClass_BaseClassFeature()).observe(o2);
			}
		};
		injectMembers(factory);
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		assertTextEditable(control, true);
		// the Text changes when the second object changes,
		// although the Text was created on the first object
		assertText(control, "");
		o1.setBaseClassFeature("Foo");
		assertText(control, "");
		o2.setBaseClassFeature("Foo");
		assertText(control, "Foo");
	}

	public static class CustomProposalCreator extends ProposalCreator {
		public List<String> proposals_BaseClass_baseClassFeature(BaseClass e) {
			return Arrays.asList("First Proposal", "Second Proposal");
		}
	}

	@Test
	public void testWrongContentAssistKeyStroke() {
		DialogControlFactory factory = new DialogControlFactory(getCompositeParameter(), getEObjectParameter(createBaseClassObject()));
		injectMembers(factory);
		// this will replace the string for content assist shortcut with
		// an unparsable KeyStroke

		Injector injector = createInjector(new EmfParsleyGuiceModuleForTesting() {
			@Override
			public String valueContentAssistShortcut() {
				return "Foo+Space";
			}

			@Override
			public Class<? extends ProposalCreator> bindProposalCreator() {
				return CustomDialogControlFactoryTest.CustomProposalCreator.class;
			}
		});
		injector.injectMembers(factory);
		// during the parsing of the KeyStroke an exception will be logged
		Control control = createControl(factory, fixtures.getTestPackage().getBaseClass_BaseClassFeature());
		// but the Text will be created anyway (without ContentProposalAdapter)
		assertTextEditable(control, true);
		logAppender.assertContainsMessage("Error while parsing keystroke: Foo+Space");
	}

	private BaseClass createBaseClassObject() {
		return fixtures.getTestFactory().createBaseClass();
	}
}
