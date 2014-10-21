package org.eclipse.emf.parsley.tests

import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.parsley.config.Configurator
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.junit.Test

import static extension org.junit.Assert.*

class ConfiguratorTest extends EmfParsleyAbstractTest {

	@Test
	def void testDefaultResourceURI() {
		configurator.createResourceURI(this).assertNull
	}

	@Test
	def void testCustomResourceURI() {
		new Configurator() {
			def resourceURI(ConfiguratorTest requestor) {
				URI.createFileURI("file:/atest")
			}
		}
		.createResourceURI(this).assertNotNull
	}

	@Test
	def void testDefaultGetEClass() {
		configurator.getEClass(this).assertNull
	}

	@Test
	def void testCustomGetEClass() {
		new Configurator() {
			def eClass(ConfiguratorTest requestor) {
				testPackage.ABaseClass
			}
		}
		.getEClass(this).assertEquals(testPackage.ABaseClass)
	}

	@Test
	def void testDefaultGetEStructuralFeature() {
		configurator.getEStructuralFeature(this).assertNull
	}

	@Test
	def void testCustomGetEStructuralFeature() {
		new Configurator() {
			def eStructuralFeature(ConfiguratorTest requestor) {
				testPackage.baseClass_BaseClassFeature
			}
		}
		.getEStructuralFeature(this).assertEquals(testPackage.baseClass_BaseClassFeature)
	}

	@Test
	def void testDefaultGetContents() {
		val resource = createResource
		configurator.getContents(this, resource).assertNull
	}

	@Test
	def void testCustomGetContents() {
		val resource = createResource()
		(new Configurator() {
			def contents(ConfiguratorTest requestor, Resource resource) {
				testContainer.classesForControls
			}
		}
		.getContents(this, resource) as List<ClassForControls>).assertNotNull
	}

	def private getConfigurator() {
		new Configurator().injectMembers
	}

}