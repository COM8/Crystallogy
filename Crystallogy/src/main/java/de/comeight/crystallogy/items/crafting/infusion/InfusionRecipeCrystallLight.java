package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeCrystallLight extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeCrystallLight() {
		super("crystallLight", 500);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public ArrayList<List<ItemStack>> getInputsJEI() {
		ArrayList<List<ItemStack>> ret = new ArrayList<List<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(new ItemStack(Items.FLINT_AND_STEEL));
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(Items.GLOWSTONE_DUST));
		ret.add(new ArrayList<ItemStack>());
		ret.get(2).add(new ItemStack(Items.GLOWSTONE_DUST));
		ret.add(new ArrayList<ItemStack>());
		ret.get(3).add(new ItemStack(ItemHandler.crystallDust_red));
		ret.get(3).add(new ItemStack(ItemHandler.crystallDust_blue));
		ret.get(3).add(new ItemStack(ItemHandler.crystallDust_green));
		ret.get(3).add(new ItemStack(ItemHandler.crystallDust_yellow));
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
		ret.add(new ItemStack(BlockHandler.crystalLight));
		return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeCrystallLight();
	}

	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(centerInput.getItem() != Items.FLINT_AND_STEEL){
			return false;
		}
		
		int glowstone = 0;
		int crystallDust = 0;
		
		for (int i = 0; i < ingredients.length; i++) {
			if(ingredients[i] != null){
				if(ingredients[i].getItem() == Items.GLOWSTONE_DUST){
					glowstone++;
				}
				if(ingredients[i].getItem() == ItemHandler.crystallDust_blue || ingredients[i].getItem() == ItemHandler.crystallDust_red || ingredients[i].getItem() == ItemHandler.crystallDust_green || ingredients[i].getItem() == ItemHandler.crystallDust_yellow){
					crystallDust++;
				}
			}
		}
		
		if(glowstone != 2 || crystallDust != 2){
			return false;
		}
		
		output = new ItemStack(BlockHandler.crystalLight);
		output.stackSize = 4;
		
		return true;
	}
	
}
