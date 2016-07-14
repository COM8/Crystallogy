package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.items.BaseItem;
import de.comeight.crystallogy.particles.ParticleDebug;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DebugTool extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "debugTool";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public DebugTool() {
		super(ID);
		
		setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote){
			spawnDebugParticle(worldIn, new Vec3d(pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ));
		}
		
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@SideOnly(Side.CLIENT)
	private void spawnDebugParticle(World worldIn, Vec3d pos){
		ParticleDebug pD = new ParticleDebug(worldIn, pos);
		pD.setCanTickToDeath(false);
		Minecraft.getMinecraft().effectRenderer.addEffect(pD);
	}
	
}
