package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.tileEntitys.TileEntityEntityJar;
import de.comeight.crystallogy.util.EnumThreats;
import de.comeight.crystallogy.util.NBTTags;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityJar extends BaseBlockEntityJar {
	// -----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "entityJar";

	// -----------------------------------------------Constructor:-------------------------------------------
	public EntityJar() {
		super(ID);
	}

	// -----------------------------------------------Set-, Get-Methoden:------------------------------------

	// -----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityEntityJar();
	}

	@Override
	protected void testForUsableItem(ItemStack stack, TileEntityEntityJar jar, BlockPos pos, EntityPlayer playerIn) {
		EnumThreats threat = EnumThreats.getThreatDust(stack);
		boolean removeItem = false;
		if (threat != null) {
			jar.addThreat(threat);
			removeItem = true;
		} else if (stack.getItem() == ItemHandler.entityBrain && stack.getItemDamage() == 0) {
			NBTTagCompound compound = stack.getTagCompound();
			if (compound != null && compound.hasKey(NBTTags.CUSTOM_AI_TYPE)) {
				jar.addCustomAi(stack.getTagCompound());
				removeItem = true;
			}
		}

		if (removeItem && !playerIn.capabilities.isCreativeMode) {
			ItemStack playerIStack = playerIn.getHeldItemMainhand();
			if (playerIStack != null) {
				playerIStack.stackSize--;
			}
		}
	}

}
