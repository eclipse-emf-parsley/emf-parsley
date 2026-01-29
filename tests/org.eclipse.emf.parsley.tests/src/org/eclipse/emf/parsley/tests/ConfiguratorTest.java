package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.junit.Rule;
import org.junit.Test;

public class ConfiguratorTest extends AbstractEmfParsleyTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Test
	public void testDefaultResourceURI() {
		assertNull(getConfigurator().createResourceURI(this));
	}

	@Test
	public void testCustomResourceURI() {
		Configurator customConfigurator = new Configurator() {
			public URI resourceURI(ConfiguratorTest requestor) {
				return URI.createFileURI("file:/atest");
			}
		};
		assertNotNull(customConfigurator.createResourceURI(this));
	}

	@Test
	public void testDefaultGetEClass() {
		assertNull(getConfigurator().getEClass(this));
	}

	@Test
	public void testCustomGetEClass() {
		Configurator customConfigurator = new Configurator() {
			public org.eclipse.emf.ecore.EClass eClass(ConfiguratorTest requestor) {
				return fixtures.getTestPackage().getABaseClass();
			}
		};
		assertEquals(fixtures.getTestPackage().getABaseClass(), customConfigurator.getEClass(this));
	}

	private Configurator getConfigurator() {
		Configurator configurator = new Configurator();
		getOrCreateInjector().injectMembers(configurator);
		return configurator;
	}

}
