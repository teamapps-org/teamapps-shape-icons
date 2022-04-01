/*-
 * ========================LICENSE_START=================================
 * TeamApps Shape Icon Library
 * ---
 * Copyright (C) 2022 TeamApps.org
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package org.teamapps.icon.shape;

import org.teamapps.icons.IconEncoderContext;
import org.teamapps.icons.spi.IconEncoder;

public class ShapeIconEncoder implements IconEncoder<ShapeIcon> {

	public static final String FILL_COLOR = "fillColor";
	public static final String STROKE_COLOR = "strokeColor";
	public static final String STROKE_WIDTH = "strokeWidth";
	public static final String SHADOW = "shadow";
	public static final String SCALING = "scaling";

	@Override
	public String encodeIcon(ShapeIcon icon, IconEncoderContext iconEncoderContext) {
		if (icon.getStyle() != null) {
			return icon.getIconName() + "(" + encodeStyle(icon.getStyle()) + ")";
		} else {
			return icon.getIconName();
		}
	}

	private String encodeStyle(ShapeIconStyle style) {
		return FILL_COLOR + "!" + style.getFillColor().toHtmlColorString()
				+ "!" + STROKE_COLOR + "!" + style.getStrokeColor().toHtmlColorString()
				+ "!" + STROKE_WIDTH + "!" + style.getStrokeWidth()
				+ "!" + SHADOW + "!" + style.isShadow()
				+ "!" + SCALING + "!" + style.getScaling()
				;
	}

}
