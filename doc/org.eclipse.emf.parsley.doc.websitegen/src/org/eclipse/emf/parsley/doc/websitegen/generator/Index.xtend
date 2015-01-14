package org.eclipse.emf.parsley.doc.websitegen.generator

class Index extends AbstractWebsite {

	override path() {
		"index.html"
	}

	override contents() '''
	<style>
		.row {
			-webkit-animation: zoomIn 1.5s;
			-moz-animation: zoomIn 1.5s;
			-ms-animation: zoomIn 1.5s;
			-o-animation: zoomIn 1.5s;
			animation: zoomIn 1.5s;
		}
			
		#rbhover>img:hover{
			-webkit-animation: rubberBand 0.8s;
			-moz-animation: rubberBand 0.8s;
			-ms-animation: rubberBand 0.8s;
			-o-animation: rubberBand 0.8s;
			animation: rubberBand 0.8s;
		}
	</style>
	<div class="container marketing" style="margin-top:34px;">
		<div class="row featurette">
			<div class="col-sm-6 col-md-6">
				<h2 class="featurette-heading" style="margin-top:-13px;color: #246025;">What is EMF Parsley?</h2><!-- <span class="text-muted">It'll blow your mind.</span> -->
				<p class="lead" style="text-align: justify; padding-right: 5px;margin-bottom: 26px;color:#383838;">EMF Parsley is an Eclipse project that provides a set of reusable UI components based on EMF (trees, tables, forms, views, editors), hiding most of the complexity of internal details, that can be used in your EMF-based plugin out-of-the-box. These parts are easily customizable via a DSL, implemented in Xtext. This project has been designed starting from EMF UI generated code, adding extensibility mechanism that make it very modular: all components are highly reusable and customizable via Dependency Injection (via Google Guice).<BR>As in cooking, EMF Parsley aims to be used in several EMF based parts. Its little components can be embedded whereever there is an EMF model and fully customized.</p>					
				<div class="row">
					<div class="col-md-5 col-md-offset-1"><a id="rbhover" href="download.html" style="text-decoration: none;font-size: 19px; font-family: Corbert, Segoe UI,sans-serif; letter-spacing: -0.5px;"><img class="featurette-image img-responsive pull-left" alt="" src="img/dowload_icon.png"/></br>Download</a></div>
					<div class="col-md-6" style="padding-left:0px;"><a id="rbhover" href="documentation.html" style="text-decoration: none;font-size: 19px; font-family: Corbert, Segoe UI,sans-serif; letter-spacing: -0.5px;"><img class="featurette-image img-responsive pull-left" alt="" src="img/documentation_icon.png"/></br>Documentation</a></div>
				</div>
			</div>
			<div class="col-md-1" style="width:4%"></div>
			<div class="embed-responsive embed-responsive-16by9">
			  <iframe class="embed-responsive-item" style="border: 4px solid rgb(229, 236, 242);" wmode="Opaque" allowfullscreen="" src="https://www.youtube.com/embed/-S8mh5p-ChE"></iframe>
			</div>
		</div>
		<div class="row featurette" style="margin-top:8%;">
			<div class="col-md-10 col-md-offset-1" style="padding:0px;"><img class="img-responsive" src="img/made.jpg"></div>
		</div>
		<div class="row featurette" style="margin-top:7.5%;margin-bottom:6%;">
			<div class="col-md-10 col-md-offset-1" style="padding:0px;">
			  <a href="#"><img id="zoom_img" class="featurette-image img-responsive" src="img/works.jpg" style="margin-left:auto;margin-right:auto;"></a>
			</div>
			<div id="myModal" class="modal fade" tabindex="-1" role="dialog" style="overflow:hidden;">
				<div class="lightbox-content img-responsive" style="display: block; margin: 5% 1%;"></div>
			</div>
		</div>
	</div>
	'''
}