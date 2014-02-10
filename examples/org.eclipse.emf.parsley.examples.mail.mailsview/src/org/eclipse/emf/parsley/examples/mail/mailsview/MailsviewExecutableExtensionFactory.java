/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.mail.mailsview;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.osgi.framework.Bundle;


import com.google.inject.Injector;

public class MailsviewExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return MailsviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return MailsviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return MailsviewActivator.getDefault().getInjector();
	}
}
