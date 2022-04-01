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

import org.teamapps.common.format.Color;
import org.teamapps.icons.IconDecoderContext;
import org.teamapps.icons.spi.IconDecoder;

import java.util.HashMap;

import static org.teamapps.icon.shape.ShapeIconEncoder.*;

public class ShapeIconDecoder implements IconDecoder<ShapeIcon> {

	@Override
	public ShapeIcon decodeIcon(String encodedIconString, IconDecoderContext context) {
		int dotIndex = encodedIconString.indexOf('(');

		if (dotIndex < 0) {
			return ShapeIcon.forName(encodedIconString);
		} else {
			String iconName = encodedIconString.substring(0, dotIndex);

			String encodedStyleString = encodedIconString.substring(dotIndex + 1, encodedIconString.length() - 1);

			ShapeIconStyle style = decodeStyle(encodedStyleString);
			return ShapeIcon.forName(iconName).withStyle(style);
		}
	}

	private ShapeIconStyle decodeStyle(String encodedStyleString) {
		HashMap<String, String> parameterKeyValue = extractStyleParameterStrings(encodedStyleString);

		var styleBuilder = ShapeIconStyle.builder();
		String fillColorString = parameterKeyValue.get(FILL_COLOR);if (fillColorString != null) { styleBuilder = styleBuilder.setFillColor(Color.fromHtmlString(fillColorString)); }
		String strokeColorString = parameterKeyValue.get(STROKE_COLOR);if (strokeColorString != null) { styleBuilder = styleBuilder.setStrokeColor(Color.fromHtmlString(strokeColorString)); }
		String strokeWidthString = parameterKeyValue.get(STROKE_WIDTH);if (strokeWidthString != null) { styleBuilder = styleBuilder.setStrokeWidth(Float.parseFloat(strokeWidthString)); }
		String shadowString = parameterKeyValue.get(SHADOW);if (shadowString != null) { styleBuilder = styleBuilder.setShadow(Boolean.parseBoolean(shadowString)); }
		String scalingString = parameterKeyValue.get(SCALING);if (scalingString != null) { styleBuilder = styleBuilder.setScaling(Float.parseFloat(scalingString)); }
		return styleBuilder.build();
	}

	private HashMap<String, String> extractStyleParameterStrings(String encodedStyleString) {
		String[] split = encodedStyleString.split("!");
		if (split.length % 2 != 0) {
			throw new IllegalArgumentException("Illegal style string: " + encodedStyleString);
		}

		HashMap<String, String> parameterKeyValue = new HashMap<>();
		for (int i = 0; i < split.length; i+=2) {
			parameterKeyValue.put(split[i], split[i + 1]);
		}
		return parameterKeyValue;
	}

}
