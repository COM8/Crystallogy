package net.minecraft.stats;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class StatList
{
    protected static final Map<String, StatBase> idToStatMap = Maps.<String, StatBase>newHashMap();
    public static final List<StatBase> allStats = Lists.<StatBase>newArrayList();
    public static final List<StatBase> field_188094_c = Lists.<StatBase>newArrayList();
    public static final List<StatCrafting> field_188095_d = Lists.<StatCrafting>newArrayList();
    public static final List<StatCrafting> field_188096_e = Lists.<StatCrafting>newArrayList();
    /** number of times you've left a game */
    public static final StatBase leaveGame = (new StatBasic("stat.leaveGame", new TextComponentTranslation("stat.leaveGame", new Object[0]))).initIndependentStat().registerStat();
    public static final StatBase playOneMinute = (new StatBasic("stat.playOneMinute", new TextComponentTranslation("stat.playOneMinute", new Object[0]), StatBase.timeStatType)).initIndependentStat().registerStat();
    public static final StatBase timeSinceDeath = (new StatBasic("stat.timeSinceDeath", new TextComponentTranslation("stat.timeSinceDeath", new Object[0]), StatBase.timeStatType)).initIndependentStat().registerStat();
    public static final StatBase sneakTime = (new StatBasic("stat.sneakTime", new TextComponentTranslation("stat.sneakTime", new Object[0]), StatBase.timeStatType)).initIndependentStat().registerStat();
    public static final StatBase walkOneCm = (new StatBasic("stat.walkOneCm", new TextComponentTranslation("stat.walkOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase crouchOneCm = (new StatBasic("stat.crouchOneCm", new TextComponentTranslation("stat.crouchOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase sprintOneCm = (new StatBasic("stat.sprintOneCm", new TextComponentTranslation("stat.sprintOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    /** distance you have swam */
    public static final StatBase swimOneCm = (new StatBasic("stat.swimOneCm", new TextComponentTranslation("stat.swimOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    /** the distance you have fallen */
    public static final StatBase fallOneCm = (new StatBasic("stat.fallOneCm", new TextComponentTranslation("stat.fallOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase climbOneCm = (new StatBasic("stat.climbOneCm", new TextComponentTranslation("stat.climbOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase flyOneCm = (new StatBasic("stat.flyOneCm", new TextComponentTranslation("stat.flyOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase diveOneCm = (new StatBasic("stat.diveOneCm", new TextComponentTranslation("stat.diveOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase minecartOneCm = (new StatBasic("stat.minecartOneCm", new TextComponentTranslation("stat.minecartOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase boatOneCm = (new StatBasic("stat.boatOneCm", new TextComponentTranslation("stat.boatOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase pigOneCm = (new StatBasic("stat.pigOneCm", new TextComponentTranslation("stat.pigOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase horseOneCm = (new StatBasic("stat.horseOneCm", new TextComponentTranslation("stat.horseOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    public static final StatBase aviateOneCm = (new StatBasic("stat.aviateOneCm", new TextComponentTranslation("stat.aviateOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
    /** the times you've jumped */
    public static final StatBase jump = (new StatBasic("stat.jump", new TextComponentTranslation("stat.jump", new Object[0]))).initIndependentStat().registerStat();
    /** the distance you've dropped (or times you've fallen?) */
    public static final StatBase drop = (new StatBasic("stat.drop", new TextComponentTranslation("stat.drop", new Object[0]))).initIndependentStat().registerStat();
    public static final StatBase damageDealt = (new StatBasic("stat.damageDealt", new TextComponentTranslation("stat.damageDealt", new Object[0]), StatBase.field_111202_k)).registerStat();
    public static final StatBase damageTaken = (new StatBasic("stat.damageTaken", new TextComponentTranslation("stat.damageTaken", new Object[0]), StatBase.field_111202_k)).registerStat();
    public static final StatBase deaths = (new StatBasic("stat.deaths", new TextComponentTranslation("stat.deaths", new Object[0]))).registerStat();
    public static final StatBase mobKills = (new StatBasic("stat.mobKills", new TextComponentTranslation("stat.mobKills", new Object[0]))).registerStat();
    /** the number of animals you have bred */
    public static final StatBase animalsBred = (new StatBasic("stat.animalsBred", new TextComponentTranslation("stat.animalsBred", new Object[0]))).registerStat();
    /** counts the number of times you've killed a player */
    public static final StatBase playerKills = (new StatBasic("stat.playerKills", new TextComponentTranslation("stat.playerKills", new Object[0]))).registerStat();
    public static final StatBase fishCaught = (new StatBasic("stat.fishCaught", new TextComponentTranslation("stat.fishCaught", new Object[0]))).registerStat();
    public static final StatBase junkFished = (new StatBasic("stat.junkFished", new TextComponentTranslation("stat.junkFished", new Object[0]))).registerStat();
    public static final StatBase treasureFished = (new StatBasic("stat.treasureFished", new TextComponentTranslation("stat.treasureFished", new Object[0]))).registerStat();
    public static final StatBase talkedToVillager = (new StatBasic("stat.talkedToVillager", new TextComponentTranslation("stat.talkedToVillager", new Object[0]))).registerStat();
    public static final StatBase tradedWithVillager = (new StatBasic("stat.tradedWithVillager", new TextComponentTranslation("stat.tradedWithVillager", new Object[0]))).registerStat();
    public static final StatBase cakeSlicesEaten = (new StatBasic("stat.cakeSlicesEaten", new TextComponentTranslation("stat.cakeSlicesEaten", new Object[0]))).registerStat();
    public static final StatBase cauldronFilled = (new StatBasic("stat.cauldronFilled", new TextComponentTranslation("stat.cauldronFilled", new Object[0]))).registerStat();
    public static final StatBase cauldronUsed = (new StatBasic("stat.cauldronUsed", new TextComponentTranslation("stat.cauldronUsed", new Object[0]))).registerStat();
    public static final StatBase armorCleaned = (new StatBasic("stat.armorCleaned", new TextComponentTranslation("stat.armorCleaned", new Object[0]))).registerStat();
    public static final StatBase bannerCleaned = (new StatBasic("stat.bannerCleaned", new TextComponentTranslation("stat.bannerCleaned", new Object[0]))).registerStat();
    public static final StatBase brewingstandInteraction = (new StatBasic("stat.brewingstandInteraction", new TextComponentTranslation("stat.brewingstandInteraction", new Object[0]))).registerStat();
    public static final StatBase beaconInteraction = (new StatBasic("stat.beaconInteraction", new TextComponentTranslation("stat.beaconInteraction", new Object[0]))).registerStat();
    public static final StatBase dropperInspected = (new StatBasic("stat.dropperInspected", new TextComponentTranslation("stat.dropperInspected", new Object[0]))).registerStat();
    public static final StatBase hopperInspected = (new StatBasic("stat.hopperInspected", new TextComponentTranslation("stat.hopperInspected", new Object[0]))).registerStat();
    public static final StatBase dispenserInspected = (new StatBasic("stat.dispenserInspected", new TextComponentTranslation("stat.dispenserInspected", new Object[0]))).registerStat();
    public static final StatBase noteblockPlayed = (new StatBasic("stat.noteblockPlayed", new TextComponentTranslation("stat.noteblockPlayed", new Object[0]))).registerStat();
    public static final StatBase noteblockTuned = (new StatBasic("stat.noteblockTuned", new TextComponentTranslation("stat.noteblockTuned", new Object[0]))).registerStat();
    public static final StatBase flowerPotted = (new StatBasic("stat.flowerPotted", new TextComponentTranslation("stat.flowerPotted", new Object[0]))).registerStat();
    public static final StatBase trappedChestTriggered = (new StatBasic("stat.trappedChestTriggered", new TextComponentTranslation("stat.trappedChestTriggered", new Object[0]))).registerStat();
    public static final StatBase enderchestOpened = (new StatBasic("stat.enderchestOpened", new TextComponentTranslation("stat.enderchestOpened", new Object[0]))).registerStat();
    public static final StatBase itemEnchanted = (new StatBasic("stat.itemEnchanted", new TextComponentTranslation("stat.itemEnchanted", new Object[0]))).registerStat();
    public static final StatBase recordPlayed = (new StatBasic("stat.recordPlayed", new TextComponentTranslation("stat.recordPlayed", new Object[0]))).registerStat();
    public static final StatBase furnaceInteraction = (new StatBasic("stat.furnaceInteraction", new TextComponentTranslation("stat.furnaceInteraction", new Object[0]))).registerStat();
    public static final StatBase craftingTableInteraction = (new StatBasic("stat.craftingTableInteraction", new TextComponentTranslation("stat.workbenchInteraction", new Object[0]))).registerStat();
    public static final StatBase chestOpened = (new StatBasic("stat.chestOpened", new TextComponentTranslation("stat.chestOpened", new Object[0]))).registerStat();
    public static final StatBase sleepInBed = (new StatBasic("stat.sleepInBed", new TextComponentTranslation("stat.sleepInBed", new Object[0]))).registerStat();
    private static final StatBase[] field_188065_ae = new StatBase[4096];
    private static final StatBase[] field_188066_af = new StatBase[32000];
    /** Tracks the number of times a given block or item has been used. */
    private static final StatBase[] objectUseStats = new StatBase[32000];
    /** Tracks the number of times a given block or item has been broken. */
    private static final StatBase[] objectBreakStats = new StatBase[32000];
    private static final StatBase[] field_188067_ai = new StatBase[32000];
    private static final StatBase[] field_188068_aj = new StatBase[32000];

    public static StatBase func_188055_a(Block p_188055_0_)
    {
        return field_188065_ae[Block.getIdFromBlock(p_188055_0_)];
    }

    public static StatBase func_188060_a(Item p_188060_0_)
    {
        return field_188066_af[Item.getIdFromItem(p_188060_0_)];
    }

    public static StatBase func_188057_b(Item p_188057_0_)
    {
        return objectUseStats[Item.getIdFromItem(p_188057_0_)];
    }

    public static StatBase func_188059_c(Item p_188059_0_)
    {
        return objectBreakStats[Item.getIdFromItem(p_188059_0_)];
    }

    public static StatBase func_188056_d(Item p_188056_0_)
    {
        return field_188067_ai[Item.getIdFromItem(p_188056_0_)];
    }

    public static StatBase func_188058_e(Item p_188058_0_)
    {
        return field_188068_aj[Item.getIdFromItem(p_188058_0_)];
    }

    public static void init()
    {
        initMiningStats();
        initStats();
        initItemDepleteStats();
        initCraftableStats();
        func_188054_f();
        AchievementList.init();
        EntityList.func_151514_a();
    }

    /**
     * Initializes statistics related to craftable items. Is only called after both block and item stats have been
     * initialized.
     */
    private static void initCraftableStats()
    {
        Set<Item> set = Sets.<Item>newHashSet();

        for (IRecipe irecipe : CraftingManager.getInstance().getRecipeList())
        {
            if (irecipe.getRecipeOutput() != null)
            {
                set.add(irecipe.getRecipeOutput().getItem());
            }
        }

        for (ItemStack itemstack : FurnaceRecipes.instance().getSmeltingList().values())
        {
            set.add(itemstack.getItem());
        }

        for (Item item : set)
        {
            if (item != null)
            {
                int i = Item.getIdFromItem(item);
                String s = func_180204_a(item);

                if (s != null)
                {
                    field_188066_af[i] = (new StatCrafting("stat.craftItem.", s, new TextComponentTranslation("stat.craftItem", new Object[] {(new ItemStack(item)).getChatComponent()}), item)).registerStat();
                }
            }
        }

        replaceAllSimilarBlocks(field_188066_af, true);
    }

    private static void initMiningStats()
    {
        for (Block block : Block.blockRegistry)
        {
            Item item = Item.getItemFromBlock(block);

            if (item != null)
            {
                int i = Block.getIdFromBlock(block);
                String s = func_180204_a(item);

                if (s != null && block.getEnableStats())
                {
                    field_188065_ae[i] = (new StatCrafting("stat.mineBlock.", s, new TextComponentTranslation("stat.mineBlock", new Object[] {(new ItemStack(block)).getChatComponent()}), item)).registerStat();
                    field_188096_e.add((StatCrafting)field_188065_ae[i]);
                }
            }
        }

        replaceAllSimilarBlocks(field_188065_ae, false);
    }

    private static void initStats()
    {
        for (Item item : Item.itemRegistry)
        {
            if (item != null)
            {
                int i = Item.getIdFromItem(item);
                String s = func_180204_a(item);

                if (s != null)
                {
                    objectUseStats[i] = (new StatCrafting("stat.useItem.", s, new TextComponentTranslation("stat.useItem", new Object[] {(new ItemStack(item)).getChatComponent()}), item)).registerStat();

                    if (!(item instanceof ItemBlock))
                    {
                        field_188095_d.add((StatCrafting)objectUseStats[i]);
                    }
                }
            }
        }

        replaceAllSimilarBlocks(objectUseStats, true);
    }

    private static void initItemDepleteStats()
    {
        for (Item item : Item.itemRegistry)
        {
            if (item != null)
            {
                int i = Item.getIdFromItem(item);
                String s = func_180204_a(item);

                if (s != null && item.isDamageable())
                {
                    objectBreakStats[i] = (new StatCrafting("stat.breakItem.", s, new TextComponentTranslation("stat.breakItem", new Object[] {(new ItemStack(item)).getChatComponent()}), item)).registerStat();
                }
            }
        }

        replaceAllSimilarBlocks(objectBreakStats, true);
    }

    private static void func_188054_f()
    {
        for (Item item : Item.itemRegistry)
        {
            if (item != null)
            {
                int i = Item.getIdFromItem(item);
                String s = func_180204_a(item);

                if (s != null)
                {
                    field_188067_ai[i] = (new StatCrafting("stat.pickup.", s, new TextComponentTranslation("stat.pickup", new Object[] {(new ItemStack(item)).getChatComponent()}), item)).registerStat();
                    field_188068_aj[i] = (new StatCrafting("stat.drop.", s, new TextComponentTranslation("stat.drop", new Object[] {(new ItemStack(item)).getChatComponent()}), item)).registerStat();
                }
            }
        }

        replaceAllSimilarBlocks(objectBreakStats, true);
    }

    private static String func_180204_a(Item p_180204_0_)
    {
        ResourceLocation resourcelocation = (ResourceLocation)Item.itemRegistry.getNameForObject(p_180204_0_);
        return resourcelocation != null ? resourcelocation.toString().replace(':', '.') : null;
    }

    private static void replaceAllSimilarBlocks(StatBase[] p_75924_0_, boolean useItemIds)
    {
        mergeStatBases(p_75924_0_, Blocks.water, Blocks.flowing_water, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.lava, Blocks.flowing_lava, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.lit_pumpkin, Blocks.pumpkin, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.lit_furnace, Blocks.furnace, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.lit_redstone_ore, Blocks.redstone_ore, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.powered_repeater, Blocks.unpowered_repeater, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.powered_comparator, Blocks.unpowered_comparator, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.redstone_torch, Blocks.unlit_redstone_torch, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.lit_redstone_lamp, Blocks.redstone_lamp, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.double_stone_slab, Blocks.stone_slab, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.double_wooden_slab, Blocks.wooden_slab, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.double_stone_slab2, Blocks.stone_slab2, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.grass, Blocks.dirt, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.farmland, Blocks.dirt, useItemIds);
    }

    private static void mergeStatBases(StatBase[] statBaseIn, Block p_151180_1_, Block p_151180_2_, boolean useItemIds)
    {
        int i;
        int j;
        if (useItemIds) {
            i = Item.getIdFromItem(Item.getItemFromBlock(p_151180_1_));
            j = Item.getIdFromItem(Item.getItemFromBlock(p_151180_2_));
        } else {
            i = Block.getIdFromBlock(p_151180_1_);
            j = Block.getIdFromBlock(p_151180_2_);
        }

        if (statBaseIn[i] != null && statBaseIn[j] == null)
        {
            statBaseIn[j] = statBaseIn[i];
        }
        else
        {
            allStats.remove(statBaseIn[i]);
            field_188096_e.remove(statBaseIn[i]);
            field_188094_c.remove(statBaseIn[i]);
            statBaseIn[i] = statBaseIn[j];
        }
    }

    public static StatBase getStatKillEntity(EntityList.EntityEggInfo eggInfo)
    {
        return eggInfo.spawnedID == null ? null : (new StatBase("stat.killEntity." + eggInfo.spawnedID, new TextComponentTranslation("stat.entityKill", new Object[] {new TextComponentTranslation("entity." + eggInfo.spawnedID + ".name", new Object[0])}))).registerStat();
    }

    public static StatBase getStatEntityKilledBy(EntityList.EntityEggInfo eggInfo)
    {
        return eggInfo.spawnedID == null ? null : (new StatBase("stat.entityKilledBy." + eggInfo.spawnedID, new TextComponentTranslation("stat.entityKilledBy", new Object[] {new TextComponentTranslation("entity." + eggInfo.spawnedID + ".name", new Object[0])}))).registerStat();
    }

    public static StatBase getOneShotStat(String p_151177_0_)
    {
        return (StatBase)idToStatMap.get(p_151177_0_);
    }
}