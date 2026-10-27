package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.parsley.junit4.util.TestDefaultRealm;
import org.junit.Test;

/**
 * Demonstrates Core Data Binding observables without SWT or a workbench.
 */
public class DefaultRealmTest {

	@Test
	public void observableChangesAreDeliveredInTheTestRealm() {
		Realm previous = Realm.getDefault();
		var realm = new TestDefaultRealm();
		try {
			assertSame(realm, Realm.getDefault());

			var observable = new WritableValue<>("Before", String.class);
			try {
				var notifications = new ArrayList<String>();
				observable.addValueChangeListener(event -> notifications.add(observable.getValue()));

				observable.setValue("After");

				assertEquals("After", observable.getValue());
				assertEquals(List.of("After"), notifications);
			} finally {
				observable.dispose();
			}
		} finally {
			realm.dispose();
		}
		// The testing realm must not leak into subsequent JUnit tests.
		assertSame(previous, Realm.getDefault());
	}
}
