package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.parsley.dsl.pluginxml.PluginXmlLoader;
import org.eclipse.pde.internal.core.text.plugin.PluginAttribute;
import org.eclipse.pde.internal.core.text.plugin.PluginElementNode;
import org.junit.Test;

@SuppressWarnings("restriction")
public class EmfParsleyDslPluginXmlLoaderTest {
	private static final String EDITOR_POINT = "org.eclipse.ui.editors";
	private static final String VIEW_POINT = "org.eclipse.ui.views";

	private static final String s1 = """
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
      <view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
            id="org.eclipse.emf.parsley.tests.views"
            name="Test Model Tree Form View"
            restorable="true">
      </view>
      <view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeView"
            id="org.eclipse.emf.parsley.tests.treeviews"
            name="Test Model Tree View"
            restorable="true">
      </view>
   </extension>
   <extension
         point="org.eclipse.ui.editors">
      <editor
            class="org.eclipse.emf.parsley.tests.factories.OpenDialogMouseEventExecutableExtensionFactory:org.eclipse.emf.parsley.editors.EmfTreeEditor"
            contributorClass="org.eclipse.emf.parsley.tests.factories.OpenDialogMouseEventExecutableExtensionFactory:org.eclipse.emf.parsley.editors.EmfParsleyEditorActionBarContributor"
            default="false"
            id="org.eclipse.emf.parsley.openDialogOnDoubleClick"
            name="EMF Tree Editor Opening Dialog">
         <contentTypeBinding
               contentTypeId="org.eclipse.emf.ecore.xmi">
         </contentTypeBinding>
      </editor>
   </extension>
</plugin>
""";

	private static final String singleView = """
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
       <view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
            id="viewId"
            name="Test Model Tree Form View"
            restorable="true">
     </view>
   </extension>
</plugin>
""";

	private static final String singleView2 = """
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
       <view
            category="org.eclipse.emf.parsley"
            class="MyClass"
            id="viewId"
            name="My View">
     </view>
   </extension>
</plugin>
""";

	private static final String singleEditor = """
	<?xml version="1.0" encoding="UTF-8"?>
	<?eclipse version="3.4"?>
	<plugin>
	   <extension
	         point="org.eclipse.ui.editors">
	      <editor
	            class="EditorClass"
	            contributorClass="ContributorClass"
	            default="false"
	            id="editorId"
	            name="EMF Tree Editor">
	         <contentTypeBinding
	               contentTypeId="myType">
	         </contentTypeBinding>
	      </editor>
	   </extension>
	</plugin>
	""";

	private static final String emptyPlugin = """
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
""";

	private static final String emptyView = """
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
       <view
            >
     </view>
   </extension>
</plugin>
""";

	@Test
	public void testLoad() throws Exception {
		var loader = load(s1);
		var extensions = loader.getExtensionNodes();
		assertEquals("""
<extension
         point="org.eclipse.ui.views">
      <view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
            id="org.eclipse.emf.parsley.tests.views"
            name="Test Model Tree Form View"
            restorable="true">
      </view>
      <view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeView"
            id="org.eclipse.emf.parsley.tests.treeviews"
            name="Test Model Tree View"
            restorable="true">
      </view>
   </extension>""", extensions.get(0).toString());
		var elements = loader.getExtensionElements();
		assertEquals("""
<view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
            id="org.eclipse.emf.parsley.tests.views"
            name="Test Model Tree Form View"
            restorable="true">
      </view>
tag: view
category=org.eclipse.emf.parsley, class=org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView, id=org.eclipse.emf.parsley.tests.views, name=Test Model Tree Form View, restorable=true

<view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeView"
            id="org.eclipse.emf.parsley.tests.treeviews"
            name="Test Model Tree View"
            restorable="true">
      </view>
tag: view
category=org.eclipse.emf.parsley, class=org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeView, id=org.eclipse.emf.parsley.tests.treeviews, name=Test Model Tree View, restorable=true

<editor
            class="org.eclipse.emf.parsley.tests.factories.OpenDialogMouseEventExecutableExtensionFactory:org.eclipse.emf.parsley.editors.EmfTreeEditor"
            contributorClass="org.eclipse.emf.parsley.tests.factories.OpenDialogMouseEventExecutableExtensionFactory:org.eclipse.emf.parsley.editors.EmfParsleyEditorActionBarContributor"
            default="false"
            id="org.eclipse.emf.parsley.openDialogOnDoubleClick"
            name="EMF Tree Editor Opening Dialog">
         <contentTypeBinding
               contentTypeId="org.eclipse.emf.ecore.xmi">
         </contentTypeBinding>
      </editor>
tag: editor
class=org.eclipse.emf.parsley.tests.factories.OpenDialogMouseEventExecutableExtensionFactory:org.eclipse.emf.parsley.editors.EmfTreeEditor, contributorClass=org.eclipse.emf.parsley.tests.factories.OpenDialogMouseEventExecutableExtensionFactory:org.eclipse.emf.parsley.editors.EmfParsleyEditorActionBarContributor, default=false, id=org.eclipse.emf.parsley.openDialogOnDoubleClick, name=EMF Tree Editor Opening Dialog
<contentTypeBinding
               contentTypeId="org.eclipse.emf.ecore.xmi">
         </contentTypeBinding>
tag: contentTypeBinding
contentTypeId=org.eclipse.emf.ecore.xmi
		""", repr(elements, loader));
	}

	@Test
	public void testComputedOnlyTheFirstTime() throws Exception {
		var loader = load(s1);
		assertSame(loader.getExtensionNodes(), loader.getExtensionNodes());
		assertSame(loader.getExtensionElements(), loader.getExtensionElements());
	}

	@Test
	public void testWrite() throws Exception {
		var loader = load(s1);
		assertEquals(s1, loader.getContentsAsString());
	}

	@Test
	public void testGetExtensionByPoint() throws Exception {
		var loader = load(singleView);
		assertNull(loader.getExtensionByPoint(EDITOR_POINT));
		assertNotNull(loader.getExtensionByPoint(VIEW_POINT));
	}

	@Test
	public void testGetElementExtension() throws Exception {
		var loader = load(s1);
		var elements = loader.getExtensionElements();
		assertEquals(VIEW_POINT, loader.getElementExtension(elements.get(0)));
		assertEquals(EDITOR_POINT, loader.getElementExtension(elements.get(elements.size() - 1)));
	}

	@Test
	public void testElementByTagAndId() throws Exception {
		var loader = load(s1);
		assertSame(loader.getElementByTagAndId("view", "org.eclipse.emf.parsley.tests.treeviews"),
			loader.getExtensionElements().get(1));
	}

	@Test
	public void testElementByTagAndIdNotFound() throws Exception {
		var loader = load("""
			<?xml version="1.0" encoding="UTF-8"?>
			<?eclipse version="3.4"?>
			<plugin>
			   <extension
			         point="org.eclipse.ui.views">
			       <view
			            category="org.eclipse.emf.parsley"
			            name="Test Model Tree Form View"
			            restorable="true">
			     </view>
			   </extension>
			</plugin>
			""");
		assertNull(loader.getElementByTagAndId("view", "foo"));
	}

	@Test
	public void testInsertExtension() throws Exception {
		var loader = load(singleView);
		loader.insertExtension(EDITOR_POINT);
		assertEquals("""
<extension
      point="org.eclipse.ui.editors">
</extension>""",
			loader.getExtensionNodes().get(loader.getExtensionNodes().size() - 1).toString());
	}

	@Test
	public void testInsertExtensionInEmptyPlugin() throws Exception {
		var loader = load(emptyPlugin);
		loader.insertExtension(EDITOR_POINT);
		assertEquals("""
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.editors">
   </extension>
</plugin>
		""", loader.getContentsAsString());
	}

	@Test
	public void testInsertExtensionInEmptyPluginFile() throws Exception {
		var loader = load("");
		loader.insertExtension(EDITOR_POINT);
		assertEquals("""
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.editors">
   </extension>
</plugin>
		""", loader.getContentsAsString());
	}

	@Test
	public void testCopySourceIntoTarget() throws Exception {
		var source = load(singleView);
		var sourceView = source.getExtensionElements().get(0);
		var target = load(singleView2);
		var targetView = target.getExtensionElements().get(0);
		target.copy(sourceView, targetView);
		assertEquals(sourceView.toString(), targetView.toString());
	}

	@Test
	public void testCopySourceIntoEmptyView() throws Exception {
		var source = load(singleView);
		var sourceView = source.getExtensionElements().get(0);
		var target = load(emptyView);
		var targetView = target.getExtensionElements().get(0);
		target.copy(sourceView, targetView);
		assertEquals(sourceView.toString(), targetView.toString());
	}

	@Test
	public void testCopyIntoExistingView() throws Exception {
		var source = load(singleView);
		var sourceView = source.getExtensionElements().get(0);
		var target = load(singleView2);
		target.copy(sourceView);
		assertEquals(sourceView.toString(), target.getExtensionElements().get(0).toString());
	}

	@Test
	public void testCopyIntoEmptyPlugin() throws Exception {
		var source = load(singleView);
		var sourceView = source.getExtensionElements().get(0);
		var target = load(emptyPlugin);
		target.copy(sourceView);
		assertEquals(source.getContentsAsString(), target.getContentsAsString());
	}

	@Test
	public void testCopyIntoEmptyPluginFile() throws Exception {
		var source = load(singleView);
		var sourceView = source.getExtensionElements().get(0);
		var target = load("");
		target.copy(sourceView);
		assertEquals(source.getContentsAsString(), target.getContentsAsString());
	}

	@Test
	public void testCopyFromPluginFileIntoExistingOne() throws Exception {
		var target = load(singleView2);
		target.copyFromPluginXml(singleView);
		// note that the string contents are different, but only for
		// indentation
		assertEquals("""
		<?xml version="1.0" encoding="UTF-8"?>
		<?eclipse version="3.4"?>
		<plugin>
		   <extension
		         point="org.eclipse.ui.views">
		      <view
		            category="org.eclipse.emf.parsley"
		            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
		            id="viewId"
		            name="Test Model Tree Form View"
		            restorable="true">
		      </view>
		   </extension>
		</plugin>
		""", target.getContentsAsString());
	}

	@Test
	public void testCopyFromPluginFileIntoEmptyPlugin() throws Exception {
		var target = load(emptyPlugin);
		target.copyFromPluginXml(s1);
		assertEquals(s1, target.getContentsAsString());
	}

	@Test
	public void testCopyFromPluginFileIntoEmptyPluginFile() throws Exception {
		var target = load("");
		target.copyFromPluginXml(s1);
		assertEquals(s1, target.getContentsAsString());
	}

	@Test
	public void testCopyFromPluginFileIntoExtensionWithExistingChild() throws Exception {
		// the editor already has the contentTypeBinding child
		var target = load("""
	<?xml version="1.0" encoding="UTF-8"?>
	<?eclipse version="3.4"?>
	<plugin>
	   <extension
	         point="org.eclipse.ui.editors">
	      <editor
	            class="EditorClass"
	            contributorClass="ContributorClass"
	            default="false"
	            id="editorId"
	            name="EMF Tree Editor">
	         <contentTypeBinding
	               contentTypeId="myDifferentType">
	         </contentTypeBinding>
	      </editor>
	   </extension>
	</plugin>""");
		target.copyFromPluginXml(singleEditor);
		assertEquals(singleEditor, target.getContentsAsString());
	}

	@Test
	public void testCopyFromPluginFileIntoExtensionWithoutExistingChild() throws Exception {
		// the editor does not have the contentTypeBinding child
		var target = load("""
	<?xml version="1.0" encoding="UTF-8"?>
	<?eclipse version="3.4"?>
	<plugin>
	   <extension
	         point="org.eclipse.ui.editors">
	      <editor
	            class="EditorClass"
	            contributorClass="ContributorClass"
	            default="false"
	            id="editorId"
	            name="EMF Tree Editor">
	      </editor>
	   </extension>
	</plugin>""");
		target.copyFromPluginXml(singleEditor);
		assertEquals(singleEditor, target.getContentsAsString());
	}

	@Test
	public void testInsertExtensionElement() throws Exception {
		var loader = load(singleView);
		loader.insertExtensionElement(VIEW_POINT, "view");
		assertEquals("<view></view>",
			loader.getExtensionElements().get(loader.getExtensionElements().size() - 1).toString());
	}

	@Test
	public void testInsertExtensionElementInEmptyPlugin() throws Exception {
		var loader = load(emptyPlugin);
		loader.insertExtensionElement(VIEW_POINT, "view");
		assertEquals("<view></view>",
			loader.getExtensionElements().get(loader.getExtensionElements().size() - 1).toString());
	}

	@Test
	public void testModification() throws Exception {
		var loader = load(singleView);
		var e = loader.getExtensionElements().get(0);

		assertEquals("""
<view
             category="org.eclipse.emf.parsley"
             class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
             id="viewId"
             name="Test Model Tree Form View"
             restorable="true">
       </view>""", e.toString());

		e.setAttribute("category", "modified-category");
		e.setAttribute("class", "modified-class");

		assertEquals("""
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
      <view
            category="modified-category"
            class="modified-class"
            id="viewId"
            name="Test Model Tree Form View"
            restorable="true">
      </view>
   </extension>
</plugin>
		""", loader.getContentsAsString());
	}

	private String repr(List<PluginElementNode> nodes, PluginXmlLoader loader) {
		return nodes.stream()
			.map(node -> repr(node, loader))
			.collect(Collectors.joining(System.lineSeparator()));
	}

	private String repr(PluginElementNode node, PluginXmlLoader loader) {
		var entrySet = loader.getPluginAttributesEntrySet(node);
		var extensionChildren = loader.getExtensionChildren(node);
		var childrenRepr = extensionChildren.isEmpty() ? "" : repr(extensionChildren, loader);
		return node.toString() + "\n" +
			"tag: " + node.getXMLTagName() + "\n" +
			StreamSupport.stream(entrySet.spliterator(), false)
				.map(e -> e.getKey() + "=" + ((PluginAttribute) e.getValue()).getValue())
				.collect(Collectors.joining(", ")) + "\n" +
			childrenRepr;
	}

	private PluginXmlLoader load(String s) throws Exception {
		var loader = new PluginXmlLoader(s);
		loader.load();
		return loader;
	}

}
