package org.teamapps.icon.shape;

import org.teamapps.common.format.Color;

public class ShapeIconStyle {

	private final Color fillColor;
	private final Color strokeColor;
	private final float strokeWidth;
	private final boolean shadow;
	private final float scaling;

	public static ShapeIconStyleBuilder builder() {
		return new ShapeIconStyleBuilder();
	}

	public ShapeIconStyle(Color fillColor, Color strokeColor, float strokeWidth, boolean shadow, float scaling) {
		this.fillColor = fillColor;
		this.strokeColor = strokeColor;
		this.strokeWidth = strokeWidth;
		this.shadow = shadow;
		this.scaling = scaling;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public float getStrokeWidth() {
		return strokeWidth;
	}

	public boolean isShadow() {
		return shadow;
	}

	public float getScaling() {
		return scaling;
	}
}
