package de.comeight.crystallogy.items.tools;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class BaseItemHammer extends BaseItemPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemHammer(ToolMaterial material, String ID) {
		super(material, ID);
		
		setMaxDamage(getMaxDamage() * 11);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving) {
		if(!entityLiving.isSneaking() && blockIn.getBlock().isToolEffective("pickaxe", blockIn)){
			breakBlocksHammer(stack, worldIn, blockIn, pos, entityLiving);
		}
		else{
			if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
	        {
	            stack.damageItem(1, entityLiving);
	        }
		}
		return true;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Mines a " + TextFormatting.DARK_PURPLE + "3x3 " + TextFormatting.RESET + TextFormatting.GRAY + "area.");
		
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}
