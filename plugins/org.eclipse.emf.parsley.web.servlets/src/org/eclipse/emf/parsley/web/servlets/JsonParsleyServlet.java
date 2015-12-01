/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * Super-class Servlet that provides common behaviours
 * 
 * @author Vincenzo Caselli
 * 
 */
public class JsonParsleyServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String OBJECT_ID_PARAMETER = "id";
    public static final String SWITCH_PARAMETER = "s";

    protected PrintWriter prepareOutput(HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.addHeader("Access-Control-Allow-Origin", "*");
        return out;
    }

    protected void closeOutput(PrintWriter out) throws IOException {
        out.flush();
        out.close();
    }

}
