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
package org.eclipse.emf.parsley.dsl.tests;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.formatter.FormatterTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslFormatterTest extends EmfParsleyDslAbstractTest {

	@Inject
	private FormatterTestHelper formatterTestHelper;

	@Test
	public void testFormatPartSpecification() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
module Foo {
	parts {
		viewpart my.view.tree.part {
			viewname "My Tree View"
			viewclass AbstractSaveableTreeView
			viewcategory My.Category
		}
		viewpart my.view.tree.part2 {
			viewname "My Tree View"
			viewclass AbstractSaveableTreeView
		}
	}
}
""".replace("\r", ""));
			request.setToBeFormatted("""
module Foo { parts { viewpart \

	my.view.tree.part { viewname  "My Tree View" viewclass  AbstractSaveableTreeView 	viewcategory  My.Category}
	viewpart my.view.tree.part2 { viewname  "My Tree View" viewclass  AbstractSaveableTreeView 	}
	}
}
""".replace("\r", ""));
		});
	}

	@Test
	public void testFieldSpecifications() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.ecore.resource.Resource
import com.google.inject.Inject

module my.empty {
	tableViewerContentProvider {
		val field = "a field";
		@Inject
		val field2 = "a field";
		var f1 = 0;
		var extension int f2 = 0;
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.ecore.resource.Resource
import com.google.inject.Inject

module my.empty {
	tableViewerContentProvider { 		val field  =  "a field"; @Inject
		val field2 = "a field";  var  f1  =  0 ; var  extension  int  f2  =  0 ;
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testExtends() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
module my.empty {
	tableViewerContentProvider extends Foo {
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
module my.empty {
	tableViewerContentProvider  extends  Foo {
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testResourceManager() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
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
""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory

				module my.empty {
					resourceManager {
						val EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;
						
						initializeResource { 				getContents() += libraryFactory.createLibrary
						}
						saveResource {
							it.save(null)
							return true
						}
					}
				}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testResourceManagerWithoutSaveResource() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory

module my.empty {
	resourceManager {
		val EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;

		initializeResource {
			getContents() += libraryFactory.createLibrary
		}
	}
}
""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory

				module my.empty {
					resourceManager {
						val EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;
						
						initializeResource { 				getContents() += libraryFactory.createLibrary
						}
					}
				}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testTableViewerContentProvider() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.ecore.resource.Resource

module my.empty {
	tableViewerContentProvider {
		val field = "a field";
		elements {
			Resource -> {
				getContents()
			}
		}
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.ecore.resource.Resource

module my.empty {
	tableViewerContentProvider { 		val field  =  "a field";
	elements { 			Resource  ->  { 				getContents()
			}
		}
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testTableViewerContentProviderWithoutElements() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.ecore.resource.Resource

module my.empty {
	tableViewerContentProvider {
		val field = "a field";
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.ecore.resource.Resource

module my.empty {
	tableViewerContentProvider { 		val field  =  "a field";
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testViewerContentProvider() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.EObject

module my.empty {
	viewerContentProvider {
		val field = "a field";
		elements {
			Resource -> {
				getContents()
			}
		}
		children {
			Resource -> {
				getContents()
			}
			EObject -> {
				emptyList
			}
		}
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.EObject

module my.empty {
	viewerContentProvider { 		val field  =  "a field";
	elements { 			Resource  ->  { 				getContents()
			}
		}
		children { 			Resource  ->  { 				getContents()
			} EObject  ->  { 				emptyList
			}
		}
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testConfigurator() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.common.util.URI

module my.empty {
	configurator {
		val EXTLibraryPackage libraryPackage = EXTLibraryPackage.eINSTANCE;

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
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.common.util.URI

module my.empty {
	
	configurator { 		val EXTLibraryPackage libraryPackage = EXTLibraryPackage.eINSTANCE;
		
		resourceURI { 			Library lib -> URI.createFileURI("file:/foo") 			Writer -> null
		}
	eClass { 			Library lib -> libraryPackage.library 			Writer -> libraryPackage.writer
		}
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testMenuBuilder() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	menuBuilder {
		val EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;

		menus {
			Object -> #[
				actionRedo,
				actionUndo,
				separator,
				actionCopy,
				actionPaste
			]

			Writer -> {
				#[
					actionUndo,
					separator,
					submenu("Submenu1", #[
						actionCopy,
						submenu("Submenu2", #[
							actionCut
						])
					]),
					actionPaste
				]
			}
		}

		emfMenus {
			Library lib -> newArrayList(
				actionAdd(
					"New Writer",
					lib.writers,
					libraryFactory.createWriter => [
						name = "This is a new writer"
					]
				)
			)
			Writer -> #[
				actionAdd(
					"New book",
					books,
					libraryFactory.createBook => [
						title = "New book"
					]
				)
			]
		}
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	
	menuBuilder {
		val EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;
		
		menus {
			Object  ->  #[
				actionRedo,
				actionUndo,
				separator,
				actionCopy,
				actionPaste
			]
			
			Writer -> {
				#[
					actionUndo,
					separator,
					submenu("Submenu1", #[
						actionCopy,
						submenu("Submenu2", #[
							actionCut
						])
					]),
					actionPaste
				]
			}
		}
		
		emfMenus {
			Library lib  ->  newArrayList(
				actionAdd("New Writer", lib.writers, 
					libraryFactory.createWriter => [
						name = "This is a new writer"
					]
				)
			)
			Writer -> #[
				actionAdd("New book", books,
					libraryFactory.createBook => [
						title = "New book"
					]
				)
			]
		}
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testControlFactory() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.parsley.examples.library.Borrower
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	formControlFactory {
		control {
			Library : name -> {
			}
			Writer : books -> createLabel(books.map[title].join(", "))
			Writer : name -> {
				createLabel(parent, "")
			} target {
				observeText
			}
			Writer : firstName -> toolkit.createLabel(parent, "") target observeText(SWT::Modify)
			Borrower : firstName -> {
				createText(firstName, SWT::MULTI, SWT::BORDER, SWT::WRAP, SWT::V_SCROLL)
			}
		}
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.parsley.examples.library.Borrower
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	formControlFactory {
		control {
			Library : name -> { }
			Writer  :  books -> 
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
			""".replace("\r", ""));
		});
	}

	@Test
	public void testFeaturesProvider() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
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
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	featuresProvider {
		features {
			Library  ->  name
			Writer -> firstName ,  lastName, books
		}
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testFeatureCaptionProvider() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	featureCaptionProvider {
		text {
			Library : name -> 'Name'
			Library : books -> 'Books'
			Writer : lastName -> name.toFirstUpper
		}
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	featureCaptionProvider {
		text {
			Library:name -> 'Name'
			Library:books  ->  'Books'
			Writer:lastName -> name.toFirstUpper
		}
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testCaptionProviderWithLabel() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Label

module my.empty {
	formFeatureCaptionProvider {
		text {
			Library : name -> 'Name' // constant
			Library : books -> 'Books' // constant
			Writer : lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
		label {
			Library : name -> createLabel(parent, "Name")
			Library : books -> createLabel(parent, EXTLibraryPackage.eINSTANCE.library,
				EXTLibraryPackage.eINSTANCE.library_Books)
			Writer : lastName -> {
				new Label(parent, SWT.NONE) => [ l |
					l.^text = name
				]
			// the implicit param is an EStructuralFeature
			// 'name' comes from EStructuralFeature
			}
		}
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
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
			""".replace("\r", ""));
		});
	}

	@Test
	public void testTableLabelProvider() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
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
			Library : name -> 'Name' // constant
			Library : books -> 'Books' // constant
			Writer : lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}

		image {
			Book : author -> if (author.name.nullOrEmpty)
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
			""".replace("\r", ""));
			request.setToBeFormatted("""
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
			""".replace("\r", ""));
		});
	}

	@Test
	public void testLabelProvider() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
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
				val buffer = 'borrowed: ' + borrowed.map [ b |
					b.copies
				]
				buffer.toUpperCase
			}
			BookOnTape -> getTitle
		}

		image {
			Library -> 'library.jpeg' // constant
			Writer writer -> if (writer.name.nullOrEmpty)
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
			""".replace("\r", ""));
			request.setToBeFormatted("""
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
			""".replace("\r", ""));
		});
	}

	@Test
	public void testBindings() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.IBaseLabelProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider

module my.empty {
	bindings {
		type ILabelProvider -> ViewerLabelProvider
		type IBaseLabelProvider -> ViewerLabelProvider
		provide IBaseLabelProvider -> ViewerLabelProvider
		value IBaseLabelProvider a -> ViewerLabelProvider
	}
}
			""".replace("\r", ""));
			request.setToBeFormatted("""
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.IBaseLabelProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider

module my.empty {
	bindings {
		type  ILabelProvider  ->  ViewerLabelProvider
		type  IBaseLabelProvider -> ViewerLabelProvider
		provide  IBaseLabelProvider  ->  ViewerLabelProvider
		value IBaseLabelProvider  a  ->  ViewerLabelProvider
	}
}
			""".replace("\r", ""));
		});
	}

	@Test
	public void testProposals() {
		formatterTestHelper.assertFormatted(request -> {
			request.setExpectation("""
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.Borrower
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer

module my.empty {
	proposals {
		Library : name -> {
			newArrayList('foo', 'bar')
		}
		Writer : books -> {
			books
		} // it.books
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
			""".replace("\r", ""));
			request.setToBeFormatted("""
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
			""".replace("\r", ""));
		});
	}

}
