package org.eclipse.emf.parsley.tests

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForCompare
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.eclipse.emf.parsley.internal.viewers.GenericFeatureViewerComparator
import org.eclipse.swt.SWT

class GenericFeatureViewerComparatorTest extends AbstractViewerTest {

	var GenericFeatureViewerComparator genericViewerComparator

	@Before
	def void setupLabelProvider() {
		genericViewerComparator = getOrCreateInjector.getInstance(GenericFeatureViewerComparator)
		genericViewerComparator.init(TestmodelsPackage.eINSTANCE.classForCompare.EStructuralFeatures)
	}

	@Test
	def void testStringCompare() {
		simulateSortColumn(0)
		assertCompareLower(createWithString("aaa"),createWithString("bbb"))
		simulateSortColumn(0)
		assertCompareGreater(createWithString("aaa"),createWithString("bbb"))
		simulateSortColumn(0)
	}

	@Test
	def void testIntCompare() {
		assertCompareEquals(createWithInt(1),createWithInt(2))
		simulateSortColumn(1)
		assertCompareLower(createWithInt(1),createWithInt(2))
		simulateSortColumn(1)
		assertCompareGreater(createWithInt(1),createWithInt(2))
		simulateSortColumn(1)
	}

	@Test
	def void testBigDecimalCompare() {
		assertCompareEquals(createWithBigDecimal(BigDecimal.ZERO),createWithBigDecimal(BigDecimal.ONE))
		simulateSortColumn(2)
		assertCompareLower(createWithBigDecimal(BigDecimal.ZERO),createWithBigDecimal(BigDecimal.ONE))
		simulateSortColumn(2)
		assertCompareGreater(createWithBigDecimal(BigDecimal.ZERO),createWithBigDecimal(BigDecimal.ONE))
		simulateSortColumn(2)
	}

	@Test
	def void testDateCompare() {
		assertCompareEquals(createWithDate(day("21/12/1971")),createWithDate(day("12/08/1975")))
		simulateSortColumn(3)
		assertCompareLower(createWithDate(day("21/12/1971")),createWithDate(day("12/08/1975")))
		simulateSortColumn(3)
		assertCompareGreater(createWithDate(day("09/12/1966")),createWithDate(day("12/08/1975")))
		simulateSortColumn(3)
	}

	@Test
	def void testDirection() {
		assertEquals(SWT.NONE,genericViewerComparator.direction)
		simulateSortColumn(0)
		assertEquals(0, genericViewerComparator.propertyIndex)
		assertEquals(SWT.UP,genericViewerComparator.direction)
		simulateSortColumn(0)
		assertEquals(SWT.DOWN,genericViewerComparator.direction)
		simulateSortColumn(0)
		assertEquals(SWT.NONE,genericViewerComparator.direction)
	}

	private def void simulateSortColumn(int index) {
		genericViewerComparator.propertyIndex = index
	}

	private def day(String value) {
		new SimpleDateFormat("dd/MM/yyyy").parse(value)
	}

	private def assertCompareGreater(ClassForCompare first, ClassForCompare second) {
		assertTrue(genericViewerComparator.compare(null,first,second)>0)
	}

	private def assertCompareLower(ClassForCompare first, ClassForCompare second) {
		assertTrue(genericViewerComparator.compare(null,first,second)<0)
	}

	private def assertCompareEquals(ClassForCompare first, ClassForCompare second) {
		assertTrue(genericViewerComparator.compare(null,first,second)==0)
	}

	private def createWithInt(int value){
		TestmodelsFactory.eINSTANCE.createClassForCompare =>[
			intAttribute=value
		]
		
	}

	private def createWithBigDecimal(BigDecimal value){
		TestmodelsFactory.eINSTANCE.createClassForCompare =>[
			bigDecimalAttribute=value
		]
		
	}

	private def createWithDate(Date value){
		TestmodelsFactory.eINSTANCE.createClassForCompare =>[
			dateAttribute=value
		]
		
	}

	private def createWithString(String value){
		TestmodelsFactory.eINSTANCE.createClassForCompare =>[
			stringAttribute=value
		]
		
	}
}