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
import org.eclipse.emf.parsley.dsl.model.ControlFactorySpecification
import org.eclipse.emf.parsley.dsl.model.FeatureAssociatedExpression
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PartSpecification
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.factories.TreeFormFactory
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.emf.parsley.widgets.TreeFormComposite
import org.eclipse.jface.viewers.IContentProvider
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Label
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.TypesFactory
import org.eclipse.xtext.common.types.util.TypeReferences
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

import static extension org.eclipse.xtext.EcoreUtil2.*
import org.eclipse.swt.SWT
import org.eclipse.emf.parsley.dsl.model.AbstractControlFactory
import org.eclipse.emf.parsley.dsl.model.AbstractFeatureProvider
import org.eclipse.emf.parsley.dsl.model.AbstractFeatureCaptionProviderWithLabel
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause

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
	 *            {@link org.eclipse.xtext.common.types.JvmDeclaredType declared
	 *            types} from.
	 * @param acceptor
	 *            each created
	 *            {@link org.eclipse.xtext.common.types.JvmDeclaredType type}
	 *            without a container should be passed to the acceptor in order
	 *            get attached to the current resource. The acceptor's
	 *            {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
	 *            accept(..)} method takes the constructed empty type for the
	 *            pre-indexing phase. This one is further initialized in the
	 *            indexing phase using the closure you pass to the returned
	 *            {@link org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing#initializeLater(org.eclipse.xtext.xbase.lib.Procedures.Procedure1)
	 *            initializeLater(..)}.
	 * @param isPreIndexingPhase
	 *            whether the method is called in a pre-indexing phase, i.e.
	 *            when the global index is not yet fully updated. You must not
	 *            rely on linking using the index if isPreIndexingPhase is
	 *            <code>true</code>.
	 */
   	def dispatch void infer(Module element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		if (element.name == null)
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
		val treeFormFactoryClass = element.inferTreeFormFactory(acceptor)
		
		acceptor.accept(moduleClass).initializeLater [
			documentation = element.documentation
			moduleClass.setSuperClassType(element, typeof(EmfParsleyGuiceModule))
			
			members += element.toConstructor() [
				parameters += element.toParameter("plugin", element.newTypeRef(typeof(AbstractUIPlugin)))
				body = [it.append("super(plugin);")]
			]
			
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
			if (treeFormFactoryClass != null)
				members += element.treeFormFactory.genBindMethod(treeFormFactoryClass, typeof(TreeFormFactory))
			
		]

   	}

	def setSuperClassType(JvmGenericType e, WithExtendsClause dslElement, Class<?> defaultSuperClass) {
		if (dslElement.extendsClause != null)
			e.superTypes += dslElement.extendsClause.superType.cloneWithProxies
		else
			e.superTypes += e.newTypeRef(defaultSuperClass)
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

	def treeFormFactoryQN(Module element) {
		element.fullyQualifiedName + ".factory.TreeFormFactoryGen"
	}

	def partsClassQN(Module element) {
		element.fullyQualifiedName + ".ui.parts.DummyPartsClass"
	}

	def inferLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.labelProvider == null)
			null
		else {
			val labelProvider = element.labelProvider
			val labelProviderClass = labelProvider.toClass(element.labelProviderQN)
			acceptor.accept(labelProviderClass).initializeLater [
				setSuperClassType(labelProvider, typeof(ViewerLabelProvider))
				
				members += labelProvider.toConstructor() [
					parameters += labelProvider.
						toParameter("delegate", 
							element.newTypeRef(typeof(AdapterFactoryLabelProvider))
						)
					body = [it.append("super(delegate);")]
					annotations += element.toAnnotation(typeof(Inject))
				]
				
				labelProvider.labelSpecifications.forEach [
					labelSpecification |
					members += labelSpecification.toMethod("text", element.newTypeRef(typeof(String))) [
						parameters += labelSpecification.toParameter(
							if (labelSpecification.name != null)
								labelSpecification.name
							else
								"it"
							, labelSpecification.parameterType
						)
						body = labelSpecification.expression
					]
				]
				
				labelProvider.imageSpecifications.forEach [
					imageSpecification |
					members += imageSpecification.toMethod("image", element.newTypeRef(typeof(Object))) [
						parameters += imageSpecification.toParameter(
							if (imageSpecification.name != null)
								imageSpecification.name
							else
								"it"
							, imageSpecification.parameterType
						)
						body = imageSpecification.expression
					]
				]
			]
			labelProviderClass
		}
	}
	
	def inferTableLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.tableLabelProvider == null)
			null
		else {
			val tableLabelProviderClass = element.tableLabelProvider.toClass(element.tableLabelProviderQN)
			acceptor.accept(tableLabelProviderClass).initializeLater [
				superTypes += element.newTypeRef(typeof(TableColumnLabelProvider))
				
					
				element.tableLabelProvider.labelSpecifications.forEach [
					labelSpecification |
					if (labelSpecification.feature?.simpleName != null) {
						members += labelSpecification.toMethod("text_" + 
							labelSpecification.parameterType.simpleName + "_" +
							labelSpecification.feature.simpleName.propertyNameForGetterSetterMethod
							, element.newTypeRef(typeof(String))
						) [
							parameters += labelSpecification.toParameter(
								"it"
								, labelSpecification.parameterType
							)
							body = labelSpecification.expression
						]
					}
				]
			
				element.tableLabelProvider.imageSpecifications.forEach [
					imageSpecification |
					if (imageSpecification.feature?.simpleName != null) {
						members += imageSpecification.toMethod("image_" + 
							imageSpecification.parameterType.simpleName + "_" +
							imageSpecification.feature.simpleName.propertyNameForGetterSetterMethod
							, element.newTypeRef(typeof(Object))) [
							parameters += imageSpecification.toParameter(
									"it"
								, imageSpecification.parameterType
							)
							body = imageSpecification.expression
						]
					}
				]
			]
			tableLabelProviderClass
		}
	}
	
	def inferFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.featureCaptionProvider == null)
			null
		else {
			val propertyDescriptionProviderClass = element.featureCaptionProvider.toClass(element.featureCaptionProviderQN)
			acceptor.accept(propertyDescriptionProviderClass).initializeLater [
				superTypes += element.newTypeRef(typeof(FeatureCaptionProvider))
				
				inferMethodsForTextPropertyDescription(element, it, element.featureCaptionProvider.specifications)
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
					element.newTypeRef(typeof(String))
				) [
					parameters += spec.toParameter(
						"it", element.newTypeRef(typeof(EStructuralFeature))
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
			acceptor.accept(descriptionProviderClass).initializeLater [
				superTypes += element.newTypeRef(superClass)
				
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
					element.newTypeRef(typeof(Label))
				) [
					parameters += spec.toParameter(
						"parent", element.newTypeRef(typeof(Composite))
					)
					parameters += spec.toParameter(
						"it", element.newTypeRef(typeof(EStructuralFeature))
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
			acceptor.accept(featureProviderClass).initializeLater [
				superTypes += element.newTypeRef(superClass)
				
				documentation = element.documentation
				members += element.
						toMethod("buildStringMap", Void::TYPE.getTypeForName(element)) [
					annotations += element.toAnnotation(typeof(Override))
					parameters += element.toParameter("stringMap",
							element.newTypeRef(
								typeof(EClassToEStructuralFeatureAsStringsMap)
							)
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
			acceptor.accept(controlFactoryClass).initializeLater [
				superTypes += e.newTypeRef(superClass)
				
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
							"dataBindingContext", e.newTypeRef(typeof(DataBindingContext))
						)
						parameters += spec.toParameter(
							"observableValue", e.newTypeRef(typeof(IObservableValue))
						)
						body = [
							append(spec.newTypeRef(typeof(Control)).type)
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
						spec.newTypeRef(typeof(Control))) [
							visibility = JvmVisibility::PROTECTED
							body = spec.expression
					]
					
					members += spec.toMethod
					(createTargetMethodName, 
						spec.newTypeRef(typeof(IObservableValue))) [
							visibility = JvmVisibility::PROTECTED
							parameters += spec.toParameter(
								"it", e.newTypeRef(typeof(Control))
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
			val viewerContentProviderClass = element.viewerContentProvider.toClass(element.viewerContentProviderQN)
			acceptor.accept(viewerContentProviderClass).initializeLater [
				superTypes += element.newTypeRef(typeof(ViewerContentProvider))
				
				members += element.viewerContentProvider.toConstructor() [
					parameters += element.viewerContentProvider.
						toParameter("adapterFactory", 
							element.newTypeRef(typeof(AdapterFactory))
						)
					body = [it.append("super(adapterFactory);")]
					annotations += element.toAnnotation(typeof(Inject))
				]
				
				element.viewerContentProvider.elementsSpecifications.forEach [
					specification |
					members += specification.toMethod("elements", element.newTypeRef(typeof(Object))) [
						parameters += specification.toParameter(
							if (specification.name != null)
								specification.name
							else
								"it"
							, specification.parameterType
						)
						body = specification.expression
					]
				]
				
				element.viewerContentProvider.childrenSpecifications.forEach [
					specification |
					members += specification.toMethod("children", element.newTypeRef(typeof(Object))) [
						parameters += specification.toParameter(
							if (specification.name != null)
								specification.name
							else
								"it"
							, specification.parameterType
						)
						body = specification.expression
					]
				]
			]
			viewerContentProviderClass
		}
	}

	def inferProposalCreator(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.proposalCreator == null)
			null
		else {
			val proposalCreatorClass = element.proposalCreator.toClass(element.proposalCreatorQN)
			acceptor.accept(proposalCreatorClass).initializeLater [
				superTypes += element.newTypeRef(typeof(ProposalCreator))
				
				element.proposalCreator.proposalsSpecifications.forEach [
					spec |
					if (spec.feature?.simpleName != null) {
						// associate the method to the expression, not to the whole
						// specification, otherwise the 'feature' is logically
						// contained in a method which should return a list
						// and the validator would complain
						members += spec.expression.toMethod
						("proposals_" + 
								spec.parameterType.simpleName + "_" +
								spec.feature.simpleName.propertyNameForGetterSetterMethod, 
							element.newTypeRef(typeof(List)).type.createTypeRef(wildCard)
						) [
							parameters += spec.toParameter(
								"it", spec.parameterType
							)
							parameters += spec.toParameter(
								"feature", spec.newTypeRef(typeof(EStructuralFeature))
							)
							body = spec.expression
						]
					}
				]
			]
			proposalCreatorClass
		}
	}
	
	def inferTreeFormFactory(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.treeFormFactory == null)
			null
		else {
			val treeFormFactoryClass = element.treeFormFactory.toClass(element.treeFormFactoryQN)
			acceptor.accept(treeFormFactoryClass).initializeLater [
				superTypes += element.newTypeRef(typeof(TreeFormFactory))
				
				members += element.treeFormFactory.toMethod('createComposite',element.newTypeRef(typeof(TreeFormComposite))) [
						parameters += element.treeFormFactory.toParameter(
						"parent", element.newTypeRef(typeof(Composite))
					)
					parameters += element.treeFormFactory.toParameter(
						"style", element.newTypeRef(typeof(int))
					)
						body = [
				
							append(element.newTypeRef(typeof(TreeFormComposite)).type)
							var weights=''', new int['''
							if((element.treeFormFactory.treeWeight!=0) &&element.treeFormFactory.formWeight!=0){
								weights=weights+'''] {'''+
								element.treeFormFactory.treeWeight +''','''+element.treeFormFactory.formWeight+'''}'''
							} else{
								weights=weights+'''0]'''
							}
							
							
							append(''' control = new TreeFormComposite (parent,	style, ''').
							append(typeof(SWT).findDeclaredType(element)).append('''.''')
							append(element.treeFormFactory.orientation.getName).append(weights.toString).
							append(''');''').newLine
							
							append('''return control;''')
						]
					]
			]
			treeFormFactoryClass
		}
	}

	def control_EClass_EStructuralFeature(
			ControlFactorySpecification spec, XExpression exp, (JvmOperation)=>void init
	) {
		exp.toMethod
			(spec.methodNameForFormFeatureSpecification("control_"), 
				spec.newTypeRef(typeof(Control))
			, init)
	}
	
	def methodNameForFormFeatureSpecification(ControlFactorySpecification spec, String prefix) {
		prefix + 
					spec.parameterType.simpleName + "_" +
					spec.feature.simpleName.propertyNameForGetterSetterMethod
	}
	
	def genBindMethod(EObject element, JvmGenericType type, Class<?> clazz) {
		val wildCard = createJvmWildcardTypeReference
		val upperBound = createJvmUpperBound
		upperBound.typeReference = element.newTypeRef(clazz)
		wildCard.constraints += upperBound
		element.toMethod("bind" + clazz.simpleName, 
				element.newTypeRef(typeof(Class), wildCard) ) [
			annotations += element.toAnnotation(typeof(Override))
			body = [
				append("return ")
				append(type)
				append(".class;")
			]
		]
	}
}

