/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.junit4.ui.util;

import static org.junit.Assert.fail;

import java.text.MessageFormat;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class ImageTester {

	private ImageTester() {

	}

	public static void assertImageIs(Image expectedImage,
			Image actualImage) {
		assertImageDataIs(expectedImage.getImageData(), actualImage.getImageData());
	}

	public static void assertImageDataIs(ImageData expectedImageData,
			ImageData actualImageData) {
		if (expectedImageData.width != actualImageData.width
				|| expectedImageData.height != actualImageData.height) {
			fail(MessageFormat
					.format("Image data do not have the same dimensions ({0}x{1} expected, got {2}x{3})",
							expectedImageData.width, expectedImageData.height,
							actualImageData.width, actualImageData.height));
		}

		for (int y = 0; y < expectedImageData.height; y++) {
			for (int x = 0; x < expectedImageData.width; x++) {
				int actualPixel = actualImageData.getPixel(x, y);
				int expectedPixel = expectedImageData.getPixel(x, y);
				RGB actualRGB = actualImageData.palette.getRGB(actualPixel);
				RGB expectedRGB = expectedImageData.palette
						.getRGB(expectedPixel);
				if (!actualRGB.equals(expectedRGB)) {
					fail(MessageFormat.format(
							"Image data do not match at ({0},{1})", x, y));
				}
			}
		}
	}
}
