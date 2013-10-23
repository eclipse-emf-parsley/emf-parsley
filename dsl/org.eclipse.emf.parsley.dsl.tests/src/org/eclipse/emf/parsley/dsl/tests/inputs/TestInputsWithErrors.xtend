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

	def notAnEObjectInEmfFeatureAccess() 
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

	def notValidModuleExtends() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*
import org.eclipse.emf.parsley.views.*

module my.empty extends Library {

}
'''
}