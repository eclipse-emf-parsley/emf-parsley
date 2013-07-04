/**
 * 
 */
package org.eclipse.emf.parsley.edit;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Handles saving of {@link Resource}
 * 
 * @author Lorenzo Bettini
 *
 */
public class ResourceSaveManager {

	public boolean save(Resource resource) throws IOException {
		return save(resource, null);
	}
	
	public boolean save(Resource resource, Map<?, ?> options) throws IOException {
		if (!precondition(resource))
			return false;
		resource.save(options);
		return true;
	}

	protected boolean precondition(Resource resource) {
		return true;
	}
}
