package de.comeight.crystallogy.items.armor;

import java.util.List;

import de.comeight.crystallogy.blocks.materials.CustomArmorMaterials;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.ToolTipBuilder;
import de.comeight.crystallogy.util.armor.ArmorUtilities;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Armor_yellow extends BaseArmor {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final String ID = "armor_yellow_";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public Armor_yellow(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(CustomArmorMaterials.CRYSTALL_YELLOW, renderIndexIn, equipmentSlotIn, ID + equipmentSlotIn.getName());
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public String getID(){
		return ID + armorType.getName();
	}
	
	private boolean isPlayerWearingFullArmor(EntityPlayer player){
        return player.inventory.armorInventory[3] != null && ArmorUtilities.hasArmor(player.inventory.armorInventory[3], ItemHandler.armorHelmet_yellow)
                && player.inventory.armorInventory[2] != null && ArmorUtilities.hasArmor(player.inventory.armorInventory[2], ItemHandler.armorChestplate_yellow)
                && player.inventory.armorInventory[1] != null && ArmorUtilities.hasArmor(player.inventory.armorInventory[1], ItemHandler.armorLeggins_yellow)
                && player.inventory.armorInventory[0] != null && ArmorUtilities.hasArmor(player.inventory.armorInventory[0], ItemHandler.armorBoots_yellow);
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if(world.isRemote){
			return;
		}
		if (isPlayerWearingFullArmor(player)) {
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(3), 1, 1, true, false));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			tooltip.add(TextFormatting.DARK_PURPLE + "When completely equipped:");
			tooltip.add(TextFormatting.BLUE + "-Haste");
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}
