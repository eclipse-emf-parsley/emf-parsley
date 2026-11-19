/**
 * Copyright (c) 2026 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.parsley.dsl.jvmmodel;

/** Names shared by the Parsley DSL's generated Java types. */
public final class EmfParsleyDslNaming {

	private EmfParsleyDslNaming() {
	}

	public static String buildClassNameFromProject(String projectName) {
		int dot = projectName.lastIndexOf('.');
		String simpleName = dot < 0 ? projectName : projectName.substring(dot + 1);
		return Character.toUpperCase(simpleName.charAt(0)) + simpleName.substring(1);
	}

	public static String buildFQNFromProject(String projectName) {
		return projectName + "." + buildClassNameFromProject(projectName);
	}
}
