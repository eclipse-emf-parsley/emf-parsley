/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.binding;


import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Borrower;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Writer;

/**
 * @author Lorenzo Bettini
 * 
 */
public class CustomLibraryProposalCreator extends ProposalCreator {

	public List<? extends Object> proposals_Book_author(Book book) {
		List<Object> proposals = new LinkedList<Object>();
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		writer.setFirstName("Fake");
		writer.setLastName("Writer");
		proposals.add(writer);
		writer = EXTLibraryFactory.eINSTANCE.createWriter();
		writer.setFirstName("Fake");
		writer.setLastName("Writer2");
		proposals.add(writer);
		return proposals;
	}

	public List<?> proposals_Borrower_borrowed(Borrower b,
			EStructuralFeature feature) {
		List<Object> proposals = defaultProposals(feature);

		Book fakeBook = EXTLibraryFactory.eINSTANCE.createBook();
		fakeBook.setTitle("Fake Book");
		proposals.add(fakeBook);

		return proposals;
	}

	public List<?> proposals_Book_borrowers(Book b,
			EStructuralFeature feature) {
		List<Object> proposals = defaultProposals(feature);

		Borrower fakeBorrower = EXTLibraryFactory.eINSTANCE.createBorrower();
		fakeBorrower.setFirstName("Fake Borrower");
		proposals.add(fakeBorrower);

		return proposals;
	}
}
