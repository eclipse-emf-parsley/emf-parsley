/**
 * Copyright (c) 2026 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.waitForBuild;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.parsley.dsl.ui.wizard.ParsleyProjectFiles;
import org.eclipse.emf.parsley.views.OnSelectionFormView;
import org.eclipse.emf.parsley.views.OnSelectionTableFormView;
import org.eclipse.emf.parsley.views.OnSelectionTableView;
import org.eclipse.emf.parsley.views.OnSelectionTreeFormView;
import org.eclipse.emf.parsley.views.OnSelectionTreeView;
import org.eclipse.emf.parsley.views.SaveableTableFormView;
import org.eclipse.emf.parsley.views.SaveableTableView;
import org.eclipse.emf.parsley.views.SaveableTreeFormView;
import org.eclipse.emf.parsley.views.SaveableTreeView;
import org.eclipse.emf.parsley.views.SaveableTreeWithColumnsView;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.DefaultProjectInfo;
import org.junit.Test;

public class EmfParsleyDslProjectTemplateGenerationTest {

	@Test
	public void viewProjectsMatchReferences() throws Exception {
		Object[][] cases = {
			{ "onselectiontreeview", OnSelectionTreeView.class, false },
			{ "onselectionformview", OnSelectionFormView.class, false },
			{ "onselectiontableformview", OnSelectionTableFormView.class, false },
			{ "onselectiontableview", OnSelectionTableView.class, false },
			{ "onselectiontreeformview", OnSelectionTreeFormView.class, false },
			{ "saveabletableformview", SaveableTableFormView.class, false },
			{ "saveabletableview", SaveableTableView.class, false },
			{ "saveabletreeformview", SaveableTreeFormView.class, false },
			{ "saveabletreeview", SaveableTreeView.class, false },
			{ "saveabletreecolumnsview", SaveableTreeWithColumnsView.class, false },
			{ "onselection.rap", OnSelectionTreeFormView.class, true }
		};
		for (Object[] testCase : cases) {
			String name = "org.eclipse.emf.parsley.wizard." + testCase[0] + ".test.project";
			assertProjectMatchesReference(name, (Class<?>) testCase[1], (boolean) testCase[2]);
		}
	}

	private void assertProjectMatchesReference(String name, Class<?> viewType, boolean rap) throws Exception {
		DefaultProjectInfo info = new DefaultProjectInfo();
		info.setProjectName(name);
		AtomicReference<ProjectFactory> result = new AtomicReference<>();
		ParsleyProjectFiles.generate(result::set, info, viewType, rap);
		ProjectFactory factory = result.get();
		assertNotNull(factory);
		factory.setWorkspace(ResourcesPlugin.getWorkspace());
		factory.setWorkbench(PlatformUI.getWorkbench());
		IProject project = factory.createProject(new NullProgressMonitor(), null);
		assertNotNull(project);
		waitForBuild();
		Path expected = findReferenceProjects().resolve(name);
		Path actual = project.getLocation().toFile().toPath();
		assertTrue(Files.isDirectory(expected));
		Set<Path> expectedFiles = projectFiles(expected);
		assertEquals("Project files differ for " + name, expectedFiles, projectFiles(actual));
		for (Path file : expectedFiles) {
			String expectedContents = normalizeContents(file, Files.readString(expected.resolve(file), StandardCharsets.UTF_8));
			String actualContents = normalizeContents(file, Files.readString(actual.resolve(file), StandardCharsets.UTF_8));
			if (!expectedContents.equals(actualContents)) {
				fail("Different contents: " + file + "\nExpected:\n" + expectedContents
					+ "\nActual:\n" + actualContents);
			}
		}
	}

	private Path findReferenceProjects() {
		Path path = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
		while (path != null) {
			Path candidate = path.resolve("dsl/org.eclipse.emf.parsley.wizard.tests.projects");
			if (Files.isDirectory(candidate)) {
				return candidate;
			}
			path = path.getParent();
		}
		throw new AssertionError("Cannot find wizard reference projects");
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
}
