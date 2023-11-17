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

import java.util.Map;

import org.eclipse.pde.internal.core.text.DocumentElementNode;
import org.eclipse.pde.internal.core.text.IDocumentAttributeNode;
import org.eclipse.pde.internal.core.text.plugin.PluginAttribute;
import org.eclipse.pde.internal.core.text.plugin.PluginElementNode;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Utility methods that abstract from the specific internal implementation of
 * PDE across several Eclipse versions from Indigo on.
 *
 * {@link DocumentElementNode#getNodeAttributesMap()} returns a parameterized
 * TreeMap, while in Indigo it is not parameterized.
 *
 * @author Lorenzo Bettini - initial API and implementation
 */
public class PluginXmlUtils {

	protected PluginXmlUtils() {
		// empty
	}

	public static Iterable<Map.Entry<String, IDocumentAttributeNode>> getPluginAttributesEntrySet(
			final PluginElementNode node) {
		return IterableExtensions.<Map.Entry<String, IDocumentAttributeNode>> filter(
				node.getNodeAttributesMap().entrySet(),
				it -> it.getValue() instanceof PluginAttribute);
	}

	public static IDocumentAttributeNode getId(final DocumentElementNode node) {
		return (node.getNodeAttributesMap().get("id"));
	}

	@SuppressWarnings("rawtypes")
	public static Map getNodeAttributesMap(final DocumentElementNode node) {
		return node.getNodeAttributesMap();
	}
}
