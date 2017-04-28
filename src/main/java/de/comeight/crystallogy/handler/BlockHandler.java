package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.blocks.crystals.*;
import de.comeight.crystallogy.util.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static CrystalOreRed CRYSTAL_ORE_RED;
    public static ItemBlock ITEM_BLOCK_CRYSTAL_ORE_RED;
    public static CrystalOreBlue CRYSTAL_ORE_BLUE;
    public static ItemBlock ITEM_BLOCK_CRYSTAL_ORE_BLUE;
    public static CrystalOreGreen CRYSTAL_ORE_GREEN;
    public static ItemBlock ITEM_BLOCK_CRYSTAL_ORE_GREEN;
    public static CrystalOreYellow CRYSTAL_ORE_YELLOW;
    public static ItemBlock ITEM_BLOCK_CRYSTAL_ORE_YELLOW ;
    public static CrystalOreWhite CRYSTAL_ORE_WHITE;
    public static ItemBlock ITEM_BLOCK_CRYSTAL_ORE_WHITE;

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void createBlocks() {
        CRYSTAL_ORE_RED = new CrystalOreRed();
        ITEM_BLOCK_CRYSTAL_ORE_RED = new ItemBlock(CRYSTAL_ORE_RED);
        CRYSTAL_ORE_BLUE = new CrystalOreBlue();
        ITEM_BLOCK_CRYSTAL_ORE_BLUE = new ItemBlock(CRYSTAL_ORE_BLUE);
        CRYSTAL_ORE_GREEN = new CrystalOreGreen();
        ITEM_BLOCK_CRYSTAL_ORE_GREEN = new ItemBlock(CRYSTAL_ORE_GREEN);
        CRYSTAL_ORE_YELLOW = new CrystalOreYellow();
        ITEM_BLOCK_CRYSTAL_ORE_YELLOW = new ItemBlock(CRYSTAL_ORE_YELLOW);
        CRYSTAL_ORE_WHITE = new CrystalOreWhite();
        ITEM_BLOCK_CRYSTAL_ORE_WHITE = new ItemBlock(CRYSTAL_ORE_WHITE);
    }

    private void registerAllBlocks() {
        createBlocks();

        registerBlock(CRYSTAL_ORE_RED, ITEM_BLOCK_CRYSTAL_ORE_RED);
        registerBlock(CRYSTAL_ORE_BLUE, ITEM_BLOCK_CRYSTAL_ORE_BLUE);
        registerBlock(CRYSTAL_ORE_GREEN, ITEM_BLOCK_CRYSTAL_ORE_GREEN);
        registerBlock(CRYSTAL_ORE_YELLOW, ITEM_BLOCK_CRYSTAL_ORE_YELLOW);
        registerBlock(CRYSTAL_ORE_WHITE, ITEM_BLOCK_CRYSTAL_ORE_WHITE);

        Logger.info("All blocks got registered.");
    }

    private void registerOreDictionaryEntries() {
        Logger.info("All ore dictionary entries got added.");
    }

    private void registerBlock(Block block, ItemBlock itemBlock){
        GameRegistry.register(block);
        GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
        registerAllBlocks();
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
        registerOreDictionaryEntries();
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}