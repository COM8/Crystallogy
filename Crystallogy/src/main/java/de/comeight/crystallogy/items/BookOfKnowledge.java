package de.comeight.crystallogy.items;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookMain;
import de.comeight.crystallogy.handler.SoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BookOfKnowledge extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "bookOfKnowledge";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookOfKnowledge() {
		super(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		BlockPos pos = playerIn.getPosition();
		
		if (worldIn.isRemote)
        {	
			playerIn.openGui(CrystallogyBase.INSTANCE, GuiBookMain.ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
			playerIn.playSound(SoundHandler.BOOKOPEN, 1.0F, 1.0F);
        }
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}
	
}
