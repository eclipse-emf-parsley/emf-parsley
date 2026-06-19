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

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.util.ReplaceRegion;
import org.eclipse.xtext.xbase.imports.ImportOrganizer;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lorenzo Bettini - inspired by the Xtext Domainmodel example
 */
@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslOrganizeImportsTest {

	@Inject
	private ParseHelper<Model> parseHelper;

	@Inject
	private ImportOrganizer importOrganizer;

	protected void assertIsOrganizedTo(CharSequence input, CharSequence expected) throws Exception {
		var model = parseHelper.parse(input.toString());
		var changes = importOrganizer.getOrganizedImportChanges((XtextResource) model.eResource());
		var builder = new StringBuilder(input);
		List<ReplaceRegion> sortedChanges = new ArrayList<>(changes);
		Collections.sort(sortedChanges, (a, b) -> Integer.compare(a.getOffset(), b.getOffset()));
		ReplaceRegion lastChange = null;
		for (var it : sortedChanges) {
			if (lastChange != null && lastChange.getEndOffset() > it.getOffset())
				fail("Overlapping text edits: " + lastChange + " and " + it);
			lastChange = it;
		}
		List<ReplaceRegion> reversed = new ArrayList<>(sortedChanges);
		Collections.reverse(reversed);
		for (var it : reversed) {
			builder.replace(it.getOffset(), it.getOffset() + it.getLength(), it.getText());
		}
		assertEquals(
			expected.toString().replace("\r", ""),
			builder.toString().replace("\r", "")
		);
	}

	@Test
	public void testTypeInLabelProvider() throws Exception {
		assertIsOrganizedTo("""
module my.test.proj {
	labelProvider {
		text {
			org.eclipse.emf.ecore.EClass -> ""
		}
	}
}
		""", """
import org.eclipse.emf.ecore.EClass

module my.test.proj {
	labelProvider {
		text {
			EClass -> ""
		}
	}
}
		""");
	}

	@Test
	public void testWithWildCard() throws Exception {
		assertIsOrganizedTo("""
import org.eclipse.emf.ecore.*

module my.test.proj {
	labelProvider {
		text {
			EClass -> ""
		}
	}
}
		""", """
import org.eclipse.emf.ecore.EClass

module my.test.proj {
	labelProvider {
		text {
			EClass -> ""
		}
	}
}
		""");
	}

	@Test
	public void testForViewSpecification() throws Exception {
		assertIsOrganizedTo("""
import org.eclipse.ui.views.contentoutline.ContentOutline

module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass ContentOutline
		}
	}
}
		""", """
import org.eclipse.ui.views.contentoutline.ContentOutline

module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass ContentOutline
		}
	}
}
		""");
	}

	@Test
	public void testForManyTypes() throws Exception {
		assertIsOrganizedTo("""
module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass org.eclipse.ui.views.contentoutline.ContentOutline
		}
	}
	
	labelProvider {
		text {
			org.eclipse.emf.ecore.EClass -> ""
		}
	}
}
		""", """
import org.eclipse.emf.ecore.EClass
import org.eclipse.ui.views.contentoutline.ContentOutline

module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass ContentOutline
		}
	}
	
	labelProvider {
		text {
			EClass -> ""
		}
	}
}
		""");
	}
	
}
