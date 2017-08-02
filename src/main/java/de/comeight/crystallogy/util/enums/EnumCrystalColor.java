package de.comeight.crystallogy.util.enums;

import de.comeight.crystallogy.util.RGBColor;

public enum EnumCrystalColor {

    RED(0, "red", new RGBColor(1.0F, 0.0F, 0.0F)),
    BLUE(1, "blue", new RGBColor(0.0F, 0.0F, 1.0F)),
    GREEN(2, "green", new RGBColor(0.0F, 1.0F, 0.0F)),
    YELLOW(3, "yellow", new RGBColor(1.0F, 1.0F, 0.0F)),
    WHITE(4, "white", new RGBColor(1.0F, 1.0F, 1.0F));

    private final int META;
    private final String NAME;
    private final RGBColor RGB_COLOR;

    EnumCrystalColor(int meta, String name, RGBColor color) {
        this.META = meta;
        this.NAME = name;
        this.RGB_COLOR = color;
    }

    public String getName() {
        return NAME;
    }

    public int getMeta() {
        return META;
    }

    public RGBColor getRGB_COLOR() {
        return RGB_COLOR;
    }

    public static EnumCrystalColor fromMeta(int meta) {
        for(EnumCrystalColor enumCrystalColor : values()) {
            if(enumCrystalColor.getMeta() == meta) {
                return enumCrystalColor;
            }
        }
        return null;
    }
}
