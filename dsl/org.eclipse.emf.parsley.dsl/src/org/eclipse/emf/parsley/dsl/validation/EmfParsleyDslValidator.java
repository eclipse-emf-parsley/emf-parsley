/**
 * Copyright (c) 2024 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.validation;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.filter;
import static org.eclipse.xtext.xbase.lib.ListExtensions.map;
import static org.eclipse.xtext.xbase.typesystem.util.Multimaps2.newLinkedHashListMultimap;

import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess;
import org.eclipse.emf.parsley.dsl.model.FieldSpecification;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.PartSpecification;
import org.eclipse.emf.parsley.dsl.model.PartsSpecifications;
import org.eclipse.emf.parsley.dsl.model.ViewSpecification;
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause;
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.ComposedChecks;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.validation.JvmGenericTypeValidator;

import com.google.common.collect.ListMultimap;
import com.google.inject.Inject;

/**
 * Custom validation rules.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
@ComposedChecks(validators = JvmGenericTypeValidator.class)
public class EmfParsleyDslValidator extends AbstractEmfParsleyDslValidator {
	public static final String TYPE_MISMATCH = "org.eclipse.emf.parsley.dsl.TypeMismatch";

	public static final String FINAL_FIELD_NOT_INITIALIZED = "org.eclipse.emf.parsley.dsl.FinalFieldNotInitialized";

	public static final String TOO_LITTLE_TYPE_INFORMATION = "org.eclipse.emf.parsley.dsl.TooLittleTypeInformation";

	public static final String DUPLICATE_ELEMENT = "org.eclipse.emf.parsley.dsl.DuplicateElement";

	@Inject
	@Extension
	private EmfParsleyDslExpectedSuperTypes expectedSuperTypes;

	@Inject
	@Extension
	private EmfParsleyDslGuiceModuleHelper guiceModuleHelper;

	@Inject
	private ResourceDescriptionsProvider rdp;

	@Inject
	private IContainer.Manager cm;

	private static final ModelPackage MODEL_PACKAGE = ModelPackage.eINSTANCE;

	// perform this check only on file save
	@Check(CheckType.NORMAL)
	public void checkDuplicateViewSpecificationAcrossFiles(final ViewSpecification viewSpecification) {
		var descriptions = getVisibleEObjectDescriptions(viewSpecification,
				ModelPackage.Literals.VIEW_SPECIFICATION);
		for (final IEObjectDescription desc : descriptions) {
			if (Objects.equals(desc.getQualifiedName().toString(), viewSpecification.getId())
					&& !Objects.equals(desc.getEObjectOrProxy(), viewSpecification)
					&& !Objects.equals(desc.getEObjectURI().trimFragment(),
							viewSpecification.eResource().getURI())) {
				error(String.format("The part id %s is already defined", viewSpecification.getId()),
						MODEL_PACKAGE.getViewSpecification_Id(),
						EmfParsleyDslValidator.DUPLICATE_ELEMENT);
				return;
			}
		}
	}

	private Iterable<IEObjectDescription> getVisibleEObjectDescriptions(final EObject o, final EClass type) {
		return concat(
				map(getVisibleContainers(o), container -> container.getExportedObjectsByType(type)));
	}

	private List<IContainer> getVisibleContainers(final EObject o) {
		final IResourceDescriptions index = rdp.getResourceDescriptions(o.eResource());
		final IResourceDescription rd = index.getResourceDescription(o.eResource().getURI());
		return cm.getVisibleContainers(rd, index);
	}

	@Check
	public void checkViewSpecification(final ViewSpecification viewSpecification) {
		checkType(viewSpecification, viewSpecification.getType(),
				expectedSuperTypes.getExpectedSupertype(viewSpecification),
				ModelPackage.Literals.VIEW_SPECIFICATION__TYPE);
	}

	@Check
	public void checkEmfFeatureAccess(final EmfFeatureAccess emfFeatureAccess) {
		checkType(emfFeatureAccess, emfFeatureAccess.getParameterType(),
				expectedSuperTypes.getExpectedSupertype(emfFeatureAccess),
				ModelPackage.Literals.EMF_FEATURE_ACCESS__PARAMETER_TYPE);
	}

	@Check
	public void checkExtendsClause(final WithExtendsClause withExtendsClause) {
		if (withExtendsClause.getExtendsClause() != null) {
			checkType(withExtendsClause.getExtendsClause(), withExtendsClause.getExtendsClause().getSuperType(),
					expectedSuperTypes.getExpectedSupertype(withExtendsClause),
					ModelPackage.Literals.EXTENDS_CLAUSE__SUPER_TYPE);
		}
	}

	@Check
	public void checkFieldInitialization(final FieldSpecification f) {
		if (!f.isWriteable() && f.getRight() == null) {
			error(String.format("The blank final field %s may not have been initialized", f.getName()),
					ModelPackage.Literals.FIELD_SPECIFICATION__NAME,
					EmfParsleyDslValidator.FINAL_FIELD_NOT_INITIALIZED);
		}
		if (f.getType() == null && f.getRight() == null) {
			error(String.format("The field %s needs an explicit type since there is no initialization expression to infer the type from.",
					f.getName()),
					f, ModelPackage.Literals.FIELD_SPECIFICATION__NAME,
					EmfParsleyDslValidator.TOO_LITTLE_TYPE_INFORMATION);
		}
	}

	@Check
	public void checkModule(final org.eclipse.emf.parsley.dsl.model.Module module) {
		// the inferred Guice module for this DSL Module element
		// we create a single class for the Module and it is a Guice module
		// so we can take the first element of the filter
		JvmGenericType guiceModuleClass = guiceModuleHelper.getModuleInferredType(module);
		if (guiceModuleClass == null) {
			return;
		}
		PartsSpecifications partsSpecifications = module.getPartsSpecifications();
		if (partsSpecifications != null) {
			checkDuplicateViewSpecifications(partsSpecifications.getParts());
		}
	}

	private void checkDuplicateViewSpecifications(final List<PartSpecification> parts) {
		final ListMultimap<String, ViewSpecification> map = newLinkedHashListMultimap();
		final Iterable<ViewSpecification> filtered = filter(parts, ViewSpecification.class);
		for (ViewSpecification p : filtered) {
			map.put(p.getId(), p);
		}
		checkDuplicates(map, d -> error("Duplicate element", d, MODEL_PACKAGE.getViewSpecification_Id(),
				EmfParsleyDslValidator.DUPLICATE_ELEMENT));
	}

	private <T extends Object> void checkDuplicates(final ListMultimap<String, T> map,
			final Procedure1<? super T> errorReporter) {
		var entrySet = map.asMap().entrySet();
		for (var entry : entrySet) {
			var duplicates = entry.getValue();
			if (duplicates.size() > 1) {
				for (T d : duplicates) {
					errorReporter.apply(d);
				}
			}
		}
	}

	protected void checkType(final EObject context, final JvmTypeReference actualType, final Class<?> expectedType,
			final EStructuralFeature feature) {
		if (actualType != null) {
			var jvmType = actualType.getType();
			if (jvmType instanceof JvmGenericType) {
				boolean allSuperTypesAreObject = ((JvmGenericType) jvmType).getSuperTypes().stream()
						.allMatch(t -> 
							// this means that the hierarchy has not been
							// completely resolved...
							Objects.equals(t.getIdentifier(), "java.lang.Object"));
				// ... so we don't perform other checks
				// otherwise when everything is resolved, the old
				// error markers is not removed
				// https://github.com/eclipse-emf-parsley/emf-parsley/issues/42
				if (allSuperTypesAreObject) {
					return;
				}
			}
			// was:
			// var superType = lightweightActualType.getSuperType(expectedType)
			var lightweightActualType = toLightweightTypeReference(actualType);
			if (!lightweightActualType.isSubtypeOf(expectedType)) {
				error(String.format("Type mismatch: cannot convert from %s to %s",
						actualType.getSimpleName(), expectedType.getSimpleName()),
					context, feature, EmfParsleyDslValidator.TYPE_MISMATCH);
			}
		}
	}
}
