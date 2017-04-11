package de.comeight.crystallogy.util;

public enum EnumCustomAis {
	EMPTY(0, "empty"),
	MOVE_TO_POS(1, "moveToPos"),
	FOLLOW_PLAYER(2, "followPlayer"),
	QUARRY(3, "quarry"),
	PICKUP_ITEMS(4, "pickupItems");
	
	
	public final int ID;
	public final String NAME;
	
	private EnumCustomAis(int id, String name){
		this.ID = id;
		this.NAME = name;
	}
	
	public static EnumCustomAis fromID(int id){
		for(EnumCustomAis ai: values()){
			if(ai.ID == id){
				return ai;
			}
		}
		return null;
	}
}
