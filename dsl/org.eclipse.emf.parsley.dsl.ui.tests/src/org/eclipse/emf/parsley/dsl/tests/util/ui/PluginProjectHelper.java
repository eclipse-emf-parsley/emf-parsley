package org.eclipse.emf.parsley.dsl.tests.util.ui;

import static java.util.stream.Collectors.toList;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.join;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.map;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.size;
import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * Utility class for Plug-in project testing.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class PluginProjectHelper {
	public static void assertNoErrors() throws CoreException {
		final Iterable<IMarker> markers = getErrorMarkers();
		assertEquals(
			("unexpected errors:\n" + 
				join(map(markers, it -> {
					try {
						return it.getAttribute(IMarker.LOCATION) + ", " + it.getAttribute(IMarker.MESSAGE);
					} catch (CoreException e) {
						throw Exceptions.sneakyThrow(e);
					}
				}), "\n")), 0, size(markers));
	}

	public static Iterable<IMarker> getErrorMarkers() throws CoreException {
		return Stream.of(IResourcesSetupUtil.root().findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE))
			.filter(it -> it.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR)
			.collect(toList());
	}

}
