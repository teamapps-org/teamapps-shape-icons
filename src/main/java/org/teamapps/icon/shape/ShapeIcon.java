package org.teamapps.icon.shape;

import org.teamapps.icons.Icon;
import org.teamapps.icons.spi.IconLibrary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@IconLibrary(
		name = "shape",
		encoder = ShapeIconEncoder.class,
		decoder = ShapeIconDecoder.class,
		loader = ShapeIconLoader.class,
		defaultStyleSupplier = ShapeIconDefaultStyleSupplier.class
)
public class ShapeIcon implements Icon<ShapeIcon, ShapeIconStyle> {

	private static final Map<String, ShapeIcon> ICONS_BY_CONSTANT_NAME = new HashMap<>();
	private static final Map<String, ShapeIcon> ICONS_BY_ICON_NAME = new HashMap<>();

	public static final ShapeIcon CIRCLE = createInternal("circle", "CIRCLE");
	public static final ShapeIcon HEXAGON = createInternal("hexagon", "HEXAGON");
	public static final ShapeIcon PENTAGON = createInternal("pentagon", "PENTAGON");
	public static final ShapeIcon SQUARE = createInternal("square", "SQUARE");
	public static final ShapeIcon TRIANGLE = createInternal("triangle", "TRIANGLE");

	public static ShapeIcon valueOf(String constantName) {
		return ICONS_BY_CONSTANT_NAME.get(constantName);
	}

	public static ShapeIcon forName(String constantName) {
		return ICONS_BY_ICON_NAME.get(constantName);
	}

	public static Collection<ShapeIcon> values() {
		return ICONS_BY_ICON_NAME.values();
	}

	private final String iconName;
	private final String constantName;
	private final ShapeIconStyle style;

	private ShapeIcon(String iconName, String constantName) {
		this(iconName, constantName, null);
	}

	private ShapeIcon(String iconName, String constantName, ShapeIconStyle style) {
		this.iconName = iconName;
		this.constantName = constantName;
		this.style = style;
	}

	private static ShapeIcon createInternal(String name, String constantName) {
		ShapeIcon standardIcon = new ShapeIcon(name, constantName);
		ICONS_BY_ICON_NAME.put(name, standardIcon);
		ICONS_BY_CONSTANT_NAME.put(constantName, standardIcon);
		return standardIcon;
	}

	public String getIconName() {
		return iconName;
	}

	public String constantName() {
		return constantName;
	}

	@Override
	public ShapeIcon withStyle(ShapeIconStyle style) {
		return new ShapeIcon(iconName, constantName, style);
	}

	@Override
	public ShapeIconStyle getStyle() {
		return style;
	}

	public static ShapeIconStyleBuilder styleBuilder() {
		return ShapeIconStyle.builder();
	}

}
