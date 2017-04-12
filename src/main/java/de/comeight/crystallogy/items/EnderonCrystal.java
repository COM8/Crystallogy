package de.comeight.crystallogy.items;

import java.util.List;

import de.comeight.crystallogy.particles.ParticleEnderon;
import de.comeight.crystallogy.util.ToolTipBuilder;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnderonCrystal extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "enderonCrystal";
	
	private static final int TELEPORT_TIME = 100;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EnderonCrystal() {
		super(ID);
		setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private Vec3d getRandomParticleSpawn(Vec3d playerPos){
		return new Vec3d(playerPos.xCoord + Utilities.getRandDouble(-4.0, 4.0), playerPos.yCoord + Utilities.getRandDouble(-4.0, 4.0), playerPos.zCoord + Utilities.getRandDouble(-4.0, 4.0));
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(worldIn.isRemote){
			for (int i = 0; i < 20; i++) {
				spawnParticles(worldIn, playerIn.getPositionVector());
				
			}
			if(shouldTeleportPlayer(itemStackIn)){
				teleportPlayer(playerIn);
				playerIn.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, 1.0F, 1.5F);
			}
			
		}
		else{
			if(shouldTeleportPlayer(itemStackIn)){
				worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.PLAYERS, 1.0F, Utilities.getRandFloat(0.5F, 1.0F));
			}
			updateCompound(itemStackIn, playerIn);
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	private boolean shouldTeleportPlayer(ItemStack itemStackIn){
        return itemStackIn.hasTagCompound() && itemStackIn.getTagCompound().getInteger("tick") >= TELEPORT_TIME;
    }
	
	@SideOnly(Side.CLIENT)
	private void spawnParticles(World worldIn, Vec3d playerPos){
		ParticleEnderon eP = new ParticleEnderon(worldIn, getRandomParticleSpawn(playerPos), playerPos.addVector(0.0, 1.0, 0.0));
		eP.setRBGColorF(0.2F, 1.0F, 0.0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(eP);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(!stack.hasTagCompound()){
			createNewCompound(stack, playerIn);
		}
		
		tooltip.add("Hold right-click to charge.");
		tooltip.add("Can teleport you up to 1000 blocks away.");
		
		
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			int tick = stack.getTagCompound().getInteger("tick");
			tooltip.add(TextFormatting.DARK_PURPLE + String.valueOf((int)(((double)tick / (double)TELEPORT_TIME)*100)) + "%" + TextFormatting.RESET +  " charged. Don't move or your progress will get reset!");
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	private void updateCompound(ItemStack itemStackIn, EntityPlayer playerIn){
		if(!itemStackIn.hasTagCompound() || !itemStackIn.getTagCompound().hasKey("playerPrevPosX")){
			createNewCompound(itemStackIn, playerIn);
			return;
		}
		
		if(playerIn.posX != itemStackIn.getTagCompound().getDouble("playerPrevPosX") || playerIn.posY != itemStackIn.getTagCompound().getDouble("playerPrevPosY") || playerIn.posZ != itemStackIn.getTagCompound().getDouble("playerPrevPosZ")){
			createNewCompound(itemStackIn, playerIn);
			return;
		}
		
		int tick = itemStackIn.getTagCompound().getInteger("tick");
		if(tick >= TELEPORT_TIME){
			teleportPlayer(playerIn);
			playerIn.addChatComponentMessage(new TextComponentString("WOOSH!"));
			playerIn.inventory.removeStackFromSlot(playerIn.inventory.currentItem);
		}
		else{
			if(tick == 0 || tick % 5 == 0){
				int progress = (int) (((double)tick / (double)TELEPORT_TIME)*100);
				playerIn.addChatComponentMessage(new TextComponentString(TextFormatting.DARK_PURPLE + String.valueOf(progress + "%" + TextFormatting.RESET +  " charged. Don't move or your progress will get reset!")));
				playerIn.getEntityWorld().playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_ENDERMITE_HURT, SoundCategory.PLAYERS, 1.0F, (float)progress / 50);
				
			}
			tick++;
			itemStackIn.getTagCompound().setInteger("tick", tick);
		}
	}
	
	private void teleportPlayer(EntityPlayer playerIn){
		double targetPosX = playerIn.posX + Utilities.getRandInt(-1000, 1000);
		double targetPosZ = playerIn.posZ + Utilities.getRandInt(-1000, 1000);
		double targetPosY = playerIn.getEntityWorld().getHeight(playerIn.getPosition()).getY() + 1;
		
		playerIn.moveEntity(targetPosX, targetPosY, targetPosZ);
	}
	
	private void createNewCompound(ItemStack itemStackIn, EntityPlayer playerIn){
		NBTTagCompound compound = new NBTTagCompound();
		compound.setDouble("playerPrevPosX", playerIn.posX);
		compound.setDouble("playerPrevPosY", playerIn.posY);
		compound.setDouble("playerPrevPosZ", playerIn.posZ);
		compound.setInteger("tick", 0);
		
		itemStackIn.setTagCompound(compound);
	}
	
	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if(entityItem.worldObj.isRemote){
			spawnParticles(entityItem.worldObj, new Vec3d(entityItem.posX, entityItem.posY - 0.75, entityItem.posZ));
		}
		return super.onEntityItemUpdate(entityItem);
	}
	
}
