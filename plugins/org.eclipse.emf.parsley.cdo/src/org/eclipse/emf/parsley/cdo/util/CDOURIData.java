/**
 * 
 */
package org.eclipse.emf.parsley.cdo.util;

import org.eclipse.emf.common.util.URI;

/**
 * @author bettini
 * 
 */
public class CDOURIData {

	public String host;

	public String sessionName;

	public String resourceName;

	public CDOURIData(String host, String sessionName, String resourceName) {
		super();
		this.host = host;
		this.sessionName = sessionName;
		this.resourceName = resourceName;
	}

	public static CDOURIData parse(URI uri) {
		String host = uri.authority();
		if (host == null)
			throw new IllegalArgumentException("missing host in URI: " + uri);
		String scheme = uri.scheme();
		if (scheme == null || !scheme.equals("cdo"))
			throw new IllegalArgumentException("not a cdo scheme: " + uri);
		String sessionName = uri.segment(0);
		String resourceName = uri.segment(1);
		return new CDOURIData(host, sessionName, resourceName);
	}
}
