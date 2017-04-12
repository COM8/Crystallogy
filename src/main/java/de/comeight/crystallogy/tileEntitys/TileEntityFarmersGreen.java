package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.blocks.FarmersGreen;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.particles.ParticleInformation;
import de.comeight.crystallogy.particles.TransportParticle;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityFarmersGreen extends BaseTileEntity implements ITickable{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public int growthLeft;
	
	private NBTTagCompound data;
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityFarmersGreen() {
		growthLeft = 0;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private boolean isActive(){
        return getBlockState() == 3;
    }
	
	private int getBlockState(){
		return worldObj.getBlockState(pos).getValue(FarmersGreen.STATUS);
	}
	
	private void setBlockState(int state){
		FarmersGreen.setBlockState(state, worldObj, pos);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onLoad() {
		if(worldObj.isRemote){
			requestSync();
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		writeCustomDataToNBT(compound);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		readCustomDataFromNBT(compound);
	}
	
	public void writeCustomDataToNBT(NBTTagCompound compound) {
		compound.setInteger("growthLeft", growthLeft);
	}
	
	public void readCustomDataFromNBT(NBTTagCompound compound) {
		growthLeft = compound.getInteger("growthLeft");
	}
	
	@Override
	public void onCustomDataPacket(NetworkPacketTileEntitySync packet) {
		readFromNBT(packet.getNBTTagCompound());
	}

	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		writeToNBT(compound);
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
		}
		else{
			applyGrowth();
			
			if(growthLeft > 0){
				growthLeft--;
			}
			else{
				if(isActive()){
					setBlockState(2);
				}
			}
		}
	}
	
	private void applyGrowth(){
		int range = 3;
		for(int i = 0; i < 20; i++){
			BlockPos posGrowth = new BlockPos(pos.getX() + Utilities.getRandInt(-range, range + 1), pos.getY() + Utilities.getRandInt(-range, range + 1), pos.getZ() + Utilities.getRandInt(-range, range + 1));
			IBlockState state = worldObj.getBlockState(posGrowth);
			if(state.getBlock() instanceof IPlantable || state.getBlock() instanceof IGrowable){
				state.getBlock().updateTick(worldObj, posGrowth, state, worldObj.rand);
				if(state != worldObj.getBlockState(posGrowth)){
					spawnParticlesNetwork(posGrowth);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	private void spawnActiveParticle(){
		ParticleB gP = new ParticleB(worldObj, new Vec3d(pos.getX() + 0.5, pos.getY() + 0.75, pos.getZ() + 0.5));
		gP.setRBGColorF(Utilities.getRandFloat(0.0F, 0.5F), 1.0F, Utilities.getRandFloat(0.0F, 0.5F));
		Minecraft.getMinecraft().effectRenderer.addEffect(gP);
	}
	
	private void spawnParticlesNetwork(BlockPos posGrowth){
		TransportParticle tP = new TransportParticle(new Vec3d(posGrowth.getX() + 0.5, posGrowth.getY(), posGrowth.getZ() + 0.5));
		tP.maxAge = 60;
		tP.color = new RGBColor(Utilities.getRandFloat(0.0F, 0.5F), 1.0F, Utilities.getRandFloat(0.0F, 0.5F));
		NetworkParticle nP = new NetworkParticle(tP, ParticleInformation.ID_PARTICLE_B);
		nP.setSize(new Vec3d(0.0, 0.0, 0.0));
		nP.setNumberOfParticle(3);
		NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
		CommonProxy.NETWORKWRAPPER.sendToDimension(pMtS, worldObj.provider.getDimension());
	}
	
}
