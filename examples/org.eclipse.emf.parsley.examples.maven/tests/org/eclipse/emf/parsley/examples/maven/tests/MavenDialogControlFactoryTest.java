package org.eclipse.emf.parsley.examples.maven.tests;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.examples.maven.binding.MavenDialogControlFactoryGen;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.swt.widgets.Control;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class MavenDialogControlFactoryTest extends AbstractEmfParsleyControlBasedTest {

	@Inject
	private DialogControlFactory dialogControlFactory;

	@Inject
	private AdapterFactoryEditingDomain editingDomain;

	@Before
	public void setupLabelProvider() {
		createInjector(new EmfParsleyJavaGuiceModule() {
			@Override
			public Class<? extends DialogControlFactory> bindDialogControlFactory() {
				return MavenDialogControlFactoryGen.class;
			}
		}).injectMembers(this);
	}

	@Test
	public void testDialogControlFactoryForEClassName() {
		EClass eclass = EcorePackage.eINSTANCE.getEClass();
		dialogControlFactory.init(editingDomain, eclass, getShell());
		assertText(dialogControlFactory.create(EcorePackage.Literals.ENAMED_ELEMENT__NAME), "EClass");
	}

	@Test
	public void testDialogControlFactoryForEClassInterface() {
		EClass eclass = EcorePackage.eINSTANCE.getEClass();
		dialogControlFactory.init(editingDomain, eclass, getShell());
		final Control checkbox = dialogControlFactory.create(EcorePackage.Literals.ECLASS__INTERFACE);
		assertCheckbox(checkbox, false);
		eclass.setInterface(true);
		assertCheckbox(checkbox, true);
	}
}
