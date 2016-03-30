package de.comeight.crystallogy.blocks;

import java.util.Random;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.blocks.container.BaseBlockContainer;
import de.comeight.crystallogy.network.MessageToServer;
import de.comeight.crystallogy.structures.StructureInfuser;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InfuserBlock extends BaseBlockContainer {

	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "infuserBlock";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfuserBlock() {
		super(Material.rock, ID);
		
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(30);
		this.setStepSound(SoundType.STONE);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.555, 1.0);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEnityInfuserBlock tE = (TileEnityInfuserBlock) worldIn.getTileEntity(pos);
		
		if (tE == null || playerIn.isSneaking())
        {
			if(tE != null){
				Utilities.printWorldSide(worldIn);
				System.out.println(tE.getStackInSlot(0));
			}
			
			if(!tE.getWorld().isRemote){
				tE.setActive(tE.checkForStructure());
			}
			
            return false;
        }
		
		ItemStack playerIStack = playerIn.getHeldItemMainhand();
		
		if (tE.getStackInSlot(0) == null && playerIStack != null){
			ItemStack newItem = playerIStack.copy();
            newItem.stackSize = 1;
            playerIStack.stackSize--;
            tE.setInventorySlotContents(0, newItem);
            tryInfuse(tE, worldIn, pos);
        } else if (tE.getStackInSlot(0) != null && playerIStack == null)
        {
            playerIn.inventory.addItemStackToInventory(tE.removeStackFromSlot(0));
        }
		updateTileEntity(worldIn, pos);
		worldIn.notifyNeighborsOfStateChange(pos, this);
        tE.markDirty();
        if(worldIn.isRemote){
        	setItemsFromInfuserBlocksNetwork(tE.getPos(), tE.getStackInSlot(0));
        }
        return true;
	}
	
	private void setItemsFromInfuserBlocksNetwork(BlockPos pos, ItemStack stack) {
		MessageToServer message = new MessageToServer(MessageToServer.ITEMSTACK, new Vec3d(pos), stack);
		CommonProxy.NETWORKWRAPPER.sendToServer(message);
	}
	
	private void tryInfuse(TileEnityInfuserBlock tE, World worldIn, BlockPos pos) {
		if(!tE.isActive()){
			tE.checkForStructure();
		}
		if(tE.isActive() && !tE.recipe.isActive()){
			BlockPos[] infuserBlocks = StructureInfuser.getSurroundingPedals(pos);
			if(tE.recipe.match(pos, infuserBlocks, worldIn)){
				tE.recipe.cook();
			}
		}
	}

	private void updateTileEntity(World worldIn, BlockPos pos){
		TileEnityInfuserBlock tE = (TileEnityInfuserBlock) worldIn.getTileEntity(pos);
		tE.update();
	}
	
	@Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEnityInfuserBlock();
    }
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		if(worldIn.isRemote){	//Client Side Welt?
		     return 0;   
			}
			else{
				TileEnityInfuserBlock tE = (TileEnityInfuserBlock) worldIn.getTileEntity(pos);
				if(tE != null){
					if(tE.getStackInSlot(0) != null){
						return 10;
					}
				}
				return 0;
			}
	}
	
}
