package de.comeight.crystallogy.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FertilizerPotato extends BaseItemFood {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "fertilizerPotato";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public FertilizerPotato() {
		super(-10, 0.0F, false, ID);
		setAlwaysEdible();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote){
			int duration = 300;
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), duration, 4, false, true)); //Poison
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), duration, 4, false, true)); //Slowness
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(4), duration, 1, false, true)); //Mining Fatigue
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), duration, 1, false, true)); //Nasusea
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), duration, 1, false, true)); //Blindnes
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), duration, 3, false, true)); //Hunger
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(18), duration, 4, false, true)); //Weakness
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(27), duration, 1, false, true)); //Bad Luck
		}
		super.onFoodEaten(stack, worldIn, player);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.DARK_RED + "Extremely Poisonous!");
		tooltip.add("");
		tooltip.add("I warned you :-)");
	}
	
}
