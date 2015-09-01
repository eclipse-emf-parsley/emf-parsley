/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/

package org.eclipse.emf.parsley;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.composite.FormFactory;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.composite.TreeFormFactory;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.ecore.FeatureResolver;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.OnTheFlyEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.action.EditingActionManager;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.edit.actionbar.TreeActionBarContributor;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.edit.domain.DefaultAdapterFactoryEditingDomainProvider;
import org.eclipse.emf.parsley.edit.provider.InjectableAdapterFactory;
import org.eclipse.emf.parsley.edit.ui.dnd.ViewerDragAndDropHelper;
import org.eclipse.emf.parsley.edit.ui.provider.InjectableAdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.handlers.OutlineSelectionHandler;
import org.eclipse.emf.parsley.listeners.IEditorMouseListener;
import org.eclipse.emf.parsley.listeners.OpenDialogMouseAdapter;
import org.eclipse.emf.parsley.listeners.OpenPropertyViewMouseAdapter;
import org.eclipse.emf.parsley.menus.ViewerContextMenuHelper;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.resource.ResourceSaveStrategy;
import org.eclipse.emf.parsley.runtime.service.AbstractGenericModule;
import org.eclipse.emf.parsley.runtime.ui.ClassLoaderImageHelper;
import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.emf.parsley.util.FeatureHelper;
import org.eclipse.emf.parsley.validation.DiagnosticUtil;
import org.eclipse.emf.parsley.validation.DialogErrorReporter;
import org.eclipse.emf.parsley.validation.IssueReporter;
import org.eclipse.emf.parsley.validation.LogIssueReporter;
import org.eclipse.emf.parsley.validation.ValidationRunner;
import org.eclipse.emf.parsley.viewers.ColumnLabelProviderFactory;
import org.eclipse.emf.parsley.viewers.IViewerMouseListener;
import org.eclipse.emf.parsley.viewers.TableViewerColumnBuilder;
import org.eclipse.emf.parsley.viewers.ViewerFactory;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;

import com.google.inject.Binder;
import com.google.inject.Provider;

/**
 * Default Google Guice bindings; this can be used in a pure Java environment, without
 * requiring an OSGI - Plug-in environment.
 * 
 * @author Lorenzo Bettini
 * @author Francesco Guidieri - only Javadocs :-)
 * 
 */
public class EmfParsleyJavaGuiceModule extends AbstractGenericModule {

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
	}

	/**
	 * The String constant for Content Assist Shortcut
	 */
	public String valueContentAssistShortcut() {
		return "Ctrl+Space";
	}

	/**
	 * The String constant used as a separator for Iterable string representation
	 */
	public String valueIterableStringSeparator() {
		return ", ";
	}

	/**
	 * The String constant used as a ellipses for Iterable string representation
	 * when it is too long
	 */
	public String valueIterableStringEllipses() {
		return "...";
	}

	/**
	 * The Integer constant used as the maximum length for Iterable string representation
	 */
	public Integer valueIterableStringMaxLength() {
		return 80;
	}

	/**
	 * The Integer constant used as the default weight for a table's columns
	 */
	public Integer valueDefaultTableColumnWeight() {
		return 3;
	}

	/**
	 * The list of Integer weights for a table's columns
	 */
	public List<Integer> valueTableColumnWeights() {
		return Collections.<Integer>emptyList();
	}

	/**
	 * The int constant defining the Sash style in a TreeFormComposite
	 */
	public int valueTreeFormSashStyle() {
		return SWT.VERTICAL;
	}

	/**
	 * The list of Integer weights for the Sash of a TreeFormComposite
	 */
	public List<Integer> valueTreeFormSashWeights() {
		return Collections.<Integer>emptyList();
	}

	/**
	 * Use this binding to provide your own implementation of getImages methods.
	 * Default implementation is {@link ClassLoaderImageHelper}.
	 * @return a specialization of {@link IImageHelper}
	 * 
	 */
	public Class<? extends IImageHelper> bindIImageHelper() {
		return ClassLoaderImageHelper.class;
	}

	/**
	 * Use this binding to change the way your resources are loaded from the framework.
	 * @return a specialization of {@link ResourceLoader}
	 * 
	 */
	public Class<? extends ResourceLoader> bindResourceLoader() {
		return ResourceLoader.class;
	}

	/**
	 * Use this binding to define the mouse listener for editors.
	 * Default implementation is {@link OpenPropertyViewMouseAdapter}
	 * @return a specialization of {@link IEditorMouseListener}
	 * @see MouseListener
	 * @see IViewerMouseListener
	 */
	public Class<? extends IEditorMouseListener> bindIEditorMouseListener() {
		return OpenPropertyViewMouseAdapter.class;
	}

	/**
	 * Use this binding to define the mouse listener for viewers.
	 * Default implementation is {@link OpenDialogMouseAdapter}
	 * @return a specialization of {@link IViewerMouseListener}
	 * @see MouseListener
	 */
	public Class<? extends IViewerMouseListener> bindIViewerMouseListener() {
		return OpenDialogMouseAdapter.class;
	}

	/**
	 * Use this binding to implement the label provider.
	 * Default implementation is {@link ViewerLabelProvider}
	 * @return a specialization of {@link ILabelProvider}
	 */
	public Class<? extends ILabelProvider> bindILabelProvider() {
		return ViewerLabelProvider.class;
	}

	/**
	 * Use this binding to provide the caption provider for your EStructuralFeatures.
	 * @return a specialization of {@link FeatureCaptionProvider}
	 * @see FormFeatureCaptionProvider
	 * @see DialogFeatureCaptionProvider
	 */	
	public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
		return FeatureCaptionProvider.class;
	}

	/**
	 * Use this binding to provide the caption provider only for Forms.
	 * @return a specialization of {@link FormFeatureCaptionProvider}
	 * @see FeatureCaptionProvider
	 * @see DialogFeatureCaptionProvider  
	 */	
	public Class<? extends FormFeatureCaptionProvider> bindFormFeatureCaptionProvider() {
		return FormFeatureCaptionProvider.class;
	}

	/**
	 * Use this binding to provide the caption provider only for Dialogs.
	 * @return a specialization of {@link DialogFeatureCaptionProvider}
	 * @see FeatureCaptionProvider
	 * @see FormFeatureCaptionProvider  
	 */	
	public Class<? extends DialogFeatureCaptionProvider> bindDialogFeatureCaptionProvider() {
		return DialogFeatureCaptionProvider.class;
	}

	/**
	 * Use this binding to provide an handler for outline selection in editors.
	 * @return a specialization of {@link OutlineSelectionHandler}
	 */
	public Class<? extends OutlineSelectionHandler> bindOutlineSelectionHandler() {
		return OutlineSelectionHandler.class;
	}

	/**
	 * Use this bind to change the way JFace viewers (tree and tables) are built
	 * @return a specialization of {@link ViewerFactory}
	 */
	public Class<? extends ViewerFactory> bindViewerFactory() {
		return ViewerFactory.class;
	}

	/**
	 * Use this bind to provide a factory that builds Tree Form components
	 * @return a specialization of {@link TreeFormFactory}
	 * @see FormFactory
	 */
	public Class<? extends TreeFormFactory> bindTreeFormFactory() {
		return TreeFormFactory.class;
	}

	/**
	 * Use this bind to provide a factory that builds Form components
	 * @return a specialization of {@link FormFactory}
	 * @see TreeFormFactory
	 */
	public Class<? extends FormFactory> bindFormFactory() {
		return FormFactory.class;
	}

	/**
	 * Use this bind to change the viewers context menu mechanisms. 
	 * @return a specialization of {@link ViewerContextMenuHelper}
	 */
	public Class<? extends ViewerContextMenuHelper> bindViewerContextMenuHelper() {
		return ViewerContextMenuHelper.class;
	}

	/**
	 * Use this bind to change the viewers drag and drop mechanisms. 
	 * @return a specialization of {@link ViewerDragAndDropHelper}
	 */
	public Class<? extends ViewerDragAndDropHelper> bindViewerDragAndDropHelper() {
		return ViewerDragAndDropHelper.class;
	}

	/**
	 * Use this bind to provide a factory that builds controls for your forms
	 * @return a specialization of {@link FormControlFactory}
	 * @see TreeFormFactory
	 */
	public Class<? extends FormControlFactory> bindFormControlFactory() {
		return FormControlFactory.class;
	}

	/**
	 * Use this bind to provide a factory that builds controls for your dialogs
	 * @return a specialization of {@link DialogControlFactory}
	 * @see TreeFormFactory
	 */
	public Class<? extends DialogControlFactory> bindDialogControlFactory() {
		return DialogControlFactory.class;
	}

	/**
	 * Use this bind to customize the label provider factory, specifically for columns.
	 * The default implementation will use the same implementation specified by bind method {@link #bindILabelProvider()}  
	 * @return a specialization of {@link ColumnLabelProviderFactory}
	 * @see ViewerLabelProvider
	 */
	public Class<? extends ColumnLabelProviderFactory> bindColumnLabelProviderFactory() {
		return ColumnLabelProviderFactory.class;
	}

	/**
	 * Use this binding to provide the label provider factory only for columns.
	 * The default implementation will use the same implementation specified by bind method {@link #bindILabelProvider()}  
	 * @return a specialization of {@link TableColumnLabelProvider}
	 * @see FeatureCaptionProvider
	 */		
	public Class<? extends TableColumnLabelProvider> bindTableColumnLabelProvider() {
		return TableColumnLabelProvider.class;
	}

	/**
	 * Use this bind to change the way columns for table are built. 
	 * @return a specialization of {@link TableViewerColumnBuilder}
	 * @see TableViewerFactory
	 */
	public Class<? extends TableViewerColumnBuilder> bindTableViewerColumnBuilder() {
		return TableViewerColumnBuilder.class;
	}

	/**
	 * Use this binding to provide your implementation of a <b>action bar contributor</b> for a workbench context.
	 * Default implementation is {@link WorkbenchActionBarContributor}
	 * @return an implementation of  {@link WorkbenchActionBarContributor}
	 */
	public Class<? extends WorkbenchActionBarContributor> bindWorkbenchActionBarContributor() {
		return WorkbenchActionBarContributor.class;
	}
	
	/**
	 * Use this bind to provide your implementation of a of <b>action bar contributor</b>  without a workbench.
	 * This scenario can be used where you have a view instead of an editor, for example in a <b>e4 application</b>.  
	 * @return an implementation of  {@link TreeActionBarContributor}
	 * @see WorkbenchActionBarContributor
	 */
	public Class<? extends TreeActionBarContributor> bindTreeActionBarContributor() {
		return TreeActionBarContributor.class;
	}
	
	/**
	 * Use this bind method to change the way Edit actions (Copy, cut and paste)
	 * and EMF actions are added to context menu.
	 * 
	 * @return a specialization of {@link EditingActionManager}
	 */
	public Class<? extends EditingActionManager> bindEditingActionManager() {
		return EditingActionManager.class;
	}

	/**
	 * Use this bind method to change the way the context and other menus are created for
	 * Edit actions.
	 * 
	 * @return a specialization of {@link EditingMenuBuilder}
	 */
	public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
		return EditingMenuBuilder.class;
	}

	/**
	 * Use this method to specify how the editing domain can be found
	 * @return a specialization of {@link EditingDomainFinder}
	 * 
	 */
	public Class<? extends EditingDomainFinder> bindEditingDomainFinder() {
		return EditingDomainFinder.class;
	}

	/**
	 * Use this binding to provide a custom list of EStructuralFetures for your model
	 * @return a specialization of {@link FeaturesProvider}
	 */
	public Class<? extends FeaturesProvider> bindFeaturesProvider() {
		return FeaturesProvider.class;
	}

	/**
	 * Use this binding to customize the way to resolve EStructuralFeatures
	 * @return a specialization of {@link FeatureResolver}
	 */
	public Class<? extends FeatureResolver> bindFeatureResolver() {
		return FeatureResolver.class;
	}

	/**
	 * Use this binding to provide a custom list of EStructuralFetures only for tables.
	 * The default behavior is to use the same class provided by {@link #bindFeaturesProvider()} method.
	 * @return a specialization of {@link TableFeaturesProvider}
	 * @see FeaturesProvider
	 */
	public Class<? extends TableFeaturesProvider> bindTableFeaturesProvider() {
		return TableFeaturesProvider.class;
	}

	/**
	 * Use this binding to customize the helper EmfSelectionHelper
	 * @return a specialization of {@link EmfSelectionHelper}
	 */
	public Class<? extends EmfSelectionHelper> bindEmfSelectionHelper() {
		return EmfSelectionHelper.class;
	}
	
	/**
	 * Use this method to customize save mechanism
	 * @return a specification of {@link ResourceSaveStrategy}
	 */
	public Class<? extends ResourceSaveStrategy> bindResourceSaveStrategy() {
		return ResourceSaveStrategy.class;
	}

	/**
	 * Use this method to customize validity checks.
	 * @return a specification of {@link Diagnostician}
	 */
	public Diagnostician bindDiagnostician() {
		return Diagnostician.INSTANCE;
	}
	
	/**
	 * Use this method to customize tasks concerning a resource,
	 * for example, its initialization.
	 * 
	 * @return a specification of {@link ResourceManager}
	 */
	public Class<? extends ResourceManager> bindResourceManager() {
		return ResourceManager.class;
	}

	/**
	 * Use this binding to implement a custom label provider.
	 * Default implementation is {@link ViewerContentProvider}
	 * @return an implementation of {@link IContentProvider}
	 */
	public Class<? extends IContentProvider> bindIContentProvider() {
		return ViewerContentProvider.class;
	}

	/**
	 * Use this method to customize the proposal provider.
	 * @return a specification of {@link ProposalCreator}
	 */
	public Class<? extends ProposalCreator> bindProposalCreator() {
		return ProposalCreator.class;
	}

	/**
	 * Use this method to customize an editing strategy, to prepare the eObject for edit and update.
	 * The default implementation is {@link OnTheFlyEditingStrategy}.
	 * @return a specification of {@link IEditingStrategy}
	 * @see UndoableEditingStrategy
	 */
	public Class<? extends IEditingStrategy> bindIEditingStrategy() {
		return OnTheFlyEditingStrategy.class;
	}

	/**
	 * Use this method to customize a {@link Configurator}.
	 * @return a specification of {@link Configurator}
	 * @see UndoableEditingStrategy
	 */
	public Class<? extends Configurator> bindConfigurator() {
		return Configurator.class;
	}

	/**
	 * Use this method to customize a {@link IssueReporter},
	 * the default implementation is {@link DialogErrorReporter}.
	 * @return an implementation of {@link IssueReporter}
	 * @see LogIssueReporter	
	 */
	public Class<? extends IssueReporter> bindIssueReporter() {
		return DialogErrorReporter.class;
	}

	/**
	 * Use this method to customize a {@link ValidationRunner}.
	 * @return an implementation of {@link ValidationRunner}
	 */
	public Class<? extends ValidationRunner> bindValidationRunner() {
		return ValidationRunner.class;
	}

	/**
	 * Use this method to customize a {@link DiagnosticUtil}.
	 * @return an implementation of {@link DiagnosticUtil}
	 */
	public Class<? extends DiagnosticUtil> bindDiagnosticUtil() {
		return DiagnosticUtil.class;
	}

	/**
	 * Use this method to customize a {@link FeatureHelper}.
	 * @return an implementation of {@link FeatureHelper}
	 */
	public Class<? extends FeatureHelper> bindFeatureHelper() {
		return FeatureHelper.class;
	}

	/**
	 * Use this method to specify a provider for {@link AdapterFactoryEditingDomain}.
	 * The default implementation in {@link DefaultAdapterFactoryEditingDomainProvider}
	 * @return an implementation of {@link Provider} for {@link AdapterFactoryEditingDomain}
	 */
	public Class<? extends Provider<AdapterFactoryEditingDomain>> provideAdapterFactoryEditingDomain() {
    	return DefaultAdapterFactoryEditingDomainProvider.class;
    }

	/**
	 * Use this method to specify an adapter factory
	 * The default implementation is {@link InjectableAdapterFactory}
	 * @return an implementation of {@link AdapterFactory}
	 */
	public Class<? extends AdapterFactory> bindAdapterFactory() {
		return InjectableAdapterFactory.class;
	}

	public Class<? extends AdapterFactoryLabelProvider> bindAdapterFactoryLabelProvider() {
		return InjectableAdapterFactoryLabelProvider.class;
	}

	public ComposedAdapterFactory.Descriptor.Registry bindComposedAdapterFactory$Descriptor$RegistryToInstance() {
		return ComposedAdapterFactory.Descriptor.Registry.INSTANCE;
	}
}
