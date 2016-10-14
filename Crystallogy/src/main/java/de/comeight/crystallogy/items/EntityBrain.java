package de.comeight.crystallogy.items;

import java.util.List;
import java.util.Random;

import de.comeight.crystallogy.handler.AiHandler;
import de.comeight.crystallogy.util.EnumEntityBrains;
import de.comeight.crystallogy.util.ToolTipBuilder;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBrain extends BaseItemFood{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "entityBrain";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityBrain() {
		super(2, 0, true, ID);
		setAlwaysEdible();
		setHasSubtypes(true);
        setMaxDamage(0);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + EnumEntityBrains.byMetadata(stack.getMetadata()).getName();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for (int i = 0; i < 4; ++i)
        {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
	}
	
	@Override
	public int getHealAmount(ItemStack stack) {
		return stack.getMetadata() / 2;
	}
	
	@Override
	public float getSaturationModifier(ItemStack stack) {
		if(stack.getMetadata() == 3){
			return 2;
		}
		return 0;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("aiType");
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		switch (stack.getMetadata()) {
			case 3:
				addEffectsOnEaten(Utilities.getRandInt(11, 14, new Random()), 0, worldIn, player);
				break;
				
			case 2:
				addEffectsOnEaten(Utilities.getRandInt(7, 12, new Random()), 100, worldIn, player);
				break;
				
			case 1:
				addEffectsOnEaten(Utilities.getRandInt(4, 7, new Random()), 300, worldIn, player);
				break;

			default:
				addEffectsOnEaten(Utilities.getRandInt(0, 4, new Random()), 500, worldIn, player);
				break;
		}
		
	}
	
	private void addEffectsOnEaten(int textID, int duration, World worldIn, EntityPlayer player){
		if(!worldIn.isRemote){
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), duration, 1, false, true));
		}
		else{
			player.addChatMessage(new TextComponentString(Utilities.localizeText("item.entityBrain.onFoodEaten." + textID)));
			switch (textID) {
				case 5 :
				case 6 :
					worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_AMBIENT, SoundCategory.PLAYERS, 1.0F, Utilities.getRandFloat(0.8F, 1.2F));
					break;
					
				case 7:
					worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 1.0F, Utilities.getRandFloat(0.8F, 1.2F));
					break;
					
				case 8 :
					worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_DONKEY_AMBIENT, SoundCategory.PLAYERS, 1.0F, Utilities.getRandFloat(0.8F, 1.2F));
					break;
					
				case 9 :
					worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_PIG_AMBIENT, SoundCategory.PLAYERS, 1.0F, Utilities.getRandFloat(0.8F, 1.2F));
					break;
					
				case 10 :
					worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_SHEEP_AMBIENT, SoundCategory.PLAYERS, 1.0F, Utilities.getRandFloat(0.8F, 1.2F));
					break;

				default:
					break;
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(Utilities.localizeText("item.entityBrain.tooltip.0"));
		if(stack.getMetadata() == 3){
			tooltip.add(Utilities.localizeText("item.entityBrain.tooltip.1"));
		}
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("aiType")){
			tooltip.add("");
			tooltip.add(Utilities.localizeText("item.entityBrain.tooltip.2") + "§6 " + Utilities.localizeText("ai.type." + stack.getTagCompound().getInteger("aiType")));
			if(GuiScreen.isShiftKeyDown()){
				tooltip.add("");
				AiHandler.addAdvancedTooltip(stack, playerIn, tooltip);
			}
			else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);	
			}
		}
	}
	
}
