/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Lorenzo Bettini - Initial Contribution
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EmfParsleyOnSelectionViewTests.class,
	EmfParsleySaveableViewTests.class,
	EmfParsleyTestViewsTests.class,
	EmfParsleyDragAndDropTests.class,
	EmfParsleyEditableTableTests.class,
	EmfParsleyEditorTests.class,
	EmfParsleyFormBasedDialogTests.class,
	EmfParsleyFormTests.class,
	EmfParsleyDialogTests.class,
	EmfParsleyEditingStrategyTests.class,
	EmfParsleyExamplesWizardsTests.class,
	EmfParsleyDslWizardsTests.class,
	EmfParsleyDslEditorTests.class
})
public class EmfParsleyTestsSuite {
}
