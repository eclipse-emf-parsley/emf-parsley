/**
 * 
 */
package org.eclipse.emf.parsley.factories;


import org.eclipse.emf.parsley.widgets.FormDetailComposite;
import org.eclipse.emf.parsley.widgets.FormDetailReadOnlyComposite;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class FormFactory {

	@Inject
	protected MembersInjector<FormDetailComposite> formDetailCompositeMembersInjector;
	
	@Inject
	protected MembersInjector<FormDetailReadOnlyComposite> formDetailReadOnlyCompositeMembersInjector;

	@Inject
	public FormFactory() {

	}

	public FormDetailComposite createFormDetailComposite(Composite parent,
			int style) {
		FormDetailComposite formDetailComposite = new FormDetailComposite(parent, style);
		formDetailCompositeMembersInjector.injectMembers(formDetailComposite);
		return formDetailComposite;
	}
	
	public FormDetailReadOnlyComposite createFormDetailReadOnlyComposite(Composite parent,
			int style) {
		FormDetailReadOnlyComposite formDetailComposite = new FormDetailReadOnlyComposite(parent, style);
		formDetailReadOnlyCompositeMembersInjector.injectMembers(formDetailComposite);
		return formDetailComposite;
	}

}
