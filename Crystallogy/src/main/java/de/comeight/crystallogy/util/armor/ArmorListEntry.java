package de.comeight.crystallogy.util.armor;

import java.util.LinkedList;
import java.util.UUID;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ArmorListEntry {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public UUID id;
	private LinkedList<ItemStack> list;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ArmorListEntry(UUID id) {
		this.id = id;
		this.list = new LinkedList<ItemStack>();
	}
	
	public ArmorListEntry(NBTTagCompound compound) {
		readFromNBT(compound);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	/**
	 * Adds a specific {@link ItemStack} the the list.
	 * <p>
	 * It checks whether the {@link ItemStack} is null or it's content
	 * is not a instace of {@link ItemArmor}
	 * 
	 * @param armor is representing a {@link ItemStack} filled with a {@link ItemArmor} 
	 */
	public void addArmor(ItemStack armor){
		if(armor == null){
			throw new NullPointerException("ItemStack == null! This is NOT allowed");
		}
		if(!(armor.getItem() instanceof ItemArmor)){
			throw new IllegalArgumentException("Item is no instance of ItemArmor!");
		}
		
		list.add(armor);
	}
	
	public ItemStack getArmor(int index){
		return list.get(index);
	}
	
	public LinkedList<ItemStack> getList() {
		return list;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void writeToNBT(NBTTagCompound compound){
		NBTTagList armorList = new NBTTagList();
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) != null){
				NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                list.get(i).writeToNBT(stackTag);
                armorList.appendTag(stackTag);
			}
		}
		
		compound.setTag("armorList", armorList);
		compound.setUniqueId("id", id);
	}
	
	public void readFromNBT(NBTTagCompound compound){
		list = new LinkedList<ItemStack>();
		id = compound.getUniqueId("id");
		
		NBTTagList armorList = compound.getTagList("armorList", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < armorList.tagCount(); i++)
        {
            NBTTagCompound stackTag = armorList.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot");

            if (slot >= 0){
            	list.add(ItemStack.loadItemStackFromNBT(stackTag));
            }
        }
	}
}
