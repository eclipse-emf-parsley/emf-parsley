/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyEditableTableTests extends EmfParsleySWTBotAbstractTests {

	private static final String MULTI_REFERENCE_FEATURE_LABEL = "Multi Reference Feature";
	private static final String CLASS_WITH_NAME_LABEL = "Class With Name";
	private static final String SECOND_CLASS_WITH_NAME = CLASS_WITH_NAME_LABEL + " Second Class With Name";

	private static final int ROW = 0;

	// the indexes must correspond to the order of the features specified in the
	// module.parsley of the tests.views project

	private static final int BOOLEAN_FEATURE = 0;
	private static final int BOOLEAN_OBJECT_FEATURE = 1;
	private static final int BOOLEAN_DATATYPE_FEATURE = 2;
	private static final int BOOLEAN_PRIMITIVE_DATATYPE_FEATURE = 3;

	private static final int ENUM_FEATURE = 4;

	private static final int STRING_FEATURE = 5;
	private static final int STRING_DATATYPE_FEATURE = 6;
	private static final int STRING_WITH_DEFAULT_FEATURE = 7;

	private static final int BIG_DECIMAL_FEATURE = 8;
	private static final int BIG_INTEGER_FEATURE = 9;

	private static final int DOUBLE_FEATURE = 10;
	private static final int DOUBLE_OBJECT_FEATURE = 11;

	private static final int INT_FEATURE = 12;
	private static final int INT_OBJECT_FEATURE = 13;

	private static final int FLOAT_FEATURE = 14;
	private static final int FLOAT_OBJECT_FEATURE = 15;

	private static final int SHORT_FEATURE = 16;
	private static final int SHORT_OBJECT_FEATURE = 17;

	private static final int BYTE_FEATURE = 18;
	private static final int BYTE_OBJECT_FEATURE = 19;

	private static final int DATE_FEATURE = 20;

	private static final int SINGLE_REFERENCE_FEATURE = 21;
	private static final int MULTI_REFERENCE_FEATURE = 22;

	private SWTBotTable table;

	@Before
	public void setupProject() {
		createMyTestProject();
		table = openTestEditableTableView();
	}

	@After
	public void closeView() {
		closeView(TEST_MODEL_EDITABLE_TABLE_VIEW);
	}

	@Test
	public void testBooleanCell() {
		clickBooleanCell(BOOLEAN_FEATURE, "false");
	}

	@Test
	public void testBooleanObjectCell() {
		clickBooleanCell(BOOLEAN_OBJECT_FEATURE, "");
	}

	@Test
	public void testBooleanDataTypeCell() {
		clickBooleanCell(BOOLEAN_DATATYPE_FEATURE, "");
	}

	@Test
	public void testBooleanPrimitiveDataTypeCell() {
		clickBooleanCell(BOOLEAN_PRIMITIVE_DATATYPE_FEATURE, "false");
	}

	@Test
	public void testEnumCell() {
		clickComboCell(ENUM_FEATURE, EnumForControls.get(EnumForControls.FIRST_VALUE).toString(),
				EnumForControls.get(EnumForControls.SECOND_VALUE).toString());
	}

	@Test
	public void testStringCell() {
		clickStringCell(STRING_FEATURE, "", "a new value");
	}

	@Test
	public void testStringDatatypeCell() {
		clickStringCell(STRING_DATATYPE_FEATURE, "", "a new value");
	}

	@Test
	public void testStringWithDefaultCell() {
		clickStringCell(STRING_WITH_DEFAULT_FEATURE, "default", "a new value");
	}

	@Test
	public void testStringCellWithoutModification() {
		clickStringCellWithoutModification(STRING_WITH_DEFAULT_FEATURE, "default");
	}

	@Test
	public void testBigDecimalCell() {
		clickStringCell(BIG_DECIMAL_FEATURE, "", "100.9");
	}

	@Test
	public void testBigIntegerCell() {
		clickStringCell(BIG_INTEGER_FEATURE, "", "100");
	}

	@Test
	public void testDoubleCell() {
		clickStringCell(DOUBLE_FEATURE, "0.0", "100", "100.0");
	}

	@Test
	public void testDoubleObjectCell() {
		clickStringCell(DOUBLE_OBJECT_FEATURE, "", "100", "100.0");
	}

	@Test
	public void testIntCell() {
		clickStringCell(INT_FEATURE, "0", "100");
	}

	@Test
	public void testIntObjectCell() {
		clickStringCell(INT_OBJECT_FEATURE, "", "100");
	}

	@Test
	public void testFloatCell() {
		clickStringCell(FLOAT_FEATURE, "0.0", "100", "100.0");
	}

	@Test
	public void testFloatObjectCell() {
		clickStringCell(FLOAT_OBJECT_FEATURE, "", "100", "100.0");
	}

	@Test
	public void testShortCell() {
		clickStringCell(SHORT_FEATURE, "0", "100");
	}

	@Test
	public void testShortObjectCell() {
		clickStringCell(SHORT_OBJECT_FEATURE, "", "100");
	}

	@Test
	public void testByteCell() {
		clickStringCell(BYTE_FEATURE, "0", "100");
	}

	@Test
	public void testByteObjectCell() {
		clickStringCell(BYTE_OBJECT_FEATURE, "", "100");
	}

	@Test
	public void testDateCell() {
		int column = DATE_FEATURE;
		String originalValue = "";
		String newValue = "2000-11-11";
		// don't specify the whole effective new value:
		// it might contain CET or EST depending on the system
		String effectiveNewValue = "Sat Nov 11 00:00:00";
		table.click(ROW, column);
		bot.text(originalValue).setText(newValue);
		// we leave the cell by clicking on the enum cell
		leaveEditingCellClickingOnAnotherCell(STRING_FEATURE);
		String cellContents = table.cell(ROW, column);
		// just assert string containment for the reason above
		assertTrue(
				effectiveNewValue + " not found in " + cellContents,
				cellContents.contains(effectiveNewValue));
		assertDirtyAndSave();
		undo("Set");
		cellContents = table.cell(ROW, column);
		assertEquals(originalValue, cellContents);
		assertDirtyAndSave();
		redo("Set");
		cellContents = table.cell(ROW, column);
		assertTrue(
				effectiveNewValue + " not found in " + cellContents,
				cellContents.contains(effectiveNewValue));
		assertDirtyAndSave();
	}

	@Test
	public void testDateCellWithWrongDate() {
		table.click(ROW, DATE_FEATURE);
		bot.text("").setText("2000");
		// we leave the cell by clicking on the enum cell
		leaveEditingCellClickingOnAnotherCell(STRING_FEATURE);
		// the date was not valid so the original value has not changed
		assertEquals("", table.cell(ROW, DATE_FEATURE));
		assertSaveableViewIsDirty(false, TEST_MODEL_EDITABLE_TABLE_VIEW);
	}

	@Test
	public void testSingleReferenceCell() {
		clickComboCell(SINGLE_REFERENCE_FEATURE, "", SECOND_CLASS_WITH_NAME);
	}

	@Test
	public void testMultiReferenceCell() {
		if (isIndigo())
			return; // this test does not seem to work when building against Indigo
		clickMultiReferenceCell(MULTI_REFERENCE_FEATURE, CLASS_FOR_CONTROLS_LABEL, "", SECOND_CLASS_WITH_NAME);
	}

	/**
	 * Uses the combo to select true for the boolean feature
	 * 
	 * @param column
	 * @param originalValue
	 */
	private void clickBooleanCell(int column, String originalValue) {
		if (getOrgEclipseEmfEditUiMinorVersion() < 14) {
			clickComboCell(column, originalValue, "true");
		} else {
			// In EMF 2.14 for booleans we have checkboxes instead of
			// combo box
			table.click(ROW, column);
			SWTBotCheckBox checkBox = bot.checkBox();
			// we always start from an unchecked checkbox
			assertFalse("should not be checked", checkBox.isChecked());
			checkBox.click(); // checked
			leaveEditingCellClickingOnAnotherCell(STRING_FEATURE);
			assertDirtyAndSave();
			undo("Set");
			assertEquals(originalValue, table.cell(ROW, column));
			assertDirtyAndSave();
			redo("Set");
			// for Boolean objects (not primitive boolean) or
			// Boolean datatype we have 3 state checkbox:
			// null, false, true
			// for Boolean and Boolean datatype we pass an empty originalValue
			// and redoing leads to "false" (instead of "true"),
			// since we redo one state (different from clicking?)
			assertEquals(originalValue.isEmpty() ? "false" : "true", table.cell(ROW, column));
			assertDirtyAndSave();
		}
	}

	private void clickComboCell(int column, String firstValue, String secondValue) {
		table.click(ROW, column);
		if (getOrgEclipseEmfEditUiMinorVersion() >= 14) {
			// in EMF 2.14 the combo box automatically shows the dropdown list
			// as soon as the cell is selected, see
			// org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor.ExtendedComboBoxCellEditor(Composite, List<?>, ILabelProvider, boolean, int, ValueHandler, boolean)
			// so we need to close the list window, otherwise the original combo
			// cannot be found by SWTBot
			bot.activeShell().close();
		}
		final SWTBotCCombo ccomboBox = bot.ccomboBox(firstValue);
		ccomboBox.setSelection(secondValue);
		leaveEditingCellClickingOnAnotherCell(STRING_FEATURE);
		assertDirtyAndSave();
		undo("Set");
		assertEquals(firstValue, table.cell(ROW, column));
		assertDirtyAndSave();
		redo("Set");
		assertEquals(secondValue, table.cell(ROW, column));
		assertDirtyAndSave();
	}

	private void clickStringCell(int column, String originalValue, String newValue) {
		clickStringCell(column, originalValue, newValue, newValue);
	}

	/**
	 * After the insertion of a new value, the effective value might be different
	 * depending on the type, e.g., setting 100 for float will result in an effective
	 * 100.0.
	 * 
	 * @param column
	 * @param originalValue
	 * @param newValue
	 * @param effectiveNewValue
	 */
	private void clickStringCell(int column, String originalValue, String newValue, String effectiveNewValue) {
		table.click(ROW, column);
		bot.text(originalValue).setText(newValue);
		// we leave the cell by clicking on the enum cell
		leaveEditingCellClickingOnAnotherCell(INT_FEATURE);
		assertEquals(effectiveNewValue, table.cell(ROW, column));
		assertDirtyAndSave();
		undo("Set");
		assertEquals(originalValue, table.cell(ROW, column));
		assertDirtyAndSave();
		redo("Set");
		assertEquals(effectiveNewValue, table.cell(ROW, column));
		assertDirtyAndSave();
	}

	private void clickStringCellWithoutModification(int column, String value) {
		table.click(ROW, column);
		bot.text(value).setText(value);
		// we leave the cell by clicking on the enum cell
		leaveEditingCellClickingOnAnotherCell(INT_FEATURE);
		assertEquals(value, table.cell(ROW, column));
		assertSaveableViewIsDirty(false, TEST_MODEL_EDITABLE_TABLE_VIEW);
	}

	private void clickMultiReferenceCell(int column, String containerName, String originalValue, String newValue) {
		table.click(ROW, column);
		// The following is not required and would fail in 2019-12
		// bot.text(originalValue);
		// TODO: only when originalValue is empty?

		bot.button("...").click();
		SWTBotShell shell = bot.shell(MULTI_REFERENCE_FEATURE_LABEL + " -- " + containerName);
		shell.activate();

		// add a reference in the dialog
		bot.table(0).select(newValue); // left table
		bot.button("Add").click();

		bot.button("OK").click();
		bot.waitUntil(shellCloses(shell), SWTBotPreferences.TIMEOUT);

		// we leave the cell by clicking on the enum cell
		leaveEditingCellClickingOnAnotherCell(STRING_FEATURE);

		assertEquals(newValue, table.cell(ROW, column));
		assertDirtyAndSave();
		undo("Set");
		assertEquals(originalValue, table.cell(ROW, column));
		assertDirtyAndSave();
		redo("Set");
		assertEquals(newValue, table.cell(ROW, column));
		assertDirtyAndSave();
	}

	private void assertDirtyAndSave() {
		assertDirtyThenSaveAndAssertNotDirty(TEST_MODEL_EDITABLE_TABLE_VIEW);
	}

	/**
	 * So that the model is updated
	 * @param column you must make sure you specify a column which is different
	 * from the one being tested and that does not fire another model update
	 */
	private void leaveEditingCellClickingOnAnotherCell(int column) {
		// we need to click on another cell so that the model is updated
		table.click(ROW, column);
	}

	protected SWTBotTable openTestEditableTableView() {
		SWTBotTable table = tableFromView(TEST_MODEL_EDITABLE_TABLE_VIEW);
		return table;
	}
}
