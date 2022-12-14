package org.eclipse.emf.parsley.examples.maven.tests;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenLabelProvider;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.jface.viewers.ILabelProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class MavenExampleLabelProviderTest extends AbstractEmfParsleyShellBasedTest {

	@Inject
	private ILabelProvider labelProvider;

	@Before
	public void setupLabelProvider() {
		createInjector(new EmfParsleyJavaGuiceModule() {
			@Override
			public Class<? extends ILabelProvider> bindILabelProvider() {
				return MavenLabelProvider.class;
			}
		}).
		injectMembers(this);
	}

	@Test
	public void testLabelProviderForEClass() {
		EClass eclass = EcorePackage.eINSTANCE.getEClass();
		Assert.assertEquals("org.eclipse.emf.ecore.EClass", labelProvider.getText(eclass));
	}

	@Test
	public void testLabelProviderForEDataType() {
		Assert.assertEquals("EString [java.lang.String]",
				labelProvider.getText(EcorePackage.eINSTANCE.getEString()));
	}
}
