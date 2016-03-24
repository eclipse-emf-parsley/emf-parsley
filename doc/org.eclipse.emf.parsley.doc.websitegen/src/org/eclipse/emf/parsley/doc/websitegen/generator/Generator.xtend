package org.eclipse.emf.parsley.doc.websitegen.generator

import java.io.File
import org.eclipse.xtend.core.XtendStandaloneSetup

class Generator {
	
	def static void main(String[] args) {
		System::setProperty("java.awt.headless", "true")
		XtendStandaloneSetup.doSetup
		val file = new File("website")
		println(file.absolutePath)
		val out = file
		out.generateFiles(
			new Index,
			new Download,
			new Documentation,
			new GettingSources,  
			new Support,
			new UserReviews
		)
		println("Done.")
	}
	
	def static void generateFiles(File targetDir, Resource ... sites) {
		for (site : sites) {
			site.generateTo(targetDir)
		}
	}
	
}