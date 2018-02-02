package org.eclipse.emf.parsley.examples.maven.tests;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.examples.maven.binding.MavenDialogControlFactory;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.swt.widgets.Control;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class MavenDialogControlFactoryTest extends AbstractEmfParsleyControlBasedTest {

	@Inject
	private AdapterFactoryEditingDomain editingDomain;

	@Inject
	private CompositeFactory compositeFactory;

	private DialogControlFactory dialogControlFactory;

	private EClass eclass;

	@Before
	public void setupDialogControlFactory() {
		createInjector(new EmfParsleyJavaGuiceModule() {
			@Override
			public Class<? extends DialogControlFactory> bindDialogControlFactory() {
				return MavenDialogControlFactory.class;
			}
		}).injectMembers(this);
		eclass = EcorePackage.eINSTANCE.getEClass();
		dialogControlFactory = compositeFactory.createDialogControlFactory(getShell(), eclass, editingDomain);
	}

	@Test
	public void testDialogControlFactoryForEClassName() {
		assertText(dialogControlFactory.create(EcorePackage.Literals.ENAMED_ELEMENT__NAME), "EClass");
	}

	@Test
	public void testDialogControlFactoryForEClassInterface() {
		final Control checkbox = dialogControlFactory.create(EcorePackage.Literals.ECLASS__INTERFACE);
		assertCheckbox(checkbox, false);
		eclass.setInterface(true);
		assertCheckbox(checkbox, true);
	}
}
