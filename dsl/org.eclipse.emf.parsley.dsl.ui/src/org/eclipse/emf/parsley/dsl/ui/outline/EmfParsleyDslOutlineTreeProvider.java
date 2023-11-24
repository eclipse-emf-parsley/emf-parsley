/**
 * Copyright (c) 2023 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.ui.outline;

import static org.eclipse.xtext.xbase.lib.IterableExtensions.sortBy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dsl.model.BindingsSpecification;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.model.PartsSpecifications;
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;

/**
 * Customization of the default outline structure.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#outline
 */
public class EmfParsleyDslOutlineTreeProvider extends DefaultOutlineTreeProvider {
	protected void _createChildren(DocumentRootNode parentNode, Model m) { // NOSONAR
		// skip useless toplevel node and imports - start tree main module element
		createChildren(parentNode, m.getModule());
	}

	protected void _createChildren(IOutlineNode parentNode, org.eclipse.emf.parsley.dsl.model.Module m) { // NOSONAR
		// this way the nodes will appear in the same order as
		// specified in the program text
		var contentsOrderedByOffset = sortBy(m.eContents(),
			it -> Integer.valueOf(NodeModelUtils.getNode(it).getOffset()));
		for (EObject o : contentsOrderedByOffset) {
			createNode(parentNode, o);
		}
	}

	public boolean _isLeaf(org.eclipse.emf.parsley.dsl.model.Module m) { // NOSONAR
		return false;
	}

	public boolean _isLeaf(BindingsSpecification e) { // NOSONAR
		return true;
	}

	public boolean _isLeaf(PartsSpecifications e) { // NOSONAR
		return true;
	}

	public boolean _isLeaf(WithExtendsClause e) { // NOSONAR
		return true;
	}
}
