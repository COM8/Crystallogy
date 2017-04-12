package de.comeight.crystallogy.items.tools;

import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.util.NBTTags;
import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerCrystalKnife extends BaseCrystalKnife{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "playerCrystalKnife";
	
	private float attackDamage;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public PlayerCrystalKnife() {
		super(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private GameProfile getGameProfile(ItemStack stack){
		NBTTagCompound c = stack.getTagCompound();
		String name = c.getString(NBTTags.ENTITY_NAME);
		String uuid = c.getString(NBTTags.ENTITY_UUID);
		GameProfile p = new GameProfile(UUID.fromString(uuid), name);
		return p;
	}
	
	@Override
	public boolean hasEntity(ItemStack stack){
		NBTTagCompound c = stack.getTagCompound();
        return !(!super.hasEntity(stack) || c.getString(NBTTags.ENTITY_UUID) == null || c.getString(NBTTags.ENTITY_UUID).equals("-") || c.getString(NBTTags.ENTITY_UUID).equals(""));
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ItemStack saveNBT(ItemStack stack, EntityLivingBase entity){
		stack = saveEmptyNBT(stack);
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			
			nbtTagCompound.setString(NBTTags.ENTITY_NAME, player.getGameProfile().getName());
			nbtTagCompound.setString(NBTTags.ENTITY_UUID, player.getGameProfile().getId().toString());
			
			stack.setTagCompound(nbtTagCompound);
		}
		
		return stack;
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(hasEntity(stack)){
			if(worldIn.getTileEntity(pos) instanceof TileEntityPlayerJar){
				TileEntityPlayerJar tE = (TileEntityPlayerJar) worldIn.getTileEntity(pos);
				if(!tE.hasEntity()){
					tE.setEntity(new EntityPlayer(worldIn, getGameProfile(stack)) {
						
						@Override
						public boolean isSpectator() {
							return false;
						}
						
						@Override
						public boolean isCreative() {
							return false;
						}
					});
					stack = removeEntity(stack, worldIn, playerIn.getPositionVector(), false);
				}
			}
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(hasEntity(stack)){
			removeEntity(stack, target.worldObj, attacker.getPositionVector(), true);
		}
		if(target instanceof EntityPlayer){
			stack = saveNBT(stack, target);
			super.hitEntity(stack, target, attacker);
			return true;
		}
		return super.hitEntity(stack, target, attacker);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if(nbtTagCompound == null){
			stack = saveEmptyNBT(stack);
			tooltip.add(TextFormatting.GOLD + "Name:" + TextFormatting.RESET + " -");
			tooltip.add(TextFormatting.GOLD + "UUID:" + TextFormatting.RESET + " -");
		}
		else{
			if(hasEntity(stack)){
				tooltip.add("Has Player: " + TextFormatting.DARK_GREEN + "Yes");
			}
			else{
				tooltip.add("Has Player: " + TextFormatting.DARK_RED + "No");
			}
			if(GuiScreen.isShiftKeyDown()){
				tooltip.add("");
				tooltip.add(TextFormatting.GOLD + "Name: " + TextFormatting.RESET + nbtTagCompound.getString(NBTTags.ENTITY_NAME));
				tooltip.add(TextFormatting.GOLD + "UUID: " + TextFormatting.RESET + nbtTagCompound.getString(NBTTags.ENTITY_UUID));
			}
			else{
				ToolTipBuilder.addShiftForMoreDetails(tooltip);
			}
		}
	}
	
	@Override
	protected ItemStack saveEmptyNBT(ItemStack stack){
		stack = super.saveEmptyNBT(stack);
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
	
		nbtTagCompound.setString(NBTTags.ENTITY_UUID, "-");

		stack.setTagCompound(nbtTagCompound);
		return stack;
	}
	
	public ItemStack saveNBT(ItemStack stack, EntityPlayer player){
		stack = saveEmptyNBT(stack);
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		
		nbtTagCompound.setString(NBTTags.ENTITY_NAME, player.getGameProfile().getName());
		nbtTagCompound.setString(NBTTags.ENTITY_UUID, player.getGameProfile().getId().toString());
		
		stack.setTagCompound(nbtTagCompound);
		return stack;
	}

}
