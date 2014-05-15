/**
 */
package org.eclipse.emf.parsley.examples.cdo.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>VAT</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.examples.cdo.company.CompanyPackage#getVAT()
 * @model
 * @generated
 */
public enum VAT implements Enumerator {
	/**
	 * The '<em><b>Vat0</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VAT0_VALUE
	 * @generated
	 * @ordered
	 */
	VAT0(0, "vat0", "vat0"),

	/**
	 * The '<em><b>Vat7</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VAT7_VALUE
	 * @generated
	 * @ordered
	 */
	VAT7(7, "vat7", "vat7"),

	/**
	 * The '<em><b>Vat15</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VAT15_VALUE
	 * @generated
	 * @ordered
	 */
	VAT15(15, "vat15", "vat15");

	/**
	 * The '<em><b>Vat0</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Vat0</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VAT0
	 * @model name="vat0"
	 * @generated
	 * @ordered
	 */
	public static final int VAT0_VALUE = 0;

	/**
	 * The '<em><b>Vat7</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Vat7</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VAT7
	 * @model name="vat7"
	 * @generated
	 * @ordered
	 */
	public static final int VAT7_VALUE = 7;

	/**
	 * The '<em><b>Vat15</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Vat15</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VAT15
	 * @model name="vat15"
	 * @generated
	 * @ordered
	 */
	public static final int VAT15_VALUE = 15;

	/**
	 * An array of all the '<em><b>VAT</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final VAT[] VALUES_ARRAY =
		new VAT[] {
			VAT0,
			VAT7,
			VAT15,
		};

	/**
	 * A public read-only list of all the '<em><b>VAT</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<VAT> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>VAT</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VAT get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VAT result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>VAT</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VAT getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VAT result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>VAT</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VAT get(int value) {
		switch (value) {
			case VAT0_VALUE: return VAT0;
			case VAT7_VALUE: return VAT7;
			case VAT15_VALUE: return VAT15;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private VAT(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //VAT
