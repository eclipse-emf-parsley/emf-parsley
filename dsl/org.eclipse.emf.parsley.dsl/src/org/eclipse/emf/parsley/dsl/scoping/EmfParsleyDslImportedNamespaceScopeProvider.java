/**
 * 
 */
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
		List<ImportNormalizer> implicitImports = new ArrayList<ImportNormalizer>(super.getImplicitImports(ignoreCase));
		implicitImports.add(new ImportNormalizer(QualifiedName.create("org","eclipse","swt"), true, ignoreCase));
		return implicitImports;
	}
}
