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

package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jface.dialogs.MessageDialog;

import com.google.inject.Inject;

/**
 * Executes validation before saving.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class ValidateResourceSaveManager extends ResourceSaveManager {
    
    @Inject
    protected Diagnostician diagnostician;

	@Override
	protected boolean precondition(Resource resource) {
		return super.precondition(resource) && validateModel(resource);
	}

	protected boolean validateModel(Resource resource) {
		for (EObject eObject : resource.getContents()) {
			Diagnostic diagnostic = diagnostician.validate(eObject);
			if (diagnostic.getSeverity() == Diagnostic.ERROR) {
				String errors = buildMessageString(diagnostic, Diagnostic.ERROR);
				MessageDialog.openError(null, "Validation Error", errors);
				return false;
			} else if (diagnostic.getSeverity() == Diagnostic.WARNING) {
				String warnings = buildMessageString(diagnostic,
						Diagnostic.ERROR);
				MessageDialog.openWarning(null, "Validation Warning", warnings);
			}
		}
		return true;
	}

    protected String buildMessageString(Diagnostic diagnosticParent, int level) {
		String messages = "";
		for (Diagnostic diagnostic : diagnosticParent.getChildren()) {
			messages = recoursiveAddMessage(messages, diagnostic, level);
		}
		return messages;
	}

	protected String recoursiveAddMessage(String messages,
			Diagnostic diagnostic, int level) {
		if (diagnostic.getChildren().size() == 0) {
			messages += "- " + diagnostic.getMessage() + "\n\n";
		} else {
			for (Diagnostic diagnosticChild : diagnostic.getChildren()) {
				messages = recoursiveAddMessage(messages, diagnosticChild,
						level);
			}
		}
		return messages;
	}

}
