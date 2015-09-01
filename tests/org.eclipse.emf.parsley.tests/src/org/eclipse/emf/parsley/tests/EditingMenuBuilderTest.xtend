package org.eclipse.emf.parsley.tests

import com.google.inject.Injector
import org.eclipse.emf.common.command.CommandStackListener
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException
import org.eclipse.emf.ecore.xmi.DanglingHREFException
import org.eclipse.emf.edit.command.ChangeCommand
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.util.EmfCommandsUtil
import org.eclipse.emf.parsley.util.EmfParsleyUtil
import org.eclipse.jface.action.ActionContributionItem
import org.eclipse.jface.action.IContributionItem
import org.eclipse.jface.action.MenuManager
import org.eclipse.jface.action.Separator
import org.eclipse.jface.viewers.ISelection
import org.eclipse.jface.viewers.StructuredSelection
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static org.eclipse.emf.parsley.tests.EditingMenuBuilderTest.*

import static extension org.junit.Assert.*
import java.util.EventObject

class EditingMenuBuilderTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	var EditingDomain editingDomain = null

	val static THIS_IS_A_NEW_BOOK = "This is a new book"

	/**
	 * We will use it also for easily testing EMF actions, since there will
	 * be no submenus
	 */
	static class FlattenedNewChildEditingMenuBuilder extends EditingMenuBuilder {

		override protected defaultEmfMenuContributions(ISelection selection) {
			createChildActions(selection)
		}

	}

	@Before
	def void setEditingDomainToNull() {
		editingDomain = null
	}

	@Test
	def void testDefaultMenu() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertMenuItemsGivenObject(classForControlsInstance,
		"&Undo @Ctrl+Z, &Redo @Ctrl+Y, separator, Cu&t, &Copy, &Paste, separator, &Delete, separator")
	}

	@Test
	def void testDefaultEmfNewChildMenu() {
		val editingMenuBuilder = getAndInitializeEditingMenuBuilder
		editingMenuBuilder.assertEmfMenuItemsGivenObject(library,
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
		editingMenuBuilder.assertEmfMenuItemsGivenObject(writer,
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
	def void testCustomEmfDefaultMenu() {
		val editingMenuBuilder = new FlattenedNewChildEditingMenuBuilder().
			injectMembers.initializeEditingMenuBuilder
		editingMenuBuilder.assertEmfMenuItemsGivenObject(library,
'''Stock Book, Stock Book On Tape, Stock Video Cassette, Branches Library, Writers Writer, Employees Employee, Borrowers Borrower'''
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
		editingMenuBuilder.assertMenuItemsGivenObject(classForControlsInstance,
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
		editingMenuBuilder.assertMenuItemsGivenObject(classForControlsInstance,
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
		editingMenuBuilder.assertMenuItemsGivenObject(classForControlsInstance,
'''
&Redo @Ctrl+Y, &Undo @Ctrl+Z, separator, Submenu1 -> [
	&Copy, Submenu2 -> [
		Cu&t
	]
]
, &Paste'''
		)
	}

	@Test
	def void testExecuteNewChildMenu() {
		val editingMenuBuilder = new FlattenedNewChildEditingMenuBuilder().
			injectMembers.initializeEditingMenuBuilder
		// before executing there must be no employee
		library.employees.head.assertNull
		editingMenuBuilder.emfMenuManagerForSelection(library).
			executeAction("Employees Employee")
		// now there should be one
		library.employees.head.assertNotNull
	}

	@Test
	def void testCustomAddCommand() {
		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Library o) {
				#[
					actionAdd("New Writer", o.writers, 
						libraryFactory.createWriter => [
							name = "This is a new writer"
						]
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder
		editingMenuBuilder.emfMenuManagerForSelection(library).
			executeAction("New Writer")
		library.writers.exists[name == "This is a new writer"].assertTrue
	}

	@Test(expected=DanglingHREFException)
	def void testCustomAddCommand_Bug466219() {
		// this will recreate the context of
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=466219
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library

		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionAdd("New Book for Writer", (w.eContainer as Library).books, 
						libraryFactory.createBook => [
							title = THIS_IS_A_NEW_BOOK
							author = w
						]
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder
		val writerForMenu = library.writers.head
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("New Book for Writer")
		val addedBook = library.getAddedNewBook
		addedBook.author.assertSame(writerForMenu)

		// retrigger menu creation, this will create a dangling reference
		// the writer will reference a book which is not yet in the resource
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu)
		// and will never be in the resource

		try {
			resource.save(null)
		} catch (IOWrappedException e) {
			throw e.cause
		}
	}

	@Test
	def void testCustomAddCommandAndInitializer() {
		// see also
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=466219
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library

		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionAdd("New Book for Writer", (w.eContainer as Library).books, 
						libraryFactory.createBook => [
							title = THIS_IS_A_NEW_BOOK
						],
						[
							// initialize the added object only after it has
							// been added effectively
							// https://bugs.eclipse.org/bugs/show_bug.cgi?id=466219
							book |
							book.author = w
						]
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder
		val writerForMenu = library.writers.head
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("New Book for Writer")
		val addedBook = library.getAddedNewBook
		addedBook.author.assertSame(writerForMenu)

		// retrigger menu creation
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu)

		resource.save(null)
	}

	@Test
	def void testAddCommandUndoRedoAffectedObjects() {
		// see also
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=476289
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library

		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionUndo,
					actionRedo,
					actionAdd("Custom New Book",
						(w.eContainer as Library).books, 
						libraryFactory.createBook =>
							[title = THIS_IS_A_NEW_BOOK]
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder

		val writerForMenu = library.writers.head

		oneTimeCommandStackListener[
			mostRecentCommand.affectedObjects => [
				1.assertEquals(size)
				head.assertSame(library.getAddedNewBook)
			]
		]

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("Custom New Book")
		val addedBook = library.getAddedNewBook

		oneTimeCommandStackListener[
			// after an undo, the affected object must be the container
			mostRecentCommand.affectedObjects => [
				1.assertEquals(size)
				head.assertSame(library)
			]
		]

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeUndo
		addedBook.eContainer.assertNull

		oneTimeCommandStackListener[
			// after the redo, the book will be added back,
			// so that must be the affected object
			mostRecentCommand.affectedObjects => [
				1.assertEquals(size)
				head.assertSame(library.getAddedNewBook)
			]
		]

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeRedo
		addedBook.eContainer.assertSame(library)

		resource.save(null)
	}

	@Test
	def void testChangeCommand() {
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library
		
		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionChange("New Book for Writer", w,
						[
							writer |
							val newBook = libraryFactory.createBook => [
								title = THIS_IS_A_NEW_BOOK
							]
							(w.eContainer as Library).books += newBook
							newBook.author = writer
						] 
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder
		val writerForMenu = library.writers.head
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("New Book for Writer")
		val addedBook = library.getAddedNewBook
		addedBook.author.assertSame(writerForMenu)

		// retrigger menu creation
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu)

		resource.save(null)
	}

	@Test
	def void testChangeCommandUndoRedoOnSelectedObject() {
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library

		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionUndo,
					actionRedo,
					actionChange("New Book for Writer", w,
						[
							writer |
							val newBook = libraryFactory.createBook => [
								title = THIS_IS_A_NEW_BOOK
							]
							// this change will NOT be recorded, since we track
							// only the specified writer as the element
							(w.eContainer as Library).books += newBook
							// this change will be recorded
							newBook.author = writer
						] 
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder
		val writerForMenu = library.writers.head
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("New Book for Writer")
		val addedBook = library.getAddedNewBook
		addedBook.author.assertSame(writerForMenu)

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeUndo

		// since we track change only on the writer, the added book is still
		// in the library
		addedBook.eContainer.assertSame(library)
		// but its author is not the writer anymore
		addedBook.author.assertNull

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeRedo

		addedBook.author.assertSame(writerForMenu)

		resource.save(null)
	}

	@Test
	def void testChangeCommandAffectedObjects() {
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library

		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionUndo,
					actionRedo,
					actionChange("New Book for Writer", w,
						[
							writer |
							val newBook = libraryFactory.createBook => [
								title = THIS_IS_A_NEW_BOOK
							]
							// this change will NOT be recorded, since we track
							// only the specified writer as the element
							(w.eContainer as Library).books += newBook
							// this change will be recorded
							newBook.author = writer
						] 
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder

		commandStackListener[
			event |
			val command = EmfCommandsUtil.mostRecentCommand(event) as ChangeCommand
			command.affectedObjects => [
				contains(library.writers.head).assertTrue
				// the added book is not recorded as an affected object
				contains(library.getAddedNewBook).assertFalse
				1.assertEquals(size)
			]
		]

		val writerForMenu = library.writers.head
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("New Book for Writer")
		val addedBook = library.getAddedNewBook
		addedBook.author.assertSame(writerForMenu)

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeUndo

		// since we track change only on the writer, the added book is still
		// in the library
		addedBook.eContainer.assertSame(library)
		// but its author is not the writer anymore
		addedBook.author.assertNull

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeRedo

		addedBook.author.assertSame(writerForMenu)

		resource.save(null)
	}

	@Test
	def void testChangeCommandUndoRedoOnSelectedObjectContainer() {
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library

		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionUndo,
					actionRedo,
					actionChange("New Book for Writer", (w.eContainer as Library),
						[
							library |
							val newBook = libraryFactory.createBook => [
								// this change will NOT be recorded, since
								// the book is not yet part of the library
								title = THIS_IS_A_NEW_BOOK
							]
							// both changes will be recorded since we specified
							// the library as the element
							library.books += newBook
							newBook.author = w
						] 
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder

		commandStackListener[
			event |
			val command = EmfCommandsUtil.mostRecentCommand(event) as ChangeCommand
			command.affectedObjects => [
				contains(library.writers.head).assertTrue
				// since we get notifications for undo as well, we can't retrieve
				// the book as affected object from the library, since after an undo
				// the book has been removed
				findFirst[it instanceof Book].assertNotNull
			]
		]

		val writerForMenu = library.writers.head
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("New Book for Writer")
		val addedBook = library.getAddedNewBook
		addedBook.author.assertSame(writerForMenu)

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeUndo

		// since we track change on the library, the added book is removed from the library
		addedBook.eContainer.assertNotSame(library)
		// and its author is unset
		addedBook.author.assertNull
		// (1) note that the book's title is still set, since that
		// change had not been recorded (see the above menu implementation)
		THIS_IS_A_NEW_BOOK.assertEquals(addedBook.title)

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeRedo

		addedBook.eContainer.assertSame(library)
		addedBook.author.assertSame(writerForMenu)

		resource.save(null)
	}

	@Test
	def void testChangeCommandUndoRedoOnSelectedObjectContainer2() {
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library

		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionUndo,
					actionRedo,
					actionChange("New Book for Writer", (w.eContainer as Library),
						[
							library |
							val newBook = libraryFactory.createBook
							// both changes will be recorded since we specified
							// the library as the element
							library.books += newBook
							newBook.author = w
							newBook.title = THIS_IS_A_NEW_BOOK
						] 
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder
		val writerForMenu = library.writers.head
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("New Book for Writer")
		val addedBook = library.getAddedNewBook
		addedBook.author.assertSame(writerForMenu)

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeUndo

		// since we track change on the library, the added book is removed from the library
		addedBook.eContainer.assertNotSame(library)
		// and its author is unset
		addedBook.author.assertNull
		// also its title is null, since the title had been set after
		// the book was added to the library, so such change had been recorded
		addedBook.title.assertNull

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeRedo

		addedBook.eContainer.assertSame(library)
		addedBook.author.assertSame(writerForMenu)
		THIS_IS_A_NEW_BOOK.assertEquals(addedBook.title)

		resource.save(null)
	}

	@Test
	def void testChangeCommandUndoRedoOnSelectedObjectResource() {
		val resource = createTestLibrayResourceAndInitialize
		val library = resource.contents.head as Library

		val editingMenuBuilder = new EditingMenuBuilder() {

			def protected emfMenuContributions(Writer w) {
				#[
					actionUndo,
					actionRedo,
					actionChange("New Book for Writer", w.eResource,
						[
							resource |
							val newBook = libraryFactory.createBook => [
								title = THIS_IS_A_NEW_BOOK
							]
							val newLibrary = libraryFactory.createLibrary => [
								name = "A new library"
							]
							// all changes concerning the resource will be recorded
							resource.contents += newLibrary
							newLibrary.books += newBook
							newBook.author = w
						] 
					)
				]
			}

		}.injectMembers.initializeEditingMenuBuilder

		commandStackListener[
			event |
			val command = EmfCommandsUtil.mostRecentCommand(event) as ChangeCommand
			command.affectedObjects => [
				contains(library.writers.head).assertTrue
				// since we get notifications for undo as well, we can't retrieve
				// the book as affected object from the library, since after an undo
				// the book has been removed
				findFirst[it instanceof Book].assertNotNull
			]
		]

		val writerForMenu = library.writers.head
		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeAction("New Book for Writer")

		// the book's library is not the original one
		val addedLibrary = resource.contents.last as Library
		val addedBook = addedLibrary.getAddedNewBook
		addedBook.author.assertSame(writerForMenu)

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeUndo

		// since we track change on the resource, the added book is removed from the library
		addedBook.eContainer.assertNotSame(library)
		// the added library is removed from the resource
		addedLibrary.eResource.assertNotSame(resource)
		// and its author is unset
		addedBook.author.assertNull
		// see (1) for the reason why the book still has its title
		THIS_IS_A_NEW_BOOK.assertEquals(addedBook.title)
		"A new library".assertEquals(addedLibrary.name)

		editingMenuBuilder.emfMenuManagerForSelection(writerForMenu).
			executeRedo

		addedBook.eContainer.assertSame(addedLibrary)
		addedLibrary.eResource.assertSame(resource)
		addedBook.author.assertSame(writerForMenu)

		resource.save(null)
	}

	def protected getEditingDomain() {
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
			emb | 
			emb.createActions
			emb.setEditingDomain(getEditingDomain)
		]
	}

	def private initializeEditingMenuBuilder(EditingMenuBuilder editingMenuBuilder) {
		editingMenuBuilder => [
			emb | 
			emb.createActions
			emb.setEditingDomain(getEditingDomain)
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
		val menuManager = emfMenuManagerForSelection(editingMenuBuilder, sel)
		
		expectedRepresentation.toString.
		assertEquals(menuManager.menuItemsToStringRepresentation)
	}

	private def emfMenuManagerForSelection(EditingMenuBuilder editingMenuBuilder, EObject o) {
		emfMenuManagerForSelection(editingMenuBuilder, createSelection(o))
	}

	private def emfMenuManagerForSelection(EditingMenuBuilder editingMenuBuilder, ISelection sel) {
		val menuManager = createMenuManager
		editingMenuBuilder.updateSelection(sel)
		editingMenuBuilder.emfMenuAboutToShow(menuManager)
		menuManager
	}

	private def executeAction(MenuManager menuManager, String actionText) {
		menuManager.items.filter(ActionContributionItem).findFirst[action.text == actionText] => [
			assertTrue(
				"Could not find " + actionText + " in " + menuManager.menu,
				it != null
			)
			action.run
		]
	}

	private def executeUndo(MenuManager menuManager) {
		menuManager.executeAction("&Undo @Ctrl+Z")
	}

	private def executeRedo(MenuManager menuManager) {
		menuManager.executeAction("&Redo @Ctrl+Y")
	}

	def private CharSequence menuItemsToStringRepresentation(MenuManager menuManager) {
		menuManager.items.map[menuItemToStringRepresentation].join(", ")
	}

	def private CharSequence menuItemToStringRepresentation(IContributionItem item) {
		switch (item) {
			Separator: "separator"
			ActionContributionItem: item.action.text
			MenuManager: '''
			«item.menuText» -> [
				«item.menuItemsToStringRepresentation»
			]
			'''
			default: "unknown"
		}
	}

	def private getAddedNewBook(Library library) {
		val addedBook = library.books.findFirst[title == THIS_IS_A_NEW_BOOK]
		addedBook.assertNotNull
		return addedBook
	}

	def private commandStackListener(CommandStackListener listener) {
		editingDomain.commandStack.addCommandStackListener(listener)
	}

	/**
	 * The listener will get only one event.
	 */
	def private oneTimeCommandStackListener(CommandStackListener listener) {
		val commandStack = editingDomain.commandStack
		val wrapper = new CommandStackListener() {
			var executed = false
			override commandStackChanged(EventObject event) {
				if (!executed) {
					executed = true
					listener.commandStackChanged(event)
				}
			}
		}
		commandStack.addCommandStackListener(wrapper)
	}

	def private mostRecentCommand(EventObject event) {
		EmfCommandsUtil.mostRecentCommand(event)
	}

}