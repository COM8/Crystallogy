package de.comeight.crystallogy.items.armor;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import de.comeight.crystallogy.blocks.materials.CustomArmorMaterials;
import de.comeight.crystallogy.util.ToolTipBuilder;
import de.comeight.crystallogy.util.Utilities;
import de.comeight.crystallogy.util.armor.ArmorListEntry;
import de.comeight.crystallogy.util.armor.CombinedArmorList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Armor_combined extends BaseArmor implements ISpecialArmor{
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
	
	public UUID getArmorListEntryId(ItemStack itemStackIn){
		if(!itemStackIn.hasTagCompound()){
			createNewTagCompound(itemStackIn);
		}
		if(!itemStackIn.getTagCompound().hasUniqueId("armorListEntryId")){
			createNewIdTag(itemStackIn);
		}
		return itemStackIn.getTagCompound().getUniqueId("armorListEntryId");
	}
	
	public LinkedList<ItemStack> getArmorList(ItemStack itemStackIn){
		ArmorListEntry entry = CombinedArmorList.getEntry(getArmorListEntryId(itemStackIn));
		if(entry != null){
			return entry.getList();
		}
		else{
			return null;
		}
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void addArmor(ItemStack itemStackIn, ItemStack armor){
		if(itemStackIn.getItem() instanceof Armor_combined){
			Armor_combined armorCombined = (Armor_combined) itemStackIn.getItem();
			UUID id = armorCombined.getArmorListEntryId(itemStackIn);
			CombinedArmorList.addArmor(armor, id);
			
			armorCombined.save(itemStackIn);
		}
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		LinkedList<ItemStack> list = getArmorList(itemStack);
		if(list == null){
			return;
		}
		
		for (ItemStack armorStack : list) {
			ItemArmor armor = (ItemArmor) armorStack.getItem();
			armor.onArmorTick(world, player, itemStack);
		}
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(Utilities.getRandInt(0, 10) == 0 || System.currentTimeMillis() % 50 == 0){
			manageArmor(itemStack);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		LinkedList<ItemStack> list = getArmorList(stack);
		
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					ItemArmor armor = (ItemArmor) list.get(i).getItem();
					tooltip.add(TextFormatting.DARK_AQUA + String.valueOf(i + 1) + ": " + TextFormatting.RESET + armor.getItemStackDisplayName(new ItemStack(armor)));
					armor.addInformation(stack, playerIn, tooltip, advanced);
					tooltip.add("");
				}
			}
			else{
				tooltip.add(TextFormatting.DARK_AQUA + "No armor added!");
			}
		}
		else{
			if(list != null){
				tooltip.add(TextFormatting.DARK_AQUA + "Size: " + list.size());
			}
			else{
				tooltip.add(TextFormatting.DARK_AQUA + "Size: 0");
			}
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		ArmorProperties ap = new ArmorProperties(1, 2.0, 1);
		return ap;
	}
	
	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		int iArmor = 0;
		return iArmor;
	}
	
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		LinkedList<ItemStack> list = getArmorList(stack);
		if(list == null){
			return;
		}
		
		for (ItemStack armorStack : list) {
			ItemArmor armor = (ItemArmor) armorStack.getItem();
			if(armor instanceof ISpecialArmor){
				ISpecialArmor armor2 = (ISpecialArmor) armor;
				armor2.damageArmor(entity, stack, source, damage, slot);
			}
		}
	}
	
	private void createNewTagCompound(ItemStack itemStackIn){ 
		itemStackIn.setTagCompound(new NBTTagCompound());
	}
	
	private void createNewIdTag(ItemStack itemStackIn){
		NBTTagCompound compound = itemStackIn.getTagCompound();
		compound.setUniqueId("armorListEntryId", UUID.randomUUID());
		itemStackIn.setTagCompound(compound);
	}
	
	private void save(ItemStack itemStackIn){
		UUID id = getArmorListEntryId(itemStackIn);
		CombinedArmorList.writeToNBT(id, itemStackIn.getTagCompound());
	}
	
	private void read(ItemStack itemStackIn){
		if(itemStackIn.hasTagCompound()){
			CombinedArmorList.readFromNBT(itemStackIn.getTagCompound());
		}
	}
	
	private boolean hasArmorIdStored(ItemStack itemStackIn){
		if(itemStackIn.hasTagCompound() && itemStackIn.getTagCompound().hasUniqueId("armorListEntryId")){
			return true;
		}
		return false;
	}
	
	private void manageArmor(ItemStack itemStackIn){
		if(hasArmorIdStored(itemStackIn)){
			read(itemStackIn);
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
}