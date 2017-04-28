package de.comeight.crystallogy.util.enums;

public enum EnumCrystalColor {

    RED(0, "red"),
    BLUE(1, "blue"),
    GREEN(2, "green"),
    YELLOW(3, "yellow"),
    WHITE(4, "white");

    private final int meta;
    private final String name;

    EnumCrystalColor(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMeta() {
        return meta;
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
