package de.comeight.crystallogy.items.armor;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.blocks.materials.CustomArmorMaterials;
import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Armor_combined extends BaseArmor implements ISpecialArmor{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final String ID = "armor_combined_";
	
	private ArrayList<ItemArmor> armorList = new ArrayList<ItemArmor>();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public Armor_combined(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(CustomArmorMaterials.CRYSTALL_COMBINED, renderIndexIn, equipmentSlotIn, ID + equipmentSlotIn.getName());
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public String getID(){
		return ID + armorType.getName();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void addArmor(ItemStack itemStackIn, ItemArmor armor){
		if(itemStackIn.getItem() instanceof Armor_combined){
			Armor_combined armor2 = (Armor_combined) itemStackIn.getItem();
			armor2.armorList.add(armor);
		}
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		for (ItemArmor armor : armorList) {
			armor.onArmorTick(world, player, itemStack);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			for (ItemArmor armor : armorList) {
				tooltip.add(TextFormatting.AQUA + armor.getItemStackDisplayName(new ItemStack(armor)));
				armor.addInformation(stack, playerIn, tooltip, advanced);
				tooltip.add("");
			}
		}
		else{
			tooltip.add("Size: " + armorList.size());
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return null;
	}
	

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		int iArmor = 10;
		return iArmor;
	}
	

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		for (ItemArmor armor : armorList) {
			if(armor instanceof ISpecialArmor){
				ISpecialArmor armor2 = (ISpecialArmor) armor;
				armor2.damageArmor(entity, stack, source, damage, slot);
			}
		}
	}
	
}