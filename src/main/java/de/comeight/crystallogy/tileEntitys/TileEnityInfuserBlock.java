package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.items.crafting.InfusionRecipeVaporizer;
import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.particles.LightParticle;
import de.comeight.crystallogy.structures.StructureInfuser;
import de.comeight.crystallogy.util.StructureAreaDescription;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class TileEnityInfuserBlock extends TileEntityInventory implements ITickable{

	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int invSize = 1;
	private int tick;
	
	private StructureInfuser struct;
	private boolean active;
	private BlockPos centerInfuserBlockPos;
	
	private LightParticle lightParticle;
	public InfusionRecipeVaporizer recipe;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEnityInfuserBlock() {
		super(invSize);
		this.tick = 0;
		this.struct = new StructureInfuser();
		setActive(false);
		this.recipe = new InfusionRecipeVaporizer();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		updateParticle();
		
		if(worldObj == null){
			return;
		}
		BlockPos[] surPos = struct.getSurroundingPedals(getPos());
		for (int i = 0; i < surPos.length; i++) {
			TileEntity tE = worldObj.getTileEntity(surPos[i]);
			if(tE != null && tE instanceof TileEnityInfuserBlock){
				TileEnityInfuserBlock tEB = (TileEnityInfuserBlock) tE;
				if(active){
					tEB.setCenterInfuserBlockPos(getPos());
				}
				else{
					tEB.setCenterInfuserBlockPos(null);
				}
			}
		}
	}
	
	@Override
    public String getName()
    {
        return "TileEnityInfuserBlock";
    }
	
	public void setCenterInfuserBlockPos(BlockPos centerInfuserBlockPos) {
		this.centerInfuserBlockPos = centerInfuserBlockPos;
	}
	
	public BlockPos getCenterInfuserBlockPos() {
		return centerInfuserBlockPos;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		//compound.setBoolean("active", recipe.isActive());
		//compound.setInteger("cookTime", recipe.cookTime);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		//recipe.setActive(compound.getBoolean("active"));
		//recipe.cookTime = compound.getInteger("cookTime");
		
	}
	
    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }
    
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return slot == 0;
    }
    
    public void updateParticle() {
    	if(worldObj != null && !worldObj.isRemote){
    		NetworkPacketInfuserBlockEnabled message = new NetworkPacketInfuserBlockEnabled(new Vec3d(this.pos), active);
    		CommonProxy.NETWORKWRAPPER.sendToServer(message);
		}
	}
    
	@Override
	public void update() {
		if(!worldObj.isRemote){ // Server:
			if(recipe.isActive()){
				if(recipe.tick()){
					this.setInventorySlotContents(0, recipe.getRecipeOutput());
				}
			}
			
			if(tick % 5 == 0){
				testForCenterInfusionBlock();
			}
			
			if(tick > 200){
				tick = 0;
				setActive(checkForStructure());
			}
			updateParticle();
		}
		else{ // Client:
			
		}
		tick++;
	}
	
	private void testForCenterInfusionBlock(){
		if(centerInfuserBlockPos == null){
			return;
		}
		TileEntity tE = worldObj.getTileEntity(centerInfuserBlockPos);
		if(tE != null && tE instanceof TileEnityInfuserBlock){
			return;
		}
		else{
			centerInfuserBlockPos = null;
		}
	}
	
	public boolean checkForStructure(){
		StructureAreaDescription structureArea = struct.infuserArea;
		return structureArea.testForStructure(worldObj, getPos(), 2, 2);
	}
	
	private void setInfuserBlocks(){
		BlockPos[] infuserBlocks = StructureInfuser.getSurroundingPedals(this.pos);
		recipe.setInfuserBlocks(this.pos, infuserBlocks, this.worldObj);
	}
	
	public void changeRecipeStatus(boolean status, WorldClient worldClient){
		if(status){
			setInfuserBlocks();
			recipe.startAnimations(worldClient);
		}
		else{
			recipe.stopAnimation(worldClient);
		}
	}
	
	public void changeParticleActive(Boolean status) {
		if(status){
			if(lightParticle == null){
				lightParticle = new LightParticle(worldObj, this.pos.getX() + 0.5, this.pos.getY() + 1.0, this.pos.getZ() + 0.5, 0, 0, 0);
				lightParticle.setParticleMaxAge(5);
				Minecraft.getMinecraft().effectRenderer.addEffect(lightParticle);
			}
			else{
				lightParticle.setParticleAge(0);
			}
		}
		else{
			if(lightParticle != null){
				lightParticle.setExpired();
				lightParticle = null;
			}
		}
	}

}
