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
