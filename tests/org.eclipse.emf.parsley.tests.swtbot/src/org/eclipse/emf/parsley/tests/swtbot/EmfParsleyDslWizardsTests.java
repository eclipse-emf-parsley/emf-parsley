/**
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 */
package org.eclipse.emf.parsley.tests.swtbot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyDslWizardsTests extends EmfParsleyDslUiAbstractTests {
	private static final String REFERENCE_PROJECTS = "dsl/org.eclipse.emf.parsley.wizard.tests.projects";
	private static final String PROJECT_PREFIX = "org.eclipse.emf.parsley.wizard.";
	private static final String PROJECT_SUFFIX = ".test.project";
	private final String TREE_FORM_TEMPLATE = "Saveable Tree Form View";

	private final String TREE_TEMPLATE = "Saveable Tree View";

	private final String TREE_WITH_COLUMNS_TEMPLATE = "Saveable Tree With Columns View";

	private final String TABLE_FORM_TEMPLATE = "Saveable Table Form View";

	private final String TABLE_TEMPLATE = "Saveable Table View";

	private final String ONSELECTION_TREE_TEMPLATE = "On selection Tree View";

	private final String ONSELECTION_TREE_FORM_TEMPLATE = "On selection Tree Form View";

	private final String ONSELECTION_TABLE_TEMPLATE = "On selection Table View";

	private final String ONSELECTION_TABLE_FORM_TEMPLATE = "On selection Table Form View";

	private final String ONSELECTION_FORM_TEMPLATE = "On selection Form View";

	@Test
	public void canCreateDslProjectWithWizard() throws Exception {
		createDslProjectWithWizard();
	}

	@Test
	public void canCreateDslProjectWithWizardFromBadProjectName1() throws Exception {
		createDslProjectWithWizardFromBadProjectName1();
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndTreeFormTemplate() throws Exception {
		assertTemplateProject("saveabletreeformview", TREE_FORM_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndTreeTemplate() throws Exception {
		assertTemplateProject("saveabletreeview", TREE_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndTableFormTemplate() throws Exception {
		assertTemplateProject("saveabletableformview", TABLE_FORM_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndTableTemplate() throws Exception {
		assertTemplateProject("saveabletableview", TABLE_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndTreeWithColumnsTemplate() throws Exception {
		assertTemplateProject("saveabletreecolumnsview", TREE_WITH_COLUMNS_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionTreeTemplate() throws Exception {
		assertTemplateProject("onselectiontreeview", ONSELECTION_TREE_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionTreeFormTemplate() throws Exception {
		assertTemplateProject("onselectiontreeformview", ONSELECTION_TREE_FORM_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionTableTemplate() throws Exception {
		assertTemplateProject("onselectiontableview", ONSELECTION_TABLE_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionFormTemplate() throws Exception {
		assertTemplateProject("onselectionformview", ONSELECTION_FORM_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionTableFormTemplate() throws Exception {
		assertTemplateProject("onselectiontableformview", ONSELECTION_TABLE_FORM_TEMPLATE);
	}

	@Test
	public void canCreateDslProjectWithWizardAndRapOption() throws Exception {
		String projectName = PROJECT_PREFIX + "onselection.rap" + PROJECT_SUFFIX;
		createTemplateProject(projectName, ONSELECTION_TREE_FORM_TEMPLATE, true);
		assertNoErrorsInProjectAfterAutoBuild();
		assertProjectMatchesReference(projectName);
	}

	private void assertTemplateProject(String name, String template) throws Exception {
		String projectName = PROJECT_PREFIX + name + PROJECT_SUFFIX;
		createTemplateProject(projectName, template, false);
		assertNoIssuesInProjectAfterAutoBuild();
		assertProjectMatchesReference(projectName);
	}

	private void createTemplateProject(String projectName, String template, boolean rap) {
		SWTBotShell shell = createNewProjectWizard(
				EMF_PARSLEY_CATEGORY, NEW_EMF_PARSLEY_DSL_PROJECT, projectName);
		bot.button("Next >").click();
		bot.table().select(template);
		bot.button("Next >").click();
		if (rap) {
			bot.checkBox("Prepare for Remote Application Platform (RAP)").select();
		}
		bot.button("Finish").click();
		waitForShellToClose(shell);
		assertProjectIsCreated(projectName, shell);
	}

	private void assertProjectMatchesReference(String projectName) throws IOException {
		Path reference = findReferenceProjects().resolve(projectName);
		assertTrue("Missing reference project: " + reference, Files.isDirectory(reference));
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		Path actual = project.getLocation().toFile().toPath();
		Set<Path> expectedFiles = projectFiles(reference);
		assertEquals("Project files differ from " + reference, expectedFiles, projectFiles(actual));
		for (Path file : expectedFiles) {
			String expected = normalizeContents(file, Files.readString(reference.resolve(file), StandardCharsets.UTF_8));
			String generated = normalizeContents(file, Files.readString(actual.resolve(file), StandardCharsets.UTF_8));
			if (!expected.equals(generated)) {
				fail("Different contents: " + file + "\nExpected:\n" + expected
						+ "\nActual:\n" + generated);
			}
		}
	}

	private String normalizeContents(Path file, String text) {
		text = text.replace("\r\n", "\n").replace('\r', '\n');
		if (file.equals(Paths.get(".settings", "org.eclipse.jdt.core.prefs"))) {
			text = text.replaceAll("(?m)^(org\\.eclipse\\.jdt\\.core\\.compiler\\."
					+ "(?:codegen\\.targetPlatform|compliance|source)=)\\d+", "$1<version>");
		} else if (file.equals(Paths.get(".classpath"))
				|| file.equals(Paths.get("META-INF", "MANIFEST.MF"))) {
			text = text.replaceAll("JavaSE-\\d+", "JavaSE-<version>");
		}
		return text;
	}

	private Set<Path> projectFiles(Path project) throws IOException {
		try (Stream<Path> files = Files.walk(project)) {
			return files.filter(Files::isRegularFile)
					.map(project::relativize)
					.filter(path -> !path.startsWith("emfparsley-gen") && !path.startsWith("bin")
							&& !path.startsWith("target"))
					.collect(Collectors.toCollection(TreeSet::new));
		}
	}

	private Path findReferenceProjects() {
		IProject fixtureProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject("org.eclipse.emf.parsley.wizard.tests.projects");
		if (fixtureProject.exists() && fixtureProject.getLocation() != null) {
			return fixtureProject.getLocation().toFile().toPath();
		}
		Path path = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
		while (path != null) {
			Path candidate = path.resolve(REFERENCE_PROJECTS);
			if (Files.isDirectory(candidate)) {
				return candidate;
			}
			path = path.getParent();
		}
		throw new AssertionError("Cannot find " + REFERENCE_PROJECTS + " from " + System.getProperty("user.dir"));
	}
}
