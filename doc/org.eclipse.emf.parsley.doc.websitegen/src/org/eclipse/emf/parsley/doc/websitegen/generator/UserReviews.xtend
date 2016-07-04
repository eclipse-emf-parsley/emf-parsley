package org.eclipse.emf.parsley.doc.websitegen.generator

class UserReviews extends AbstractWebsite {

	override path() {
		"userReviews.html"
	}

	override contents() '''
	<style>
		.divbox {
			border: 1px solid #e7e7e7;
			margin-bottom:2.5%;
			padding:5px 0px 32px 0px;
		}
		
		#questiondiv,#suppdiv {
			-webkit-animation: zoomInRight 1.5s;
			-moz-animation: zoomInRight 1.5s;
			-ms-animation: zoomInRight 1.5s;
			-o-animation: zoomInRight 1.5s;
			animation: zoomInRight 1.5s;
		}
		
		#bugdiv,#twdiv {
			-webkit-animation: zoomInLeft 1.5s;
			-moz-animation: zoomInLeft 1.5s;
			-ms-animation: zoomInLeft 1.5s;
			-o-animation: zoomInLeft 1.5s;
			animation: zoomInLeft 1.5s;
		}
	</style>
	<div class="container marketing" style="margin-top:6px;">
		<div class="row featurette">
		<a href="https://goo.gl/pLtvOg" target="_blank">
			<div id="questiondiv" class="col-md-9 col-md-offset-1 divbox" style="background: none repeat scroll 0% 0% #e8f9ea;">
				<h3 style="text-shadow: 0px 0px 4px #80BA81;color:#2B2B2B;margin-left: 10px;">Ed Merks - EMF Project Lead</h3>
				<p style="margin-left: 20px;margin-right: 20px;">
					<b>"What you've showed is crazy cool..."</b> commenting the First talk on EMF Parsley at EclipseConEurope 2013!
					<br><br><i>Click for the video</i>
				</p>
			</div>
		</a>
		<a href="http://www.objectsecurity.com/img/2a.jpg" target="_blank">
			<div id="bugdiv" class="col-md-9 col-md-offset-1 divbox" style="background: none repeat scroll 0% 0% rgb(246, 247, 227);">
				<h3 style="text-shadow: 0px 0px 4px #B2B56E;color:#2B2B2B;margin-left: 10px;">Ulrich Lang - Object Security</h3>
				<p style="margin-left: 20px;margin-right: 20px;">
					“EMF Parsley has helped us achieve some of the specific UI goals for our EMF-based product. We were looking for a tool that would give us a flexible, low-maintenance EMF-based model editing capability without hard-coding etc. and that would support Eclipse RAP. At the same time we were looking for ways to make the editor visually appealing. 
					And because this was for production use, we needed good documentation and technical support. EMF Parsley met these requirements very well. 
					<br>And the Parsley technical support team at RCP-Vision was great to assist us, very competent and responsive.”
					<br><br><i>Click for a screenshot</i>
			</p>
			</div>
		</a>
	</div>
	</div>
	'''
}