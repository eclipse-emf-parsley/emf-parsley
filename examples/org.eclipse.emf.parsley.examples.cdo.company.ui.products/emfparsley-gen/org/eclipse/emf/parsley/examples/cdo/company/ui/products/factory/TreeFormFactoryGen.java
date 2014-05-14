package org.eclipse.emf.parsley.examples.cdo.company.ui.products.factory;

import org.eclipse.emf.parsley.factories.TreeFormFactory;
import org.eclipse.emf.parsley.widgets.TreeFormComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("all")
public class TreeFormFactoryGen extends TreeFormFactory {
  public TreeFormComposite createComposite(final Composite parent, final int style) {
    TreeFormComposite control = new TreeFormComposite (parent,	style, SWT.HORIZONTAL, new int[] {1,3});
    return control;
  }
}
