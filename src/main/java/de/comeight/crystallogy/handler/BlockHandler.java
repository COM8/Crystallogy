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
    //Blocks:
    public static final CrystalOreRed CRYSTAL_ORE_RED = new CrystalOreRed();
    public static final ItemBlock ITEM_BLOCK_CRYSTAL_ORE_RED = new ItemBlock(CRYSTAL_ORE_RED);
    public static final CrystalOreBlue CRYSTAL_ORE_BLUE = new CrystalOreBlue();
    public static final ItemBlock ITEM_BLOCK_CRYSTAL_ORE_BLUE = new ItemBlock(CRYSTAL_ORE_BLUE);
    public static final CrystalOreGreen CRYSTAL_ORE_GREEN = new CrystalOreGreen();
    public static final ItemBlock ITEM_BLOCK_CRYSTAL_ORE_GREEN = new ItemBlock(CRYSTAL_ORE_GREEN);
    public static final CrystalOreYellow CRYSTAL_ORE_YELLOW = new CrystalOreYellow();
    public static final ItemBlock ITEM_BLOCK_CRYSTAL_ORE_YELLOW = new ItemBlock(CRYSTAL_ORE_YELLOW);
    public static final CrystalOreWhite CRYSTAL_ORE_WHITE = new CrystalOreWhite();
    public static final ItemBlock ITEM_BLOCK_CRYSTAL_ORE_WHITE = new ItemBlock(CRYSTAL_ORE_WHITE);

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerAllBlocks() {
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