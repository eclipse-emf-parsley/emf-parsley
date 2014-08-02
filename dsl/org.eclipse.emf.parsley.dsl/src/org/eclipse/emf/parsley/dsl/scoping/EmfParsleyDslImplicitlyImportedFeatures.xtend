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
package org.eclipse.emf.parsley.dsl.scoping

import org.eclipse.jface.databinding.swt.SWTObservables
import org.eclipse.swt.widgets.Control
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedFeatures

class EmfParsleyDslImplicitlyImportedFeatures extends ImplicitlyImportedFeatures {
	override protected getExtensionClasses() {
		(super.getExtensionClasses() + #[typeof(Control), typeof(SWTObservables)])
			.toList
	}
}