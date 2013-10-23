package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryFormFeatureCaptionProvider;
import org.eclipse.emf.parsley.tests.utils.EmfParsleyTestsUtils;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyProvidersBasedOnViewTests extends EmfParsleyCustomLibraryAbstractTests {

	protected EXTLibraryFactory libFactory = EXTLibraryFactory.eINSTANCE;

	protected EmfParsleyTestsUtils utils = new EmfParsleyTestsUtils();

	@Test
	public void testLibraryFeatureLabelProviderForLabelWidget() {
		final FormFeatureCaptionProvider formFeatureCaptionProvider = getInjector()
				.getInstance(CustomLibraryFormFeatureCaptionProvider.class);
		final SWTBotView view = getTestView();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					// we need a non-null display and parent so we use
					// those in the view and in the tree
					FormToolkit formToolkit = createFormToolkit(view);
					formFeatureCaptionProvider.setFormToolkit(formToolkit);
					Label label = formFeatureCaptionProvider.getLabel(
							createCompositeParent(view),
							EXTLibraryPackage.Literals.WRITER__NAME);
					assertEquals(
							formToolkit.getColors().getColor(IFormColors.TITLE),
							label.getBackground());
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			}
		});
		//closeLibraryView(LIBRARY_EMF_VIEW);
	}

	@Test
	public void testFormFeatureControlFactoryMethodWithTwoParams() {
		final FormControlFactory bindingFactory = getInjector().getInstance(
				FormControlFactory.class);
		final Writer writer = createTestResourceAndWriter();
		final SWTBotView view = getTestView();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					// we need a non-null display and parent so we use
					// those in the view and in the tree
					FormToolkit formToolkit = createFormToolkit(view);
					bindingFactory.init(null, writer,
							createCompositeParent(view), formToolkit);
					Control control = bindingFactory
							.create(EXTLibraryPackage.Literals.WRITER__NAME);
					assertEquals(
							formToolkit.getColors().getColor(IFormColors.TITLE),
							control.getBackground());
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			}
		});
		//closeLibraryView(LIBRARY_EMF_VIEW);
	}

	@Test
	public void testFormFeatureControlFactoryMethodWithOneParam() {
		final FormControlFactory bindingFactory = getInjector().getInstance(
				FormControlFactory.class);
		final Writer writer = createTestResourceAndWriter();
		final SWTBotView view = getTestView();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					// we need a non-null display and parent so we use
					// those in the view and in the tree
					FormToolkit formToolkit = createFormToolkit(view);
					bindingFactory.init(null, writer,
							createCompositeParent(view), formToolkit);
					Control control = bindingFactory
							.create(EXTLibraryPackage.Literals.WRITER__BOOKS);
					assertEquals("Test Book 1, Test Book 1",
							((Label)control).getText());
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			}
		});
		//closeLibraryView(LIBRARY_EMF_VIEW);
	}

	@Test
	public void testFormControlFactoryDefaultProposals() {
		final FormControlFactory bindingFactory = getInjector().getInstance(
				FormControlFactory.class);
		final Writer writer = createTestResourceAndWriter();
		final SWTBotView view = getTestView();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					// we need a non-null display and parent so we use
					// those in the view and in the tree
					FormToolkit formToolkit = createFormToolkit(view);
					bindingFactory.init(null, writer,
							createCompositeParent(view), formToolkit);
					List<?> proposals = bindingFactory
							.createProposals(EXTLibraryPackage.Literals.WRITER__BOOKS);
					assertEquals("Book: Test Book 1, Book: Test Book 1", 
							utils.toStringRep(proposals));
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			}
		});
		//closeLibraryView(LIBRARY_EMF_VIEW);
	}

	@Test
	public void testFormControlFactoryCustomProposals() {
		final FormControlFactory bindingFactory = getInjector().getInstance(
				FormControlFactory.class);
		final Writer writer = createTestResourceAndWriter();
		final SWTBotView view = getTestView();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					// we need a non-null display and parent so we use
					// those in the view and in the tree
					FormToolkit formToolkit = createFormToolkit(view);
					bindingFactory.init(null, writer.getBooks().get(0),
							createCompositeParent(view), formToolkit);
					List<?> proposals = bindingFactory
							.createProposals(EXTLibraryPackage.Literals.BOOK__AUTHOR);
					assertEquals("Writer: Fake Writer, Writer: Fake Writer2", 
							utils.toStringRep(proposals));
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			}
		});
		//closeLibraryView(LIBRARY_EMF_VIEW);
	}

	@Test
	public void testFormControlFactoryCustomAndDefaultProposals() {
		final FormControlFactory bindingFactory = getInjector().getInstance(
				FormControlFactory.class);
		final Writer writer = createTestResourceAndWriter();
		final SWTBotView view = getTestView();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					// we need a non-null display and parent so we use
					// those in the view and in the tree
					FormToolkit formToolkit = createFormToolkit(view);

					Borrower borrower = EXTLibraryFactory.eINSTANCE
							.createBorrower();
					writer.eResource().getContents().add(borrower);

					bindingFactory.init(null, borrower,
							createCompositeParent(view), formToolkit);
					List<?> proposals = bindingFactory
							.createProposals(EXTLibraryPackage.Literals.BORROWER__BORROWED);
					assertEquals("Book: Test Book 1, Book: Test Book 1, Book: Fake Book",
							utils.toStringRep(proposals));
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			}
		});
		//closeLibraryView(LIBRARY_EMF_VIEW);
	}

	@Test
	public void testFormControlFactoryCustomProposalsForFeatureDefinedInBaseClass() {
		final FormControlFactory bindingFactory = getInjector().getInstance(
				FormControlFactory.class);
		final Writer writer = createTestResourceAndWriter();
		final SWTBotView view = getTestView();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					// we need a non-null display and parent so we use
					// those in the view and in the tree
					FormToolkit formToolkit = createFormToolkit(view);
					bindingFactory.init(null, writer.getBooks().get(0),
							createCompositeParent(view), formToolkit);
					List<?> proposals = bindingFactory
							.createProposals(EXTLibraryPackage.Literals.LENDABLE__BORROWERS);
					assertEquals("Borrower: Fake Borrower", 
							utils.toStringRep(proposals));
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			}
		});
		//closeLibraryView(LIBRARY_EMF_VIEW);
	}
	
	protected Writer createTestResourceAndWriter() {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(URI
				.createURI("http:///My.extlibrary"));
		final Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		writer.setFirstName("Test");
		writer.setLastName("Writer");
		createTestBook(resource, writer);
		createTestBook(resource, writer);
		resource.getContents().add(writer);
		return writer;
	}

	protected void createTestBook(Resource resource, Writer writer) {
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		book.setTitle("Test Book 1");
		writer.getBooks().add(book);
		resource.getContents().add(book);
	}

	protected void assertFeatureNames(Iterable<EStructuralFeature> expected,
			Iterable<EStructuralFeature> actual) {
		assertEquals(utils.toStringNameBased(expected),
				utils.toStringNameBased(actual));
	}

	protected void assertFeatureNames(String expected,
			Iterable<EStructuralFeature> actual) {
		assertEquals(expected, utils.toStringNameBased(actual));
	}

	protected void assertLabelForFeature(
			FormFeatureCaptionProvider formFeatureCaptionProvider, String expected,
			EStructuralFeature feature) {
		String labelText = formFeatureCaptionProvider.getText(feature);
		assertEquals(expected, labelText);
	}

	protected FormToolkit createFormToolkit(final SWTBotView view) {
		return new FormToolkit(view.getWidget().getDisplay());
	}

	protected Composite createCompositeParent(final SWTBotView view) {
		return view.bot().tree().widget.getParent();
	}

	protected SWTBotView getTestView() {
		// fake view just for tests
		SWTBotView view = bot.viewByTitle("Problems");
		return view;
		// return openTestView(LIBRARY_EMF_VIEW);
	}
}
