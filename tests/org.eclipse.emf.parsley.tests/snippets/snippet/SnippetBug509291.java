package snippet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SnippetBug509291 {
	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());

		@SuppressWarnings("unused")
		DateTime dateTime = new DateTime(shell, SWT.DATE | SWT.DROP_DOWN);
		// specify also SWT.DATE, otherwise, on Mac, it won't work:
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=509291

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();
	}
}
