package org.eclipse.emf.parsley.tests;

import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.tests.EmfComponentsAbstractTests;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
@SuppressWarnings("all")
public class EmfComponentsDslWizardsTests extends EmfComponentsAbstractTests {
  private final String TEST_PROJ_NAME = "my.emfparsley.proj";
  
  private final String TREE_FORM_TEMPLATE = "Saveable Tree Form View";
  
  private final String TREE_TEMPLATE = "Saveable Tree View";
  
  private final String TABLE_FORM_TEMPLATE = "Saveable Table Form View";
  
  private final String TABLE_TEMPLATE = "Saveable Table View";
  
  private final String ONSELECTION_TREE_TEMPLATE = "On selection Tree Form View";
  
  private final String ONSELECTION_TABLE_TEMPLATE = "On selection Table Form View";
  
  @Test
  public void canCreateDslProjectWithWizard() {
    try {
      this.createProjectWithoutTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME);
      this.assertNoErrorsInProjectAfterAutoBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void canCreateDslProjectWithWizardAndTreeFormTemplate() {
    try {
      this.createProjectWithTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME, this.TREE_FORM_TEMPLATE);
      this.assertNoErrorsInProjectAfterAutoBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void canCreateDslProjectWithWizardAndTreeTemplate() {
    try {
      this.createProjectWithTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME, this.TREE_TEMPLATE);
      this.assertNoErrorsInProjectAfterAutoBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void canCreateDslProjectWithWizardAndTableFormTemplate() {
    try {
      this.createProjectWithTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME, this.TABLE_FORM_TEMPLATE);
      this.assertNoErrorsInProjectAfterAutoBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void canCreateDslProjectWithWizardAndTableTemplate() {
    try {
      this.createProjectWithTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME, this.TABLE_TEMPLATE);
      this.assertNoErrorsInProjectAfterAutoBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void canCreateDslProjectWithWizardAndOnselectionTreeTemplate() {
    try {
      this.createProjectWithTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME, this.ONSELECTION_TREE_TEMPLATE);
      this.assertNoErrorsInProjectAfterAutoBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void canCreateDslProjectWithWizardAndOnselectionTableTemplate() {
    try {
      this.createProjectWithTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME, this.ONSELECTION_TABLE_TEMPLATE);
      this.assertNoErrorsInProjectAfterAutoBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkPluginXmlGen() {
    try {
      this.createProjectWithoutTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME);
      this.assertNoErrorsInProjectAfterAutoBuild();
      final SWTBotEditor editor = EmfComponentsAbstractTests.bot.editorByTitle("module.parsley");
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("module ");
      _builder.append(this.TEST_PROJ_NAME, "");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// parts should trigger generation of ");
      _builder.append(EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("parts {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("viewpart id {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("viewname \"View Name\"");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("viewclass org.eclipse.emf.parsley.views.AbstractSaveableTreeView");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("// viewcategory my.category");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.setEditorContentsSaveAndWaitForAutoBuild(editor, _builder);
      final SWTBotTreeItem projTree = this.getProjectTreeItem(this.TEST_PROJ_NAME);
      SWTBotTreeItem _expand = projTree.expand();
      _expand.getNode(EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("module ");
      _builder_1.append(this.TEST_PROJ_NAME, "");
      _builder_1.append(" {");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t");
      _builder_1.append("// removed parts");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("// ");
      _builder_1.append(EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN, "	");
      _builder_1.append(" should be removed");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("}");
      _builder_1.newLine();
      this.setEditorContentsSaveAndWaitForAutoBuild(editor, _builder_1);
      String _plus = (EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN + " still present!");
      List<String> _nodes = projTree.getNodes();
      final Function1<String,Boolean> _function = new Function1<String,Boolean>() {
        public Boolean apply(final String it) {
          boolean _notEquals = (!Objects.equal(it, EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN));
          return Boolean.valueOf(_notEquals);
        }
      };
      boolean _forall = IterableExtensions.<String>forall(_nodes, _function);
      Assert.assertTrue(_plus, _forall);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void checkTemplateProposalForViewSpecification() {
    try {
      this.createProjectWithoutTemplateInWorkspace(EmfComponentsAbstractTests.EMF_PARSLEY_CATEGORY, 
        EmfComponentsAbstractTests.NEW_EMF_COMPONENTS_DSL_PROJECT, this.TEST_PROJ_NAME);
      this.assertNoErrorsInProjectAfterAutoBuild();
      final SWTBotEditor editor = EmfComponentsAbstractTests.bot.editorByTitle("module.parsley");
      this.setEditorContentsSaveAndWaitForAutoBuild(editor, 
        "", false);
      SWTBotEclipseEditor _textEditor = editor.toTextEditor();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("module ");
      _builder.append(this.TEST_PROJ_NAME, "");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("parts { ");
      _builder.newLine();
      _textEditor.insertText(_builder.toString());
      SWTBotEclipseEditor _textEditor_1 = editor.toTextEditor();
      _textEditor_1.navigateTo(2, 10);
      SWTBotEclipseEditor _textEditor_2 = editor.toTextEditor();
      _textEditor_2.autoCompleteProposal(" ", 
        "ViewSpecification - Template for ViewSpecification");
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("module my.emfparsley.proj {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("parts { ");
      _builder_1.newLine();
      _builder_1.append(" ");
      _builder_1.append("viewpart id {");
      _builder_1.newLine();
      _builder_1.append(" \t");
      _builder_1.append("viewname \"View Name\"");
      _builder_1.newLine();
      _builder_1.append(" \t");
      _builder_1.append("viewclass type");
      _builder_1.newLine();
      _builder_1.append(" \t");
      _builder_1.append("// viewcategory my.category");
      _builder_1.newLine();
      _builder_1.append(" ");
      _builder_1.append("}");
      String _string = _builder_1.toString();
      SWTBotEclipseEditor _textEditor_3 = editor.toTextEditor();
      String _text = _textEditor_3.getText();
      Assert.assertEquals(_string, _text);
      editor.saveAndClose();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
