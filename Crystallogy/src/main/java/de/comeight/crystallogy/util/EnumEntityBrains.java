package de.comeight.crystallogy.util;

import net.minecraft.item.ItemStack;

public enum EnumEntityBrains {
	NORMAL(0, "normal"),
	SMALL(1, "small"),
	TINY(2, "tiny"),
	WALENUT(3, "walnut");
	
	private final int META;
	private final String NAME;
	
	private EnumEntityBrains(int meta, String name){
		this.META = meta;
		this.NAME = name;
	}
	
	public static EnumEntityBrains byMetadata(int meta){
		if(meta < 0 || meta >= values().length){
			return values()[0];
		}
		return values()[meta];
	}
	
	public String getName(){
		return NAME;
	}
	
	public ItemStack getStack(ItemStack stack){
    	stack.setItemDamage(META);
    	return stack;
    }
	
	@Override
	public String toString() {
		return NAME;
	}
}
