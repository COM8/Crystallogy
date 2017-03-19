package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.util.Logger;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BlockRenderHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerBlockRenderer() {
        Logger.info("All block renderer got registered.");
    }

    private void registerRenderer(Block block) {
        Item item = Item.getItemFromBlock(block);
        ModelResourceLocation iMRL = new ModelResourceLocation(Crystallogy.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, iMRL);
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
        registerBlockRenderer();
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}