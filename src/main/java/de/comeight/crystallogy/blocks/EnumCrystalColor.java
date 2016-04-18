package de.comeight.crystallogy.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.item.ItemStack;

public enum EnumCrystalColor {
	RED(0, "red", MapColor.redColor),
	BLUE(1, "blue", MapColor.blueColor),
	GREEN(2, "green", MapColor.greenColor),
	YELLOW(3, "yellow", MapColor.yellowColor);
	
    private static final EnumCrystalColor[] META_LOOKUP = new EnumCrystalColor[values().length];
    private final int meta;
    private final String name;
    private final MapColor mapColor;
    
    private EnumCrystalColor(int meta, String name, MapColor mapColorIn)
    {
        this.meta = meta;
        this.name = name;
        this.mapColor = mapColorIn;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public MapColor getMapColor()
    {
        return this.mapColor;
    }

    public static EnumCrystalColor byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String toString()
    {
        return this.name;
    }

    public String getName()
    {
        return this.name;
    }
    
    public ItemStack getStack(ItemStack stack){
    	stack.setItemDamage(meta);
    	return stack;
    }

    static
    {
        for (EnumCrystalColor enumCrystalColor : values())
        {
            META_LOOKUP[enumCrystalColor.getMetadata()] = enumCrystalColor;
        }
    }
}
