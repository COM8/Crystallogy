package de.comeight.crystallogy.blocks;

import java.util.List;
import java.util.Random;

import de.comeight.crystallogy.blocks.materials.CustomMaterials;
import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerJar extends BaseBlockTileEntity {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "playerJar";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public PlayerJar() {
		super(CustomMaterials.glass, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.685, 0.75);
	}
	
	private ItemStack getItemStackWithData(World world, BlockPos pos){
		TileEntity tE = world.getTileEntity(pos);
		ItemStack stack = new ItemStack(this);
		if(tE instanceof TileEntityPlayerJar){
			TileEntityPlayerJar jar = (TileEntityPlayerJar) tE;
			NBTTagCompound compound = new NBTTagCompound();
			
			if(jar.hasPlayer()){
				jar.writeToNBT(compound);
				stack.setTagCompound(compound);
			}
		}
		return stack;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return getItemStackWithData(world, pos);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
		ret.add(getItemStackWithData((World) world, pos));
        return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity tE = worldIn.getTileEntity(pos);
		if(tE instanceof TileEntityPlayerJar && stack.hasTagCompound()){
			TileEntityPlayerJar jar = (TileEntityPlayerJar) tE;
			jar.readFromNBT(stack.getTagCompound());
		}
	}
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		if (worldIn.isRemote) { // Client Side Welt?
			return 0;
		} 
		else {
			TileEntity tE = worldIn.getTileEntity(pos);
			if(tE instanceof TileEntityPlayerJar){
				TileEntityPlayerJar jar = (TileEntityPlayerJar) tE;
				if (tE != null) {
					if(jar.hasPlayer()){
						return 2;
					}
				}
			}
			return 0;
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity tE = worldIn.getTileEntity(pos);
		if(tE instanceof TileEntityPlayerJar){
			TileEntityPlayerJar jar = (TileEntityPlayerJar) tE;
			if(jar.getPlayer() != null){
				if(playerIn.isSneaking()){
					jar.removePlayer(worldIn, new Vec3d(pos), true); //TODO Fix no sound playing
					worldIn.notifyNeighborsOfStateChange(pos, this);
				}
				else{
					if(worldIn.isRemote){
						PlayerClientDummy player = jar.getPlayer();
						playerIn.addChatMessage(new TextComponentString("Name: " + player.getGameProfile().getName()));
						playerIn.addChatMessage(new TextComponentString("UUID: " + player.getGameProfile().getId()));
					}
				}
			}
		}
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		super.onBlockClicked(worldIn, pos, playerIn);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPlayerJar();
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
}
