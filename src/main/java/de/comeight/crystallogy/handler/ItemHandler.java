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
                    CRYSTAL_HAMMER_HEAD,
                    CRYSTAL_PICKAXE_HEAD,
                    CRYSTAL_SWORD_BLADE,
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
                    CRYSTAL_SHOVEL_HEAD
            };

            for (final Item item : items) {
                registry.register(item);
                ITEMS.add(item);
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