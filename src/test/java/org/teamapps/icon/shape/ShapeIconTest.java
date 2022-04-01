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

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.teamapps.common.format.Color;
import org.teamapps.icons.IconResource;

import java.io.File;
import java.io.IOException;

class ShapeIconTest {

	@Test
	void testLoadIcon() throws IOException {
		for (ShapeIcon icon : ShapeIcon.allIcons()) {
			icon = icon.withStyle(ShapeIconStyle.builder()
					.setFillColor(Color.BEIGE.withAlpha(.5f))
					.setStrokeColor(Color.MATERIAL_BLUE_900)
					.setStrokeWidth(5)
					.setShadow(true)
					.setScaling(.5f)
					.build());
			String encoded = new ShapeIconEncoder().encodeIcon(icon, icon1 -> null);
			System.out.println(encoded);
			ShapeIcon decodedIcon = new ShapeIconDecoder().decodeIcon(encoded, qualifiedEncodedIcon -> null);
			IconResource iconResource = new ShapeIconLoader().loadIcon(decodedIcon, 24, (icn, ctx) -> null);
			FileUtils.writeByteArrayToFile(new File(decodedIcon.getIconName() + ".svg"), iconResource.getBytes());
		}
	}
}
