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
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.binding.DialogControlFactory
import org.eclipse.emf.parsley.binding.FormControlFactory
import org.eclipse.emf.parsley.binding.ProposalCreator
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.factories.TreeFormFactory
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView
import org.eclipse.ui.IViewPart
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslValidator.*
import java.util.List
import org.eclipse.xtext.xbase.XbasePackage
import org.eclipse.xtext.xbase.validation.IssueCodes
import org.eclipse.xtext.diagnostics.Severity

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
				EmfParsleyGuiceModule,
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
	def void testNotValidTreeFormFactoryExtends() {
		"treeFormFactory".
			assertExtendsTypeMismatch(TreeFormFactory)
	}

	@Test
	def void testModuleExtendsItself() {
		'''
		module my.first extends my.first.EmfParsleyGuiceModuleGen {
		}
		'''.parse.assertHierarchyCycle("EmfParsleyGuiceModuleGen")
	}

	@Test
	def void testModuleCycleInHierarchy() {
		val m1 = '''
		module my.first extends my.second.EmfParsleyGuiceModuleGen {
		}
		'''.parse
		
		val m2 = '''
		module my.second extends my.third.EmfParsleyGuiceModuleGen {
		}
		'''.parse(m1.eResource.resourceSet)
		
		val m3 = '''
		module my.third extends my.first.EmfParsleyGuiceModuleGen {
		}
		'''.parse(m2.eResource.resourceSet)
		
		val className = "EmfParsleyGuiceModuleGen"
		
		m1.assertHierarchyCycle(className)
		m2.assertHierarchyCycle(className)
		m3.assertHierarchyCycle(className)
	}

	@Test
	def void testLabelProviderCycleInHierarchy() {
		val m1 = '''
		module my.first {
			labelProvider extends my.second.ui.provider.LabelProviderGen {}
		}
		'''.parse
		
		val m2 = '''
		module my.second {
			labelProvider extends my.third.ui.provider.LabelProviderGen {}
		}
		'''.parse(m1.eResource.resourceSet)
		
		val m3 = '''
		module my.third {
			labelProvider extends my.first.ui.provider.LabelProviderGen {}
		}
		'''.parse(m2.eResource.resourceSet)
		
		val className = "LabelProviderGen"
		
		m1.assertHierarchyCycle(className)
		m2.assertHierarchyCycle(className)
		m3.assertHierarchyCycle(className)
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