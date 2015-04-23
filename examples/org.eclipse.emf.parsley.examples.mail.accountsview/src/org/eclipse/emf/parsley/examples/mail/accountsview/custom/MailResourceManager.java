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
package org.eclipse.emf.parsley.examples.mail.accountsview.custom;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.examples.mail.Account;
import org.eclipse.emf.parsley.examples.mail.Folder;
import org.eclipse.emf.parsley.examples.mail.Mail;
import org.eclipse.emf.parsley.examples.mail.MailFactory;
import org.eclipse.emf.parsley.resource.ResourceManager;


/**
 * @author Lorenzo Bettini
 * 
 */
public class MailResourceManager extends ResourceManager {
	
	int counter = 0;

	@Override
	public void initialize(Resource resource) {
		super.initialize(resource);

		Account account = createAccount("Lorenzo", "lorenzo@foobar");
		resource.getContents().add(account);
		createDefaultFolders(account);

		account = createAccount("Lorenzo2", "lorenzo2@foobar");
		resource.getContents().add(account);
		createDefaultFolders(account);

		account = createAccount("Lorenzo3", "lorenzo3@foobar");
		resource.getContents().add(account);
		createDefaultFolders(account);
	}

	protected Account createAccount(String name, String email) {
		Account account = MailFactory.eINSTANCE.createAccount();
		account.setName(name);
		account.setEmail(email);
		return account;
	}

	protected void createDefaultFolders(Account account) {
		Folder folder = createFolder(account, "Inbox");
		createMail(folder);
		createMail(folder);

		createFolder(account, "Sent");
		createFolder(account, "Trash");
		
		createFolder(account, "myfolder");
		folder = createFolder(account, "myfolder2");
		createSubFolder(folder, "sub1");
		createSubFolder(folder, "sub2");
	}

	protected Folder createFolder(Account account, String name) {
		Folder folder = MailFactory.eINSTANCE.createFolder();
		folder.setName(name);
		account.getFolders().add(folder);
		return folder;
	}

	protected Folder createSubFolder(Folder parent, String name) {
		Folder folder = MailFactory.eINSTANCE.createFolder();
		folder.setName(name);
		parent.getSubfolders().add(folder);
		return folder;
	}

	protected void createMail(Folder folder) {
		Mail mail = MailFactory.eINSTANCE.createMail();
		mail.setFrom("foo@foobar");
		mail.setSubject("Test subject " + (++counter));
		mail.getRecipients().add("dest@foobar");
		mail.setMessage("This is a test message."
				+ "\nNumber " + counter
				+ "\nCheers!");
		folder.getMails().add(mail);
	}
}
