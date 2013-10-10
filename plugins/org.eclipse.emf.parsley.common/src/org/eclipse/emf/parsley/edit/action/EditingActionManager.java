package org.eclipse.emf.parsley.edit.action;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.views.properties.IPropertySheetPage;

@SuppressWarnings("restriction")
public class EditingActionManager {

	private CommandActionHandler deleteAction;

	private CommandActionHandler cutAction;

	private CommandActionHandler copyAction;

	private CommandActionHandler pasteAction;

	private UndoAction undoAction;

	private RedoAction redoAction;

	public void initializeActions(IActionBars actionBars) {

		createActions();

		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
				deleteAction);
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
				copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
				pasteAction);
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
				undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
				redoAction);
	}

	public void createActions() {
		ISharedImages sharedImages = getSharedImages();

		deleteAction = createDeleteAction();
		deleteAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

		cutAction = createCutAction();
		cutAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));

		copyAction = createCopyAction();
		copyAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));

		pasteAction = createPasteAction();
		pasteAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));

		undoAction = createUndoAction();
		undoAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));

		redoAction = createRedoAction();
		redoAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
	}

	protected ISharedImages getSharedImages() {
		return new ISharedImages() {
			public Image getImage(String symbolicName) {
				return getImageDescriptor(symbolicName).createImage();
			}
			public ImageDescriptor getImageDescriptor(String symbolicName) {
				return (ImageDescriptor) WorkbenchImages.getImageDescriptor(symbolicName);
			}
		};
	}

	public void shareGlobalActions(IPage page, IActionBars actionBars) {
		if (!(page instanceof IPropertySheetPage)) {
			actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					deleteAction);
			actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
					cutAction);
			actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
					copyAction);
			actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
					pasteAction);
		}
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
				undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
				redoAction);
	}

	public void removeSelectionChangeListener(
			ISelectionProvider selectionProvider) {
		selectionProvider.removeSelectionChangedListener(deleteAction);
		selectionProvider.removeSelectionChangedListener(cutAction);
		selectionProvider.removeSelectionChangedListener(copyAction);
		selectionProvider.removeSelectionChangedListener(pasteAction);
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		deleteAction.setEditingDomain(editingDomain);
		cutAction.setEditingDomain(editingDomain);
		copyAction.setEditingDomain(editingDomain);
		pasteAction.setEditingDomain(editingDomain);
		undoAction.setEditingDomain(editingDomain);
		redoAction.setEditingDomain(editingDomain);
	}

	public void addSelectionListener(ISelectionProvider selectionProvider) {
		selectionProvider.addSelectionChangedListener(deleteAction);
		selectionProvider.addSelectionChangedListener(cutAction);
		selectionProvider.addSelectionChangedListener(copyAction);
		selectionProvider.addSelectionChangedListener(pasteAction);
	}

	public void updateSelection(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			deleteAction.updateSelection(structuredSelection);
			cutAction.updateSelection(structuredSelection);
			copyAction.updateSelection(structuredSelection);
			pasteAction.updateSelection(structuredSelection);
		}
	}

	public void updateUndoRedo() {
		undoAction.update();
		redoAction.update();
	}

	public void menuAboutToShow(IMenuManager menuManager) {
		menuManager.add(new ActionContributionItem(undoAction));
		menuManager.add(new ActionContributionItem(redoAction));
		menuManager.add(new Separator());
		menuManager.add(new ActionContributionItem(cutAction));
		menuManager.add(new ActionContributionItem(copyAction));
		menuManager.add(new ActionContributionItem(pasteAction));
		menuManager.add(new Separator());
		menuManager.add(new ActionContributionItem(deleteAction));
		menuManager.add(new Separator());
	}

	protected DeleteAction createDeleteAction() {
		return new DeleteAction(true);
	}

	protected CutAction createCutAction() {
		return new CutAction();
	}

	protected CopyAction createCopyAction() {
		return new CopyAction();
	}

	protected PasteAction createPasteAction() {
		return new PasteAction();
	}

	protected UndoAction createUndoAction() {
		return new UndoAction();
	}

	protected RedoAction createRedoAction() {
		return new RedoAction();
	}

	protected LoadResourceAction createLoadResourceAction() {
		return new LoadResourceAction();
	}

	public ControlAction createControlAction() {
		return new ControlAction();
	}

	public EditingDomainValidateAction createValidateAction() {
		return new EditingDomainValidateAction();
	}

	public UndoAction getUndoAction() {
		return undoAction;
	}

}
