 
package org.eclipse.emf.parsley.examples.eclipse4.parsleypart.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ShowParsleyPartHandler {

	@Inject
	private EPartService partService;
	
	@Execute
	public void execute(MApplication application) {
		MPart part = partService.findPart("org.eclipse.emf.parsley.examples.eclipse4.parsleypart.part.0");
		part.setVisible(true);
		partService.showPart(part, EPartService.PartState.VISIBLE);
	}
		
}