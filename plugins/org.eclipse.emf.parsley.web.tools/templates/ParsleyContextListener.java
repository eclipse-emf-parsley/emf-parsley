package org.eclipse.emf.parsley.web.tools.templates;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ParsleyContextListener implements ServletContextListener {
    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event) {
        this.context = event.getServletContext();
    }

    public void contextDestroyed(ServletContextEvent event) {
        this.context = null;
    }

}