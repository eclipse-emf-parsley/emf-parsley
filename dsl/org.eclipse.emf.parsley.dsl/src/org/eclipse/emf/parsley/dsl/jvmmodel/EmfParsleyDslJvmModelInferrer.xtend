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

import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector
import com.google.inject.Provider
import java.io.IOException
import java.util.List
import org.eclipse.core.databinding.observable.value.IObservableValue
import org.eclipse.emf.common.notify.AdapterFactory
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.composite.DialogControlFactory
import org.eclipse.emf.parsley.composite.FormControlFactory
import org.eclipse.emf.parsley.composite.ProposalCreator
import org.eclipse.emf.parsley.config.Configurator
import org.eclipse.emf.parsley.dsl.model.AbstractControlFactory
import org.eclipse.emf.parsley.dsl.model.AbstractFeatureCaptionProviderWithLabel
import org.eclipse.emf.parsley.dsl.model.AbstractFeatureProvider
import org.eclipse.emf.parsley.dsl.model.BindingsSpecification
import org.eclipse.emf.parsley.dsl.model.ContentProviderChildren
import org.eclipse.emf.parsley.dsl.model.ContentProviderElements
import org.eclipse.emf.parsley.dsl.model.ControlFactorySpecification
import org.eclipse.emf.parsley.dsl.model.FeatureAssociatedExpression
import org.eclipse.emf.parsley.dsl.model.FeatureLabels
import org.eclipse.emf.parsley.dsl.model.FeatureTexts
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PolymorphicSpecification
import org.eclipse.emf.parsley.dsl.model.ProviderBinding
import org.eclipse.emf.parsley.dsl.model.SimpleMethodSpecification
import org.eclipse.emf.parsley.dsl.model.TypeBinding
import org.eclipse.emf.parsley.dsl.model.ValueBinding
import org.eclipse.emf.parsley.dsl.model.WithExpressions
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause
import org.eclipse.emf.parsley.dsl.model.WithFeatureAssociatedExpressions
import org.eclipse.emf.parsley.dsl.model.WithFields
import org.eclipse.emf.parsley.dsl.typing.EmfParsleyDslTypeSystem
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder
import org.eclipse.emf.parsley.edit.action.IMenuContributionSpecification
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider
import org.eclipse.emf.parsley.resource.ResourceManager
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory
import org.eclipse.emf.parsley.runtime.ui.PluginUtil
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
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.graphics.Font
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
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

import static extension org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGeneratorUtil.*

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

	@Inject extension EmfParsleyDslTypeSystem

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
		val tableViewerContentProviderClass = element.inferTableViewerContentProvider(acceptor)
		val proposalCreatorClass = element.inferProposalCreator(acceptor)
		val menuBuilderClass = element.inferMenuBuilder(acceptor)
		val configuratorClass = element.inferConfigurator(acceptor)
		val resourceManagerClass = element.inferResourceManager(acceptor)
		
		acceptor.accept(moduleClass) [
			documentation = element.documentation
			moduleClass.setSuperClassType(element, typeof(EmfParsleyGuiceModule))
			
			members += element.toConstructor() [
				parameters += element.toParameter("plugin", typeRef(AbstractUIPlugin))
				// EmfParsleyJavaGuiceModule does not have a constructor with AbstractUIPlugin
				// so we must not call super in the generated constructor
				// bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=474140 
				val extendsClause = element.extendsClause
				if (extendsClause !== null && 
					!(element.isConformant(EmfParsleyGuiceModule, extendsClause.superType))) {
					body = '''// not used'''
				} else {
					body = '''super(plugin);'''
				}
			]

			val bindingsSpecification = element.bindingsSpecification
			if (element.bindingsSpecification !== null) {
				handleBindingsSpecification(it, bindingsSpecification)
			}
			
			if (labelProviderClass !== null)
				members += element.labelProvider.genBindMethod(labelProviderClass, typeof(ILabelProvider))
			if (tableLabelProviderClass !== null)
				members += element.tableLabelProvider.genBindMethod(tableLabelProviderClass, typeof(TableColumnLabelProvider)
				)
			if (featureCaptionProviderClass !== null)
				members += element.featureCaptionProvider.genBindMethod(featureCaptionProviderClass, typeof(FeatureCaptionProvider))
			if (formFeatureCaptionProviderClass !== null)
				members += element.formFeatureCaptionProvider.genBindMethod(formFeatureCaptionProviderClass, typeof(FormFeatureCaptionProvider))
			if (dialogFeatureCaptionProviderClass !== null)
				members += element.dialogFeatureCaptionProvider.genBindMethod(dialogFeatureCaptionProviderClass, typeof(DialogFeatureCaptionProvider))
			if (featureProviderClass !== null)
				members += element.featuresProvider.genBindMethod(featureProviderClass, typeof(FeaturesProvider))
			if (tableFeatureProviderClass !== null)
				members += element.tableFeaturesProvider.genBindMethod(tableFeatureProviderClass, typeof(TableFeaturesProvider))
			if (formControlFactoryClass !== null)
				members += element.formControlFactory.genBindMethod(formControlFactoryClass, typeof(FormControlFactory))
			if (dialogControlFactoryClass !== null)
				members += element.dialogControlFactory.genBindMethod(dialogControlFactoryClass, typeof(DialogControlFactory))
			if (viewerContentProviderClass !== null)
				members += element.viewerContentProvider.genBindMethod(viewerContentProviderClass, typeof(IContentProvider))
			if (tableViewerContentProviderClass !== null)
				members += element.tableViewerContentProvider.genBindMethod(tableViewerContentProviderClass, typeof(TableViewerContentProvider))
			if (proposalCreatorClass !== null)
				members += element.proposalCreator.genBindMethod(proposalCreatorClass, typeof(ProposalCreator))
			if (menuBuilderClass !== null)
				members += element.menuBuilder.genBindMethod(menuBuilderClass, typeof(EditingMenuBuilder))
			if (configuratorClass !== null)
				members += element.configurator.genBindMethod(configuratorClass, typeof(Configurator))
			if (resourceManagerClass !== null)
				members += element.resourceManager.genBindMethod(resourceManagerClass, typeof(ResourceManager))
		]

		val injectorProviderClass = element.toClass(element.injectorProviderQN)
		acceptor.accept(injectorProviderClass) [
			members += element.toField("injector", typeRef(Injector)) => [
				static = true
				visibility = JvmVisibility.PRIVATE
			]
			members += element.toMethod("getInjector", typeRef(Injector)) [
				static = true
				synchronized = true
				body = '''
				if (injector == null) {
				  injector = «Guice».createInjector(
				    new «moduleClass»(«PluginUtil».getPlugin(
				      «PluginUtil».getBundle(«injectorProviderClass».class))));
				}
				return injector;
				'''
			]
		]

		if (element.shouldGenerateExtensions) {
			acceptor.accept(element.toClass(element.executableExtensionFactoryQN)) [
				superTypes += typeRef(AbstractGuiceAwareExecutableExtensionFactory)
				members += element.toMethod("getInjector", typeRef(Injector)) [
					addOverrideAnnotation
					exceptions += typeRef(Exception)
					body = '''
					return «injectorProviderClass».getInjector();
					'''
				]
			]
		}
	}

	def private setSuperClassType(JvmGenericType e, WithExtendsClause dslElement, Class<?> defaultSuperClass) {
		if (dslElement.extendsClause !== null)
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
		target.addAnnotations(annotations.filterNull.filter[annotationType !== null])
	}

	def private moduleQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".EmfParsleyGuiceModule")
	}

	def private injectorProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".InjectorProvider")
	}

	def private labelProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".ui.provider.LabelProvider")
	}

	def private tableLabelProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".ui.provider.TableLabelProvider")
	}

	def private featureCaptionProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".ui.provider.FeatureCaptionProvider")
	}

	def private formFeatureCaptionProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".ui.provider.FormFeatureCaptionProvider")
	}

	def private dialogFeatureCaptionProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".ui.provider.DialogFeatureCaptionProvider")
	}

	def private featuresProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".ui.provider.FeaturesProvider")
	}

	def private tableFeaturesProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".ui.provider.TableFeaturesProvider")
	}

	def private formControlFactoryQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".binding.FormControlFactory")
	}

	def private dialogControlFactoryQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".binding.DialogControlFactory")
	}

	def private viewerContentProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".edit.ui.provider.ViewerContentProvider")
	}

	def private tableViewerContentProviderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".edit.ui.provider.TableViewerContentProvider")
	}

	def private proposalCreatorQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".binding.ProposalCreator")
	}

	def private menuBuilderQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".edit.action.MenuBuilder")
	}

	def private configuratorQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".config.Configurator")
	}

	def private resourceManagerQN(Module element) {
		element.fromModuleToJavaFullyQualifiedName(".resource.ResourceManager")
	}

	def private String fromModuleToJavaFullyQualifiedName(Module element, String templateString) {
		val fqn = element.fullyQualifiedName.toString
		val prefix = fqn.buildClassNameFromProject
		val lastDot = templateString.lastIndexOf(".")
		'''«fqn»«templateString.substring(0, lastDot+1)»«prefix»«templateString.substring(lastDot+1)»'''
	}

	def private inferLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.labelProvider === null)
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
				
				specificationsToMethods(labelProvider.texts, "text", typeRef(String))
				specificationsToMethods(labelProvider.images, "image", typeRef(Object))
				specificationsToMethods(labelProvider.fonts, "font", typeRef(Font))
				specificationsToMethods(labelProvider.foregrounds, "foreground", typeRef(Color))
				specificationsToMethods(labelProvider.backgrounds, "background", typeRef(Color))
			]
			labelProviderClass
		}
	}
	
	def private inferTableLabelProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.tableLabelProvider === null)
			null
		else {
			val tableLabelProvider = element.tableLabelProvider
			val tableLabelProviderClass = tableLabelProvider.toClass(element.tableLabelProviderQN)
			acceptor.accept(tableLabelProviderClass) [
				setSuperClassTypeAndFields(tableLabelProvider, typeof(TableColumnLabelProvider))
				
				inferMethodsForFeatureAssociatedExpression(tableLabelProvider.featureTexts, "text_", typeRef(String), parameterCreatorForFeatureAssociatedExpression)
				inferMethodsForFeatureAssociatedExpression(tableLabelProvider.featureImages, "image_", typeRef(Object), parameterCreatorForFeatureAssociatedExpression)
				inferMethodsForFeatureAssociatedExpression(tableLabelProvider.featureFonts, "font_", typeRef(Font), parameterCreatorForFeatureAssociatedExpression)
				inferMethodsForFeatureAssociatedExpression(tableLabelProvider.featureForegrounds, "foreground_", typeRef(Color), parameterCreatorForFeatureAssociatedExpression)
				inferMethodsForFeatureAssociatedExpression(tableLabelProvider.featureBackgrounds, "background_", typeRef(Color), parameterCreatorForFeatureAssociatedExpression)
				
				specificationsToMethods(tableLabelProvider.rowFonts, "rowFont", typeRef(Font))
				specificationsToMethods(tableLabelProvider.rowForegrounds, "rowForeground", typeRef(Color))
				specificationsToMethods(tableLabelProvider.rowBackgrounds, "rowBackground", typeRef(Color))
			]
			tableLabelProviderClass
		}
	}
	
	def private inferFeatureCaptionProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.featureCaptionProvider === null)
			null
		else {
			val featureCaptionProvider = element.featureCaptionProvider
			val propertyDescriptionProviderClass = featureCaptionProvider.toClass(element.featureCaptionProviderQN)
			acceptor.accept(propertyDescriptionProviderClass) [
				setSuperClassTypeAndFields(featureCaptionProvider, typeof(FeatureCaptionProvider))
				
				inferMethodsForTextCaptionSpecifications(featureCaptionProvider.featureTexts)
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
		if (element === null)
			null
		else {
			val descriptionProviderClass = element.toClass(name)
			acceptor.accept(descriptionProviderClass) [
				setSuperClassTypeAndFields(element, superClass)
				
				inferMethodsForTextCaptionSpecifications(element.featureTexts)
				
				inferMethodsForLabelCaptionSpecifications(element.featureLabels)
			]
			descriptionProviderClass
		}
	}

	def private (JvmOperation, FeatureAssociatedExpression) => void getParameterCreatorForFeatureAssociatedExpression() {
		[
			it, spec |
			parameters += spec.toParameter(
				"it", spec.parameterType
			)
		]
	}

	def private inferMethodsForTextCaptionSpecifications(JvmGenericType it, FeatureTexts texts) {
		inferMethodsForFeatureAssociatedExpression(
			it, texts, "text_", typeRef(String)
		) [
			it, spec |
			parameters += spec.toParameter(
				"it", typeRef(EStructuralFeature)
			)
		]
	}

	def private inferMethodsForLabelCaptionSpecifications(JvmGenericType it, FeatureLabels labels) {
		inferMethodsForFeatureAssociatedExpression(
			it, labels, "label_", typeRef(Label)
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

	def private inferMethodsForFeatureAssociatedExpression(JvmGenericType it, WithFeatureAssociatedExpressions withFeatureAssociatedExpressions, 
		String prefix, JvmTypeReference returnType, (JvmOperation, FeatureAssociatedExpression) => void parameterCreator
	) {
		nullSafeAccess(withFeatureAssociatedExpressions) [
			for (spec : withFeatureAssociatedExpressions.specifications) {
				featureAssociatedExpressionToMethod(spec, prefix, returnType, parameterCreator)
			}
		]
	}

	def private featureAssociatedExpressionToMethod(JvmGenericType it, FeatureAssociatedExpression spec,
			String prefix, JvmTypeReference returnType, (JvmOperation, FeatureAssociatedExpression) => void parameterCreator) {
		if (spec.feature?.simpleName !== null) {
			members += spec.toMethod
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
		if (element === null)
			null
		else {
			val featureProviderClass = element.toClass(name)
			acceptor.accept(featureProviderClass) [
				setSuperClassTypeAndFields(element, superClass)
				
				documentation = element.documentation
				members += element.toMethod("buildStringMap", Void::TYPE.getTypeForName(element)) [
					m |
					m.addOverrideAnnotation
					m.parameters += element.toParameter("stringMap",
							typeRef(EClassToEStructuralFeatureAsStringsMap)
					)
					m.body = [
						a |
						a.append("super.buildStringMap(stringMap);").newLine
						nullSafeAccess(element.features) [
							for (featureSpecification : element.features.featureSpecifications) {
								a.newLine.
									append('''stringMap.mapTo("«featureSpecification.parameterType.identifier»",''').
										increaseIndentation.newLine
								val fs = featureSpecification.features.map [
									feature |
									'"' + feature.simpleName.propertyNameForGetterSetterMethod
									+ '"'
								]
								a.append(fs.join(", "))
								a.append(");").decreaseIndentation
							}
						]
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
		if (e === null)
			null
		else {
			val controlFactoryClass = e.toClass(name)
			acceptor.accept(controlFactoryClass) [
				setSuperClassTypeAndFields(e, superClass)
				
				documentation = e.documentation
				
				nullSafeAccess(e.controls) [
					inferMethodsForControlFactory(e, it, e.controls.specifications)
				]
			]
			controlFactoryClass
		}
	}
	
	def private inferMethodsForControlFactory(AbstractControlFactory e, JvmGenericType it, Iterable<ControlFactorySpecification> specifications) {
		for (spec: specifications) {
			if (spec.feature?.simpleName !== null) {
				if (spec.target === null)
					members += spec.
					control_EClass_EStructuralFeature() [
						parameters += spec.toParameter(
							"it", spec.parameterType
						)
						body = spec.expression
					]
				else {
					val createControlMethodName = spec.methodNameForFormFeatureSpecification("createControl_")
					val createTargetMethodName = spec.methodNameForFormFeatureSpecification("createTarget_")
					members += spec.
					control_EClass_EStructuralFeature() [
						parameters += spec.toParameter(
							"observableValue", typeRef(IObservableValue)
						)
						parameters += spec.toParameter(
							"feature", typeRef(EStructuralFeature)
						)
						body = [
							append(typeRef(Control).type)
							append(''' control = «createControlMethodName»();''').newLine
							append(
							'''
							bindValue(
								feature,
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
		if (element.viewerContentProvider === null)
			null
		else {
			val viewerContentProvider = element.viewerContentProvider
			val viewerContentProviderClass = viewerContentProvider.toClass(element.viewerContentProviderQN)
			acceptor.accept(viewerContentProviderClass) [
				setSuperClassTypeAndFields(viewerContentProvider, typeof(ViewerContentProvider))
				
				members += toConstructorWithInjectedAdapterFactory(viewerContentProvider)
				
				inferContentProviderElements(viewerContentProvider.elements)
				
				inferContentProviderChildren(viewerContentProvider.children)
			]
			viewerContentProviderClass
		}
	}

	def private inferTableViewerContentProvider(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.tableViewerContentProvider === null)
			null
		else {
			val viewerContentProvider = element.tableViewerContentProvider
			val viewerContentProviderClass = viewerContentProvider.toClass(element.tableViewerContentProviderQN)
			acceptor.accept(viewerContentProviderClass) [
				setSuperClassTypeAndFields(viewerContentProvider, typeof(TableViewerContentProvider))
				
				members += toConstructorWithInjectedAdapterFactory(viewerContentProvider)
				
				inferContentProviderElements(viewerContentProvider.elements)
			]
			viewerContentProviderClass
		}
	}

	def private inferContentProviderElements(JvmGenericType it, ContentProviderElements elements) {
		specificationsToMethods(elements, "elements", typeRef(Object))
	}

	def private inferContentProviderChildren(JvmGenericType it, ContentProviderChildren children) {
		specificationsToMethods(children, "children", typeRef(Object))
	}

	def private <T> nullSafeAccess(JvmGenericType it, T parent, (JvmGenericType)=>void acceptor) {
		if (parent !== null) {
			acceptor.apply(it)
		}
	}

	private def toConstructorWithInjectedAdapterFactory(EObject e) {
		e.toConstructor() [
			parameters += e.
				toParameter("adapterFactory",
					typeRef(AdapterFactory)
				)
			body = [it.append("super(adapterFactory);")]
			annotations += annotationRef(Inject)
		]
	}

	def private inferProposalCreator(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.proposalCreator === null)
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
		if (element.menuBuilder === null)
			null
		else {
			val menuBuilder = element.menuBuilder
			val menuBuilderClass = menuBuilder.toClass(element.menuBuilderQN)
			acceptor.accept(menuBuilderClass) [
				setSuperClassTypeAndFields(menuBuilder, EditingMenuBuilder)
				
				val returnType = typeRef(List, typeRef(IMenuContributionSpecification))
				
				specificationsToMethods(menuBuilder.menus, "menuContributions", returnType)
				specificationsToMethods(menuBuilder.emfMenus, "emfMenuContributions", returnType)
			]
			menuBuilderClass
		}
	}

	def private inferConfigurator(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.configurator === null)
			null
		else {
			val configurator = element.configurator
			val configuratorClass = configurator.toClass(element.configuratorQN)
			acceptor.accept(configuratorClass) [
				setSuperClassTypeAndFields(configurator, Configurator)
				
				specificationsToMethods(configurator.resourceURI, "resourceURI", typeRef(URI))
				specificationsToMethods(configurator.EClassSpec, "eClass", typeRef(EClass))
			]
			configuratorClass
		}
	}

	def private inferResourceManager(Module element, IJvmDeclaredTypeAcceptor acceptor) {
		if (element.resourceManager === null)
			null
		else {
			val resourceManager = element.resourceManager
			val resourceManagerClass = resourceManager.toClass(element.resourceManagerQN)
			acceptor.accept(resourceManagerClass) [
				setSuperClassTypeAndFields(resourceManager, ResourceManager)

				resourceManagerElementToMethod
					(resourceManager.initializeBody, "initialize", Void.TYPE.typeRef, null)
				resourceManagerElementToMethod
					(resourceManager.saveBody, "save", Boolean.TYPE.typeRef) [
						exceptions += IOException.typeRef
					]
			]
			resourceManagerClass
		}
	}

	def private resourceManagerElementToMethod(JvmDeclaredType it, 
		SimpleMethodSpecification m, String methodName,
		JvmTypeReference returnType, (JvmOperation)=>void additionalSetup
	) {
		if (m !== null) {
			val method = m.toMethod(methodName, returnType) [
							addOverrideAnnotation
							parameters += m.toParameter("it", typeRef(Resource))
							body = m.body
						]
			additionalSetup?.apply(method)
			members += method
		}
	}

	def private specificationsToMethods(JvmGenericType it, WithExpressions e, String methodName, JvmTypeReference returnType) {
		nullSafeAccess(e) [
			for (specification : e.specifications) {
				members += specificationToMethod(specification, methodName, returnType)
			}
		]
	}

	def private specificationToMethod(PolymorphicSpecification specification, String methodName, JvmTypeReference returnType) {
		specification.toMethod(methodName, returnType) [
			parameters += specification.specificationParameter
			body = specification.expression
		]
	}

	def private specificationParameter(PolymorphicSpecification specification) {
		specification.toParameter(
			if (specification.name !== null)
				specification.name
			else
				"it"
			, specification.parameterType
		)
	}

	def private control_EClass_EStructuralFeature(
			ControlFactorySpecification spec, (JvmOperation)=>void init
	) {
		spec.toMethod
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
			
			if (method !== null) {
				members += method
			}
		}
	}

	def private genBindMethod(JvmGenericType it, TypeBinding typeBinding) {
		if (typeBinding.typeToBind === null) {
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
		if (binding.type === null) {
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
		if (binding.typeDecl === null || binding.id === null) {
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
				addOverrideAnnotation(it)
			}
			acceptor.accept(it)
		]
	}
	
	def private shouldOverride(JvmGenericType it, String methodName) {
		allFeatures.filter(JvmOperation).exists[simpleName == methodName]
	}

	private def addOverrideAnnotation(JvmOperation it) {
		annotations += annotationRef(Override)
	}

}

