package de.comeight.crystallogy.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ThreatDust extends BaseItemFood {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public int numOfCalls;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ThreatDust(int amount, float saturation, boolean isWolfFood, int numOfCalls, String id) {
		super(amount, saturation, isWolfFood, id);
		this.numOfCalls = numOfCalls;
		setAlwaysEdible();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);	
	}
	
	public abstract void castOnPlayer(ItemStack stack, World worldIn, EntityPlayer player);
	
}
