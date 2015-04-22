package org.eclipse.emf.parsley.dsl.tests

import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGeneratorUtil
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
class EmfParsleyProjectFilesGeneratorUtilTests {
	
	new() {
		// to avoid missed code coverage for the protected constructor
		new EmfParsleyProjectFilesGeneratorUtil() {
			
		}
	}

	@Test
	def void testClassNameFromProject() {
		"Name".assertEquals(
			EmfParsleyProjectFilesGeneratorUtil.buildClassNameFromProject("my.project.name")
		)
	}

	@Test
	def void testStripPathFromProject() {
		"name".assertEquals(
			EmfParsleyProjectFilesGeneratorUtil.stripPackageFromProject("my.project.name")
		)
		"name".assertEquals(
			EmfParsleyProjectFilesGeneratorUtil.stripPackageFromProject("name")
		)
	}

	@Test
	def void testGetPackageFromProject() {
		"my.project.name".assertEquals(
			EmfParsleyProjectFilesGeneratorUtil.getPackageFromProject("my.project.name")
		)
		"name".assertEquals(
			EmfParsleyProjectFilesGeneratorUtil.getPackageFromProject("name")
		)
	}

	@Test
	def void testFQNFromProject() {
		"my.project.name.Name".assertEquals(
			EmfParsleyProjectFilesGeneratorUtil.buildFQNFromProject("my.project.name")
		)
		"name.Name".assertEquals(
			EmfParsleyProjectFilesGeneratorUtil.buildFQNFromProject("name")
		)
	}
}