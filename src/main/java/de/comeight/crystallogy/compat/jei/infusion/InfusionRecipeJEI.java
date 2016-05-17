package de.comeight.crystallogy.compat.jei.infusion;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipe;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class InfusionRecipeJEI extends BlankRecipeWrapper {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private ArrayList<ArrayList<ItemStack>> inputs = new ArrayList<ArrayList<ItemStack>>();
	private ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
	private int totalCookTime;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeJEI(InfusionRecipe recipe) {
		this.inputs = recipe.getInputsJEI();
		this.outputs = recipe.getOutputJEI();
		this.totalCookTime = recipe.getTotalCookTime();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public List<ArrayList<ItemStack>> getInputs() {
		return inputs;
	}
	
	@Override
	public List getOutputs() {
		return outputs;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRendererObj.drawString("Total Cook Time: " + totalCookTime, 47, 0, 4210752);
	}
	
}
