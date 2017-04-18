package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.blocks.EnumCrystalColor;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class InfusionRecipeSword extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeSword() {
		super("sword", 300);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeSword();
	}
	
	@Override
	public ArrayList<List<ItemStack>> getInputsJEI() {
		ItemStack hR = new ItemStack(ItemHandler.crystallSwordBlade, 1, 0);
		ItemStack hB = new ItemStack(ItemHandler.crystallSwordBlade, 1, 1);
		ItemStack hG = new ItemStack(ItemHandler.crystallSwordBlade, 1, 2);
		ItemStack hY = new ItemStack(ItemHandler.crystallSwordBlade, 1, 3);
		
		ArrayList<List<ItemStack>> ret = new ArrayList<List<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(new ItemStack(ItemHandler.toolRod));
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(ItemHandler.crystallDust_red));
		ret.get(1).add(new ItemStack(ItemHandler.crystallDust_blue));
		ret.get(1).add(new ItemStack(ItemHandler.crystallDust_green));
		ret.get(1).add(new ItemStack(ItemHandler.crystallDust_yellow));
		ret.add(new ArrayList<ItemStack>());
		ret.get(2).add(new ItemStack(ItemHandler.crystallDust_red));
		ret.get(2).add(new ItemStack(ItemHandler.crystallDust_blue));
		ret.get(2).add(new ItemStack(ItemHandler.crystallDust_green));
		ret.get(2).add(new ItemStack(ItemHandler.crystallDust_yellow));
		ret.add(new ArrayList<ItemStack>());
		ret.get(3).add(hR);
		ret.get(3).add(hB);
		ret.get(3).add(hG);
		ret.get(3).add(hY);
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(ItemHandler.crystalSword_red));
		ret.add(new ItemStack(ItemHandler.crystalSword_blue));
		ret.add(new ItemStack(ItemHandler.crystalSword_green));
		ret.add(new ItemStack(ItemHandler.crystalSword_yellow));
		return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(centerInput.getItem() != ItemHandler.toolRod){
			return false;
		}
		
		int sword_red = 0;
		int dust_red = 0;
		
		int sword_blue = 0;
		int dust_blue = 0;
		
		int sword_green = 0;
		int dust_green = 0;
		
		int sword_yellow = 0;
		int dust_yellow = 0;
		
		for (int i = 0; i < ingredients.length; i++) {
			if(ingredients[i] != null){
				if(ingredients[i].getItem() == ItemHandler.crystallSwordBlade){
					int meta = ingredients[i].getItem().getDamage(ingredients[i]); 
					if(meta == EnumCrystalColor.RED.getMetadata()){
						sword_red++;
					}
					else if(meta == EnumCrystalColor.BLUE.getMetadata()){
						sword_blue++;
					}
					else if(meta == EnumCrystalColor.GREEN.getMetadata()){
						sword_green++;
					}
					else if(meta == EnumCrystalColor.YELLOW.getMetadata()){
						sword_yellow++;
					}
				}
				else if(ingredients[i].getItem() == ItemHandler.crystallDust_red){
					dust_red++;
				}
				else if(ingredients[i].getItem() == ItemHandler.crystallDust_blue){
					dust_blue++;
				}
				else if(ingredients[i].getItem() == ItemHandler.crystallDust_green){
					dust_green++;
				}
				else if(ingredients[i].getItem() == ItemHandler.crystallDust_yellow){
					dust_yellow++;
				}
				
			}
		}
		if(ingredients.length != 3){
			return false;
		}
		if(sword_red == 1 && dust_red == 2){
			output = new ItemStack(ItemHandler.crystalSword_red);
			return true;
		}
		else if(sword_blue == 1 && dust_blue == 2){
			output = new ItemStack(ItemHandler.crystalSword_blue);
			return true;
		}
		else if(sword_green == 1 && dust_green == 2){
			output = new ItemStack(ItemHandler.crystalSword_green);
			return true;
		}
		else if(sword_yellow == 1 && dust_yellow == 2){
			output = new ItemStack(ItemHandler.crystalSword_yellow);
			return true;
		}
		
		return false;
	}
}