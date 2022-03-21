package org.teamapps.icon.shape;

import org.teamapps.common.format.Color;

public class ShapeIconStyleBuilder {

	private Color fillColor = Color.RED;
	private Color strokeColor = Color.TRANSPARENT;
	private float strokeWidth = 10;
	private boolean shadow = true;
	private float scaling = 1f;

	public ShapeIconStyleBuilder setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		return this;
	}

	public ShapeIconStyleBuilder setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
		return this;
	}

	public ShapeIconStyleBuilder setStrokeWidth(float strokeWidth) {
		this.strokeWidth = strokeWidth;
		return this;
	}

	public ShapeIconStyleBuilder setShadow(boolean shadow) {
		this.shadow = shadow;
		return this;
	}

	public ShapeIconStyleBuilder setScaling(float scaling) {
		this.scaling = scaling;
		return this;
	}

	public ShapeIconStyle build() {
		return new ShapeIconStyle(fillColor, strokeColor, strokeWidth, shadow, scaling);
	}
}