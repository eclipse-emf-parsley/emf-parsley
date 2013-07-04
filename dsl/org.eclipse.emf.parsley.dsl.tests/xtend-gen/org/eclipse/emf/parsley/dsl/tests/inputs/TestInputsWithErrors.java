package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class TestInputsWithErrors {
  public CharSequence duplicateLabelSpecifications() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("module my.empty {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("labelProvider {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("text {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("image {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("text {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence wrongPropertyDescriptionSpecifications() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.*");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.examples.library.*");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("module my.empty {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("propertyDescriptionProvider {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("text {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Item:publicationDate -> \'Publication Date\'");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Library:newArrayList -> \'Name\' // static method, wrong");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Library:getBooks -> \'Books\' // don\'t use get methods");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Writer:lastName -> name.toFirstUpper // the implicit param is an EStructuralFeature");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence viewSpecificationIsNotIViewPart() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.*");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.examples.library.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("module my.empty {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("parts {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("viewpart my.view.part {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("viewname \"My View\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("viewclass Library // not an IViewPart!");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence notAnEObjectInEmfFeatureAccess() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.*");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.examples.library.*");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.views.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("module my.empty {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("propertyDescriptionProvider {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("text {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("List:publicationDate -> \'Publication Date\'");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("AbstractSaveableTreeView:lastName -> null");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence notValidModuleExtends() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.*");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.examples.library.*");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.views.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("module my.empty extends Library {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
