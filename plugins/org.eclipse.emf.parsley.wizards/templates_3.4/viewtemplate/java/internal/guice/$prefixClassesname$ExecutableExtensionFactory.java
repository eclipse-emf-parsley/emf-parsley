package $packageName$.internal.guice;

import $packageName$.Activator;
import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;

import org.osgi.framework.Bundle;

public class $prefixClassesname$ExecutableExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Activator.getDefault().getBundle();
	}

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return new $prefixClassesname$Module(Activator.getDefault());
	}

}
