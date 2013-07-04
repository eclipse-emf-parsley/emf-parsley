/**
 * 
 */
package org.eclipse.emf.parsley.tests.labeling;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.PropertyDescriptionProvider;

/**
 * @author bettini
 * 
 */
public class CustomLibraryFeatureLabelProvider extends PropertyDescriptionProvider {

	public String text_Person_firstName(EStructuralFeature feature) {
		return "First name";
	}
	
	public String text_Person_lastName(EStructuralFeature feature) {
		return "Cognome";
	}
	
}
