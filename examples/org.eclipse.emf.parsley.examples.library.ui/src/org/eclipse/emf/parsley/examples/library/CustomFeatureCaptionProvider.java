/**
 * 
 */
package org.eclipse.emf.parsley.examples.library;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;

/**
 * @author bettini
 * 
 */
public class CustomFeatureCaptionProvider extends FeatureCaptionProvider {

	public String text_Person_firstName(EStructuralFeature f) {
		return "First name";
	}

	public String text_Person_lastName(EStructuralFeature f) {
		return "Surname";
	}
}
