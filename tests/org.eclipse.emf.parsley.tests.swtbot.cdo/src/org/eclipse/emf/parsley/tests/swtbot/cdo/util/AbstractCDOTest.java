/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.cdo.util;


import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.parsley.examples.cdo.server.CDOServer;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.security.PasswordCredentialsProvider;

public class AbstractCDOTest {
	
	private static final String USER = "login";
	private static final String PASSWORD = "password";
	private static final String PORT = "2037";
	private static final String SERVER = "localhost";

	protected void startServer(String repoName){
		CDOServer.startMemoryRepository(repoName,PORT);
	}
	
    protected CDOSession openSession(String repoName) {
        IConnector connector = TCPUtil.getConnector(IPluginContainer.INSTANCE, SERVER + ":" + PORT);
        CDONet4jSessionConfiguration configuration = CDONet4jUtil.createNet4jSessionConfiguration();
        configuration.setCredentialsProvider(new PasswordCredentialsProvider(USER, PASSWORD));
        configuration.setConnector(connector);
        configuration.setRepositoryName(repoName);
        return configuration.openNet4jSession();
    }
    
    protected CDOResource createResource(String repo, String resource){
		return openSession(repo).openTransaction().createResource(resource);
    }

}
