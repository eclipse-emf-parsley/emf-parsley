/**
 * 
 */
package org.eclipse.emf.parsley.edit.ui.provider;

import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;

import com.google.inject.Inject;

/**
 * Declarative ContentProvider based on {@link AdapterFactoryContentProvider}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class ViewerContentProvider extends AdapterFactoryContentProvider {

	@Inject
	public ViewerContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	private PolymorphicDispatcher<Object> childrenDispatcher = PolymorphicDispatcher
			.createForSingleTarget("children", 1, 1, this);

	private PolymorphicDispatcher<Object> elementsDispatcher = PolymorphicDispatcher
			.createForSingleTarget("elements", 1, 1, this);

//	/**
//	 * This implements {@link IStructuredItemContentProvider#getElements
//	 * IStructuredItemContentProvider.getElements} by forwarding the call to
//	 * {@link #getChildren getChildren}. It seems that you almost always want
//	 * getElements and getChildren to return the same thing, so this makes that
//	 * easy.
//	 */
//	public Object[] getElements(Object inputElement) {
//		return getChildren(inputElement);
//	}

	/**
	 * This implements {@link ITreeItemContentProvider#hasChildren
	 * ITreeItemContentProvider.hasChildren}. The default, non-optimized
	 * approach simply tests whether whether {@link #getChildren getChildren}
	 * returns any children.
	 */
	public boolean hasChildren(Object element) {
		// Lorenzo: ugly hack
		// if the super method is not invoked the tree is not
		// refreshed correctly
		super.hasChildren(element); // ignore it.
		return getChildren(element).length > 0;
	}

	/**
	 * The default implementation
	 * 
	 * @param o
	 * @return null
	 */
	public List<Object> children(Object o) {
		return null;
	}

	/**
	 * The default implementation
	 * 
	 * @param o
	 * @return null
	 */
	public List<Object> elements(Object o) {
		return null;
	}

	@Override
	public Object[] getChildren(Object element) {
		Object children = childrenDispatcher.invoke(element);
		if (children != null) {
			return EmfParsleyUtil.ensureCollection(children).toArray();
		}
		return super.getChildren(element);
	}

	@Override
	public Object[] getElements(Object element) {
		Object elements = elementsDispatcher.invoke(element);
		if (elements != null) {
			return EmfParsleyUtil.ensureCollection(elements).toArray();
		}
		return super.getElements(element);
	}
}
