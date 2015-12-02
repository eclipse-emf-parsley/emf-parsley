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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for handling the submission of a JSON object that will be re-mapped
 * into an EMF object and saved into the EMF Resource when available
 * 
 * @author Vincenzo Caselli
 * 
 */
public class JsonSaveServlet extends JsonParsleyServlet {
	private static final long serialVersionUID = 1L;

	protected void doPut(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = prepareOutput(response);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		System.out.println(data);
		closeOutput(out);
	}
}
