package org.eclipse.emf.parsley.web.tools.templates;

import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import org.eclipse.emf.parsley.web.tools.templates.ParsleyWebGuiceModule;

@WebListener
public class ParsleyGuiceServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ParsleyWebGuiceModule(null));
    }

}