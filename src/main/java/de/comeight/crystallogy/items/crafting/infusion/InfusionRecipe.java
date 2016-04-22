package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.handler.InfusionRecipeHandler;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleNColor;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
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
		return totalCookTime;
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
		if(worldIn != null){
			//worldIn.spawnParticle(EnumParticleTypes.LAVA, centerInput.getPos().getX() + 0.5, centerInput.getPos().getY() + 0.8, centerInput.getPos().getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
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
		ParticleNColor particle = new ParticleNColor(worldIn, pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
		NetworkParticle nP = new NetworkParticle(particle, particle.NAME);
		nP.setSize(new Vec3d(0.25, 2.0, 0.25));
		nP.setNumberOfParticle(30);
		NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
		CommonProxy.NETWORKWRAPPER.sendToServer(pMtS);
		
		if(!successfully){
			for (int i = 0; i < ingredients.length; i++) {
				pos = ingredients[i].getPos();
				particle = new ParticleNColor(worldIn, pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
				nP = new NetworkParticle(particle, particle.NAME);
				nP.setSize(new Vec3d(0.25, 2.0, 0.25));
				nP.setNumberOfParticle(30);
				pMtS = new NetworkPacketParticle(nP);
				CommonProxy.NETWORKWRAPPER.sendToServer(pMtS);
			}
		}
		
	}
	
	protected void startAnimationOnClients(){
		NetworkPacketInfusionRecipeStatus packet = new NetworkPacketInfusionRecipeStatus(centerInput.getPos(), true, InfusionRecipeHandler.getIndexOfRecipe(this), true);
		CommonProxy.NETWORKWRAPPER.sendToServer(packet);
		
	}
	
	protected void stopAnimationOnClients(boolean successfully){
		NetworkPacketInfusionRecipeStatus packet = new NetworkPacketInfusionRecipeStatus(centerInput.getPos(), false, -1, successfully);
		CommonProxy.NETWORKWRAPPER.sendToServer(packet);
	}
	
	protected void removeIngredients(){
		for (int i = 0; i < ingredients.length; i++) {
			ingredients[i].setInventorySlotContents(0, null);
			setItemOnClient(ingredients[i].getPos(), null);
		}
	}
	
	protected void setCenterInputItem(){
		centerInput.setInventorySlotContents(0, output);
		setItemOnClient(centerInput.getPos(), output);
	}

	protected void setItemOnClient(BlockPos pos, ItemStack stack) {
		NetworkPacketUpdateInventory message = new NetworkPacketUpdateInventory(pos, stack, 0); //TODO update
		CommonProxy.NETWORKWRAPPER.sendToServer(message);
	}

}
