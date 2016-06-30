package de.comeight.crystallogy.items.tools;

import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
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
		String name = c.getString("name");
		String uuid = c.getString("uuid");
		GameProfile p = new GameProfile(UUID.fromString(uuid), name);
		return p;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ItemStack saveNBT(ItemStack stack, EntityLivingBase entity){
		stack = saveEmptyNBT(stack);
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			
			nbtTagCompound.setString("name", player.getGameProfile().getName());
			nbtTagCompound.setString("uuid", player.getGameProfile().getId().toString());
			
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
				tooltip.add(TextFormatting.GOLD + "Name: " + TextFormatting.RESET + nbtTagCompound.getString("name"));
				tooltip.add(TextFormatting.GOLD + "UUID: " + TextFormatting.RESET + nbtTagCompound.getString("uuid"));
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
	
		nbtTagCompound.setString("uuid", "-");

		stack.setTagCompound(nbtTagCompound);
		return stack;
	}
	
	public ItemStack saveNBT(ItemStack stack, EntityPlayer player){
		stack = saveEmptyNBT(stack);
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		
		nbtTagCompound.setString("name", player.getGameProfile().getName());
		nbtTagCompound.setString("uuid", player.getGameProfile().getId().toString());
		
		stack.setTagCompound(nbtTagCompound);
		return stack;
	}

}
