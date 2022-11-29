package org.eclipse.emf.parsley.tests.swtbot.menu;

import java.util.List;

import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.edit.action.IMenuContributionSpecification;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;

public class CustomLibraryMenuBuilder extends EditingMenuBuilder {
	public List<IMenuContributionSpecification> emfMenuContributions(final Writer w) {
		return List.of(actionChange("New book", (Library) w.eContainer(),
			library -> {
				var book = EXTLibraryFactory.eINSTANCE.createBook();
				library.getBooks().add(book);
				book.setTitle("A new book");
				book.setAuthor(w);
			}
		)
		);
	}
}
