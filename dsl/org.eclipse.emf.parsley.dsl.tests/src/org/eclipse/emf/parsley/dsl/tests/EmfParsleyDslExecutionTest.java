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
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper.Result;
import org.eclipse.xtext.xbase.testing.TemporaryFolder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.base.Joiner;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslExecutionTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	@Inject
	public TemporaryFolder temporaryFolder;

	@Inject
	private CompilationTestHelper compilationTestHelper;

	private final EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;

	@Test
	public void testGeneratedLabelProvider() throws Exception {
		var labelProvider = compileInstantiate("""
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
		""", ILabelProvider.class);

		assertEquals("foo", labelProvider.getText(libraryFactory.createLibrary()));
		assertEquals("Title: no title", labelProvider.getText(libraryFactory.createBook()));
		var book = libraryFactory.createBook();
		book.setTitle("Foo");
		assertEquals("Title: Foo", labelProvider.getText(book));
	}

	/**
	 * WARNING: on macOS, this requires SWT thread access, so we must use
	 * <code>-XstartOnFirstThread</code> in the POM
	 */
	@Test
	public void testGeneratedContentProvider() throws Exception {
		var viewerFactory = compileInstantiate("""
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
		""", ViewerFactory.class);
		var tree = new TreeViewer(getShell());

		var resource = new ResourceImpl();
		var library = libraryFactory.createLibrary();
		resource.getContents().add(library);
		
		var book1 = libraryFactory.createBook();
		book1.setTitle("a book");
		library.getBooks().add(book1);
		
		var book2 = libraryFactory.createBook();
		book2.setTitle("another book");
		library.getBooks().add(book2);
		
		var writer = libraryFactory.createWriter();
		writer.setName("an author");
		library.getWriters().add(writer);
		
		// this won't be shown by the content provider
		library.getBorrowers().add(libraryFactory.createBorrower());

		viewerFactory.initialize(tree, resource);
		tree.expandAll();
		assertAllLabels(tree,
			"""
			Library
			  Book a book
			  Book another book
			  Writer an author
			"""
		);
	}

	@SuppressWarnings("unchecked")
	private <T> T compileInstantiate(CharSequence input, Class<T> clazz) throws Exception {
		final Object[] result = new Object[1];
		compilationTestHelper.compile(input, it -> {
			assertNoValidationErrors(it);
			var moduleClassName = "my.empty.EmptyEmfParsleyGuiceModule";
			try {
				var moduleClass = it.getCompiledClass(moduleClassName);
				var cons = moduleClass.getConstructor(AbstractUIPlugin.class);
				var module = (Module) cons.newInstance(new Object[]{null});
				var injector = Guice.createInjector(module);
				result[0] = injector.getInstance(clazz);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		return (T) result[0];
	}

	private void assertNoValidationErrors(Result it) {
		var allErrors = it.getErrorsAndWarnings().stream()
			.filter(d -> d.getSeverity() == Severity.ERROR)
			.toList();
		if (!allErrors.isEmpty()) {
			throw new IllegalStateException(
				"One or more resources contained errors : " + Joiner.on(',').join(allErrors)
			);
		}
	}

}
