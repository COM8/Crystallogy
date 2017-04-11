package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusionRecipeRefulelVaporizer extends InfusionRecipe {
	// -----------------------------------------------Variabeln:---------------------------------------------

	// -----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeRefulelVaporizer() {
		super("refulelVaporizer", 640);
	}

	// -----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public ArrayList<List<ItemStack>> getInputsJEI() {
		ArrayList<List<ItemStack>> ret = new ArrayList<List<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ItemStack st = new ItemStack(ItemHandler.vaporizerDirection);
		st.setItemDamage(175);
		ret.get(0).add(st);
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(BlockHandler.fireCrystall));
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(ItemHandler.vaporizerDirection));
		return ret;
	}
	
	// -----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeRefulelVaporizer();
	}

	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if (centerInput.getItem() != ItemHandler.vaporizerDirection) {
			return false;
		}

		int fireCrystall = 0;

		for (int i = 0; i < ingredients.length; i++) {
			if (ingredients[i] != null) {
				if (ingredients[i].getItem() == Item.getItemFromBlock(BlockHandler.fireCrystall)) {
					fireCrystall++;
				}
			}
		}

		if (fireCrystall != 1) {
			return false;
		}

		output = centerInput;
		output.setItemDamage(0);

		return true;
	}

}
