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
package org.eclipse.emf.parsley.dsl.tests.inputs

class TestInputsWithErrors {
	
	def duplicateLabelSpecifications() 
'''
import java.util.*

module my.empty {
	labelProvider {
		text {
			
		}
		image {
			
		}
		text {
		}
	}
}
'''

	def wrongPropertyDescriptionSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*


module my.empty {
	featureCaptionProvider {
		text {
			Item:publicationDate -> 'Publication Date'
			Library:newArrayList -> 'Name' // static method, wrong
			Library:getBooks -> 'Books' // don't use get methods
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
	}
}
'''

	def viewSpecificationIsNotIViewPart() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	parts {
		viewpart my.view.part {
			viewname "My View"
			viewclass Library // not an IViewPart!
		}
	}
}
'''

	def notAnEObjectInFeatureCaptionProvider() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*
import org.eclipse.emf.parsley.views.*

module my.empty {
	featureCaptionProvider {
		text {
			List:publicationDate -> 'Publication Date'
			AbstractSaveableTreeView:lastName -> null
		}
	}
}
'''

	def notAnEObjectInFeaturesProvider() 
'''
import java.util.List

module my.empty {
	featuresProvider {
		features {
			List -> null
		}
	}
}
'''

	def notValidModuleExtends() 
'''
import org.eclipse.emf.parsley.examples.library.Library

module my.empty extends Library {

}
'''

}