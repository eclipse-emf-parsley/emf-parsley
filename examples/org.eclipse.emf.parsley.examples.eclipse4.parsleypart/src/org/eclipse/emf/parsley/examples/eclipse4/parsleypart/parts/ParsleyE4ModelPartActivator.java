package org.eclipse.emf.parsley.examples.eclipse4.parsleypart.parts;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.ui.EmfParsleyAbstractActivator;
import org.osgi.framework.BundleContext;

public class ParsleyE4ModelPartActivator extends EmfParsleyAbstractActivator {

	private static ParsleyE4ModelPartActivator plugin;

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}


	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}


	public static ParsleyE4ModelPartActivator getDefault() {
		return plugin;
	}
	
	public EmfParsleyGuiceModule createModule() {
		return new ParlseyE4ModelGuiceModule(getDefault());
	}

}
