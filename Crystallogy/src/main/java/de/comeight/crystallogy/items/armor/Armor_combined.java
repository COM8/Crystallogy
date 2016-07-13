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
import net.minecraft.util.EnumActionResult;
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
		setMaxDamage(100);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public String getID(){
		return ID + armorType.getName();
	}
	
	public static UUID getArmorListEntryId(ItemStack itemStackIn){
		if(!itemStackIn.hasTagCompound()){
			createNewTagCompound(itemStackIn);
		}
		if(!itemStackIn.getTagCompound().hasUniqueId("armorListEntryId")){
			createNewIdTag(itemStackIn);
		}
		return itemStackIn.getTagCompound().getUniqueId("armorListEntryId");
	}
	
	public static LinkedList<ItemStack> getArmorList(ItemStack itemStackIn){
		ArmorListEntry entry = CombinedArmorList.getEntry(getArmorListEntryId(itemStackIn));
		if(entry != null){
			return entry.getList();
		}
		else{
			return null;
		}
	}
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		LinkedList<ItemStack> list = getArmorList(armor);
		int priority = 0;
		double ratio = 0.0;
		int max = 0;
		
		if(list != null){
			for (ItemStack armorStack : list) {
				ItemArmor itemArmor = (ItemArmor) armorStack.getItem();
				if(itemArmor instanceof ISpecialArmor){
					ISpecialArmor armor2 = (ISpecialArmor) itemArmor;
					ArmorProperties temp = armor2.getProperties(player, armorStack, source, damage, slot);
					priority += temp.Priority;
					ratio += temp.AbsorbRatio;
					if((max + temp.AbsorbMax) > Integer.MAX_VALUE){
						max = Integer.MAX_VALUE;
					}
				}
				else{
					ratio += (double)itemArmor.damageReduceAmount * 0.1;
					max += itemArmor.toughness * 10;
				}
			}
		}
		ArmorProperties ap = new ArmorProperties(priority, ratio, max);
		return ap;
	}
	
	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		int iArmor = 0;
		LinkedList<ItemStack> list = getArmorList(armor);
		if(list != null){
			for (ItemStack armorStack : list) {
				ItemArmor itemArmor = (ItemArmor) armorStack.getItem();
				if(itemArmor instanceof ISpecialArmor){
					ISpecialArmor armor2 = (ISpecialArmor) itemArmor;
					iArmor += armor2.getArmorDisplay(player, armorStack, slot);
				}
				else{
					iArmor += itemArmor.getArmorMaterial().getDamageReductionAmount(CustomArmorMaterials.getSlotFromIndex(slot));
				}
			}
		}
		return iArmor;
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
		
		int dRA = 0;
		float tN = 0.0F;
		
		for (ItemStack armorStack : list) {
			ItemArmor armor = (ItemArmor) armorStack.getItem();
			armor.onArmorTick(world, player, armorStack);
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
					addArmorDurability(list.get(i), tooltip);
					armor.addInformation(list.get(i), playerIn, tooltip, advanced);
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

	@SideOnly(Side.CLIENT)
	private void addArmorDurability(ItemStack stack, List<String> tooltip){
		int maxDamage = stack.getMaxDamage();
		int damage = stack.getItemDamage();
		double percent = ((double) damage) / ((double) maxDamage); 
		
		if(percent >= 0.75){
			tooltip.add("Durability: " + TextFormatting.DARK_GREEN + (maxDamage - damage) + " / " + maxDamage);
		}
		else if(percent >= 0.5){
			tooltip.add("Durability: " + TextFormatting.GREEN + (maxDamage - damage) + " / " + maxDamage);
		}
		else if(percent >= 0.25){
			tooltip.add("Durability: " + TextFormatting.GOLD + (maxDamage - damage) + " / " + maxDamage);
		}
		else{
			tooltip.add("Durability: " + TextFormatting.RED + (maxDamage - damage) + " / " + maxDamage);
		}
	}
	
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		System.out.println("damage");
		LinkedList<ItemStack> list = getArmorList(stack);
		if(list == null){
			return;
		}
		
		for (ItemStack armorStack : list) {
			ItemArmor armor = (ItemArmor) armorStack.getItem();
			if(armor instanceof ISpecialArmor){
				ISpecialArmor armor2 = (ISpecialArmor) armor;
				armor2.damageArmor(entity, armorStack, source, damage, slot);
			}
		}
	}
	
	private static void createNewTagCompound(ItemStack itemStackIn){ 
		itemStackIn.setTagCompound(new NBTTagCompound());
	}
	
	private static void createNewIdTag(ItemStack itemStackIn){
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
		if(playerIn.isSneaking()){
			ArmorListEntry entry = CombinedArmorList.removeEntry(getArmorListEntryId(itemStackIn));
			createNewTagCompound(itemStackIn);
			createNewIdTag(itemStackIn);
			if(entry == null){
				return new ActionResult(EnumActionResult.FAIL, itemStackIn);
			}
			
			for (ItemStack armorStack : entry.getList()) {
				if (!playerIn.inventory.addItemStackToInventory(armorStack))
		        {
					playerIn.dropItem(armorStack, false);
		        }
			}
			
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}
		else{
			return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
		}
	}
	
}