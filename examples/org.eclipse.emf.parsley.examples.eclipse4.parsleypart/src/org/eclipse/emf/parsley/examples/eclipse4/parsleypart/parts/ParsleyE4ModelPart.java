package org.eclipse.emf.parsley.examples.eclipse4.parsleypart.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.edit.actionbar.TreeActionBarContributor;
import org.eclipse.emf.parsley.examples.eclipse4.parsleypart.ParsleypartActivator;
import org.eclipse.emf.parsley.factories.TreeFormFactory;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.emf.parsley.widgets.TreeFormComposite;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Injector;

public class ParsleyE4ModelPart implements IMenuListener {

	//Guice injector
	private Injector injector=ParsleypartActivator.getDefault().getInjector();
	
	// Guice injected EMF Parsley component for contributing to the tree context menu
	private TreeActionBarContributor treeActionBarContributor;
	// Guice injected EMF Parsley factory for the tree detail form
	private TreeFormFactory treeFormFactory;
	// Guice injected EMF Parsley editing domain
	private AdapterFactoryEditingDomain editingDomain;
	// Guice injected viewer initializer
	private ViewerInitializer viewerInitializer;
	
	private TreeFormComposite treeFormComposite;
	
	@Inject
	MApplication application;

	@Inject
	@PostConstruct
	public void postConstruct(Composite parent) {
		treeActionBarContributor = injector.getInstance(TreeActionBarContributor.class);
		// Guice injected EMF Parsley factory for the tree detail form
		treeFormFactory = injector.getInstance(TreeFormFactory.class);
		// Guice injected EMF Parsley editing domain
		editingDomain = injector.getInstance(AdapterFactoryEditingDomain.class);
		// Guice injected viewer initializer
		viewerInitializer = injector.getInstance(ViewerInitializer.class);
		
		// Initialize Parsley Tree Form:  
		// 1) create the tree-form composite
		treeFormComposite = treeFormFactory
				.createTreeFormMasterDetailComposite(parent, SWT.BORDER);

		// 2) initialize and bind the context menu to the tree-form composite
		treeActionBarContributor.initialize(editingDomain);
		viewerInitializer.addContextMenu(treeFormComposite.getViewer(),
				treeActionBarContributor, editingDomain, this);
		// 3) prepare the menu action bar contributor upon the selection
		treeFormComposite.getViewer().addSelectionChangedListener(
						treeActionBarContributor);
		// 4) fill the data
		treeFormComposite.update(application);
	}

	@Override
	public void menuAboutToShow(IMenuManager manager) {
		treeActionBarContributor.menuAboutToShow(manager);
	}

	@Focus
	public void onFocus() {
		treeFormComposite.setFocus();
	}
}