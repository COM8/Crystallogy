package de.comeight.crystallogy.util;

public enum EnumDebugToolModes {
	BLOCKUPDATE(0, "Force block update"),
	REQSYNC(1, "Reqest synchronisation from server"),
	SYNC(2, "Synchronise to server"),
	SPAWNPARTICLES(3, "Spawn particle");
	
	private final int id;
	private final String name;
	
	private EnumDebugToolModes(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public static EnumDebugToolModes fromID(int id){
		for(EnumDebugToolModes mode : values()){
			if(mode.getId() == id){
				return mode;
			}
		}
		return null;
	}
	
	public static int getNextMode(int modeCurrent){
		if(modeCurrent == values().length - 1){
			return 0;
		}
		else{
			return ++modeCurrent;
		}
	}
}
