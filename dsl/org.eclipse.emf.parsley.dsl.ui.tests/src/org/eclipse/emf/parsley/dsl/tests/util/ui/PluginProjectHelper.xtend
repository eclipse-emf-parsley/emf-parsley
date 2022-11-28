package org.eclipse.emf.parsley.dsl.tests.util.ui

import java.io.File
import java.io.FileFilter
import java.io.FileNotFoundException
import org.eclipse.core.resources.IMarker
import org.eclipse.core.resources.IResource
import org.eclipse.jdt.core.JavaCore

import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.*
import static org.junit.Assert.*

/**
 * Utility class for creating a Plug-in project for testing.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
class PluginProjectHelper {

	def assertNoErrors() {
		val markers = root.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).filter [
			getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR
		]
		assertEquals(
			"unexpected errors:\n" + markers.map[getAttribute(IMarker.LOCATION) + ", " + getAttribute(IMarker.MESSAGE)].
				join("\n"),
			0,
			markers.size
		)
	}

	def void clearJdtIndex() {
		val jdtMetadata = JavaCore.getPlugin().getStateLocation().toFile();
		var success = false;
		try {
			cleanFolder(jdtMetadata);
			success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.err.println("Clean up index " + jdtMetadata.getAbsolutePath() + ": " + success);
	}

	def void cleanFolder(File parentFolder) throws FileNotFoundException {
		cleanFolder(parentFolder, [true]);
	}

	def void cleanFolder(File parentFolder, FileFilter myFilter) throws FileNotFoundException {
		if (!parentFolder.exists()) {
			throw new FileNotFoundException(parentFolder.getAbsolutePath());
		}
		val File[] contents = parentFolder.listFiles(myFilter);
		for (var j = 0; j < contents.length; j++) {
			val File file = contents.get(j);
			if (file.isDirectory()) {
				cleanFolder(file, myFilter);
			} else {
				file.delete();
			}
		}
	}
}
