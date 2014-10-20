package org.eclipse.emf.parsley.tests

import com.google.inject.Injector
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.util.EmfParsleyUtil
import org.eclipse.jface.action.ActionContributionItem
import org.eclipse.jface.action.IContributionItem
import org.eclipse.jface.action.MenuManager
import org.eclipse.jface.action.Separator
import org.eclipse.jface.viewers.StructuredSelection
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.jface.viewers.ISelection
import org.eclipse.emf.ecore.EObject

class EditingMenuBuilderTest extends EmfParsleyAbstractTest {

	var EditingDomain editingDomain = null
	
	@Before
	def void setEditingDomainToNull() {
		editingDomain = null
	}

	@Test
	def void testDefaultMenu() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertMenuItemsGivenObject(eobj,
		"&Undo @Ctrl+Z, &Redo @Ctrl+Y, separator, Cu&t, &Copy, &Paste, separator, &Delete, separator")
	}

	@Test
	def void testDefaultEmfNewChildMenu() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertEmfMenuItemsGivenObject(lib,
'''
&New Child -> [
	Stock Book, Stock Book On Tape, Stock Video Cassette, Branches Library, Writers Writer, Employees Employee, Borrowers Borrower
]
, N&ew Sibling -> [
]
'''
		)
	}

	@Test
	def void testDefaultEmfNewSiblingMenu() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertEmfMenuItemsGivenObject(wr,
'''
&New Child -> [
]
, N&ew Sibling -> [
	Stock Book, Stock Book On Tape, Stock Video Cassette, Branches Library, Writers Writer, Employees Employee, Borrowers Borrower
]
'''
		)
	}

	@Test
	def void testDefaultEmfMenuWithEmptySelection() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertEmfMenuItemsGivenObject(createEmptySelection, "")
	}

	@Test
	def void testEmfCreateActionsWithEmptySelection() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		assertTrue(editingMenuBuilder.createChildActions(createEmptySelection).empty)
		assertTrue(editingMenuBuilder.createSiblingActions(createEmptySelection).empty)
	}

	@Test
	def void testEmfCreateActionsWithNull() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		assertTrue(editingMenuBuilder.createChildActions(null).empty)
		assertTrue(editingMenuBuilder.createSiblingActions(null).empty)
	}


	@Test
	def void testWithNonStructuredSelection() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertMenuItemsGivenObject(createEmptySelection,
		"")
	}

	@Test
	def void testCustomMenu() {
		val editingMenuBuilder = new EditingMenuBuilder() {
							
			def protected menuContributions(ClassForControls o) {
				#[
					actionRedo(),
					actionUndo(),
					separator(),
					actionCopy(),
					actionPaste()
				]
			}
							
		}.injectMembers.initializeEditingMenuBuilder
		editingMenuBuilder.assertMenuItemsGivenObject(eobj,
		"&Redo @Ctrl+Y, &Undo @Ctrl+Z, separator, &Copy, &Paste")
	}

	@Test
	def void testCustomDefaultMenu() {
		val editingMenuBuilder = new EditingMenuBuilder() {
			
			override protected defaultMenuContributions(Object object) {
				#[
					actionRedo(),
					actionUndo(),
					separator(),
					actionCopy(),
					actionPaste()
				]
			}
			
		}.injectMembers.initializeEditingMenuBuilder
		editingMenuBuilder.assertMenuItemsGivenObject(eobj,
		"&Redo @Ctrl+Y, &Undo @Ctrl+Z, separator, &Copy, &Paste")
	}

	@Test
	def void testCustomMenuWithSubmenu() {
		val editingMenuBuilder = new EditingMenuBuilder() {
							
			def protected menuContributions(ClassForControls o) {
				#[
					actionRedo(),
					actionUndo(),
					separator(),
					submenu("Submenu1", #[
						actionCopy(),
						submenu("Submenu2", #[
							actionCut()
						])
					]),
					actionPaste()
				]
			}
							
		}.injectMembers.initializeEditingMenuBuilder
		editingMenuBuilder.assertMenuItemsGivenObject(eobj,
'''
&Redo @Ctrl+Y, &Undo @Ctrl+Z, separator, Submenu1 -> [
	&Copy, Submenu2 -> [
		Cu&t
	]
]
, &Paste'''
		)
	}
	
	override protected getEditingDomain() {
		if (editingDomain === null) {
			editingDomain = getOrCreateInjector.getProvider(AdapterFactoryEditingDomain).get()
		}
		return editingDomain
	}

	def private getAndInitializeEditingMenuBuilder() {
		getOrCreateInjector().getAndInitializeEditingMenuBuilder
	}

	def private getAndInitializeEditingMenuBuilder(Injector injector) {
		injector.getInstance(EditingMenuBuilder) => [
			createActions
			setEditingDomain(getEditingDomain)
		]
	}

	def private initializeEditingMenuBuilder(EditingMenuBuilder editingMenuBuilder) {
		editingMenuBuilder => [
			createActions
			setEditingDomain(getEditingDomain)
		]
	}

	def private createMenuManager() {
		new MenuManager("#PopUp") => [
			setRemoveAllWhenShown(true);
		]
	}

	def private createSelection(Object o) {
		new StructuredSelection(EmfParsleyUtil.ensureCollection(o).toArray)
	}

	def private createEmptySelection() {
		new ISelection() {
			override isEmpty() {
				true
			}
		}
	}

	def private assertMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, Object o, CharSequence expectedRepresentation) {
		assertMenuItemsGivenObject(editingMenuBuilder, createSelection(o), expectedRepresentation)
	}

	def private assertMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, ISelection sel, CharSequence expectedRepresentation) {
		val menuManager = createMenuManager
		editingMenuBuilder.updateSelection(sel)
		editingMenuBuilder.menuAboutToShow(menuManager)
		
		expectedRepresentation.toString.
		assertEquals(menuManager.items.map[menuItemToStringRepresentation].join(", "))
	}

	def private assertEmfMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, EObject o, CharSequence expectedRepresentation) {
		assertEmfMenuItemsGivenObject(editingMenuBuilder, createSelection(o), expectedRepresentation)
	}

	def private assertEmfMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, ISelection sel, CharSequence expectedRepresentation) {
		val menuManager = createMenuManager
		editingMenuBuilder.updateSelection(sel)
		editingMenuBuilder.emfMenuAboutToShow(menuManager)
		
		expectedRepresentation.toString.
		assertEquals(menuManager.items.map[menuItemToStringRepresentation].join(", "))
	}

	def private CharSequence menuItemToStringRepresentation(IContributionItem item) {
		switch (item) {
			Separator: "separator"
			ActionContributionItem: item.action.text
			MenuManager: '''
			«item.menuText» -> [
				«item.items.map[menuItemToStringRepresentation].join(", ")»
			]
			'''
			default: "unknown"
		}
	}
}