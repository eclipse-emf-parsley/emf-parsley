package org.eclipse.emf.parsley.dsl.tests.util;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslRuntimeModule;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslStandaloneSetup;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.runtime.service.AbstractGenericModule;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.xtext.xbase.compiler.OnTheFlyJavaCompiler;
import org.eclipse.xtext.xbase.compiler.OnTheFlyJavaCompiler.EclipseRuntimeDependentJavaCompiler;
import org.eclipse.xtext.xbase.junit.evaluation.AbstractXbaseEvaluationTest;
import org.eclipse.xtext.xbase.lib.Functions;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

/**
 * This is required to run tests based on Java compiler in Tycho.
 * 
 * @author Lorenzo Bettini
 *
 */
public class EmfParsleyDslInjectorProviderCustom extends EmfParsleyDslInjectorProvider {
	public Injector internalCreateInjector() {
		return new EmfParsleyDslStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new EmfParsleyDslRuntimeModule() {
					@Override
					public ClassLoader bindClassLoaderToInstance() {
						return EmfParsleyDslInjectorProviderCustom.class.getClassLoader();
					}

					@SuppressWarnings("unused")
					public Class<? extends OnTheFlyJavaCompiler> bindOnTheFlyJavaCompiler() {
						try {
							if (ResourcesPlugin.getWorkspace() != null)
								return EclipseRuntimeDependentJavaCompiler.class;
						} catch (Exception e) {
							// ignore
						}
						return OnTheFlyJavaCompiler.class;
					}
					
					@SuppressWarnings("unused")
					public Class<? extends OnTheFlyJavaCompiler.ClassPathAssembler> bindClassPathAssembler() {
						return TestClassPathAssembler.class;
					}
				});
			}
		}.createInjectorAndDoEMFRegistration();
	}

	public static class TestClassPathAssembler extends
			OnTheFlyJavaCompiler.ClassPathAssembler {
		@Override
		public void assembleCompilerClassPath(OnTheFlyJavaCompiler compiler) {
			super.assembleCompilerClassPath(compiler);
			if (compiler instanceof EclipseRuntimeDependentJavaCompiler) {
				compiler.addClassPathOfClass(getClass());
				
				compiler.addClassPathOfClass(AbstractUIPlugin.class);
				
				compiler.addClassPathOfClass(EmfParsleyGuiceModule.class);
				compiler.addClassPathOfClass(AbstractGenericModule.class);
				compiler.addClassPathOfClass(TableColumnLabelProvider.class);
				
				compiler.addClassPathOfClass(AdapterFactoryLabelProvider.class);
				compiler.addClassPathOfClass(AdapterFactoryContentProvider.class);
				
				compiler.addClassPathOfClass(ILabelProvider.class);
				compiler.addClassPathOfClass(ColumnLabelProvider.class);
				compiler.addClassPathOfClass(CellLabelProvider.class);
				compiler.addClassPathOfClass(IFontProvider.class);
				compiler.addClassPathOfClass(IColorProvider.class);
				compiler.addClassPathOfClass(BaseLabelProvider.class);
				compiler.addClassPathOfClass(IBaseLabelProvider.class);
				compiler.addClassPathOfClass(ITreeContentProvider.class);
				compiler.addClassPathOfClass(IPropertySourceProvider.class);
				compiler.addClassPathOfClass(INotifyChangedListener.class);
				
				compiler.addClassPathOfClass(Image.class);
				compiler.addClassPathOfClass(FormToolkit.class);

				compiler.addClassPathOfClass(EObject.class);
				compiler.addClassPathOfClass(EStructuralFeature.class);
				compiler.addClassPathOfClass(EList.class);

				compiler.addClassPathOfClass(EventManager.class);
				
				compiler.addClassPathOfClass(IObservableValue.class);
				compiler.addClassPathOfClass(ISWTObservableValue.class);
				compiler.addClassPathOfClass(DataBindingContext.class);
				
				compiler.addClassPathOfClass(Library.class);
				
				compiler.addClassPathOfClass(AbstractXbaseEvaluationTest.class);
				compiler.addClassPathOfClass(Functions.class);
				compiler.addClassPathOfClass(Provider.class);
				compiler.addClassPathOfClass(Inject.class);
				compiler.addClassPathOfClass(javax.inject.Provider.class);
				compiler.addClassPathOfClass(Supplier.class);
				compiler.addClassPathOfClass(Predicate.class);
			}
		}
	}

}
