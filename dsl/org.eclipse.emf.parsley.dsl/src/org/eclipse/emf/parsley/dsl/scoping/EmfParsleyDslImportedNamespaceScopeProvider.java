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
package org.eclipse.emf.parsley.dsl.scoping;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;

/**
 * @author Lorenzo Bettini
 *
 */
public class EmfParsleyDslImportedNamespaceScopeProvider extends
	XImportSectionNamespaceScopeProvider {

	@Override
	protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase) {
		List<ImportNormalizer> implicitImports = new ArrayList<>(super.getImplicitImports(ignoreCase));
		implicitImports.add(new ImportNormalizer(QualifiedName.create("org","eclipse","swt"), true, ignoreCase));
		return implicitImports;
	}
}
