/**
 * 
 */
package org.eclipse.emf.parsley.dsl.scoping;

import com.google.common.collect.Iterables
import com.google.inject.Inject
import java.util.Collections
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess
import org.eclipse.emf.parsley.dsl.model.FeatureSpecification
import org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.resource.EObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.impl.SimpleScope
import org.eclipse.xtext.xbase.annotations.scoping.XbaseWithAnnotationsScopeProvider
import org.eclipse.xtext.xbase.scoping.LocalVariableScopeContext

/**
 * This class contains custom scoping description. XbaseScopeProvider is
 * NOT used by the Xbase validator: it is used only by the content assist,
 * that's why it is still here, see
 * http://www.eclipse.org/forums/index.php/t/476486/
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyDslScopeProvider extends XbaseWithAnnotationsScopeProvider {
	
	@Inject extension EmfParsleyDslScopeProviderHelper
	
	override getScope(EObject context, EReference reference) {
		val scope = context.createCustomScope(reference)
		
		if (scope != null)
			return scope;
		super.getScope(context, reference);
	}

	override protected JvmDeclaredType getContextType(EObject obj) {
		if (obj instanceof EmfFeatureAccess) {
			val featureAccess = obj as EmfFeatureAccess;
			val parameterType = featureAccess.getParameterType().getType();
			if (parameterType instanceof JvmDeclaredType) {
				return parameterType as JvmDeclaredType;
			}
		}

		return super.getContextType(obj);
	}

	override protected IScope createLocalVarScope(IScope parent,
			LocalVariableScopeContext scopeContext) {
		var parentScope = super.createLocalVarScope(parent, scopeContext);

		if (scopeContext != null && scopeContext.getContext() != null) {
			val context = scopeContext.getContext();
			if (context instanceof EmfFeatureAccess) {
				val featureAccess = context as EmfFeatureAccess;
				return new SimpleScope(parentScope,
						Collections.singleton(EObjectDescription.create(THIS,
								featureAccess.getParameterType().getType())));
			}
		}

		return parentScope;
	}

	override protected IScope createImplicitFeatureCallScope(EObject call,
			Resource resource, IScope parent, IScope localVariableScope) {
		val superScope = super.createImplicitFeatureCallScope(call,
				resource, parent, localVariableScope);

		return filterScope(call, superScope);
	}

	def protected IScope filterScope(EObject call, IScope superScope) {
		var shouldFilter = false;
		val container = call.eContainer();
		if (container instanceof PropertyDescriptionSpecification) {
			val featureLabelSpecification = container as PropertyDescriptionSpecification;
			if (featureLabelSpecification.getFeature() == call)
				shouldFilter = true;
		}
		if (container instanceof FeatureSpecification) {
			val featureSpecification = container as FeatureSpecification;
			if (featureSpecification.getFeatures().contains(call))
				shouldFilter = true;
		}

		if (!shouldFilter)
			return superScope;

		val exportedObjects = superScope.getAllElements();

		// we filter the feature scope so that it contains only
		// the name of the features, no get/set/is method names
		// no static methods, etc.
		val filtered = Iterables.filter(exportedObjects) 
			[
				input |
				val element = input.getEObjectOrProxy();
				if (element instanceof JvmOperation) {
					val operation = element as JvmOperation;
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
			]

		return new SimpleScope(filtered);
	}

	def protected boolean isPropertyNameForGetterSetterMethod(String opName) {
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
