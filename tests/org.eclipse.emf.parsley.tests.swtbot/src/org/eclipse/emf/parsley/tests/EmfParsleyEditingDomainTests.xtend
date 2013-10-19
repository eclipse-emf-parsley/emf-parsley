package org.eclipse.emf.parsley.tests

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.resource.ResourceLoader
import org.eclipse.emf.parsley.tests.factories.GlobalAdapterFactoryEditingDomainModule
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(SWTBotJunit4ClassRunner)
class EmfParsleyEditingDomainTests {

	@Test
	def void testDefaultEditingDomainProvider() {
		val injector = createDefaultEditingDomainProviderInjector
		
		val e1 = injector.getProvider(AdapterFactoryEditingDomain).get
		val e2 = injector.getProvider(AdapterFactoryEditingDomain).get
		
		e1.assertNotSame(e2)
	}

	@Test
	def void testGlobalEditingDomainProvider() {
		val injector = createGlobalEditingDomainProviderInjector
		
		val e1 = injector.getProvider(AdapterFactoryEditingDomain).get
		val e2 = injector.getProvider(AdapterFactoryEditingDomain).get
		
		e1.assertSame(e2)
	}

	@Test
	def void testDifferentResourceWithDefaultEditingDomainProvider() {
		val injector = createDefaultEditingDomainProviderInjector
		
		val res1 = injector.loadResourceWithContents
		val res2 = injector.loadResourceWithContents
		
		res1.assertNotSame(res2)
	}

	@Test
	def void testSameResourceWithGlobalEditingDomainProvider() {
		val injector = createGlobalEditingDomainProviderInjector
		
		val res1 = injector.loadResourceWithContents
		val res2 = injector.loadResourceWithContents
		
		res1.assertSame(res2)
		
		// we act on the same Resource since we used the same
		// EditingDomain
		res1.library.name = "Changed"
		"Changed".assertEquals(res2.library.name)
	}

	def private loadResourceWithContents(Injector injector) {
		val e1 = injector.getProvider(AdapterFactoryEditingDomain).get
		val resource = injector.getInstance(ResourceLoader).
			getResource(e1, URI.createURI("dummy:/My.mail")).resource
		resource.assertNotNull
		resource
	}

	def private library(Resource resource) {
		resource.contents.head as Library
	}

	def private createDefaultEditingDomainProviderInjector() {
		createTestInjector(new EmfParsleyGuiceModule(EmfParsleyTestsActivator.^default))
	}

	def private createGlobalEditingDomainProviderInjector() {
		createTestInjector(new GlobalAdapterFactoryEditingDomainModule(EmfParsleyTestsActivator.^default))
	}

	def private createTestInjector(Module module) {
		Guice.createInjector(module);
	}
}