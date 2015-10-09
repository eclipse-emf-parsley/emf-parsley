package org.eclipse.emf.parsley.web.servlets;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;

public class WebViewPart implements IViewPart {

	@Override
	public void addPropertyListener(IPropertyListener listener) {
	}

	@Override
	public void createPartControl(Composite parent) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public IWorkbenchPartSite getSite() {
		return null;
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public Image getTitleImage() {
		return null;
	}

	@Override
	public String getTitleToolTip() {
		return null;
	}

	@Override
	public void removePropertyListener(IPropertyListener listener) {
	}

	@Override
	public void setFocus() {
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public IViewSite getViewSite() {
		return null;
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
	}

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
	}

	@Override
	public void saveState(IMemento memento) {
	}

}
