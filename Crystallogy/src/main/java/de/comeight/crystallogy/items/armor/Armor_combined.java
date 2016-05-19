package de.comeight.crystallogy.items.armor;

import java.util.List;

import de.comeight.crystallogy.blocks.materials.CustomArmorMaterials;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.ToolTipBuilder;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Armor_combined extends BaseArmor {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final String ID = "armor_combined_";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public Armor_combined(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(CustomArmorMaterials.CRYSTALL_COMBINED, renderIndexIn, equipmentSlotIn, ID + equipmentSlotIn.getName());
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public String getID(){
		return ID + armorType.getName();
	}
	
	public boolean isPlayerWearingFullArmor(EntityPlayer player){
		if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ItemHandler.armorHelmet_combined
		        && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ItemHandler.armorChestplate_combined
		        && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ItemHandler.armorLeggins_combined
		        && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ItemHandler.armorBoots_combined) {
		        return true;
		}
		return false;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		int index = -1;
		if (!isPlayerWearingFullArmor(player) || (index = hasEnergyCrystal(player)) == -1) {
			disableCapabilities(world, player);
		}
		else{
			if(itemStack.getItem() == ItemHandler.armorChestplate_combined){
				enableCapabilities(world, player);
				
				if(!world.isRemote){
					if(player.capabilities.isFlying){
						damageEnergyCrystal(player, index);
					}
					else{
						if(Utilities.getRandInt(0, 5) == 0){
							damageEnergyCrystal(player, index);
						}
					}
				}
			}
		}
	}
	
	private void enableCapabilities(World world, EntityPlayer player){
		player.capabilities.allowFlying = true;
		player.capabilities.disableDamage = true;
		if(!world.isRemote){
			player.capabilities.setPlayerWalkSpeed(0.12F);
			player.capabilities.setFlySpeed(0.06F);	
		}
	}
	
	private void disableCapabilities(World world, EntityPlayer player){
		player.capabilities.allowFlying = false;
		player.capabilities.isFlying = false;
		player.capabilities.disableDamage = false;
		if(!world.isRemote){
			player.capabilities.setPlayerWalkSpeed(0.1F);
			player.capabilities.setFlySpeed(0.05F);
		}
	}
	
	private int hasEnergyCrystal(EntityPlayer player){
		for(int i = 0; i < player.inventory.mainInventory.length; i++){
			if(player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == ItemHandler.energyCrystal){
				if(player.inventory.mainInventory[i].getItemDamage() < player.inventory.mainInventory[i].getMaxDamage()){
					return i;
				}
			}
		}
		return -1;
	}
	
	private void damageEnergyCrystal(EntityPlayer player, int index){
		player.inventory.getStackInSlot(index).damageItem(1, player);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			tooltip.add(TextFormatting.DARK_PURPLE + "When completely equipped:");
			tooltip.add(TextFormatting.BLUE + "-Flight");
			tooltip.add(TextFormatting.BLUE + "-Full damage protection (except void damage)");
			tooltip.add(TextFormatting.BLUE + "+20% Speed");
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}