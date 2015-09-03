package org.eclipse.emf.parsley.tests

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.parsley.config.Configurator
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class ConfiguratorTest extends AbstractEmfParsleyTest {
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

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

	def private getConfigurator() {
		new Configurator().injectMembers
	}

}