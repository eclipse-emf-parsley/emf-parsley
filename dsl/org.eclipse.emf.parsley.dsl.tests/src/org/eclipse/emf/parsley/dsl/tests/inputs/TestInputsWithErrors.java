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
package org.eclipse.emf.parsley.dsl.tests.inputs;

public class TestInputsWithErrors {
	
	public CharSequence duplicateLabelSpecifications() {
		return """
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
""";
	}

	public CharSequence wrongPropertyDescriptionSpecifications() {
		return """
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
""";
	}

	public CharSequence viewSpecificationIsNotIViewPart() {
		return """
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
""";
	}

	public CharSequence notAnEObjectInFeatureCaptionProvider() {
		return """
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
""";
	}

	public CharSequence notAnEObjectInFeaturesProvider() {
		return """
import java.util.List

module my.empty {
	featuresProvider {
		features {
			List -> null
		}
	}
}
""";
	}

	public CharSequence notValidModuleExtends() {
		return """
import org.eclipse.emf.parsley.examples.library.Library

module my.empty extends Library {

}
""";
	}

}
