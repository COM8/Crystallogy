package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.comeight.crystallogy.handler.ConfigHandler;
import de.comeight.crystallogy.handler.InfusionRecipeHandler;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleInformation;
import de.comeight.crystallogy.particles.TransportParticle;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.util.NetworkUtilitis;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final String id;
	
	protected final int totalCookTime;
	protected int curentCookTime;
	
	protected World worldIn;
	protected TileEnityInfuserBlock centerInput;
	protected TileEnityInfuserBlock[] ingredients;
	protected ItemStack output;
	
	protected boolean active;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipe(String id, int totalCookTime) {
		this.id = id;
		this.totalCookTime = totalCookTime;
		this.curentCookTime = 0;
		
		this.worldIn = null;
		this.centerInput = null;
		this.ingredients = null;
		this.output = null;
		
		this.active = false;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public int getTotalCookTime(){
		return (int)( totalCookTime * ConfigHandler.infusionTimeMultiplier);
	}
	
	public boolean isActive() {
		return active;
	}

	public ItemStack getRecipeOutput(){
		ItemStack iStack = output;
		output = null;
		return iStack;
	}
	
	public abstract InfusionRecipe getRecipe();
	
	protected void setTileEnityInfuserBlocks(BlockPos centerInputPos, BlockPos[] ingredientsPos, World worldIn){
		TileEntity tE = worldIn.getTileEntity(centerInputPos);
		if(tE instanceof TileEnityInfuserBlock){
			centerInput = (TileEnityInfuserBlock) tE;
		}
		ArrayList<TileEnityInfuserBlock> list = new ArrayList<TileEnityInfuserBlock>();
		for (int i = 0; i < ingredientsPos.length; i++) {
			tE = worldIn.getTileEntity(ingredientsPos[i]);
			if(tE instanceof TileEnityInfuserBlock){
				TileEnityInfuserBlock tEI = (TileEnityInfuserBlock) tE;
				if(tEI.getStackInSlot(0) != null){
					list.add(tEI);
				}
			}
		}
		TileEnityInfuserBlock[] a = new TileEnityInfuserBlock[list.size()];
		for (int i = 0; i < list.size(); i++) {
			a[i] = list.get(i);
		}
		ingredients = a;
	}
	
	public BlockPos[] getActiveInfuserBlocks(){
		BlockPos[] positions = new BlockPos[ingredients.length];
		for (int i = 0; i < ingredients.length; i++) {
			positions[i] = ingredients[i].getPos();
		}
		return positions;
	}
	
	public abstract ArrayList<List<ItemStack>> getInputsJEI();
	
	public abstract ArrayList<ItemStack> getOutputJEI();
	
	public LinkedList<LinkedList<ItemStack>> getReturns(){
		LinkedList<LinkedList<ItemStack>> list = new LinkedList<LinkedList<ItemStack>>();
		
		LinkedList<ItemStack> waterBucket = new LinkedList<ItemStack>();
		waterBucket.add(new ItemStack(Items.WATER_BUCKET));
		waterBucket.add(new ItemStack(Items.BUCKET));
		list.add(waterBucket);
		
		LinkedList<ItemStack> lavaBucket = new LinkedList<ItemStack>();
		lavaBucket.add(new ItemStack(Items.LAVA_BUCKET));
		lavaBucket.add(new ItemStack(Items.BUCKET));
		list.add(lavaBucket);
		
		return list;
	}
	
	protected ItemStack getleftOver(ItemStack stack){
		for (LinkedList<ItemStack> list : getReturns()) {
			if(list.get(0).getItem() == stack.getItem()){
				return list.get(1);
			}
		}
		return null;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public abstract boolean match(ItemStack centerInput, ItemStack[] ingredients);
	
	public void cook(BlockPos centerInputPos, BlockPos[] ingredientsPos, World worldIn){
		if(output == null){
			try {
				throw new Exception("Match recipe first!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		else{
			this.worldIn = worldIn;
			setTileEnityInfuserBlocks(centerInputPos, ingredientsPos, worldIn);
			startAnimationOnClients();
			active = true;
		}
	}
	
	public void tick(){
		if(!active){
			return;
		}
		if(!stillActive()){
			done(false);
		}
		if(!canIncCurentCookTime()){
			done(true);
		}
	}
	
	protected boolean stillActive(){
		if (centerInput == null || centerInput.getStackInSlot(0) == null) {
			return false;
		}
		for (int i = 0; i < ingredients.length; i++) {
			if (ingredients[i] == null || ingredients[i].getStackInSlot(0) == null) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean canIncCurentCookTime(){
		curentCookTime++;
		if(curentCookTime >= totalCookTime){
			curentCookTime = 0;
			return false;
		}
		return true;
	}
	
	protected void done(boolean successfully){
		if(successfully){
			setCenterInputItem();
			removeIngredients();
		}
		else{
			
		}
		stopAnimationOnClients(successfully);
		spawnParticlesOnClient(successfully);
		active = false;
	}
	
	protected void spawnParticlesOnClient(boolean successfully){
		BlockPos pos = centerInput.getPos();
		TransportParticle tP = new TransportParticle(new Vec3d(pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5));
		tP.randomColor = true;
		
		NetworkParticle nP = new NetworkParticle(tP, ParticleInformation.ID_PARTICLE_N_COLOR);
		nP.setSize(new Vec3d(0.25, 2.0, 0.25));
		nP.setNumberOfParticle(30);
		NetworkPacketParticle packet = new NetworkPacketParticle(nP);
		NetworkUtilitis.sendAllAround(packet, worldIn.isRemote);
		
		if(!successfully){
			for (int i = 0; i < ingredients.length; i++) {
				pos = ingredients[i].getPos();
				tP = new TransportParticle(new Vec3d(pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5));
				tP.randomColor = true;
				
				nP = new NetworkParticle(tP, ParticleInformation.ID_PARTICLE_N_COLOR);
				nP.setSize(new Vec3d(0.25, 2.0, 0.25));
				nP.setNumberOfParticle(30);
				packet = new NetworkPacketParticle(nP);
				NetworkUtilitis.sendAllAround(packet, worldIn.isRemote);
			}
		}
		
	}
	
	protected void startAnimationOnClients(){
		NetworkPacketInfusionRecipeStatus packet = new NetworkPacketInfusionRecipeStatus(centerInput.getPos(), true, InfusionRecipeHandler.getIndexOfRecipe(this), true);
		NetworkUtilitis.sendAllAround(packet, worldIn.isRemote);
		
	}
	
	protected void stopAnimationOnClients(boolean successfully){
		NetworkPacketInfusionRecipeStatus packet = new NetworkPacketInfusionRecipeStatus(centerInput.getPos(), false, -1, successfully);
		NetworkUtilitis.sendAllAround(packet, worldIn.isRemote);
	}
	
	protected void removeIngredients(){
		for (int i = 0; i < ingredients.length; i++) {
			ItemStack leftOver = getleftOver(ingredients[i].getStackInSlot(0));
			ingredients[i].setInventorySlotContents(0, leftOver);
			setItemOnClient(ingredients[i].getPos(), leftOver);
		}
	}
	
	protected void setCenterInputItem(){
		centerInput.setInventorySlotContents(0, output);
		setItemOnClient(centerInput.getPos(), output);
	}

	protected void setItemOnClient(BlockPos pos, ItemStack stack) {
		NetworkPacketUpdateInventory packet = new NetworkPacketUpdateInventory(pos, stack, 0); //TODO update
		NetworkUtilitis.sendAllAround(packet, worldIn.isRemote);
	}
	
	protected boolean compare(ItemStack i1, ItemStack i2){
		if(i1.getItem() != i2.getItem()){
			return false;
		}
		if(i1.getItem().getHasSubtypes()){
			if(i1.getItem().getDamage(i1) != i2.getItem().getDamage(i2)){
				return false;
			}
		}
		return true;
	}

}
