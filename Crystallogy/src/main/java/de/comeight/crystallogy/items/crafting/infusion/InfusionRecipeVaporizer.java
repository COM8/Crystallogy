package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.particles.ParticleA;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.particles.ParticleC;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class InfusionRecipeVaporizer extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeVaporizer() {
		super("vaporizer", 100);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public ArrayList<ArrayList<ItemStack>> getInputsJEI() {
		ArrayList<ArrayList<ItemStack>> ret = new ArrayList<ArrayList<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(new ItemStack(ItemHandler.vaporizer));
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(Items.dye));
		ret.add(new ArrayList<ItemStack>());
		ret.get(2).add(new ItemStack(Items.gunpowder));
		ret.add(new ArrayList<ItemStack>());
		ret.get(3).add(new ItemStack(Items.diamond));
		ret.get(3).add(new ItemStack(Blocks.leaves));
		ret.get(3).add(new ItemStack(Blocks.dirt));
		ret.add(new ArrayList<ItemStack>());
		ret.get(4).add(new ItemStack(ItemHandler.crystallDust_red));
		ret.get(4).add(new ItemStack(ItemHandler.crystallDust_blue));
		ret.get(4).add(new ItemStack(ItemHandler.crystallDust_green));
		ret.get(4).add(new ItemStack(ItemHandler.crystallDust_yellow));
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(ItemHandler.vaporizer));
		return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeVaporizer();
	}

	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(centerInput.getItem() != ItemHandler.vaporizer){
			return false;
		}
		//Crafting:
		NBTTagCompound tagC2 = null;
		if(centerInput.getItem() == ItemHandler.vaporizer){
			tagC2 = centerInput.getTagCompound();
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
		
		for(int i = 0;i < ingredients.length; i++){
			ItemStack itemstack = ingredients[i];
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
					return false;
				}
			}
			else{
				return false;
			}
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
			for(int i = 0;i < ingredients.length; i++){
				ItemStack itemstack = ingredients[i];
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
		
		output.setTagCompound(tagC);
		return true;
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
	
}
