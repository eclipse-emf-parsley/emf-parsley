/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.util.EObjectState;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class EditCommand extends AbstractOverrideableCommand {

	EObject original;

	EObjectState state;

	/**
	 * @param domain
	 * @param label
	 */
	public EditCommand(EditingDomain domain, String label, EObject original,
			EObjectState state) {
		super(domain, label);
		this.original = original;
		this.state = state;
	}

	@Override
	protected boolean prepare() {
		return true;
	}

	@Override
	public void doExecute() {
		// nothing to do, it's already up to date, since
		// the original object has been edited.
	}

	@Override
	public void doUndo() {
		EObjectState tempState = new EObjectState(original);
		state.copyStateTo(original);
		state = tempState;
	}

	@Override
	public void doRedo() {
		EObjectState tempState = new EObjectState(original);
		state.copyStateTo(original);
		state = tempState;
	}

	@Override
	public Collection<?> doGetAffectedObjects() {
		return Collections.singleton(original);
	}

}
