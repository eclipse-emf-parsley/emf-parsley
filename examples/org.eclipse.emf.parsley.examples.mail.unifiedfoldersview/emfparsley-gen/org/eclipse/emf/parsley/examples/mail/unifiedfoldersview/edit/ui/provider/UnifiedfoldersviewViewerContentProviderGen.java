package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui.provider;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import java.util.Iterator;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.mail.Folder;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.UnifiedFolderContainer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class UnifiedfoldersviewViewerContentProviderGen extends ViewerContentProvider {
  @Inject
  public UnifiedfoldersviewViewerContentProviderGen(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
  
  public Object elements(final Resource resource) {
    UnifiedFolderContainer _unifiedFolderContainer = new UnifiedFolderContainer("Inbox", resource);
    UnifiedFolderContainer _unifiedFolderContainer_1 = new UnifiedFolderContainer("Sent", resource);
    UnifiedFolderContainer _unifiedFolderContainer_2 = new UnifiedFolderContainer("Trash", resource);
    return CollectionLiterals.<UnifiedFolderContainer>newArrayList(_unifiedFolderContainer, _unifiedFolderContainer_1, _unifiedFolderContainer_2);
  }
  
  public Object children(final UnifiedFolderContainer c) {
    Resource _resource = c.getResource();
    TreeIterator<EObject> _allContents = _resource.getAllContents();
    Iterator<Folder> _filter = Iterators.<Folder>filter(_allContents, Folder.class);
    final Function1<Folder, Boolean> _function = new Function1<Folder, Boolean>() {
      @Override
      public Boolean apply(final Folder it) {
        String _name = it.getName();
        String _containerName = c.getContainerName();
        return Boolean.valueOf(Objects.equal(_name, _containerName));
      }
    };
    return IteratorExtensions.<Folder>filter(_filter, _function);
  }
  
  public Object children(final Folder it) {
    return CollectionLiterals.<Object>emptyList();
  }
}
