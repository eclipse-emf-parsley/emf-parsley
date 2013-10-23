/**
 * 
 */
package org.eclipse.emf.parsley.tests.labeling;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;

/**
 * @author bettini
 * 
 */
public class CustomLibraryFeatureCaptionProvider extends FeatureCaptionProvider {

	public String text_Person_firstName(EStructuralFeature feature) {
		return "First name";
	}
	
	public String text_Person_lastName(EStructuralFeature feature) {
		return "Cognome";
	}
	
}
