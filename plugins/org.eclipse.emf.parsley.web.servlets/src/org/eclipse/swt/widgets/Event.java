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

import java.awt.Rectangle;

public class Event {

	/**
	 * the display where the event occurred
	 * 
	 * @since 2.0
	 */
	public Display display;

	/**
	 * the widget that issued the event
	 */
	public Widget widget;

	/**
	 * the type of event, as defined by the event type constants in class
	 * <code>SWT</code>
	 *
	 * @see org.eclipse.swt.SWT
	 */
	public int type;

	/**
	 * the event specific detail field, as defined by the detail constants in
	 * class <code>SWT</code>
	 * 
	 * @see org.eclipse.swt.SWT
	 */
	public int detail;

	/**
	 * the item that the event occurred in (can be null)
	 */
	public Widget item;

	/**
	 * the index of the item where the event occurred
	 * 
	 * @since 3.2
	 */
	public int index;

	/**
	 * the graphics context to use when painting that is configured to use the
	 * colors, font and damaged region of the control. It is valid only during
	 * the paint and must not be disposed
	 */
	// public GC gc;

	/**
	 * depending on the event type, the x offset of the bounding rectangle of
	 * the region that requires painting or the widget-relative, x coordinate of
	 * the pointer at the time the mouse button was pressed or released
	 */
	public int x;

	/**
	 * depending on the event type, the y offset of the bounding rectangle of
	 * the region that requires painting or the widget-relative, y coordinate of
	 * the pointer at the time the mouse button was pressed or released
	 */
	public int y;

	/**
	 * the width of the bounding rectangle of the region that requires painting
	 */
	public int width;

	/**
	 * the height of the bounding rectangle of the region that requires painting
	 */
	public int height;

	/**
	 * depending on the event type, the number of following paint events that
	 * are pending which may always be zero on some platforms, or the number of
	 * lines or pages to scroll using the mouse wheel, or the number of times
	 * the mouse has been clicked
	 */
	public int count;

	/**
	 * the time that the event occurred.
	 * 
	 * NOTE: This field is an unsigned integer and should be AND'ed with
	 * 0xFFFFFFFFL so that it can be treated as a signed long.
	 */
	public int time;

	/**
	 * the button that was pressed or released; 1 for the first button, 2 for
	 * the second button, and 3 for the third button, etc.
	 */
	public int button;

	/**
	 * depending on the event, the character represented by the key that was
	 * typed. This is the final character that results after all modifiers have
	 * been applied. For example, when the user types Ctrl+A, the character
	 * value is 0x01 (ASCII SOH). It is important that applications do not
	 * attempt to modify the character value based on a stateMask (such as
	 * SWT.CTRL) or the resulting character will not be correct.
	 */
	public char character;

	/**
	 * depending on the event, the key code of the key that was typed, as
	 * defined by the key code constants in class <code>SWT</code>. When the
	 * character field of the event is ambiguous, this field contains the
	 * unaffected value of the original character. For example, typing Ctrl+M or
	 * Enter both result in the character '\r' but the keyCode field will also
	 * contain '\r' when Enter was typed and 'm' when Ctrl+M was typed.
	 * 
	 * @see org.eclipse.swt.SWT
	 */
	public int keyCode;

	/**
	 * depending on the event, the location of key specified by the keyCode or
	 * character. The possible values for this field are <code>SWT.LEFT</code>,
	 * <code>SWT.RIGHT</code>, <code>SWT.KEYPAD</code>, or <code>SWT.NONE</code>
	 * representing the main keyboard area.
	 * <p>
	 * The location field can be used to differentiate key events that have the
	 * same key code and character but are generated by different keys in the
	 * keyboard. For example, a key down event with the key code equals to
	 * SWT.SHIFT can be generated by the left and the right shift keys in the
	 * keyboard. The location field can only be used to determine the location
	 * of the key code or character in the current event. It does not include
	 * information about the location of modifiers in state mask.
	 * </p>
	 * 
	 * @see org.eclipse.swt.SWT#LEFT
	 * @see org.eclipse.swt.SWT#RIGHT
	 * @see org.eclipse.swt.SWT#KEYPAD
	 * 
	 * @since 3.6
	 */
	public int keyLocation;

	/**
	 * depending on the event, the state of the keyboard modifier keys and mouse
	 * masks at the time the event was generated.
	 * 
	 * @see org.eclipse.swt.SWT#MODIFIER_MASK
	 * @see org.eclipse.swt.SWT#BUTTON_MASK
	 */
	public int stateMask;

	/**
	 * depending on the event, the range of text being modified. Setting these
	 * fields only has effect during ImeComposition events.
	 */
	public int start, end;

	/**
	 * depending on the event, the new text that will be inserted. Setting this
	 * field will change the text that is about to be inserted or deleted.
	 */
	public String text;

	/**
	 * Bidi segment offsets
	 * 
	 * @since 3.8
	 */
	public int[] segments;

	/**
	 * Characters to be applied on the segment boundaries
	 * 
	 * @since 3.8
	 */
	public char[] segmentsChars;

	/**
	 * depending on the event, a flag indicating whether the operation should be
	 * allowed. Setting this field to false will cancel the operation.
	 */
	public boolean doit = true;

	/**
	 * a field for application use
	 */
	public Object data;

	/**
	 * If nonzero, a positive value indicates a swipe to the right, and a
	 * negative value indicates a swipe to the left.
	 *
	 * @since 3.7
	 */
	public int xDirection;

	/**
	 * If nonzero, a positive value indicates a swipe in the up direction, and a
	 * negative value indicates a swipe in the down direction.
	 *
	 * @since 3.7
	 */
	public int yDirection;

	/**
	 * The change in magnification. This value should be added to the current
	 * scaling of an item to get the new scale factor.
	 *
	 * @since 3.7
	 */
	public double magnification;

	/**
	 * The number of degrees rotated on the track pad.
	 *
	 * @since 3.7
	 */
	public double rotation;

	/**
	 * Gets the bounds.
	 * 
	 * @return a rectangle that is the bounds.
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Sets the bounds.
	 * 
	 * @param rect
	 *            the new rectangle
	 */
	public void setBounds(Rectangle rect) {
		this.x = rect.x;
		this.y = rect.y;
		this.width = rect.width;
		this.height = rect.height;
	}

	/**
	 * Returns a string containing a concise, human-readable description of the
	 * receiver.
	 *
	 * @return a string representation of the event
	 */
	@Override
	public String toString() {
		return "Event {type=" + type + " " + widget + " time=" + time + " data=" + data + " x=" + x + " y=" + y //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ " width=" + width + " height=" + height + " detail=" + detail + "}"; //$NON-NLS-1$
	}
}
