package org.eclipse.emf.parsley.dsl.tests.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.compiler.CompilationTestHelper;

import com.google.inject.Inject;

/**
 * A custom helper since we generate two classes for each input file.
 * 
 * @author Lorenzo Bettini
 * 
 */
@SuppressWarnings("restriction")
public class CustomCompilationTestHelper extends CompilationTestHelper {
	@Inject
	private CustomOnTheFlyJavaCompiler javaCompiler;

	@Inject
	private ParseHelper<EObject> parseHelper;

	@Inject
	private ValidationTestHelper validationTestHelper;

	@Inject
	private IGenerator generator;
	
	/**
	 * A result contains information about various aspects of a compiled piece of code.
	 *   
	 */
	public static interface Result {
		/**
		 * @return the loaded, validated and fully linked source resource
		 */
		Resource getSource();
		
		/**
		 * Compile all Java sources generated for a resource
		 */
		void compileToJava();
		
		/**
		 * @return access to all generated artifacts. The key points to the pathesa dn the values are the generated code.
		 */
		Map<String,CharSequence> getAllGeneratedResources();
	}

	public void compileAll(CharSequence source, IAcceptor<Result> acceptor) {
		try {
			final EObject parsed = parseHelper.parse(source);
			validationTestHelper.assertNoErrors(parsed);
			final InMemoryFileSystemAccess access = new InMemoryFileSystemAccess();
			generator.doGenerate(parsed.eResource(), access);
			
			acceptor.accept(new Result() {

				public Resource getSource() {
					return (Resource) parsed.eResource();
				}

				public void compileToJava() {
					Map<String, String> toCompile = new HashMap<String, String>();
					
					for (final Entry<String, CharSequence> e : access.getTextFiles().entrySet()) {
						if (!e.getKey().contains(".java"))
							continue; // it's not a Java file
						
						String name = e.getKey().substring(
								"DEFAULT_OUTPUT".length(),
								e.getKey().length() - ".java".length());
						name = name.replace('/', '.');
						toCompile.put(name, e.getValue().toString());
					}
					
					javaCompiler.compileAll(toCompile);
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
