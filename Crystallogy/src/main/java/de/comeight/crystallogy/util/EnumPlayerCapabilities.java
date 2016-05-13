package de.comeight.crystallogy.util;

import net.minecraft.entity.player.EntityPlayer;

public enum EnumPlayerCapabilities {
	FLIGHT(0, "flight", false),
	DAMAGE(1, "damage", false),
	SPEED(2, "speed", false);
	
	private int id;
	private String name;
	private boolean enabled;
	private static final EnumPlayerCapabilities[] LOOKUP = new EnumPlayerCapabilities[values().length];
	
	private EnumPlayerCapabilities(int id, String name, boolean enabled){
		this.id = id;
		this.name = name;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public static EnumPlayerCapabilities getById(int id){
		return LOOKUP[id];
	}
	
	public void setCapabilityForPlayer(EntityPlayer player, boolean enable){
		if(enable){
			switch (id) {
				case 0:
					player.capabilities.allowFlying = true;
					break;
				case 1:
					player.capabilities.disableDamage = true;
					break;
				case 2:
					player.capabilities.setPlayerWalkSpeed(0.12F);
					player.capabilities.setFlySpeed(0.06F);
					break;
				default:
					Log.error("Unknown Capabilities id:" + id);
					break;
			}
		}
		else{
			switch (id) {
				case 0:
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					break;
				case 1:
					player.capabilities.disableDamage = false;
					break;
				case 2:
					player.capabilities.setPlayerWalkSpeed(0.1F);
					player.capabilities.setFlySpeed(0.05F);
					break;
				default:
					Log.error("Unknown Capabilities id:" + id);
					break;
			}
		}
	}
	
	static{
		for(EnumPlayerCapabilities enumPlayerCapabilities: values()){
			LOOKUP[enumPlayerCapabilities.getId()] = enumPlayerCapabilities;
		}
	}
	
}
