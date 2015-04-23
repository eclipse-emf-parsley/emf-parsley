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
package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.util.ReplaceRegion
import org.eclipse.xtext.xbase.imports.ImportOrganizer
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

/**
 * @author Lorenzo Bettini - inspired by the Xtext Domainmodel example
 */
@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslOrganizeImportsTests {
	
	@Inject extension ParseHelper<Model> 
	@Inject ImportOrganizer importOrganizer

	def protected assertIsOrganizedTo(CharSequence input, CharSequence expected) {
		val model = parse(input.toString)
		val changes = importOrganizer.getOrganizedImportChanges(model.eResource as XtextResource)
		val builder = new StringBuilder(input)
		val sortedChanges= changes.sortBy[offset]
		var ReplaceRegion lastChange = null
		for(it: sortedChanges) {
			if(lastChange != null && lastChange.endOffset > offset)
				fail("Overlapping text edits: " + lastChange + ' and ' +it)
			lastChange = it
		}
		for(it: sortedChanges.reverse)
			builder.replace(offset, offset + length, text)
		assertEquals(expected.toString, builder.toString)
	}

	@Test def testTypeInLabelProvider() {
		'''
module my.test.proj {
	labelProvider {
		text {
			org.eclipse.emf.ecore.EClass -> ""
		}
	}
}
		'''.assertIsOrganizedTo('''
import org.eclipse.emf.ecore.EClass

module my.test.proj {
	labelProvider {
		text {
			EClass -> ""
		}
	}
}
		''')
	}

	@Test def testWithWildCard() {
		'''
import org.eclipse.emf.ecore.*

module my.test.proj {
	labelProvider {
		text {
			EClass -> ""
		}
	}
}
		'''.assertIsOrganizedTo('''
import org.eclipse.emf.ecore.EClass

module my.test.proj {
	labelProvider {
		text {
			EClass -> ""
		}
	}
}
		''')
	}

	@Test def testForViewSpecification() {
		'''
import org.eclipse.ui.views.contentoutline.ContentOutline

module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass ContentOutline
		}
	}
}
		'''.assertIsOrganizedTo('''
import org.eclipse.ui.views.contentoutline.ContentOutline

module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass ContentOutline
		}
	}
}
		''')
	}

	@Test def testForManyTypes() {
		'''
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
		'''.assertIsOrganizedTo('''
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
		''')
	}
	
}