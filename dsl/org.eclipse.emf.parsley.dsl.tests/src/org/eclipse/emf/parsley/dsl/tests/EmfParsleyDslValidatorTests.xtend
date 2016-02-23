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
package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.composite.DialogControlFactory
import org.eclipse.emf.parsley.composite.FormControlFactory
import org.eclipse.emf.parsley.composite.ProposalCreator
import org.eclipse.emf.parsley.config.Configurator
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.resource.ResourceManager
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView
import org.eclipse.ui.IViewPart
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.xbase.XbasePackage
import org.eclipse.xtext.xbase.validation.IssueCodes
import org.junit.Test
import org.junit.runner.RunWith

import static org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslValidator.*

import static extension org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslValidatorTests extends EmfParsleyDslAbstractTests {

	@Inject extension ParseHelper<Model>
 	@Inject extension ValidationTestHelper

	@Test
	def void testViewSpecificationIsNotIViewPart() {
		inputsWithErrors.viewSpecificationIsNotIViewPart.parseModel.
		assertTypeMismatch(
			ModelPackage.eINSTANCE.viewSpecification,
			IViewPart,
			Library
		)
	}

	@Test
	def void testNotAnEObjectInFeatureCaptionProvider() {
		inputsWithErrors.notAnEObjectInFeatureCaptionProvider.parseModel => [
			assertTypeMismatch(
				ModelPackage.eINSTANCE.emfFeatureAccess,
				EObject,
				AbstractSaveableTreeView
			)
			assertTypeMismatch(
				ModelPackage.eINSTANCE.emfFeatureAccess,
				EObject,
				List
			)
		]
	}

	@Test
	def void testNotAnEObjectInFeaturesProvider() {
		inputsWithErrors.notAnEObjectInFeaturesProvider.parseModel => [
			assertTypeMismatch(
				ModelPackage.eINSTANCE.emfFeatureAccess,
				EObject,
				List
			)
		]
	}

	@Test
	def void testNotValidModuleExtends() {
		inputsWithErrors.notValidModuleExtends.parseModel.
			assertTypeMismatch(
				ModelPackage.eINSTANCE.extendsClause,
				EmfParsleyJavaGuiceModule,
				Library
			)
	}

	@Test
	def void testNotValidLabelProviderExtends() {
		"labelProvider".
			assertExtendsTypeMismatch(ViewerLabelProvider)
	}

	@Test
	def void testNotValidTableLabelProviderExtends() {
		"tableLabelProvider".
			assertExtendsTypeMismatch(TableColumnLabelProvider)
	}

	@Test
	def void testNotValidFeatureCaptionProviderExtends() {
		"featureCaptionProvider".
			assertExtendsTypeMismatch(FeatureCaptionProvider)
	}

	@Test
	def void testNotValidFormFeatureCaptionProviderExtends() {
		"formFeatureCaptionProvider".
			assertExtendsTypeMismatch(FormFeatureCaptionProvider)
	}

	@Test
	def void testNotValidDialogFeatureCaptionProviderExtends() {
		"dialogFeatureCaptionProvider".
			assertExtendsTypeMismatch(DialogFeatureCaptionProvider)
	}

	@Test
	def void testNotValidFeaturesProviderExtends() {
		"featuresProvider".
			assertExtendsTypeMismatch(FeaturesProvider)
	}

	@Test
	def void testNotValidTableFeaturesProviderExtends() {
		"tableFeaturesProvider".
			assertExtendsTypeMismatch(TableFeaturesProvider)
	}

	@Test
	def void testNotValidFormControlFactoryExtends() {
		"formControlFactory".
			assertExtendsTypeMismatch(FormControlFactory)
	}

	@Test
	def void testNotValidDialogControlFactoryExtends() {
		"dialogControlFactory".
			assertExtendsTypeMismatch(DialogControlFactory)
	}

	@Test
	def void testNotValidProposalsExtends() {
		"proposals".
			assertExtendsTypeMismatch(ProposalCreator)
	}

	@Test
	def void testNotValidViewerContentProviderExtends() {
		"viewerContentProvider".
			assertExtendsTypeMismatch(ViewerContentProvider)
	}

	@Test
	def void testNotValidTableViewerContentProviderExtends() {
		"tableViewerContentProvider".
			assertExtendsTypeMismatch(TableViewerContentProvider)
	}

	@Test
	def void testNotValidMenuBuilderExtends() {
		"menuBuilder".
			assertExtendsTypeMismatch(EditingMenuBuilder)
	}

	@Test
	def void testNotValidConfiguratorExtends() {
		"configurator".
			assertExtendsTypeMismatch(Configurator)
	}

	@Test
	def void testNotValidResourceManagerExtends() {
		"resourceManager".
			assertExtendsTypeMismatch(ResourceManager)
	}

	@Test
	def void testModuleExtendsItself() {
		'''
		module my.first extends my.first.FirstEmfParsleyGuiceModule {
		}
		'''.parse.assertHierarchyCycle("FirstEmfParsleyGuiceModule")
	}

	@Test
	def void testModuleCycleInHierarchy() {
		val m1 = '''
		module my.first extends my.second.SecondEmfParsleyGuiceModule {
		}
		'''.parse
		
		val m2 = '''
		module my.second extends my.third.ThirdEmfParsleyGuiceModule {
		}
		'''.parse(m1.eResource.resourceSet)
		
		val m3 = '''
		module my.third extends my.first.FirstEmfParsleyGuiceModule {
		}
		'''.parse(m2.eResource.resourceSet)
		
		m1.assertHierarchyCycle("SecondEmfParsleyGuiceModule")
		m2.assertHierarchyCycle("ThirdEmfParsleyGuiceModule")
		m3.assertHierarchyCycle("FirstEmfParsleyGuiceModule")
	}

	@Test
	def void testLabelProviderCycleInHierarchy() {
		val m1 = '''
		module my.first {
			labelProvider extends my.second.ui.provider.SecondLabelProvider {}
		}
		'''.parse
		
		val m2 = '''
		module my.second {
			labelProvider extends my.third.ui.provider.ThirdLabelProvider {}
		}
		'''.parse(m1.eResource.resourceSet)
		
		val m3 = '''
		module my.third {
			labelProvider extends my.first.ui.provider.FirstLabelProvider {}
		}
		'''.parse(m2.eResource.resourceSet)
		
		m1.assertHierarchyCycle("SecondLabelProvider")
		m2.assertHierarchyCycle("ThirdLabelProvider")
		m3.assertHierarchyCycle("FirstLabelProvider")
	}

	@Test
	def void testTypeMismatchInFieldInitializer() {
		'''
		import java.util.List
		
		module my.test {
			labelProvider {
				val List<Object> list = "foo"
			}
		}
		'''.parse.assertError(
			XbasePackage.eINSTANCE.XStringLiteral,
			IssueCodes.INCOMPATIBLE_TYPES,
			"Type mismatch: cannot convert from String to List<Object>"
		)
	}

	@Test
	def void testMissingInitializerForFinalField() {
		'''
		import java.util.List
		
		module my.test {
			labelProvider {
				val List<Object> list
			}
		}
		'''.parse.assertError(
			ModelPackage.eINSTANCE.fieldSpecification,
			FINAL_FIELD_NOT_INITIALIZED,
			"The blank final field list may not have been initialized"
		)
	}

	@Test
	def void testMissingInitializerForFieldWithoutDeclaredType() {
		'''
		module my.test {
			labelProvider {
				var list
			}
		}
		'''.parse.assertError(
			ModelPackage.eINSTANCE.fieldSpecification,
			TOO_LITTLE_TYPE_INFORMATION,
			"The field list needs an explicit type since there is no initialization expression to infer the type from."
		)
	}

	@Test
	def void testWrongTypeBindingUsingClassReference() {
		'''
		import org.eclipse.jface.viewers.ILabelProvider
		import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
		
		module my.empty {
			bindings {
				type ILabelProvider -> ViewerContentProvider
			}
		}
		'''.parse.assertIncompatibleTypes(
			XbasePackage.eINSTANCE.XFeatureCall,
			"Class<? extends ILabelProvider>",
			"Class<ViewerContentProvider>"
		)
	}

	@Test
	def void testWrongTypeBindingUsingExpression() {
		'''
		import org.eclipse.jface.viewers.ILabelProvider
		import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
		
		module my.empty {
			bindings {
				type ILabelProvider -> new ViewerLabelProvider(null)
			}
		}
		'''.parse.assertIncompatibleTypes(
			XbasePackage.eINSTANCE.XConstructorCall,
			"Class<? extends ILabelProvider>",
			"ViewerLabelProvider"
		)
	}

	@Test
	def void testDuplicateTypeBinding() {
		'''
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
		'''.parse.assertErrorMessages(
'''
Duplicate binding for: Class<? extends ILabelProvider>
Duplicate binding for: Class<? extends ILabelProvider>
'''
		)
	}

	@Test
	def void testDuplicateTypeBinding2() {
		'''
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
		'''.parse.assertErrorMessages(
'''
Duplicate binding for: Class<? extends ILabelProvider>
Duplicate binding for: Class<? extends ILabelProvider>
Duplicate binding for: Class<? extends ILabelProvider>
'''
		)
	}

	@Test
	def void testDuplicateTypeBindingErrorPosition() {
		'''
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
		'''.parse => [
			assertDuplicateBinding(
				ModelPackage.eINSTANCE.labelProvider,
				"Class<? extends ILabelProvider>"
			)
			assertDuplicateBinding(
				ModelPackage.eINSTANCE.typeBinding,
				"Class<? extends ILabelProvider>"
			)
		]
	}

	@Test
	def void testWrongProviderBindingUsingClassReference() {
		'''
		import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
		
		module my.empty {
			bindings {
				provide AdapterFactoryEditingDomain -> AdapterFactoryEditingDomain
			}
		}
		'''.parse.assertIncompatibleTypes(
			XbasePackage.eINSTANCE.XFeatureCall,
			"Class<? extends Provider<AdapterFactoryEditingDomain>>",
			"Class<AdapterFactoryEditingDomain>"
		)
	}

	@Test
	def void testDuplicateProviderBinding() {
		'''
		import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
		import org.eclipse.emf.parsley.edit.domain.DefaultAdapterFactoryEditingDomainProvider
		
		module my.empty {
			bindings {
				provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
				provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
			}
		}
		'''.parse.assertErrorMessages(
'''
Duplicate binding for: Class<? extends Provider<AdapterFactoryEditingDomain>>
Duplicate binding for: Class<? extends Provider<AdapterFactoryEditingDomain>>
'''
		)
	}

	@Test
	def void testDuplicateProviderBindingErrorPosition() {
		'''
		import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
		import org.eclipse.emf.parsley.edit.domain.DefaultAdapterFactoryEditingDomainProvider
		
		module my.empty {
			bindings {
				provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
				provide AdapterFactoryEditingDomain -> DefaultAdapterFactoryEditingDomainProvider
			}
		}
		'''.parse.assertDuplicateBinding(
				ModelPackage.eINSTANCE.providerBinding,
				"Class<? extends Provider<AdapterFactoryEditingDomain>>"
			)
	}

	@Test
	def void testDuplicateValueBinding() {
		'''
		import java.util.List

		module my.empty {
			bindings {
				value List<Integer> TableColumnWeights -> #[5, 2]
				value List<String> TableColumnWeights -> #["foo", "bar"]
			}
		}
		'''.parse.assertErrorMessages(
'''
Duplicate binding for: TableColumnWeights
Duplicate binding for: TableColumnWeights
'''
		)
	}

	@Test
	def void testDuplicateValueBindingErrorPosition() {
		'''
		import java.util.List

		module my.empty {
			bindings {
				value List<Integer> TableColumnWeights -> #[5, 2]
				value List<Integer> TableColumnWeights -> #[5, 2]
			}
		}
		'''.parse.assertDuplicateBinding(
				ModelPackage.eINSTANCE.valueBinding,
				"TableColumnWeights"
			)
	}

	@Test
	def void testNonCompliantValueBinding() {
		'''
		import java.util.List

		module my.empty {
			bindings {
				// the correct type should be List<Integer>
				value Integer TableColumnWeights -> 5
			}
		}
		'''.parse.assertError(
			ModelPackage.eINSTANCE.valueBinding,
			NON_COMPLIANT_BINDING,
			"Incorrect value binding: Integer is not compliant with inherited binding's type List<Integer>"
		)
	}

	@Test
	def void testDuplicateSpecifications() {
		val input = '''
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
				'''
		input.parse => [
			4.assertEquals(validate.size)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.polymorphicSpecification,
				input.indexOf("EClass ->"), 'EClass -> ""'.length
			)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.polymorphicSpecification,
				input.indexOf("EClass c"), 'EClass c -> ""'.length
			)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.featureAssociatedExpression,
				input.indexOf("EClass  : name"), 'EClass  : name -> ""'.length
			)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.featureAssociatedExpression,
				input.indexOf("EClass : name"), 'EClass : name -> ""'.length
			)
		]
	}

	@Test
	def void testDuplicateSpecificationsWithObservableTarget() {
		val input = '''
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
				'''
		input.parse => [
			2.assertEquals(validate.size)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.controlFactorySpecification,
				input.indexOf("EClass : name ->"),
				'EClass : name -> { createLabel(parent, "") } target { observeText }'.length
			)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.controlFactorySpecification,
				input.indexOf("EClass  : name ->"),
				'EClass  : name -> { createLabel(parent, "") } target { observeText }'.length
			)
		]
	}

	@Test
	def void testDuplicateFields() {
		val input = '''
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
				'''
		input.parse => [
			4.assertEquals(validate.size)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.fieldSpecification,
				input.indexOf("val int e1"),
				'val int e1 = 0'.length
			)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.fieldSpecification,
				input.indexOf("val String e1"),
				'val String e1 = null'.length
			)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.fieldSpecification,
				input.indexOf("val int f1"),
				'val int f1 = 0'.length
			)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.fieldSpecification,
				input.indexOf("val String f1"),
				'val String f1 = null'.length
			)
		]
	}

	@Test
	def void testDuplicateViewParts() {
		val input = '''
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
				'''
		input.parse => [
			2.assertEquals(validate.size)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.viewSpecification,
				input.indexOf("myId1"),
				'myId1'.length
			)
			assertDuplicateElement(
				ModelPackage.eINSTANCE.viewSpecification,
				input.lastIndexOf("myId1"),
				'myId1'.length
			)
		]
	}

	@Test
	def void testResouceManagerEmptySaveMethod() {
		'''
		module my.empty {
			resourceManager {
				saveResource {
				}
			}
		}
		'''.
		parse.assertErrorMessages("Type mismatch: cannot convert from null to boolean")
	}

	def private assertExtendsTypeMismatch(String keyword, Class<?> expectedType) {
		// the wrong actual type is always Library in these tests
		'''
		import org.eclipse.emf.parsley.examples.library.Library
		
		module my.empty {
			«keyword» extends Library {}
		}
		'''.parseModel.assertTypeMismatch(
			ModelPackage.eINSTANCE.extendsClause,
			expectedType,
			Library
		)
	}

	def private assertTypeMismatch(EObject e, EClass eClass, Class<?> expectedType, Class<?> actualType) {
		e.assertError(
			eClass,
			TYPE_MISMATCH,
			"Type mismatch: cannot convert from " + actualType.simpleName +
				" to " + expectedType.simpleName
		)
	}

	def private assertDuplicateBinding(EObject e, EClass eClass, String expectedType) {
		e.assertError(
			eClass,
			DUPLICATE_BINDING,
			"Duplicate binding for: " + expectedType
		)
	}

	def private assertDuplicateElement(EObject e, EClass eClass, int offset, int length) {
		e.assertError(
			eClass,
			DUPLICATE_ELEMENT,
			offset, length,
			"Duplicate element"
		)
	}

	def private assertIncompatibleTypes(EObject e, EClass eClass, String expectedType, String actualType) {
		e.assertError(
			eClass,
			IssueCodes.INCOMPATIBLE_TYPES,
			"Type mismatch: cannot convert from " + actualType +
				" to " + expectedType
		)
	}

	def private assertHierarchyCycle(EObject e, String className) {
		e.assertError(
			ModelPackage.eINSTANCE.extendsClause,
			CYCLIC_INHERITANCE,
			'''The inheritance hierarchy of «className» contains cycles'''
		)
	}

	def private assertErrorMessages(EObject elem, CharSequence expected) {
		expected.toString.trim.assertEqualsStrings(
			elem.validate.filter[severity == Severity.ERROR].
				map[message].join("\n")
		)
	}
}