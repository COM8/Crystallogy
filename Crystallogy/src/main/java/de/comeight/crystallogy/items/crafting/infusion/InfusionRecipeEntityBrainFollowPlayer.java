package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;
import java.util.UUID;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.EnumCustomAis;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class InfusionRecipeEntityBrainFollowPlayer extends InfusionRecipeBaseEntityBrainAi {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeEntityBrainFollowPlayer() {
		super("entityBrainFollowPlayer", 200);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeEntityBrainFollowPlayer();
	}

	@Override
	public ArrayList<ArrayList<ItemStack>> getInputsJEI() {
		ArrayList<ArrayList<ItemStack>> ret = new ArrayList<ArrayList<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(new ItemStack(ItemHandler.entityBrain, 1, 0));
		
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(ItemHandler.pureCrystallDust));
		
		ret.add(new ArrayList<ItemStack>());
		ret.get(2).add(new ItemStack(ItemHandler.energyCrystal));
		
		ret.add(new ArrayList<ItemStack>());
		ret.get(3).add(new ItemStack(ItemHandler.pureCrystallDust));
		
		ret.add(new ArrayList<ItemStack>());
		ItemStack stack = new ItemStack(ItemHandler.playerCrystalKnife);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("name", "Any Player!");
		stack.setTagCompound(compound);
		ret.get(4).add(stack);
		
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ItemStack s = new ItemStack(ItemHandler.entityBrain, 1, 0);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("aiType", EnumCustomAis.FOLLOW_PLAYER.ID);
		compound.setString("playerName", "Any Player!");
		s.setTagCompound(compound);
		ret.add(s);
		return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(!ItemStack.areItemsEqual(centerInput, new ItemStack(ItemHandler.entityBrain, 1, 0))){
			return false;
		}
		
		this.output = null;
		
		NBTTagCompound compound = centerInput.getTagCompound();
		if(compound == null){
			compound = new NBTTagCompound();
		}
		
		int playerCrystalKnife = 0;
		int energyCrystal = 0;
		int pureCrystalDust = 0;
		
		BlockPos targetPos = null;
		
		for(int i = 0;i < ingredients.length; i++){
			ItemStack itemstack = ingredients[i];
			if(itemstack != null){
				if(itemstack.getItem() == ItemHandler.playerCrystalKnife && ItemHandler.playerCrystalKnife.hasEntity(itemstack)){
					compound.setString("playerName", itemstack.getTagCompound().getString("name"));
					compound.setUniqueId("playerUUID", UUID.fromString(itemstack.getTagCompound().getString("uuid")));
					playerCrystalKnife++;
				}
				else if(itemstack.getItem() == ItemHandler.energyCrystal && itemstack.getItemDamage() == 0){
					energyCrystal++;
				}
				else if(itemstack.getItem() == ItemHandler.pureCrystallDust){
					pureCrystalDust++;
				}
				else{
					return false;
				}
			}
		}
		
		if(energyCrystal != 1 || pureCrystalDust != 2 || playerCrystalKnife != 1){
			return false;
		}
		compound.setInteger("aiType", EnumCustomAis.FOLLOW_PLAYER.ID);
		output = new ItemStack(ItemHandler.entityBrain, 1, 0);
		output.setTagCompound(compound);
		
		return true;
	}
	
}
