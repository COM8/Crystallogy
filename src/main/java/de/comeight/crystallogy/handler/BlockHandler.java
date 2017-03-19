package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.util.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerAllBlocks() {
        Logger.info("All blocks got registered.");
    }

    private void registerOreDictionaryEntries() {
        Logger.info("All ore dictionary entries got added.");
    }

    private void registerBlock(Block block, String id){
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(id));
    }

    private void registerBlock(Block block, ItemBlock itemBlock, String id){
        GameRegistry.register(block);
        GameRegistry.register(itemBlock.setRegistryName(id));
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