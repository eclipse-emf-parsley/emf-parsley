/**
 * 
 */
package org.eclipse.emf.parsley.cdo.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.cdo.util.CDOURIData;
import org.junit.Test;

/**
 * @author bettini
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
	public void testCDOURIData() {
		URI uri = URI.createURI(CDO_LOCALHOST_2036_DEMO_RES2);
		CDOURIData data = CDOURIData.parse(uri);
		assertEquals("localhost:2036", data.server);
		assertEquals("demo", data.repository);
		assertEquals("res2", data.resource);
	}
}
