package de.comeight.crystallogy.items.tools;

import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;
import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.util.ToolTipBuilder;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrystallKnife extends BaseItemSword{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystallKnife";
	
	private float attackDamage;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallKnife() {
		super(CustomToolMaterials.CRYSTALLKNIF, ID);
		this.canRepair = true;
		
		this.setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private GameProfile getGameProfile(ItemStack stack){
		NBTTagCompound c = stack.getTagCompound();
		String name = c.getString("name");
		String uuid = c.getString("uuid");
		GameProfile p = new GameProfile(UUID.fromString(uuid), name);
		return p;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack)
    {
		return hasPlayer(stack);
    }
	
	public boolean hasPlayer(ItemStack stack){
		NBTTagCompound c = stack.getTagCompound();
		if(c == null||c.getString("name") == null||c.getString("name").equals("-")||c.getString("name").equals("")){
			return false;
		}
        else {
			return true;
		}
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(playerIn.isSneaking()){
			itemStackIn = removePlayer(itemStackIn, worldIn, playerIn.getPositionVector(), true);
		}
		else{
			if(!hasPlayer(itemStackIn)){
				if(playerIn.capabilities.isCreativeMode){
					saveNBT(itemStackIn, playerIn);
				}
				else{
					playerIn.attackTargetEntityWithCurrentItem(playerIn);
				}
			}
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(hasPlayer(stack)){
			if(worldIn.getTileEntity(pos) instanceof TileEntityPlayerJar){
				TileEntityPlayerJar tE = (TileEntityPlayerJar) worldIn.getTileEntity(pos);
				if(!tE.hasPlayer()){
					tE.setPlayer(new PlayerClientDummy(worldIn, getGameProfile(stack)));
					stack = removePlayer(stack, worldIn, playerIn.getPositionVector(), false);
					return EnumActionResult.SUCCESS;
				}
			}
		}
		
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(hasPlayer(stack)){
			removePlayer(stack, target.worldObj, attacker.getPositionVector(), true);
		}
		if(target instanceof EntityPlayer){
			stack = saveNBT(stack, (EntityPlayer) target);
			super.hitEntity(stack, target, attacker);
			return false;
		}
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public float getDamageVsEntity() {
		return this.attackDamage;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if(nbtTagCompound == null){
			stack = saveNBT(stack);
			tooltip.add("§6Name:§r -");
			tooltip.add("§6UUID:§r -");
		}
		else{
			if(hasPlayer(stack)){
				tooltip.add("Has Player: " + TextFormatting.DARK_GREEN + "Yes");
			}
			else{
				tooltip.add("Has Player: " + TextFormatting.DARK_RED + "No");
			}
			if(GuiScreen.isShiftKeyDown()){
				tooltip.add("");
				tooltip.add("§6Name:§r " + nbtTagCompound.getString("name"));
				tooltip.add("§6UUID:§r " + nbtTagCompound.getString("uuid"));
			}
			else{
				ToolTipBuilder.addShiftForMoreDetails(tooltip);
			}
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	public ItemStack saveNBT(ItemStack stack, EntityPlayer player){
		stack = saveNBT(stack);
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		
		nbtTagCompound.setString("name", player.getGameProfile().getName());
		nbtTagCompound.setString("uuid", player.getGameProfile().getId().toString());
		
		stack.setTagCompound(nbtTagCompound);
		return stack;
	}
	
	private ItemStack saveNBT(ItemStack stack){
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if(nbtTagCompound == null){
			nbtTagCompound = new NBTTagCompound();
		}
		
		nbtTagCompound.setString("name", "-");
		nbtTagCompound.setString("uuid", "-");

		stack.setTagCompound(nbtTagCompound);
		return stack;
	}
	
	private ItemStack getItemStack(){
		return this.saveNBT(new ItemStack(this));
	}
	
	private ItemStack removePlayer(ItemStack stack, World worldIn, Vec3d pos, boolean release){
		if(hasPlayer(stack)){
			stack = saveNBT(stack);
			
			if(release){
				if(worldIn.isRemote){
					for (int i = 0; i < 5; i++) { //Particel:
						ParticleB gP = new ParticleB(worldIn, pos.xCoord, pos.yCoord, pos.zCoord, 0.0, 0.0, 0.0);
						gP.setParticleMaxAge(120);
						gP.setRBGColorF(Utilities.getRandFloat(0, 100), Utilities.getRandFloat(0, 100), Utilities.getRandFloat(0, 100));
						NetworkParticle nP = new NetworkParticle(gP, gP.name);
						nP.setSize(new Vec3d(1.0, 2.0, 1.0));
						nP.setNumberOfParticle(30);
						NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
						CommonProxy.NETWORKWRAPPER.sendToServer(pMtS);	
					}
				}
				worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.xCoord, pos.yCoord, pos.zCoord, false));
				worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_endermen_stare, SoundCategory.NEUTRAL, 1.0F, 1.0F);
				worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_enderdragon_growl, SoundCategory.NEUTRAL, 1.0F, 1.0F);
				worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_wither_spawn, SoundCategory.NEUTRAL, 1.0F, 0.6F);
			}
		}
		return stack;
	}


}
