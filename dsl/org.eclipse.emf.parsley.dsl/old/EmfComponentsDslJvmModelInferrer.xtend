package org.eclipse.emf.parsley.dsl.jvmmodel

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.emf.parsley.EmfComponentsGenericModule
import org.eclipse.emf.parsley.EmfComponentsExecutableExtensionFactory
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.eclipse.emf.parsley.ui.EmfComponentsAbstractActivator
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.util.TypeReferences
import org.osgi.framework.BundleContext
import org.osgi.framework.Bundle
import com.google.inject.Injector

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class EmfComponentsDslJvmModelInferrer extends AbstractModelInferrer {

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject extension JvmTypesBuilder
	
	@Inject extension IQualifiedNameProvider
	
	@Inject extension TypeReferences

	/**
	 * The dispatch method {@code infer} is called for each instance of the
	 * given element's type that is contained in a resource.
	 * 
	 * @param element
	 *            the model to create one or more
	 *            {@link org.eclipse.xtext.common.types.JvmDeclaredType declared
	 *            types} from.
	 * @param acceptor
	 *            each created
	 *            {@link org.eclipse.xtext.common.types.JvmDeclaredType type}
	 *            without a container should be passed to the acceptor in order
	 *            get attached to the current resource. The acceptor's
	 *            {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
	 *            accept(..)} method takes the constructed empty type for the
	 *            pre-indexing phase. This one is further initialized in the
	 *            indexing phase using the closure you pass to the returned
	 *            {@link org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing#initializeLater(org.eclipse.xtext.xbase.lib.Procedures.Procedure1)
	 *            initializeLater(..)}.
	 * @param isPreIndexingPhase
	 *            whether the method is called in a pre-indexing phase, i.e.
	 *            when the global index is not yet fully updated. You must not
	 *            rely on linking using the index if isPreIndexingPhase is
	 *            <code>true</code>.
	 */
   	def dispatch void infer(Module element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		if (element.name == null)
   			return
   		
		val moduleClass = element.toClass(element.moduleQN)
		val execExtFactoryClass = element.toClass(element.executableExtensionFactoryQN)
		val activatorClass = element.toClass(element.activatorQN)
		
		acceptor.accept(moduleClass).initializeLater [
			documentation = element.documentation
			superTypes += element.newTypeRef(typeof(EmfComponentsGenericModule))
			
			members += element.toConstructor() [
				parameters += element.toParameter("plugin", element.newTypeRef(typeof(AbstractUIPlugin)))
				body = [it.append("super(plugin);")]
			]
		]

		acceptor.accept(execExtFactoryClass).initializeLater [
			documentation = element.documentation
			superTypes += element.newTypeRef(typeof(EmfComponentsExecutableExtensionFactory))
			
			members += element.toMethod("getBundle", element.newTypeRef(typeof(Bundle))) [
				annotations += element.toAnnotation(typeof(Override))
				visibility = JvmVisibility::PROTECTED
				body = [ 
					append("return " + element.activatorQN + ".getDefault().getBundle();")
				]
			]
			
			members += element.toMethod("getModule", element.newTypeRef(typeof(EmfComponentsGenericModule))) [
				annotations += element.toAnnotation(typeof(Override))
				visibility = JvmVisibility::PROTECTED
				body = [ 
					append("return " + element.activatorQN + ".getDefault().createModule();")
				]
			]
			
			members += element.toMethod("getInjector", element.newTypeRef(typeof(Injector))) [
				annotations += element.toAnnotation(typeof(Override))
				visibility = JvmVisibility::PROTECTED
				body = [ 
					append("return " + element.activatorQN + ".getDefault().getInjector();")
				]
			]
		]

		acceptor.accept(activatorClass).initializeLater [
			documentation = element.documentation
			superTypes += element.newTypeRef(typeof(EmfComponentsAbstractActivator))
			
			members += toField("PLUGIN_ID", element.newTypeRef(typeof(String))) [
				visibility = JvmVisibility::PUBLIC
				^static = true
				final = true
				setInitializer [ append('''"«element.fullyQualifiedName»"''') ]
			]
			
			members += toField("plugin", activatorClass.createTypeRef) [
				visibility = JvmVisibility::PRIVATE
				^static = true
			]
			
			members += toMethod("start", Void::TYPE.getTypeForName(element)) [
				parameters += element.toParameter(
					"context", element.newTypeRef(typeof(BundleContext)))
				exceptions += element.newTypeRef(typeof(Exception))
				body = [
					append('''
					super.start(context);
					plugin = this;''')
				]
			]
			
			members += toMethod("stop", Void::TYPE.getTypeForName(element)) [
				parameters += element.toParameter(
					"context", element.newTypeRef(typeof(BundleContext)))
				exceptions += element.newTypeRef(typeof(Exception))
				body = [
					append('''
					plugin = null;
					super.stop(context);''')
				]
			]
			
			members += toMethod("getDefault", activatorClass.createTypeRef) [
				^static = true
				body = [ append("return plugin;") ]
			]
			
			members += element.toMethod("createModule", element.newTypeRef(typeof(EmfComponentsGenericModule))) [
				visibility = JvmVisibility::PROTECTED
				body = [
					append("return new " + element.moduleQN + "(getDefault());")
				]
			]
		]
   	}
   	
   	def activatorQN(Module element) {
   		element.fullyQualifiedName + ".Activator"
   	}
   	
   	def moduleQN(Module element) {
   		element.fullyQualifiedName + ".EmfComponentsGuiceModuleGen"
   	}

   	def executableExtensionFactoryQN(Module element) {
   		element.fullyQualifiedName + ".ExecutableExtensionFactory"
   	}
}

