/**
 * 
 */
package org.eclipse.emf.parsley.runtime.ui;

import org.eclipse.emf.parsley.runtime.service.AbstractGenericModule;

import com.google.inject.Injector;
import static com.google.inject.Guice.createInjector;

/**
 * @author bettini
 *
 */
public abstract class MinimalExecutableExtensionFactory extends
		AbstractGuiceAwareExecutableExtensionFactory {

	protected Injector getInjector() {
		return createInjector(getModule());
	}

	protected abstract AbstractGenericModule getModule();

}
