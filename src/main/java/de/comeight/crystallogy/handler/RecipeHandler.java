package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.util.Logger;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class RecipeHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerAllCraftingRecipes(){
        Logger.info("All crafting recipes got registered.");
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
        registerAllCraftingRecipes();
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}