package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.util.Logger;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BlockRenderHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerBlockRenderer() {
        registerRenderer(BlockHandler.ITEM_BLOCK_CRYSTAL_ORE_RED);
        registerRenderer(BlockHandler.ITEM_BLOCK_CRYSTAL_ORE_BLUE);
        registerRenderer(BlockHandler.ITEM_BLOCK_CRYSTAL_ORE_GREEN);
        registerRenderer(BlockHandler.ITEM_BLOCK_CRYSTAL_ORE_YELLOW);
        registerRenderer(BlockHandler.ITEM_BLOCK_CRYSTAL_ORE_WHITE);

        Logger.info("All block renderer got registered.");
    }

    private void registerRenderer(ItemBlock itemBlock, int meta, String modelName) {
        ModelResourceLocation mRL = new ModelResourceLocation(Crystallogy.MOD_ID + ':' + modelName, "inventory");
        ModelLoader.setCustomModelResourceLocation(itemBlock, meta, mRL);
    }

    private void registerRenderer (ItemBlock itemBlock) {
        registerRenderer(itemBlock, 0, itemBlock.getUnlocalizedName().substring(5));
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
        registerBlockRenderer();
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}