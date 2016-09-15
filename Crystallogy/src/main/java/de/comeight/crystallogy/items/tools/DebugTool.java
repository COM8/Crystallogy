package de.comeight.crystallogy.items.tools;

import java.util.List;

import de.comeight.crystallogy.items.BaseItem;
import de.comeight.crystallogy.particles.ParticleDebug;
import de.comeight.crystallogy.tileEntitys.BaseTileEntity;
import de.comeight.crystallogy.util.EnumDebugToolModes;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DebugTool extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "debugTool";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public DebugTool() {
		super(ID);
		
		setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(playerIn.isSneaking()){
			nextMode(itemStackIn, playerIn);
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumDebugToolModes mode = getMode(stack);
		switch (mode) {
			case BLOCKUPDATE:
				worldIn.getBlockState(pos).getBlock().updateTick(worldIn, pos, worldIn.getBlockState(pos), worldIn.rand);
				break;
				
			case REQSYNC:
				if(worldIn.isRemote){
					TileEntity tE = worldIn.getTileEntity(pos);
					if(tE != null && tE instanceof BaseTileEntity){
						BaseTileEntity btE = (BaseTileEntity) tE;
						btE.requestSync();
					}
				}
				break;
				
			case SYNC:
				if(!worldIn.isRemote){
					TileEntity tE = worldIn.getTileEntity(pos);
					if(tE != null && tE instanceof BaseTileEntity){
						BaseTileEntity btE = (BaseTileEntity) tE;
						btE.sync();
					}
				}
				break;
				
			case SPAWNPARTICLES:
				if(worldIn.isRemote){
					spawnDebugParticle(worldIn, new Vec3d(pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ));
				}
				break;

			default:
				return EnumActionResult.PASS;
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	@SideOnly(Side.CLIENT)
	private void spawnDebugParticle(World worldIn, Vec3d pos){
		ParticleDebug pD = new ParticleDebug(worldIn, pos);
		pD.setCanTickToDeath(false);
		Minecraft.getMinecraft().effectRenderer.addEffect(pD);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Mode: " + TextFormatting.DARK_PURPLE + getMode(stack).getName());
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	private void nextMode(ItemStack itemStackIn, EntityPlayer playerIn) {
		int curMode = getMode(itemStackIn).getId();
		NBTTagCompound compound = itemStackIn.getTagCompound();
		EnumDebugToolModes mode = EnumDebugToolModes.fromID(EnumDebugToolModes.getNextMode(curMode));
		compound.setInteger("mode", mode.getId());
		itemStackIn.setTagCompound(compound);
		
		if(playerIn.worldObj.isRemote){
			playerIn.addChatMessage(new TextComponentString("Mode: " + TextFormatting.DARK_PURPLE + mode.getName()));
		}
	}
	
	private EnumDebugToolModes getMode(ItemStack itemStackIn) {
		if(!itemStackIn.hasTagCompound()){
			NBTTagCompound compound = new NBTTagCompound();
			compound.setInteger("mode", 0);
			itemStackIn.setTagCompound(compound);
			return EnumDebugToolModes.fromID(0);
		}
		
		NBTTagCompound compound = itemStackIn.getTagCompound();
		if(!compound.hasKey("mode")){
			compound.setInteger("mode", 0);
			itemStackIn.setTagCompound(compound);
			return EnumDebugToolModes.fromID(0);
		}
		else{
			return EnumDebugToolModes.fromID(compound.getInteger("mode"));
		}
	}
	
}
