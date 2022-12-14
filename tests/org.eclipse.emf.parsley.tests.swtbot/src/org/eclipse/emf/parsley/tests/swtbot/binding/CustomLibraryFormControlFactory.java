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

import static org.eclipse.xtext.xbase.lib.IterableExtensions.join;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.map;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.util.DatabindingUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.xtext.xbase.lib.Functions.Function1;

/**
 * @author Lorenzo Bettini
 * 
 */
public class CustomLibraryFormControlFactory extends FormControlFactory {

//	public ControlObservablePair control_Writer_name(EStructuralFeature feature) {
//		Text t = getToolkit().createText(getParent(), "");
//		t.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TREE_BORDER);
//		t.setBackground(getToolkit().getColors().getColor(IFormColors.TITLE));
//		return new ControlObservablePair(t, SWTObservables.observeText(t,
//				SWT.Modify));
//	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Control control_Writer_name(DataBindingContext dbc,
			IObservableValue featureObservable) {
		Text text = createText("");
		text.setBackground(getToolkit().getColors().getColor(IFormColors.TITLE));
		dbc.bindValue(DatabindingUtil.observeText(text, SWT.Modify),
				featureObservable);
		return text;
	}
	
	public Control control_Writer_books(Writer writer) {
		return createLabel(
				join(map(writer.getBooks(), new Function1<Book, String>() {
					@Override
					public String apply(Book book) {
						return book.getTitle();
					}
				}), ", "));
	}

	public List<?> proposals_Book_author(Book book) {
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
}
