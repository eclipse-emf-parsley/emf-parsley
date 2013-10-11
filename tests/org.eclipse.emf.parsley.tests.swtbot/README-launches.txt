EmfParsleyTestsSuite.launch is the launch configuration for
running all SWTBOT tests from the workspace
(it is a SWTBOT Test launch configuration).

EmfParsleySwtBotAllTests.launch is the launch configuration
running all SWTBOT tests from Buckminster
(it is a Junit Plug-in Test launch configuration
with "Run in UI Thread" unchecked, so that it mimics
SWTBOT Test launch but it can be run from Buckminster
where SWTBOT features are not installed).

All launches are based on a single feature,
org.eclipse.emf.parsley.tests.swtbot.feature,
which brings all the necessary dependencies.
