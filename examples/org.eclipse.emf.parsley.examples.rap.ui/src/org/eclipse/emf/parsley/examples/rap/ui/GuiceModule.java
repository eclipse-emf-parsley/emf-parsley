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
package org.eclipse.emf.parsley.examples.rap.ui;


import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.examples.rap.model.Element;
import org.eclipse.emf.parsley.examples.rap.model.Item;
import org.eclipse.emf.parsley.examples.rap.model.Model;
import org.eclipse.emf.parsley.examples.rap.model.ModelFactory;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Inject;

public class GuiceModule extends EmfParsleyGuiceModule {

	public static class CustomResourceManager extends
			ResourceManager {
		public void initialize(org.eclipse.emf.ecore.resource.Resource resource) {
			Model model = ModelFactory.eINSTANCE.createModel();
			model.setName("My Model");

			Element firstElement = addElement(model, "First Element", 1);
			Element secondElement = addElement(model, "Second Element", 2);
			addElement(model, "Third Element", 3);
			
			Item firstItem = addItem(model, "First Item");
			Item secondItem = addItem(model, "Second Item");
			addItem(model, "Third Item");
			addItem(model, "Fourth Item");
			
			firstElement.getItems().add(firstItem);
			firstElement.getItems().add(secondItem);

			secondElement.getItems().add(firstItem);
			
			resource.getContents().add(model);
		}

		private Element addElement(Model model, String name, int age) {
			Element element = ModelFactory.eINSTANCE.createElement();
			element.setName(name);
			element.setAge(age);
			model.getElements().add(element);
			return element;
		}
		
		private Item addItem(Model model, String name) {
			Item item = ModelFactory.eINSTANCE.createItem();
			item.setName(name);
			model.getItems().add(item);
			return item;
		}
	}

	public static class CustomLabelProvider extends ViewerLabelProvider {

		@Inject
		public CustomLabelProvider(AdapterFactoryLabelProvider delegate) {
			super(delegate);
		}

		public String text(Model o) {
			return "Model: " + (o.getName() != null ? o.getName() : "NO NAME");
		}

		public Object image(Model o) {
			return "component.jpg";
		}

		public String text(Element o) {
			return "Element: "
					+ (o.getName() != null ? o.getName() : "NO NAME")
					+ " (" + o.getAge() + ")";
		}
		
		public Object image(Element o) {
			return "detail.jpg";
		}
		
		public Object image(Item o) {
			return "table.jpg";
		}
	}

	public GuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends ILabelProvider> bindILabelProvider() {
		return CustomLabelProvider.class;
	}

	@Override
	public Class<? extends ResourceManager> bindResourceManager() {
		return CustomResourceManager.class;
	}
}
