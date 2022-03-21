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

		for (ShapeIcon icon : ShapeIcon.values()) {
			icon = icon.withStyle(ShapeIconStyle.builder()
							.setFillColor(Color.BEIGE.withAlpha(.5f))
							.setStrokeColor(Color.MATERIAL_BLUE_900)
							.setStrokeWidth(5)
							.setShadow(true)
							.setScaling(.5f)
							.build());
			IconResource iconResource = new ShapeIconLoader().loadIcon(icon, 24, (icn, ctx) -> null);
			FileUtils.writeByteArrayToFile(new File(icon.getIconName() + ".svg"), iconResource.getBytes());
		}


	}
}