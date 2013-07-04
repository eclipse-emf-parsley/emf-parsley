package org.eclipse.emf.parsley.dsl.scoping;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslScopeProviderHelper;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.scoping.batch.XbaseBatchScopeProvider;

@SuppressWarnings("all")
public class EmfParsleyDslXbaseBatchScopeProvider extends XbaseBatchScopeProvider {
  @Inject
  @Extension
  private EmfParsleyDslScopeProviderHelper _emfParsleyDslScopeProviderHelper;
  
  public IScope getScope(final EObject context, final EReference reference) {
    IScope _xblockexpression = null;
    {
      final IScope scope = this._emfParsleyDslScopeProviderHelper.createCustomScope(context, reference);
      boolean _notEquals = (!Objects.equal(scope, null));
      if (_notEquals) {
        return scope;
      }
      IScope _scope = super.getScope(context, reference);
      _xblockexpression = (_scope);
    }
    return _xblockexpression;
  }
}
