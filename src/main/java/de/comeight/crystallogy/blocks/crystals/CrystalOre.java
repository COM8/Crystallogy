package de.comeight.crystallogy.blocks.crystals;

import de.comeight.crystallogy.blocks.BaseBlockCutout;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.enums.EnumCrystalColor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public abstract class CrystalOre extends BaseBlockCutout {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public final EnumCrystalColor color;

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalOre(String id, EnumCrystalColor color) {
        super(Material.GLASS, id);

        this.color = color;

        setHarvestLevel("pickax", 2);
        setLightLevel(0.3F);
        setLightOpacity(0);
        setSoundType(SoundType.GLASS);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN));
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing;
        switch (meta & 7)
        {
            case 0:
                enumfacing = EnumFacing.DOWN;
                break;
            case 1:
                enumfacing = EnumFacing.EAST;
                break;
            case 2:
                enumfacing = EnumFacing.WEST;
                break;
            case 3:
                enumfacing = EnumFacing.SOUTH;
                break;
            case 4:
                enumfacing = EnumFacing.NORTH;
                break;
            case 5:
            default:
                enumfacing = EnumFacing.UP;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getStateFromMeta(meta).withProperty(FACING, facing);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        java.util.List<ItemStack> list = super.getDrops(world, pos, state, fortune);

        Random rand = world instanceof World ? ((World)world).rand : new Random();

        int count = fortune + 4;
        for (int i = 0; i < count; ++i)
        {
            if(rand.nextInt(2) == 0) {
                list.add(new ItemStack(ItemHandler.CRYSTAL_SHARD, 1, color.getMeta()));
            }
        }

        count = fortune / 2 + 1;
        for (int i = 0; i < count; ++i)
        {
            if(rand.nextInt(2) == 0) {
                list.add(new ItemStack(ItemHandler.CRYSTAL_DUST, 1, color.getMeta()));
            }
        }

        if(rand.nextInt(5) == 0) {
            list.add(new ItemStack(Blocks.COBBLESTONE));
        }

        return list;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        return MathHelper.getInt(rand, 2, 5);
    }

    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    protected BlockStateContainer createBlockState() {
        return  new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
        if(entityIn instanceof EntityLivingBase){
            entityIn.attackEntityFrom(DamageSource.GENERIC, 0.5F);
        }
    }

    //-----------------------------------------------Events:------------------------------------------------

}