package de.comeight.crystallogy.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import de.comeight.crystallogy.tileEntitys.TileEntityCrystalOfHolding;
import de.comeight.crystallogy.util.EntityUtils;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrystalOfHolding extends BaseBlockCutout implements IPlantable {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystalOfHolding";
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
	private static final AxisAlignedBB[] CRYSTAL_OF_HOLDING_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D)};
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalOfHolding() {
		super(Material.ICE, ID);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CRYSTAL_OF_HOLDING_AABB[((Integer)state.getValue(AGE)).intValue()];
    }
	
	@Override
    public boolean hasTileEntity()
    {
        return true;
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }
    
    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
    
    @Override
    public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int count = 1;

        if (((Integer)state.getValue(AGE)) >= 2)
        {
            count = 2 + rand.nextInt(3) + (fortune > 0 ? rand.nextInt(fortune + 1) : 0);
        }

        for (int i = 0; i < count; i++)
        {
            ret.add(getItemStackWithData((World) world, pos));
        }

        return ret;
    }
    
    @Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return getItemStackWithData(world, pos);
	}
	
	private ItemStack getItemStackWithData(World world, BlockPos pos){
		TileEntity tE = world.getTileEntity(pos);
		ItemStack stack = new ItemStack(this);
		if(tE instanceof TileEntityCrystalOfHolding){
			TileEntityCrystalOfHolding crystal = (TileEntityCrystalOfHolding) tE;
			NBTTagCompound compound = new NBTTagCompound();
			
			if(crystal.hasEntity()){
				EntityUtils.writeEntityToCompound(compound, crystal.getEntity());
				stack.setTagCompound(compound);
			}
		}
		return stack;
	}
	
	private Entity getEntity(World worldIn, BlockPos pos){
		TileEntity tE = worldIn.getTileEntity(pos);
		if(tE instanceof TileEntityCrystalOfHolding){
			TileEntityCrystalOfHolding crystal = (TileEntityCrystalOfHolding) tE;
			return crystal.getEntity();
		}
		return null;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity tE = worldIn.getTileEntity(pos);
		if(tE instanceof TileEntityCrystalOfHolding && stack.hasTagCompound()){
			TileEntityCrystalOfHolding crystal = (TileEntityCrystalOfHolding) tE;
			crystal.setEntity(EntityUtils.readEntityFromCompound(stack.getTagCompound(), worldIn));
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if(worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && canSustainCrystal(worldIn.getBlockState(pos.down()))){
			return true;
		}
		return false;
	}
	
	private void findAndTryPlant(World worldIn, BlockPos pos, Random rand){
		Entity ent = getEntity(worldIn, pos);
		int side = rand.nextInt(4);
		switch(side){
			case 0:
				tryPlantAt(worldIn, pos.north(), ent);
				break;
			case 1:
				tryPlantAt(worldIn, pos.east(), ent);
				break;
			case 2:
				tryPlantAt(worldIn, pos.west(), ent);
				break;
			case 3:
				tryPlantAt(worldIn, pos.south(), ent);
				break;
		}
	}
	
	private void tryPlantAt(World worldIn, BlockPos pos, Entity ent){
		if(worldIn.getBlockState(pos).getBlock() == Blocks.AIR && canSustainCrystal(worldIn.getBlockState(pos.down()))){
			worldIn.setBlockState(pos, this.getDefaultState(), 2);
			copyEntity(worldIn, pos, ent);
		}
	}
	
	private void copyEntity(World worldIn, BlockPos pos, Entity ent){
		TileEntity tE = worldIn.getTileEntity(pos);
		if(tE instanceof TileEntityCrystalOfHolding){
			TileEntityCrystalOfHolding crystal = (TileEntityCrystalOfHolding) tE;
			crystal.setEntity(ent);
		}
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int i = ((Integer)state.getValue(AGE)).intValue();

        if (i <= 2 && rand.nextInt(10) == 0)
        {
        	if(i == 2){
            	findAndTryPlant(worldIn, pos, rand);
            }
        	else{
        		grow(worldIn, pos, state, i + 1);
        	}
        }

        super.updateTick(worldIn, pos, state, rand);
    }
	
	private void grow(World worldIn, BlockPos pos, IBlockState state, int age){
        TileEntity tE = worldIn.getTileEntity(pos);
		
        state = state.withProperty(AGE, Integer.valueOf(age));
        worldIn.setBlockState(pos, state, 2);
		
		if(tE != null){
			tE.validate();
			worldIn.setTileEntity(pos, tE);
		}
	}
	
	protected boolean canSustainCrystal(IBlockState state)
    {
        return state.getBlock() == Blocks.SANDSTONE;
    }
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityCrystalOfHolding();
	}
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }
	
}
