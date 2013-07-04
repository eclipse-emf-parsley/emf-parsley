/**
 * 
 */
package org.eclipse.emf.parsley.factories;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.widgets.TableFormComposite;
import org.eclipse.emf.parsley.widgets.TreeFormComposite;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author bettini
 * 
 */
public class TableFormFactory {

	@Inject
	protected MembersInjector<TableFormComposite> tableFormCompositeMembersInjector;

	@Inject
	public TableFormFactory() {

	}

	public TableFormComposite createTableFormMasterDetailComposite(
			Composite parent, int style, EClass type) {
		TableFormComposite tableFormComposite = new TableFormComposite(parent,style);
		tableFormCompositeMembersInjector.injectMembers(tableFormComposite);
		tableFormComposite.buildTable(type);
		return tableFormComposite;
	}
	
	public TableFormComposite createTableFormMasterDetailComposite(
			Composite parent, int style) {
		TableFormComposite tableFormComposite = new TableFormComposite(parent,style, true);
		tableFormCompositeMembersInjector.injectMembers(tableFormComposite);
		return tableFormComposite;
	}
}
