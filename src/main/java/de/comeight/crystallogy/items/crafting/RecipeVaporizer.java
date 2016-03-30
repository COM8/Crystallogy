package de.comeight.crystallogy.items.crafting;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RecipeVaporizer implements IRecipe{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private ItemStack outupVaporizer;
	private Item inputVaporizer;

	//-----------------------------------------------Constructor:-------------------------------------------
	public RecipeVaporizer() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return this.outupVaporizer.copy();
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.outupVaporizer;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		ItemStack[] aitemstack = new ItemStack[inv.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
        }
        return aitemstack;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		this.outupVaporizer = null;
		int vaporizer = 0;
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
		
		NBTTagCompound tagC2 = null;
		
		for(int i = 0;i < inv.getSizeInventory(); i++){
			ItemStack itemstack = inv.getStackInSlot(i);
			if(itemstack != null){
				if(itemstack.getItem() == Items.gunpowder){
					gunpowder++;
				}
				else if(itemstack.getItem() == ItemHandler.vaporizer){
					vaporizer++;
					inputVaporizer = itemstack.getItem();
					tagC2 = itemstack.getTagCompound();
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
		}
		if(vaporizer != 1){
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
		outupVaporizer = new ItemStack(ItemHandler.vaporizer);
		NBTTagCompound tagC = new NBTTagCompound();
		if(tagC2 != null){
			tagC = (NBTTagCompound) tagC2.copy();
		}
		
		//Type:
		if(diamond == 1){
			tagC.setString("particleType", "b");
		}
		if(leave == 1){
			tagC.setString("particleType", "a");
		}
		if(dirt == 1){
			tagC.setString("particleType", "c");
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
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cP * 10);
			tagC = (NBTTagCompound) manageColor(new RGBColor(0.1F, -0.1F, -0.1F), tagC).copy();
		}
		if(cG > 0){
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cP * 10);
			tagC = (NBTTagCompound) manageColor(new RGBColor(-0.1F, 0.1F, -0.1F), tagC).copy();
		}
		if(cB > 0){
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cP * 10);
			tagC = (NBTTagCompound) manageColor(new RGBColor(-0.1F, -0.1F, 0.1F), tagC).copy();
		}
		if(cY > 0){
			tagC.setInteger("numberOfParticle", tagC.getInteger("numberOfParticle") + cP * 10);
			tagC = (NBTTagCompound) manageColor(new RGBColor(0.1F, 0.1F, -0.1F), tagC).copy();
		}
		
		//Color:
		if(dye > 0){
			Item item;
			for(int i = 0;i < inv.getSizeInventory(); i++){
				ItemStack itemstack = inv.getStackInSlot(i);
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
		
		outupVaporizer.setTagCompound(tagC);
		
		return true;
	}

	private NBTTagCompound manageColor(RGBColor modifire, NBTTagCompound nbtTag){
		RGBColor c = new RGBColor();
		c.r = nbtTag.getFloat("r");
		c.r = (float) calcNewColorPercentage((float) c.r, modifire.r);
		nbtTag.setFloat("r", c.r);
		
		c.g = nbtTag.getFloat("g");
		c.g = (float) calcNewColorPercentage((float) c.g, modifire.g);
		nbtTag.setFloat("g", c.g);
		
		c.b = nbtTag.getFloat("b");
		c.b = (float) calcNewColorPercentage((float) c.b, modifire.b);
		nbtTag.setFloat("b", c.b);
		return nbtTag;
	}
	
	private double calcNewColorPercentage(double color, double modifire){
		color += modifire;
		color = (double) Utilities.round((float) color, 2);
		if(color > 1.0){
			color = 1.0;
		}
		if(color < 0.0){
			color = 0.0;
		}
		
		return color;
	}
	
}
