package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.EventObject;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.xmi.DanglingHREFException;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.parsley.edit.action.EditingDomainValidateAction;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.parsley.edit.action.IMenuContributionSpecification;
import org.eclipse.emf.parsley.edit.domain.EditingDomainPresetStrategy;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.util.EmfCommandsUtil;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Injector;

public abstract class AbstractEditingMenuBuilderTest extends AbstractEmfParsleyTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private EditingDomain editingDomain = null;

	private static final String THIS_IS_A_NEW_BOOK = "This is a new book";

	/**
	 * We will use it also for easily testing EMF actions, since there will
	 * be no submenus
	 */
	public static class FlattenedNewChildEditingMenuBuilder extends EditingMenuBuilder {

		@Override
		protected List<IMenuContributionSpecification> defaultEmfMenuContributions(ISelection selection) {
			return createChildActions(selection);
		}

	}

	/**
	 * Provides access to protected action getters
	 */
	public static class TestableEditingMenuBuilder extends EditingMenuBuilder {
		@Override
		public CommandActionHandler getDeleteAction() {
			return super.getDeleteAction();
		}

		@Override
		public CommandActionHandler getCutAction() {
			return super.getCutAction();
		}

		@Override
		public CommandActionHandler getCopyAction() {
			return super.getCopyAction();
		}

		@Override
		public CommandActionHandler getPasteAction() {
			return super.getPasteAction();
		}

		@Override
		public UndoAction getUndoAction() {
			return super.getUndoAction();
		}

		@Override
		public RedoAction getRedoAction() {
			return super.getRedoAction();
		}

		@Override
		public LoadResourceAction createLoadResourceAction() {
			return super.createLoadResourceAction();
		}

		@Override
		public ControlAction createControlAction() {
			return super.createControlAction();
		}

		@Override
		public EditingDomainValidateAction createValidateAction() {
			return super.createValidateAction();
		}

		@Override
		public void updateEditingDomain(Object object) {
			super.updateEditingDomain(object);
		}
	}

	protected abstract Resource createResourceForTest();

	@Before
	public void setEditingDomainToNull() {
		editingDomain = null;
	}

	@Test
	public void testMenuWithNotEObject() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertMenuItemsGivenObject(editingMenuBuilder, "aString",
			"&Undo @Ctrl+Z, &Redo @Ctrl+Y, separator, Cu&t, &Copy, &Paste, separator, &Delete, separator");
	}

	@Test
	public void testDefaultMenu() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertMenuItemsGivenObject(editingMenuBuilder, fixtures.getLibrary(),
			"&Undo @Ctrl+Z, &Redo @Ctrl+Y, separator, Cu&t, &Copy, &Paste, separator, &Delete, separator");
	}

	@Test
	public void testDefaultEmfNewChildMenu() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertEmfMenuItemsGivenObject(editingMenuBuilder, fixtures.getLibrary(),
			"&New Child -> [\n" +
			"\tStock Book, Stock Book On Tape, Stock Video Cassette, Branches Library, Writers Writer, Employees Employee, Borrowers Borrower\n" +
			"]\n" +
			", N&ew Sibling -> [\n" +
			"\t\n" +
			"]\n"
		);
	}

	@Test
	public void testDefaultEmfNewSiblingMenu() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertEmfMenuItemsGivenObject(editingMenuBuilder, fixtures.getWriter(),
			"&New Child -> [\n" +
			"\t\n" +
			"]\n" +
			", N&ew Sibling -> [\n" +
			"\tStock Book, Stock Book On Tape, Stock Video Cassette, Branches Library, Writers Writer, Employees Employee, Borrowers Borrower\n" +
			"]\n"
		);
	}

	@Test
	public void testCustomEmfDefaultMenu() {
		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new FlattenedNewChildEditingMenuBuilder()));
		assertEmfMenuItemsGivenObject(editingMenuBuilder, fixtures.getLibrary(),
			"Stock Book, Stock Book On Tape, Stock Video Cassette, Branches Library, Writers Writer, Employees Employee, Borrowers Borrower"
		);
	}

	@Test
	public void testDefaultEmfMenuWithEmptySelection() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertEmfMenuItemsGivenObject(editingMenuBuilder, createEmptySelection(), "");
	}

	@Test
	public void testEmfCreateActionsWithEmptySelection() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertTrue(editingMenuBuilder.createChildActions(createEmptySelection()).isEmpty());
		assertTrue(editingMenuBuilder.createSiblingActions(createEmptySelection()).isEmpty());
	}

	@Test
	public void testEmfCreateActionsWithNull() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertTrue(editingMenuBuilder.createChildActions(null).isEmpty());
		assertTrue(editingMenuBuilder.createSiblingActions(null).isEmpty());
	}

	@Test
	public void testWithNonStructuredSelection() {
		final var editingMenuBuilder = getAndInitializeEditingMenuBuilder();
		assertMenuItemsGivenObject(editingMenuBuilder, createEmptySelection(),
			"");
	}

	@Test
	public void testCustomMenu() {
		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> menuContributions(Library o) {
					return Arrays.asList(
						actionRedo(),
						actionUndo(),
						separator(),
						actionCopy(),
						actionPaste()
					);
				}

			}));
		assertMenuItemsGivenObject(editingMenuBuilder, fixtures.getLibrary(),
			"&Redo @Ctrl+Y, &Undo @Ctrl+Z, separator, &Copy, &Paste");
	}

	@Test
	public void testCustomDefaultMenu() {
		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				@Override
				protected List<IMenuContributionSpecification> defaultMenuContributions(Object object) {
					return Arrays.asList(
						actionRedo(),
						actionUndo(),
						separator(),
						actionCopy(),
						actionPaste()
					);
				}

			}));
		assertMenuItemsGivenObject(editingMenuBuilder, fixtures.getLibrary(),
			"&Redo @Ctrl+Y, &Undo @Ctrl+Z, separator, &Copy, &Paste");
	}

	@Test
	public void testCustomMenuWithSubmenu() {
		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> menuContributions(Library o) {
					return Arrays.asList(
						actionRedo(),
						actionUndo(),
						separator(),
						submenu("Submenu1", Arrays.asList(
							actionCopy(),
							submenu("Submenu2", Arrays.asList(
								actionCut()
							))
						)),
						actionPaste()
					);
				}

			}));
		assertMenuItemsGivenObject(editingMenuBuilder, fixtures.getLibrary(),
			"&Redo @Ctrl+Y, &Undo @Ctrl+Z, separator, Submenu1 -> [\n" +
			"\t&Copy, Submenu2 -> [\n" +
			"\tCu&t\n" +
			"]\n" +
			"\n" +
			"]\n" +
			", &Paste"
		);
	}

	@Test
	public void testExecuteNewChildMenu() {
		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new FlattenedNewChildEditingMenuBuilder()));
		// before executing there must be no employee
		assertNull(fixtures.getLibrary().getEmployees().isEmpty() ? null : fixtures.getLibrary().getEmployees().get(0));
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, fixtures.getLibrary()),
			"Employees Employee");
		// now there should be one
		assertNotNull(fixtures.getLibrary().getEmployees().get(0));
	}

	@Test
	public void testCustomAddCommand() {
		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Library o) {
					final var newWriter = fixtures.getLibraryFactory().createWriter();
					newWriter.setName("This is a new writer");
					return Arrays.asList(
						actionAdd("New Writer", o.getWriters(), newWriter)
					);
				}

			}));
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, fixtures.getLibrary()),
			"New Writer");
		assertTrue(fixtures.getLibrary().getWriters().stream().anyMatch(w -> "This is a new writer".equals(w.getName())));
	}

	@Test(expected = DanglingHREFException.class)
	public void testCustomAddCommand_Bug466219() throws Exception {
		// this will recreate the context of
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=466219
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);

		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					final var newBook = fixtures.getLibraryFactory().createBook();
					newBook.setTitle(THIS_IS_A_NEW_BOOK);
					newBook.setAuthor(w);
					return Arrays.asList(
						actionAdd("New Book for Writer", ((Library) w.eContainer()).getBooks(), newBook)
					);
				}

			}));
		final var writerForMenu = library.getWriters().get(0);
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"New Book for Writer");
		final var addedBook = getAddedNewBook(library);
		assertSame(writerForMenu, addedBook.getAuthor());

		// retrigger menu creation, this will create a dangling reference
		// the writer will reference a book which is not yet in the resource
		emfMenuManagerForSelection(editingMenuBuilder, writerForMenu);
		// and will never be in the resource

		try {
			resource.save(null);
		} catch (IOWrappedException e) {
			throw (DanglingHREFException) e.getCause();
		}
	}

	@Test
	public void testCustomAddCommandAndInitializer() throws Exception {
		// see also
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=466219
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);

		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					final var newBook = fixtures.getLibraryFactory().createBook();
					newBook.setTitle(THIS_IS_A_NEW_BOOK);
					return Arrays.asList(
						actionAdd("New Book for Writer", ((Library) w.eContainer()).getBooks(), 
							newBook,
							// initialize the added object only after it has
							// been added effectively
							// https://bugs.eclipse.org/bugs/show_bug.cgi?id=466219
							(Book book) -> {
								book.setAuthor(w);
							}
						)
					);
				}

			}));
		final var writerForMenu = library.getWriters().get(0);
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"New Book for Writer");
		final var addedBook = getAddedNewBook(library);
		assertSame(writerForMenu, addedBook.getAuthor());

		// retrigger menu creation
		emfMenuManagerForSelection(editingMenuBuilder, writerForMenu);

		resource.save(null);
	}

	@Test
	public void testAddCommandUndoRedoAffectedObjects() throws Exception {
		// see also
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=476289
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);

		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					final var newBook = fixtures.getLibraryFactory().createBook();
					newBook.setTitle(THIS_IS_A_NEW_BOOK);
					return Arrays.asList(
						actionUndo(),
						actionRedo(),
						actionAdd("Custom New Book",
							((Library) w.eContainer()).getBooks(), 
							newBook)
					);
				}

			}));

		final var writerForMenu = library.getWriters().get(0);

		oneTimeCommandStackListener((EventObject event) -> {
			final var affectedObjects = mostRecentCommand(event).getAffectedObjects();
			assertEquals(1, affectedObjects.size());
			assertSame(getAddedNewBook(library), affectedObjects.iterator().next());
		});

		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"Custom New Book");
		final var addedBook = getAddedNewBook(library);

		oneTimeCommandStackListener((EventObject event) -> {
			// after an undo, the affected object must be the container
			final var affectedObjects = mostRecentCommand(event).getAffectedObjects();
			assertEquals(1, affectedObjects.size());
			assertSame(library, affectedObjects.iterator().next());
		});

		executeUndo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));
		assertNull(addedBook.eContainer());

		oneTimeCommandStackListener((EventObject event) -> {
			// after the redo, the book will be added back,
			// so that must be the affected object
			final var affectedObjects = mostRecentCommand(event).getAffectedObjects();
			assertEquals(1, affectedObjects.size());
			assertSame(getAddedNewBook(library), affectedObjects.iterator().next());
		});

		executeRedo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));
		assertSame(library, addedBook.eContainer());

		resource.save(null);
	}

	@Test
	public void testChangeCommand() throws Exception {
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);
		
		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					return Arrays.asList(
						actionChange("New Book for Writer", w,
							(Writer writer) -> {
								final var newBook = fixtures.getLibraryFactory().createBook();
								newBook.setTitle(THIS_IS_A_NEW_BOOK);
								((Library) w.eContainer()).getBooks().add(newBook);
								newBook.setAuthor(writer);
							}
						)
					);
				}

			}));
		final var writerForMenu = library.getWriters().get(0);
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"New Book for Writer");
		final var addedBook = getAddedNewBook(library);
		assertSame(writerForMenu, addedBook.getAuthor());

		// retrigger menu creation
		emfMenuManagerForSelection(editingMenuBuilder, writerForMenu);

		resource.save(null);
	}

	@Test
	public void testChangeCommandUndoRedoOnSelectedObject() throws Exception {
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);

		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					return Arrays.asList(
						actionUndo(),
						actionRedo(),
						actionChange("New Book for Writer", w,
							(Writer writer) -> {
								final var newBook = fixtures.getLibraryFactory().createBook();
								newBook.setTitle(THIS_IS_A_NEW_BOOK);
								// this change will NOT be recorded, since we track
								// only the specified writer as the element
								((Library) w.eContainer()).getBooks().add(newBook);
								// this change will be recorded
								newBook.setAuthor(writer);
							}
						)
					);
				}

			}));
		final var writerForMenu = library.getWriters().get(0);
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"New Book for Writer");
		final var addedBook = getAddedNewBook(library);
		assertSame(writerForMenu, addedBook.getAuthor());

		executeUndo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		// since we track change only on the writer, the added book is still
		// in the library
		assertSame(library, addedBook.eContainer());
		// but its author is not the writer anymore
		assertNull(addedBook.getAuthor());

		executeRedo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		assertSame(writerForMenu, addedBook.getAuthor());

		resource.save(null);
	}

	@Test
	public void testChangeCommandAffectedObjects() throws Exception {
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);

		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					return Arrays.asList(
						actionUndo(),
						actionRedo(),
						actionChange("New Book for Writer", w,
							(Writer writer) -> {
								final var newBook = fixtures.getLibraryFactory().createBook();
								newBook.setTitle(THIS_IS_A_NEW_BOOK);
								// this change will NOT be recorded, since we track
								// only the specified writer as the element
								((Library) w.eContainer()).getBooks().add(newBook);
								// this change will be recorded
								newBook.setAuthor(writer);
							}
						)
					);
				}

			}));

		commandStackListener((EventObject event) -> {
			final var command = (ChangeCommand) EmfCommandsUtil.mostRecentCommand(event);
			final var affectedObjects = command.getAffectedObjects();
			assertTrue(affectedObjects.contains(library.getWriters().get(0)));
			// the added book is not recorded as an affected object
			assertFalse(affectedObjects.contains(getAddedNewBook(library)));
			assertEquals(1, affectedObjects.size());
		});

		final var writerForMenu = library.getWriters().get(0);
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"New Book for Writer");
		final var addedBook = getAddedNewBook(library);
		assertSame(writerForMenu, addedBook.getAuthor());

		executeUndo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		// since we track change only on the writer, the added book is still
		// in the library
		assertSame(library, addedBook.eContainer());
		// but its author is not the writer anymore
		assertNull(addedBook.getAuthor());

		executeRedo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		assertSame(writerForMenu, addedBook.getAuthor());

		resource.save(null);
	}

	@Test
	public void testChangeCommandUndoRedoOnSelectedObjectContainer() throws Exception {
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);

		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					return Arrays.asList(
						actionUndo(),
						actionRedo(),
						actionChange("New Book for Writer", (Library) w.eContainer(),
							(Library lib) -> {
								final var newBook = fixtures.getLibraryFactory().createBook();
								newBook.setTitle(THIS_IS_A_NEW_BOOK);
								// both changes will be recorded since we specified
								// the library as the element
								lib.getBooks().add(newBook);
								newBook.setAuthor(w);
							}
						)
					);
				}

			}));

		commandStackListener((EventObject event) -> {
			final var command = (ChangeCommand) EmfCommandsUtil.mostRecentCommand(event);
			final var affectedObjects = command.getAffectedObjects();
			assertTrue(affectedObjects.contains(library.getWriters().get(0)));
			// since we get notifications for undo as well, we can't retrieve
			// the book as affected object from the library, since after an undo
			// the book has been removed
			assertNotNull(affectedObjects.stream().filter(it -> it instanceof Book).findFirst().orElse(null));
		});

		final var writerForMenu = library.getWriters().get(0);
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"New Book for Writer");
		final var addedBook = getAddedNewBook(library);
		assertSame(writerForMenu, addedBook.getAuthor());

		executeUndo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		// since we track change on the library, the added book is removed from the library
		assertNotSame(library, addedBook.eContainer());
		// and its author is unset
		assertNull(addedBook.getAuthor());
		// (1) note that the book's title is still set, since that
		// change had not been recorded (see the above menu implementation)
		assertEquals(THIS_IS_A_NEW_BOOK, addedBook.getTitle());

		executeRedo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		assertSame(library, addedBook.eContainer());
		assertSame(writerForMenu, addedBook.getAuthor());

		resource.save(null);
	}

	@Test
	public void testChangeCommandUndoRedoOnSelectedObjectContainer2() throws Exception {
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);

		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					return Arrays.asList(
						actionUndo(),
						actionRedo(),
						actionChange("New Book for Writer", (Library) w.eContainer(),
							(Library lib) -> {
								final var newBook = fixtures.getLibraryFactory().createBook();
								// both changes will be recorded since we specified
								// the library as the element
								lib.getBooks().add(newBook);
								newBook.setAuthor(w);
								newBook.setTitle(THIS_IS_A_NEW_BOOK);
							}
						)
					);
				}

			}));
		final var writerForMenu = library.getWriters().get(0);
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"New Book for Writer");
		final var addedBook = getAddedNewBook(library);
		assertSame(writerForMenu, addedBook.getAuthor());

		executeUndo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		// since we track change on the library, the added book is removed from the library
		assertNotSame(library, addedBook.eContainer());
		// and its author is unset
		assertNull(addedBook.getAuthor());
		// also its title is null, since the title had been set after
		// the book was added to the library, so such change had been recorded
		assertNull(addedBook.getTitle());

		executeRedo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		assertSame(library, addedBook.eContainer());
		assertSame(writerForMenu, addedBook.getAuthor());
		assertEquals(THIS_IS_A_NEW_BOOK, addedBook.getTitle());

		resource.save(null);
	}

	@Test
	public void testChangeCommandUndoRedoOnSelectedObjectResource() throws Exception {
		final var resource = createResourceForTest();
		final var library = (Library) resource.getContents().get(0);

		final var editingMenuBuilder = initializeEditingMenuBuilder(
			injectMembers(new EditingMenuBuilder() {

				protected List<IMenuContributionSpecification> emfMenuContributions(Writer w) {
					return Arrays.asList(
						actionUndo(),
						actionRedo(),
						actionChange("New Book for Writer", w.eResource(),
							(Resource res) -> {
								final var newBook = fixtures.getLibraryFactory().createBook();
								newBook.setTitle(THIS_IS_A_NEW_BOOK);
								final var newLibrary = fixtures.getLibraryFactory().createLibrary();
								newLibrary.setName("A new library");
								// all changes concerning the resource will be recorded
								res.getContents().add(newLibrary);
								newLibrary.getBooks().add(newBook);
								newBook.setAuthor(w);
							}
						)
					);
				}

			}));

		commandStackListener((EventObject event) -> {
			final var command = (ChangeCommand) EmfCommandsUtil.mostRecentCommand(event);
			final var affectedObjects = command.getAffectedObjects();
			assertTrue(affectedObjects.contains(library.getWriters().get(0)));
			// since we get notifications for undo as well, we can't retrieve
			// the book as affected object from the library, since after an undo
			// the book has been removed
			assertNotNull(affectedObjects.stream().filter(it -> it instanceof Book).findFirst().orElse(null));
		});

		final var writerForMenu = library.getWriters().get(0);
		executeAction(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu),
			"New Book for Writer");

		// the book's library is not the original one
		final var addedLibrary = (Library) resource.getContents().get(resource.getContents().size() - 1);
		final var addedBook = getAddedNewBook(addedLibrary);
		assertSame(writerForMenu, addedBook.getAuthor());

		executeUndo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		// since we track change on the resource, the added book is removed from the library
		assertNotSame(library, addedBook.eContainer());
		// the added library is removed from the resource
		assertNotSame(resource, addedLibrary.eResource());
		// and its author is unset
		assertNull(addedBook.getAuthor());
		// see (1) for the reason why the book still has its title
		assertEquals(THIS_IS_A_NEW_BOOK, addedBook.getTitle());
		assertEquals("A new library", addedLibrary.getName());

		executeRedo(emfMenuManagerForSelection(editingMenuBuilder, writerForMenu));

		assertSame(addedLibrary, addedBook.eContainer());
		assertSame(resource, addedLibrary.eResource());
		assertSame(writerForMenu, addedBook.getAuthor());

		resource.save(null);
	}

	@Test
	public void testActionsAreCreatedByCreateActions() {
		final var builder = createTestableEditingMenuBuilder();
		collectActions(builder).forEach(action -> assertNotNull(action));
	}

	@Test
	public void testSafeActionUpdate() {
		final var builder = createTestableEditingMenuBuilder();
		builder.updateEditingDomain(getEditingDomain());
		builder.updateUndoRedo();
	}

	@Test
	public void testCreateOtherNonDefaultActions() {
		final var builder = createTestableEditingMenuBuilder();
		assertNotNull(builder.createLoadResourceAction());
		assertNotNull(builder.createControlAction());
		assertNotNull(builder.createValidateAction());
	}

	protected EditingDomain getEditingDomain() {
		if (editingDomain == null) {
			editingDomain = getOrCreateInjector().getProvider(EditingDomain.class).get();
		}
		return editingDomain;
	}

	protected EditingMenuBuilder getAndInitializeEditingMenuBuilder() {
		return getAndInitializeEditingMenuBuilder(getOrCreateInjector());
	}

	private EditingMenuBuilder getAndInitializeEditingMenuBuilder(Injector injector) {
		final var emb = injector.getInstance(EditingMenuBuilder.class);
		emb.createActions();
		setEditingDomain(emb, getEditingDomain());
		return emb;
	}

	private EditingMenuBuilder initializeEditingMenuBuilder(EditingMenuBuilder editingMenuBuilder) {
		editingMenuBuilder.createActions();
		setEditingDomain(editingMenuBuilder, getEditingDomain());
		return editingMenuBuilder;
	}

	private TestableEditingMenuBuilder createTestableEditingMenuBuilder() {
		final var builder = injectMembers(new TestableEditingMenuBuilder());
		initializeEditingMenuBuilder(builder);
		return builder;
	}

	private void setEditingDomain(EditingMenuBuilder editingMenuBuilder, EditingDomain editingDomain) {
		if (editingDomain != null) {
			final var strategy = new EditingDomainPresetStrategy();
			strategy.setEditingDomain(editingDomain);
			editingMenuBuilder.setEditingDomainFinderStrategy(strategy);
		}
	}

	private MenuManager createMenuManager() {
		final var menuManager = new MenuManager("#PopUp");
		menuManager.setRemoveAllWhenShown(true);
		return menuManager;
	}

	private ISelection createSelection(Object o) {
		return new StructuredSelection(EmfParsleyUtil.ensureCollection(o).toArray());
	}

	private ISelection createEmptySelection() {
		return new ISelection() {
			@Override
			public boolean isEmpty() {
				return true;
			}
		};
	}

	protected void assertMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, Object o, CharSequence expectedRepresentation) {
		assertMenuItemsGivenObject(editingMenuBuilder, createSelection(o), expectedRepresentation);
	}

	private void assertMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, ISelection sel, CharSequence expectedRepresentation) {
		final var menuManager = createMenuManager();
		editingMenuBuilder.updateSelection(sel);
		editingMenuBuilder.menuAboutToShow(menuManager);
		
		assertEquals(expectedRepresentation.toString(),
			Arrays.stream(menuManager.getItems()).map(this::menuItemToStringRepresentation).collect(Collectors.joining(", ")));
	}

	private void assertEmfMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, EObject o, CharSequence expectedRepresentation) {
		assertEmfMenuItemsGivenObject(editingMenuBuilder, createSelection(o), expectedRepresentation);
	}

	private void assertEmfMenuItemsGivenObject(EditingMenuBuilder editingMenuBuilder, ISelection sel, CharSequence expectedRepresentation) {
		final var menuManager = emfMenuManagerForSelection(editingMenuBuilder, sel);
		
		assertEquals(expectedRepresentation.toString(), menuItemsToStringRepresentation(menuManager));
	}

	private MenuManager emfMenuManagerForSelection(EditingMenuBuilder editingMenuBuilder, EObject o) {
		return emfMenuManagerForSelection(editingMenuBuilder, createSelection(o));
	}

	private MenuManager emfMenuManagerForSelection(EditingMenuBuilder editingMenuBuilder, ISelection sel) {
		final var menuManager = createMenuManager();
		editingMenuBuilder.updateSelection(sel);
		editingMenuBuilder.emfMenuAboutToShow(menuManager);
		return menuManager;
	}

	private void executeAction(MenuManager menuManager, String actionText) {
		final var item = Arrays.stream(menuManager.getItems())
			.filter(ActionContributionItem.class::isInstance)
			.map(ActionContributionItem.class::cast)
			.filter(i -> actionText.equals(i.getAction().getText()))
			.findFirst()
			.orElse(null);
		assertTrue(
			"Could not find " + actionText + " in " + menuManager.getMenu(),
			item != null
		);
		item.getAction().run();
	}

	private void executeUndo(MenuManager menuManager) {
		executeAction(menuManager, "&Undo @Ctrl+Z");
	}

	private void executeRedo(MenuManager menuManager) {
		executeAction(menuManager, "&Redo @Ctrl+Y");
	}

	private String menuItemsToStringRepresentation(MenuManager menuManager) {
		return Arrays.stream(menuManager.getItems()).map(this::menuItemToStringRepresentation).collect(Collectors.joining(", "));
	}

	private String menuItemToStringRepresentation(IContributionItem item) {
		if (item instanceof Separator) {
			return "separator";
		} else if (item instanceof ActionContributionItem) {
			return ((ActionContributionItem) item).getAction().getText();
		} else if (item instanceof MenuManager) {
			final var mm = (MenuManager) item;
			return mm.getMenuText() + " -> [\n" +
				"\t" + menuItemsToStringRepresentation(mm) + "\n" +
				"]\n";
		} else {
			return "unknown";
		}
	}

	private Book getAddedNewBook(Library library) {
		final var addedBook = library.getBooks().stream()
			.filter(b -> THIS_IS_A_NEW_BOOK.equals(b.getTitle()))
			.findFirst()
			.orElse(null);
		assertNotNull(addedBook);
		return addedBook;
	}

	protected void commandStackListener(CommandStackListener listener) {
		commandStackListener(listener, editingDomain);
	}

	/**
	 * The listener will get only one event.
	 */
	protected void oneTimeCommandStackListener(CommandStackListener listener) {
		oneTimeCommandStackListener(listener, editingDomain);
	}

	protected void commandStackListener(CommandStackListener listener, EditingDomain editingDomain) {
		editingDomain.getCommandStack().addCommandStackListener(listener);
	}

	/**
	 * The listener will get only one event.
	 */
	protected void oneTimeCommandStackListener(CommandStackListener listener, EditingDomain editingDomain) {
		final var commandStack = editingDomain.getCommandStack();
		final var wrapper = new CommandStackListener() {
			private boolean executed = false;
			@Override
			public void commandStackChanged(EventObject event) {
				if (!executed) {
					executed = true;
					listener.commandStackChanged(event);
				}
			}
		};
		commandStack.addCommandStackListener(wrapper);
	}

	private Command mostRecentCommand(EventObject event) {
		return EmfCommandsUtil.mostRecentCommand(event);
	}

	private List<org.eclipse.jface.action.Action> collectActions(TestableEditingMenuBuilder e) {
		return Arrays.asList(
			e.getDeleteAction(),
			e.getCutAction(),
			e.getCopyAction(),
			e.getPasteAction(),
			e.getUndoAction(),
			e.getRedoAction()
		);
	}

}
