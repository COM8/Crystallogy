package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.util.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ItemRenderHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerBlockRenderer() {
        Logger.info("All item renderer got registered.");
    }

    public static void registerBasicItemRender(Item item) {
        registerBasicItemRender(item, 0 , item.getUnlocalizedName().substring(5));
    }

    private static void registerBasicItemRender(Item item, int meta, String filePath){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Crystallogy.MOD_ID + ":" + filePath, "inventory"));
    }

    private static void registerItemVariantsRenderer(Item item, int meta, String filePath){
        registerBasicItemRender(item, meta, filePath);
        ModelBakery.registerItemVariants(item, new ResourceLocation(Crystallogy.MOD_ID + ":" + filePath));
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