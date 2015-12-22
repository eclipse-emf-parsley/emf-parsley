/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests.util;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.compiler.CompilationTestHelper;
import org.eclipse.xtext.xbase.compiler.OnTheFlyJavaCompiler;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import com.google.common.base.Joiner;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * A custom helper since we generate both Java and non Java files
 * (for instance, xml files); the default implementation of compile method
 * assumes that all files generated are Java files instead.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomCompilationTestHelper extends CompilationTestHelper {
	private final static Logger log = Logger.getLogger(CustomCompilationTestHelper.class);
	
	@Inject
	private OnTheFlyJavaCompiler javaCompiler;
	
	@Inject private Provider<InMemoryFileSystemAccess> fileSystemAccessProvider;

	@Override
	public void compile(
			final ResourceSet resourceSet,
			IAcceptor<org.eclipse.xtext.xbase.compiler.CompilationTestHelper.Result> acceptor) {
		try {
			boolean hasErrors = false;
			List<Issue> allErrors = newArrayList();
			List<Resource> resourcesToCheck = newArrayList(resourceSet.getResources());
			for (Resource resource : resourcesToCheck) {
				if (resource instanceof XtextResource) {
					XtextResource xtextResource = (XtextResource) resource;
					if (!xtextResource.isLoaded()) {
						xtextResource.load(resourceSet.getLoadOptions());
					}
					List<Issue> issues = xtextResource.getResourceServiceProvider().getResourceValidator().validate(xtextResource, CheckMode.ALL, CancelIndicator.NullImpl);
					for (Issue issue : issues) {
						if (issue.getSeverity() == Severity.ERROR) {
							hasErrors = true;
							log.error(issue);
							allErrors.add(issue);
						} else {
							log.info(issue);
						}
					}
				}
			}
			if (hasErrors) {
				throw new IllegalStateException("One or more resources contained errors : "+Joiner.on(',').join(allErrors));
			}
			
			final InMemoryFileSystemAccess access = fileSystemAccessProvider.get();
			for (Resource resource : resourcesToCheck) {
				if (resource instanceof XtextResource) {
					XtextResource xtextResource = (XtextResource) resource;
					IGenerator generator = xtextResource.getResourceServiceProvider().get(IGenerator.class);
					if (generator != null)
						generator.doGenerate(xtextResource, access);
				}
			}
			acceptor.accept(new Result() {
				
				private ClassLoader classLoader;
				private Map<String,Class<?>> compiledClasses;
				private Map<String,String> generatedCode;
				
				public Map<String,Class<?>> getCompiledClasses() {
					if (compiledClasses == null) {
						compile();
					}
					return compiledClasses;
				}
				
				private void compile() {
					try {
						org.eclipse.xtext.util.Pair<ClassLoader, Map<String, Class<?>>> compilationResult = javaCompiler.internalCompileToClasses(getGeneratedCode());
						this.classLoader = compilationResult.getFirst();
						this.compiledClasses = compilationResult.getSecond();
					} catch (IllegalArgumentException e) {
						throw new AssertionError(e);
					}
				}
				
				public ClassLoader getClassLoader() {
					if (classLoader == null) {
						compile();
					}
					return classLoader;
				}
				
				public Map<String,String> getGeneratedCode() {
					if (generatedCode == null) {
						generatedCode = newHashMap();
						for (final Entry<String, CharSequence> e : access.getTextFiles().entrySet()) {
							if (e.getKey().endsWith(".java")) {
								String name = e.getKey().substring("DEFAULT_OUTPUT".length(), e.getKey().length() - ".java".length());
								generatedCode.put(name.replace('/', '.'), e.getValue().toString());
							}
						}
					}
					return generatedCode;
				}

				public String getGeneratedCode(String typeName) {
					return getGeneratedCode().get(typeName);
				}
				
				public String getSingleGeneratedCode() {
					if (access.getTextFiles().size() == 1)
						return access.getTextFiles().values().iterator().next().toString();
					String separator = System.getProperty("line.separator");
					if (separator == null)
						separator = "\n";
					List<Entry<String,CharSequence>> files = newArrayList(access.getTextFiles().entrySet());
					Collections.sort(files, new Comparator<Entry<String,CharSequence>>() {
						public int compare(Entry<String, CharSequence> o1,
								Entry<String, CharSequence> o2) {
							return o1.getKey().compareTo(o2.getKey());
						}
					});
					StringBuilder result = new StringBuilder("MULTIPLE FILES WERE GENERATED"+separator+separator);
					int i = 1;
					for (Entry<String,CharSequence> entry: files) {
						result.append("File "+i+" : "+entry.getKey().replace("DEFAULT_OUTPUT", "")+separator+separator);
						result.append(entry.getValue()).append(separator);
						i++;
					}
					return result.toString();
				}

				public ResourceSet getResourceSet() {
					return resourceSet;
				}

				public Class<?> getCompiledClass() {
					return IterableExtensions.head(getCompiledClasses().values());
				}
				
				public Class<?> getCompiledClass(String className) {
					return getCompiledClasses().get(className);
				}

				public Map<String, CharSequence> getAllGeneratedResources() {
					return access.getTextFiles();
				}
				
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
