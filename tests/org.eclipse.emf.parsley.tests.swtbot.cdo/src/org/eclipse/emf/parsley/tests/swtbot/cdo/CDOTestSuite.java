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