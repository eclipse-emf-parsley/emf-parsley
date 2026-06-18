package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGeneratorUtil;
import org.junit.Test;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class EmfParsleyProjectFilesGeneratorUtilTest {

	@Test
	public void testClassNameFromProject() {
		assertEquals("Name",
			EmfParsleyProjectFilesGeneratorUtil.buildClassNameFromProject("my.project.name")
		);
	}

	@Test
	public void testStripPathFromProject() {
		assertEquals("name",
			EmfParsleyProjectFilesGeneratorUtil.stripPackageFromProject("my.project.name")
		);
		assertEquals("name",
			EmfParsleyProjectFilesGeneratorUtil.stripPackageFromProject("name")
		);
	}

	@Test
	public void testGetPackageFromProject() {
		assertEquals("my.project.name",
			EmfParsleyProjectFilesGeneratorUtil.getPackageFromProject("my.project.name")
		);
		assertEquals("name",
			EmfParsleyProjectFilesGeneratorUtil.getPackageFromProject("name")
		);
	}

	@Test
	public void testFQNFromProject() {
		assertEquals("my.project.name.Name",
			EmfParsleyProjectFilesGeneratorUtil.buildFQNFromProject("my.project.name")
		);
		assertEquals("name.Name",
			EmfParsleyProjectFilesGeneratorUtil.buildFQNFromProject("name")
		);
	}
}
