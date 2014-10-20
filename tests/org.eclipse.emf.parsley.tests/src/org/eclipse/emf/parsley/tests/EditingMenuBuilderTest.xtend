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
	def void testWithNonStructuredSelection() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertMenuItemsGivenObject(new ISelection() {
			
			override isEmpty() {
				true
			}
			
		},
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

	def private assertMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, Object o, CharSequence expectedRepresentation) {
		assertMenuItemsGivenObject(editingMenuBuilder, createSelection(o), expectedRepresentation)
	}

	def private assertMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, ISelection sel, CharSequence expectedRepresentation) {
		val menuManager = createMenuManager
		editingMenuBuilder.updateSelection(sel)
		editingMenuBuilder.menuAboutToShow(menuManager)
		
		expectedRepresentation.
		assertEquals(menuManager.items.map[menuItemToStringRepresentation].join(", "))
	}

	def private menuItemToStringRepresentation(IContributionItem item) {
		switch (item) {
			Separator: "separator"
			ActionContributionItem: item.action.text
			default: "unknown"
		}
	}
}