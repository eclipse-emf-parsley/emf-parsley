# Development Eclipse EMF Parsley

## Update to a new Eclipse release and Xtext release

* Update the Oomph file `devtools/org.eclipse.emf.parsley.oomph/EMFParsley.setup`: add another target platform node, e.g., "2024-03" (copying the latest existing one), for the current Eclipse release, in "Modular Target" both for "RCP" and "RAP".
    * In that new node for the target platform update the URL of the Eclipse releases and the Orbit update site (e.g., https://download.eclipse.org/tools/orbit/simrel/orbit-aggregation/2024-03).
    * Change the default value of `eclipse.target.platform` to the new target platform
* Run the Oomph workflow (when run from Eclipse already Oomphed, change the Target Plarform to the latest one).

## Update the `.target` files

Both the `simrel.target` and `simrel-rap.target` in `org.eclipse.emf.parsley.targetplatform` with the URL of the Eclipse releases and the Orbit update site (e.g., https://download.eclipse.org/tools/orbit/simrel/orbit-aggregation/2024-03)