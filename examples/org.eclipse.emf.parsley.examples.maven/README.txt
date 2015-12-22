This is a Maven example project to check whether we can compile a Parsley module from Maven
using the Maven artifacts that we publish on Maven Central.

To check that, first you should run on the org.eclipse.emf.parsley.parent project
an install using the maven profile

mvn clean install -Pmaven

Then from this project you can run

mvn clean verify
