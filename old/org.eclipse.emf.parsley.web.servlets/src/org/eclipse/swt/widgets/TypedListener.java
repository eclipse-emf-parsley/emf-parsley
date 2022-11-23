/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 * 
 *******************************************************************************/
package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.SWTEventListener;

/**
 * Instances of this class are <em>internal SWT implementation</em> objects
 * which provide a mapping between the typed and untyped listener mechanisms
 * that SWT supports.
 * <p>
 * <b>IMPORTANT:</b> This class is <em>not</em> part of the SWT public API. It
 * is marked public only so that it can be shared within the packages provided
 * by SWT. It should never be referenced from application code.
 * </p>
 *
 * @see Listener
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further
 *      information</a>
 */
public class TypedListener implements Listener {

	/**
	 * The receiver's event listener
	 */
	protected SWTEventListener eventListener;

	/**
	 * Constructs a new instance of this class for the given event listener.
	 * <p>
	 * <b>IMPORTANT:</b> This method is <em>not</em> part of the SWT public API.
	 * It is marked public only so that it can be shared within the packages
	 * provided by SWT. It should never be referenced from application code.
	 * </p>
	 *
	 * @param listener
	 *            the event listener to store in the receiver
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public TypedListener(SWTEventListener listener) {
		eventListener = listener;
	}

	/**
	 * Returns the receiver's event listener.
	 * <p>
	 * <b>IMPORTANT:</b> This method is <em>not</em> part of the SWT public API.
	 * It is marked public only so that it can be shared within the packages
	 * provided by SWT. It should never be referenced from application code.
	 * </p>
	 *
	 * @return the receiver's event listener
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public SWTEventListener getEventListener() {
		return eventListener;
	}

	/**
	 * Handles the given event.
	 * <p>
	 * <b>IMPORTANT:</b> This method is <em>not</em> part of the SWT public API.
	 * It is marked public only so that it can be shared within the packages
	 * provided by SWT. It should never be referenced from application code.
	 * </p>
	 * 
	 * @param e
	 *            the event to handle
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public void handleEvent(Event e) {
		/// +
	}

}
