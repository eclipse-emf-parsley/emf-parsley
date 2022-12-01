This is a Maven example project to check whether we can compile a Parsley module from Maven
using the Maven artifacts that we publish on Maven Central.

To check that, first you should run on the org.eclipse.emf.parsley.parent project
an install using the maven profile

mvn clean install -Pmaven

Then from this project you can run

mvn clean verify

In the "launches" directory there's a launch configuration to install Parsley bundles in a
local Maven repository (${user.home}/tmp/maven-repository). After that, you can use
the other launch configuration to build the example against the installed Maven repository.
