package de.comeight.crystallogy.util.enums;

public enum ToolClass {
    PICKAXE(0, "pickaxe"),
    SHOVEL(1, "shovel"),
    AXE(2, "axe");
    private final int ID;
    private final String NAME;

    ToolClass(int id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    public String getName() {
        return NAME;
    }
}
