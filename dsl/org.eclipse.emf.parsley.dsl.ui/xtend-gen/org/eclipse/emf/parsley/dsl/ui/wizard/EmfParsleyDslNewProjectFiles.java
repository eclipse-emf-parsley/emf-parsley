package org.eclipse.emf.parsley.dsl.ui.wizard;

import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class EmfParsleyDslNewProjectFiles {
  public CharSequence exampleDslFile(final String projectName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/* ");
    _builder.append(projectName, "");
    _builder.append(" Emf Components Dsl Module file */");
    _builder.newLineIfNotEmpty();
    _builder.append("module ");
    _builder.append(projectName, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence dslFileWithView(final String projectName, final String viewId) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/* ");
    _builder.append(projectName, "");
    _builder.append(" Emf Components Dsl Module file */");
    _builder.newLineIfNotEmpty();
    _builder.append("module ");
    _builder.append(projectName, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("parts {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("viewpart ");
    _builder.append(projectName, "		");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("viewname \"");
    _builder.append(projectName, "			");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("viewclass ");
    _builder.append(viewId, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
