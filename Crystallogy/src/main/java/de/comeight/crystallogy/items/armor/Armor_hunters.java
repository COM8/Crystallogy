package de.comeight.crystallogy.items.armor;

import java.util.List;

import de.comeight.crystallogy.blocks.materials.CustomArmorMaterials;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.ToolTipBuilder;
import de.comeight.crystallogy.util.Utilities;
import de.comeight.crystallogy.util.armor.ArmorUtilities;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Armor_hunters extends BaseArmor {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final String ID = "armor_hunter_";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public Armor_hunters(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(CustomArmorMaterials.CRYSTALL_HUNTER, renderIndexIn, equipmentSlotIn, ID + equipmentSlotIn.getName());
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public String getID(){
		return ID + armorType.getName();
	}
	
	private boolean isPlayerWearingFullArmor(EntityPlayer player){
		if (player.inventory.armorInventory[3] != null && ArmorUtilities.hasArmor(player.inventory.armorInventory[3], ItemHandler.armorHelmet_hunter)
		        && player.inventory.armorInventory[2] != null && ArmorUtilities.hasArmor(player.inventory.armorInventory[2], ItemHandler.armorChestplate_hunter)
		        && player.inventory.armorInventory[1] != null && ArmorUtilities.hasArmor(player.inventory.armorInventory[1], ItemHandler.armorLeggins_hunter)
		        && player.inventory.armorInventory[0] != null && ArmorUtilities.hasArmor(player.inventory.armorInventory[0], ItemHandler.armorBoots_hunter)) {
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
			if(itemStack.getItem() == ItemHandler.armorChestplate_hunter){
				enableCapabilities(world, player);
				player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 1, 1, true, false));
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
		if(!player.capabilities.isCreativeMode){
			player.capabilities.allowFlying = true;
			player.capabilities.disableDamage = true;
		}
	}
	
	private void disableCapabilities(World world, EntityPlayer player){
		if(!player.capabilities.isCreativeMode){
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
			player.capabilities.disableDamage = false;
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
			tooltip.add("");
			tooltip.add(TextFormatting.DARK_RED + "Requires a charged Energy Crystal!");
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}