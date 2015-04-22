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
package org.eclipse.emf.parsley.dsl.jvmmodel

import com.google.inject.Inject
import com.google.inject.Provider
import java.util.List
import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.observable.value.IObservableValue
import org.eclipse.emf.common.notify.AdapterFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.binding.DialogControlFactory
import org.eclipse.emf.parsley.binding.FormControlFactory
import org.eclipse.emf.parsley.binding.ProposalCreator
import org.eclipse.emf.parsley.dsl.model.AbstractControlFactory
import org.eclipse.emf.parsley.dsl.model.AbstractFeatureCaptionProviderWithLabel
import org.eclipse.emf.parsley.dsl.model.AbstractFeatureProvider
import org.eclipse.emf.parsley.dsl.model.BindingsSpecification
import org.eclipse.emf.parsley.dsl.model.ControlFactorySpecification
import org.eclipse.emf.parsley.dsl.model.FeatureAssociatedExpression
import org.eclipse.emf.parsley.dsl.model.LabelSpecification
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.ProviderBinding
import org.eclipse.emf.parsley.dsl.model.TypeBinding
import org.eclipse.emf.parsley.dsl.model.ValueBinding
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause
import org.eclipse.emf.parsley.dsl.model.WithFields
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder
import org.eclipse.emf.parsley.edit.action.IMenuContributionSpecification
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.jface.viewers.IContentProvider
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Label
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.eclipse.xtext.common.types.JvmAnnotationTarget
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmExecutable
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.TypesFactory
import org.eclipse.xtext.common.types.util.TypeReferences
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.util.IAcceptor
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.emf.parsley.config.Configurator
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.resource.Resource

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class EmfParsleyDslJvmModelInferrer extends AbstractModelInferrer {

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject extension JvmTypesBuilder
	
	@Inject extension IQualifiedNameProvider
	
	@Inject extension TypeReferences
	
	@Inject extension TypesFactory
	
	@Inject extension EmfParsleyDslGeneratorUtils

	/**
	 * The dispatch method {@code infer} is called for each instance of the
	 * given element's type that is contained in a resource.
	 * 
	 * @param element
	 *            the model to create one or more
	 *            {@link JvmDeclaredType declared
	 *            types} from.
	 * @param acceptor
	 *            each created
	 *            {@link JvmDeclaredType type}
	 *            without a container should be passed to the acceptor in order
	 *            get attached to the current resource. The acceptor's
	 *            {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
	 *            accept(..)} method takes the constructed empty type for the
	 *            pre-indexing phase. This one is further initialized in the
	 *            indexing phase using the closure you pass as the last argument.
	 * @param isPreIndexingPhase
	 *            whether the method is called in a pre-indexing phase, i.e.
	 *            when the global index is not yet fully updated. You must not
	 *            rely on linking using the index if isPreIndexingPhase is
	 *            <code>true</code>.
	 */
   	def dispatch void infer(Module element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		if (element.name.empty)
   			return
   		
		val moduleClass = element.toClass(element.moduleQN)
		
		val labelProviderClass = element.inferLabelProvider(acceptor)
		val tableLabelProviderClass = element.inferTableLabelProvider(acceptor)
		val featureCaptionProviderClass = element.inferFeatureCaptionProvider(acceptor)
		val formFeatureCaptionProviderClass = element.inferFormFeatureCaptionProvider(acceptor)
		val dialogFeatureCaptionProviderClass = element.inferDialogFeatureCaptionProvider(acceptor)
		val featureProviderClass = element.inferFeatureProvider(acceptor)
		val tableFeatureProviderClass = element.inferTableFeatureProvider(acceptor)
		val formControlFactoryClass = element.inferFormControlFactory(acceptor)
		val dialogControlFactoryClass = element.inferDialogControlFactory(acceptor)
		val viewerContentProviderClass = element.inferViewerContentProvider(acceptor)
		val proposalCreatorClass = element.inferProposalCreator(acceptor)
		val menuBuilderClass = element.inferMenuBuilder(acceptor)
		val configuratorClass = element.inferConfigurator(acceptor)
		
		acceptor.accept(moduleClass) [
			documentation = element.documentation
			moduleClass.setSuperClassType(element, typeof(EmfParsleyGuiceModule))
			
			members += element.toConstructor() [
				parameters += element.toParameter("plugin", typeRef(AbstractUIPlugin))
				body = [it.append("super(plugin);")]
			]

			val bindingsSpecification = element.bindingsSpecification
			if (element.bindingsSpecification != null) {
				handleBindingsSpecification(it, bindingsSpecification)
			}
			
			if (labelProviderClass != null)
				members += element.labelProvider.genBindMethod(labelProviderClass, typeof(ILabelProvider))
			if (tableLabelProviderClass != null)
				members += element.tableLabelProvider.genBindMethod(tableLabelProviderClass, typeof(TableColumnLabelProvider)
				)
			if (featureCaptionProviderClass != null)
				members += element.featureCaptionProvider.genBindMethod(featureCaptionProviderClass, typeof(FeatureCaptionProvider))
			if (formFeatureCaptionProviderClass != null)
				members += element.formFeatureCaptionProvider.genBindMethod(formFeatureCaptionProviderClass, typeof(FormFeatureCaptionProvider))
			if (dialogFeatureCaptionProviderClass != null)
				members += element.dialogFeatureCaptionProvider.genBindMethod(dialogFeatureCaptionProviderClass, typeof(DialogFeatureCaptionProvider))
			if (featureProviderClass != null)
				members += element.featuresProvider.genBindMethod(featureProviderClass, typeof(FeaturesProvider))
			if (tableFeatureProviderClass != null)
				members += element.tableFeaturesProvider.genBindMethod(tableFeatureProviderClass, typeof(TableFeaturesProvider))
			if (formControlFactoryClass != null)
				members += element.formControlFactory.genBindMethod(formControlFactoryClass, typeof(FormControlFactory))
			if (dialogControlFactoryClass != null)
				members += element.dialogControlFactory.genBindMethod(dialogControlFactoryClass, typeof(DialogControlFactory))
			if (viewerContentProviderClass != null)
				members += element.viewerContentProvider.genBindMethod(viewerContentProviderClass, typeof(IContentProvider))
			if (proposalCreatorClass != null)
				members += element.proposalCreator.genBindMethod(proposalCreatorClass, typeof(ProposalCreator))
			if (menuBuilderClass != null)
				members += element.menuBuilder.genBindMethod(menuBuilderClass, typeof(EditingMenuBuilder))
			if (configuratorClass != null)
				members += element.configurator.genBindMethod(configuratorClass, typeof(Configurator))
		]

   	}
				
	def private setSuperClassType(JvmGenericType e, WithExtendsClause dslElement, Class<?> defaultSuperClass) {
		if (dslElement.extendsClause != null)
			e.superTypes += dslElement.extendsClause.superType.cloneWithProxies
		else
			e.superTypes += typeRef(defaultSuperClass)
	}

	def private setSuperClassTypeAndFields(JvmGenericType e, WithFields dslElement, Class<?> defaultSuperClass) {
		e.setSuperClassType(dslElement, defaultSuperClass)
		e.processFields(dslElement)
	}

	def private processFields(JvmGenericType it, WithFields dslElement) {
		for (f : dslElement.fields) {
			val type = f.type ?: f.right?.inferredType
			val name = f.name

			val field = f.toField(name, type) [
				initializer = f.right
				final = !f.writeable
				visibility = JvmVisibility.PRIVATE
				translateAnnotations(f.annotations)
				if (f.extension) {
					annotations += annotationRef(Extension)
				}
			]
			
			members += field
			
			
			members += f.toGetter(name, type)
			if (f.writeable) {
				members += f.toSetter(name, type)
			}
		}
	}

	def private void translateAnnotations(JvmAnnotationTarget target, List<XAnnotation> annotations) {
		target.addAnnotations(annotations.filterNull.filter[annotationType != null])
	}
   	
   	def private moduleQN(Module element) {
   		element.fullyQualifiedName + ".EmfParsleyGuiceModuleGen"
   	}

	def private labelProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.LabelProviderGen"
	}
	
	def private tableLabelProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.TableLabelProviderGen"
	}
	
	def private featureCaptionProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.FeatureCaptionProviderGen"
	}

	def private formFeatureCaptionProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.FormFeatureCaptionProviderGen"
	}

	def private dialogFeatureCaptionProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.DialogFeatureCaptionProviderGen"
	}

	def private featuresProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.FeaturesProviderGen"
	}

	def private tableFeaturesProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.TableFeaturesProviderGen"
	}

	def private formControlFactoryQN(Module element) {
		element.fullyQualifiedName + ".binding.FormControlFactoryGen"
	}

	def private dialogControlFactoryQN(Module element) {
		element.fullyQualifiedName + ".binding.DialogControlFactoryGen"
	}

	def private viewerContentProviderQN(Module element) {
		element.fullyQualifiedName + ".edit.ui.provider.ViewerContentProviderGen"
	}

	def private proposalCreatorQN(Module element) {
		element.fullyQualifiedName + ".binding.ProposalCreatorGen"
	}

	def private menuBuilderQN(Module element) {
		element.fullyQualifiedName + ".edit.action.MenuBuilderGen"
	}

	def private configuratorQN(Module element) {
		element.fullyQualifiedName + ".config.ConfiguratorGen"
	}

	def private inferLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.labelProvider == null)
			null
		else {
			val labelProvider = element.labelProvider
			val labelProviderClass = labelProvider.toClass(element.labelProviderQN)
			acceptor.accept(labelProviderClass) [
				setSuperClassTypeAndFields(labelProvider, typeof(ViewerLabelProvider))
				
				members += labelProvider.toConstructor() [
					parameters += labelProvider.
						toParameter("delegate", 
							typeRef(AdapterFactoryLabelProvider)
						)
					body = [it.append("super(delegate);")]
					annotations += annotationRef(Inject)
				]
				
				for (labelSpecification : labelProvider.labelSpecifications) {
					members += labelSpecification.specificationToMethod("text", typeRef(String))
				}
				
				for (imageSpecification : labelProvider.imageSpecifications) {
					members += imageSpecification.specificationToMethod("image", typeRef(Object))
				}
			]
			labelProviderClass
		}
	}
	
	def private inferTableLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.tableLabelProvider == null)
			null
		else {
			val tableLabelProvider = element.tableLabelProvider
			val tableLabelProviderClass = tableLabelProvider.toClass(element.tableLabelProviderQN)
			acceptor.accept(tableLabelProviderClass) [
				setSuperClassTypeAndFields(tableLabelProvider, typeof(TableColumnLabelProvider))
					
				for (labelSpecification : tableLabelProvider.labelSpecifications) {
					if (labelSpecification.feature?.simpleName != null) {
						members += labelSpecification.toMethod("text_" + 
							labelSpecification.parameterType.simpleName + "_" +
							labelSpecification.feature.simpleName.propertyNameForGetterSetterMethod
							, typeRef(String)
						) [
							parameters += labelSpecification.toParameter(
								"it"
								, labelSpecification.parameterType
							)
							body = labelSpecification.expression
						]
					}
				}
			
				for (imageSpecification : tableLabelProvider.imageSpecifications) {
					if (imageSpecification.feature?.simpleName != null) {
						members += imageSpecification.toMethod("image_" + 
							imageSpecification.parameterType.simpleName + "_" +
							imageSpecification.feature.simpleName.propertyNameForGetterSetterMethod
							, typeRef(Object)) [
							parameters += imageSpecification.toParameter(
									"it"
								, imageSpecification.parameterType
							)
							body = imageSpecification.expression
						]
					}
				}
			]
			tableLabelProviderClass
		}
	}
	
	def private inferFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.featureCaptionProvider == null)
			null
		else {
			val featureCaptionProvider = element.featureCaptionProvider
			val propertyDescriptionProviderClass = featureCaptionProvider.toClass(element.featureCaptionProviderQN)
			acceptor.accept(propertyDescriptionProviderClass) [
				setSuperClassTypeAndFields(featureCaptionProvider, typeof(FeatureCaptionProvider))
				
				inferMethodsForTextCaptionSpecifications(element, it, featureCaptionProvider.specifications)
			]
			propertyDescriptionProviderClass
		}
	}
	
	def private inferFormFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		element.formFeatureCaptionProvider.inferDialogFeatureCaptionProviderWithLabel(
			element.formFeatureCaptionProviderQN, typeof(FormFeatureCaptionProvider), acceptor)
	}

	def private inferDialogFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		element.dialogFeatureCaptionProvider.inferDialogFeatureCaptionProviderWithLabel(
			element.dialogFeatureCaptionProviderQN, typeof(DialogFeatureCaptionProvider), acceptor)
	}

	def private inferDialogFeatureCaptionProviderWithLabel(AbstractFeatureCaptionProviderWithLabel element, String name, Class<?> superClass, IJvmDeclaredTypeAcceptor acceptor) {
		if (element == null)
			null
		else {
			val descriptionProviderClass = element.toClass(name)
			acceptor.accept(descriptionProviderClass) [
				setSuperClassTypeAndFields(element, superClass)
				
				inferMethodsForTextCaptionSpecifications(element, it, element.specifications)
				
				inferMethodsForLabelCaptionSpecifications(element, it, element.labelSpecifications)			
			]
			descriptionProviderClass
		}
	}

	def private inferMethodsForTextCaptionSpecifications(EObject element, JvmGenericType it, Iterable<FeatureAssociatedExpression> specifications) {
		inferMethodsForCaptionSpecifications(
			it, specifications, "text_", typeRef(String)
		) [
			it, spec |
			parameters += spec.toParameter(
				"it", typeRef(EStructuralFeature)
			)
		]
	}
	
	def private inferMethodsForLabelCaptionSpecifications(EObject element, JvmGenericType it, Iterable<FeatureAssociatedExpression> specifications) {
		inferMethodsForCaptionSpecifications(
			it, specifications, "label_", typeRef(Label)
		) [
			it, spec |
			parameters += spec.toParameter(
				"parent", typeRef(Composite)
			)
			parameters += spec.toParameter(
				"it", typeRef(EStructuralFeature)
			)
		]
	}

	def private inferMethodsForCaptionSpecifications(JvmGenericType it, Iterable<FeatureAssociatedExpression> specifications, 
		String prefix, JvmTypeReference returnType, (JvmOperation, FeatureAssociatedExpression) => void parameterCreator
	) {
		for (spec : specifications) {
			featureAssociatedExpressionToMethod(spec, prefix, returnType, parameterCreator)
		}
	}

	def private featureAssociatedExpressionToMethod(JvmGenericType it, FeatureAssociatedExpression spec, 
			String prefix, JvmTypeReference returnType, (JvmOperation, FeatureAssociatedExpression) => void parameterCreator) {
		if (spec.feature?.simpleName != null) {
			// associate the method to the expression, not to the whole
			// spec, otherwise the 'feature' is logically
			// contained in a method which should return a label
			// and the validator would complain
			members += spec.expression.toMethod
			(prefix + 
					spec.parameterType.simpleName + "_" +
					spec.feature.simpleName.propertyNameForGetterSetterMethod, 
				returnType
			) [
				parameterCreator.apply(it, spec)
				body = spec.expression
			]
		}
	}

	def private inferFeatureProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		element.featuresProvider.inferFeatureProviderCommon(
			element.featuresProviderQN, typeof(FeaturesProvider), acceptor)
	}

	def private inferTableFeatureProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		element.tableFeaturesProvider.
			inferFeatureProviderCommon(element.tableFeaturesProviderQN, typeof(TableFeaturesProvider), acceptor)
	}

	def private inferFeatureProviderCommon(AbstractFeatureProvider element, String name, Class<?> superClass, IJvmDeclaredTypeAcceptor acceptor) {
		if (element == null)
			null
		else {
			val featureProviderClass = element.toClass(name)
			acceptor.accept(featureProviderClass) [
				setSuperClassTypeAndFields(element, superClass)
				
				documentation = element.documentation
				members += element.
						toMethod("buildStringMap", Void::TYPE.getTypeForName(element)) [
					annotations += annotationRef(Override)
					parameters += element.toParameter("stringMap",
							typeRef(EClassToEStructuralFeatureAsStringsMap)
					)
					body = [
						append("super.buildStringMap(stringMap);").newLine
						for (featureSpecification : element.featureSpecifications) {
							newLine.
								append('''stringMap.mapTo("«featureSpecification.parameterType.identifier»",''').
									increaseIndentation.newLine
							val fs = featureSpecification.features.map [
								feature |
								'"' + feature.simpleName.propertyNameForGetterSetterMethod
								+ '"'
							]
							append(fs.join(", "))
							append(");").decreaseIndentation
						}
					]
				]
			]
			featureProviderClass
		}
	}

	def private inferFormControlFactory(Module e, IJvmDeclaredTypeAcceptor acceptor) {
		e.formControlFactory.inferControlFactory(e.formControlFactoryQN, typeof(FormControlFactory), acceptor)
	}

	def private inferDialogControlFactory(Module e, IJvmDeclaredTypeAcceptor acceptor) {
		e.dialogControlFactory.inferControlFactory(e.dialogControlFactoryQN, typeof(DialogControlFactory), acceptor)
	}

	def private inferControlFactory(AbstractControlFactory e, String name, Class<?> superClass, IJvmDeclaredTypeAcceptor acceptor) {
		if (e == null)
			null
		else {
			val controlFactoryClass = e.toClass(name)
			acceptor.accept(controlFactoryClass) [
				setSuperClassTypeAndFields(e, superClass)
				
				documentation = e.documentation
				
				inferMethodsForControlFactory(e, it, e.controlSpecifications)
			]
			controlFactoryClass
		}
	}
	
	def private inferMethodsForControlFactory(AbstractControlFactory e, JvmGenericType it, Iterable<ControlFactorySpecification> specifications) {
		for (spec: specifications) {
			if (spec.feature?.simpleName != null) {
				if (spec.target == null)
					members += spec.
					control_EClass_EStructuralFeature(spec.expression) [
						parameters += spec.toParameter(
							"it", spec.parameterType
						)
						body = spec.expression
					]
				else {
					val createControlMethodName = spec.methodNameForFormFeatureSpecification("createControl_")
					val createTargetMethodName = spec.methodNameForFormFeatureSpecification("createTarget_")
					members += spec.
					control_EClass_EStructuralFeature(spec.expression) [
						parameters += spec.toParameter(
							"dataBindingContext", typeRef(DataBindingContext)
						)
						parameters += spec.toParameter(
							"observableValue", typeRef(IObservableValue)
						)
						body = [
							append(typeRef(Control).type)
							append(''' control = «createControlMethodName»();''').newLine
							append(
							'''
							dataBindingContext.bindValue(
								«createTargetMethodName»(control),
								observableValue);'''
							).newLine
							append('''return control;''')
						]
					]
					
					members += spec.toMethod
					(createControlMethodName, 
						typeRef(Control)) [
							visibility = JvmVisibility::PROTECTED
							body = spec.expression
					]
					
					members += spec.toMethod
					(createTargetMethodName, 
						typeRef(IObservableValue)) [
							visibility = JvmVisibility::PROTECTED
							parameters += spec.toParameter(
								"it", typeRef(Control)
							)
							body = spec.target
					]
				}
			}
		}
	}

	def private inferViewerContentProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.viewerContentProvider == null)
			null
		else {
			val viewerContentProvider = element.viewerContentProvider
			val viewerContentProviderClass = viewerContentProvider.toClass(element.viewerContentProviderQN)
			acceptor.accept(viewerContentProviderClass) [
				setSuperClassTypeAndFields(viewerContentProvider, typeof(ViewerContentProvider))
				
				members += viewerContentProvider.toConstructor() [
					parameters += element.viewerContentProvider.
						toParameter("adapterFactory", 
							typeRef(AdapterFactory)
						)
					body = [it.append("super(adapterFactory);")]
					annotations += annotationRef(Inject)
				]
				
				for (specification : viewerContentProvider.elementsSpecifications) {
					members += specification.specificationToMethod("elements", typeRef(Object))
				}
				
				for (specification : viewerContentProvider.childrenSpecifications) {
					members += specification.specificationToMethod("children", typeRef(Object))
				}
			]
			viewerContentProviderClass
		}
	}

	def private inferProposalCreator(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.proposalCreator == null)
			null
		else {
			val proposalCreator = element.proposalCreator
			val proposalCreatorClass = proposalCreator.toClass(element.proposalCreatorQN)
			acceptor.accept(proposalCreatorClass) [
				setSuperClassTypeAndFields(proposalCreator, typeof(ProposalCreator))
				
				for (specification : proposalCreator.proposalsSpecifications) {
					featureAssociatedExpressionToMethod(specification, "proposals_", typeRef(List).type.createTypeRef(wildCard)) [
						it, spec |
						parameters += spec.toParameter(
							"it", spec.parameterType
						)
						parameters += spec.toParameter(
							"feature", typeRef(EStructuralFeature)
						)
					]
				}
			]
			proposalCreatorClass
		}
	}

	def private inferMenuBuilder(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.menuBuilder == null)
			null
		else {
			val menuBuilder = element.menuBuilder
			val menuBuilderClass = menuBuilder.toClass(element.menuBuilderQN)
			acceptor.accept(menuBuilderClass) [
				setSuperClassTypeAndFields(menuBuilder, EditingMenuBuilder)
				
				val returnType = typeRef(List, typeRef(IMenuContributionSpecification))
				
				for (specification : menuBuilder.menuSpecifications) {
					members += specification.specificationToMethod("menuContributions", returnType)
				}
				
				for (specification : menuBuilder.emfMenuSpecifications) {
					members += specification.specificationToMethod("emfMenuContributions", returnType)
				}
			]
			menuBuilderClass
		}
	}

	def private inferConfigurator(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.configurator == null)
			null
		else {
			val configurator = element.configurator
			val configuratorClass = configurator.toClass(element.configuratorQN)
			acceptor.accept(configuratorClass) [
				setSuperClassTypeAndFields(configurator, Configurator)
				
				for (specification : configurator.resourceURISpecifications) {
					members += specification.specificationToMethod("resourceURI", typeRef(URI))
				}
				
				for (specification : configurator.EClassSpecifications) {
					members += specification.specificationToMethod("eClass", typeRef(EClass))
				}
				
				for (specification : configurator.EStructuralFeatureSpecifications) {
					members += specification.specificationToMethod("eStructuralFeature", typeRef(EStructuralFeature))
				}
				
				for (specification : configurator.contentsSpecifications) {
					members += specification.specificationToMethod("contents", typeRef(Object)) => [
						m |
						m.parameters += specification.toParameter("resource", typeRef(Resource))
					]
				}
			]
			configuratorClass
		}
	}

	def private specificationToMethod(LabelSpecification specification, String methodName, JvmTypeReference returnType) {
		specification.toMethod(methodName, returnType) [
			parameters += specification.specificationParameter
			body = specification.expression
		]
	}

	def private specificationParameter(LabelSpecification specification) {
		specification.toParameter(
			if (specification.name != null)
				specification.name
			else
				"it"
			, specification.parameterType
		)
	}

	def private control_EClass_EStructuralFeature(
			ControlFactorySpecification spec, XExpression exp, (JvmOperation)=>void init
	) {
		exp.toMethod
			(spec.methodNameForFormFeatureSpecification("control_"), 
				typeRef(Control)
			, init)
	}
	
	def private methodNameForFormFeatureSpecification(ControlFactorySpecification spec, String prefix) {
		prefix + 
					spec.parameterType.simpleName + "_" +
					spec.feature.simpleName.propertyNameForGetterSetterMethod
	}
	
	def private genBindMethod(EObject element, JvmGenericType type, Class<?> clazz) {
		genBindMethod(element, typeRef(clazz), true) [
			body = [
				append("return ")
				append(type)
				append(".class;")
			]
		]
	}

	def private handleBindingsSpecification(JvmGenericType it, BindingsSpecification bindingsSpecification) {
		for (binding : bindingsSpecification.bindings) {
			var JvmOperation method = null
			if (binding instanceof TypeBinding) {
				method = genBindMethod(binding)
			} else if (binding instanceof ProviderBinding) {
				method = genProvideMethod(binding)
			} else if (binding instanceof ValueBinding) {
				method = genValueMethod(binding)
			}
			
			if (method != null) {
				members += method
			}
		}
	}

	def private genBindMethod(JvmGenericType it, TypeBinding typeBinding) {
		if (typeBinding.typeToBind == null) {
			return null
		}
		
		// we must trigger resolution of JvmTypeReference
		// otherwise the parameterized Class type with wildcard
		// will contain an unresolved type reference
		genBindMethod(typeBinding, typeBinding.typeToBind.type.typeRef) [
			body = typeBinding.getTo
		]
	}

	def private genProvideMethod(JvmGenericType it, ProviderBinding binding) {
		if (binding.type == null) {
			return null
		}
		
		// we must trigger resolution of JvmTypeReference
		// otherwise the parameterized Class type with wildcard
		// will contain an unresolved type reference
		genProvideMethod(binding, binding.type.type.typeRef) [
			body = binding.getTo
		]
	}

	def private genValueMethod(JvmGenericType it, ValueBinding binding) {
		if (binding.typeDecl == null || binding.id == null) {
			return null
		}
		
		genValueMethod(binding, binding.id, binding.typeDecl) [
			body = binding.getTo
		]
	}


	def private genBindMethod(EObject element, JvmTypeReference typeRefToBind, boolean shouldOverride, IAcceptor<JvmExecutable> acceptor) {
		element.genMethodForGuiceModuleWithWildcard("bind" + typeRefToBind.simpleName,
			typeRefToBind.cloneWithProxies, shouldOverride, acceptor
		)
	}

	def private genBindMethod(JvmGenericType it, EObject element, JvmTypeReference typeRefToBind, IAcceptor<JvmExecutable> acceptor) {
		val methodName = "bind" + typeRefToBind.simpleName
		element.genMethodForGuiceModuleWithWildcard(methodName,
			typeRefToBind.cloneWithProxies, shouldOverride(methodName), acceptor
		)
	}

	def private genProvideMethod(JvmGenericType it, EObject element, JvmTypeReference typeRefToBind, IAcceptor<JvmExecutable> acceptor) {
		val methodName = "provide" + typeRefToBind.simpleName
		element.genMethodForGuiceModuleWithWildcard(methodName, 
			typeRef(Provider, typeRefToBind.cloneWithProxies), shouldOverride(methodName), acceptor
		)
	}

	def private genValueMethod(JvmGenericType it, EObject element, String name, JvmTypeReference typeRefToBind, IAcceptor<JvmExecutable> acceptor) {
		val methodName = "value" + name
		element.genMethodForGuiceModule(methodName, typeRefToBind.cloneWithProxies, shouldOverride(methodName), acceptor)
	}

	def private genMethodForGuiceModuleWithWildcard(EObject element, String methodName, 
		JvmTypeReference typeRefToBind, boolean shouldOverride, IAcceptor<JvmExecutable> acceptor
	) {
		val wildCard = createJvmWildcardTypeReference
		val upperBound = createJvmUpperBound
		upperBound.typeReference = typeRefToBind.cloneWithProxies
		wildCard.constraints += upperBound
		element.genMethodForGuiceModule(methodName, typeRef(Class, wildCard), shouldOverride, acceptor)
	}

	def private genMethodForGuiceModule(EObject element, String methodName, JvmTypeReference typeRefToBind, boolean shouldOverride, IAcceptor<JvmExecutable> acceptor) {
		element.toMethod(methodName, typeRefToBind) [
			if (shouldOverride) {
				annotations += annotationRef(Override)
			}
			acceptor.accept(it)
		]
	}

	def private shouldOverride(JvmGenericType it, String methodName) {
		allFeatures.filter(JvmOperation).exists[simpleName == methodName]
	}
}

