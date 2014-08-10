/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.wizards;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelFactory;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.internal.ui.IHelpContextIds;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.eclipse.pde.ui.templates.PluginReference;

@SuppressWarnings("restriction")
public class TableViewTemplateSection extends OptionTemplateSection {
	
	public TableViewTemplateSection() {
		setPageCount(1);
		createOptions();
	}
	
	private static final String KEY_PROJECT_NAME = "packageName";
	private static final String KEY_PREFIX_CLASSESNAME = "prefixClassesname";
	private static final String KEY_EDITABLE = "editableView";
	
	private static final String MASTERDETAIL_VIEWNAME = "org.eclipse.emf.parsley.view.masterdetail.TableMasterDetailView";
	
	public String getUsedExtensionPoint() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public URL getTemplateLocation() {
		// TODO Auto-generated method stub
		return super.getTemplateLocation();
	}
	
	public String[] getNewFiles() {
		return new String[] { "icons/" };
	}
	
	@Override
	public String getSectionId() {
		return "viewtemplate";
	}
	
	@Override
	protected URL getInstallURL() {
		return EmfParsleyWizardsActivator.getDefault().getBundle().getEntry("/");
	}
	
	@Override
	protected ResourceBundle getPluginResourceBundle() {
		return Platform.getResourceBundle(EmfParsleyWizardsActivator.getDefault().getBundle());
	}
	
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0, IHelpContextIds.TEMPLATE_INTRO);
		page.setTitle("Table Master Detail Template");
		page.setDescription("Creates a Table Master Detail view");
		wizard.addPage(page);
		markPagesAdded();
	}
	
	private void createOptions() {
		addOption(KEY_PREFIX_CLASSESNAME, "Prefix classes name", "", 0);
		addOption(KEY_EDITABLE, "Is editable", true, 0);
	}
	
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		String projectId = project.getDescription().getName();
		addOption(KEY_PROJECT_NAME, "Project Name", projectId, 0);
		
		IPluginModelFactory factory = model.getPluginFactory();
		IPluginExtension extensionView = createExtensionView(factory);
		IPluginExtension extensionMenu = createExtensionMenu(factory);
		
		IPluginBase plugin = model.getPluginBase();
		plugin.add(extensionView);
		plugin.add(extensionMenu);
	}
	
	private IPluginExtension createExtensionMenu(IPluginModelFactory factory)
			throws CoreException {
		IPluginExtension extensionMenu = createExtension("org.eclipse.ui.menus", true);

		IPluginElement elementMenu = factory.createElement(extensionMenu);
		elementMenu.setName("menuContribution");
		elementMenu.setAttribute("locationURI", "menu:org.eclipse.ui.main.menu");
		elementMenu.setAttribute("allPopups", "false");
		
		IPluginElement elementSubMenu = factory.createElement(elementMenu);
		elementSubMenu.setName("command");
		elementSubMenu.setAttribute("commandId", "org.eclipse.ui.views.showView");
		elementSubMenu.setAttribute("label", getStringOption(KEY_PREFIX_CLASSESNAME));
		elementSubMenu.setAttribute("tooltip", "Open the Master Detail");
		elementSubMenu.setAttribute("style", "push");
		
		IPluginElement elementSubMenuParameter = factory.createElement(elementSubMenu);
		elementSubMenuParameter.setName("parameter");
		elementSubMenuParameter.setAttribute("name", "org.eclipse.ui.views.showView.viewId");
		elementSubMenuParameter.setAttribute("value", getViewId());
		
		extensionMenu.add(elementMenu);
		elementMenu.add(elementSubMenu);
		elementSubMenu.add(elementSubMenuParameter);

		return extensionMenu;
	}
	
	private IPluginExtension createExtensionView(IPluginModelFactory factory)
			throws CoreException {
		IPluginExtension extensionView = createExtension("org.eclipse.ui.views", true);

		IPluginElement elementView = factory.createElement(extensionView);
		elementView.setName("view");
		elementView.setAttribute("id", getViewId());
		elementView.setAttribute("name", getStringOption(KEY_PREFIX_CLASSESNAME));

		String fullClassName = getAbstractDispatcherPath() + MASTERDETAIL_VIEWNAME;
		elementView.setAttribute("class", fullClassName);
		elementView.setAttribute("icon", "icons/table.jpg");
		
		extensionView.add(elementView);

		return extensionView;
	}
	
	private String getAbstractDispatcherPath() {
		return getStringOption(KEY_PROJECT_NAME) + ".internal.guice." + getStringOption(KEY_PREFIX_CLASSESNAME) + "ExecutableExtensionFactory:";
	}

	private String getViewId() {
		return getStringOption(KEY_PROJECT_NAME) + "." + getStringOption(KEY_PREFIX_CLASSESNAME);
	}

	@Override
	public IPluginReference[] getDependencies(String schemaVersion) {
		List<IPluginReference> result = new ArrayList<IPluginReference>();
		result.addAll(Arrays.asList(super.getDependencies(schemaVersion)));
		result.add(new PluginReference("org.eclipse.core.runtime", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.emf.cdo", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.emf.cdo.net4j", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.net4j.tcp", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.xtext", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.ui.forms", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.core.databinding.property", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.emf.parsley", null, 0)); //$NON-NLS-1$
		result.add(new PluginReference("org.eclipse.emf.parsley.cdo", null, 0)); //$NON-NLS-1$
		return result.toArray(new IPluginReference[result.size()]);
	}
	
}
