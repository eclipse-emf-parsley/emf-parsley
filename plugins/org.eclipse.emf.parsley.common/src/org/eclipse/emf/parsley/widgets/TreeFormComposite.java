package org.eclipse.emf.parsley.widgets;


import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.factories.FormFactory;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.PageBook;

import com.google.inject.Inject;

/**
 * A generic composite with a Tree and a Form with details of the selected
 * object in the tree.
 * 
 * @author Lorenzo Bettini, Francesco Guidieri
 * 
 */
public class TreeFormComposite extends Composite implements IViewerProvider {

	protected class SelectionChangedListener implements
			ISelectionChangedListener {
		public void selectionChanged(SelectionChangedEvent event) {
			EObject selectedObject = emfSelectionHelper
					.getFirstSelectedEObject(event.getSelection());

			eObjectSelectionChanged(selectedObject);
		}

	}

	protected ViewerInitializer viewerInitializer;

	protected FormFactory formFactory;

	protected EmfSelectionHelper emfSelectionHelper;

	private final StructuredViewer viewer;

	private final PageBook pagebook;

	private final Composite detail;

	protected FormDetailComposite detailForm;

	public TreeFormComposite(Composite parent, int style) {
		this(parent, style, SWT.VERTICAL, new int[0]);
	}
	
	public TreeFormComposite(Composite parent, int style, int sashStyle, int[] weights) {
		super(parent, style);
		setLayout(new FillLayout());

		SashForm sashForm = new SashForm(this, sashStyle);
		// Lorenzo: these do not seem to be necessary, and they throw an exception
		// when used with RAP
		//GridLayoutFactory.fillDefaults().applyTo(sashForm);
		//GridDataFactory.fillDefaults().grab(true, true).applyTo(sashForm);

		pagebook = new PageBook(sashForm, SWT.BORDER);
		detail = new Composite(sashForm, SWT.BORDER);
		detail.setLayout(new FillLayout());
		viewer = createViewer(pagebook);
		viewer.addSelectionChangedListener(new SelectionChangedListener());
		if(weights.length>0){
			sashForm.setWeights(weights);
		}
	}

	public StructuredViewer getViewer() {
		return viewer;
	}

	public void update(Object element) {
		if (element != null) {
			viewerInitializer.initialize(viewer, element);
			pagebook.showPage(viewer.getControl());
		}

	}

	protected StructuredViewer createViewer(Composite parent) {
		return new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	}

	protected void eObjectSelectionChanged(EObject selectedObject) {
		if (detailForm != null)
			detailForm.dispose();

		if (selectedObject != null) {
			detailForm = createFormDetailComposite();
			detailForm.init(selectedObject);
			detail.layout(true);
		}
	}

	protected FormDetailComposite createFormDetailComposite() {
		return formFactory.createFormDetailComposite(detail,
				SWT.BORDER);
	}

	public ViewerInitializer getViewerInitializer() {
		return viewerInitializer;
	}

	@Inject
	public void setViewerInitializer(ViewerInitializer viewerInitializer) {
		this.viewerInitializer = viewerInitializer;
	}

	public FormFactory getFormFactory() {
		return formFactory;
	}

	@Inject
	public void setFormFactory(FormFactory formFactory) {
		this.formFactory = formFactory;
	}

	public EmfSelectionHelper getEmfSelectionHelper() {
		return emfSelectionHelper;
	}

	@Inject
	public void setEmfSelectionHelper(EmfSelectionHelper emfSelectionHelper) {
		this.emfSelectionHelper = emfSelectionHelper;
	}

}
