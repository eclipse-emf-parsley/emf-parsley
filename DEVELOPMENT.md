# Development Eclipse EMF Parsley

Build and target-platform resolution require JDK 25. EMF Parsley bundles remain
compiled with a Java 21 source and target level.

## Update to a new Eclipse release

* Update the Oomph file `org.eclipse.emf.parsley.oomph/EMFParsley.setup`: add another target platform node (copying the latest existing one) for the current Eclipse release, in "Modular Target" both for "RCP" and "RAP".
    * For RCP, use the Eclipse staging repository and update the Orbit update site for the current release.
    * For RAP, retain the compatible Eclipse release repository and add the staging repository for Xtext and MWE2.
    * Change the default value of `eclipse.target.platform` to the new target platform
* Run the Oomph workflow (when run from Eclipse already Oomphed, change the Target Plarform to the latest one).

## Update to a new Xtext release

* Update the Oomph file `org.eclipse.emf.parsley.oomph/EMFParsley.setup`: use the Eclipse staging repository for Xtext and MWE2 and change the Xtext version range in "master / P2 Directory / Xtext Releases".
* Update the Xtext version in `/org.eclipse.emf.parsley.workspace/feature.xml`.
* Update the Xtext version in  `/org.eclipse.emf.parsley.dsl.feature/feature.xml`.
* Update the `.target` files to use the Eclipse staging repository for Xtext and MWE2.
* Update the properties for Xtext and MWE2 in the parent POM.
* Run the Oomph workflow.