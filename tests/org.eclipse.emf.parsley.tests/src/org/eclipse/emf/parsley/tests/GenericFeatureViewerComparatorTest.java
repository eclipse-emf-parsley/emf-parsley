package org.eclipse.emf.parsley.tests;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.emf.parsley.internal.viewers.GenericFeatureViewerComparator;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;
import org.eclipse.swt.SWT;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenericFeatureViewerComparatorTest extends AbstractViewerTest {

	private GenericFeatureViewerComparator genericViewerComparator;

	@Before
	public void setupLabelProvider() {
		genericViewerComparator = getOrCreateInjector().getInstance(GenericFeatureViewerComparator.class);
		genericViewerComparator.init(TestmodelsPackage.eINSTANCE.getClassForCompare().getEStructuralFeatures());
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
		assertEquals(SWT.NONE, genericViewerComparator.getDirection());
		simulateSortColumn(0);
		assertEquals(0, genericViewerComparator.getPropertyIndex());
		assertEquals(SWT.UP, genericViewerComparator.getDirection());
		simulateSortColumn(0);
		assertEquals(SWT.DOWN, genericViewerComparator.getDirection());
		simulateSortColumn(0);
		assertEquals(SWT.NONE, genericViewerComparator.getDirection());
	}

	private void simulateSortColumn(int index) {
		genericViewerComparator.setPropertyIndex(index);
	}

	private Date day(String value) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private void assertCompareGreater(ClassForCompare first, ClassForCompare second) {
		assertTrue(genericViewerComparator.compare(null, first, second) > 0);
	}

	private void assertCompareLower(ClassForCompare first, ClassForCompare second) {
		assertTrue(genericViewerComparator.compare(null, first, second) < 0);
	}

	private void assertCompareEquals(ClassForCompare first, ClassForCompare second) {
		assertTrue(genericViewerComparator.compare(null, first, second) == 0);
	}

	private ClassForCompare createWithInt(int value) {
		ClassForCompare obj = TestmodelsFactory.eINSTANCE.createClassForCompare();
		obj.setIntAttribute(value);
		return obj;
	}

	private ClassForCompare createWithBigDecimal(BigDecimal value) {
		ClassForCompare obj = TestmodelsFactory.eINSTANCE.createClassForCompare();
		obj.setBigDecimalAttribute(value);
		return obj;
	}

	private ClassForCompare createWithDate(Date value) {
		ClassForCompare obj = TestmodelsFactory.eINSTANCE.createClassForCompare();
		obj.setDateAttribute(value);
		return obj;
	}

	private ClassForCompare createWithString(String value) {
		ClassForCompare obj = TestmodelsFactory.eINSTANCE.createClassForCompare();
		obj.setStringAttribute(value);
		return obj;
	}
}
