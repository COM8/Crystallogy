package de.comeight.crystallogy.items.tools;

import java.util.ArrayList;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BaseItemPickaxe extends ItemPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemPickaxe(ToolMaterial material, String ID) {
		super(material);
		setCreativeTab(CommonProxy.crystallogyMainTab);
		setUnlocalizedName(ID);
		setRegistryName(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	protected void canHarvestBlock(ItemStack stack, World worldIn, BlockPos pos, ArrayList<BlockPos> list){
		IBlockState state = worldIn.getBlockState(pos);
		if(state.getBlock().getHarvestLevel(state) <= getHarvestLevel(stack, "pickaxe") && canHarvestBlock(state)){
			list.add(pos);
		}
	}
	
}
