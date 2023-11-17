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

package org.eclipse.emf.parsley.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.util.EObjectState;
import org.eclipse.jface.viewers.ILabelProvider;

import com.google.inject.Inject;

/**
 * The object is being edited without live notifications, so that views do not
 * get updated until the editing is submitted, for instance, with a dialog "OK"
 * button; the editing can also be undone and the object is rolledback to its
 * original state.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class UndoableEditingStrategy implements IEditingStrategy {

	@Inject
	private EditingDomainFinder editingDomainFinder;

	@Inject
	private ILabelProvider labelProvider;

	private EObjectState state;

	private NotificationBuffer notificationBuffer;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.emf.parsley.edit.IEditingStrategy#prepare(org.eclipse.emf
	 * .ecore.EObject)
	 */
	@Override
	public void prepare(EObject original) {
		state = new EObjectState(original);
		notificationBuffer = new NotificationBuffer(original);
		disableNotifications(original);
	}

	/**
	 * This returns null because an {@link EditingDomain} must not be used,
	 * otherwise modifications to the object are reflected in other views and
	 * editors.
	 */
	@Override
	public EditingDomain getEditingDomain(EObject edited) {
		return null;
	}

	@Override
	public void update(EObject edited) {
		enableNotifications(edited);
		EditingDomain domain = editingDomainFinder.getEditingDomainFor(edited);
		EditCommand editCommand = new EditCommand(domain, "Edit "
				+ labelProvider.getText(edited), edited, state);
		domain.getCommandStack().execute(editCommand);
		triggerViewerNotification(edited);
	}

	protected void triggerViewerNotification(EObject edited) {
		notificationBuffer.propagateBufferedNotifications();
	}

	@Override
	public void rollback(EObject edited) {
		state.copyStateTo(edited);
		enableNotifications(edited);
	}

	protected void disableNotifications(EObject edited) {
		notificationBuffer.startBuffering();
	}

	protected void enableNotifications(EObject edited) {
		notificationBuffer.stopBuffering();
	}
}
