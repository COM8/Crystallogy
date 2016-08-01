package de.comeight.crystallogy.items.crafting;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeBookOfKnowledge implements IRecipe{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public RecipeBookOfKnowledge() {
		
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return new ItemStack(ItemHandler.bookOfKnowledge);
	}

	@Override
	public int getRecipeSize() {
		return 2;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		return null;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		int books = 0;
		int crystals = 0;
		ItemStack itemstack = null;

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			itemstack = inv.getStackInSlot(i);

			if (itemstack != null) {
				if (itemstack.getItem() == Items.BOOK) {
					books++;
				}
				else if (itemstack.getItem() == Item.getItemFromBlock(BlockHandler.crystall_blue) || itemstack.getItem() == Item.getItemFromBlock(BlockHandler.crystall_red) || itemstack.getItem() == Item.getItemFromBlock(BlockHandler.crystall_green) || itemstack.getItem() == Item.getItemFromBlock(BlockHandler.crystall_yellow)) {
					crystals++;
				}
				else{
					return false;
				}
			}
		}
		return books == 1 && crystals == 1;
	}
	
}
