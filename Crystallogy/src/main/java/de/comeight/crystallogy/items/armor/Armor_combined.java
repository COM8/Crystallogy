package de.comeight.crystallogy.items.armor;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.blocks.materials.CustomArmorMaterials;
import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Armor_combined extends BaseArmor {
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
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack stack = playerIn.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
		ItemArmor itemArmor = (ItemArmor) stack.getItem();
		armorList.add(itemArmor);
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
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
			for (ItemArmor armor : armorList) {
				armor.addInformation(stack, playerIn, tooltip, advanced);
				tooltip.add("");
			}
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}