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
					To install via the update site URL listed below, copy and paste it into the "Help &gt; Install new software" dialog.
				</p>
				<br>
				<p>
				Each EMF Parsley DSL version (included in the EMF Parsley SDK) requires a specific version of Xtext. Unless you install EMF Parsley from
				the Eclipse releases update site that includes that specific version of EMF Parsley (and the corresponding
				required version of Xtext) you should add the Xtext releases composite update site as well,
				so that the required Xtext version will be automatically installed:<br>
				<a href="http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases">http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases</a>
				</p>
				<br><br>
				<div>
					<h2 class="featurette-heading text-parsley1">Update Sites</h2>
					<ul>
						<li>Version 1.2.x is part of Oxygen: <a href="http://download.eclipse.org/releases/oxygen/">http://download.eclipse.org/releases/oxygen/</a></li>
						<li>Version 1.0.x and 1.1.x are part of Neon: <a href="http://download.eclipse.org/releases/neon/">http://download.eclipse.org/releases/neon/</a></li>
						<li>All Releases: <a href="http://download.eclipse.org/emf-parsley/updates">http://download.eclipse.org/emf-parsley/updates</a></li>
						<li>1.2.x: <a href="http://download.eclipse.org/emf-parsley/updates/1.2">http://download.eclipse.org/emf-parsley/updates/1.2</a>
							(EMF Parsley DSL requires Xtext 2.12.0).</li>
						<li>1.1.x: <a href="http://download.eclipse.org/emf-parsley/updates/1.1">http://download.eclipse.org/emf-parsley/updates/1.1</a>
							(EMF Parsley DSL requires Xtext 2.10.0).</li>
						<li>1.0.x: <a href="http://download.eclipse.org/emf-parsley/updates/1.0">http://download.eclipse.org/emf-parsley/updates/1.0</a>
							(EMF Parsley DSL requires Xtext 2.10.0).</li>
						<li>0.7.x: <a href="http://download.eclipse.org/emf-parsley/updates/0.7">http://download.eclipse.org/emf-parsley/updates/0.7</a>
							(EMF Parsley DSL requires Xtext 2.9.2).</li>
						<li>0.6.x: <a href="http://download.eclipse.org/emf-parsley/updates/0.6">http://download.eclipse.org/emf-parsley/updates/0.6</a>
							(EMF Parsley DSL requires Xtext 2.9.1).</li>
						<li>0.5.x: <a href="http://download.eclipse.org/emf-parsley/updates/0.5">http://download.eclipse.org/emf-parsley/updates/0.5</a>
							(EMF Parsley DSL requires Xtext 2.8.4).</li>
						<li>0.4.x: <a href="http://download.eclipse.org/emf-parsley/updates/0.4">http://download.eclipse.org/emf-parsley/updates/0.4</a>
							(EMF Parsley DSL requires Xtext 2.8.3).</li>
						<li>0.3.x: <a href="http://download.eclipse.org/emf-parsley/updates/0.3">http://download.eclipse.org/emf-parsley/updates/0.3</a>
							(EMF Parsley DSL requires Xtext 2.7.3).</li>
					<li>Milestones: <a href="http://download.eclipse.org/emf-parsley/milestones/">http://download.eclipse.org/emf-parsley/milestones/</a></li>
					<li>Latest Stable Nightly Build: <a href="https://hudson.eclipse.org/emf-parsley/job/emf-parsley-nightly/lastSuccessfulBuild/artifact/target/repository/">https://hudson.eclipse.org/emf-parsley/job/emf-parsley-nightly/lastSuccessfulBuild/artifact/target/repository/</a></li>
					<!--
					<li>Latest Stable Snapshot Build: <a href="https://hudson.eclipse.org/emf-parsley/job/emf-parsley-tycho-gerrit/lastSuccessfulBuild/artifact/target/repository/">https://hudson.eclipse.org/emf-parsley/job/emf-parsley-tycho-gerrit/lastSuccessfulBuild/artifact/target/repository/</a></li>
					-->
					</ul>
					<p>
						All downloads are provided under the terms and conditions of the Eclipse Foundation Software User Agreement unless otherwise specified.
					</p>
				</div>
				<br><br>
				<div>
					<h2 class="featurette-heading text-parsley1">Old Milestones</h2>
					<ul>
						<li>These are old milestone releases: <a href="http://download.eclipse.org/emf-parsley/archive/updates">http://download.eclipse.org/emf-parsley/archive/updates</a></li>
					</ul>
					<p>
						All downloads are provided under the terms and conditions of the Eclipse Foundation Software User Agreement unless otherwise specified.
					</p>
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