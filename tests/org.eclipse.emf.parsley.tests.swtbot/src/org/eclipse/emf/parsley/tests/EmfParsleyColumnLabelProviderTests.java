package org.eclipse.emf.parsley.tests;

import static org.eclipse.emf.parsley.examples.library.EXTLibraryPackage.Literals.WRITER__NAME;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.factories.ColumnLabelProviderFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Francesco Guidieri
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyColumnLabelProviderTests extends
		EmfParsleyCustomLibraryAbstractTests {

	@Test
	public void testLabelProviderForAuthorName() {
		ColumnLabelProvider labelProvider = createLabelProvider(WRITER__NAME);

		Library library = createTestLibraryWithPeople();
		Writer writer = library.getWriters().get(0);

		assertEquals("Writer My Writer", labelProvider.getText(writer));
	}

	protected ColumnLabelProvider createLabelProvider(
			EStructuralFeature eStructuralFeature) {
		return getInjector().getInstance(ColumnLabelProviderFactory.class)
				.createColumnLabelProvider(eStructuralFeature);
	}

	protected Library createTestLibraryWithPeople() {
		Library library = EXTLibraryFactory.eINSTANCE.createLibrary();
		library.setName("My Library");
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		writer.setName("My Writer");
		library.getWriters().add(writer);
		return library;
	}
}
