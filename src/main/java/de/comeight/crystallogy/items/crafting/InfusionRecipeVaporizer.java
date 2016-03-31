package de.comeight.crystallogy.items.crafting;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.InfusionParticle;
import de.comeight.crystallogy.particles.ParticleA;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.particles.ParticleC;
import de.comeight.crystallogy.particles.ParticleNColor;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class InfusionRecipeVaporizer {

	//-----------------------------------------------Variabeln:---------------------------------------------
	private boolean active;
	private boolean animationActive;
	public static final int FULLCOOCKTIME = 100;
	public int cookTime;
	
	private World worldIn;
	private TileEnityInfuserBlock centerInfuserBlock;
	private TileEnityInfuserBlock[] infuserBlocks;
	private InfusionParticle[] infusionParticles;
	
	private ItemStack output;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeVaporizer() {
		this.active = false;
		this.animationActive = false;
		this.cookTime = 0;
		this.worldIn = null;
		this.centerInfuserBlock = null;
		this.infuserBlocks = null;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean isAnimationActive() {
		return animationActive;
	}

	public void setAnimationActive(boolean animationActive) {
		this.animationActive = animationActive;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public ItemStack getRecipeOutput() {
		for (int i = 0; i < 30; i++) {
			addInfusionParticle(worldIn, centerInfuserBlock.getPos());
		}
		return output;
	}
	
	public void setInfuserBlocks(BlockPos centerInfuserBlock, BlockPos[] infuserBlocks, World worldIn){
		this.centerInfuserBlock = (TileEnityInfuserBlock) worldIn.getTileEntity(centerInfuserBlock);
		this.infuserBlocks = new TileEnityInfuserBlock[infuserBlocks.length];
		for (int i = 0; i < infuserBlocks.length; i++) {
			this.infuserBlocks[i] = (TileEnityInfuserBlock) worldIn.getTileEntity(infuserBlocks[i]);
			
		}
		this.worldIn = worldIn;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	protected void addInfusionParticle(World worldIn, BlockPos pos){
		ParticleNColor iP = new ParticleNColor(worldIn, pos.getX() + Utilities.getRandDouble(0.25, 0.75), pos.getY() + Utilities.getRandDouble(0.25, 0.75), pos.getZ() + Utilities.getRandDouble(0.25, 0.75), 0.0, 0.0, 0.0);
		iP.setRBGColorF(Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F));
		NetworkParticle nP = new NetworkParticle(iP, iP.NAME);
		nP.setSize(new Vec3d(0.0, 2.0, 0.0));
		NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
		CommonProxy.NETWORKWRAPPER.sendToServer(pMtS);
	}

	public boolean match(BlockPos centerInfuserBlock, BlockPos[] infuserBlocks, World worldIn) {
		setInfuserBlocks(centerInfuserBlock, infuserBlocks, worldIn);
		
		if(this.centerInfuserBlock == null || this.centerInfuserBlock.getStackInSlot(0) == null){
			return false;
		}
		//Crafting:
		NBTTagCompound tagC2 = null;
		if(this.centerInfuserBlock.getStackInSlot(0).getItem() == ItemHandler.vaporizer){
			tagC2 = this.centerInfuserBlock.getStackInSlot(0).getTagCompound();
		}
		else{
			return false;
		}
		
		this.output = null;
		int dye = 0;
		int gunpowder = 0;
		int cB = 0;
		int cR = 0;
		int cG = 0;
		int cY = 0;
		int cP = 0;
		int leave = 0;
		int diamond = 0;
		int dirt = 0;
		int wrongItem = 0;
		
		for(int i = 0;i < this.infuserBlocks.length; i++){
			TileEnityInfuserBlock t = this.infuserBlocks[i];
			if(t == null){
				wrongItem++;
			}
			else{
				ItemStack itemstack = t.getStackInSlot(0);
				if(itemstack != null){
					if(itemstack.getItem() == Items.gunpowder){
						gunpowder++;
					}
					else if(itemstack.getItem() == Items.dye){
						dye++;
					}
					else if(itemstack.getItem() == ItemHandler.crystallDust_blue){
						cB++;
					}
					else if(itemstack.getItem() == ItemHandler.crystallDust_green){
						cG++;			
					}
					else if(itemstack.getItem() == ItemHandler.crystallDust_red){
						cR++;
					}
					else if(itemstack.getItem() == ItemHandler.crystallDust_yellow){
						cY++;
					}
					else if(itemstack.getItem() == ItemHandler.pureCrystallDust){
						cP++;
					}
					else if(itemstack.getItem() == Items.diamond){
						diamond++;
					}
					else if(itemstack.getItem() == Item.getItemFromBlock(Blocks.leaves)){
						leave++;
					}
					else if(itemstack.getItem() == Item.getItemFromBlock(Blocks.dirt)){
						dirt++;
					}
					else{
						wrongItem++;
						this.infuserBlocks[i] = null;
						return false;
					}
				}
				else{
					this.infuserBlocks[i] = null;
					wrongItem++;
				}
			}
		}
		if(wrongItem == 4){
			return false;
		}
		if(diamond > 1){
			return false;
		}
		if(leave > 1){
			return false;
		}
		if(dirt > 1){
			return false;
		}
		
		//Output:
		output = new ItemStack(ItemHandler.vaporizer);
		NBTTagCompound tagC = new NBTTagCompound();
		if(tagC2 != null){
			tagC = (NBTTagCompound) tagC2.copy();
		}
		
		//Type:
		if(leave == 1){
			tagC.setString("particleType", ParticleA.NAME);
		}
		if(diamond == 1){
			tagC.setString("particleType", ParticleB.NAME);
		}
		if(dirt == 1){
			tagC.setString("particleType", ParticleC.NAME);
		}
		
		//Size:
		if(gunpowder > 0){
			Vec3d size = new Vec3d(tagC.getDouble("sizeX") + gunpowder, tagC.getDouble("sizeY") + gunpowder, tagC.getDouble("sizeZ") + gunpowder);
			tagC.setDouble("sizeX", size.xCoord);
			tagC.setDouble("sizeY", size.yCoord);
			tagC.setDouble("sizeZ", size.zCoord);
		}
		
		//NumberOfParticle:
		if(cP > 0){
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cP * 10);
		}
		if(cR > 0){
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cR * 10);
			tagC = (NBTTagCompound) manageColor(new RGBColor(0.1F, -0.1F, -0.1F), tagC).copy();
		}
		if(cG > 0){
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cG * 10);
			tagC = (NBTTagCompound) manageColor(new RGBColor(-0.1F, 0.1F, -0.1F), tagC).copy();
		}
		if(cB > 0){
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cB * 10);
			tagC = (NBTTagCompound) manageColor(new RGBColor(-0.1F, -0.1F, 0.1F), tagC).copy();
		}
		if(cY > 0){
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cY * 10);
			tagC = (NBTTagCompound) manageColor(new RGBColor(0.1F, 0.1F, -0.1F), tagC).copy();
		}
		
		//Color:
		if(dye > 0){
			Item item;
			for(int i = 0;i < this.infuserBlocks.length; i++){
				TileEnityInfuserBlock t = this.infuserBlocks[i];
				if(t != null){
					ItemStack itemstack = t.getStackInSlot(0);
					if(itemstack != null){
						if((item = itemstack.getItem()) == Items.dye){
							if(item.getDamage(itemstack) == EnumDyeColor.RED.getDyeDamage()){
								tagC = (NBTTagCompound) manageColor(new RGBColor(0.1F, -0.1F, -0.1F), tagC).copy();
							}
							if(item.getDamage(itemstack) == EnumDyeColor.GREEN.getDyeDamage()){
								tagC = (NBTTagCompound) manageColor(new RGBColor(-0.1F, 0.1F, -0.1F), tagC).copy();
							}
							if(item.getDamage(itemstack) == EnumDyeColor.BLUE.getDyeDamage()){
								tagC = (NBTTagCompound) manageColor(new RGBColor(-0.1F, -0.1F, 0.1F), tagC).copy();
							}
							if(item.getDamage(itemstack) == EnumDyeColor.YELLOW.getDyeDamage()){
								tagC = (NBTTagCompound) manageColor(new RGBColor(0.1F, 0.1F, -0.1F), tagC).copy();
							}
							if(item.getDamage(itemstack) == EnumDyeColor.WHITE.getDyeDamage()){
								tagC = (NBTTagCompound) manageColor(new RGBColor(0.1F, 0.1F, 0.1F), tagC).copy();
							}
							if(item.getDamage(itemstack) == EnumDyeColor.BLACK.getDyeDamage()){
								tagC = (NBTTagCompound) manageColor(new RGBColor(-0.1F, -0.1F, -0.1F), tagC).copy();
							}
							if(item.getDamage(itemstack) == EnumDyeColor.PURPLE.getDyeDamage()){
								tagC = (NBTTagCompound) manageColor(new RGBColor(0.1F, -0.1F, 0.1F), tagC).copy();
							}
						}
					}
				}
			}
		}
		
		output.setTagCompound(tagC);
		
		return true;
	}
	
	public void cook(){
		this.active = true;
		this.cookTime = 0;
		startAnimationServer();
	}
	
	public boolean tick(){
		if(incCookTime()){
			removeItemsFromInfuserBlocks();
			stopAnimationServer();
			this.active = false;
			animationActive = false;
			return true;
		}
		else{
			if(!isStillActive()){
				this.active = false;
				this.output = centerInfuserBlock.getStackInSlot(0);
				
				for (int j = 0; j < infuserBlocks.length; j++) {
					if(infuserBlocks[j] != null){
						for (int i = 0; i < 30; i++) {
							addInfusionParticle(worldIn, infuserBlocks[j].getPos());
						}
					}
				}
				if(!worldIn.isRemote){
					stopAnimationServer();
				}
				return true;
			}
			return false;
		}
	}
	
	private void startAnimationServer(){
		NetworkPacketInfusionRecipeStatus message = new NetworkPacketInfusionRecipeStatus(new Vec3d(this.centerInfuserBlock.getPos()), true);
		CommonProxy.NETWORKWRAPPER.sendToServer(message);
	}

	public void startAnimations(World world){
		if(!world.isRemote){
			return;
		}
		if(infuserBlocks == null){
			System.out.println("InfuserBlock == null!");
			return;
		}
		if(animationActive){
			return;
		}
		this.infusionParticles = new InfusionParticle[infuserBlocks.length];
		for (int i = 0; i < infuserBlocks.length; i++) {
			if(infuserBlocks[i] != null && infuserBlocks[i].getStackInSlot(0) != null){
				BlockPos p1 = infuserBlocks[i].getPos();
				BlockPos p2 = centerInfuserBlock.getPos();
				this.infusionParticles[i] = new InfusionParticle(world, 0.0, 0.0, 0.0, new Vec3d(p1.getX() + 0.5, p1.getY() + 0.75, p1.getZ() + 0.5), new Vec3d(p2.getX() + 0.5, p2.getY() + 0.75, p2.getZ() + 0.5), this.FULLCOOCKTIME - cookTime);
				this.infusionParticles[i].setRBGColorF(Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F));
				Minecraft.getMinecraft().effectRenderer.addEffect(infusionParticles[i]);
			}
		}
		animationActive = true;
	}
	
	private void stopAnimationServer(){
		NetworkPacketInfusionRecipeStatus message = new NetworkPacketInfusionRecipeStatus(new Vec3d(this.centerInfuserBlock.getPos()), false);
		CommonProxy.NETWORKWRAPPER.sendToServer(message);
	}
	
	public void stopAnimation(World worldIn){
		if(!worldIn.isRemote){
			return;
		}
		if(infusionParticles == null){
			return;
		}
		if(!animationActive){
			return;
		}
		animationActive = false;
		for (int j = 0; j < infusionParticles.length; j++) {
			if(infusionParticles[j] != null){
				infusionParticles[j].setExpired();
			}
		}
	}
	
	private boolean incCookTime(){
		if(this.cookTime == FULLCOOCKTIME){
			return true;
		}
		else{
			cookTime++;
			return false;
		}
	}
	
	private NBTTagCompound manageColor(RGBColor modifire, NBTTagCompound nbtTag){
		RGBColor c = new RGBColor();
		c.r = nbtTag.getFloat("r");
		c.r = (float) Utilities.calcNewColorPercentage((float) c.r, modifire.r);
		nbtTag.setFloat("r", c.r);
		
		c.g = nbtTag.getFloat("g");
		c.g = (float) Utilities.calcNewColorPercentage((float) c.g, modifire.g);
		nbtTag.setFloat("g", c.g);
		
		c.b = nbtTag.getFloat("b");
		c.b = (float) Utilities.calcNewColorPercentage((float) c.b, modifire.b);
		nbtTag.setFloat("b", c.b);
		return nbtTag;
	}
	
	public void removeItemsFromInfuserBlocks(){
		for (int i = 0; i < infuserBlocks.length; i++) {
			if(infuserBlocks[i] != null){
				infuserBlocks[i].removeStackFromSlot(0);
				setItemsFromInfuserBlocksNetwork(infuserBlocks[i].getPos(), null);
			}
		}
	}
	
	private boolean isStillActive(){
		if(centerInfuserBlock.getStackInSlot(0) == null){
			return false;
		}
		for (int i = 0; i < infuserBlocks.length; i++) {
			if(infuserBlocks[i] != null){
				if(infuserBlocks[i].getStackInSlot(0) == null){
					return false;
				}
			}
		}
		return true;
	}
	
	private void setItemsFromInfuserBlocksNetwork(BlockPos pos, ItemStack stack) {
		NetworkPacketUpdateInventory message = new NetworkPacketUpdateInventory(new Vec3d(pos), stack, 0);
		CommonProxy.NETWORKWRAPPER.sendToServer(message);
	}
	
}
