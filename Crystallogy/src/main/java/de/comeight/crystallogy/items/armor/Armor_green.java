package de.comeight.crystallogy.items.armor;

import java.util.List;

import de.comeight.crystallogy.blocks.materials.CustomArmorMaterials;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Armor_green extends BaseArmor {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final String ID = "armor_green_";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public Armor_green(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(CustomArmorMaterials.CRYSTALL_GREEN, renderIndexIn, equipmentSlotIn, ID + equipmentSlotIn.getName());
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public String getID(){
		return ID + armorType.getName();
	}
	
	private boolean isPlayerWearingFullArmor(EntityPlayer player){
		if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ItemHandler.armorHelmet_green
		        && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ItemHandler.armorChestplate_green
		        && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ItemHandler.armorLeggins_green
		        && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ItemHandler.armorBoots_green) {
		        return true;
		}
		return false;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if(world.isRemote){
			return;
		}
		if (isPlayerWearingFullArmor(player)) {
			player.capabilities.setPlayerWalkSpeed(0.12F);
			player.capabilities.setFlySpeed(0.06F);
		}
		else{
			if(player.capabilities.getWalkSpeed() != 0.1F){
				player.capabilities.setPlayerWalkSpeed(0.1F);
				player.capabilities.setFlySpeed(0.05F);
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			tooltip.add(TextFormatting.DARK_PURPLE + "When completely equipped:");
			tooltip.add(TextFormatting.BLUE + "+20% Speed");
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}
