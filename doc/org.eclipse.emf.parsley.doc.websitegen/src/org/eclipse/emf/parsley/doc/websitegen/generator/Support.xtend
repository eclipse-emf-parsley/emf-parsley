package org.eclipse.emf.parsley.doc.websitegen.generator

class Support extends AbstractWebsite {

	override path() {
		'support.html'
	}
	
	override contents() '''
	<div id="header_wrapper" class="container" >
	</div>
		<div id="page">
			<div id="team" class="container clearfix"> 
				<h2>Resources</h2>
		        <hr>
				«entry('''
						icon_forum.png
					''','''
						Got Questions?
					''','''
						https://www.eclipse.org/forums/index.php/f/263/
					''','''
						<p>The <strong>EMF Parsley forum</strong> is the first sources for getting answers.
						<br>Please ask any questions clearly and with sufficient information attached.</p>
					''')»
				«entry('''
						icon_bugzilla.png
					''','''
						Found a Bug?
					''','''
						https://bugs.eclipse.org/bugs/buglist.cgi?classification=Modeling&component=Core&product=EMF.Parsley
					''','''
						<p>Bug reports and enhancement request are tracked at <strong>bugs.eclipse.org</strong>. Please 
						explain the problem and provide a reduced but reproducable example.</p>
					''')»
				«entry('''
						icon_twitter.png
					''','''
						EmfParsley on Twitter
					''','''
						https://twitter.com/intent/user?screen_name=EmfParsley
					''','''
						<p>If you are on twitter and want to get notified about all things EmfParsley, you should consider following <strong>@EmfParsley</strong>.</p>
					''')»
				«entry('''
						icon_prof_support.png
					''','''
						Professional Support
					''','''
						http://EmfParsley.rcp-vision.com
					''','''
						<p>Need training, problem solving, or just a prototype for your modeled UI? 
						<br><strong>RCP Vision</strong> offers all kinds of professional consulting around EMF Parsley.</p>
					''')»
			</div>
			<br/><br/>
		</div>
	'''
	
	def entry(String img, String title, String link, String description) '''
		<div class="row">
			<div class="span1">&nbsp;</div>
			<div class="span9 team">
		    <a href="«link.trim»" class="anchor-in-div"></a>
		    <div class="row">
			  <div class="span1 ">
			    <img src="images/«img.trim»" alt="image" class="image_left">
			  </div>
			  <div class="span2 ">
			    <h3>«title.trim»</h3>
			 	</div>
				<div class="span6 ">
					«description»
				</div>
				</div>
			</div>
		  <div class="span1">&nbsp;</div>
		</div>
	'''
	
}