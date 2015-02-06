This will be used only to test that we can build the RAP examples, using the target platform
example project that we provide, org.eclipse.emf.parsley.examples.rap.targetplatform.

For this compilation test, we won't be using the actual .target file of
org.eclipse.emf.parsley.examples.rap.targetplatform (since that refers to a released
EMF Parsley update site with rap features, and we want to test the target platform resolution
against what we've just built).

So first the maven build to create the EMF Parsley repository with RAP features must be
run; during that run, a .target file pointing to the repository we've just built will
be created in the project org.eclipse.emf.parsley.examples.rap.test.targetplatform (note the
.test), which is basically the one of org.eclipse.emf.parsley.examples.rap.targetplatform
after replacing the URL http://download.eclipse.org/emf-parsley/rt with the local file
system path where we built the repository). Note that for RAP features we still point to
the original rap site.

Then we can run the maven build from this parent project, which will compile
our rap examples using the target platform that we created during the previous build.