package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.util.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ItemBlockRenderHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerItemBlockRenderer(){
        Logger.info("All itemBlock renderer got registered.");
    }

    private void registerRenderer(String id){
        String location = Crystallogy.MOD_ID + ":" + id;
        Item item = Item.REGISTRY.getObject(new ResourceLocation(location));
        ModelResourceLocation mRL = new ModelResourceLocation(location, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, mRL);
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
        registerItemBlockRenderer();
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}