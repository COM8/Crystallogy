package net.minecraft.potion;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class PotionHelper
{
    private static final List<PotionHelper.MixPredicate<PotionType>> POTION_TYPE_CONVERSIONS = Lists.<PotionHelper.MixPredicate<PotionType>>newArrayList();
    private static final List<PotionHelper.MixPredicate<Item>> POTION_ITEM_CONVERSIONS = Lists.<PotionHelper.MixPredicate<Item>>newArrayList();
    private static final List<PotionHelper.ItemPredicateInstance> POTION_ITEMS = Lists.<PotionHelper.ItemPredicateInstance>newArrayList();
    private static final Predicate<ItemStack> IS_POTION_ITEM = new Predicate<ItemStack>()
    {
        public boolean apply(ItemStack p_apply_1_)
        {
            for (PotionHelper.ItemPredicateInstance potionhelper$itempredicateinstance : PotionHelper.POTION_ITEMS)
            {
                if (potionhelper$itempredicateinstance.apply(p_apply_1_))
                {
                    return true;
                }
            }

            return false;
        }
    };

    public static boolean isReagent(ItemStack p_185205_0_)
    {
        return isItemConversionReagent(p_185205_0_) || isTypeConversionReagent(p_185205_0_);
    }

    protected static boolean isItemConversionReagent(ItemStack p_185203_0_)
    {
        int i = 0;

        for (int j = POTION_ITEM_CONVERSIONS.size(); i < j; ++i)
        {
            if (((PotionHelper.MixPredicate)POTION_ITEM_CONVERSIONS.get(i)).reagent.apply(p_185203_0_))
            {
                return true;
            }
        }

        return false;
    }

    protected static boolean isTypeConversionReagent(ItemStack p_185211_0_)
    {
        int i = 0;

        for (int j = POTION_TYPE_CONVERSIONS.size(); i < j; ++i)
        {
            if (((PotionHelper.MixPredicate)POTION_TYPE_CONVERSIONS.get(i)).reagent.apply(p_185211_0_))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean hasConversions(ItemStack input, ItemStack reagent)
    {
        return !IS_POTION_ITEM.apply(input) ? false : hasItemConversions(input, reagent) || hasTypeConversions(input, reagent);
    }

    protected static boolean hasItemConversions(ItemStack p_185206_0_, ItemStack p_185206_1_)
    {
        Item item = p_185206_0_.getItem();
        int i = 0;

        for (int j = POTION_ITEM_CONVERSIONS.size(); i < j; ++i)
        {
            PotionHelper.MixPredicate<Item> mixpredicate = (PotionHelper.MixPredicate)POTION_ITEM_CONVERSIONS.get(i);

            if (mixpredicate.input == item && mixpredicate.reagent.apply(p_185206_1_))
            {
                return true;
            }
        }

        return false;
    }

    protected static boolean hasTypeConversions(ItemStack p_185209_0_, ItemStack p_185209_1_)
    {
        PotionType potiontype = PotionUtils.getPotionFromItem(p_185209_0_);
        int i = 0;

        for (int j = POTION_TYPE_CONVERSIONS.size(); i < j; ++i)
        {
            PotionHelper.MixPredicate<PotionType> mixpredicate = (PotionHelper.MixPredicate)POTION_TYPE_CONVERSIONS.get(i);

            if (mixpredicate.input == potiontype && mixpredicate.reagent.apply(p_185209_1_))
            {
                return true;
            }
        }

        return false;
    }

    public static ItemStack doReaction(ItemStack reagent, ItemStack potionIn)
    {
        if (potionIn != null)
        {
            PotionType potiontype = PotionUtils.getPotionFromItem(potionIn);
            Item item = potionIn.getItem();
            int i = 0;

            for (int j = POTION_ITEM_CONVERSIONS.size(); i < j; ++i)
            {
                PotionHelper.MixPredicate<Item> mixpredicate = (PotionHelper.MixPredicate)POTION_ITEM_CONVERSIONS.get(i);

                if (mixpredicate.input == item && mixpredicate.reagent.apply(reagent))
                {
                    return PotionUtils.addPotionToItemStack(new ItemStack((Item)mixpredicate.output), potiontype);
                }
            }

            i = 0;

            for (int k = POTION_TYPE_CONVERSIONS.size(); i < k; ++i)
            {
                PotionHelper.MixPredicate<PotionType> mixpredicate1 = (PotionHelper.MixPredicate)POTION_TYPE_CONVERSIONS.get(i);

                if (mixpredicate1.input == potiontype && mixpredicate1.reagent.apply(reagent))
                {
                    return PotionUtils.addPotionToItemStack(new ItemStack(item), (PotionType)mixpredicate1.output);
                }
            }
        }

        return potionIn;
    }

    public static void init()
    {
        Predicate<ItemStack> predicate = new PotionHelper.ItemPredicateInstance(Items.nether_wart);
        Predicate<ItemStack> predicate1 = new PotionHelper.ItemPredicateInstance(Items.golden_carrot);
        Predicate<ItemStack> predicate2 = new PotionHelper.ItemPredicateInstance(Items.redstone);
        Predicate<ItemStack> predicate3 = new PotionHelper.ItemPredicateInstance(Items.fermented_spider_eye);
        Predicate<ItemStack> predicate4 = new PotionHelper.ItemPredicateInstance(Items.rabbit_foot);
        Predicate<ItemStack> predicate5 = new PotionHelper.ItemPredicateInstance(Items.glowstone_dust);
        Predicate<ItemStack> predicate6 = new PotionHelper.ItemPredicateInstance(Items.magma_cream);
        Predicate<ItemStack> predicate7 = new PotionHelper.ItemPredicateInstance(Items.sugar);
        Predicate<ItemStack> predicate8 = new PotionHelper.ItemPredicateInstance(Items.fish, ItemFishFood.FishType.PUFFERFISH.getMetadata());
        Predicate<ItemStack> predicate9 = new PotionHelper.ItemPredicateInstance(Items.speckled_melon);
        Predicate<ItemStack> predicate10 = new PotionHelper.ItemPredicateInstance(Items.spider_eye);
        Predicate<ItemStack> predicate11 = new PotionHelper.ItemPredicateInstance(Items.ghast_tear);
        Predicate<ItemStack> predicate12 = new PotionHelper.ItemPredicateInstance(Items.blaze_powder);
        registerPotionItem(new PotionHelper.ItemPredicateInstance(Items.potionitem));
        registerPotionItem(new PotionHelper.ItemPredicateInstance(Items.splash_potion));
        registerPotionItem(new PotionHelper.ItemPredicateInstance(Items.lingering_potion));
        registerPotionItemConversion(Items.potionitem, new PotionHelper.ItemPredicateInstance(Items.gunpowder), Items.splash_potion);
        registerPotionItemConversion(Items.splash_potion, new PotionHelper.ItemPredicateInstance(Items.dragon_breath), Items.lingering_potion);
        registerPotionTypeConversion(PotionTypes.water, predicate9, PotionTypes.mundane);
        registerPotionTypeConversion(PotionTypes.water, predicate11, PotionTypes.mundane);
        registerPotionTypeConversion(PotionTypes.water, predicate4, PotionTypes.mundane);
        registerPotionTypeConversion(PotionTypes.water, predicate12, PotionTypes.mundane);
        registerPotionTypeConversion(PotionTypes.water, predicate10, PotionTypes.mundane);
        registerPotionTypeConversion(PotionTypes.water, predicate7, PotionTypes.mundane);
        registerPotionTypeConversion(PotionTypes.water, predicate6, PotionTypes.mundane);
        registerPotionTypeConversion(PotionTypes.water, predicate5, PotionTypes.thick);
        registerPotionTypeConversion(PotionTypes.water, predicate2, PotionTypes.mundane);
        registerPotionTypeConversion(PotionTypes.water, predicate, PotionTypes.awkward);
        registerPotionTypeConversion(PotionTypes.awkward, predicate1, PotionTypes.night_vision);
        registerPotionTypeConversion(PotionTypes.night_vision, predicate2, PotionTypes.long_night_vision);
        registerPotionTypeConversion(PotionTypes.night_vision, predicate3, PotionTypes.invisibility);
        registerPotionTypeConversion(PotionTypes.long_night_vision, predicate3, PotionTypes.long_invisibility);
        registerPotionTypeConversion(PotionTypes.invisibility, predicate2, PotionTypes.long_invisibility);
        registerPotionTypeConversion(PotionTypes.awkward, predicate6, PotionTypes.fire_resistance);
        registerPotionTypeConversion(PotionTypes.fire_resistance, predicate2, PotionTypes.long_fire_resistance);
        registerPotionTypeConversion(PotionTypes.awkward, predicate4, PotionTypes.leaping);
        registerPotionTypeConversion(PotionTypes.leaping, predicate2, PotionTypes.long_leaping);
        registerPotionTypeConversion(PotionTypes.leaping, predicate5, PotionTypes.strong_leaping);
        registerPotionTypeConversion(PotionTypes.leaping, predicate3, PotionTypes.slowness);
        registerPotionTypeConversion(PotionTypes.long_leaping, predicate3, PotionTypes.long_slowness);
        registerPotionTypeConversion(PotionTypes.slowness, predicate2, PotionTypes.long_slowness);
        registerPotionTypeConversion(PotionTypes.swiftness, predicate3, PotionTypes.slowness);
        registerPotionTypeConversion(PotionTypes.long_swiftness, predicate3, PotionTypes.long_slowness);
        registerPotionTypeConversion(PotionTypes.awkward, predicate7, PotionTypes.swiftness);
        registerPotionTypeConversion(PotionTypes.swiftness, predicate2, PotionTypes.long_swiftness);
        registerPotionTypeConversion(PotionTypes.swiftness, predicate5, PotionTypes.strong_swiftness);
        registerPotionTypeConversion(PotionTypes.awkward, predicate8, PotionTypes.water_breathing);
        registerPotionTypeConversion(PotionTypes.water_breathing, predicate2, PotionTypes.long_water_breathing);
        registerPotionTypeConversion(PotionTypes.awkward, predicate9, PotionTypes.healing);
        registerPotionTypeConversion(PotionTypes.healing, predicate5, PotionTypes.strong_healing);
        registerPotionTypeConversion(PotionTypes.healing, predicate3, PotionTypes.harming);
        registerPotionTypeConversion(PotionTypes.strong_healing, predicate3, PotionTypes.strong_harming);
        registerPotionTypeConversion(PotionTypes.harming, predicate5, PotionTypes.strong_harming);
        registerPotionTypeConversion(PotionTypes.poison, predicate3, PotionTypes.harming);
        registerPotionTypeConversion(PotionTypes.long_poison, predicate3, PotionTypes.harming);
        registerPotionTypeConversion(PotionTypes.strong_poison, predicate3, PotionTypes.strong_harming);
        registerPotionTypeConversion(PotionTypes.awkward, predicate10, PotionTypes.poison);
        registerPotionTypeConversion(PotionTypes.poison, predicate2, PotionTypes.long_poison);
        registerPotionTypeConversion(PotionTypes.poison, predicate5, PotionTypes.strong_poison);
        registerPotionTypeConversion(PotionTypes.awkward, predicate11, PotionTypes.regeneration);
        registerPotionTypeConversion(PotionTypes.regeneration, predicate2, PotionTypes.long_regeneration);
        registerPotionTypeConversion(PotionTypes.regeneration, predicate5, PotionTypes.strong_regeneration);
        registerPotionTypeConversion(PotionTypes.awkward, predicate12, PotionTypes.strength);
        registerPotionTypeConversion(PotionTypes.strength, predicate2, PotionTypes.long_strength);
        registerPotionTypeConversion(PotionTypes.strength, predicate5, PotionTypes.strong_strength);
        registerPotionTypeConversion(PotionTypes.water, predicate3, PotionTypes.weakness);
        registerPotionTypeConversion(PotionTypes.weakness, predicate2, PotionTypes.long_weakness);
    }

    /**
     * Registers a conversion from one potion item to another, with the given reagent. For example, normal potions
     * become splash potions using gunpowder.
     */
    private static void registerPotionItemConversion(ItemPotion p_185201_0_, PotionHelper.ItemPredicateInstance p_185201_1_, ItemPotion p_185201_2_)
    {
        POTION_ITEM_CONVERSIONS.add(new PotionHelper.MixPredicate(p_185201_0_, p_185201_1_, p_185201_2_));
    }

    /**
     * Registers an itempredicate that identifies a potion item, for example Items.potionItem, or Items.lingering_potion
     */
    private static void registerPotionItem(PotionHelper.ItemPredicateInstance p_185202_0_)
    {
        POTION_ITEMS.add(p_185202_0_);
    }

    /**
     * Registers a conversion from one PotionType to another PotionType, with the given reagent
     */
    private static void registerPotionTypeConversion(PotionType input, Predicate<ItemStack> reagentPredicate, PotionType output)
    {
        POTION_TYPE_CONVERSIONS.add(new PotionHelper.MixPredicate(input, reagentPredicate, output));
    }

    static class ItemPredicateInstance implements Predicate<ItemStack>
        {
            private final Item item;
            private final int meta;

            public ItemPredicateInstance(Item itemIn)
            {
                this(itemIn, -1);
            }

            public ItemPredicateInstance(Item itemIn, int metaIn)
            {
                this.item = itemIn;
                this.meta = metaIn;
            }

            public boolean apply(ItemStack p_apply_1_)
            {
                return p_apply_1_ != null && p_apply_1_.getItem() == this.item && (this.meta == -1 || this.meta == p_apply_1_.getMetadata());
            }
        }

    static class MixPredicate<T>
        {
            final T input;
            final Predicate<ItemStack> reagent;
            final T output;

            public MixPredicate(T inputIn, Predicate<ItemStack> reagentIn, T outputIn)
            {
                this.input = inputIn;
                this.reagent = reagentIn;
                this.output = outputIn;
            }
        }
}