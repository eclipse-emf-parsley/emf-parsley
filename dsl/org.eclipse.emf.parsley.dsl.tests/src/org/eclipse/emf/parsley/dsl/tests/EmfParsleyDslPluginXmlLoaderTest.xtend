package org.eclipse.emf.parsley.dsl.tests

import java.util.List
import org.eclipse.emf.parsley.dsl.pluginxml.PluginXmlLoader
import org.eclipse.pde.internal.core.text.plugin.PluginAttribute
import org.eclipse.pde.internal.core.text.plugin.PluginElementNode
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.xtend2.lib.StringConcatenation
import org.eclipse.emf.parsley.dsl.pluginxml.PluginXmlUtils

class EmfParsleyDslPluginXmlLoaderTest {

	new() {
		// to avoid missed code coverage for the protected constructor
		new PluginXmlUtils() {
			
		}
	}

	val EDITOR_POINT = "org.eclipse.ui.editors"
	val VIEW_POINT = "org.eclipse.ui.views"

	val s1 = '''
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
'''

	val singleView = '''
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
'''

	val singleView2 = '''
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
'''

	val singleEditor = '''
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
	'''

	val emptyPlugin = '''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
'''

	val emptyView = '''
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
'''

	@Test
	def void testLoad() {
		val extension loader = load(s1)
		val extensions = loader.extensionNodes
		'''
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
   </extension>'''.toString.assertEquals(extensions.head.toString)
		val elements = loader.extensionElements
		'''
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
		'''.toString.
		assertEquals(elements.repr(loader))
	}

	@Test
	def void testComputedOnlyTheFirstTime() {
		val loader = load(s1)
		loader.extensionNodes.assertSame(loader.extensionNodes)
		loader.extensionElements.assertSame(loader.extensionElements)
	}

	@Test
	def void testWrite() {
		val loader = load(s1)
		s1.assertEquals(loader.contentsAsString)
	}

	@Test
	def void testGetExtensionByPoint() {
		val loader = load(singleView)
		loader.getExtensionByPoint(EDITOR_POINT).assertNull
		loader.getExtensionByPoint(VIEW_POINT).assertNotNull
	}

	@Test
	def void testGetElementExtension() {
		val loader = load(s1)
		val elements = loader.extensionElements
		VIEW_POINT.assertEquals(loader.getElementExtension(elements.head))
		EDITOR_POINT.assertEquals(loader.getElementExtension(elements.last))
	}

	@Test
	def void testElementByTagAndId() {
		val loader = load(s1)
		loader.
			getElementByTagAndId("view", "org.eclipse.emf.parsley.tests.treeviews").
			assertSame(loader.extensionElements.get(1))
	}

	@Test
	def void testElementByTagAndIdNotFound() {
		val loader = load(
			'''
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
			'''
		)
		loader.
			getElementByTagAndId("view", "foo").
			assertNull
	}

	@Test
	def void testInsertExtension() {
		val loader = load(singleView)
		loader.insertExtension(EDITOR_POINT)
		'''
<extension
      point="org.eclipse.ui.editors">
</extension>'''.toString.
		assertEquals(loader.extensionNodes.last.toString)
	}

	@Test
	def void testInsertExtensionInEmptyPlugin() {
		val loader = load(emptyPlugin)
		loader.insertExtension(EDITOR_POINT)
		'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.editors">
   </extension>
</plugin>
		'''.toString.
		assertEquals(loader.contentsAsString)
	}

	@Test
	def void testInsertExtensionInEmptyPluginFile() {
		val loader = load("")
		loader.insertExtension(EDITOR_POINT)
		'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.editors">
   </extension>
</plugin>
		'''.toString.
		assertEquals(loader.contentsAsString)
	}

	@Test
	def void testCopySourceIntoTarget() {
		val source = load(singleView)
		val sourceView = source.extensionElements.head
		val target = load(singleView2)
		val targetView = target.extensionElements.head
		target.copy(sourceView, targetView)
		sourceView.toString.assertEquals(targetView.toString)
	}

	@Test
	def void testCopySourceIntoEmptyView() {
		val source = load(singleView)
		val sourceView = source.extensionElements.head
		val target = load(emptyView)
		val targetView = target.extensionElements.head
		target.copy(sourceView, targetView)
		sourceView.toString.assertEquals(targetView.toString)
	}

	@Test
	def void testCopyIntoExistingView() {
		val source = load(singleView)
		val sourceView = source.extensionElements.head
		val target = load(singleView2)
		target.copy(sourceView)
		sourceView.toString.assertEquals(target.extensionElements.head.toString)
	}

	@Test
	def void testCopyIntoEmptyPlugin() {
		val source = load(singleView)
		val sourceView = source.extensionElements.head
		val target = load(emptyPlugin)
		target.copy(sourceView)
		source.contentsAsString.assertEquals(target.contentsAsString)
	}

	@Test
	def void testCopyIntoEmptyPluginFile() {
		val source = load(singleView)
		val sourceView = source.extensionElements.head
		val target = load("")
		target.copy(sourceView)
		source.contentsAsString.assertEquals(target.contentsAsString)
	}

	@Test
	def void testCopyFromPluginFileIntoExistingOne() {
		val target = load(singleView2)
		target.copyFromPluginXml(singleView)
		// note that the string contents are different, but only for
		// indentation
		'''
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
		'''.toString.assertEquals(target.contentsAsString)
	}

	@Test
	def void testCopyFromPluginFileIntoEmptyPlugin() {
		val target = load(emptyPlugin)
		target.copyFromPluginXml(s1)
		s1.toString.assertEquals(target.contentsAsString)
	}

	@Test
	def void testCopyFromPluginFileIntoEmptyPluginFile() {
		val target = load("")
		target.copyFromPluginXml(s1)
		s1.toString.assertEquals(target.contentsAsString)
	}

	@Test
	def void testCopyFromPluginFileIntoExtensionWithExistingChild() {
		// the editor already has the contentTypeBinding child
		val target = load('''
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
	</plugin>''')
		target.copyFromPluginXml(singleEditor)
		singleEditor.toString.assertEquals(target.contentsAsString)
	}

	@Test
	def void testCopyFromPluginFileIntoExtensionWithoutExistingChild() {
		// the editor does not have the contentTypeBinding child
		val target = load('''
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
	</plugin>''')
		target.copyFromPluginXml(singleEditor)
		singleEditor.toString.assertEquals(target.contentsAsString)
	}

	@Test
	def void testInsertExtensionElement() {
		val loader = load(singleView)
		loader.insertExtensionElement(VIEW_POINT, "view")
		'''<view></view>'''.toString.
		assertEquals(loader.extensionElements.last.toString)
	}

	@Test
	def void testInsertExtensionElementInEmptyPlugin() {
		val loader = load(emptyPlugin)
		loader.insertExtensionElement(VIEW_POINT, "view")
		'''<view></view>'''.toString.
		assertEquals(loader.extensionElements.last.toString)
	}

	@Test
	def void testModification() {
		val loader = load(singleView)
		val e = loader.getExtensionElements().head

		'''
<view
             category="org.eclipse.emf.parsley"
             class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
             id="viewId"
             name="Test Model Tree Form View"
             restorable="true">
       </view>'''.toString.assertEquals(e.toString)

		e.setAttribute("category", "modified-category")
		e.setAttribute("class", "modified-class")

		'''
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
		'''.toString.assertEquals(loader.contentsAsString)
	}

	def CharSequence repr(List<PluginElementNode> nodes, PluginXmlLoader loader) {
		nodes.map[repr(loader)].join(StringConcatenation.DEFAULT_LINE_DELIMITER)
	}

	def repr(PluginElementNode node, PluginXmlLoader loader) {
		val entrySet = loader.getPluginAttributesEntrySet(node)
		val extensionChildren = loader.getExtensionChildren(node)
		'''
		«node»
		tag: «node.XMLTagName»
		«entrySet.map[
			key + "=" + (value as PluginAttribute).value
		].join(", ")»
		«if (!extensionChildren.empty) {
			extensionChildren.repr(loader)
		}»
		'''
	}

	def private load(String s) {
		new PluginXmlLoader(s) => [
			load
		]
	}

}