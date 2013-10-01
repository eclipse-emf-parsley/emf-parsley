package org.eclipse.emf.parsley.dsl.ui.wizard;

import java.util.Iterator;

import org.eclipse.emf.parsley.dsl.ui.wizard.template.IWizardTemplate;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.OnSelectionTableFormTemplateWizardHelper;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.OnSelectionTreeFormTemplateWizardHelper;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.SaveableTableFormTemplateWizardHelper;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.SaveableTableTemplateWizardHelper;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.SaveableTreeFormTemplateWizardHelper;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.SaveableTreeTemplateWizardHelper;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.internal.ui.parts.FormBrowser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;

/**
 * @author Francesco Guidieri
 * 
 */
public class EmfParsleyDslProjectTemplateSelectionPage extends WizardPage implements ISelectionChangedListener{

	private static final IWizardTemplate[] INPUT = new IWizardTemplate[]{
			SaveableTreeTemplateWizardHelper.singlethon,
			SaveableTreeFormTemplateWizardHelper.singlethon,
			SaveableTableTemplateWizardHelper.singlethon,
			SaveableTableFormTemplateWizardHelper.singlethon,
			OnSelectionTreeFormTemplateWizardHelper.singlethon,
			OnSelectionTableFormTemplateWizardHelper.singlethon
			};
	public static final String ONSELECTION_CATEGORY = "On selection";
	public static final String SAVEABLE_CATEGORY = "Saveable";

	private TableViewer templateSelectionViewer;
	private GridData gd;
	private FormBrowser descriptionBrowser;
	
	protected EmfParsleyDslProjectTemplateSelectionPage() {
		super("emfParsleySelectPredefinedViewPage");
		descriptionBrowser = new FormBrowser(SWT.BORDER | SWT.V_SCROLL);
		descriptionBrowser.setText(""); //$NON-NLS-1$
	}
	
	public void createDescriptionIn(Composite composite) {
		descriptionBrowser.createControl(composite);
		Control c = descriptionBrowser.getControl();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 200;
		c.setLayoutData(gd);
	}
	
	public void setDescriptionText(String text) {
		if (text == null)
			text = "No Description available.";
		descriptionBrowser.setText(text);
	}

	public void setDescriptionEnabled(boolean enabled) {
		Control dcontrol = descriptionBrowser.getControl();
		if (dcontrol != null)
			dcontrol.setEnabled(enabled);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		initializeDialogUnits(parent);

		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(container, IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);

		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		new Label(container,SWT.NONE).setText("Select one of these Emf Parsley templates");;
		new Label(container,SWT.NONE);
		
		SashForm sashForm = new SashForm(container, SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_BOTH);
		// limit the width of the sash form to avoid the wizard
		// opening very wide. This is just preferred size - 
		// it can be made bigger by the wizard
		// See bug #83356
		gd.widthHint = 300;
		sashForm.setLayoutData(gd);

		templateSelectionViewer = new TableViewer(sashForm, SWT.BORDER);
		templateSelectionViewer.setContentProvider(new ArrayContentProvider());
		templateSelectionViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((IWizardTemplate)element).getLabel();
			}
		});
		
		createDescriptionIn(sashForm);
		templateSelectionViewer.setInput(INPUT);
		templateSelectionViewer.addSelectionChangedListener(this);
		Dialog.applyDialogFont(container);
		setPageComplete(false);
		setControl(container);
	}
	
	public IWizardTemplate getSelectedTemplate(){
		return (IWizardTemplate) ((IStructuredSelection)templateSelectionViewer.getSelection()).getFirstElement();
	}
	
	public void selectionChanged(SelectionChangedEvent event) {
		setErrorMessage(null);
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		IWizardTemplate currentWizardSelection = null;
		Iterator<?> iter = selection.iterator();
		if (iter.hasNext())
			currentWizardSelection = (IWizardTemplate) iter.next();
		if (currentWizardSelection == null) {
			setDescriptionText(""); //$NON-NLS-1$
		}else{
			setDescriptionText(currentWizardSelection.getDescription());			
		}
		setPageComplete(currentWizardSelection!=null);
		getContainer().updateButtons();
	}
	
}
