package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;

/**
 * Exercises a dynamic EMF model using plain JUnit: no SWT or workbench needed.
 */
public class DynamicEmfModelTest {

	private record PersonModel(EClass personType, EAttribute nameAttribute) {
	}

	private static PersonModel createPersonMetamodel() {
		var ecore = EcoreFactory.eINSTANCE;
		var peoplePackage = ecore.createEPackage();
		peoplePackage.setName("people");
		peoplePackage.setNsPrefix("people");
		peoplePackage.setNsURI("https://example.org/people");
		peoplePackage.setEFactoryInstance(ecore.createEFactory());

		var personType = ecore.createEClass();
		personType.setName("Person");
		peoplePackage.getEClassifiers().add(personType);

		var name = ecore.createEAttribute();
		name.setName("name");
		name.setEType(EcorePackage.Literals.ESTRING);
		personType.getEStructuralFeatures().add(name);

		return new PersonModel(personType, name);
	}

	@Test
	public void modelValuesCanBeVerifiedWithoutAUi() {
		var model = createPersonMetamodel();
		var person = EcoreUtil.create(model.personType());

		person.eSet(model.nameAttribute(), "Ada");

		assertSame(model.personType(), person.eClass());
		assertEquals("Ada", person.eGet(model.nameAttribute()));
	}

	@Test
	public void modelChangesEmitEmfNotifications() {
		var model = createPersonMetamodel();
		var person = EcoreUtil.create(model.personType());
		var notifications = new ArrayList<Notification>();
		person.eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(Notification notification) {
				notifications.add(notification);
			}
		});

		person.eSet(model.nameAttribute(), "Grace");

		assertEquals(1, notifications.size());
		var notification = notifications.get(0);
		assertEquals(Notification.SET, notification.getEventType());
		assertSame(person, notification.getNotifier());
		assertSame(model.nameAttribute(), notification.getFeature());
		assertEquals("Grace", notification.getNewValue());
	}
}
