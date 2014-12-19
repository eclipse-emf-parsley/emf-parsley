package org.eclipse.emf.parsley.doc.websitegen.generator

import com.google.common.base.Charsets
import java.io.File
import java.lang.Iterable
import org.eclipse.xtext.xbase.lib.Pair
import org.eclipse.xtext.xdoc.XdocStandaloneSetup

import static extension com.google.common.io.CharStreams.*
import static extension com.google.common.io.Files.*

abstract class AbstractWebsite implements Resource {
	
	protected new() {
		val injector = standaloneSetup.createInjectorAndDoEMFRegistration
		injector.injectMembers(this)
	}
	
	def XdocStandaloneSetup getStandaloneSetup() {
		new XdocStandaloneSetup()
	}
	
	override generateTo(File targetDir) {
		val file = new File(targetDir, path)
		if (file.exists) {
			print("overwriting ")
		}
		website.write(file.newWriterSupplier(Charsets::UTF_8))
		println("generated '"+file+"'")
	}
	
	/*
	 * the path relative the website root
	 */
	def String path()
	
	def CharSequence contents()
	
	def website() '''
		<!DOCTYPE html>
		<html lang="en">
		<head>
			<meta charset="utf-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<meta name="description" content="«websiteDescription»">
			<meta name="author" content="Francesco Guidieri, Paolo Bachini">
			<link rel="icon" href="/favicon.ico">

			<title>«websiteTitle»</title>
			<link rel="SHORTCUT ICON" href="img/logo_nw.gif" /> 
			«stylesheets»
			«javaScriptDocumentStart»
		</head>
		<body>
			«navBar»
		«contents»
			«footer»
			«javaScriptAtTheEnd»
		</body>
		</html>
	'''
	
	def websiteDescription() { 
		'The website of Eclipse EMF Parsley, an open-source framework for development User Interfaces upon EMF models'
	}

	def websiteTitle() {
		'EMF Parsley - Play with your UI and EMF!'
	}
	
	def javaScriptDocumentStart() '''
		<!--<script src="js/twitter.js" type="text/javascript"></script>-->
		<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
		<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
		<script src="assets/js/ie-emulation-modes-warning.js"></script>

		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
		
		<!-- Run Prettify -->
		<script src="prettify/run_prettify.js"></script>
		
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	'''
	def analyticsAccount() { 'UA-2429174-3' }
	
	def CharSequence jsOnLoad() '''
		«IF isPrettyPrint»
			prettyPrint();
		«ENDIF»
		$('a[data-rel]').each(function() {
			$(this).attr('rel', $(this).data('rel'));
		});

		$("a[rel^='prettyPhoto']").prettyPhoto({
			animation_speed: 'fast',
			slideshow: 5000,
			autoplay_slideshow: false,
			opacity: 0.80,
			show_title: true,
			theme: 'ligh_square',
			overlay_gallery: false,
			social_tools: false
		});
		«IF isOutline»
			$('#nav-outline > li > a').live('click', function() {        
				$(this).parent().find('ul').slideToggle();      
			});
		«ENDIF»
		«IF isPopover()»
			$('.has-popover').popover();
		«ENDIF»
		var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
		po.src = 'https://apis.google.com/js/plusone.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
	'''

	def protected boolean isPrettyPrint() { false }
	def protected boolean isOutline() { true }
	def protected boolean isPopover() { true }
	
	def Iterable<Pair<String,String>> topLevelMenu() {
		newArrayList(
			'index.html' -> 'Home',
			'download.html' -> 'Download',
			'documentation.html' -> 'Documentation',
			'sources.html' -> 'Sources',
			'support.html' -> 'Support'
		)
	}
	
	def Iterable<Pair<String,String>> topRightLevelMenu() {
		newArrayList(
			'support.html' -> 'Contact Us',
			'http://www.eclipse.org' -> 'Eclipse.org',
			'https://twitter.com/EmfParsley' -> '@EmfParsley'
		)
	}

	def navBar() '''
	<!-- ====== NAVBAR ====== -->
	<body style="overflow-y: scroll; padding-top:185px;">
		<div class="navbar-fixed-top" style="background:url(img/bg-100x100.jpg)">
			<div class="container" style="width:1150px;">
				<div class="navbar-header">
					<a href="Index.htm"><img class="featurette-image img-responsive" alt="" src="img/logo.gif"/></a>
				</div>
			</div>
			<nav class="navbar navbar-default" role="navigation" style="background-color:transparent; border:0 none; margin:-31px 0px 3px 0px;min-height: 36px;">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" style="margin-bottom:2px;">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						 </button>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav pull-right mioli">
							«FOR it : topRightLevelMenu»
								«IF key.contains("twitter")»
								<li style="border-right: 0 none;"><a target="_blank" href="«key»" id="twitterli"><img style="width:25px;float:left;margin-top: 5px; margin-right:1px;" alt="" src="img/twitter.png"/>«value»</a></li>
								«ELSE»
								<li><a href="«key»">«value»</a></li>
								«ENDIF»
							«ENDFOR»
						</ul>
					</div><!-- /.navbar-collapse -->
				</div>
			</nav>
			<div style="background:url(img/bg1-100x100.jpg);">
				<nav class="navbar navbar-default miomenubar" role="navigation" style="border-radius:0px; background: url('img/Home/menu.jpg') no-repeat; border: 0 none; -webkit-box-shadow: 0px 3px 8px 0px rgba(171,209,173,1);-moz-box-shadow: 0px 3px 8px 0px rgba(171,209,173,1);box-shadow: 0px 3px 8px 0px rgba(171,209,173,1);margin-bottom:0;">
					<div class="container">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
							<ul class="nav navbar-nav pull-left miomenu">
								«FOR it : topLevelMenu»
								<li><a href="«key»">«value»</a></li>
								«ENDFOR»
							</ul>
						</div><!-- /.navbar-collapse -->
					</div>
				</nav>
			</div>
		</div>		
		<!-- NAVBAR End -->
	'''
	
	
	def plusoneURL() { 'http://www.eclipse.org/emf-parsley' }


	def footer()'''
	<!-- FOOTER -->
		<footer style="z-index: 1001;position:relative;background-color:#35414C;-webkit-box-shadow: 0px -3px 8px 0px rgba(171,209,173,1);-moz-box-shadow: 0px -3px 8px 0px rgba(171,209,173,1);box-shadow: 0px -3px 8px 0px rgba(30,51,72,1);margin-top:1%;">
			<img width="100%" alt="" src="img/footer.jpg" />
			<nav class="navbar navbar-default" role="navigation" style="background-color:transparent; border:0 none; margin:-97px 0px 31px 0px;min-height: 36px;">
				<div class="container" style="width:37.6%;">
				<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-3">
						<ul class="nav navbar-nav pull-right miolifooter">
							<li><a href="">About</a></li>
							<li><a href="">Contact Us</a></li>
							<li style="border-right: 0 none;"><a target="_blank" href="https://twitter.com/EmfParsley" id="twitterfooter"><img style="width:25px;float:left;margin-top: -2px; margin-right:1px;" alt="" src="img/twitter.png"/>@EmfParsley</a></li>
						</ul>
					</div><!-- /.navbar-collapse -->
				</div>
			</nav>
			<nav class="navbar navbar-default" role="navigation" style="background-color:transparent; border:0 none; margin:-18px 0px 0px 0px;min-height: 36px;">
				<div class="container" style="width:57%;">
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-4">
						<ul class="nav navbar-nav miolifooter">
							«FOR it : topLevelMenu»
								<li«IF equals(topLevelMenu.last)» style="border-right: 0 none;"«ENDIF»><a href="«key»">«value»</a></li>
							«ENDFOR»
						</ul>
					</div><!-- /.navbar-collapse -->
				</div>
			</nav>
			<p class="terms" style="margin:0px;padding:21px 0px 6px 20px;"><a target="_blank" href="http://www.eclipse.org/legal/privacy.php">Privacy Policy</a>&nbsp;&nbsp;&middot;&nbsp;&nbsp;<a target="_blank" href="http://www.eclipse.org/legal/termsofuse.php">Terms of Use</a>&nbsp;&nbsp;&middot;&nbsp;&nbsp;<a target="_blank" href="http://www.eclipse.org/legal/copyright.php">Copyright Agent</a>&nbsp;&nbsp;&middot;&nbsp;&nbsp;<a target="_blank" href="http://www.eclipse.org/legal/">Legal</a><a class="pull-right" style="z-index: 1001;position:relative;margin:-38px 15px 0px 0px;" href="#top" id="topbutton"><img alt="Back to top" src="img/arrow_up.png"/></a></p>
		</footer>
	'''

	

	def javaScriptAtTheEnd() '''
		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
		<script src="js/vendor/jquery.min.js"></script>
		<script src="js/vendor/jquery.scrollstop.js"></script>
		<script src="js/vendor/bootstrap.min.js"></script>
		
		<!-- Open lightbox-content image -->
		<script type="text/javascript">
		$('.featurette-image').click(function(){
		$('.lightbox-content').empty();  	
		$($(this).parents('div').html()).appendTo('.lightbox-content');
		$('#myModal').modal({show:true});});
		</script>
		
		<script type="text/javascript">
		$('#twitterli').mouseover(function() {
			$('a#twitterli > img').attr('src','img/twitter_h1.png');
		});
		$('#twitterli').mouseout(function() {
			$('a#twitterli > img').attr('src','img/twitter.png');
		});
		
		$('#twitterfooter').mouseover(function() {
			$('a#twitterfooter > img').attr('src','img/twitter_h2.png');
		});
		$('#twitterfooter').mouseout(function() {
			$('a#twitterfooter > img').attr('src','img/twitter.png');
		});
		
		$('#topbutton').mouseover(function() {
			$('a#topbutton > img').attr('src','img/arrow_up_h.png');
		});
		$('#topbutton').mouseout(function() {
			$('a#topbutton > img').attr('src','img/arrow_up.png');
		});
		
		//gestione scroll verticale
		$(function() {
		  $('a[href*=#]').click(function() {
			if(this.hash=="#top"){
				$('html,body').animate({scrollTop:0}, 800);
				return false;
			} else {
				var arrivo = $(this.hash);
				if (arrivo.length) {
					arrivo=arrivo.offset().top;
					$('html,body').animate({scrollTop:arrivo-205}, 500);
					return false;
				}
			}
		  });
		});
		
		$(window).on("scrollstop", function() {
			var scrollposition = $('html,body').scrollTop()+210;
			if(/chrome/.test(navigator.userAgent.toLowerCase()))
				scrollposition = $("body").scrollTop()+210;
			var mieipar = $("[id^='par']");
			mieipar.each(function(idx) {
				if(idx==mieipar.length-1 && scrollposition>=$(this).offset().top){
					var parid = $(this).attr('id').match(/\d+/);
					$('.activemenu').removeClass("activemenu");
					$("a[href='#par"+parid+"']").parent().addClass("activemenu");
				} else if(scrollposition>=$(this).offset().top && scrollposition<mieipar.eq(idx + 1).offset().top){
					var parid = $(this).attr('id').match(/\d+/);
					$('.activemenu').removeClass("activemenu");
					$("a[href='#par"+parid+"']").parent().addClass("activemenu");
				}
			});
		})
		
		$(document).ready(function() {
			//Setto link attivo nel menu
			var mieili = $('.miomenu li');
			mieili.each(function(idx) {
				var indirizzo = $(this).children().attr('href');
				if(indirizzo.length>0 && window.location.href.indexOf(indirizzo)>0){
					 $(this).addClass('mioactive');
				}
			});
			//Setto link attivo nel footer
			mieili = $('.miolifooter li');
			mieili.each(function(idx) {
				var indirizzo = $(this).children().attr('href');
				if(indirizzo.length>0 && window.location.href.indexOf(indirizzo)>0){
					 $(this).children().addClass('mioactivefooter');
				}
			});
			
			//Setto gli indici per l'effertto hover del dropdown-menu
			var mieiidx = $(".dropdown-menu a[href='#par']");
			var mieipar = $("[id^='par']");
			mieiidx.each(function(idx) {
				$(this).attr('href','#par'+idx);
				if(idx<mieipar.length)
					mieipar.eq(idx).attr('id','par'+idx);
			});
			
			//Setto le references #addref
			var mieiaddref = $("a[href='#addref']");
			mieiaddref.each(function(idx) {
				var ref = $(".dropdown-menu a:contains("+$(this).attr('rel')+")").attr('href');
				if(ref.length>0)
					$(this).attr('href',ref);
			});
		});
		
		//Levo il blur da tutti i link
		$("a").focus(
			function () {
				if ($(this).blur) $(this).blur();
			}
		);
		
		$('#questiondiv').mouseover(function() {
			$('#questiondiv').css('background-color','#D7EFDA');
		});
		$('#questiondiv').mouseout(function() {
			$('#questiondiv').css('background-color','#e8f9ea');
		});
		
		$('#bugdiv').mouseover(function() {
			$('#bugdiv').css('background-color','#F0F2C6');
		});
		$('#bugdiv').mouseout(function() {
			$('#bugdiv').css('background-color','rgb(246, 247, 227)');
		});
		
		$('#suppdiv').mouseover(function() {
			$('#suppdiv').css('background-color','#E8E8E8');
		});
		$('#suppdiv').mouseout(function() {
			$('#suppdiv').css('background-color','rgb(242, 242, 242)');
		});
		
		$('#twdiv').mouseover(function() {
			$('#twdiv').css('background-color','#CFE5F7');
		});
		$('#twdiv').mouseout(function() {
			$('#twdiv').css('background-color','#e8f3fc');
		});
	</script>
	'''

	
	
	
	def stylesheets() '''
		<!--  styles -->
		<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		
		<!-- Bootstrap core CSS -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/main.css" rel="stylesheet">
		<!-- Animate CSS -->
		<link href="css/animate.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
		<link href="css/iebugs.css" rel="stylesheet" type='text/css'>
		<![endif]-->
		
		<!-- Custom styles for this template -->
		<link href="carousel.css" rel="stylesheet">
		
		<!-- Bootstrap Lightbox -->
		<link href="bootstrap-lightbox/bootstrap-lightbox.min.css" rel="stylesheet">
	'''

	def headline(String title) '''
		<div id="header_wrapper" class="container" >
		</div>
	'''
}