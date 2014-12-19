package org.eclipse.emf.parsley.doc.websitegen.generator

class Support extends AbstractWebsite {

	override path() {
		"support.html"
	}

	override contents() '''
	<style>
		.divbox {
			border: 1px solid #e7e7e7;
			margin-bottom:2.5%;
			padding:5px 0px 32px 0px;
		}
	</style>
	<div class="container marketing" style="margin-top:6px;">
		<div class="row featurette">
			<a href="http://www.eclipse.org/forums/index.php?t=thread&frm_id=263" target="_blank">
				<div id="questiondiv" class="col-md-9 col-md-offset-1 divbox" style="background: none repeat scroll 0% 0% #e8f9ea; animation: zoomInRight 1.5s;">
					<img src="img/question.png" alt="" class="featurette-image img-responsive pull-left" style="width:64px; margin:25px 58px 0px 44px;">
					<h3 style="text-shadow: 0px 0px 4px #80BA81;color:#2B2B2B;">Got Questions?</h3>
					<p>
						The <b>EMF Parsley forum</b> is the first sources for getting answers.<br>Please ask any questions clearly and with sufficient information attached.
					</p>
				</div>
			</a>
			<a href="https://bugs.eclipse.org/bugs/buglist.cgi?classification=Modeling&component=Core&product=EMF.Parsley" target="_blank">
				<div id="bugdiv" class="col-md-9 col-md-offset-1 divbox" style="background: none repeat scroll 0% 0% rgb(246, 247, 227); animation: zoomInLeft 1.5s;">
					<img style="width:70px; margin:36px 55px 5px 41px;" class="featurette-image img-responsive pull-left" alt="" src="img/bug.png">
					<h3 style="text-shadow: 0px 0px 4px #B2B56E;color:#2B2B2B;">Found a Bug?</h3>
					<p>
						Bug reports and enhancement request are tracked at <b>bugs.eclipse.org</b>.<br>Please explain the problem and provide a reduced but reproducable example.
					</p>
				</div>
			</a>
			<a href="http://www.rcp-vision.com/?page_id=6927" target="_blank">
				<div id="suppdiv" class="col-md-9 col-md-offset-1 divbox" style="background: none repeat scroll 0% 0% rgb(242, 242, 242); animation: zoomInRight 1.5s;">
					<img style="width:48px; margin:39px 66px 5px 52px;" class="featurette-image img-responsive pull-left" alt="" src="img/support.png">
					<h3 style="text-shadow: 0px 0px 4px #9E7479;color:#2B2B2B;">Professional Support</h3>
					<p>
						Need training, problem solving, or just a prototype for your modeled UI?<br><b>RCP Vision</b> offers all kinds of professional consulting around EMF Parsley.
					</p>
				</div>
			</a>
			<a href="https://twitter.com/EmfParsley" target="_blank">
				<div id="twdiv" class="col-md-9 col-md-offset-1 divbox" style="background: none repeat scroll 0 0 #e8f3fc;margin-bottom:1.5%; animation: zoomInLeft 1.5s;">
					<img style="width:46px; margin:41px 67px 5px 53px;" class="featurette-image img-responsive pull-left" alt="" src="img/twitter_support.png">
					<h3 style="text-shadow: 0px 0px 4px #648CAD;color:#2B2B2B;">EmfParsley on Twitter</h3>
					<p>
						If you are on twitter and want to get notified about all things EmfParsley,<br>you should consider following <b>@EmfParsley</b>.
					</p>
				</div>
			</a>
		</div>
	</div>
	'''
}