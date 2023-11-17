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

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
import java.util.Stack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

/**
 * Adds the Undo-Redo functionality (working Ctrl+Z and Ctrl+Y) to an instance
 * of {@link Text}.
 *
 * @author Lorenzo Bettini - Initial Contribution and API
 *
 * @see {@linkplain http
 *      ://sourceforge.net/p/etinyplugins/blog/2013/02/add-undoredo
 *      -support-to-your-swt-styledtext-s/} - inspiration for this code...
 * @see {@linkplain http
 *      ://www.java2s.com/Code/Java/SWT-JFace-Eclipse/SWTUndoRedo.htm} -
 *      inspiration for this code, though not really functioning - it mainly
 *      shows which listeners to use...
 * @see {@linkplain http
 *      ://stackoverflow.com/questions/7179464/swt-how-to-recreate
 *      -a-default-context-menu-for-text-fields} -
 *      "SWT's StyledText doesn't support Undo-Redo out-of-the-box"
 */
public class TextUndoRedo implements KeyListener, ModifyListener {

	private static class TextInfo {
		String currentText;

		int currentCaretPosition;

		public TextInfo(String currentText, int currentCaretPosition) {
			super();
			this.currentText = currentText;
			this.currentCaretPosition = currentCaretPosition;
		}
	}

	/**
	 * Encapsulation of the Undo and Redo stack(s).
	 *
	 * It is crucial to use Stack (and not Deque) even if Sonarqube suggests the
	 * other way around, because here we're in a multithreading context and
	 * we need a synchronized data structure to make it thread-safe.
	 */
	private static class UndoRedoStack {

		private Stack<TextInfo> undo;
		private Stack<TextInfo> redo;

		public UndoRedoStack() {
			undo = new Stack<>();
			redo = new Stack<>();
		}

		public void pushUndo(TextInfo delta) {
			undo.add(delta);
		}

		public void pushRedo(TextInfo delta) {
			redo.add(delta);
		}

		public TextInfo popUndo() {
			TextInfo res = undo.pop();
			return res;
		}

		public TextInfo popRedo() {
			TextInfo res = redo.pop();
			return res;
		}

		public void clearRedo() {
			redo.clear();
		}

		public boolean hasUndo() {
			return !undo.isEmpty();
		}

		public boolean hasRedo() {
			return !redo.isEmpty();
		}

	}

	private Text text;

	private TextInfo currentTextInfo;

	private UndoRedoStack stack;

	private boolean isUndo;

	private boolean isRedo;

	/**
	 * Creates a new instance of this class. Automatically starts listening to
	 * corresponding key and modify events coming from the given
	 * <var>text</var>.
	 *
	 * @param text
	 *            the text field to which the Undo-Redo functionality should be
	 *            added
	 */
	public TextUndoRedo(Text text) {
		text.addModifyListener(this);
		text.addKeyListener(this);

		this.text = text;
		stack = new UndoRedoStack();
		currentTextInfo = currentInfo();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.
	 * KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// Listen to CTRL+Z for Undo, to CTRL+Y or CTRL+SHIFT+Z for Redo
		final boolean isCtrl = (e.stateMask & SWT.CTRL) != 0;
		final boolean isAlt = (e.stateMask & SWT.ALT) != 0;
		if (isCtrl && !isAlt) {
			boolean isShift = (e.stateMask & SWT.SHIFT) != 0;
			if (!isShift && e.keyCode == 'z') {
				undo();
			} else {
				final boolean notShiftY = !isShift && e.keyCode == 'y';
				final boolean isShiftZ = isShift && e.keyCode == 'z';
				if (notShiftY || isShiftZ) {
					redo();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events
	 * .KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// ignore
	}

	/**
	 * Creates a corresponding Undo or Redo step from the given event and pushes
	 * it to the stack. The Redo stack is, logically, emptied if the event comes
	 * from a normal user action.
	 *
	 * @param event
	 */
	@Override
	public void modifyText(ModifyEvent event) {
		if (isUndo) {
			stack.pushRedo(currentTextInfo);
		} else { // is Redo or a normal user action
			stack.pushUndo(currentTextInfo);
			if (!isRedo) {
				stack.clearRedo();
			}
		}
		currentTextInfo = currentInfo();
	}

	/**
	 * Performs the Undo action. A new corresponding Redo step is automatically
	 * pushed to the stack.
	 */
	private void undo() {
		if (stack.hasUndo()) {
			isUndo = true;
			TextInfo popUndo = stack.popUndo();
			updateText(popUndo);
			isUndo = false;
		}
	}

	/**
	 * Performs the Redo action. A new corresponding Undo step is automatically
	 * pushed to the stack.
	 */
	private void redo() {
		if (stack.hasRedo()) {
			isRedo = true;
			updateText(stack.popRedo());
			isRedo = false;
		}
	}

	private void updateText(TextInfo info) {
		text.setText(info.currentText);
		text.setSelection(info.currentCaretPosition);
	}

	private TextInfo currentInfo() {
		return new TextInfo(text.getText(), text.getCaretPosition());
	}
}
