# Development Eclipse EMF Parsley

## Update to a new Eclipse release

* Update the Oomph file `org.eclipse.emf.parsley.oomph/EMFParsley.setup`: add another target platform node, e.g., "2024-03" (copying the latest existing one), for the current Eclipse release, in "Modular Target" both for "RCP" and "RAP".
    * In that new node for the target platform update the URL of the Eclipse releases and the Orbit update site (e.g., https://download.eclipse.org/tools/orbit/simrel/orbit-aggregation/2024-03).
    * Change the default value of `eclipse.target.platform` to the new target platform
* Run the Oomph workflow (when run from Eclipse already Oomphed, change the Target Plarform to the latest one).

## Update to a new Xtext release

* Update the Oomph file `org.eclipse.emf.parsley.oomph/EMFParsley.setup`: replace the Xtext and MWE2 update sites in the corresponding properties (`xtext.site` and`mwe2.site`). Change the Xtext version range in "master / P2 Directory / Xtext Releases".
* Update the Xtext version in `/org.eclipse.emf.parsley.workspace/feature.xml`.
* Update the Xtext version in  `/org.eclipse.emf.parsley.dsl.feature/feature.xml`.
* Update the `.target` files: Both the `simrel.target` and `simrel-rap.target` in `org.eclipse.emf.parsley.targetplatform` with the URL of the Xtext and MWE2 update sites.
* Update the properties for Xtext and MWE2 in the parent POM.
* Run the Oomph workflow.