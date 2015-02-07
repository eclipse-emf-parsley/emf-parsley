Use the ant file to create a .target definition file, starting from another
.target file, but replacing all URLs to remote update sites with URLs 
pointing to a local file system path where a mirror of the original URLs
has previously been created (see the .b3aggr files).

The generated local.target file is automatically ignored by the
Git repository.

This file can be used by selecting the "local" profile in the
main pom.xml file.

--------------------------------------------------------------

rap-example-test.target will be used only to test that we can build the RAP examples, using the target platform
example project that we provide, org.eclipse.emf.parsley.examples.rap.targetplatform.
In this project we create a test .target file that points to the p2 repository that has
been created during the Tycho build (using the rap profile).

The .target file in this project will be created during the Tycho build and will not be
put in the git repository.