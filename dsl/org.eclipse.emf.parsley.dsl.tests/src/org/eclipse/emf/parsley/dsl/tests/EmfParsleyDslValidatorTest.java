/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests;

import static org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslValidator.DUPLICATE_ELEMENT;
import static org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslValidator.FINAL_FIELD_NOT_INITIALIZED;
import static org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslValidator.TOO_LITTLE_TYPE_INFORMATION;
import static org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslValidator.TYPE_MISMATCH;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView;
import org.eclipse.ui.IViewPart;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.validation.IssueCodes;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslValidatorTest extends EmfParsleyDslAbstractTest {

	@Inject
	private ParseHelper<Model> parseHelper;

	@Inject
	private ValidationTestHelper validationTestHelper;

	@Test
	public void testViewSpecificationIsNotIViewPart() throws Exception {
		assertTypeMismatch(
			parseModel(inputsWithErrors.viewSpecificationIsNotIViewPart()),
			ModelPackage.eINSTANCE.getViewSpecification(),
			IViewPart.class,
			Library.class
		);
	}

	@Test
	public void testNotAnEObjectInFeatureCaptionProvider() throws Exception {
		var model = parseModel(inputsWithErrors.notAnEObjectInFeatureCaptionProvider());
		assertTypeMismatch(
			model,
			ModelPackage.eINSTANCE.getEmfFeatureAccess(),
			EObject.class,
			AbstractSaveableTreeView.class
		);
		assertTypeMismatch(
			model,
			ModelPackage.eINSTANCE.getEmfFeatureAccess(),
			EObject.class,
			List.class
		);
	}

	@Test
	public void testNotAnEObjectInFeaturesProvider() throws Exception {
		assertTypeMismatch(
			parseModel(inputsWithErrors.notAnEObjectInFeaturesProvider()),
			ModelPackage.eINSTANCE.getEmfFeatureAccess(),
			EObject.class,
			List.class
		);
	}

	@Test
	public void testNotValidModuleExtends() throws Exception {
		assertTypeMismatch(
			parseModel(inputsWithErrors.notValidModuleExtends()),
			ModelPackage.eINSTANCE.getExtendsClause(),
			EmfParsleyJavaGuiceModule.class,
			Library.class
		);
	}

	@Test
	public void testNotValidLabelProviderExtends() throws Exception {
		assertExtendsTypeMismatch("labelProvider", ViewerLabelProvider.class);
	}

	@Test
	public void testNotValidTableLabelProviderExtends() throws Exception {
		assertExtendsTypeMismatch("tableLabelProvider", TableColumnLabelProvider.class);
	}

	@Test
	public void testNotValidFeatureCaptionProviderExtends() throws Exception {
		assertExtendsTypeMismatch("featureCaptionProvider", FeatureCaptionProvider.class);
	}

	@Test
	public void testNotValidFormFeatureCaptionProviderExtends() throws Exception {
		assertExtendsTypeMismatch("formFeatureCaptionProvider", FormFeatureCaptionProvider.class);
	}

	@Test
	public void testNotValidDialogFeatureCaptionProviderExtends() throws Exception {
		assertExtendsTypeMismatch("dialogFeatureCaptionProvider", DialogFeatureCaptionProvider.class);
	}

	@Test
	public void testNotValidFeaturesProviderExtends() throws Exception {
		assertExtendsTypeMismatch("featuresProvider", FeaturesProvider.class);
	}

	@Test
	public void testNotValidTableFeaturesProviderExtends() throws Exception {
		assertExtendsTypeMismatch("tableFeaturesProvider", TableFeaturesProvider.class);
	}

	@Test
	public void testNotValidFormControlFactoryExtends() throws Exception {
		assertExtendsTypeMismatch("formControlFactory", FormControlFactory.class);
	}

	@Test
	public void testNotValidDialogControlFactoryExtends() throws Exception {
		assertExtendsTypeMismatch("dialogControlFactory", DialogControlFactory.class);
	}

	@Test
	public void testNotValidProposalsExtends() throws Exception {
		assertExtendsTypeMismatch("proposals", ProposalCreator.class);
	}

	@Test
	public void testNotValidViewerContentProviderExtends() throws Exception {
		assertExtendsTypeMismatch("viewerContentProvider", ViewerContentProvider.class);
	}

	@Test
	public void testNotValidTableViewerContentProviderExtends() throws Exception {
		assertExtendsTypeMismatch("tableViewerContentProvider", TableViewerContentProvider.class);
	}

	@Test
	public void testNotValidMenuBuilderExtends() throws Exception {
		assertExtendsTypeMismatch("menuBuilder", EditingMenuBuilder.class);
	}

	@Test
	public void testNotValidConfiguratorExtends() throws Exception {
		assertExtendsTypeMismatch("configurator", Configurator.class);
	}

	@Test
	public void testNotValidResourceManagerExtends() throws Exception {
		assertExtendsTypeMismatch("resourceManager", ResourceManager.class);
	}

	@Test
	public void testModuleExtendsItself() throws Exception {
		assertHierarchyCycle(
			parseHelper.parse("""
				module my.first extends my.first.FirstEmfParsleyGuiceModule {
				}
				"""),
			ModelPackage.eINSTANCE.getModule(),
			"FirstEmfParsleyGuiceModule"
		);
	}

	@Test
	public void testModuleCycleInHierarchy() throws Exception {
		var m1 = parseHelper.parse("""
			module my.first extends my.second.SecondEmfParsleyGuiceModule {
			}
			""");
		
		var m2 = parseHelper.parse("""
			module my.second extends my.third.ThirdEmfParsleyGuiceModule {
			}
			""", m1.eResource().getResourceSet());
		
		var m3 = parseHelper.parse("""
			module my.third extends my.first.FirstEmfParsleyGuiceModule {
			}
			""", m2.eResource().getResourceSet());
		
		assertHierarchyCycle(m1, ModelPackage.eINSTANCE.getModule(), "FirstEmfParsleyGuiceModule");
		assertHierarchyCycle(m2, ModelPackage.eINSTANCE.getModule(), "SecondEmfParsleyGuiceModule");
		assertHierarchyCycle(m3, ModelPackage.eINSTANCE.getModule(), "ThirdEmfParsleyGuiceModule");
	}

	@Test
	public void testLabelProviderCycleInHierarchy() throws Exception {
		var m1 = parseHelper.parse("""
			module my.first {
				labelProvider extends my.second.ui.provider.SecondLabelProvider {}
			}
			""");
		
		var m2 = parseHelper.parse("""
			module my.second {
				labelProvider extends my.third.ui.provider.ThirdLabelProvider {}
			}
			""", m1.eResource().getResourceSet());
		
		var m3 = parseHelper.parse("""
			module my.third {
				labelProvider extends my.first.ui.provider.FirstLabelProvider {}
			}
			""", m2.eResource().getResourceSet());
		
		assertHierarchyCycle(m1, ModelPackage.eINSTANCE.getLabelProvider(), "FirstLabelProvider");
		assertHierarchyCycle(m2, ModelPackage.eINSTANCE.getLabelProvider(), "SecondLabelProvider");
		assertHierarchyCycle(m3, ModelPackage.eINSTANCE.getLabelProvider(), "ThirdLabelProvider");
	}

	@Test
	public void testTypeMismatchInFieldInitializer() throws Exception {
		validationTestHelper.assertError(
			parseHelper.parse("""
				import java.util.List
				
				module my.test {
					labelProvider {
						val List<Object> list = "foo"
					}
				}
				"""),
			XbasePackage.eINSTANCE.getXStringLiteral(),
			IssueCodes.INCOMPATIBLE_TYPES,
			"Type mismatch: cannot convert from String to List<Object>"
		);
	}

	@Test
	public void testMissingInitializerForFinalField() throws Exception {
		validationTestHelper.assertError(
			parseHelper.parse("""
				import java.util.List
				
				module my.test {
					labelProvider {
						val List<Object> list
					}
				}
				"""),
			ModelPackage.eINSTANCE.getFieldSpecification(),
			FINAL_FIELD_NOT_INITIALIZED,
			"The blank final field list may not have been initialized"
		);
	}

	@Test
	public void testMissingInitializerForFieldWithoutDeclaredType() throws Exception {
		validationTestHelper.assertError(
			parseHelper.parse("""
				module my.test {
					labelProvider {
						var list
					}
				}
				"""),
			ModelPackage.eINSTANCE.getFieldSpecification(),
			TOO_LITTLE_TYPE_INFORMATION,
			"The field list needs an explicit type since there is no initialization expression to infer the type from."
		);
	}

	@Test
	public void testWrongTypeBindingUsingClassReference() throws Exception {
		assertIncompatibleTypes(
			parseHelper.parse("""
				import org.eclipse.jface.viewers.ILabelProvider
				import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
				
				module my.empty {
					bindings {
						type ILabelProvider -> ViewerContentProvider
					}
				}
				"""),
			XbasePackage.eINSTANCE.getXFeatureCall(),
			"Class<? extends ILabelProvider>",
			"Class<ViewerContentProvider>"
		);
	}

	@Test
	public void testWrongTypeBindingUsingExpression() throws Exception {
		assertIncompatibleTypes(
			parseHelper.parse("""
				import org.eclipse.jface.viewers.ILabelProvider
				import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
				
				module my.empty {
					bindings {
						type ILabelProvider -> new ViewerLabelProvider(null)
					}
				}
				"""),
			XbasePackage.eINSTANCE.getXConstructorCall(),
			"Class<? extends ILabelProvider>",
			"ViewerLabelProvider"
		);
	}

	@Test
	public void testDuplicateTypeBinding() throws Exception {
		assertErrorMessages(
			parseHelper.parse("""
				import org.eclipse.jface.viewers.ILabelProvider
				import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
				
				module my.empty {
					bindings {
						type ILabelProvider -> ViewerLabelProvider
					}
					
					// this is to be considered a duplicate binding
					labelProvider {
					}
				}
				"""),
			"""
				Duplicate method bindILabelProvider() in type EmptyEmfParsleyGuiceModule
				Duplicate method bindILabelProvider() in type EmptyEmfParsleyGuiceModule"""
		);
	}

	@Test
	public void testDuplicateTypeBinding2() throws Exception {
		assertErrorMessages(
			parseHelper.parse("""
				import org.eclipse.jface.viewers.ILabelProvider
				import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
				
				module my.empty {
					bindings {
						type ILabelProvider -> ViewerLabelProvider
						type ILabelProvider -> ViewerLabelProvider
					}
					
					// this is to be considered a duplicate binding
					labelProvider {
					}
				}
				"""),
			"""
				Duplicate method bindILabelProvider() in type EmptyEmfParsleyGuiceModule
				Duplicate method bindILabelProvider() in type EmptyEmfParsleyGuiceModule
				Duplicate method bindILabelProvider() in type EmptyEmfParsleyGuiceModule"""
		);
	}

	@Test
	public void testDuplicateTypeBindingErrorPosition() throws Exception {
		var model = parseHelper.parse("""
			import org.eclipse.jface.viewers.ILabelProvider
			import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
			
			module my.empty {
				bindings {
					type ILabelProvider -> ViewerLabelProvider
				}
				
				// this is to be considered a duplicate binding
				labelProvider {
				}
			}
			""");
		assertDuplicateBinding(
			model,
			ModelPackage.eINSTANCE.getLabelProvider(),
			"bindILabelProvider"
		);
		assertDuplicateBinding(
			model,
			ModelPackage.eINSTANCE.getTypeBinding(),
			"bindILabelProvider"
		);
	}

	@Test
	public void testWrongProviderBindingUsingClassReference() throws Exception {
		assertIncompatibleTypes(
			parseHelper.parse("""
				import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
				
				module my.empty {
					bindings {
						provide AdapterFactoryEditingDomain -> AdapterFactoryEditingDomain
					}
				}
				"""),
			XbasePackage.eINSTANCE.getXFeatureCall(),
			"Class<? extends Provider<AdapterFactoryEditingDomain>>",
			"Class<AdapterFactoryEditingDomain>"
		);
	}

	@Test
	public void testDuplicateProviderBinding() throws Exception {
		assertErrorMessages(
			parseHelper.parse("""
				import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
				import org.eclipse.emf.parsley.edit.domain.DefaultAdapterFactoryEditingDomainProvider
				
				module my.empty {
					bindings {
						provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
						provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
					}
				}
				"""),
			"""
				Duplicate method provideAdapterFactoryEditingDomain() in type EmptyEmfParsleyGuiceModule
				Duplicate method provideAdapterFactoryEditingDomain() in type EmptyEmfParsleyGuiceModule"""
		);
	}

	@Test
	public void testDuplicateProviderBindingErrorPosition() throws Exception {
		assertDuplicateBinding(
			parseHelper.parse("""
				import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
				import org.eclipse.emf.parsley.edit.domain.DefaultAdapterFactoryEditingDomainProvider
				
				module my.empty {
					bindings {
						provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
						provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
					}
				}
				"""),
			ModelPackage.eINSTANCE.getProviderBinding(),
			"provideAdapterFactoryEditingDomain"
		);
	}

	@Test
	public void testDuplicateValueBinding() throws Exception {
		assertErrorMessages(
			parseHelper.parse("""
				import java.util.List

				module my.empty {
					bindings {
						value List<Integer> TableColumnWeights -> #[5, 2]
						value List<String> TableColumnWeights -> #["foo", "bar"]
					}
				}
				"""),
			"""
				Duplicate method valueTableColumnWeights() in type EmptyEmfParsleyGuiceModule
				Duplicate method valueTableColumnWeights() in type EmptyEmfParsleyGuiceModule"""
		);
	}

	@Test
	public void testDuplicateValueBindingErrorPosition() throws Exception {
		assertDuplicateBinding(
			parseHelper.parse("""
				import java.util.List

				module my.empty {
					bindings {
						value List<Integer> TableColumnWeights -> #[5, 2]
						value List<Integer> TableColumnWeights -> #[5, 2]
					}
				}
				"""),
			ModelPackage.eINSTANCE.getValueBinding(),
			"valueTableColumnWeights"
		);
	}

	@Test
	public void testNonCompliantValueBinding() throws Exception {
		validationTestHelper.assertError(
			parseHelper.parse("""
				module my.empty {
					bindings {
						// the correct type should be List<Integer>
						value Integer TableColumnWeights -> 5
					}
				}
				"""),
			ModelPackage.eINSTANCE.getValueBinding(),
			IssueCodes.INCOMPATIBLE_RETURN_TYPE,
			"The return type is incompatible with valueTableColumnWeights()"
		);
	}

	@Test
	public void testDuplicateSpecifications() throws Exception {
		var input = """
			import java.util.List
			import org.eclipse.emf.ecore.EClass
			import org.eclipse.emf.ecore.EObject
			import org.eclipse.emf.ecore.EStructuralFeature
			
			module my.empty {
				labelProvider {
					text {
						EClass -> ""
						EObject -> ""
						// we the parameter to distinguish
						// the two duplicate elements
						EClass c -> ""
						List<String> -> ""
					}
				}
				formFeatureCaptionProvider {
					text {
						EClass : name -> ""
						EStructuralFeature : transient -> ""
						// we ad an additional space before : to distinguish
						// the two duplicate elements
						EClass  : name -> ""
						EStructuralFeature : derived -> ""
					}
				}
			}
			""";
		assertErrorMessages(
			parseHelper.parse(input),
			"""
				Duplicate method text(EClass) in type EmptyLabelProvider
				Duplicate method text(EClass) in type EmptyLabelProvider
				Duplicate method text_EClass_name(EStructuralFeature) in type EmptyFormFeatureCaptionProvider
				Duplicate method text_EClass_name(EStructuralFeature) in type EmptyFormFeatureCaptionProvider"""
		);
	}

	@Test
	public void testDuplicateSpecificationsWithObservableTarget() throws Exception {
		var input = """
			import org.eclipse.emf.ecore.EClass
			
			module my.empty {
				formControlFactory {
					control {
						EClass : name -> { createLabel(parent, "") } target { observeText }
						// we ad an additional space before : to distinguish
						// the two duplicate elements
						EClass  : name -> { createLabel(parent, "") } target { observeText }
					}
				}
			}
			""";
		var model = parseHelper.parse(input);
		assertEquals(2, validationTestHelper.validate(model).size());
		assertDuplicateMethod(
			model,
			ModelPackage.eINSTANCE.getControlFactorySpecification(),
			input.indexOf("EClass : name ->"),
			"EClass : name -> { createLabel(parent, \"\") } target { observeText }".length()
		);
		assertDuplicateMethod(
			model,
			ModelPackage.eINSTANCE.getControlFactorySpecification(),
			input.indexOf("EClass  : name ->"),
			"EClass  : name -> { createLabel(parent, \"\") } target { observeText }".length()
		);
	}

	@Test
	public void testDuplicateFields() throws Exception {
		var input = """
			module my.empty {
				labelProvider {
					val int e1 = 0
					val int e2 = 0
					val String e1 = null
				}
				formFeatureCaptionProvider {
					val int f1 = 0
					val int f2 = 0
					val String f1 = null
				}
			}
			""";
		var model = parseHelper.parse(input);
		assertEquals(8, validationTestHelper.validate(model).size()); // 8 because there also inferred getters
		assertDuplicateField(
			model,
			ModelPackage.eINSTANCE.getFieldSpecification(),
			input.indexOf("e1"),
			"e1".length()
		);
		assertDuplicateField(
			model,
			ModelPackage.eINSTANCE.getFieldSpecification(),
			input.lastIndexOf("e1"),
			"e1".length()
		);
		assertDuplicateField(
			model,
			ModelPackage.eINSTANCE.getFieldSpecification(),
			input.indexOf("f1"),
			"f1".length()
		);
		assertDuplicateField(
			model,
			ModelPackage.eINSTANCE.getFieldSpecification(),
			input.lastIndexOf("f1"),
			"f1".length()
		);
	}

	@Test
	public void testDuplicateViewParts() throws Exception {
		var input = """
			import org.eclipse.emf.parsley.views.SaveableTreeFormView

			module my.empty {
				parts {
					viewpart myId1 {
						viewname "Test Model Tree Form View"
						viewclass SaveableTreeFormView
					}
					viewpart myId2 {
						viewname "Test Model Tree Form View 2"
						viewclass SaveableTreeFormView
					}
					// this is a duplicate since it has the same id
					viewpart myId1 {
						viewname "Test Model Tree Form View 3"
						viewclass SaveableTreeFormView
					}
				}
			}
			""";
		var model = parseHelper.parse(input);
		// the errors are 4 because in standalone tests we also get the
		// errors for checking duplicates across files
		// in fact the URIs look different in the standalone test
		// 4.assertEquals(validate.size)
		assertDuplicateElement(
			model,
			ModelPackage.eINSTANCE.getViewSpecification(),
			input.indexOf("myId1"),
			"myId1".length()
		);
		assertDuplicateElement(
			model,
			ModelPackage.eINSTANCE.getViewSpecification(),
			input.lastIndexOf("myId1"),
			"myId1".length()
		);
	}

	@Test
	public void testDuplicateViewPartsInDifferentFiles() throws Exception {
		var first = """
			import org.eclipse.emf.parsley.views.SaveableTreeFormView

			module my.empty1 {
				parts {
					viewpart myId1 {
						viewname "Test Model Tree Form View"
						viewclass SaveableTreeFormView
					}
				}
			}
			""";
		var firstModel = parseHelper.parse(first);
		var second = """
			import org.eclipse.emf.parsley.views.SaveableTreeFormView

			module my.empty2 {
				parts {
					viewpart myId1 {
						viewname "Test Model Tree Form View"
						viewclass SaveableTreeFormView
					}
				}
			}
			""";
		var secondModel = parseHelper.parse(second, firstModel.eResource().getResourceSet());

		assertEquals(1, validationTestHelper.validate(firstModel).size());
		validationTestHelper.assertError(
			firstModel,
			ModelPackage.eINSTANCE.getViewSpecification(),
			DUPLICATE_ELEMENT,
			first.indexOf("myId1"),
			"myId1".length(),
			"The part id myId1 is already defined"
		);
		assertEquals(1, validationTestHelper.validate(secondModel).size());
		validationTestHelper.assertError(
			secondModel,
			ModelPackage.eINSTANCE.getViewSpecification(),
			DUPLICATE_ELEMENT,
			second.indexOf("myId1"),
			"myId1".length(),
			"The part id myId1 is already defined"
		);
	}

	@Test
	public void testResouceManagerEmptySaveMethod() throws Exception {
		assertErrorMessages(
			parseHelper.parse("""
				module my.empty {
					resourceManager {
						saveResource {
						}
					}
				}
				"""),
			"Type mismatch: cannot convert from null to boolean"
		);
	}

	private void assertExtendsTypeMismatch(String keyword, Class<?> expectedType) throws Exception {
		// the wrong actual type is always Library in these tests
		assertTypeMismatch(
			parseModel("""
				import org.eclipse.emf.parsley.examples.library.Library
				
				module my.empty {
					%s extends Library {}
				}
				""".formatted(keyword)),
			ModelPackage.eINSTANCE.getExtendsClause(),
			expectedType,
			Library.class
		);
	}

	private void assertTypeMismatch(EObject e, EClass eClass, Class<?> expectedType, Class<?> actualType) {
		validationTestHelper.assertError(
			e,
			eClass,
			TYPE_MISMATCH,
			"Type mismatch: cannot convert from " + actualType.getSimpleName() +
				" to " + expectedType.getSimpleName()
		);
	}

	private void assertDuplicateBinding(EObject e, EClass eClass, String expectedMethodName) {
		validationTestHelper.assertError(
			e,
			eClass,
			IssueCodes.DUPLICATE_METHOD,
			"Duplicate method " + expectedMethodName
		);
	}

	private void assertDuplicateElement(EObject e, EClass eClass, int offset, int length) {
		validationTestHelper.assertError(
			e,
			eClass,
			DUPLICATE_ELEMENT,
			offset, length,
			"Duplicate element"
		);
	}

	private void assertDuplicateField(EObject e, EClass eClass, int offset, int length) {
		validationTestHelper.assertError(
			e,
			eClass,
			IssueCodes.DUPLICATE_FIELD,
			offset, length,
			"Duplicate field"
		);
	}

	private void assertDuplicateMethod(EObject e, EClass eClass, int offset, int length) {
		validationTestHelper.assertError(
			e,
			eClass,
			IssueCodes.DUPLICATE_METHOD,
			offset, length,
			"Duplicate method"
		);
	}

	private void assertIncompatibleTypes(EObject e, EClass eClass, String expectedType, String actualType) {
		validationTestHelper.assertError(
			e,
			eClass,
			IssueCodes.INCOMPATIBLE_TYPES,
			"Type mismatch: cannot convert from " + actualType +
				" to " + expectedType
		);
	}

	private void assertHierarchyCycle(EObject e, EClass type, String className) {
		validationTestHelper.assertError(
			e,
			type,
			IssueCodes.CYCLIC_INHERITANCE,
			"The inheritance hierarchy of " + className + " contains cycles"
		);
	}

	private void assertErrorMessages(EObject elem, CharSequence expected) {
		assertEqualsStrings(
			expected.toString().trim(),
			validationTestHelper.validate(elem).stream()
				.filter(issue -> issue.getSeverity() == Severity.ERROR)
				.map(Issue::getMessage)
				.reduce((a, b) -> a + "\n" + b)
				.orElse("")
		);
	}
}
