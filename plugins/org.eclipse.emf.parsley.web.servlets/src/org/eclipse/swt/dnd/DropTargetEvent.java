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

package org.eclipse.swt.dnd;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Widget;

public class DropTargetEvent extends TypedEvent {
	public int x;
	public int y;
	public int detail;
	public int operations;
	public int feedback;
	public Widget item;
	public TransferData currentDataType;
	public TransferData[] dataTypes;

	public DropTargetEvent(DNDEvent e) {
		super(e);
		this.data = e.data;
		this.x = e.x;
		this.y = e.y;
		this.detail = e.detail;
		this.currentDataType = e.dataType;
		this.dataTypes = e.dataTypes;
		this.operations = e.operations;
		this.feedback = e.feedback;
		this.item = e.item;
	}

	void updateEvent(DNDEvent e) {
		e.widget = this.widget;
		e.time = this.time;
		e.data = this.data;
		e.x = this.x;
		e.y = this.y;
		e.detail = this.detail;
		e.dataType = this.currentDataType;
		e.dataTypes = this.dataTypes;
		e.operations = this.operations;
		e.feedback = this.feedback;
		e.item = this.item;
	}
	
	@Override
	public String toString() {
		String string = super.toString ();
		StringBuffer sb = new StringBuffer();
		sb.append(string.substring (0, string.length() - 1)); // remove trailing '}'
		sb.append(" x="); sb.append(x);
		sb.append(" y="); sb.append(y);
		sb.append(" item="); sb.append(item);
		sb.append(" operations="); sb.append(operations);
		sb.append(" operation="); sb.append(detail);
		sb.append(" feedback="); sb.append(feedback);
		sb.append(" dataTypes={ ");
		if (dataTypes != null) {
			for (int i = 0; i < dataTypes.length; i++) {
				sb.append(dataTypes[i].type); sb.append(' ');
			}
		}
		sb.append('}');
		sb.append(" currentDataType="); sb.append(currentDataType != null ? currentDataType.type : '0');
		sb.append('}');
		return sb.toString();
	}

}
