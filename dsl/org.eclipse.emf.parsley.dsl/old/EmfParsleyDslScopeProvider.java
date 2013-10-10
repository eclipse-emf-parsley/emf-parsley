/**
 * 
 */
package org.eclipse.emf.parsley.dsl.scoping;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess;
import org.eclipse.emf.parsley.dsl.model.FeatureSpecification;
import org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.xbase.annotations.scoping.XbaseWithAnnotationsScopeProvider;
import org.eclipse.xtext.xbase.scoping.LocalVariableScopeContext;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyDslScopeProvider extends XbaseWithAnnotationsScopeProvider {

	// public IScope __getScope(EObject context, EReference reference) {
	// if (context instanceof FeatureLabelSpecification
	// && TypesPackage.Literals.JVM_IDENTIFIABLE_ELEMENT
	// .isSuperTypeOf(reference.getEReferenceType())) {
	// IScope parent = super.getScope(context, reference);
	//
	// FeatureLabelSpecification featureLabelSpecification =
	// (FeatureLabelSpecification) context;
	// // JvmTypeReference parameterType = featureLabelSpecification
	// // .getParameterType();
	// JvmFeatureScopeAcceptor featureScopeDescriptions = new
	// JvmFeatureScopeAcceptor();
	// JvmDeclaredType type = (JvmDeclaredType) parameterType.getType();
	// addFeatureScopes(parameterType, context, type, null, null,
	// getDefaultPriority(), featureScopeDescriptions);
	// IScope result = featureScopeDescriptions.createScope(parent);
	// // IScope featureScopeForTypeRef = createFeatureScopeForTypeRef(
	// // parameterType, parameterType, null, IScope.NULLSCOPE);
	// // IScope result = createFeatureScopeForTypeRef(parameterType,
	// // featureLabelSpecification, null, IScope.NULLSCOPE);
	// return result;
	// // System.out.println(reference);
	// }
	//
	// return super.getScope(context, reference);
	// }

	@Override
	protected JvmDeclaredType getContextType(EObject obj) {
		if (obj instanceof EmfFeatureAccess) {
			EmfFeatureAccess featureAccess = (EmfFeatureAccess) obj;
			JvmType parameterType = featureAccess.getParameterType().getType();
			if (parameterType instanceof JvmDeclaredType) {
				return (JvmDeclaredType) parameterType;
			}
		}

		return super.getContextType(obj);
	}

	@Override
	protected IScope createLocalVarScope(IScope parentScope,
			LocalVariableScopeContext scopeContext) {
		parentScope = super.createLocalVarScope(parentScope, scopeContext);

		if (scopeContext != null && scopeContext.getContext() != null) {
			EObject context = scopeContext.getContext();
			if (context instanceof EmfFeatureAccess) {
				EmfFeatureAccess featureAccess = (EmfFeatureAccess) context;
				return new SimpleScope(parentScope,
						Collections.singleton(EObjectDescription.create(THIS,
								featureAccess.getParameterType().getType())));
			}
		}

		return parentScope;
	}

	@Override
	protected IScope createImplicitFeatureCallScope(EObject call,
			Resource resource, IScope parent, IScope localVariableScope) {
		IScope superScope = super.createImplicitFeatureCallScope(call,
				resource, parent, localVariableScope);

		return filterScope(call, superScope);
	}

	protected IScope filterScope(EObject call, IScope superScope) {
		boolean shouldFilter = false;
		EObject container = call.eContainer();
		if (container instanceof PropertyDescriptionSpecification) {
			PropertyDescriptionSpecification featureLabelSpecification = (PropertyDescriptionSpecification) container;
			if (featureLabelSpecification.getFeature() == call)
				shouldFilter = true;
		}
		if (container instanceof FeatureSpecification) {
			FeatureSpecification featureSpecification = (FeatureSpecification) container;
			if (featureSpecification.getFeatures().contains(call))
				shouldFilter = true;
		}

		if (!shouldFilter)
			return superScope;

		Iterable<IEObjectDescription> exportedObjects = superScope
				.getAllElements();

		// we filter the feature scope so that it contains only
		// the name of the features, no get/set/is method names
		// no static methods, etc.
		Iterable<IEObjectDescription> filtered = Iterables.filter(
				exportedObjects, new Predicate<IEObjectDescription>() {
					public boolean apply(IEObjectDescription input) {
						EObject element = input.getEObjectOrProxy();
						if (element instanceof JvmOperation) {
							JvmOperation operation = (JvmOperation) element;
							if (operation.isStatic())
								return false;
							if (isPropertyNameForGetterSetterMethod(input
									.toString()))
								return false;
							if (input.toString().contains("("))
								return false;
							return true;
						}
						return false;
					}
				});

		return new SimpleScope(filtered);
	}

	protected boolean isPropertyNameForGetterSetterMethod(String opName) {
		if ((opName.startsWith("get") || opName.startsWith("set"))
				&& opName.length() > 3
				&& Character.isUpperCase(opName.charAt(3)))
			return true;

		if (opName.startsWith("is") && opName.length() > 2
				&& Character.isUpperCase(opName.charAt(2)))
			return true;
		return false;
	}
}
