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

class TestInputs {
	
	def emptyModule() 
'''
import java.util.*

module my.empty {
	
}
'''

	def moduleWithExtends() 
'''
import java.util.*

module my.empty extends org.eclipse.emf.parsley.dsl.tests.additional.MyTestGuiceModule {
	
}
'''

	def emptyLabelProvider() 
'''
import java.util.*

module my.empty {
	labelProvider {
		
	}
}
'''

	def emptyPropertyDescriptionProvider() 
'''
import java.util.*

module my.empty {
	featureCaptionProvider {
		
	}
}
'''

	def emptyLabelSpecifications() 
'''
import java.util.*

module my.empty {
	labelProvider {
		text {
			
		}
		image {
			
		}
	}
}
'''

	def emptyLabelSpecificationsForColumns() 
'''
import java.util.*

module my.empty {
	tableLabelProvider {
		text {
			
		}
		image {
			
		}
	}
}
'''


	def labelSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	labelProvider {
		text {
			Library -> 'foo' // constant
			Writer writer -> writer.getName() // explicit param
			Book -> title // implit 'it' param
			Lendable -> 'copies: ' + copies
			Borrower -> { // complex block
				val buffer = 'borrowed: ' + borrowed.map [
					b | b.copies
				]
				buffer.toUpperCase
			}
			BookOnTape -> getTitle
		}
		
		image {
			Library -> 'library.jpeg' // constant
			Writer writer -> 
				if (writer.name.nullOrEmpty) 
					"noname.gif"
				else
					new org.eclipse.swt.graphics.ImageData("writer.jpeg")
		}
	}
}
'''

	def labelSpecificationsForColumns() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	tableLabelProvider {
		text {
			Library:name -> 'Name' // constant
			Library:books -> 'Books' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
		
		image {
			Book: author -> 
				if (author.name.nullOrEmpty) 
					"noname.gif"
				else
					new org.eclipse.swt.graphics.ImageData("writer.jpeg")
		}
	}
}
'''

	def propertyDescriptionSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	featureCaptionProvider {
		text {
			Library:name -> 'Name' // constant
			Library:books -> 'Books' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
	}
}
'''

	def formPropertyDescriptionSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*
import org.eclipse.swt.widgets.Label

module my.empty {
	formFeatureCaptionProvider {
		text {
			Library:name -> 'Name' // constant
			Library:books -> 'Books' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
		label {
			Library:name -> createLabel(parent, "Name")
			Library:books -> createLabel(parent, EXTLibraryPackage.eINSTANCE.library_Books)
			Writer:lastName -> {
				new Label(parent, SWT.NONE) => [
					l |
					l.^text = name
				] 
				// the implicit param is an EStructuralFeature
				// 'name' comes from EStructuralFeature
			}
		}
	}
}
'''

	def dialogPropertyDescriptionSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*
import org.eclipse.swt.widgets.Label

module my.empty {
	dialogFeatureCaptionProvider {
		text {
			Library:name -> 'Name' // constant
			Library:books -> 'Books' // constant
			Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature
		}
		label {
			Library:name -> createLabel(parent, "Name")
			Library:books -> createLabel(parent, EXTLibraryPackage.eINSTANCE.library_Books)
			Writer:lastName -> {
				new Label(parent, SWT.NONE) => [
					l |
					l.^text = name
				] 
				// the implicit param is an EStructuralFeature
				// 'name' comes from EStructuralFeature
			}
		}
	}
}
'''

	def featuresSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	featuresProvider {
		features {
			Library -> name
			Writer -> firstName, lastName, books
		}
	}
}
'''

	def formControlFactory()
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	formControlFactory {
		control {
			Library : name -> { }
			Writer : books -> 
				createLabel(
					books.map[title].join(", "))
			Writer : name -> { createLabel(parent, "") }
				target { observeText }
			Writer : firstName -> 
				toolkit.createLabel(parent, "")
				target observeText(SWT::Modify)
			Borrower : firstName -> {
				createText(firstName, SWT::MULTI, SWT::BORDER,
									SWT::WRAP, SWT::V_SCROLL)
			}
		}
	}
}
'''

	def dialogControlFactory()
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	dialogControlFactory {
		control {
			Library : name -> { }
			Writer : books -> 
				createLabel(
					books.map[title].join(", "))
			Writer : name -> { createLabel(parent, "") }
				target { observeText }
			Writer : firstName -> 
				createLabel(parent, "")
				target observeText(SWT::Modify)
			Borrower : firstName -> {
				createText(firstName, SWT::MULTI, SWT::BORDER,
									SWT::WRAP, SWT::V_SCROLL)
			}
		}
	}
}
'''

	def proposalsSpecifications()
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	proposals {
		Library : name -> { newArrayList('foo', 'bar') }
		Writer : books -> 
			{ books } // it.books
		Book : author -> {
			newArrayList(
				EXTLibraryFactory::eINSTANCE.createWriter => [
					name = "Foo"
				],
				EXTLibraryFactory::eINSTANCE.createWriter => [
					name = "Bar"
				]
			)
		}
		Borrower : borrowed -> {
			val p = defaultProposals(feature) => [
				add(EXTLibraryFactory::eINSTANCE.createBook => [
					title = "Fake Book"
				])
			]
			return p
		}
	}
}
'''

	def viewerContentProviderSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*
import org.eclipse.emf.ecore.resource.Resource

module my.empty {
	viewerContentProvider {
		elements {
			Resource -> allContents.filter(Library)
		}
		
		children {
			Library -> books + writers
			Writer writer -> writer.books
			Book -> author // implit 'it' param
		}
	}
}
'''

	def emptyViewsSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	parts {
		
	}
}
'''

	def nonEmptyViewsSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*
import org.eclipse.emf.parsley.views.*

module my.test {
	parts {
		viewpart my.view.part {
			viewname "My View"
			viewclass AbstractSaveableTreeView
		}
	}
}
'''

	def multipleViewsSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*
import org.eclipse.emf.parsley.views.*

module my.test {
	parts {
		viewpart my.view.tree.part {
			viewname "My Tree View"
			viewclass AbstractSaveableTreeView
		}
		viewpart my.view.form.part {
			viewname "My Tree Form View"
			viewclass AbstractSaveableTreeFormView
			viewcategory my.view.category
		}
	}
}
'''

	def tableFeaturesSpecifications() 
'''
import java.util.*
import org.eclipse.emf.parsley.examples.library.*

module my.empty {
	tableFeaturesProvider {
		features {
			Library -> name
			Writer -> firstName, lastName, books
		}
	}
}
'''
}