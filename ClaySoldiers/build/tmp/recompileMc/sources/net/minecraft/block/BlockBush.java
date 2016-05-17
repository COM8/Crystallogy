package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBush extends Block implements net.minecraftforge.common.IPlantable
{
    protected static final AxisAlignedBB field_185515_b = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

    protected BlockBush()
    {
        this(Material.plants);
    }

    protected BlockBush(Material materialIn)
    {
        this(materialIn, materialIn.getMaterialMapColor());
    }

    protected BlockBush(Material p_i46452_1_, MapColor p_i46452_2_)
    {
        super(p_i46452_1_, p_i46452_2_);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return super.canPlaceBlockAt(worldIn, pos) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }

    protected boolean func_185514_i(IBlockState state)
    {
        return state.getBlock() == Blocks.grass || state.getBlock() == Blocks.dirt || state.getBlock() == Blocks.farmland;
    }

    /**
     * Called when a neighboring block changes.
     */
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
        this.checkAndDropBlock(worldIn, pos, state);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 3);
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            IBlockState soil = worldIn.getBlockState(pos.down());
            return soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
        }
        return this.func_185514_i(worldIn.getBlockState(pos.down()));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return field_185515_b;
    }

    public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        if (this == Blocks.wheat)          return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.carrots)        return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.potatoes)       return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.melon_stem)     return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.pumpkin_stem)   return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.deadbush)       return net.minecraftforge.common.EnumPlantType.Desert;
        if (this == Blocks.waterlily)      return net.minecraftforge.common.EnumPlantType.Water;
        if (this == Blocks.red_mushroom)   return net.minecraftforge.common.EnumPlantType.Cave;
        if (this == Blocks.brown_mushroom) return net.minecraftforge.common.EnumPlantType.Cave;
        if (this == Blocks.nether_wart)    return net.minecraftforge.common.EnumPlantType.Nether;
        if (this == Blocks.sapling)        return net.minecraftforge.common.EnumPlantType.Plains;
        if (this == Blocks.tallgrass)      return net.minecraftforge.common.EnumPlantType.Plains;
        if (this == Blocks.double_plant)   return net.minecraftforge.common.EnumPlantType.Plains;
        if (this == Blocks.red_flower)     return net.minecraftforge.common.EnumPlantType.Plains;
        if (this == Blocks.yellow_flower)  return net.minecraftforge.common.EnumPlantType.Plains;
        return net.minecraftforge.common.EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}