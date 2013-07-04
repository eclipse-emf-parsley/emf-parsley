package org.eclipse.emf.parsley.generator.common;

import com.google.common.base.Objects;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EmfParsleyProjectFilesGenerator {
  public String prefixFromProject(final String projectName) {
    String prefixName = projectName;
    final int dotIndex = projectName.lastIndexOf(".");
    boolean _greaterThan = (dotIndex > 0);
    if (_greaterThan) {
      int _plus = (dotIndex + 1);
      String _substring = projectName.substring(_plus);
      prefixName = _substring;
    }
    return StringExtensions.toFirstUpper(prefixName);
  }
  
  public CharSequence activatorName(final String projectName) {
    StringConcatenation _builder = new StringConcatenation();
    String _prefixFromProject = this.prefixFromProject(projectName);
    _builder.append(_prefixFromProject, "");
    _builder.append("Activator");
    return _builder;
  }
  
  public CharSequence moduleName(final String projectName) {
    StringConcatenation _builder = new StringConcatenation();
    String _prefixFromProject = this.prefixFromProject(projectName);
    _builder.append(_prefixFromProject, "");
    _builder.append("GuiceModule");
    return _builder;
  }
  
  public CharSequence extFactoryName(final String projectName) {
    StringConcatenation _builder = new StringConcatenation();
    String _prefixFromProject = this.prefixFromProject(projectName);
    _builder.append(_prefixFromProject, "");
    _builder.append("ExecutableExtensionFactory");
    return _builder;
  }
  
  public CharSequence generateManifest(final String projectName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Manifest-Version: 1.0");
    _builder.newLine();
    _builder.append("Bundle-ManifestVersion: 2");
    _builder.newLine();
    _builder.append("Bundle-Name: ");
    _builder.append(projectName, "");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-SymbolicName: ");
    _builder.append(projectName, "");
    _builder.append(";singleton:=true");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-Version: 1.0.0.qualifier");
    _builder.newLine();
    _builder.append("Bundle-Activator: ");
    _builder.append(projectName, "");
    _builder.append(".");
    CharSequence _activatorName = this.activatorName(projectName);
    _builder.append(_activatorName, "");
    _builder.newLineIfNotEmpty();
    _builder.append("Require-Bundle: org.eclipse.ui,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.core.runtime,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.emf.parsley,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.emf.parsley.views");
    _builder.newLine();
    _builder.append("Bundle-ActivationPolicy: lazy");
    _builder.newLine();
    _builder.append("Bundle-RequiredExecutionEnvironment: JavaSE-1.6");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateBuildProperties(final boolean hasPluginXml) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("source.. = src/");
    _builder.newLine();
    _builder.append("output.. = bin/");
    _builder.newLine();
    _builder.append("bin.includes = META-INF/,\\");
    _builder.newLine();
    _builder.append("               ");
    _builder.append(".");
    {
      if (hasPluginXml) {
        _builder.append(",\\");
        _builder.newLineIfNotEmpty();
        _builder.append("               ");
        _builder.append("plugin.xml");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateActivator(final String projectName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(projectName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.osgi.framework.BundleContext;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.EmfComponentsGuiceModule;");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.ui.EmfComponentsAbstractActivator;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* The activator class controls the plug-in life cycle");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    CharSequence _activatorName = this.activatorName(projectName);
    _builder.append(_activatorName, "");
    _builder.append(" extends EmfComponentsAbstractActivator {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// The plug-in ID");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static final String PLUGIN_ID = \"");
    _builder.append(projectName, "	");
    _builder.append("\"; //$NON-NLS-1$");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// The shared instance");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static ");
    CharSequence _activatorName_1 = this.activatorName(projectName);
    _builder.append(_activatorName_1, "	");
    _builder.append(" plugin;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    CharSequence _activatorName_2 = this.activatorName(projectName);
    _builder.append(_activatorName_2, "	");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* (non-Javadoc)");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void start(BundleContext context) throws Exception {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.start(context);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("plugin = this;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* (non-Javadoc)");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void stop(BundleContext context) throws Exception {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("plugin = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.stop(context);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Returns the shared instance");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @return the shared instance");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static ");
    CharSequence _activatorName_3 = this.activatorName(projectName);
    _builder.append(_activatorName_3, "	");
    _builder.append(" getDefault() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return plugin;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Creates the EmfComponentsGuiceModule for this this plugin");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @return the EmfComponentsGuiceModule for this this plugin");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public EmfComponentsGuiceModule createModule() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new ");
    CharSequence _moduleName = this.moduleName(projectName);
    _builder.append(_moduleName, "		");
    _builder.append("(getDefault());");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateExecutableExtensionFactory(final String projectName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(projectName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.osgi.framework.Bundle;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.parsley.EmfComponentsGuiceModule;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.google.inject.Injector;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    CharSequence _extFactoryName = this.extFactoryName(projectName);
    _builder.append(_extFactoryName, "");
    _builder.append(" extends");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("EmfComponentsExtensionFactory {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Bundle getBundle() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    CharSequence _activatorName = this.activatorName(projectName);
    _builder.append(_activatorName, "		");
    _builder.append(".getDefault().getBundle();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected EmfComponentsGuiceModule getModule() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    CharSequence _activatorName_1 = this.activatorName(projectName);
    _builder.append(_activatorName_1, "		");
    _builder.append(".getDefault().createModule();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Injector getInjector() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    CharSequence _activatorName_2 = this.activatorName(projectName);
    _builder.append(_activatorName_2, "		");
    _builder.append(".getDefault().getInjector();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateModule(final String projectName, final String superClass) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(projectName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.eclipse.ui.plugin.AbstractUIPlugin;");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _equals = Objects.equal(superClass, "EmfComponentsGuiceModule");
      if (_equals) {
        _builder.append("import org.eclipse.emf.parsley.EmfComponentsGuiceModule;");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("public class ");
    CharSequence _moduleName = this.moduleName(projectName);
    _builder.append(_moduleName, "");
    _builder.append(" extends ");
    _builder.append(superClass, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    CharSequence _moduleName_1 = this.moduleName(projectName);
    _builder.append(_moduleName_1, "	");
    _builder.append("(AbstractUIPlugin plugin) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(plugin);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
