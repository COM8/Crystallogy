package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class InfusionRecipeAiDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeAiDust() {
		super("aiDust", 230, new ItemStack(ItemHandler.blindDust),
				new ItemStack[]{ new ItemStack(ItemHandler.crystallDust_green, 1),
				new ItemStack(ItemHandler.crystallDust_yellow, 1),
				new ItemStack(ItemHandler.pureCrystallDust, 1),
				new ItemStack(ItemHandler.fertilizerPotato, 1)},
				new ItemStack(ItemHandler.aiRemoverDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeAiDust();
	}
	
}
