/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests

import com.google.common.base.Joiner
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Module
import org.eclipse.emf.ecore.resource.impl.ResourceImpl
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.viewers.ViewerFactory
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.TreeViewer
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.util.Wrapper
import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import org.eclipse.xtext.xbase.testing.CompilationTestHelper.Result
import org.eclipse.xtext.xbase.testing.TemporaryFolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(EmfParsleyDslInjectorProvider)
class EmfParsleyDslExecutionTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	@Inject public TemporaryFolder temporaryFolder

	@Inject extension CompilationTestHelper

	val libraryFactory = EXTLibraryFactory.eINSTANCE

	@Test def void testGeneratedLabelProvider() {
		val labelProvider = '''
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
			
			module my.empty extends org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule {
				labelProvider {
					text {
						Library -> 'foo' // constant
						Writer writer -> writer.getName() // explicit param
						Book -> "Title: " + (title ?: "no title") // implit 'it' param
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
		'''.compileInstantiate(ILabelProvider)

		"foo".assertEquals(labelProvider.getText(libraryFactory.createLibrary))
		"Title: no title".assertEquals(labelProvider.getText(libraryFactory.createBook))
		"Title: Foo".assertEquals(labelProvider.getText(libraryFactory.createBook => [title = "Foo"]))
	}

	/**
	 * WARNING: on macOS, this requires SWT thread access, so we must use
	 * <code>-XstartOnFirstThread</code> in the POM
	 */
	@Test def void testGeneratedContentProvider() {
		val viewerFactory = '''
			import org.eclipse.emf.ecore.resource.Resource
			import org.eclipse.emf.parsley.examples.library.Book
			import org.eclipse.emf.parsley.examples.library.Library
			import org.eclipse.emf.parsley.examples.library.Writer
			
			module my.empty extends org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule {
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
		'''.compileInstantiate(ViewerFactory)
		val tree = new TreeViewer(shell)

		val resource = new ResourceImpl => [ res |
			res.contents += libraryFactory.createLibrary => [
				books += libraryFactory.createBook => [
					title = "a book"
				]
				books += libraryFactory.createBook => [
					title = "another book"
				]
				writers += libraryFactory.createWriter => [
					name = "an author"
				]
				// this won't be shown by the content provider
				borrowers += libraryFactory.createBorrower
			]
		]

		viewerFactory.initialize(tree, resource)
		tree.expandAll
		assertAllLabels(tree,
			'''
			Library
			  Book a book
			  Book another book
			  Writer an author
			'''
		)
	}

	def private <T> T compileInstantiate(CharSequence input, Class<T> clazz) {
		val instantiated = Wrapper.forType(clazz)
		input.compile [
			assertNoValidationErrors
			val moduleClassName = "my.empty.EmptyEmfParsleyGuiceModule"
			val moduleClass = getCompiledClass(moduleClassName)
			val cons = moduleClass.getConstructor(AbstractUIPlugin)
			val module = cons.newInstance(#[null]) as Module
			val injector = Guice.createInjector(module)
			val instance = injector.getInstance(clazz)
			instantiated.set(instance)
		]
		return instantiated.get
	}

	private def assertNoValidationErrors(Result it) {
		val allErrors = getErrorsAndWarnings.filter[severity == Severity.ERROR]
		if (!allErrors.empty) {
			throw new IllegalStateException(
				"One or more resources contained errors : " + Joiner.on(',').join(allErrors)
			);
		}
	}

}
