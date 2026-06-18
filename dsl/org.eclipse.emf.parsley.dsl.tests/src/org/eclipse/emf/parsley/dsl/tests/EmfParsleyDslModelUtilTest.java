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
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertSame;

import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslModelUtilTest {

	@Inject
	private ParseHelper<Model> parseHelper;

	@Test
	public void testContainingModule() throws Exception {
		var m = parseHelper.parse("""
			module Test {}
		""").getModule();
		assertSame(m, EmfParsleyDslModelUtil.containingModule(m));
	}

	@Test
	public void testContainingEmfFeatureAccess() throws Exception {
		var m = parseHelper.parse("""
			module Test {
				formControlFactory {
					control {
						Foo : foo -> {}
					}
				}
			}
		""").getModule();
		var spec = m.getFormControlFactory().getControls().getSpecifications().get(0);
		assertSame(spec, EmfParsleyDslModelUtil.containingEmfFeatureAccess(spec));
	}
}
