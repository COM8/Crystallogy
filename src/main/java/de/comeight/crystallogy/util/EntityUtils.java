package de.comeight.crystallogy.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityUtils {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	/**
	 * Checks wether the given {@link ItemStack} contains an entity
	 */
	public static boolean hasEntity(ItemStack stack){
		return hasEntity(stack.getTagCompound());
	}
	
	/**
	 * Checks wether the given {@link NBTTagCompound} contains an entity
	 */
	public static boolean hasEntity(NBTTagCompound compound){
		if(compound != null && compound.getBoolean("hasEntity")){
			return true;
		}
		return false;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	/**
	 * Writes an entity on a given {@link NBTTagCompound}.
	 * 
	 * @param compound the {@link NBTTagCompound} where the Entity gets written to
	 * @param ent the {@link Entity} which should get written down
	 */
	public static void writeEntityToCompound(NBTTagCompound compound, Entity ent){
		if(ent != null){
			compound.setBoolean("hasEntity", true);
			ent.writeToNBT(compound);
			compound.setString("id_entity", EntityList.getEntityString(ent));
		}
		else{
			compound.setBoolean("hasEntity", false);
		}
	}
	
	/**
	 * Reads an entity from a given {@link NBTTagCompound}.
	 * 
	 * @param compound the {@link NBTTagCompound} where the Entity gets read from
	 * @param world the {@link World}
	 * 
	 * @return an {@link Entity} constructed from a given {@link NBTTagCompound}
	 */
	public static Entity readEntityFromCompound(NBTTagCompound compound, World worldIn){
		Entity ent = null;
		if(compound.getBoolean("hasEntity")){
			ent = EntityList.createEntityByName(compound.getString("id_entity"), worldIn);
			ent.readFromNBT(compound);
		}
		return ent;
	}
	
}
