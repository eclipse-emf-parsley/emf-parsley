EmfParsleySwtBotAllTests.launch: runs the EmfParsleyTestsSuite with all the bundles of the target platform

EmfParsleySwtBotAllTestsUsingFeature.launch: runs the EmfParsleyTestsSuite using the
org.eclipse.emf.parsley.tests.swtbot.feature (and the required bundles); this mimics the execution during
the Tycho build.

EmfParsleySwtBotAllTestsUsingAnotherDISPLAY.launch: as above, but using the environment variable
DISPLAY=:50 (this is useful in Linux using another display)

