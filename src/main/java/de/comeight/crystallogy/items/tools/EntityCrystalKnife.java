package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.tileEntitys.TileEntityEntityJar;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCrystalKnife extends BaseCrystalKnife{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "entityCrystalKnife";
	
	private float attackDamage;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityCrystalKnife() {
		super(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(playerIn.isSneaking()){
			itemStackIn = removeEntity(itemStackIn, worldIn, playerIn.getPositionVector(), true);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(hasEntity(stack)){
			if(worldIn.getTileEntity(pos) instanceof TileEntityEntityJar && !(worldIn.getTileEntity(pos) instanceof TileEntityPlayerJar)){
				TileEntityEntityJar tE = (TileEntityEntityJar) worldIn.getTileEntity(pos);
				if(!tE.hasEntity()){
					tE.setEntity(getEntity(stack, worldIn));
					stack = removeEntity(stack, worldIn, playerIn.getPositionVector(), false);
				}
			}
		}
		
		return EnumActionResult.SUCCESS;
	}

}
