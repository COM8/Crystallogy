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
	public static boolean hasEntity(ItemStack stack){
		return hasEntity(stack.getTagCompound());
	}
	
	public static boolean hasEntity(NBTTagCompound compound){
		if(compound != null && compound.getBoolean("hasEntity")){
			return true;
		}
		return false;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
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
	
	public static Entity readEntityFromCompound(NBTTagCompound compound, World worldIn){
		Entity ent = null;
		if(compound.getBoolean("hasEntity")){
			ent = EntityList.createEntityByName(compound.getString("id_entity"), worldIn);
			ent.readFromNBT(compound);
		}
		return ent;
	}
	
}
