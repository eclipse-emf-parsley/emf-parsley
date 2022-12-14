/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Sven Efftinge - Initial contribution and API
 * Lorenzo Bettini - adapted to work with several Java files and other
 * 	generated files, like xml files
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jdt.core.compiler.CompilationProgress;
import org.eclipse.jdt.internal.compiler.batch.FileSystem;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.eclipse.xtext.util.Files;
import org.eclipse.xtext.xbase.compiler.OnTheFlyJavaCompiler;

import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Lorenzo Bettini - adapted to work with several Java files and
 * with other generated files, like xml files
 * 
 */
public class CustomOnTheFlyJavaCompiler extends OnTheFlyJavaCompiler {
	
	static class DelegateOutStream extends OutputStream {

		private OutputStream delegate;

		@Override
		public void close() throws IOException {
			delegate.close();
		}

		@Override
		public boolean equals(Object obj) {
			return delegate.equals(obj);
		}

		@Override
		public void flush() throws IOException {
			delegate.flush();
		}

		@Override
		public int hashCode() {
			return delegate.hashCode();
		}

		public void setDelegate(OutputStream delegate) {
			this.delegate = delegate;
		}

		@Override
		public String toString() {
			return delegate.toString();
		}

		@Override
		public void write(byte[] b) throws IOException {
			delegate.write(b);
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			delegate.write(b, off, len);
		}

		@Override
		public void write(int b) throws IOException {
			delegate.write(b);
		}

	}

	/**
	 * Reuse the classpath, since it is super expensive to reopen and
	 * scan the zips.
	 * 
	 * @author Sven Efftinge - Initial contribution and API
	 */
	static class PatchedMain extends Main {

		@SuppressWarnings("rawtypes")
		public PatchedMain(PrintWriter outWriter, PrintWriter errWriter,
				boolean systemExitWhenFinished, Map customDefaultOptions,
				CompilationProgress compilationProgress) {
			super(outWriter, errWriter, systemExitWhenFinished,
					customDefaultOptions, compilationProgress);
		}

		@Override
		public FileSystem getLibraryAccess() {
			if (fileSystem == null) {
				fileSystem = new PatchedFileSystem(super.getLibraryAccess());
			}
			return fileSystem;
		}

	}
	
	private static PatchedFileSystem fileSystem;
	
	private DelegateOutStream errorStream = new DelegateOutStream();

	@Inject
	private ClassLoader parentClassLoader;
	
	@Override
	public String getClasspathArgs() {
		final String classpathArgs = super.getClasspathArgs();
		return classpathArgs
				+ (classpathArgs.isEmpty() ? "" : File.pathSeparator
						+ createTempDir().getAbsolutePath()
						+ File.separator);
	}
	
	protected Main getMain() {
		return new PatchedMain(new PrintWriter(new OutputStreamWriter(
				System.out)), new PrintWriter(new OutputStreamWriter(
				errorStream)), false /* systemExit */, null /* options */, null);
	}

	public Class<?> compileToClass(String classname, String code) {
		File tempDir = createTempDir();
		final String classNameAsPath = classname.replace('.',
				File.separatorChar);
		final File srcFile = new File(tempDir, classNameAsPath + ".java");

		createFolderStructure(srcFile.getParentFile());
		final File targetFile = new File(tempDir, classNameAsPath + ".class");
		if (targetFile.exists())
			targetFile.delete();
		try {
			srcFile.createNewFile();
			Files.writeStringIntoFile(srcFile.getCanonicalPath(), code);
			errorStream.setDelegate(new ByteArrayOutputStream());
			StringBuilder sb = new StringBuilder(getComplianceLevelArg());
			sb.append(" ");
			sb.append(getClasspathArgs());
			sb.append(" ");
			sb.append('\"');
			sb.append(srcFile.getCanonicalPath());
			sb.append('\"');
			boolean compile = compile(sb.toString());
			if (!compile)
				throw new IllegalArgumentException("Couldn't compile : "
						+ errorStream.toString() + "\n" + code);
			final URL url = tempDir.toURI().toURL();
			URLClassLoader loader = new URLClassLoader(new URL[] { url },
					parentClassLoader);
			Class<?> class1 = loader.loadClass(classname);
			// loader.close(); in Java 1.7
			return class1;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new WrappedException(e);
		} finally {
			cleanUpTmpFolder(tempDir);
		}
	}
	
	public void compileAll(Map<String, String> toCompile) {
		File tempDir = createTempDir();
		StringBuilder fileNamesToCompile = new StringBuilder();
		
		try {

			for (Entry<String, String> sourceToCompile : toCompile.entrySet()) {
				final String classNameAsPath = sourceToCompile.getKey().replace('.',
						File.separatorChar);
				final File srcFile = new File(tempDir, classNameAsPath + ".java");

				createFolderStructure(srcFile.getParentFile());
				final File targetFile = new File(tempDir, classNameAsPath + ".class");
				if (targetFile.exists())
					targetFile.delete();
				
				srcFile.createNewFile();
				Files.writeStringIntoFile(srcFile.getCanonicalPath(), sourceToCompile.getValue());
				
				fileNamesToCompile.append(" ");
				fileNamesToCompile.append('\"');
				fileNamesToCompile.append(srcFile.getCanonicalPath());
				fileNamesToCompile.append('\"');
			}

			errorStream.setDelegate(new ByteArrayOutputStream());
			StringBuilder sb = new StringBuilder(getComplianceLevelArg());
			sb.append(" ");
			sb.append(getClasspathArgs());
			sb.append(" ");
			sb.append(fileNamesToCompile);
			boolean compile = compile(sb.toString());
			if (!compile)
				throw new IllegalArgumentException("Couldn't compile : "
						+ errorStream.toString() + "\n" + fileNamesToCompile);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new WrappedException(e);
		} finally {
			cleanUpTmpFolder(tempDir);
		}
		
	}
}
