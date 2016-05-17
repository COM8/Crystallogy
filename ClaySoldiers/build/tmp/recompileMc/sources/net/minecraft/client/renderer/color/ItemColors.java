package net.minecraft.client.renderer.color;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFireworkCharge;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemColors
{
    // FORGE: Use RegistryDelegates as non-Vanilla item ids are not constant
    private final java.util.Map<net.minecraftforge.fml.common.registry.RegistryDelegate<Item>, IItemColor> itemColorMap = com.google.common.collect.Maps.newHashMap();

    public static ItemColors init(final BlockColors p_186729_0_)
    {
        ItemColors itemcolors = new ItemColors();
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : ((ItemArmor)stack.getItem()).getColor(stack);
            }
        }, new Item[] {Items.leather_helmet, Items.leather_chestplate, Items.leather_leggings, Items.leather_boots});
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : ItemBanner.getBaseColor(stack).getMapColor().colorValue;
            }
        }, new Item[] {Items.banner, Items.shield});
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = BlockDoublePlant.EnumPlantType.byMetadata(stack.getMetadata());
                return blockdoubleplant$enumplanttype != BlockDoublePlant.EnumPlantType.GRASS && blockdoubleplant$enumplanttype != BlockDoublePlant.EnumPlantType.FERN ? -1 : ColorizerGrass.getGrassColor(0.5D, 1.0D);
            }
        }, new Block[] {Blocks.double_plant});
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                if (tintIndex != 1)
                {
                    return -1;
                }
                else
                {
                    NBTBase nbtbase = ItemFireworkCharge.getExplosionTag(stack, "Colors");

                    if (!(nbtbase instanceof NBTTagIntArray))
                    {
                        return 9079434;
                    }
                    else
                    {
                        int[] aint = ((NBTTagIntArray)nbtbase).getIntArray();

                        if (aint.length == 1)
                        {
                            return aint[0];
                        }
                        else
                        {
                            int i = 0;
                            int j = 0;
                            int k = 0;
                            int l = 0;

                            for (int i1 = aint.length; l < i1; ++l)
                            {
                                int j1 = aint[l];
                                i += (j1 & 16711680) >> 16;
                                j += (j1 & 65280) >> 8;
                                k += (j1 & 255) >> 0;
                            }

                            i = i / aint.length;
                            j = j / aint.length;
                            k = k / aint.length;
                            return i << 16 | j << 8 | k;
                        }
                    }
                }
            }
        }, new Item[] {Items.firework_charge});
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : PotionUtils.getPotionColorFromEffectList(PotionUtils.getEffectsFromStack(stack));
            }
        }, new Item[] {Items.potionitem, Items.splash_potion, Items.lingering_potion});
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                EntityList.EntityEggInfo entitylist$entityegginfo = (EntityList.EntityEggInfo)EntityList.entityEggs.get(ItemMonsterPlacer.getEntityIdFromItem(stack));
                return entitylist$entityegginfo == null ? -1 : (tintIndex == 0 ? entitylist$entityegginfo.primaryColor : entitylist$entityegginfo.secondaryColor);
            }
        }, new Item[] {Items.spawn_egg});
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                return p_186729_0_.colorMultiplier(iblockstate, (IBlockAccess)null, (BlockPos)null, tintIndex);
            }
        }, new Block[] {Blocks.grass, Blocks.tallgrass, Blocks.vine, Blocks.leaves, Blocks.leaves2, Blocks.waterlily});
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                return tintIndex == 0 ? PotionUtils.getPotionColorFromEffectList(PotionUtils.getEffectsFromStack(stack)) : -1;
            }
        }, new Item[] {Items.tipped_arrow});
        return itemcolors;
    }

    public int getColorFromItemstack(ItemStack p_186728_1_, int p_186728_2_)
    {
        IItemColor iitemcolor = (IItemColor)this.itemColorMap.get(p_186728_1_.getItem().delegate);
        return iitemcolor == null ? -1 : iitemcolor.getColorFromItemstack(p_186728_1_, p_186728_2_);
    }

    public void registerItemColorHandler(IItemColor p_186731_1_, Block... p_186731_2_)
    {
        int i = 0;

        for (int j = p_186731_2_.length; i < j; ++i)
        {
            if (p_186731_2_[i] == null) throw new IllegalArgumentException("Block registered to item color handler cannot be null!");
            if (p_186731_2_[i].getRegistryName() == null) throw new IllegalArgumentException("Block must be registered before assigning color handler.");
            this.itemColorMap.put(Item.getItemFromBlock(p_186731_2_[i]).delegate, p_186731_1_);
        }
    }

    public void registerItemColorHandler(IItemColor p_186730_1_, Item... p_186730_2_)
    {
        int i = 0;

        for (int j = p_186730_2_.length; i < j; ++i)
        {
            if (p_186730_2_[i] == null) throw new IllegalArgumentException("Item registered to item color handler cannot be null!");
            if (p_186730_2_[i].getRegistryName() == null) throw new IllegalArgumentException("Item must be registered before assigning color handler.");
            this.itemColorMap.put(p_186730_2_[i].delegate, p_186730_1_);
        }
    }
}