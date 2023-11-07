package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui.provider;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.edit.ui.provider.ViewerContentProvider;
import org.eclipse.emf.parsley.examples.mail.Folder;
import org.eclipse.emf.parsley.examples.mail.accountsview.unifiedfolders.UnifiedFolderContainer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class UnifiedfoldersviewViewerContentProvider extends ViewerContentProvider {
  @Inject
  public UnifiedfoldersviewViewerContentProvider(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  public Object elements(final Resource resource) {
    UnifiedFolderContainer _unifiedFolderContainer = new UnifiedFolderContainer("Inbox", resource);
    UnifiedFolderContainer _unifiedFolderContainer_1 = new UnifiedFolderContainer("Sent", resource);
    UnifiedFolderContainer _unifiedFolderContainer_2 = new UnifiedFolderContainer("Trash", resource);
    return CollectionLiterals.<UnifiedFolderContainer>newArrayList(_unifiedFolderContainer, _unifiedFolderContainer_1, _unifiedFolderContainer_2);
  }

  public Object children(final UnifiedFolderContainer c) {
    final Function1<Folder, Boolean> _function = (Folder it) -> {
      String _name = it.getName();
      String _containerName = c.getContainerName();
      return Boolean.valueOf(Objects.equal(_name, _containerName));
    };
    return IteratorExtensions.<Folder>filter(Iterators.<Folder>filter(c.getResource().getAllContents(), Folder.class), _function);
  }

  public Object children(final Folder it) {
    return CollectionLiterals.<Object>emptyList();
  }
}
