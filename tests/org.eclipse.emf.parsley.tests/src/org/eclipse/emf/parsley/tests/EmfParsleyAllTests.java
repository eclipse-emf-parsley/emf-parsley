/**
 * 
 */
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.parsley.tests.FeatureResolverTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Lorenzo Bettini
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	FeatureNamePathTest.class,
	FeatureResolverTest.class
})
public class EmfParsleyAllTests {

}