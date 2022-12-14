/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/

package org.eclipse.emf.parsley.cdo.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.cdo.util.CDOURIData;
import org.junit.Test;

/**
 * @author Lorenzo Bettini
 * 
 */
public class CDOUriTests {

	protected static final String CDO_LOCALHOST_2036_DEMO_RES2 = "cdo://localhost:2036/demo/res2";

	@Test
	public void testUri() {
		URI uri = URI.createURI(CDO_LOCALHOST_2036_DEMO_RES2);
		System.out.println(uri);
		assertEquals("localhost:2036", uri.authority());
		assertEquals("cdo", uri.scheme());
		assertEquals("demo", uri.segment(0));
		assertEquals("res2", uri.segment(1));
	}

	@Test
	public void testCDOURIDataOk() {
		URI uri = URI.createURI(CDO_LOCALHOST_2036_DEMO_RES2);
		CDOURIData data = CDOURIData.parse(uri);
		assertEquals("localhost:2036", data.getServer());
		assertEquals("demo", data.getRepository());
		assertEquals("res2", data.getResource());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCDOURIDataMissingCDOScheme() {
		CDOURIData.parse(URI.createURI("foo://localhost:2036/demo/res2"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCDOURIDataMissingScheme() {
		CDOURIData.parse(URI.createURI("/res2"));
	}
}
