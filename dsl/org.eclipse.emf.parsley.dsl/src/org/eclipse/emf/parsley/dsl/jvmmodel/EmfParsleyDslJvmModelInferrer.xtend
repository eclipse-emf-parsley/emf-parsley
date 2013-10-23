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
import org.eclipse.emf.parsley.dsl.model.FeatureCaptionSpecification
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PartSpecification
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
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
		val featureCaptionProviderClass = element.inferFeatureCaptionProvider(acceptor)
		val formFeatureCaptionProviderClass = element.inferFormFeatureCaptionProvider(acceptor)
		val dialogFeatureCaptionProviderClass = element.inferDialogFeatureCaptionProvider(acceptor)
		val featureProviderClass = element.inferFeatureProvider(acceptor)
		val formControlFactoryClass = element.inferFormControlFactory(acceptor)
		val dialogControlFactoryClass = element.inferDialogControlFactory(acceptor)
		val viewerContentProviderClass = element.inferViewerContentProvider(acceptor)
		val proposalCreatorClass = element.inferProposalCreator(acceptor)
		
		acceptor.accept(moduleClass).initializeLater [
			documentation = element.documentation
			moduleClass.setSuperClassType(element, typeof(EmfParsleyGuiceModule))
			
			members += element.toConstructor() [
				parameters += element.toParameter("plugin", element.newTypeRef(typeof(AbstractUIPlugin)))
				body = [it.append("super(plugin);")]
			]
			
			if (labelProviderClass != null)
				members += element.labelProvider.genBindMethod(labelProviderClass, typeof(ILabelProvider))
			if (featureCaptionProviderClass != null)
				members += element.featureCaptionProvider.genBindMethod(featureCaptionProviderClass, typeof(FeatureCaptionProvider))
			if (formFeatureCaptionProviderClass != null)
				members += element.formFeatureCaptionProvider.genBindMethod(formFeatureCaptionProviderClass, typeof(FormFeatureCaptionProvider))
			if (dialogFeatureCaptionProviderClass != null)
				members += element.dialogFeatureCaptionProvider.genBindMethod(dialogFeatureCaptionProviderClass, typeof(DialogFeatureCaptionProvider))
			if (featureProviderClass != null)
				members += element.featuresProvider.genBindMethod(featureProviderClass, typeof(FeaturesProvider))
			if (formControlFactoryClass != null)
				members += element.formControlFactory.genBindMethod(formControlFactoryClass, typeof(FormControlFactory))
			if (dialogControlFactoryClass != null)
				members += element.dialogControlFactory.genBindMethod(dialogControlFactoryClass, typeof(DialogControlFactory))
			if (viewerContentProviderClass != null)
				members += element.viewerContentProvider.genBindMethod(viewerContentProviderClass, typeof(IContentProvider))
			if (proposalCreatorClass != null)
				members += element.proposalCreator.genBindMethod(proposalCreatorClass, typeof(ProposalCreator))
			
		]

   	}

	def setSuperClassType(JvmGenericType e, Module dslElement, Class<?> defaultSuperClass) {
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

	def partsClassQN(Module element) {
		element.fullyQualifiedName + ".ui.parts.DummyPartsClass"
	}

	def inferLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.labelProvider == null)
			null
		else {
			val labelProviderClass = element.labelProvider.toClass(element.labelProviderQN)
			acceptor.accept(labelProviderClass).initializeLater [
				superTypes += element.newTypeRef(typeof(ViewerLabelProvider))
				
				members += element.labelProvider.toConstructor() [
					parameters += element.labelProvider.
						toParameter("delegate", 
							element.newTypeRef(typeof(AdapterFactoryLabelProvider))
						)
					body = [it.append("super(delegate);")]
					annotations += element.toAnnotation(typeof(Inject))
				]
				
				element.labelProvider.labelSpecifications.forEach [
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
				
				element.labelProvider.imageSpecifications.forEach [
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
	
	def inferMethodsForTextPropertyDescription(Module element, JvmGenericType it, Iterable<FeatureCaptionSpecification> specifications) {
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
		if (element.formFeatureCaptionProvider == null)
			null
		else {
			val descriptionProviderClass = element.formFeatureCaptionProvider.toClass(element.formFeatureCaptionProviderQN)
			acceptor.accept(descriptionProviderClass).initializeLater [
				superTypes += element.newTypeRef(typeof(FormFeatureCaptionProvider))
				
				inferMethodsForTextPropertyDescription(element, it, element.formFeatureCaptionProvider.specifications)
				
				inferMethodsForLabelPropertyDescription(element, it, element.formFeatureCaptionProvider.labelSpecifications)
			]
			descriptionProviderClass
		}
	}
	
	def inferMethodsForLabelPropertyDescription(Module element, JvmGenericType it, Iterable<FeatureCaptionSpecification> specifications) {
		for (spec : specifications) {
			if (spec.feature?.simpleName != null) {
				// associate the method to the expression, not to the whole
				// spec, otherwise the 'feature' is logically
				// contained in a method which should return a string
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

	def inferDialogFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.dialogFeatureCaptionProvider == null)
			null
		else {
			val descriptionProviderClass = element.dialogFeatureCaptionProvider.toClass(element.dialogFeatureCaptionProviderQN)
			acceptor.accept(descriptionProviderClass).initializeLater [
				superTypes += element.newTypeRef(typeof(DialogFeatureCaptionProvider))
				
				inferMethodsForTextPropertyDescription(element, it, element.dialogFeatureCaptionProvider.specifications)
				
				inferMethodsForLabelPropertyDescription(element, it, element.dialogFeatureCaptionProvider.labelSpecifications)			
			]
			descriptionProviderClass
		}
	}

	def inferFeatureProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.featuresProvider == null)
			null
		else {
			val featureProviderClass = element.featuresProvider.toClass(element.featuresProviderQN)
			acceptor.accept(featureProviderClass).initializeLater [
				superTypes += element.newTypeRef(typeof(FeaturesProvider))
				
				documentation = element.featuresProvider.documentation
				members += element.featuresProvider.
						toMethod("buildStringMap", Void::TYPE.getTypeForName(element)) [
					annotations += element.toAnnotation(typeof(Override))
					parameters += element.featuresProvider.toParameter("stringMap",
							element.newTypeRef(
								typeof(EClassToEStructuralFeatureAsStringsMap)
							)
					)
					body = [
						append("super.buildStringMap(stringMap);").newLine
						element.featuresProvider.featureSpecifications.forEach [
							featureSpecification |
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
						]
					]
				]
			]
			featureProviderClass
		}
	}

	def inferFormControlFactory(Module e, IJvmDeclaredTypeAcceptor acceptor) {
		if (e.formControlFactory == null)
			null
		else {
			val formFeatureControlFactoryClass = e.formControlFactory.toClass(e.formControlFactoryQN)
			acceptor.accept(formFeatureControlFactoryClass).initializeLater [
				superTypes += e.newTypeRef(typeof(FormControlFactory))
				
				documentation = e.formControlFactory.documentation
				
				inferMethodsForControlFactory(e, it, e.formControlFactory.controlSpecifications)
			]
			formFeatureControlFactoryClass
		}
	}

	def inferDialogControlFactory(Module e, IJvmDeclaredTypeAcceptor acceptor) {
		if (e.dialogControlFactory == null)
			null
		else {
			val controlFactoryClass = e.dialogControlFactory.toClass(e.dialogControlFactoryQN)
			acceptor.accept(controlFactoryClass).initializeLater [
				superTypes += e.newTypeRef(typeof(DialogControlFactory))
				
				documentation = e.formControlFactory.documentation
				
				inferMethodsForControlFactory(e, it, e.dialogControlFactory.controlSpecifications)
			]
			controlFactoryClass
		}
	}
	
	def inferMethodsForControlFactory(Module e, JvmGenericType it, Iterable<ControlFactorySpecification> specifications) {
		for (spec: specifications) {
			if (spec.feature?.simpleName != null) {
				// associate the method to the expression, not to the whole
				// labelSpecification, otherwise the 'feature' is logically
				// contained in a method which should return a string
				// and the validator would complain
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
						// contained in a method which should return a string
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

