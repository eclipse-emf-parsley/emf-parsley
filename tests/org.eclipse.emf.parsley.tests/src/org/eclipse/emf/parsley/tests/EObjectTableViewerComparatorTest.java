package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.emf.parsley.inject.parameters.EStructuralFeatureParameters;
import org.eclipse.emf.parsley.internal.viewers.EObjectTableViewerComparator;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;
import org.eclipse.swt.SWT;
import org.junit.Before;
import org.junit.Test;

public class EObjectTableViewerComparatorTest extends AbstractViewerTest {

	private EObjectTableViewerComparator viewerComparator;

	@Before
	public void setupLabelProvider() {
		viewerComparator = new EObjectTableViewerComparator(
			new EStructuralFeatureParameters(
				TestmodelsPackage.eINSTANCE.getClassForCompare().getEStructuralFeatures()
			)
		);
	}

	@Test
	public void testStringCompare() {
		simulateSortColumn(0);
		assertCompareLower(createWithString("aaa"), createWithString("bbb"));
		simulateSortColumn(0);
		assertCompareGreater(createWithString("aaa"), createWithString("bbb"));
		simulateSortColumn(0);
	}

	@Test
	public void testIntCompare() {
		assertCompareEquals(createWithInt(1), createWithInt(2));
		simulateSortColumn(1);
		assertCompareLower(createWithInt(1), createWithInt(2));
		simulateSortColumn(1);
		assertCompareGreater(createWithInt(1), createWithInt(2));
		simulateSortColumn(1);
	}

	@Test
	public void testBigDecimalCompare() {
		assertCompareEquals(createWithBigDecimal(BigDecimal.ZERO), createWithBigDecimal(BigDecimal.ONE));
		simulateSortColumn(2);
		assertCompareLower(createWithBigDecimal(BigDecimal.ZERO), createWithBigDecimal(BigDecimal.ONE));
		simulateSortColumn(2);
		assertCompareGreater(createWithBigDecimal(BigDecimal.ZERO), createWithBigDecimal(BigDecimal.ONE));
		simulateSortColumn(2);
	}

	@Test
	public void testDateCompare() {
		assertCompareEquals(createWithDate(day("21/12/1971")), createWithDate(day("12/08/1975")));
		simulateSortColumn(3);
		assertCompareLower(createWithDate(day("21/12/1971")), createWithDate(day("12/08/1975")));
		simulateSortColumn(3);
		assertCompareGreater(createWithDate(day("09/12/1966")), createWithDate(day("12/08/1975")));
		simulateSortColumn(3);
	}

	@Test
	public void testDirection() {
		assertEquals(SWT.NONE, viewerComparator.getDirection());
		simulateSortColumn(0);
		assertEquals(0, viewerComparator.getPropertyIndex());
		assertEquals(SWT.UP, viewerComparator.getDirection());
		simulateSortColumn(0);
		assertEquals(SWT.DOWN, viewerComparator.getDirection());
		simulateSortColumn(0);
		assertEquals(SWT.NONE, viewerComparator.getDirection());
	}

	private void simulateSortColumn(int index) {
		viewerComparator.setPropertyIndex(index);
	}

	private Date day(String value) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private void assertCompareGreater(ClassForCompare first, ClassForCompare second) {
		assertTrue(viewerComparator.compare(null, first, second) > 0);
	}

	private void assertCompareLower(ClassForCompare first, ClassForCompare second) {
		assertTrue(viewerComparator.compare(null, first, second) < 0);
	}

	private void assertCompareEquals(ClassForCompare first, ClassForCompare second) {
		assertTrue(viewerComparator.compare(null, first, second) == 0);
	}

	private ClassForCompare createWithInt(int value) {
		var obj = TestmodelsFactory.eINSTANCE.createClassForCompare();
		obj.setIntAttribute(value);
		return obj;
	}

	private ClassForCompare createWithBigDecimal(BigDecimal value) {
		var obj = TestmodelsFactory.eINSTANCE.createClassForCompare();
		obj.setBigDecimalAttribute(value);
		return obj;
	}

	private ClassForCompare createWithDate(Date value) {
		var obj = TestmodelsFactory.eINSTANCE.createClassForCompare();
		obj.setDateAttribute(value);
		return obj;
	}

	private ClassForCompare createWithString(String value) {
		var obj = TestmodelsFactory.eINSTANCE.createClassForCompare();
		obj.setStringAttribute(value);
		return obj;
	}
}
