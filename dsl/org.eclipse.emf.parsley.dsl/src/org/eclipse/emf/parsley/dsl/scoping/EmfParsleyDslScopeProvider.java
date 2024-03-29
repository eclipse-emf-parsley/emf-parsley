/**
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.eclipse.emf.parsley.dsl.scoping;

import static org.eclipse.xtext.scoping.Scopes.scopedElementsFor;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.filter;

import java.beans.Introspector;
import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess;
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;

/**
 * This class contains custom scoping description.
 * 
 * See
 * https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
public class EmfParsleyDslScopeProvider extends AbstractEmfParsleyDslScopeProvider {
	@Override
	public IScope getScope(final EObject context, final EReference reference) {
		var scope = createCustomScope(context, reference);
		if (scope != null) {
			return scope;
		}
		return super.getScope(context, reference);
	}

	public IScope createCustomScope(final EObject context, final EReference reference) {
		EClassifier eType = reference.getEType();
		EClass jvmMember = TypesPackage.eINSTANCE.getJvmMember();
		if (Objects.equals(eType, jvmMember)) {
			EmfFeatureAccess containingEmfFeatureAccess = EmfParsleyDslModelUtil
					.containingEmfFeatureAccess(context);
			JvmTypeReference parameterType = null;
			if (containingEmfFeatureAccess != null) {
				parameterType = containingEmfFeatureAccess.getParameterType();
			}
			JvmType type = null;
			if (parameterType != null) {
				type = parameterType.getType();
			}
			return customScope(type);
		}
		return null;
	}

	protected IScope customScope(final JvmType jvmType) {
		if (jvmType instanceof JvmGenericType) {
			// a JvmMember does not have 'name', but 'simpleName'
			// thus we must also provide a function for computing the
			// QualifiedName (the default one relies on 'name')
			JvmGenericType t = (JvmGenericType) jvmType;
			var jvmOperations = filter(t.getAllFeatures(), JvmOperation.class);
			var nonStaticOperations = filter(jvmOperations, it -> !it.isStatic());
			var scopedElementsFor = scopedElementsFor(nonStaticOperations, it -> {
				if (isGetterMethod(it.getSimpleName())) {
					return QualifiedName.create(getPropertyNameForGetterMethod(it.getSimpleName()));
				}
				return null;
			});
			return new SimpleScope(scopedElementsFor);
		}
		return null;
	}

	private boolean isGetterMethod(final String opName) {
		return getterMethodForNonBoolean(opName) || getterMethodForBoolean(opName);
	}

	private String getPropertyNameForGetterMethod(final String opName) {
		if (getterMethodForNonBoolean(opName)) {
			return Introspector.decapitalize(opName.substring(3));
		}
		return Introspector.decapitalize(opName.substring(2));
	}

	private boolean getterMethodForBoolean(final String opName) {
		return getterMethodInternal(opName, "is");
	}

	private boolean getterMethodForNonBoolean(final String opName) {
		return getterMethodInternal(opName, "get");
	}

	private boolean getterMethodInternal(final String opName, final String prefix) {
		return (opName.startsWith(prefix) && opName.length() > prefix.length()
				&& Character.isUpperCase(opName.charAt(prefix.length())));
	}
}
