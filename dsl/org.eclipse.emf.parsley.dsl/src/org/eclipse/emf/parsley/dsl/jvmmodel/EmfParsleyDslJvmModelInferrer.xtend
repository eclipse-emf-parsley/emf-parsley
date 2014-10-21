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
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PartSpecification
import org.eclipse.emf.parsley.dsl.model.ProviderBinding
import org.eclipse.emf.parsley.dsl.model.TypeBinding
import org.eclipse.emf.parsley.dsl.model.ValueBinding
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause
import org.eclipse.emf.parsley.dsl.model.WithFields
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator
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

import static extension org.eclipse.xtext.EcoreUtil2.*
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder
import org.eclipse.emf.parsley.edit.action.IMenuContributionSpecification
import org.eclipse.emf.parsley.dsl.model.LabelSpecification

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
	
	@Inject extension GeneratorUtils
	
	@Inject EmfParsleyProjectFilesGenerator projectFilesGenerator

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
		]

   	}
				
	def setSuperClassType(JvmGenericType e, WithExtendsClause dslElement, Class<?> defaultSuperClass) {
		if (dslElement.extendsClause != null)
			e.superTypes += dslElement.extendsClause.superType.cloneWithProxies
		else
			e.superTypes += typeRef(defaultSuperClass)
	}

	def setSuperClassTypeAndFields(JvmGenericType e, WithFields dslElement, Class<?> defaultSuperClass) {
		e.setSuperClassType(dslElement, defaultSuperClass)
		e.processFields(dslElement)
	}

	def processFields(JvmGenericType it, WithFields dslElement) {
		for (f : dslElement.fields) {
			var type = f.type
			val name = f.name
			val initialExp = f.right
			members += f.toField(name, type) => [
				final = !f.writeable
				visibility = JvmVisibility.PRIVATE
				translateAnnotations(f.annotations)
				if (f.extension) {
					annotations += annotationRef(Extension)
				}
				initializer = initialExp
			]
			members += f.toGetter(name, type)
			if (f.writeable) {
				members += f.toSetter(name, type)
			}
		}
	}

	def void translateAnnotations(JvmAnnotationTarget target, List<XAnnotation> annotations) {
		target.addAnnotations(annotations.filterNull.filter[annotationType != null])
	}
   	
   	def activatorQN(Module element) {
   		element.fullyQualifiedName + ".Activator"
   	}
   	
   	def moduleQN(Module element) {
   		element.fullyQualifiedName + ".EmfParsleyGuiceModuleGen"
   	}

   	def executableExtensionFactoryQN(Module element) {
   		element.fullyQualifiedName.toString + "." +
   		projectFilesGenerator.extFactoryName(element.fullyQualifiedName.toString)
   	}

   	def executableExtensionFactoryQN(PartSpecification element) {
   		element.getContainerOfType(typeof(Module)).executableExtensionFactoryQN
   	}

	def labelProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.LabelProviderGen"
	}
	
	def tableLabelProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.TableLabelProviderGen"
	}
	
	def featureCaptionProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.FeatureCaptionProviderGen"
	}

	def formFeatureCaptionProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.FormFeatureCaptionProviderGen"
	}

	def dialogFeatureCaptionProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.DialogFeatureCaptionProviderGen"
	}

	def featuresProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.FeaturesProviderGen"
	}

	def tableFeaturesProviderQN(Module element) {
		element.fullyQualifiedName + ".ui.provider.TableFeaturesProviderGen"
	}

	def formControlFactoryQN(Module element) {
		element.fullyQualifiedName + ".binding.FormControlFactoryGen"
	}

	def dialogControlFactoryQN(Module element) {
		element.fullyQualifiedName + ".binding.DialogControlFactoryGen"
	}

	def viewerContentProviderQN(Module element) {
		element.fullyQualifiedName + ".edit.ui.provider.ViewerContentProviderGen"
	}

	def proposalCreatorQN(Module element) {
		element.fullyQualifiedName + ".binding.ProposalCreatorGen"
	}

	def menuBuilderQN(Module element) {
		element.fullyQualifiedName + ".edit.action.MenuBuilderGen"
	}

	def inferLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
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
					members += labelSpecification.toMethod("text", typeRef(String)) [
						parameters += labelSpecification.toParameter(
							if (labelSpecification.name != null)
								labelSpecification.name
							else
								"it"
							, labelSpecification.parameterType
						)
						body = labelSpecification.expression
					]
				}
				
				for (imageSpecification : labelProvider.imageSpecifications) {
					members += imageSpecification.toMethod("image", typeRef(Object)) [
						parameters += imageSpecification.toParameter(
							if (imageSpecification.name != null)
								imageSpecification.name
							else
								"it"
							, imageSpecification.parameterType
						)
						body = imageSpecification.expression
					]
				}
			]
			labelProviderClass
		}
	}
	
	def inferTableLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
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
	
	def inferFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.featureCaptionProvider == null)
			null
		else {
			val featureCaptionProvider = element.featureCaptionProvider
			val propertyDescriptionProviderClass = featureCaptionProvider.toClass(element.featureCaptionProviderQN)
			acceptor.accept(propertyDescriptionProviderClass) [
				setSuperClassTypeAndFields(featureCaptionProvider, typeof(FeatureCaptionProvider))
				
				inferMethodsForTextPropertyDescription(element, it, featureCaptionProvider.specifications)
			]
			propertyDescriptionProviderClass
		}
	}
	
	def inferMethodsForTextPropertyDescription(EObject element, JvmGenericType it, Iterable<FeatureAssociatedExpression> specifications) {
		for (spec : specifications) {
			if (spec.feature?.simpleName != null) {
				// associate the method to the expression, not to the whole
				// spec, otherwise the 'feature' is logically
				// contained in a method which should return a string
				// and the validator would complain
				members += spec.expression.toMethod
				("text_" + 
						spec.parameterType.simpleName + "_" +
						spec.feature.simpleName.propertyNameForGetterSetterMethod, 
					typeRef(String)
				) [
					parameters += spec.toParameter(
						"it", typeRef(EStructuralFeature)
					)
					body = spec.expression
				]
			}
		}
	}

	def inferFormFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		element.formFeatureCaptionProvider.inferDialogFeatureCaptionProviderWithLabel(
			element.formFeatureCaptionProviderQN, typeof(FormFeatureCaptionProvider), acceptor)
	}

	def inferDialogFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		element.dialogFeatureCaptionProvider.inferDialogFeatureCaptionProviderWithLabel(
			element.dialogFeatureCaptionProviderQN, typeof(DialogFeatureCaptionProvider), acceptor)
	}

	def inferDialogFeatureCaptionProviderWithLabel(AbstractFeatureCaptionProviderWithLabel element, String name, Class<?> superClass, IJvmDeclaredTypeAcceptor acceptor) {
		if (element == null)
			null
		else {
			val descriptionProviderClass = element.toClass(name)
			acceptor.accept(descriptionProviderClass) [
				setSuperClassTypeAndFields(element, superClass)
				
				inferMethodsForTextPropertyDescription(element, it, element.specifications)
				
				inferMethodsForLabelPropertyDescription(element, it, element.labelSpecifications)			
			]
			descriptionProviderClass
		}
	}
	
	def inferMethodsForLabelPropertyDescription(EObject element, JvmGenericType it, Iterable<FeatureAssociatedExpression> specifications) {
		for (spec : specifications) {
			if (spec.feature?.simpleName != null) {
				// associate the method to the expression, not to the whole
				// spec, otherwise the 'feature' is logically
				// contained in a method which should return a label
				// and the validator would complain
				members += spec.expression.toMethod
				("label_" + 
						spec.parameterType.simpleName + "_" +
						spec.feature.simpleName.propertyNameForGetterSetterMethod, 
					typeRef(Label)
				) [
					parameters += spec.toParameter(
						"parent", typeRef(Composite)
					)
					parameters += spec.toParameter(
						"it", typeRef(EStructuralFeature)
					)
					body = spec.expression
				]
			}
		}
	}

	def inferFeatureProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		element.featuresProvider.inferFeatureProviderCommon(
			element.featuresProviderQN, typeof(FeaturesProvider), acceptor)
	}

	def inferTableFeatureProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		element.tableFeaturesProvider.
			inferFeatureProviderCommon(element.tableFeaturesProviderQN, typeof(TableFeaturesProvider), acceptor)
	}

	def inferFeatureProviderCommon(AbstractFeatureProvider element, String name, Class<?> superClass, IJvmDeclaredTypeAcceptor acceptor) {
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

	def inferFormControlFactory(Module e, IJvmDeclaredTypeAcceptor acceptor) {
		e.formControlFactory.inferControlFactory(e.formControlFactoryQN, typeof(FormControlFactory), acceptor)
	}

	def inferDialogControlFactory(Module e, IJvmDeclaredTypeAcceptor acceptor) {
		e.dialogControlFactory.inferControlFactory(e.dialogControlFactoryQN, typeof(DialogControlFactory), acceptor)
	}

	def inferControlFactory(AbstractControlFactory e, String name, Class<?> superClass, IJvmDeclaredTypeAcceptor acceptor) {
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
	
	def inferMethodsForControlFactory(AbstractControlFactory e, JvmGenericType it, Iterable<ControlFactorySpecification> specifications) {
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

	def inferViewerContentProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
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
					members += specification.toMethod("elements", typeRef(Object)) [
						parameters += specification.toParameter(
							if (specification.name != null)
								specification.name
							else
								"it"
							, specification.parameterType
						)
						body = specification.expression
					]
				}
				
				for (specification : viewerContentProvider.childrenSpecifications) {
					members += specification.toMethod("children", typeRef(Object)) [
						parameters += specification.toParameter(
							if (specification.name != null)
								specification.name
							else
								"it"
							, specification.parameterType
						)
						body = specification.expression
					]
				}
			]
			viewerContentProviderClass
		}
	}

	def inferProposalCreator(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.proposalCreator == null)
			null
		else {
			val proposalCreator = element.proposalCreator
			val proposalCreatorClass = proposalCreator.toClass(element.proposalCreatorQN)
			acceptor.accept(proposalCreatorClass) [
				setSuperClassTypeAndFields(proposalCreator, typeof(ProposalCreator))
				
				for (spec : proposalCreator.proposalsSpecifications) {
					if (spec.feature?.simpleName != null) {
						// associate the method to the expression, not to the whole
						// specification, otherwise the 'feature' is logically
						// contained in a method which should return a list
						// and the validator would complain
						members += spec.expression.toMethod
						("proposals_" + 
								spec.parameterType.simpleName + "_" +
								spec.feature.simpleName.propertyNameForGetterSetterMethod, 
							typeRef(List).type.createTypeRef(wildCard)
						) [
							parameters += spec.toParameter(
								"it", spec.parameterType
							)
							parameters += spec.toParameter(
								"feature", typeRef(EStructuralFeature)
							)
							body = spec.expression
						]
					}
				}
			]
			proposalCreatorClass
		}
	}

	def inferMenuBuilder(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.menuBuilder == null)
			null
		else {
			val menuBuilder = element.menuBuilder
			val menuBuilderClass = menuBuilder.toClass(element.menuBuilderQN)
			acceptor.accept(menuBuilderClass) [
				setSuperClassTypeAndFields(menuBuilder, EditingMenuBuilder)
				
				val returnType = typeRef(List, typeRef(IMenuContributionSpecification))
				
				for (specification : menuBuilder.menuSpecifications) {
					members += specification.toMethod("menuContributions", returnType) [
						parameters += specification.specificationParameter
						body = specification.expression
					]
				}
				
				for (specification : menuBuilder.emfMenuSpecifications) {
					members += specification.toMethod("emfMenuContributions", returnType) [
						parameters += specification.specificationParameter
						body = specification.expression
					]
				}
			]
			menuBuilderClass
		}
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

	def control_EClass_EStructuralFeature(
			ControlFactorySpecification spec, XExpression exp, (JvmOperation)=>void init
	) {
		exp.toMethod
			(spec.methodNameForFormFeatureSpecification("control_"), 
				typeRef(Control)
			, init)
	}
	
	def methodNameForFormFeatureSpecification(ControlFactorySpecification spec, String prefix) {
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

	def handleBindingsSpecification(JvmGenericType it, BindingsSpecification bindingsSpecification) {
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

