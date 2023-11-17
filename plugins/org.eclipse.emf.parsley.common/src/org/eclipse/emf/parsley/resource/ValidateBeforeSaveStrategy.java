/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.resource;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.validation.IssueReporter;
import org.eclipse.emf.parsley.validation.ValidationRunner;

import com.google.inject.Inject;

/**
 * Before saving the {@link Resource} it validates it, and effectively save it
 * only if there are no errors.
 *
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class ValidateBeforeSaveStrategy extends ResourceSaveStrategy {

	@Inject
	private ValidationRunner validationRunner;

	@Inject
	private IssueReporter issueReporter;

	@Override
	public boolean save(Resource resource) throws IOException {
		if (!precondition(resource)) {
			return false;
		}
		return super.save(resource);
	}

	protected boolean precondition(Resource resource) {
		return validationRunner.validate(resource, issueReporter).size() == 0;
	}
}
