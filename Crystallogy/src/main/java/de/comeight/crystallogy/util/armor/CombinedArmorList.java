package de.comeight.crystallogy.util.armor;

import java.util.LinkedList;
import java.util.UUID;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CombinedArmorList {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static LinkedList<ArmorListEntry> list = new LinkedList<ArmorListEntry>();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CombinedArmorList() {
		
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static void addArmor(ItemStack armor, UUID id){
		int index = -1;
		if((index = findEntryPos(id)) < 0){
			ArmorListEntry entry = new ArmorListEntry(id);
			entry.addArmor(armor);
			list.add(entry);
		}
		else{
			list.get(index).addArmor(armor);
		}
		
	}
	
	public static ArmorListEntry getEntry(int index){
		return list.get(index);
	}
	
	/**
	 * Returns the corosponding ArmorListEntry for the given {@link UUID}
	 * without removing it from the list.
	 * 
	 * @param id the {@link UUID} of the ArmorListEntry you want to get back
	 */
	public static ArmorListEntry getEntry(UUID id){
		for (ArmorListEntry armorListEntry : list) {
			if(armorListEntry.id.compareTo(id) == 0){
				return armorListEntry;
			}
		}
		return null;
	}
	
	/**
	 * Returns the corosponding ArmorListEntry for the given {@link UUID}
	 * with removing it from the list.
	 * 
	 * @param id the {@link UUID} of the ArmorListEntry you want to get back
	 */
	public static ArmorListEntry removeEntry(UUID id){
		int index = findEntryPos(id);
		if(index < 0){
			return null;
		}
		
		ArmorListEntry ret = list.get(index);
		list.remove(index);
		return ret;
		
	}
	
	/**
	 * Returns the corosponding ArmorListEntry for the given {@link int}
	 * with removing it from the list.
	 * 
	 * @param index the index of the ArmorListEntry you want to get back
	 */
	public static ArmorListEntry removeEntry(int index){
		if(index < 0){
			return null;
		}
		
		ArmorListEntry ret = list.get(index);
		list.remove(index);
		return ret;
		
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	/**
	 * Finds and returns an entry in the ArmorListEntryList.
	 * <p>
	 * Returns -1 if no entry matches the given {@link UUID}.
	 * 
	 * @param id the {@link UUID} of the ArmorListEntry you want to get the position back
	 */
	public static int findEntryPos(UUID id){
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).id.compareTo(id) == 0){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Checks whether there is a entry avaidable for the given {@link UUID}. 
	 * 
	 * @param id the {@link UUID} of the ArmorListEntry you try to find
	 */
	public static boolean hasEntry(UUID id){
		if(findEntryPos(id) < 0){
			return false;
		}
		return true;
	}
	
	public static void writeToNBT(UUID id, NBTTagCompound compound){
		ArmorListEntry entry = getEntry(id);
		if(entry != null){
			entry.writeToNBT(compound);
		}
	}
	
	public static void readFromNBT(NBTTagCompound compound){
		UUID id = compound.getUniqueId("id");
		int index = -1;
		
		if((index = findEntryPos(id)) < 0){
			list.add(new ArmorListEntry(compound));
		}
		else{
			list.get(index).readFromNBT(compound);
		}
	}
}
