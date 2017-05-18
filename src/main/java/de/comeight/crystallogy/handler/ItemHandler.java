package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.*;
import de.comeight.crystallogy.items.tools.*;
import de.comeight.crystallogy.util.Logger;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static CrystalShard CRYSTAL_SHARD;
    public static CrystalDust CRYSTAL_DUST;
    public static CrystalPickaxeHead CRYSTAL_PICKAXE_HEAD;
    public static CrystalHammerHead CRYSTAL_HAMMER_HEAD;
    public static CrystalSwordBlade CRYSTAL_SWORD_BLADE;
    public static CrystalPickaxe_red CRYSTAL_PICKAXE_RED;
    public static CrystalPickaxe_blue CRYSTAL_PICKAXE_BLUE;
    public static CrystalPickaxe_green CRYSTAL_PICKAXE_GREEN;
    public static CrystalPickaxe_yellow CRYSTAL_PICKAXE_YELLOW;
    public static CrystalPickaxe_white CRYSTAL_PICKAXE_WHITE;
    public static CrystalSword_red CRYSTAL_SWORD_RED;
    public static CrystalSword_blue CRYSTAL_SWORD_BLUE;
    public static CrystalSword_green CRYSTAL_SWORD_GREEN;
    public static CrystalSword_yellow CRYSTAL_SWORD_YELLOW;
    public static CrystalSword_white CRYSTAL_SWORD_WHITE;
    public static CrystalShovelHead CRYSTAL_SHOVEL_HEAD;

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void createItems() {
        CRYSTAL_SHARD = new CrystalShard();
        CRYSTAL_DUST = new CrystalDust();
        CRYSTAL_HAMMER_HEAD = new CrystalHammerHead();
        CRYSTAL_PICKAXE_HEAD = new CrystalPickaxeHead();
        CRYSTAL_SWORD_BLADE = new CrystalSwordBlade();
        CRYSTAL_PICKAXE_RED = new CrystalPickaxe_red();
        CRYSTAL_PICKAXE_BLUE = new CrystalPickaxe_blue();
        CRYSTAL_PICKAXE_GREEN = new CrystalPickaxe_green();
        CRYSTAL_PICKAXE_YELLOW = new CrystalPickaxe_yellow();
        CRYSTAL_PICKAXE_WHITE = new CrystalPickaxe_white();
        CRYSTAL_SWORD_RED = new CrystalSword_red();
        CRYSTAL_SWORD_BLUE = new CrystalSword_blue();
        CRYSTAL_SWORD_GREEN = new CrystalSword_green();
        CRYSTAL_SWORD_YELLOW = new CrystalSword_yellow();
        CRYSTAL_SWORD_WHITE = new CrystalSword_white();
        CRYSTAL_SHOVEL_HEAD = new CrystalShovelHead();
    }

    private void registerAllItems() {
        createItems();

        GameRegistry.register(CRYSTAL_SHARD);
        GameRegistry.register(CRYSTAL_DUST);
        GameRegistry.register(CRYSTAL_HAMMER_HEAD);
        GameRegistry.register(CRYSTAL_PICKAXE_HEAD);
        GameRegistry.register(CRYSTAL_SWORD_BLADE);
        GameRegistry.register(CRYSTAL_PICKAXE_RED);
        GameRegistry.register(CRYSTAL_PICKAXE_BLUE);
        GameRegistry.register(CRYSTAL_PICKAXE_GREEN);
        GameRegistry.register(CRYSTAL_PICKAXE_YELLOW);
        GameRegistry.register(CRYSTAL_PICKAXE_WHITE);
        GameRegistry.register(CRYSTAL_SWORD_RED);
        GameRegistry.register(CRYSTAL_SWORD_BLUE);
        GameRegistry.register(CRYSTAL_SWORD_GREEN);
        GameRegistry.register(CRYSTAL_SWORD_YELLOW);
        GameRegistry.register(CRYSTAL_SWORD_WHITE);
        GameRegistry.register(CRYSTAL_SHOVEL_HEAD);

        Logger.info("All items got registered.");
    }

    private void registerAllItemBlocks() {
        Logger.info("All itemBlocks got registered.");
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
        registerAllItems();
        registerAllItemBlocks();
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}