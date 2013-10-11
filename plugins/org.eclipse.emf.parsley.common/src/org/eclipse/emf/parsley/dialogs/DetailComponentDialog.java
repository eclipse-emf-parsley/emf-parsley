/**
 * 
 */
package org.eclipse.emf.parsley.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.factories.FormFactory;
import org.eclipse.emf.parsley.widgets.FormDetailComposite;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * @author Francesco Guidieri - Initial contribution and API
 */
public class DetailComponentDialog extends Dialog {

	private String title;
	private EObject eObject;
	
	@Inject
	private FormFactory formFactory;

	public DetailComponentDialog(Shell parentShell, String title, EObject eObject) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.RESIZE | SWT.TITLE | SWT.MAX);
//		setShellStyle(SWT.CLOSE | SWT.MAX | SWT.TITLE | SWT.BORDER
//				| SWT.APPLICATION_MODAL | SWT.RESIZE | getDefaultOrientation());
		this.title = title;
		this.eObject = eObject;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (title != null) {
			newShell.setText(title);
		}
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);
		//DetailComponent detailComponent = new DetailComponent(dialogArea, SWT.NONE, this.eObject);
		Composite detailComposite = new Composite(dialogArea, SWT.NONE);
		detailComposite.setLayout(new GridLayout(1, false));
		final FormDetailComposite detailEmfComponent = formFactory.createFormDetailComposite(detailComposite, SWT.NONE);
		detailEmfComponent.init(eObject);
		detailEmfComponent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(detailComposite);
		createCustomArea(dialogArea);
		return dialogArea;
	}

	protected Composite createCustomArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		applyDialogFont(composite);
		return composite;
	}

}
