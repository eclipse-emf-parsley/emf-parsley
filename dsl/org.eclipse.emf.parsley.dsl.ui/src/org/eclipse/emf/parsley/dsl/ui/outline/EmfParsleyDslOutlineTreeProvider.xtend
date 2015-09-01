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
package org.eclipse.emf.parsley.dsl.ui.outline

import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.BindingsSpecification
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause
import org.eclipse.xtext.ui.editor.outline.IOutlineNode
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.emf.parsley.dsl.model.PartsSpecifications

/**
 * Customization of the default outline structure.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#outline
 */
class EmfParsleyDslOutlineTreeProvider extends org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider {

	def protected void _createChildren(DocumentRootNode parentNode, Model m) {
		// skip useless toplevel node and imports - start tree main module element
		createChildren(parentNode, m.module)
	}

	def protected void _createChildren(IOutlineNode parentNode, Module m) {
		// this way the nodes will appear in the same order as
		// specified in the program text
		val contentsOrderedByOffset = m.eContents.sortBy[NodeModelUtils.getNode(it).offset]
		for (o : contentsOrderedByOffset) {
			createNode(parentNode, o)
		}
	}

	def _isLeaf(Module m) {
		false
	}

	def _isLeaf(BindingsSpecification e) {
		true
	}

	def _isLeaf(PartsSpecifications e) {
		true
	}

	def _isLeaf(WithExtendsClause e) {
		true
	}
}
