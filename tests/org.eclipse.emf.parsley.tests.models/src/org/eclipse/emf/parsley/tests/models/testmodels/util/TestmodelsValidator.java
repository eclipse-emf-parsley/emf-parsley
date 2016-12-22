/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.util;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.parsley.tests.models.testmodels.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
 * @generated
 */
public class TestmodelsValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final TestmodelsValidator INSTANCE = new TestmodelsValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.parsley.tests.models.testmodels";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelsValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return TestmodelsPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case TestmodelsPackage.TEST_CONTAINER:
				return validateTestContainer((TestContainer)value, diagnostics, context);
			case TestmodelsPackage.TEST_ECLASS:
				return validateTestEClass((TestEClass)value, diagnostics, context);
			case TestmodelsPackage.TEST_ECLASS_FOR_FEATURE_NAME:
				return validateTestEClassForFeatureName((TestEClassForFeatureName)value, diagnostics, context);
			case TestmodelsPackage.ABASE_CLASS:
				return validateABaseClass((ABaseClass)value, diagnostics, context);
			case TestmodelsPackage.BASE_CLASS:
				return validateBaseClass((BaseClass)value, diagnostics, context);
			case TestmodelsPackage.DERIVED_CLASS:
				return validateDerivedClass((DerivedClass)value, diagnostics, context);
			case TestmodelsPackage.DERIVED_DERIVED_CLASS:
				return validateDerivedDerivedClass((DerivedDerivedClass)value, diagnostics, context);
			case TestmodelsPackage.MULTIPLE_INHERITANCE_CLASS:
				return validateMultipleInheritanceClass((MultipleInheritanceClass)value, diagnostics, context);
			case TestmodelsPackage.CLASS_FOR_CONTROLS:
				return validateClassForControls((ClassForControls)value, diagnostics, context);
			case TestmodelsPackage.CLASS_WITH_NAME:
				return validateClassWithName((ClassWithName)value, diagnostics, context);
			case TestmodelsPackage.BASE_CLASS_FOR_FEATURE_MAP_ENTRY:
				return validateBaseClassForFeatureMapEntry((BaseClassForFeatureMapEntry)value, diagnostics, context);
			case TestmodelsPackage.CLASS_FOR_FEATURE_MAP_ENTRY1:
				return validateClassForFeatureMapEntry1((ClassForFeatureMapEntry1)value, diagnostics, context);
			case TestmodelsPackage.CLASS_FOR_FEATURE_MAP_ENTRY2:
				return validateClassForFeatureMapEntry2((ClassForFeatureMapEntry2)value, diagnostics, context);
			case TestmodelsPackage.CLASS_FOR_TABLE:
				return validateClassForTable((ClassForTable)value, diagnostics, context);
			case TestmodelsPackage.CLASS_FOR_VALIDATION:
				return validateClassForValidation((ClassForValidation)value, diagnostics, context);
			case TestmodelsPackage.CLASS_FOR_DEFAULT_VALIDATION:
				return validateClassForDefaultValidation((ClassForDefaultValidation)value, diagnostics, context);
			case TestmodelsPackage.CLASS_FOR_COMPARE:
				return validateClassForCompare((ClassForCompare)value, diagnostics, context);
			case TestmodelsPackage.ENUM_FOR_CONTROLS:
				return validateEnumForControls((EnumForControls)value, diagnostics, context);
			case TestmodelsPackage.BOOLEAN_DATA_TYPE:
				return validateBooleanDataType((Boolean)value, diagnostics, context);
			case TestmodelsPackage.BOOLEAN_PRIMITIVE_DATA_TYPE:
				return validateBooleanPrimitiveDataType((Boolean)value, diagnostics, context);
			case TestmodelsPackage.STRING_DATA_TYPE:
				return validateStringDataType((String)value, diagnostics, context);
			case TestmodelsPackage.UNSERIALIZABLE_STRING_DATA_TYPE:
				return validateUnserializableStringDataType((String)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestContainer(TestContainer testContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(testContainer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestEClass(TestEClass testEClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(testEClass, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestEClassForFeatureName(TestEClassForFeatureName testEClassForFeatureName, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(testEClassForFeatureName, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateABaseClass(ABaseClass aBaseClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(aBaseClass, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaseClass(BaseClass baseClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(baseClass, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDerivedClass(DerivedClass derivedClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(derivedClass, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDerivedDerivedClass(DerivedDerivedClass derivedDerivedClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(derivedDerivedClass, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultipleInheritanceClass(MultipleInheritanceClass multipleInheritanceClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(multipleInheritanceClass, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassForControls(ClassForControls classForControls, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classForControls, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassWithName(ClassWithName classWithName, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classWithName, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaseClassForFeatureMapEntry(BaseClassForFeatureMapEntry baseClassForFeatureMapEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(baseClassForFeatureMapEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassForFeatureMapEntry1(ClassForFeatureMapEntry1 classForFeatureMapEntry1, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classForFeatureMapEntry1, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassForFeatureMapEntry2(ClassForFeatureMapEntry2 classForFeatureMapEntry2, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classForFeatureMapEntry2, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassForTable(ClassForTable classForTable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classForTable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassForValidation(ClassForValidation classForValidation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(classForValidation, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(classForValidation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(classForValidation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(classForValidation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(classForValidation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(classForValidation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(classForValidation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(classForValidation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(classForValidation, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassForValidation_NotEmpty(classForValidation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NotEmpty constraint of '<em>Class For Validation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateClassForValidation_NotEmpty(ClassForValidation classForValidation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String notEmpty = classForValidation.getNotEmpty();
		if (notEmpty == null || notEmpty.length() == 0) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic(
						Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						"the field 'notEmpty' cannot be empty",
						new Object[] { classForValidation, TestmodelsPackage.Literals.CLASS_FOR_VALIDATION__NOT_EMPTY }
						));
			}
			return false;
		} else {
			if (notEmpty.length() == 1) {
				if (diagnostics != null) {
					diagnostics.add
						(new BasicDiagnostic
							(Diagnostic.WARNING,
							DIAGNOSTIC_SOURCE,
							0,
							"the field 'notEmpty' should be more than one character",
							new Object[] { classForValidation, TestmodelsPackage.Literals.CLASS_FOR_VALIDATION__NOT_EMPTY }
							));
				}
			} else if (notEmpty.length() == 2) {
				if (diagnostics != null) {
					diagnostics.add
						(new BasicDiagnostic
							(Diagnostic.INFO,
							DIAGNOSTIC_SOURCE,
							0,
							"the field 'notEmpty' is two characters long",
							new Object[] { classForValidation, TestmodelsPackage.Literals.CLASS_FOR_VALIDATION__NOT_EMPTY }
							));
				}
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassForDefaultValidation(ClassForDefaultValidation classForDefaultValidation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classForDefaultValidation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassForCompare(ClassForCompare classForCompare, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classForCompare, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnumForControls(EnumForControls enumForControls, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanDataType(Boolean booleanDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanPrimitiveDataType(boolean booleanPrimitiveDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringDataType(String stringDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUnserializableStringDataType(String unserializableStringDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //TestmodelsValidator
