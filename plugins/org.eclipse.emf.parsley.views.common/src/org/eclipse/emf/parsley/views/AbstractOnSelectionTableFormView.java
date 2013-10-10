/**
 *
 */
package org.eclipse.emf.parsley.views;


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.factories.TableFormFactory;
import org.eclipse.emf.parsley.factories.ViewerFactory;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.widgets.TableFormComposite;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.google.inject.Inject;

/**
 * A View that visualizes the list of elements of an EObject (it also acts as a
 * selection provider), filtered by the specified type.
 *
 * @author Lorenzo Bettini
 *
 */
public abstract class AbstractOnSelectionTableFormView extends
		AbstractOnSelectionView {

	@Inject
	protected ViewerFactory viewerFactory;

	@Inject
	protected FeaturesProvider featuresProvider;

	@Inject
	protected TableFormFactory tableFormFactory;

	protected TableFormComposite tableFormDetailComposite;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		tableFormDetailComposite = tableFormFactory.createTableFormMasterDetailComposite(parent,
				SWT.BORDER, getEClass());
	}

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection) {

		EObject eObject = getFirstSelectedEObject(selection);
		tableFormDetailComposite.update(null);
		if (eObject != null) {
			update(eObject);
		}
	}

	protected void update(EObject eObject) {
		EStructuralFeature feature = getEStructuralFeature();
		List<EStructuralFeature> allowedFeatures= featuresProvider.getFeatures(eObject);
		if (!allowedFeatures.contains(feature))
			return;

		Object value = eObject.eGet(feature);
		tableFormDetailComposite.update(value);
	}


	public void setFocus() {
		tableFormDetailComposite.getViewer().getControl().setFocus();
	}

	/**
	 * @return the {@link EStructuralFeature} to retrieve the values of the
	 *         selected {@link EObject} to show on the table
	 */
	protected abstract EStructuralFeature getEStructuralFeature();

	/**
	 * @return the {@link EClass} to build the table columns
	 */
	protected abstract EClass getEClass();
}
