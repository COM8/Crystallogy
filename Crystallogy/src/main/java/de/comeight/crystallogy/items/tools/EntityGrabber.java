package de.comeight.crystallogy.items.tools;

import java.util.List;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.items.BaseItem;
import de.comeight.crystallogy.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;

public class EntityGrabber extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "entityGrabber";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityGrabber() {
		super(ID);
		setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Right klick to grab an entity.");
		tooltip.add("Requires an empty " + TextFormatting.UNDERLINE + TextFormatting.DARK_PURPLE + "Crystal Of Holding" + TextFormatting.RESET + "!");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(playerIn.worldObj.isRemote){
			return false;
		}
		ItemStack cOH = findEmptyCrystalOfHolding(playerIn);
		if(target instanceof EntityPlayer || (cOH = findEmptyCrystalOfHolding(playerIn)) == null){
			return false;
		}
		ItemStack fullCrystalOfHolding = catchEntity(target, cOH);
		if (!playerIn.capabilities.isCreativeMode)
        {
			if(cOH.stackSize > 1){
				--cOH.stackSize;
			}
			else{
				playerIn.inventory.mainInventory[hasEmptyCrystalOfHolding(playerIn)] = null;
			}
        }
		if (!playerIn.inventory.addItemStackToInventory(fullCrystalOfHolding))
        {
			playerIn.dropItem(fullCrystalOfHolding, false);
        }
		return true;
	}
	
	private ItemStack findEmptyCrystalOfHolding(EntityPlayer player){
		int index = hasEmptyCrystalOfHolding(player);
		if(index == -1){
			return null;
		}
		return player.inventory.getStackInSlot(index);
	}
	
	private int hasEmptyCrystalOfHolding(EntityPlayer player){
		for(int i = 0; i < player.inventory.mainInventory.length; i++){
			if(player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == Item.getItemFromBlock(BlockHandler.crystalOfHolding)){
				if(!EntityUtils.hasEntity(player.inventory.mainInventory[i].getTagCompound())){
					return i;
				}
			}
		}
		return -1;
	}
	
	private ItemStack catchEntity(Entity ent, ItemStack stack){
		NBTTagCompound compound = new NBTTagCompound();
		
		EntityUtils.writeEntityToCompound(compound, ent);
		ItemStack fullCrystalOfHolding = new ItemStack(BlockHandler.crystalOfHolding);
		fullCrystalOfHolding.setTagCompound(compound);
		ent.worldObj.removeEntity(ent);
		
		return fullCrystalOfHolding;
	}
	
}
