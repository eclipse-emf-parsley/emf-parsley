/**
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 */
package org.eclipse.emf.parsley.tests.swtbot.binding;

import java.util.List;

import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;

public class CustomProposalCreator extends ProposalCreator {
	public List<String> proposals_ClassForControls_stringFeature(ClassForControls e) {
		return List.of("First Proposal", "Second Proposal");
	}
}
