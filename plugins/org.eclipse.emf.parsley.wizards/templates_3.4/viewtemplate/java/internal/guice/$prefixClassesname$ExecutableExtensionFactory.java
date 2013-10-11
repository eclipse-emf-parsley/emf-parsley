package $packageName$.internal.guice;

import $packageName$.Activator;
import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import org.osgi.framework.Bundle;

public class $prefixClassesname$ExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Activator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new $prefixClassesname$Module(Activator.getDefault());
	}

}
