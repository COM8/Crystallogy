package de.comeight.crystallogy.tileEntitys;

import java.util.ArrayList;

import de.comeight.crystallogy.handler.InfusionRecipeHandler;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipe;
import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.particles.ParticleInfuserBlockStatus;
import de.comeight.crystallogy.renderer.InfusionAnimation;
import de.comeight.crystallogy.structures.StructureInfuser;
import de.comeight.crystallogy.util.NetworkUtilitis;
import de.comeight.crystallogy.util.StructureAreaDescription;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEnityInfuserBlock extends TileEntityInventory implements ITickable, ISidedInventory{

	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int invSize = 1;
	private int tick;
	
	private StructureInfuser struct;
	private boolean active;
	
	private InfusionRecipe recipe;
	
	private InfusionAnimation infusionAnimation;
	
	@SideOnly(Side.CLIENT)
	private ParticleInfuserBlockStatus particleInfuserBlockStatus;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEnityInfuserBlock() {
		super(invSize);
		this.tick = 0;
		this.struct = new StructureInfuser();
		setActive(false);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		updateParticle();
	}
	
	@Override
    public String getName()
    {
        return "TileEnityInfuserBlock";
    }
	
	protected ItemStack[] getSurroundingItemStacks(){
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		BlockPos surr[] = struct.getSurroundingPedals(pos);
		for (int i = 0; i < surr.length; i++) {
			TileEntity tE = worldObj.getTileEntity(surr[i]);
			if(tE instanceof TileEnityInfuserBlock){
				TileEnityInfuserBlock tEI = (TileEnityInfuserBlock) tE;
				if(tEI.getStackInSlot(0) != null){
					list.add(tEI.getStackInSlot(0));
				}
			}
		}
		ItemStack[] stacks = new ItemStack[list.size()];
		for (int i = 0; i < list.size(); i++) {
			stacks[i] = list.get(i);
		}
		return stacks;
	}
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		int[] ret = new int[1];
		ret[0] = 0;
		
		return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void tryInfuse() {
		if(!active){
			checkForStructure();
		}
		if(active){
			if(recipe != null && recipe.isActive()){
				//System.out.println("Recipe active!");
				return;
			}
			else{
				if((recipe = InfusionRecipeHandler.matchRecipes(getStackInSlot(0), getSurroundingItemStacks())) != null){
					recipe.cook(pos, struct.getSurroundingPedals(pos), worldObj);
				}
				else{
					//System.out.println("Recipe not found!");
					return;
				}
			}
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		//compound.setBoolean("active", recipe.isActive());
		//compound.setInteger("cookTime", recipe.cookTime);
		return compound;
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
        return 64;
    }
    
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
		if(inventory[index] != null){
			return false;
		}
		return true;
    }
    
    public void updateParticle() {
    	if(worldObj != null && !worldObj.isRemote){
    		NetworkPacketInfuserBlockEnabled message = new NetworkPacketInfuserBlockEnabled(new Vec3d(this.pos), active);
    		NetworkUtilitis.sendAllAround(message, false);
		}
	}
    
	@Override
	public void update() {
		if(!worldObj.isRemote){ // Server:
			if(recipe != null && recipe.isActive()){
				recipe.tick();
				worldObj.spawnParticle(EnumParticleTypes.LAVA, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
			}
			
			if(tick % 4 == 0){
				updateParticle();
			}
			
			if(tick == 100){
				setActive(checkForStructure());
			}
			
			if(tick > 200){
				tick = 0;
			}
		}
		else{ // Client:
			if(infusionAnimation != null && infusionAnimation.isActive()){
				infusionAnimation.tick();
			}
		}
		tick++;
	}
	
	public boolean checkForStructure(){
		StructureAreaDescription structureArea = struct.infuserArea;
		return structureArea.testForStructure(worldObj, this.getPos(), 2, 2);
	}
	
	public void changeRecipeStatus(boolean status, WorldClient worldClient, int recipeIndex, boolean successfully){ //Client Animation
		if(status){
			recipe = InfusionRecipeHandler.getRecipe(recipeIndex);
			infusionAnimation = new InfusionAnimation(pos, struct.getSurroundingPedals(pos), worldObj, recipe.getTotalCookTime());
			infusionAnimation.start();
		}
		else{
			if(infusionAnimation != null){
				infusionAnimation.stop(successfully);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void changeParticleActive(Boolean status) {
		if(status){
			if(particleInfuserBlockStatus == null){
				particleInfuserBlockStatus = new ParticleInfuserBlockStatus(worldObj, new Vec3d(this.pos.getX() + 0.5, this.pos.getY() + 1.0, this.pos.getZ() + 0.5));
				particleInfuserBlockStatus.setMaxAge(10);
				Minecraft.getMinecraft().effectRenderer.addEffect(particleInfuserBlockStatus);
			}
			else{
				particleInfuserBlockStatus.setParticleAge(0);
			}
		}
		else{
			if(particleInfuserBlockStatus != null){
				particleInfuserBlockStatus.setExpired();
				particleInfuserBlockStatus = null;
			}
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}
	
}
