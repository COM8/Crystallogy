package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.items.CrystalShard;
import de.comeight.crystallogy.util.Logger;
import de.comeight.crystallogy.util.enums.EnumCrystalColor;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ItemRenderHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerItemRenderer() {
        for (int i = 0; i < 5; i++) {
            registerBasicItemRender(ItemHandler.CRYSTAL_SHARD, i, ItemHandler.CRYSTAL_SHARD.getUnlocalizedName().substring(5) + '_' + EnumCrystalColor.fromMeta(i));
            registerBasicItemRender(ItemHandler.CRYSTAL_DUST, i, ItemHandler.CRYSTAL_DUST.getUnlocalizedName().substring(5) + '_' + EnumCrystalColor.fromMeta(i));
        }

        Logger.info("All item renderer got registered.");
    }

    public static void registerBasicItemRender(Item item) {
        registerBasicItemRender(item, 0 , item.getUnlocalizedName().substring(5));
    }

    private static void registerBasicItemRender(Item item, int meta, String filePath){
        ModelResourceLocation mRL =  new ModelResourceLocation(Crystallogy.MOD_ID + ":" + filePath, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, mRL);
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
        registerItemRenderer();
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {

    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}