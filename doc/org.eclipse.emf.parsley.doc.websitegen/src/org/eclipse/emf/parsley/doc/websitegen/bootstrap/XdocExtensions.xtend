package org.eclipse.emf.parsley.doc.websitegen.bootstrap

import org.eclipse.xtext.xdoc.xdoc.AbstractSection
import org.eclipse.xtext.xdoc.xdoc.Chapter
import org.eclipse.xtext.xdoc.xdoc.ChapterRef
import org.eclipse.xtext.xdoc.xdoc.Document
import org.eclipse.xtext.xdoc.xdoc.Section
import org.eclipse.xtext.xdoc.xdoc.Section2
import org.eclipse.xtext.xdoc.xdoc.Section2Ref
import org.eclipse.xtext.xdoc.xdoc.Section3
import org.eclipse.xtext.xdoc.xdoc.Section4
import org.eclipse.xtext.xdoc.xdoc.SectionRef
import org.eclipse.xtext.xdoc.xdoc.Part

class XdocExtensions {
	
	def getAllChapters(Document document) {
		(document.chapters + document.parts.map[chapters].flatten).map[resolve as Chapter]
	}
	
	def Iterable<? extends AbstractSection> getSections(AbstractSection section) {
		switch it : section.resolve {
			Document : it.allChapters
			Part 	: it.chapters.map[resolve]
			Chapter : it.subSections.map[resolve]
			Section : it.subSections.map[resolve]
			Section2 : it.subSections.map[resolve]
			Section3 : it.subSections.map[resolve]
			Section4 : emptyList
		}
	}
	
	def AbstractSection resolve(AbstractSection section) {
		switch (section) {
			ChapterRef : section.chapter
			SectionRef : section.section
			Section2Ref : section.section2
			default : section
		}
	}
	
	def id(AbstractSection section) {
		section.name
	}
	
}