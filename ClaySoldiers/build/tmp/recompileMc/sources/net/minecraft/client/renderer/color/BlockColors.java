package net.minecraft.client.renderer.color;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockColors
{
    // FORGE: Use RegistryDelegates as non-Vanilla block ids are not constant
    private final java.util.Map<net.minecraftforge.fml.common.registry.RegistryDelegate<Block>, IBlockColor> blockColorMap = com.google.common.collect.Maps.newHashMap();

    public static BlockColors init()
    {
        final BlockColors blockcolors = new BlockColors();
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = (BlockDoublePlant.EnumPlantType)state.getValue(BlockDoublePlant.VARIANT);
                return p_186720_2_ == null || pos == null || blockdoubleplant$enumplanttype != BlockDoublePlant.EnumPlantType.GRASS && blockdoubleplant$enumplanttype != BlockDoublePlant.EnumPlantType.FERN ? -1 : BiomeColorHelper.getGrassColorAtPos(p_186720_2_, pos);
            }
        }, new Block[] {Blocks.double_plant});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                if (p_186720_2_ != null && pos != null)
                {
                    TileEntity tileentity = p_186720_2_.getTileEntity(pos);

                    if (tileentity instanceof TileEntityFlowerPot)
                    {
                        Item item = ((TileEntityFlowerPot)tileentity).getFlowerPotItem();

                        if (item instanceof ItemBlock)
                        {
                            IBlockState iblockstate = Block.getBlockFromItem(item).getDefaultState();
                            return blockcolors.colorMultiplier(iblockstate, p_186720_2_, pos, tintIndex);
                        }
                    }

                    return -1;
                }
                else
                {
                    return -1;
                }
            }
        }, new Block[] {Blocks.flower_pot});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                return p_186720_2_ != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(p_186720_2_, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
            }
        }, new Block[] {Blocks.grass});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                BlockPlanks.EnumType blockplanks$enumtype = (BlockPlanks.EnumType)state.getValue(BlockOldLeaf.VARIANT);
                return blockplanks$enumtype == BlockPlanks.EnumType.SPRUCE ? ColorizerFoliage.getFoliageColorPine() : (blockplanks$enumtype == BlockPlanks.EnumType.BIRCH ? ColorizerFoliage.getFoliageColorBirch() : (p_186720_2_ != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(p_186720_2_, pos) : ColorizerFoliage.getFoliageColorBasic()));
            }
        }, new Block[] {Blocks.leaves});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                return p_186720_2_ != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(p_186720_2_, pos) : ColorizerFoliage.getFoliageColorBasic();
            }
        }, new Block[] {Blocks.leaves2});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                return p_186720_2_ != null && pos != null ? BiomeColorHelper.getWaterColorAtPos(p_186720_2_, pos) : -1;
            }
        }, new Block[] {Blocks.water, Blocks.flowing_water});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                return BlockRedstoneWire.colorMultiplier(((Integer)state.getValue(BlockRedstoneWire.POWER)).intValue());
            }
        }, new Block[] {Blocks.redstone_wire});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                return p_186720_2_ != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(p_186720_2_, pos) : -1;
            }
        }, new Block[] {Blocks.reeds});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                int i = ((Integer)state.getValue(BlockStem.AGE)).intValue();
                int j = i * 32;
                int k = 255 - i * 8;
                int l = i * 4;
                return j << 16 | k << 8 | l;
            }
        }, new Block[] {Blocks.melon_stem, Blocks.pumpkin_stem});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                return p_186720_2_ != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(p_186720_2_, pos) : (state.getValue(BlockTallGrass.TYPE) == BlockTallGrass.EnumType.DEAD_BUSH ? 16777215 : ColorizerGrass.getGrassColor(0.5D, 1.0D));
            }
        }, new Block[] {Blocks.tallgrass});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                return p_186720_2_ != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(p_186720_2_, pos) : ColorizerFoliage.getFoliageColorBasic();
            }
        }, new Block[] {Blocks.vine});
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex)
            {
                return p_186720_2_ != null && pos != null ? 2129968 : 7455580;
            }
        }, new Block[] {Blocks.waterlily});
        return blockcolors;
    }

    public int colorMultiplier(IBlockState p_186724_1_, IBlockAccess p_186724_2_, BlockPos p_186724_3_, int p_186724_4_)
    {
        IBlockColor iblockcolor = (IBlockColor)this.blockColorMap.get(p_186724_1_.getBlock().delegate);
        return iblockcolor == null ? -1 : iblockcolor.colorMultiplier(p_186724_1_, p_186724_2_, p_186724_3_, p_186724_4_);
    }

    public void registerBlockColorHandler(IBlockColor p_186722_1_, Block... p_186722_2_)
    {
        int i = 0;

        for (int j = p_186722_2_.length; i < j; ++i)
        {
            if (p_186722_2_[i] == null) throw new IllegalArgumentException("Block registered to block color handler cannot be null!");
            if (p_186722_2_[i].getRegistryName() == null) throw new IllegalArgumentException("Block must be registered before assigning color handler.");
            this.blockColorMap.put(p_186722_2_[i].delegate, p_186722_1_);
        }
    }
}