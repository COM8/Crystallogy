package de.comeight.crystallogy.util;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.items.threatDusts.ThreatDust;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public enum EnumThreats{
	POISON(new RGBColor(0.0F, 1.0F, 0.0F), ItemHandler.poisDust, 0),
	DAMAGE(new RGBColor(1.0F, 0.2F, 0.2F), ItemHandler.damDust, 1),
	FIRE(new RGBColor(1.0F, 0.0F, 0.0F), ItemHandler.fireDust, 2),
	DROWN(new RGBColor(0.0F, 0.0F, 1.0F), ItemHandler.drowDust, 3),
	HUNGER(new RGBColor(0.5F, 0.5F, 0.5F), ItemHandler.hungDust, 4),
	BADLUCK(new RGBColor(0.2F, 0.1F, 0.0F), ItemHandler.badLuckDust, 5),
	BLIND(new RGBColor(0.1F, 0.1F, 0.1F), ItemHandler.blindDust, 6),
	ENDER(new RGBColor(0.5F, 0.0F, 0.5F), ItemHandler.enderDust, 7),
	GLOW(new RGBColor(0.5F, 0.1F, 0.5F), ItemHandler.glowDust, 8),
	LEVITATION(new RGBColor(0.3F, 0.3F, 1.0F), ItemHandler.levDust, 9),
	AIREMOVER(new RGBColor(0.1F, 0.1F, 0.1F), ItemHandler.aiRemoverDust, 10);
	
	private final int id;
	private final RGBColor color;
	private final ThreatDust threatDust;
	
	EnumThreats(RGBColor color, ThreatDust threatDust, int id){
		this.color = color;
		this.threatDust = threatDust;
		this.id = id;			
	}
	
	public static EnumThreats getThreatDust(ItemStack stack){
		if(!(stack.getItem() instanceof ThreatDust)){
			return null;
		}
		
		for(EnumThreats threat : values()){
			if(threat.threatDust == stack.getItem()){
				return threat;
			}
		}
		return null;
	}
	
	public int getNumOfCalls(){
		return threatDust.numOfCalls;
	}
	
	public int getTicksBetweenCalls(){
		return threatDust.getTicksBetweenCalls();
	}
	
	public void castOnEntity(World worldIn, EntityLivingBase entity, int count){
		threatDust.castOnEntity(worldIn, entity, count);
	}
	
	public int getID(){
		return id;
	}
	
	public RGBColor getColor(){
		return color;
	}
	
	public NBTTagCompound toNBTTagCompound(NBTTagCompound compound, String key){
		compound.setInteger(key + "_EnumThreat", id);
		return compound;
	}
	
	public static EnumThreats fromNBTTagCompound(NBTTagCompound compound, String key){
		int _id = compound.getInteger(key + "_EnumThreat");
		return fromID(_id);
	}
	
	public static EnumThreats fromID(int _id){
		for(EnumThreats threat : values()){
			if(threat.getID() == _id){
				return threat;
			}
		}
		return null;
	}
	
}
