package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.items.*;
import de.comeight.crystallogy.items.tools.*;
import de.comeight.crystallogy.util.Logger;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

@GameRegistry.ObjectHolder(Crystallogy.MOD_ID)
public class ItemHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final CrystalShard CRYSTAL_SHARD = new CrystalShard();
    public static final CrystalDust CRYSTAL_DUST = new CrystalDust();
    public static final CrystalPickaxeHead CRYSTAL_PICKAXE_HEAD = new CrystalPickaxeHead();
    public static final CrystalHammerHead CRYSTAL_HAMMER_HEAD = new CrystalHammerHead();
    public static final CrystalHammer_red CRYSTAL_HAMMER_RED = new CrystalHammer_red();
    public static final CrystalHammer_blue CRYSTAL_HAMMER_BLUE = new CrystalHammer_blue();
    public static final CrystalHammer_green CRYSTAL_HAMMER_GREEN = new CrystalHammer_green();
    public static final CrystalHammer_yellow CRYSTAL_HAMMER_YELLOW = new CrystalHammer_yellow();
    public static final CrystalHammer_white CRYSTAL_HAMMER_WHITE = new CrystalHammer_white();
    public static final CrystalSwordBlade CRYSTAL_SWORD_BLADE = new CrystalSwordBlade();
    public static final CrystalPickaxe_red CRYSTAL_PICKAXE_RED = new CrystalPickaxe_red();
    public static final CrystalPickaxe_blue CRYSTAL_PICKAXE_BLUE = new CrystalPickaxe_blue();
    public static final CrystalPickaxe_green CRYSTAL_PICKAXE_GREEN = new CrystalPickaxe_green();
    public static final CrystalPickaxe_yellow CRYSTAL_PICKAXE_YELLOW = new CrystalPickaxe_yellow();
    public static final CrystalPickaxe_white CRYSTAL_PICKAXE_WHITE = new CrystalPickaxe_white();
    public static final CrystalSword_red CRYSTAL_SWORD_RED = new CrystalSword_red();
    public static final CrystalSword_blue CRYSTAL_SWORD_BLUE = new CrystalSword_blue();
    public static final CrystalSword_green CRYSTAL_SWORD_GREEN = new CrystalSword_green();
    public static final CrystalSword_yellow CRYSTAL_SWORD_YELLOW = new CrystalSword_yellow();
    public static final CrystalSword_white CRYSTAL_SWORD_WHITE = new CrystalSword_white();
    public static final CrystalShovelHead CRYSTAL_SHOVEL_HEAD = new CrystalShovelHead();
    public static final CrystalShovel_red CRYSTAL_SHOVEL_RED = new CrystalShovel_red();
    public static final CrystalShovel_blue CRYSTAL_SHOVEL_BLUE = new CrystalShovel_blue();
    public static final CrystalShovel_green CRYSTAL_SHOVEL_GREEN = new CrystalShovel_green();
    public static final CrystalShovel_yellow CRYSTAL_SHOVEL_YELLOW = new CrystalShovel_yellow();
    public static final CrystalShovel_white CRYSTAL_SHOVEL_WHITE = new CrystalShovel_white();
    public static final BookOfKnowledge BOOK_OF_KNOWLEDGE = new BookOfKnowledge();
    public static final EntityCrystalKnife ENTITY_CRYSTAL_KNIFE = new EntityCrystalKnife();
    public static final PlayerCrystalKnife PLAYER_CRYSTAL_KNIFE = new PlayerCrystalKnife();
    public static final DebugItem DEBUG_ITEM = new DebugItem();
    public static final BouncyCrystal BOUNCY_CRYSTAL = new BouncyCrystal();

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------
    @Mod.EventBusSubscriber(modid = Crystallogy.MOD_ID)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();
            final Item[] items = {
                    CRYSTAL_SHARD,
                    CRYSTAL_DUST,
                    CRYSTAL_PICKAXE_HEAD,
                    CRYSTAL_SWORD_BLADE,
                    CRYSTAL_SHOVEL_HEAD,

                    CRYSTAL_HAMMER_HEAD,
                    CRYSTAL_HAMMER_RED,
                    CRYSTAL_HAMMER_BLUE,
                    CRYSTAL_HAMMER_GREEN,
                    CRYSTAL_HAMMER_YELLOW,
                    CRYSTAL_HAMMER_WHITE,

                    CRYSTAL_PICKAXE_RED,
                    CRYSTAL_PICKAXE_BLUE,
                    CRYSTAL_PICKAXE_GREEN,
                    CRYSTAL_PICKAXE_YELLOW,
                    CRYSTAL_PICKAXE_WHITE,

                    CRYSTAL_SWORD_RED,
                    CRYSTAL_SWORD_BLUE,
                    CRYSTAL_SWORD_GREEN,
                    CRYSTAL_SWORD_YELLOW,
                    CRYSTAL_SWORD_WHITE,

                    CRYSTAL_SHOVEL_RED,
                    CRYSTAL_SHOVEL_BLUE,
                    CRYSTAL_SHOVEL_GREEN,
                    CRYSTAL_SHOVEL_YELLOW,
                    CRYSTAL_SHOVEL_WHITE,

                    ENTITY_CRYSTAL_KNIFE,
                    PLAYER_CRYSTAL_KNIFE,
                    BOUNCY_CRYSTAL,
                    ConfigHandler.enableDebugItem ? DEBUG_ITEM : null,

                    BOOK_OF_KNOWLEDGE
            };

            for (final Item item : items) {
                if(item != null) {
                    registry.register(item);
                    ITEMS.add(item);
                }
            }
            Logger.info("All items got registered.");
        }
    }

    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}