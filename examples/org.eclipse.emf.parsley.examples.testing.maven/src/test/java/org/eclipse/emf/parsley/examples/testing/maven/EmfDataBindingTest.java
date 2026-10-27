package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.junit.Rule;
import org.junit.Test;

/** Verifies two-way EMF / SWT text binding on a real SWT display realm. */
public class EmfDataBindingTest {

	@Rule
	public DisplayHelperTestRule displayHelper = new DisplayHelperTestRule();

	@SuppressWarnings("unchecked")
	@Test
	public void textAndEmfAttributeStayInSyncInBothDirections() {
		var factory = EcoreFactory.eINSTANCE;
		var ePackage = factory.createEPackage();
		ePackage.setName("people");
		ePackage.setNsPrefix("people");
		ePackage.setNsURI("https://example.org/binding-people");
		ePackage.setEFactoryInstance(factory.createEFactory());

		var personType = factory.createEClass();
		personType.setName("Person");
		ePackage.getEClassifiers().add(personType);
		var name = factory.createEAttribute();
		name.setName("name");
		name.setEType(EcorePackage.Literals.ESTRING);
		personType.getEStructuralFeatures().add(name);

		var person = EcoreUtil.create(personType);
		person.eSet(name, "Ada");
		var text = new Text(displayHelper.getShell(), SWT.BORDER);

		// An SWT DisplayRealm makes widget observations legal on this thread.
		// The context (and its subscriptions) is disposed before the widgets.
		Realm.runWithDefault(DisplayRealm.getRealm(text.getDisplay()), () -> {
			var context = new DataBindingContext();
			try {
				context.bindValue(WidgetProperties.text(SWT.Modify).observe(text),
						EMFProperties.value(name).observe(person));

				assertEquals("Ada", text.getText());

				person.eSet(name, "Grace");
				assertEquals("Grace", text.getText());

				text.setText("Linus");
				assertEquals("Linus", person.eGet(name));
			} finally {
				context.dispose();
			}
		});
	}
}
