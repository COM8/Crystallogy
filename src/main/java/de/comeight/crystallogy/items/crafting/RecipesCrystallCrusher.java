package de.comeight.crystallogy.items.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesCrystallCrusher {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final RecipesCrystallCrusher crystallCrusherRecipes = new RecipesCrystallCrusher();
    private Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    private Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	//-----------------------------------------------Constructor:-------------------------------------------
    public static RecipesCrystallCrusher instance()
    {
        return crystallCrusherRecipes;
    }
    
    public RecipesCrystallCrusher(){
    	this.addCrusherRecipeForBlock(BlockHandler.crystall_yellow, new ItemStack(ItemHandler.crystallDust_yellow), 1.0F);
    	this.addCrusherRecipeForBlock(BlockHandler.crystall_red, new ItemStack(ItemHandler.crystallDust_red), 1.0F);
    	this.addCrusherRecipeForBlock(BlockHandler.crystall_blue, new ItemStack(ItemHandler.crystallDust_blue), 1.0F);
    	this.addCrusherRecipeForBlock(BlockHandler.crystall_green, new ItemStack(ItemHandler.crystallDust_green), 1.0F);
    }
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    public float getCrusherExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }

        return 0.0F;
    }
    
    public ItemStack getCrusherResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return (ItemStack)entry.getValue();
            }
        }

        return null;
    }

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    public void addCrusherRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addCrusher(Item.getItemFromBlock(input), stack, experience);
    }

    
    public void addCrusher(Item input, ItemStack stack, float experience)
    {
        this.addCrusherRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    public void addCrusherRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (getCrusherResult(input) != null) { net.minecraftforge.fml.common.FMLLog.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack); return; }
        this.smeltingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.smeltingList;
    }

}
