package de.comeight.crystallogy.blocks.crystals;

import de.comeight.crystallogy.blocks.BaseBlockCutout;
import de.comeight.crystallogy.client.creativeTabs.particles.CrystalParticle;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.enums.EnumCrystalColor;
import de.comeight.crystallogy.util.enums.ToolClass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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

    protected static final AxisAlignedBB CRYSTAL_ORE_UP_AABB = new AxisAlignedBB(0.20D, 0.0D, 0.25D, 0.8D, 0.6D, 1.0D);
    protected static final AxisAlignedBB CRYSTAL_ORE_DOWN_AABB = new AxisAlignedBB(0.2D, 0.4D, 0.0D, 0.8D, 1.0D, 0.75D);
    protected static final AxisAlignedBB CRYSTAL_ORE_NORTH_AABB = new AxisAlignedBB(0.2D, 0.25D, 0.4D, 0.8D, 1.0D, 1.0D);
    protected static final AxisAlignedBB CRYSTAL_ORE_EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.2D, 0.6D, 1.0D, 0.8D);
    protected static final AxisAlignedBB CRYSTAL_ORE_SOUTH_AABB = new AxisAlignedBB(0.2D, 0.25D, 0.0D, 0.8D, 1.0D, 0.6D);
    protected static final AxisAlignedBB CRYSTAL_ORE_WEST_AABB = new AxisAlignedBB(0.4D, 0.25D, 0.2D, 1.0D, 1.0D, 0.8D);

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalOre(String id, EnumCrystalColor color) {
        super(Material.ROCK, id);

        this.color = color;

        setHarvestLevel(ToolClass.PICKAXE, 2);
        setResistance(15.0F);
        setHardness(5.0F);
        setLightLevel(0.3F);
        setLightOpacity(0);
        setSoundType(SoundType.GLASS);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState iblockstate = world.getBlockState(pos.offset(facing.getOpposite()));

        if (iblockstate.getBlock() instanceof CrystalOre)
        {
            EnumFacing enumfacing = iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return this.getDefaultState().withProperty(FACING, facing.getOpposite());
            }
        }

        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        java.util.List<ItemStack> list = super.getDrops(world, pos, state, fortune);

        Random rand = world instanceof World ? ((World)world).rand : new Random();

        int count = fortune + 8;
        for (int i = 0; i < count; ++i)
        {
            if(rand.nextInt(2) == 0) {
                list.add(new ItemStack(ItemHandler.CRYSTAL_SHARD, 1, color.getMeta()));
            }
        }

        count = fortune / 2 + 10;
        for (int i = 0; i < count; ++i)
        {
            if(rand.nextInt(2) == 0) {
                list.add(new ItemStack(ItemHandler.CRYSTAL_DUST, 1, color.getMeta()));
            }
        }

        if(rand.nextInt(3) == 0) {
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

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING)) {
            default:
            case UP:
                return CRYSTAL_ORE_UP_AABB;
            case DOWN:
                return CRYSTAL_ORE_DOWN_AABB;
            case NORTH:
                return CRYSTAL_ORE_NORTH_AABB;
            case EAST:
                return CRYSTAL_ORE_EAST_AABB;
            case SOUTH:
                return CRYSTAL_ORE_SOUTH_AABB;
            case WEST:
                return CRYSTAL_ORE_WEST_AABB;
        }
    }

    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withProperty(FACING, mirrorIn.mirror(state.getValue(FACING)));
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

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        if(rand.nextInt(7) == 0) {
            for (int i = 0; i < rand.nextInt(15); i++) {
                Vec3d pPos = new Vec3d(pos.getX() + 0.3 + rand.nextDouble() * 0.5, pos.getY() + 0.2 + rand.nextDouble() * 0.5, pos.getZ() + 0.3 + rand.nextDouble() * 0.5);
                CrystalParticle p = new CrystalParticle(worldIn, pPos);
                p.setRGBColor(color.getRGB_COLOR());
                Minecraft.getMinecraft().effectRenderer.addEffect(p);
            }
        }
    }

    //-----------------------------------------------Events:------------------------------------------------

}