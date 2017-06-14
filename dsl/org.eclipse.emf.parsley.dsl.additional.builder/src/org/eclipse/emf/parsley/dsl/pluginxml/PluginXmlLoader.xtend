/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.pluginxml

import java.util.List
import org.eclipse.core.runtime.CoreException
import org.eclipse.jface.text.Document
import org.eclipse.pde.internal.core.text.DocumentElementNode
import org.eclipse.pde.internal.core.text.IDocumentAttributeNode
import org.eclipse.pde.internal.core.text.plugin.PluginAttribute
import org.eclipse.pde.internal.core.text.plugin.PluginElementNode
import org.eclipse.pde.internal.core.text.plugin.PluginExtensionNode
import org.eclipse.pde.internal.core.text.plugin.PluginModel

class PluginXmlLoader extends PluginModel {

	var List<PluginExtensionNode> pluginExtensionNodes;

	var List<PluginElementNode> pluginExtensionElementNodes;

	new(String source) {
		super(new Document(source), true)
	}

	/**
	 * The nodes corresponding to &lt;extension&gt; elements in the plugin.xml file
	 */
	def getExtensionNodes() {
		if (pluginExtensionNodes === null)
			initializeExtensionNodes()
		return pluginExtensionNodes
	}

	protected def initializeExtensionNodes() {
		pluginExtensionNodes = plugin.extensions.filter(PluginExtensionNode).toList
	}

	/**
	 * The nodes corresponding to elements inside &lt;extension&gt;, for example,
	 * &lt;view&gt;, &lt;editor&gt;
	 */
	def getExtensionElements() {
		if (pluginExtensionElementNodes === null)
			initializeExtensionElements()
		return pluginExtensionElementNodes
	}

	protected def initializeExtensionElements() {
		pluginExtensionElementNodes = getExtensionNodes().map[mapToNodes].flatten.toList
	}

	private def mapToNodes(DocumentElementNode it) {
		childNodes.filter(PluginElementNode)
	}

	def getExtensionChildren(PluginElementNode node) {
		node.mapToNodes.toList
	}

	def getPluginAttributesEntrySet(PluginElementNode node) {
		PluginXmlUtils.getPluginAttributesEntrySet(node)
	}

	def getExtensionByPoint(String p) {
		getExtensionNodes().findFirst[point == p]
	}

	def getElementExtension(PluginElementNode node) {
		(node.parent as PluginExtensionNode).point
	}

	def getElementByTagAndId(String xmlTag, String id) {
		extensionElements.
			filter[XMLTagName == xmlTag].
			findFirst[
				val v = getId
				if (v !== null) {
					getAttributeAsString(v) == id
				} else {
					false
				}
			]
	}

	def getId(DocumentElementNode node) {
		PluginXmlUtils.getId(node)
	}

	def getAttributeAsString(IDocumentAttributeNode a) {
		(a as PluginAttribute).value
	}

	/**
	 * Copies all the extension and extension elements from the source
	 * plugin xml into this plugin xml.  Attributes with the same id
	 * will be overwritten in this plugin xml.
	 */
	def copyFromPluginXml(String source) throws CoreException {
		for (e : new PluginXmlLoader(source).extensionElements) {
			copy(e)
		}
	}

	/**
	 * Assumes that the source has an id.  If a corresponding element
	 * in this plugin xml file is not found it will be inserted first.
	 */
	def copy(PluginElementNode source) throws CoreException {
		val xmlTagName = source.XMLTagName
		var target = getElementByTagAndId(xmlTagName, source.getId.attributeAsString)
		if (target === null) {
			target = insertExtensionElement(source.getElementExtension, xmlTagName)
		}
		copy(source, target)
	}

	/**
	 * This assumes that both the source and the target are not null
	 */
	def void copy(PluginElementNode source, PluginElementNode target) throws CoreException {
		val atts = source.getAttributes();
		for (a : atts) {
			val att = a as PluginAttribute;
			val copy = new PluginAttribute
			copy.name = att.name
			copy.value = att.value
			PluginXmlUtils.getNodeAttributesMap(target).put(att.getName(), copy);
		}
		val children = target.childNodes
		for (c : source.childNodes) {
			var myChild = children.findFirst[XMLTagName == c.XMLTagName]
			if (myChild === null) {
				val newChild = pluginFactory.createElement(target) as PluginElementNode
				newChild.XMLTagName = c.XMLTagName
				target.addChildNode(newChild)
				copy(c as PluginElementNode, newChild)
			} else {
				copy(c as PluginElementNode, myChild as PluginElementNode)
			}
		}
	}

	def insertExtension(String point) throws CoreException {
		val e = pluginFactory.createExtension()
		e.setPoint(point)
		plugin.add(e)
		initializeExtensionNodes
		return e as PluginExtensionNode
	}

	/**
	 * If there is no extension element with the specified point, it
	 * will be automatically inserted.
	 */
	def insertExtensionElement(String point, String xmlTag) throws CoreException {
		var ext = getExtensionByPoint(point)
		if (ext === null)
			ext = insertExtension(point)
		val element = pluginFactory.createElement(ext) as PluginElementNode
		ext.addChildNode(element)
		element.XMLTagName = xmlTag
		initializeExtensionElements
		return element
	}

	def String getContentsAsString() {
		// it would be nice to use the Document contents, but these are not synchronized
		// with the plugin model changes
		'''
		«plugin.toString.replaceFirst("eclipse version=\"3.0", "eclipse version=\"3.4").replaceFirst("(<plugin)\\r?\\n(>)", "<plugin>")»
		'''
	}

}
