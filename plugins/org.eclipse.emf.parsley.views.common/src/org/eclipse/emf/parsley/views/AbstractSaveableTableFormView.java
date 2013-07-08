package org.eclipse.emf.parsley.views;


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.factories.TableFormFactory;
import org.eclipse.emf.parsley.factories.TreeFormFactory;
import org.eclipse.emf.parsley.widgets.TableFormComposite;
import org.eclipse.emf.parsley.widgets.TreeFormComposite;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

public abstract class AbstractSaveableTableFormView extends AbstractSaveableViewerView	{

	@Inject
	protected TableFormFactory tableFormFactory;

	protected TableFormComposite tableFormComposite;

	protected Object getContents(Resource resource) {
		return resource;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		tableFormComposite = tableFormFactory
				.createTableFormMasterDetailComposite(parent, SWT.BORDER,getEClass());

		tableFormComposite.update(getContents(getResource()));

		addContextMenu(tableFormComposite.getViewer());
	}

	@Override
	public void setFocus() {
		tableFormComposite.setFocus();
	}

	public StructuredViewer getViewer() {
		return tableFormComposite.getViewer();
	}
	
	protected abstract EClass getEClass();

	@Override
	protected void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		super.mostRecentCommandAffectsResource(mostRecentCommand);
		// for TableViewer the refresh does not seem to be automatic
		tableFormComposite.getViewer().refresh();
	}
}
