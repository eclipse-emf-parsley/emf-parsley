/**
 * 
 */
package org.eclipse.emf.parsley.factories;


import org.eclipse.emf.parsley.widgets.FormDetailComposite;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author bettini
 * 
 */
public class FormFactory {

	@Inject
	protected MembersInjector<FormDetailComposite> formDetailCompositeMembersInjector;

	@Inject
	public FormFactory() {

	}

	public FormDetailComposite createFormDetailComposite(Composite parent,
			int style) {
		FormDetailComposite formDetailComposite = new FormDetailComposite(parent, style);
		formDetailCompositeMembersInjector.injectMembers(formDetailComposite);
		return formDetailComposite;
	}

}
