package org.eclipse.emf.parsley.doc.websitegen.generator

class Download extends AbstractWebsite {

	override path() {
		"download.html"
	}

	override contents() '''
	<style>
	.row {
		-webkit-animation: fadeInDownBig 1s;
		-moz-animation: fadeInDownBig 1s;
		-ms-animation: fadeInDownBig 1s;
		-o-animation: fadeInDownBig 1s;
		animation: fadeInDownBig 1s;
	}
	
	#baseanim {
		padding:0px;
		width: 50px;
		margin: 8px 0px 0px -100px;
	}
	
	#arrowanim {
		padding:0px;
		width: 22px;
		margin: -12px 0px 0px -85px;
		-webkit-animation: mybounceInDown 2s;
		-moz-animation: mybounceInDown 2s;
		-ms-animation: mybounceInDown 2s;
		-o-animation: mybounceInDown 2s;
		animation: mybounceInDown 2s;
	}
	</style>

	<div class="container marketing" style="margin-top:2%;">
		<div class="row featurette">
			<div class="col-md-10 col-md-offset-1">
				<img src="img/arrow_down.png" alt="" class="featurette-image img-responsive pull-left" id="arrowanim">
				<img src="img/base.png" alt="" class="featurette-image img-responsive pull-left" id="baseanim">
				<h1 class="featurette-heading text-parsley">EMF Parsley - Downloads</h1>
				<p>
					EMF Parsley is part of the Eclipse simultaneous release, so you will find it using the standard Eclipse
					release update site, e.g.,
					<a href="https://download.eclipse.org/releases/2022-12/">https://download.eclipse.org/releases/2022-12/</a>
					.
				</p>
				<br>
				<p>
				Each EMF Parsley DSL version (included in the EMF Parsley SDK) requires a specific version of Xtext. Unless you install EMF Parsley from
				the Eclipse releases update site that includes that specific version of EMF Parsley (and the corresponding
				required version of Xtext) you should add the Xtext releases composite update site as well,
				so that the required Xtext version will be automatically installed (but please remember that it is a
				huge composite site:<br>
				<a href="https://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases">https://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases</a>.
				Alternatively, you can use an Xtext specific version's update site.
				</p>
				<br><br>
				<div>
					<h2 class="featurette-heading text-parsley1">Update Sites</h2>
					If you do not want to install EMF Parsley from the Eclipse simultaneous release update site,
					these are the EMF Parsley update sites
					<ul>
						<li>Composite update site for all recent Releases:
						<a href="https://download.eclipse.org/emf-parsley/updates">https://download.eclipse.org/emf-parsley/updates</a></li>
						<li>Composite update site for a specific MAJOR.MINOR release, e.g., for 1.14.x:
						<a href="https://download.eclipse.org/emf-parsley/updates/1.14/">https://download.eclipse.org/emf-parsley/updates/1.14/</a></li>
						<li>Milestones (when available): <a href="https://download.eclipse.org/emf-parsley/milestones/">https://download.eclipse.org/emf-parsley/milestones/</a></li>
						<li>Latest Stable Build: <a href="https://ci.eclipse.org/emf-parsley/view/Simrel/job/parsley-multibranch/job/master/lastSuccessfulBuild/artifact/target/repository/">https://ci.eclipse.org/emf-parsley/view/Simrel/job/parsley-multibranch/job/master/lastSuccessfulBuild/artifact/target/repository/</a></li>
					</ul>
					<p>
						All downloads are provided under the terms and conditions of the Eclipse Foundation Software User Agreement unless otherwise specified.
					</p>
				</div>
				<br><br>
				<div>
					<h2 class="featurette-heading text-parsley1">Old Archived Stuff</h2>
					<ul>
						<li><a href="https://archive.eclipse.org/emf-parsley/">https://archive.eclipse.org/emf-parsley/</a></li>
					</ul>
				</div>
				<br><br>
				<div style="margin-bottom:9%;">
				<h2 class="featurette-heading text-parsley1">Links</h2>
				<p>
					<a href="https://projects.eclipse.org/content/eclipse-public-license-1.0">Eclipse Public License 1.0</a>
				</p>
				</div>
			</div>
		</div>
	</div>
	'''
}