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
