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
package org.eclipse.emf.parsley.dsl.ui.tests

import org.eclipse.emf.parsley.dsl.ui.internal.DslActivator
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.ui.tests.editor.outline.AbstractOutlineWorkbenchTest
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Lorenzo Bettini
 */
@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslUiInjectorProvider))
class EmfParsleyDslOutlineTest extends AbstractOutlineWorkbenchTest {
	
	override protected getEditorId() {
		DslActivator.ORG_ECLIPSE_EMF_PARSLEY_DSL_EMFPARSLEYDSL
	}

	@Test
	def void testOutlineOfParsleyDslFile() {
		'''
import my.proj.ProjTreeFormView
import org.eclipse.emf.parsley.edit.IEditingStrategy
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy

/* my.proj Emf Parsley Dsl Module file */
module my.proj {
	
	parts {
		viewpart my.proj {
			viewname "Proj"
			viewclass ProjTreeFormView
		}
	}
	
	bindings {
		type IEditingStrategy -> UndoableEditingStrategy
	}

	labelProvider {
		text {
			
		}
	}

	configurator {
		eClass {
			
		}
	}

	dialogControlFactory {
		control {
			
		}
	}

	dialogFeatureCaptionProvider {
		label {
			
		}
	}

	featureCaptionProvider {
		text {
			
		}
	}

	featuresProvider {
		features {
			
		}
	}

	formControlFactory {
		control {
			
		}
	}

	formFeatureCaptionProvider {
		label {
			
		}
	}

	proposals {
		
	}

	resourceManager {
		initializeResource {
			
		}
	}

	tableFeaturesProvider {
		features {
			
		}
	}

	tableLabelProvider {
		text {
			
		}
	}

	viewerContentProvider {
		elements {
			
		}
	}

	tableViewerContentProvider {
		elements {
			
		}
	}

	menuBuilder {
		menus {
			
		}
	}
}
'''.assertAllLabels(
'''
my.proj
  parts
  bindings
  labelProvider
  configurator
  dialogControlFactory
  dialogFeatureCaptionProvider
  featureCaptionProvider
  featuresProvider
  formControlFactory
  formFeatureCaptionProvider
  proposals
  resourceManager
  tableFeaturesProvider
  tableLabelProvider
  viewerContentProvider
  tableViewerContentProvider
  menuBuilder
'''
		)
	}
}