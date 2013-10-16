/**
 * 
 */
package org.eclipse.emf.parsley;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.binding.DialogControlFactory;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.binding.ProposalCreator;
import org.eclipse.emf.parsley.builders.TableViewerBuilder;
import org.eclipse.emf.parsley.builders.TableViewerColumnBuilder;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.OnTheFlyEditingStrategy;
import org.eclipse.emf.parsley.edit.ResourceSaveManager;
import org.eclipse.emf.parsley.edit.action.EditingActionManager;
import org.eclipse.emf.parsley.edit.action.EmfActionManager;
import org.eclipse.emf.parsley.edit.actionbar.TreeActionBarContributor;
import org.eclipse.emf.parsley.edit.actionbar.WorkbenchActionBarContributor;
import org.eclipse.emf.parsley.edit.domain.InjectableAdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.edit.provider.InjectableAdapterFactory;
import org.eclipse.emf.parsley.edit.ui.provider.InjectableAdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.factories.ColumnLabelProviderFactory;
import org.eclipse.emf.parsley.factories.FormFactory;
import org.eclipse.emf.parsley.factories.TreeFormFactory;
import org.eclipse.emf.parsley.factories.ViewerFactory;
import org.eclipse.emf.parsley.handlers.OutlineSelectionHandler;
import org.eclipse.emf.parsley.listeners.IEditorMouseListener;
import org.eclipse.emf.parsley.listeners.IViewerMouseListener;
import org.eclipse.emf.parsley.listeners.OpenPropertyViewMouseAdapter;
import org.eclipse.emf.parsley.listeners.OpenDialogMouseAdapter;
import org.eclipse.emf.parsley.menus.ViewerContextMenuFactory;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.runtime.service.AbstractGenericModule;
import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.emf.parsley.runtime.ui.PluginImageHelper;
import org.eclipse.emf.parsley.ui.provider.DialogPropertyDescriptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesColumnProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormPropertyDescriptionProvider;
import org.eclipse.emf.parsley.ui.provider.PropertyDescriptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.emf.parsley.viewers.ViewerInitializer;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Binder;

/**
 * Default Google Guice bindings.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyGuiceModule extends AbstractGenericModule {

	private final AbstractUIPlugin plugin;

	public EmfParsleyGuiceModule(AbstractUIPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(AbstractUIPlugin.class).toInstance(plugin);
		binder.bind(IDialogSettings.class).toInstance(
				plugin.getDialogSettings());
	}

	public Class<? extends IImageHelper> bindIImageHelper() {
		return PluginImageHelper.class;
	}

	public Class<? extends ResourceLoader> bindResourceLoader() {
		return ResourceLoader.class;
	}

	public Class<? extends IEditorMouseListener> bindIEditorMouseListener() {
		return OpenPropertyViewMouseAdapter.class;
	}

	public Class<? extends IViewerMouseListener> bindIViewerMouseListener() {
		return OpenDialogMouseAdapter.class;
	}

	public Class<? extends ILabelProvider> bindILabelProvider() {
		return ViewerLabelProvider.class;
	}

	public Class<? extends PropertyDescriptionProvider> bindPropertyDescriptionProvider() {
		return PropertyDescriptionProvider.class;
	}

	public Class<? extends FormPropertyDescriptionProvider> bindFormPropertyDescriptionProvider() {
		return FormPropertyDescriptionProvider.class;
	}

	public Class<? extends DialogPropertyDescriptionProvider> bindDialogPropertyDescriptionProvider() {
		return DialogPropertyDescriptionProvider.class;
	}

	public Class<? extends OutlineSelectionHandler> bindOutlineSelectionHandler() {
		return OutlineSelectionHandler.class;
	}

	public Class<? extends ViewerFactory> bindViewerFactory() {
		return ViewerFactory.class;
	}

	public Class<? extends TreeFormFactory> bindTreeFormFactory() {
		return TreeFormFactory.class;
	}

	public Class<? extends FormFactory> bindFormFactory() {
		return FormFactory.class;
	}

	public Class<? extends ViewerInitializer> bindViewerInitializer() {
		return ViewerInitializer.class;
	}

	public Class<? extends FormControlFactory> bindFormControlFactory() {
		return FormControlFactory.class;
	}

	public Class<? extends DialogControlFactory> bindDialogControlFactory() {
		return DialogControlFactory.class;
	}

	public Class<? extends ViewerContextMenuFactory> bindViewerContextMenuFactory() {
		return ViewerContextMenuFactory.class;
	}

	public Class<? extends ColumnLabelProviderFactory> bindColumnLabelProviderFactory() {
		return ColumnLabelProviderFactory.class;
	}

	public Class<? extends TableColumnLabelProvider> bindTableColumnLabelProvider() {
		return TableColumnLabelProvider.class;
	}

	public Class<? extends TableViewerBuilder> bindTableViewerBuilder() {
		return TableViewerBuilder.class;
	}

	public Class<? extends TableViewerColumnBuilder> bindTableViewerColumnBuilder() {
		return TableViewerColumnBuilder.class;
	}

	public Class<? extends WorkbenchActionBarContributor> bindEmfActionBarContributor() {
		return WorkbenchActionBarContributor.class;
	}
	
	public Class<? extends TreeActionBarContributor> bindTreeActionBarContributor() {
		return TreeActionBarContributor.class;
	}
	
	public Class<? extends EmfActionManager> bindEmfActionManager() {
		return EmfActionManager.class;
	}

	public Class<? extends EditingActionManager> bindEditingActionManager() {
		return EditingActionManager.class;
	}
	
	public Class<? extends EditingDomainFinder> bindEditingDomainFinder() {
		return EditingDomainFinder.class;
	}

	public Class<? extends FeaturesProvider> bindFeaturesProvider() {
		return FeaturesProvider.class;
	}

	public Class<? extends FeaturesColumnProvider> bindFeaturesColumnProvider() {
		return FeaturesColumnProvider.class;
	}

	public Class<? extends EmfSelectionHelper> bindEmfSelectionHelper() {
		return EmfSelectionHelper.class;
	}

	public Class<? extends ResourceSaveManager> bindResourceSaveManager() {
		return ResourceSaveManager.class;
	}

	public Diagnostician bindDiagnostician() {
		return Diagnostician.INSTANCE;
	}
	
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return EmptyResourceInitializer.class;
	}

	public Class<? extends IContentProvider> bindIContentProvider() {
		return ViewerContentProvider.class;
	}

	public Class<? extends ProposalCreator> bindProposalCreator() {
		return ProposalCreator.class;
	}

	public Class<? extends IEditingStrategy> bindIEditingStrategy() {
		return OnTheFlyEditingStrategy.class;
	}

	public Class<? extends AdapterFactoryEditingDomain> bindAdapterFactoryEditingDomain() {
		return InjectableAdapterFactoryEditingDomain.class;
	}
	
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
