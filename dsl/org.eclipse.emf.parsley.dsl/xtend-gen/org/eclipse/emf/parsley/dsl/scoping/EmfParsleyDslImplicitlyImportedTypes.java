package org.eclipse.emf.parsley.dsl.scoping;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Control;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedTypes;

@SuppressWarnings("all")
public class EmfParsleyDslImplicitlyImportedTypes extends ImplicitlyImportedTypes {
  protected List<Class<? extends Object>> getExtensionClasses() {
    List<Class<? extends Object>> _extensionClasses = super.getExtensionClasses();
    Iterable<Class<? extends Object>> _plus = Iterables.<Class<? extends Object>>concat(_extensionClasses, Collections.<Class<? extends Object>>unmodifiableList(Lists.<Class<? extends Object>>newArrayList(Control.class, SWTObservables.class)));
    List<Class<? extends Object>> _list = IterableExtensions.<Class<? extends Object>>toList(_plus);
    return _list;
  }
}
