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
package org.eclipse.emf.parsley.dsl.pluginxml;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.Document;
import org.eclipse.pde.core.plugin.IPluginAttribute;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.internal.core.text.DocumentElementNode;
import org.eclipse.pde.internal.core.text.IDocumentAttributeNode;
import org.eclipse.pde.internal.core.text.IDocumentElementNode;
import org.eclipse.pde.internal.core.text.plugin.PluginAttribute;
import org.eclipse.pde.internal.core.text.plugin.PluginElementNode;
import org.eclipse.pde.internal.core.text.plugin.PluginExtensionNode;
import org.eclipse.pde.internal.core.text.plugin.PluginModel;

public class PluginXmlLoader extends PluginModel {

	private List<PluginExtensionNode> pluginExtensionNodes;

	private List<PluginElementNode> pluginExtensionElementNodes;

	public PluginXmlLoader(String source) {
		super(new Document(source), true);
	}

	/**
	 * The nodes corresponding to &lt;extension&gt; elements in the plugin.xml file
	 */
	public List<PluginExtensionNode> getExtensionNodes() {
		if (pluginExtensionNodes == null)
			initializeExtensionNodes();
		return pluginExtensionNodes;
	}

	protected void initializeExtensionNodes() {
		pluginExtensionNodes = Arrays.stream(getPlugin().getExtensions())
				.filter(PluginExtensionNode.class::isInstance)
				.map(PluginExtensionNode.class::cast)
				.toList();
	}

	/**
	 * The nodes corresponding to elements inside &lt;extension&gt;, for example,
	 * &lt;view&gt;, &lt;editor&gt;
	 */
	public List<PluginElementNode> getExtensionElements() {
		if (pluginExtensionElementNodes == null)
			initializeExtensionElements();
		return pluginExtensionElementNodes;
	}

	protected void initializeExtensionElements() {
		pluginExtensionElementNodes = getExtensionNodes().stream()
				.flatMap(node -> mapToNodes(node).stream())
				.toList();
	}

	private List<PluginElementNode> mapToNodes(DocumentElementNode node) {
		return Arrays.stream(node.getChildNodes())
				.filter(PluginElementNode.class::isInstance)
				.map(PluginElementNode.class::cast)
				.toList();
	}

	public List<PluginElementNode> getExtensionChildren(PluginElementNode node) {
		return mapToNodes(node);
	}

	public Iterable<Map.Entry<String, IDocumentAttributeNode>> getPluginAttributesEntrySet(PluginElementNode node) {
		return PluginXmlUtils.getPluginAttributesEntrySet(node);
	}

	public PluginExtensionNode getExtensionByPoint(String p) {
		return getExtensionNodes().stream()
				.filter(e -> Objects.equals(e.getPoint(), p))
				.findFirst()
				.orElse(null);
	}

	public String getElementExtension(PluginElementNode node) {
		return ((PluginExtensionNode) node.getParent()).getPoint();
	}

	public PluginElementNode getElementByTagAndId(String xmlTag, String id) {
		return getExtensionElements().stream()
				.filter(e -> Objects.equals(e.getXMLTagName(), xmlTag))
				.filter(e -> {
					var v = getId(e);
					if (v != null)
						return Objects.equals(getAttributeAsString(v), id);
					return false;
				})
				.findFirst()
				.orElse(null);
	}

	public IDocumentAttributeNode getId(DocumentElementNode node) {
		return PluginXmlUtils.getId(node);
	}

	public String getAttributeAsString(IDocumentAttributeNode a) {
		return ((PluginAttribute) a).getValue();
	}

	/**
	 * Copies all the extension and extension elements from the source
	 * plugin xml into this plugin xml.  Attributes with the same id
	 * will be overwritten in this plugin xml.
	 */
	public void copyFromPluginXml(String source) throws CoreException {
		for (var e : new PluginXmlLoader(source).getExtensionElements()) {
			copy(e);
		}
	}

	/**
	 * Assumes that the source has an id.  If a corresponding element
	 * in this plugin xml file is not found it will be inserted first.
	 */
	public void copy(PluginElementNode source) throws CoreException {
		var xmlTagName = source.getXMLTagName();
		PluginElementNode target = getElementByTagAndId(xmlTagName, getAttributeAsString(getId(source)));
		if (target == null)
			target = insertExtensionElement(getElementExtension(source), xmlTagName);
		copy(source, target);
	}

	/**
	 * This assumes that both the source and the target are not null
	 */
	public void copy(PluginElementNode source, PluginElementNode target) throws CoreException {
		IPluginAttribute[] atts = source.getAttributes();
		for (var a : atts) {
			var att = (PluginAttribute) a;
			var copy = new PluginAttribute();
			copy.setName(att.getName());
			copy.setValue(att.getValue());
			PluginXmlUtils.getNodeAttributesMap(target).put(att.getName(), copy);
		}
		IDocumentElementNode[] children = target.getChildNodes();
		for (IDocumentElementNode c : source.getChildNodes()) {
			IDocumentElementNode myChild = Arrays.stream(children)
					.filter(child -> Objects.equals(child.getXMLTagName(), c.getXMLTagName()))
					.findFirst()
					.orElse(null);
			if (myChild == null) {
				var newChild = (PluginElementNode) getPluginFactory().createElement(target);
				newChild.setXMLTagName(c.getXMLTagName());
				target.addChildNode(newChild);
				copy((PluginElementNode) c, newChild);
			} else {
				copy((PluginElementNode) c, (PluginElementNode) myChild);
			}
		}
	}

	public PluginExtensionNode insertExtension(String point) throws CoreException {
		IPluginExtension e = getPluginFactory().createExtension();
		e.setPoint(point);
		getPlugin().add(e);
		initializeExtensionNodes();
		return (PluginExtensionNode) e;
	}

	/**
	 * If there is no extension element with the specified point, it
	 * will be automatically inserted.
	 */
	public PluginElementNode insertExtensionElement(String point, String xmlTag) throws CoreException {
		PluginExtensionNode ext = getExtensionByPoint(point);
		if (ext == null)
			ext = insertExtension(point);
		var element = (PluginElementNode) getPluginFactory().createElement(ext);
		ext.addChildNode(element);
		element.setXMLTagName(xmlTag);
		initializeExtensionElements();
		return element;
	}

	public String getContentsAsString() {
		// it would be nice to use the Document contents, but these are not synchronized
		// with the plugin model changes
		return getPlugin().toString()
				.replaceFirst("eclipse version=\"3.0", "eclipse version=\"3.4")
				.replaceFirst("(<plugin)\\r?\\n(>)", "<plugin>")
				+ "\n";
	}
}
