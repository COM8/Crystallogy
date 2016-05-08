package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.blocks.FarmersGreen;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.IPlantable;

public class TileEntityFarmersGreen extends BaseTileEntity implements ITickable{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public int growthLeft;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityFarmersGreen() {
		growthLeft = 0;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private boolean isActive(){
		if(worldObj.getBlockState(pos).getValue(FarmersGreen.STATUS) == 3){
			return true;
		}
		else{
			return false;
		}
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("growthLeft", growthLeft);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		growthLeft = compound.getInteger("growthLeft");
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public Packet<?> getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}
	
	@Override
	public void onCustomDataPacket(NetworkPacketTileEntitySync packet) {
		growthLeft = packet.getNBTTagCompound().getInteger("growthLeft");
	}

	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		compound.setInteger("growthLeft", growthLeft);
		return new NetworkPacketTileEntitySync(pos, compound);
	}

	public void refuel(){
		growthLeft = 12000;
	}
	
	@Override
	public void update() {
		if(!isActive()){
			return;
		}
		
		if(worldObj.isRemote){
			if(Utilities.getRandInt(0, 5) == 0){
				spawnActiveParticle();
			}
			applyGrowth();
		}
		if(growthLeft <= 0 && worldObj.getBlockState(pos).getValue(FarmersGreen.STATUS) == 3){
			FarmersGreen.setBlockState(2, worldObj, pos);
		}
		if(growthLeft > 0){
			growthLeft--;
		}
		
	}
	
	private void spawnActiveParticle(){
		ParticleB gP = new ParticleB(worldObj, pos.getX() + 0.5, pos.getY() + 0.75, pos.getZ() + 0.5, 0.2, 0.2, 0.2);
		gP.setRBGColorF(Utilities.getRandFloat(0.0F, 0.5F), 1.0F, Utilities.getRandFloat(0.0F, 0.5F));
		Minecraft.getMinecraft().effectRenderer.addEffect(gP);
	}
	
	private void applyGrowth(){
		int range = 3;
		
		for(int i = 0; i < 2; i++){
			BlockPos posGrowth = new BlockPos(pos.getX() + Utilities.getRandInt(-range, range + 1), pos.getY() + Utilities.getRandInt(-range, range + 1), pos.getZ() + Utilities.getRandInt(-range, range + 1));
			IBlockState state = worldObj.getBlockState(posGrowth);
			if(state.getBlock() instanceof IPlantable || state.getBlock() instanceof IGrowable){
				state.getBlock().updateTick(worldObj, posGrowth, state, worldObj.rand);
				spawnParticlesNetwork(posGrowth);
			}
		}
	}
	
	private void spawnParticlesNetwork(BlockPos posGrowth){
		ParticleB gP = new ParticleB(worldObj, posGrowth.getX() + 0.5, posGrowth.getY(), posGrowth.getZ() + 0.5, 0.0, 0.0, 0.0);
		gP.setParticleMaxAge(60);
		gP.setRBGColorF(Utilities.getRandFloat(0.0F, 0.5F), 1.0F, Utilities.getRandFloat(0.0F, 0.5F));
		NetworkParticle nP = new NetworkParticle(gP, gP.name);
		nP.setSize(new Vec3d(0.0, 0.0, 0.0));
		nP.setNumberOfParticle(3);
		NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
		CommonProxy.NETWORKWRAPPER.sendToServer(pMtS);
	}
	
}
