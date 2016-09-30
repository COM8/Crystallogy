package de.comeight.crystallogy.items;

import java.util.List;

import de.comeight.crystallogy.entity.ai.EntityAiPickupItems;
import de.comeight.crystallogy.entity.ai.EntityAiQuarry;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityBrain extends BaseItemFood{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "entityBrain";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityBrain() {
		super(2, 0, true, ID);
		setAlwaysEdible();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private void setAi(EntityLiving entity, EntityPlayer player){
		if(player.isSneaking()){
			entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiPickupItems(entity, entity.getPosition(), new BlockPos(16, 2 ,16)));
		}
		else{
			entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiQuarry(entity, entity.getPosition().add(0, -1, 0), new BlockPos(16, -2 ,16)));	
		}
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if(!worldIn.isRemote){
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 300, 1, false, true));
		}
		else{
			player.addChatMessage(new TextComponentString(Utilities.localizeText("item.entityBrain.onFoodEaten")));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
	}
	
}
