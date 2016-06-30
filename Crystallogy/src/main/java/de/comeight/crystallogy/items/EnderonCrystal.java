package de.comeight.crystallogy.items;

import de.comeight.crystallogy.particles.ParticleEnderon;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnderonCrystal extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "enderonCrystal";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EnderonCrystal() {
		super(ID);
		setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private Vec3d getRandomParticleSpawn(Vec3d playerPos){
		return new Vec3d(playerPos.xCoord + Utilities.getRandDouble(-4.0, 4.0), playerPos.yCoord + Utilities.getRandDouble(-4.0, 4.0), playerPos.zCoord + Utilities.getRandDouble(-4.0, 4.0));
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
			}
			
		}
		else{
			updateCompound(itemStackIn, playerIn);
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	private boolean shouldTeleportPlayer(ItemStack itemStackIn){
		if(itemStackIn.hasTagCompound() && itemStackIn.getTagCompound().getInteger("tick") >= 40){
			return true;
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	private void spawnParticles(World worldIn, Vec3d playerPos){
		ParticleEnderon eP = new ParticleEnderon(worldIn, getRandomParticleSpawn(playerPos), playerPos.addVector(0.0, 1.0, 0.0));
		eP.setRBGColorF(1.0F, 1.0F, 0.0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(eP);
	}
	
	private void updateCompound(ItemStack itemStackIn, EntityPlayer playerIn){
		if(!itemStackIn.hasTagCompound() || !itemStackIn.getTagCompound().hasKey("playerPrevPosX")){
			createNewCompound(itemStackIn, playerIn);
			return;
		}
		
		if(playerIn.posX != itemStackIn.getTagCompound().getDouble("playerPrevPosX") || playerIn.posY != itemStackIn.getTagCompound().getDouble("playerPrevPosY") || playerIn.posZ != itemStackIn.getTagCompound().getDouble("playerPrevPosZ")){
			playerIn.addChatComponentMessage(new TextComponentString("You moved! Progress got RESET!"));
			createNewCompound(itemStackIn, playerIn);
			return;
		}
		
		int tick = itemStackIn.getTagCompound().getInteger("tick");
		if(tick >= 40){
			teleportPlayer(playerIn);
			playerIn.addChatComponentMessage(new TextComponentString("WOOSH!"));
			playerIn.inventory.removeStackFromSlot(playerIn.inventory.currentItem);
		}
		else{
			if(tick % 20 == 0){
				playerIn.addChatComponentMessage(new TextComponentString(String.valueOf(tick / 20)));
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
		compound.setInteger("tick", 1);
		
		itemStackIn.setTagCompound(compound);
	}
	
}
