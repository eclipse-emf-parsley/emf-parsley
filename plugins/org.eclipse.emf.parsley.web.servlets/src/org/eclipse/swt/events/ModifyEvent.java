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
package org.eclipse.swt.events;

import org.eclipse.swt.widgets.Event;

/**
 * Instances of this class are sent as a result of text being modified.
 *
 * @see ModifyListener
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further
 *      information</a>
 */

public final class ModifyEvent extends TypedEvent {

	static final long serialVersionUID = 3258129146227011891L;

	/**
	 * Constructs a new instance of this class based on the information in the
	 * given untyped event.
	 *
	 * @param e
	 *            the untyped event containing the information
	 */
	public ModifyEvent(Event e) {
		super(e);
	}

}
