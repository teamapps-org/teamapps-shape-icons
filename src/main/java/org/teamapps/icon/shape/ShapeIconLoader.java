package org.teamapps.icon.shape;

import org.apache.commons.io.IOUtils;
import org.teamapps.icons.IconLoaderContext;
import org.teamapps.icons.IconResource;
import org.teamapps.icons.IconType;
import org.teamapps.icons.spi.IconLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.teamapps.icon.shape.ShapeIconEncoder.*;

public class ShapeIconLoader implements IconLoader<ShapeIcon> {

	@Override
	public IconResource loadIcon(org.teamapps.icon.shape.ShapeIcon icon, int size, IconLoaderContext context) {
		String svg = getSVG(icon.getIconName(), icon.getStyle());
		return svg != null ? new IconResource(svg.getBytes(StandardCharsets.UTF_8), IconType.SVG) : null;
	}

	private String getSVG(String iconName, ShapeIconStyle style) {
		if (!iconName.endsWith(".svg")) {
			iconName += ".svg";
		}

		String path = "/org/teamapps/icon/shape/" + iconName;

		try (InputStream inputStream = getClass().getResourceAsStream(path)) {
			if (inputStream == null) {
				return null;
			}
			String svg = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			if (style == null) {
				style = ShapeIconDefaultStyleSupplier.DEFAULT_STYLE;
			}
			svg = applyStyle(svg, style);
			return svg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String applyStyle(String svg, ShapeIconStyle style) {
		svg = insertPlaceHolder(svg, FILL_COLOR, style.getFillColor().toHtmlColorString());
		svg = insertPlaceHolder(svg, STROKE_COLOR, style.getStrokeColor().toHtmlColorString());
		svg = insertPlaceHolder(svg, STROKE_WIDTH, "" + style.getStrokeWidth());
		svg = insertPlaceHolder(svg, "filter", style.isShadow() ? "url(#shadow)" : "none");
		svg = insertPlaceHolder(svg, "transform", style.getScaling() != 1 ? "scale(" + (style.getScaling() * 100) + "%)" : "none");
		return svg;
	}

	private String insertPlaceHolder(String svg, String placeholderName, String value) {
		return svg.replace("@" + placeholderName + "@", value);
	}

}
