package org.teamapps.icon.shape;

import org.teamapps.icons.spi.DefaultStyleSupplier;

public class ShapeIconDefaultStyleSupplier implements DefaultStyleSupplier<ShapeIconStyle> {

	public static final ShapeIconStyle DEFAULT_STYLE = ShapeIconStyle.builder().build();

	@Override
	public org.teamapps.icon.shape.ShapeIconStyle getDefaultStyle() {
		return DEFAULT_STYLE;
	}
	
}
