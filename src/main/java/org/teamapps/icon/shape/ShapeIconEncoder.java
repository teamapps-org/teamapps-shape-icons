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
		return FILL_COLOR + "," + style.getFillColor().toHtmlColorString().substring(1)
				+ "," + STROKE_COLOR + "," + style.getStrokeColor().toHtmlColorString().substring(1)
				+ "," + STROKE_WIDTH + "," + style.getStrokeWidth()
				+ "," + SHADOW + "," + style.isShadow()
				+ "," + SCALING + "," + style.getScaling()

				;
	}

}
