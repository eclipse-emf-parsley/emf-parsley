/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.formatting2;

import com.google.inject.Provider
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.dsl.model.AbstractControlFactory
import org.eclipse.emf.parsley.dsl.model.AbstractFeatureCaptionProviderWithLabel
import org.eclipse.emf.parsley.dsl.model.AbstractFeatureProvider
import org.eclipse.emf.parsley.dsl.model.Binding
import org.eclipse.emf.parsley.dsl.model.BindingsSpecification
import org.eclipse.emf.parsley.dsl.model.Configurator
import org.eclipse.emf.parsley.dsl.model.ControlFactorySpecification
import org.eclipse.emf.parsley.dsl.model.ExtendsClause
import org.eclipse.emf.parsley.dsl.model.FeatureAssociatedExpression
import org.eclipse.emf.parsley.dsl.model.FeatureCaptionProvider
import org.eclipse.emf.parsley.dsl.model.FeatureSpecification
import org.eclipse.emf.parsley.dsl.model.FieldSpecification
import org.eclipse.emf.parsley.dsl.model.LabelProvider
import org.eclipse.emf.parsley.dsl.model.MenuBuilder
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PartsSpecifications
import org.eclipse.emf.parsley.dsl.model.PolymorphicSpecification
import org.eclipse.emf.parsley.dsl.model.ProposalCreator
import org.eclipse.emf.parsley.dsl.model.ProviderBinding
import org.eclipse.emf.parsley.dsl.model.ResourceManager
import org.eclipse.emf.parsley.dsl.model.SimpleMethodSpecification
import org.eclipse.emf.parsley.dsl.model.TableLabelProvider
import org.eclipse.emf.parsley.dsl.model.TableViewerContentProvider
import org.eclipse.emf.parsley.dsl.model.TypeBinding
import org.eclipse.emf.parsley.dsl.model.ValueBinding
import org.eclipse.emf.parsley.dsl.model.ViewSpecification
import org.eclipse.emf.parsley.dsl.model.ViewerContentProvider
import org.eclipse.emf.parsley.dsl.model.WithExpressions
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause
import org.eclipse.emf.parsley.dsl.model.WithFeatureAssociatedExpressions
import org.eclipse.emf.parsley.dsl.model.WithFields
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.xbase.annotations.formatting2.XbaseWithAnnotationsFormatter
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation

import static org.eclipse.emf.parsley.dsl.model.ModelPackage.Literals.*

/**
 * @author Lorenzo Bettini
 *
 */
class EmfParsleyDslFormatter extends XbaseWithAnnotationsFormatter {

	override void format(Object o, IFormattableDocument document) {
		// we could use dispatch methods, but that will generate many other
		// if cases for inherited dispatch methods that will never be executed during the
		// tests and I prefer to have full control on code coverage.
		if (o instanceof AbstractControlFactory) {
			_format(o, document);
			return;
		} else if (o instanceof AbstractFeatureCaptionProviderWithLabel) {
			_format(o, document);
			return;
		} else if (o instanceof AbstractFeatureProvider) {
			_format(o, document);
			return;
		} else if (o instanceof Configurator) {
			_format(o, document);
			return;
		} else if (o instanceof FeatureCaptionProvider) {
			_format(o, document);
			return;
		} else if (o instanceof LabelProvider) {
			_format(o, document);
			return;
		} else if (o instanceof MenuBuilder) {
			_format(o, document);
			return;
		} else if (o instanceof ProposalCreator) {
			_format(o, document);
			return;
		} else if (o instanceof ResourceManager) {
			_format(o, document);
			return;
		} else if (o instanceof TableLabelProvider) {
			_format(o, document);
			return;
		} else if (o instanceof TableViewerContentProvider) {
			_format(o, document);
			return;
		} else if (o instanceof ViewerContentProvider) {
			_format(o, document);
			return;
		} else if (o instanceof ControlFactorySpecification) {
			_format(o, document);
			return;
		} else if (o instanceof FeatureAssociatedExpression) {
			_format(o, document);
			return;
		} else if (o instanceof FeatureSpecification) {
			_format(o, document);
			return;
		} else if (o instanceof Module) {
			_format(o, document);
			return;
		} else if (o instanceof ProviderBinding) {
			_format(o, document);
			return;
		} else if (o instanceof TypeBinding) {
			_format(o, document);
			return;
		} else if (o instanceof ValueBinding) {
			_format(o, document);
			return;
		} else if (o instanceof ViewSpecification) {
			_format(o, document);
			return;
		} else if (o instanceof BindingsSpecification) {
			_format(o, document);
			return;
		} else if (o instanceof ExtendsClause) {
			_format(o, document);
			return;
		} else if (o instanceof FieldSpecification) {
			_format(o, document);
			return;
		} else if (o instanceof PolymorphicSpecification) {
			_format(o, document);
			return;
		} else if (o instanceof Model) {
			_format(o, document);
			return;
		} else if (o instanceof PartsSpecifications) {
			_format(o, document);
			return;
		} else if (o instanceof SimpleMethodSpecification) {
			_format(o, document);
			return;
		} else {
			super.format(o, document)
		}
	}

	def void _format(Model model, extension IFormattableDocument document) {
		format(model.getImportSection(), document);
		format(model.getModule(), document);
	}

	def void _format(Module module, extension IFormattableDocument document) {
		indentBlock(module, document) [
			format(module.getExtendsClause(), document);
			format(module.getBindingsSpecification(), document);
			format(module.getLabelProvider(), document);
			format(module.getTableLabelProvider(), document);
			format(module.getFeatureCaptionProvider(), document);
			format(module.getFormFeatureCaptionProvider(), document);
			format(module.getDialogFeatureCaptionProvider(), document);
			format(module.getFeaturesProvider(), document);
			format(module.getTableFeaturesProvider(), document);
			format(module.getFormControlFactory(), document);
			format(module.getDialogControlFactory(), document);
			format(module.getProposalCreator(), document);
			format(module.getMenuBuilder(), document);
			format(module.getConfigurator(), document);
			format(module.getViewerContentProvider(), document);
			format(module.getTableViewerContentProvider(), document);
			format(module.getPartsSpecifications(), document);
			format(module.getResourceManager(), document);
		]
	}

	def void _format(ExtendsClause extendsclause, extension IFormattableDocument document) {
		format(extendsclause.getSuperType(), document);
		extendsclause.regionFor.keyword("extends").surround[oneSpace]
	}

	def void _format(BindingsSpecification bindingsspecification, extension IFormattableDocument document) {
		indentBlock(bindingsspecification, document) [
			formatAndSeparateElementsWithNewlines(bindingsspecification.bindings, document)
		]
	}

	def void _format(TypeBinding typebinding, extension IFormattableDocument document) {
		oneSpaceAfterKeyword(typebinding, "type", document)
		format(typebinding.getTypeToBind(), document);
		formatBinding(typebinding, document)
	}

	def void _format(ProviderBinding providerbinding, extension IFormattableDocument document) {
		oneSpaceAfterKeyword(providerbinding, "provide", document)
		format(providerbinding.getType(), document);
		formatBinding(providerbinding, document)
	}

	def void _format(ValueBinding valuebinding, extension IFormattableDocument document) {
		oneSpaceAfterKeyword(valuebinding, "value", document)
		format(valuebinding.getTypeDecl(), document);
		valuebinding.regionFor.feature(VALUE_BINDING__ID).surround[oneSpace]
		formatBinding(valuebinding, document)
	}

	def private void formatBinding(Binding binding, extension IFormattableDocument document) {
		binding.regionFor.keyword("->").surround[oneSpace]
		format(binding.getTo(), document);
	}

	def void _format(LabelProvider labelprovider, extension IFormattableDocument document) {
		formatWithFields(labelprovider, document) [
			formatSpecifications(labelprovider.texts, document)
			formatSpecifications(labelprovider.images, document)
			formatSpecifications(labelprovider.fonts, document)
			formatSpecifications(labelprovider.foregrounds, document)
			formatSpecifications(labelprovider.backgrounds, document)
		]
	}

	def void _format(FieldSpecification expr, extension IFormattableDocument document) {
		for (XAnnotation annotations : expr.getAnnotations()) {
			format(annotations, document);
		}
		expr.regionFor.keyword("val").append[oneSpace]
		expr.regionFor.keyword("var").append[oneSpace]
		expr.regionFor.keyword("extension").append[oneSpace]
		expr.type.append[oneSpace]
		expr.regionFor.keyword("=").surround[oneSpace]
		expr.type.format(document)
		expr.right.format(document)
		expr.regionFor.keyword(";").prepend[noSpace]
	}

	def void _format(TableLabelProvider tablelabelprovider, extension IFormattableDocument document) {
		formatWithFields(tablelabelprovider, document) [
			formatFeatureSpecifications(tablelabelprovider.featureTexts, document)
			formatFeatureSpecifications(tablelabelprovider.featureImages, document)
			formatFeatureSpecifications(tablelabelprovider.featureFonts, document)
			formatFeatureSpecifications(tablelabelprovider.featureForegrounds, document)
			formatFeatureSpecifications(tablelabelprovider.featureBackgrounds, document)
			formatSpecifications(tablelabelprovider.rowFonts, document)
			formatSpecifications(tablelabelprovider.rowForegrounds, document)
			formatSpecifications(tablelabelprovider.rowBackgrounds, document)
		]
	}

	def void _format(PolymorphicSpecification specification, extension IFormattableDocument document) {
		format(specification.getParameterType(), document);
		specification.regionFor.keyword("->").surround[oneSpace]
		format(specification.getExpression(), document);
	}

	def void _format(FeatureCaptionProvider featurecaptionprovider, extension IFormattableDocument document) {
		formatWithFields(featurecaptionprovider, document) [
			indentBlockAndFormatAndSeparateElementsWithNewlines(featurecaptionprovider.featureTexts, document) [
				featurecaptionprovider.featureTexts.specifications
			]
		]
	}

	def void _format(AbstractFeatureCaptionProviderWithLabel featurecaptionprovider,
		extension IFormattableDocument document) {
		formatWithFields(featurecaptionprovider, document) [
			formatFeatureSpecifications(featurecaptionprovider.featureTexts, document)
			formatFeatureSpecifications(featurecaptionprovider.featureLabels, document)
		]
	}

	def void _format(FeatureAssociatedExpression featureassociatedexpression,
		extension IFormattableDocument document) {
		format(featureassociatedexpression.getParameterType(), document);
		featureassociatedexpression.regionFor.keyword(":").surround[oneSpace]
		featureassociatedexpression.regionFor.keyword("->").surround[oneSpace]
		format(featureassociatedexpression.getExpression(), document);
	}

	def void _format(AbstractFeatureProvider featuresprovider, extension IFormattableDocument document) {
		formatWithFields(featuresprovider, document) [
			indentBlockAndFormatAndSeparateElementsWithNewlines(featuresprovider.features, document) [
				featuresprovider.features.featureSpecifications
			]
		]
	}

	def void _format(FeatureSpecification featurespecification, extension IFormattableDocument document) {
		format(featurespecification.getParameterType(), document);
		featurespecification.regionFor.keyword("->").surround[oneSpace]
		for (comma : featurespecification.regionFor.keywords(",")) {
			comma.prepend[noSpace].append[oneSpace]
		}
	}

	def void _format(AbstractControlFactory controlfactory, extension IFormattableDocument document) {
		formatWithFields(controlfactory, document) [
			indentBlockAndFormatAndSeparateElementsWithNewlines(controlfactory.controls, document) [
				controlfactory.controls.specifications
			]
		]
	}

	def void _format(ControlFactorySpecification controlfactoryspecification,
		extension IFormattableDocument document) {
		format(controlfactoryspecification.getParameterType(), document);
		controlfactoryspecification.regionFor.keyword(":").surround[oneSpace]
		controlfactoryspecification.regionFor.keyword("->").surround[oneSpace]
		format(controlfactoryspecification.getExpression(), document);
		controlfactoryspecification.regionFor.keyword("target").surround[oneSpace]
		format(controlfactoryspecification.getTarget(), document);
	}

	def void _format(ProposalCreator proposalcreator, extension IFormattableDocument document) {
		formatWithFields(proposalcreator, document) [
			formatAndSeparateElementsWithNewlines(proposalcreator.proposalsSpecifications, document)
		]
	}

	def void _format(MenuBuilder menubuilder, extension IFormattableDocument document) {
		formatWithFields(menubuilder, document) [
			formatSpecifications(menubuilder.menus, document)
			formatSpecifications(menubuilder.emfMenus, document)
		]
	}

	def void _format(Configurator configurator, extension IFormattableDocument document) {
		formatWithFields(configurator, document) [
			formatSpecifications(configurator.resourceURI, document)
			formatSpecifications(configurator.EClassSpec, document)
		]
	}

	def void _format(ViewerContentProvider viewercontentprovider, extension IFormattableDocument document) {
		formatWithFields(viewercontentprovider, document) [
			formatSpecifications(viewercontentprovider.elements, document)
			formatSpecifications(viewercontentprovider.children, document)
		]
	}

	def void _format(TableViewerContentProvider tableviewercontentprovider,
		extension IFormattableDocument document) {
		formatWithFields(tableviewercontentprovider, document) [
			formatSpecifications(tableviewercontentprovider.elements, document)
		]
	}

	def void _format(ResourceManager resourcemanager, extension IFormattableDocument document) {
		formatWithFields(resourcemanager, document) [
			format(resourcemanager.getInitializeBody(), document);
			format(resourcemanager.getSaveBody(), document);
		]
	}

	def void _format(SimpleMethodSpecification simplemethodspecification, extension IFormattableDocument document) {
		formatAndSeparateWithNewlines(simplemethodspecification.body, document)
	}

	def void _format(PartsSpecifications partsspecifications, extension IFormattableDocument document) {
		indentBlock(partsspecifications, document) [
			formatAndSeparateElementsWithNewlines(partsspecifications.parts, document)
		]
	}

	def void _format(ViewSpecification viewspecification, extension IFormattableDocument document) {
		indentBlock(viewspecification, document) [
			viewspecification.regionFor.keyword("viewpart").append[oneSpace]

			viewspecification.regionFor.keyword("viewname").append[oneSpace]
			viewspecification.regionFor.feature(VIEW_SPECIFICATION__VIEW_NAME).append[setNewLines(1, 1, 2)]

			viewspecification.regionFor.keyword("viewclass").append[oneSpace]
			format(viewspecification.type, document);
			viewspecification.type.append[setNewLines(1, 1, 2)]

			if (viewspecification.category !== null) {
				viewspecification.regionFor.keyword("viewcategory").append[oneSpace]
				viewspecification.regionFor.feature(VIEW_SPECIFICATION__CATEGORY).append[setNewLines(1, 1, 2)]
			}
		]
	}

	def private formatWithFields(WithFields e, extension IFormattableDocument document,
		(WithFields)=>void elementsFormatter) {
		formatWithExtendsClause(e, document) [
			formatFields(e, document)
			elementsFormatter.apply(e)
		]
	}

	def private formatWithExtendsClause(WithExtendsClause e, extension IFormattableDocument document,
		(WithExtendsClause)=>void elementsFormatter) {
		format(e.getExtendsClause(), document);
		indentBlock(e, document) [
			elementsFormatter.apply(e)
		]
		e.separateElementsWithNewlines(document)
	}

	def private formatFields(WithFields withFields, extension IFormattableDocument document) {
		formatAndSeparateElementsWithNewlines(withFields.fields, document)
	}


	def private <T extends EObject> formatSpecifications(WithExpressions e, extension IFormattableDocument document) {
		indentBlockAndFormatAndSeparateElementsWithNewlines(e, document) [
			e.specifications
		]
	}

	def private <T extends EObject> formatFeatureSpecifications(WithFeatureAssociatedExpressions e, extension IFormattableDocument document) {
		indentBlockAndFormatAndSeparateElementsWithNewlines(e, document) [
			e.specifications
		]
	}

	/**
	 * The elementsProvider is invoked only if the passed parent is not null
	 */
	def private <T extends EObject> indentBlockAndFormatAndSeparateElementsWithNewlines(EObject parent,
		extension IFormattableDocument document, Provider<List<? extends T>> elementsProvider) {
		if (parent !== null) {
			val list = elementsProvider.get
			indentBlock(parent, document) [
				formatAndSeparateElementsWithNewlines(list, document)
			]
			separateElementsWithNewlines(parent, document)
		}
	}

	def private <T extends EObject> formatAndSeparateElementsWithNewlines(List<? extends T> elements, extension IFormattableDocument document) {
		for (e : elements) {
			formatAndSeparateWithNewlines(e, document)
		}
	}
	
	private def <T extends EObject> formatAndSeparateWithNewlines(T e, IFormattableDocument document) {
		format(e, document)
		separateElementsWithNewlines(e, document)
	}

	def private <T extends EObject> oneSpaceAfterKeyword(T element, String k, extension IFormattableDocument document) {
		element.regionFor.keyword(k).append[oneSpace]
	}

	def private <T extends EObject> separateElementsWithNewlines(T element, extension IFormattableDocument document) {
		element.append[setNewLines(1, 1, 2)]
	}

	def private <T extends EObject> indentBlock(T element, extension IFormattableDocument document,
		(T)=>void elementsFormatter) {
		val open = element.regionFor.keyword("{")
		val close = element.regionFor.keyword("}")
		open.append[newLine]
		interior(open, close)[indent]
		elementsFormatter.apply(element)
	}
}

