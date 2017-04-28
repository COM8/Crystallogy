package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.CrystalDust;
import de.comeight.crystallogy.items.CrystalShard;
import de.comeight.crystallogy.util.Logger;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static CrystalShard CRYSTAL_SHARD;
    public static CrystalDust CRYSTAL_DUST;

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void createItems() {
        CRYSTAL_SHARD = new CrystalShard();
        CRYSTAL_DUST = new CrystalDust();
    }

    private void registerAllItems() {
        createItems();

        GameRegistry.register(CRYSTAL_SHARD);
        GameRegistry.register(CRYSTAL_DUST);

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