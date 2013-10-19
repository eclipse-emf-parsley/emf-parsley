package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui.provider;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import java.util.Iterator;
import java.util.List;
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
public class ViewerContentProviderGen extends ViewerContentProvider {
  @Inject
  public ViewerContentProviderGen(final AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
  
  public Object children(final UnifiedFolderContainer c) {
    Resource _resource = c.getResource();
    TreeIterator<EObject> _allContents = _resource.getAllContents();
    Iterator<Folder> _filter = Iterators.<Folder>filter(_allContents, Folder.class);
    final Function1<Folder,Boolean> _function = new Function1<Folder,Boolean>() {
      public Boolean apply(final Folder it) {
        String _name = it.getName();
        String _containerName = c.getContainerName();
        boolean _equals = Objects.equal(_name, _containerName);
        return Boolean.valueOf(_equals);
      }
    };
    Iterator<Folder> _filter_1 = IteratorExtensions.<Folder>filter(_filter, _function);
    return _filter_1;
  }
  
  public Object children(final Folder it) {
    List<Object> _emptyList = CollectionLiterals.<Object>emptyList();
    return _emptyList;
  }
}
