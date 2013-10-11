package org.eclipse.emf.parsley.tests;

import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM;
import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.LENDABLE;
import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.LIBRARY;
import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.PERIODICAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.eclipse.emf.parsley.tests.utils.EmfParsleyTestsUtils;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.tests.labeling.CustomLibraryFormFeatureLabelProvider;
import org.eclipse.emf.parsley.tests.providers.CustomLibraryViewerContentProvider;
import org.eclipse.emf.parsley.tests.providers.LibraryEStructuralFeaturesAsStringsProvider;
import org.eclipse.emf.parsley.tests.providers.LibraryEStructuralFeaturesProvider;
import org.eclipse.emf.parsley.tests.providers.OrderedEStructuralFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesColumnProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormPropertyDescriptionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyProvidersTests extends EmfParsleyCustomLibraryAbstractTests {

	protected EXTLibraryFactory libFactory = EXTLibraryFactory.eINSTANCE;

	protected EmfParsleyTestsUtils utils = new EmfParsleyTestsUtils();

	@Test
	public void testLibraryFeatureLabelProvider() {
		FormPropertyDescriptionProvider formPropertyDescriptionProvider = getInjector()
				.getInstance(CustomLibraryFormFeatureLabelProvider.class);
		assertLabelForFeature(formPropertyDescriptionProvider, "First name",
				EXTLibraryPackage.Literals.PERSON__FIRST_NAME);
		assertLabelForFeature(formPropertyDescriptionProvider, "Last name",
				EXTLibraryPackage.Literals.PERSON__LAST_NAME);
	}

	protected Writer createTestResourceAndWriter() {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(URI
				.createURI("http:///My.extlibrary"));
		final Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		createTestBook(writer);
		createTestBook(writer);
		resource.getContents().add(writer);
		return writer;
	}

	protected void createTestBook(Writer writer) {
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		book.setTitle("Test Book 1");
		writer.getBooks().add(book);
	}

	@Test
	public void testEClassFeatureProviderGetAllFeatures() {
		EClass test = LIBRARY;
		assertFeatureNames(test.getEAllStructuralFeatures(),
				getInjector().getInstance(FeaturesProvider.class)
						.getFeatures(test));
	}

	@Test
	public void testEClassFeatureProviderOrdered() {
		EClass test = LIBRARY;
		assertFeatureNames(
				"address, books, borrowers, branches, employees, name, parentBranch, people, stock, writers",
				getInjector().getInstance(
						OrderedEStructuralFeaturesProvider.class).getFeatures(
						test));
	}

	@Test
	public void testEClassFeatureProviderPolymorphic() {
		FeaturesProvider provider = getInjector().getInstance(
				LibraryEStructuralFeaturesProvider.class);
		assertFeatureNames("name, address", provider.getFeatures(LIBRARY));
		assertFeatureNames("firstName, lastName, address",
				provider.getFeatures(EXTLibraryPackage.Literals.PERSON));
		assertFeatureNames("firstName, lastName, books",
				provider.getFeatures(EXTLibraryPackage.Literals.WRITER));
	}

	@Test
	public void testEClassFeatureProviderAsStringsPolymorphic() {
		FeaturesProvider provider = getInjector().getInstance(
				LibraryEStructuralFeaturesAsStringsProvider.class);
		assertFeatureNames("name, address", provider.getFeatures(LIBRARY));
		assertFeatureNames("firstName, lastName, address",
				provider.getFeatures(EXTLibraryPackage.Literals.PERSON));
		assertFeatureNames("firstName, lastName, books",
				provider.getFeatures(EXTLibraryPackage.Literals.WRITER));
	}

	@Test
	public void testFeaturesColumnProviderDelegated() {
		FeaturesProvider provider = getInjector().getInstance(
				FeaturesColumnProvider.class);
		// this is actually defined in FeaturesProvider
		// and we delegate to it if there's no customization
		assertFeatureNames("copies", provider.getFeatures(LENDABLE));
	}

	@Test
	public void testFeaturesColumnProviderAsStringsDelegated() {
		FeaturesProvider provider = getInjector().getInstance(
				FeaturesColumnProvider.class);
		// this is actually defined in FeaturesProvider
		// and we delegate to it if there's no customization
		assertFeatureNames("title", provider.getFeatures(PERIODICAL));
	}

	@Test
	public void testFeaturesColumnProvider() {
		FeaturesProvider provider = getInjector().getInstance(
				FeaturesColumnProvider.class);
		// this is actually defined in FeaturesColumnProvider
		assertFeatureNames("damaged", provider.getFeatures(AUDIO_VISUAL_ITEM));
	}

	@Test
	public void testFeaturesColumnProviderAsStrings() {
		FeaturesProvider provider = getInjector().getInstance(
				FeaturesColumnProvider.class);
		// this is actually defined in FeaturesColumnProvider
		assertFeatureNames("reader",
				provider.getFeatures(EXTLibraryPackage.Literals.BOOK_ON_TAPE));
	}

	@Test
	public void testCustomViewerContentProvider() throws IOException {
		Library library = localLibrary("My2.extlibrary");
		ViewerContentProvider viewerContentProvider = getInjector()
				.getInstance(CustomLibraryViewerContentProvider.class);
		Object[] libraryChildren = viewerContentProvider.getChildren(library);
		assertLabels(
				"Book: Without Author; Book: First Author's Book; Book: Empty Book; ",
				libraryChildren);
		// the first book has no author, but two borrowers
		assertLabels("Borrower: First Borrower; Borrower: Second Borrower; ",
				viewerContentProvider.getChildren(libraryChildren[0]));
		// the second book has author, and one borrower
		assertLabels("Writer First Author; Borrower: First Borrower; ",
				viewerContentProvider.getChildren(libraryChildren[1]));
		// the third book has neither an author nor s borrower
		assertFalse(viewerContentProvider.hasChildren(libraryChildren[2]));
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
			FormPropertyDescriptionProvider formPropertyDescriptionProvider, String expected,
			EStructuralFeature feature) {
		String labelText = formPropertyDescriptionProvider.getText(feature);
		assertEquals(expected, labelText);
	}

	protected FormToolkit createFormToolkit(final SWTBotView view) {
		return new FormToolkit(view.getWidget().getDisplay());
	}

	protected Composite createCompositeParent(final SWTBotView view) {
		return view.bot().tree().widget.getParent();
	}
}
