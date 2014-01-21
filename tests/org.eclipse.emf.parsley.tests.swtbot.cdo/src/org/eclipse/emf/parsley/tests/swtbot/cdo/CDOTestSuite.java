/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.cdo;

import org.eclipse.emf.parsley.tests.swtbot.cdo.treeform.CDOTreeFormDirtyAndSaveableTests;
import org.eclipse.emf.parsley.tests.swtbot.cdo.treeform.CDOTreeFormTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	CDOTreeFormTests.class,
	CDOTreeFormDirtyAndSaveableTests.class
})
public class CDOTestSuite {
	
}