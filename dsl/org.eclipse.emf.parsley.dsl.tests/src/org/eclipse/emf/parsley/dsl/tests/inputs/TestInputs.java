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
package org.eclipse.emf.parsley.dsl.tests.inputs;

public class TestInputs {
	
	public CharSequence emptyModule() {
		return """
module my.empty {
	
}
""";
	}

	public CharSequence moduleWithExtends() {
		return """
module my.empty extends org.eclipse.emf.parsley.dsl.tests.additional.MyTestGuiceModule {
	
}
""";
	}

	public CharSequence moduleWithExtendsJavaGuiceModule() {
		return """
module my.empty extends org.eclipse.emf.parsley.dsl.tests.additional.MyTestJavaGuiceModule {
	
}
""";
	}

	public CharSequence emptyLabelProvider() {
		return """
module my.empty {
	labelProvider {
		
	}
}
""";
	}

	public CharSequence emptyPropertyDescriptionProvider() {
		return """
module my.empty {
	featureCaptionProvider {
		
	}
}
""";
	}

	public CharSequence emptyLabelSpecifications() {
		return """
module my.empty {
	labelProvider {
		text {
			
		}
		image {
			
		}
	}
}
""";
	}

	public CharSequence emptyLabelSpecificationsForColumns() {
		return """
module my.empty {
	tableLabelProvider {
		text {
			
		}
		image {
			
		}
	}
}
""";
	}


	public CharSequence labelProvider() {
		return """
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.BookOnTape
import org.eclipse.emf.parsley.examples.library.Borrower
import org.eclipse.emf.parsley.examples.library.Lendable
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.swt.graphics.ImageData
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

module my.empty {
	labelProvider {
		text {
			Library -> 'foo' // constant
			Writer writer -> writer.getName() // explicit param
			Book -> title // implit 'it' param
			Lendable -> 'copies: ' + copies
			Borrower -> { // complex block
				val buffer = 'borrowed: ' + borrowed.map [
					b | b.copies
				]
				buffer.toUpperCase
			}
			BookOnTape -> getTitle
		}
		
		image {
			Library -> 'library.jpeg' // constant
			Writer writer -> 
				if (writer.name.nullOrEmpty) 
					"noname.gif"
				else
					new ImageData("writer.jpeg")
		}
		
		font {
			Library -> JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT)
		}
		
		foreground {
			Library -> Display.getCurrent().getSystemColor(SWT.COLOR_BLUE)
		}
		
		background {
			Library -> Display.getCurrent().getSystemColor(SWT.COLOR_GREEN)
		}
	}
}
""";
	}

	public CharSequence labelProviderWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestLabelProvider

module my.empty {
	labelProvider extends TestLabelProvider {
		
	}
}
""";
	}

	public CharSequence labelProviderWithFields() {
		return """
import java.util.ArrayList
import java.util.List
import com.google.inject.Inject
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.emf.parsley.dsl.tests.inputs.TestExtensions
import org.eclipse.emf.parsley.dsl.tests.inputs.TestExtensions2
import org.eclipse.emf.parsley.examples.library.Library

module my.empty {
	labelProvider {
		@Inject
		var ILabelProvider parentLabelProvider;
		
		// if you use extension you need the declared type
		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=408323
		val extension TestExtensions myExtensions = new TestExtensions();

		// inferred type
		val myExtensions2 = new TestExtensions2();
		
		// initialize it with a complex expression
		val List<String> listOfString = new ArrayList() => [
			it += "first"
			it += "second"
		]
		
		text {
			Library -> {
				val myList = new ArrayList()
				myList.printList() // extension method from TestExtensions
				myExtensions2.printList2(myList) 
				return 'result: ' + parentLabelProvider.getText(it)
			}
		}
	}
}
""";
	}

	public CharSequence tableLabelProvider() {
		return """
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.swt.graphics.ImageData
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

module my.empty {
	tableLabelProvider {
		text {
			Library:name -> 'Name' // constant
			Library:books -> 'Books' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
		
		image {
			Book: author -> 
				if (author.name.nullOrEmpty) 
					"noname.gif"
				else
					new ImageData("writer.jpeg")
		}
		
		font {
			Library : name -> JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT)
		}
		
		foreground {
			Library : books -> Display.getCurrent().getSystemColor(SWT.COLOR_BLUE)
		}
		
		background {
			Library : address -> Display.getCurrent().getSystemColor(SWT.COLOR_GREEN)
		}
		
		rowFont {
			Library -> JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT)
		}
		
		rowForeground {
			Library -> Display.getCurrent().getSystemColor(SWT.COLOR_BLUE)
		}
		
		rowBackground {
			Library -> Display.getCurrent().getSystemColor(SWT.COLOR_GREEN)
		}
	}
}
""";
	}

	public CharSequence tableLabelProviderWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestTableColumnLabelProvider

module my.empty {
	tableLabelProvider extends TestTableColumnLabelProvider {
		
	}
}
""";
	}

	public CharSequence tableLabelProviderWithFields() {
		return """
import com.google.inject.Inject
import org.eclipse.jface.viewers.ILabelProvider

module my.empty {
	tableLabelProvider {
		@Inject
		var ILabelProvider parentLabelProvider;
	}
}
""";
	}

	public CharSequence featureCaptionProvider() {
		return """
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	featureCaptionProvider {
		text {
			Library:name -> 'Name' // constant
			Library:books -> 'Books' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
	}
}
""";
	}

	public CharSequence featureCaptionProviderWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestFeatureCaptionProvider

import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	featureCaptionProvider extends TestFeatureCaptionProvider {
		text {
			Library:name -> 'Name' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
	}
}""";
	}

	public CharSequence formFeatureCaptionProvider() {
		return """
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Label

module my.empty {
	formFeatureCaptionProvider {
		text {
			Library:name -> 'Name' // constant
			Library:books -> 'Books' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
		label {
			Library:name -> createLabel(parent, "Name")
			Library:books -> createLabel(parent, EXTLibraryPackage.eINSTANCE.library, EXTLibraryPackage.eINSTANCE.library_Books)
			Writer:lastName -> {
				new Label(parent, SWT.NONE) => [
					l |
					l.^text = name
				] 
				// the implicit param is an EStructuralFeature
				// 'name' comes from EStructuralFeature
			}
		}
	}
}
""";
	}

	public CharSequence formFeatureCaptionProviderWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestFormFeatureCaptionProvider

module my.empty {
	formFeatureCaptionProvider extends TestFormFeatureCaptionProvider {
	}
}
""";
	}

	public CharSequence dialogFeatureCaptionProvider() {
		return """
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Label

module my.empty {
	dialogFeatureCaptionProvider {
		text {
			Library:name -> 'Name' // constant
			Library:books -> 'Books' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
		label {
			Library:name -> createLabel(parent, "Name")
			Library:books -> createLabel(parent, EXTLibraryPackage.eINSTANCE.library, EXTLibraryPackage.eINSTANCE.library_Books)
			Writer:lastName -> {
				new Label(parent, SWT.NONE) => [
					l |
					l.^text = name
				] 
				// the implicit param is an EStructuralFeature
				// 'name' comes from EStructuralFeature
			}
		}
	}
}
""";
	}

	public CharSequence dialogFeatureCaptionProviderWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestDialogFeatureCaptionProvider

module my.empty {
	dialogFeatureCaptionProvider extends TestDialogFeatureCaptionProvider {
	}
}
""";
	}

	public CharSequence featuresProvider() {
		return """
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	featuresProvider {
		features {
			Library -> name
			Writer -> firstName, lastName, books
		}
	}
}
""";
	}

	public CharSequence featuresProviderWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestFeaturesProvider

module my.empty {
	featuresProvider extends TestFeaturesProvider {
	}
}
""";
	}

	public CharSequence tableFeaturesProvider() {
		return """
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	tableFeaturesProvider {
		features {
			Library -> name
			Writer -> firstName, lastName, books
		}
	}
}
""";
	}

	public CharSequence tableFeaturesProviderWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestTableFeaturesProvider

module my.empty {
	tableFeaturesProvider extends TestTableFeaturesProvider {
	}
}
""";
	}

	public CharSequence formControlFactory() {
		return """
import org.eclipse.emf.parsley.examples.library.Borrower
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	formControlFactory {
		control {
			Library : name -> { }
			Writer : books -> 
				createLabel(
					books.map[title].join(", "))
			Writer : name -> { createLabel(parent, "") }
				target { observeText }
			Writer : firstName -> 
				toolkit.createLabel(parent, "")
				target observeText(SWT::Modify)
			Borrower : firstName -> {
				createText(firstName, SWT::MULTI, SWT::BORDER,
									SWT::WRAP, SWT::V_SCROLL)
			}
		}
	}
}
""";
	}

	public CharSequence formControlFactoryWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestFormControlFactory

module my.empty {
	formControlFactory extends TestFormControlFactory {
	}
}
""";
	}

	public CharSequence dialogControlFactory() {
		return """
import org.eclipse.emf.parsley.examples.library.Borrower
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.swt.SWT

module my.empty {
	dialogControlFactory {
		control {
			Library : name -> { }
			Writer : books -> 
				createLabel(
					books.map[title].join(", "))
			Writer : name -> { createLabel(parent, "") }
				target { observeText }
			Writer : firstName -> 
				createLabel(parent, "")
				target observeText(SWT::Modify)
			Borrower : firstName -> {
				createText(firstName, SWT::MULTI, SWT::BORDER,
									SWT::WRAP, SWT::V_SCROLL)
			}
		}
	}
}
""";
	}

	public CharSequence dialogControlFactoryWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestDialogControlFactory

module my.empty {
	dialogControlFactory extends TestDialogControlFactory {
	}
}
""";
	}

	public CharSequence proposalCreator() {
		return """
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.Borrower
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	proposals {
		Library : name -> { newArrayList('foo', 'bar') }
		Writer : books -> 
			{ books } // it.books
		Book : author -> {
			newArrayList(
				EXTLibraryFactory::eINSTANCE.createWriter => [
					name = "Foo"
				],
				EXTLibraryFactory::eINSTANCE.createWriter => [
					name = "Bar"
				]
			)
		}
		Borrower : borrowed -> {
			val p = defaultProposals(feature) => [
				add(EXTLibraryFactory::eINSTANCE.createBook => [
					title = "Fake Book"
				])
			]
			return p
		}
	}
}
""";
	}

	public CharSequence proposalCreatorWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestProposalCreator

module my.empty {
	proposals extends TestProposalCreator {
	}
}
""";
	}

	public CharSequence viewerContentProvider() {
		return """
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	viewerContentProvider {
		elements {
			Resource -> allContents.filter(Library)
		}
		
		children {
			Library -> books + writers
			Writer writer -> writer.books
			Book -> author // implit 'it' param
		}
	}
}
""";
	}

	public CharSequence tableViewerContentProvider() {
		return """
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	tableViewerContentProvider {
		elements {
			Resource -> allContents.filter(Library)
			Library -> books + writers
			Writer writer -> writer.books
			Book -> author // implit 'it' param
		}
	}
}
""";
	}

	public CharSequence viewerContentProviderWithExtends() {
		return """
import org.eclipse.emf.parsley.dsl.tests.inputs.TestViewerContentProvider

module my.empty {
	viewerContentProvider extends TestViewerContentProvider {
	}
}
""";
	}

	public CharSequence configuratorExample() {
		return """
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.common.util.URI

module my.empty {
	
	configurator {
		val EXTLibraryPackage libraryPackage = EXTLibraryPackage.eINSTANCE;
		
		// the implementations below do not necessarily make sense:
		// they are used only to test the DSL
		
		resourceURI {
			Library lib -> URI.createFileURI("file:/foo")
			Writer -> null
		}
		
		eClass {
			Library lib -> libraryPackage.library
			Writer -> libraryPackage.writer
		}
	}
}
""";
	}

	public CharSequence resourceManagerExample() {
		return """
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory

module my.empty {

	resourceManager {
		val EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;
		
		initializeResource {
			getContents() += libraryFactory.createLibrary
		}
		saveResource {
			it.save(null)
			return true
		}
	}
}
""";
	}

	public CharSequence emptyViewsSpecifications() {
		return """
module my.empty {
	parts {
		
	}
}
""";
	}

	public CharSequence nonEmptyViewsSpecifications() {
		return """
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView

module my.test {
	parts {
		viewpart my.view.part {
			viewname "My View"
			viewclass AbstractSaveableTreeView
		}
	}
}
""";
	}

	public CharSequence multipleViewsSpecifications() {
		return """
import org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView

module my.test {
	parts {
		viewpart my.view.tree.part {
			viewname "My Tree View"
			viewclass AbstractSaveableTreeView
		}
		viewpart my.view.form.part {
			viewname "My Tree Form View"
			viewclass AbstractSaveableTreeFormView
			viewcategory my.view.category
		}
	}
}
""";
	}

}
