package de.comeight.crystallogy.util;

import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;

public class Util {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    public static String localizeText(String text){
        return I18n.format(text, new Object[1]);
    }

    public static void printWorldSide(World worldObj){
        if(worldObj.isRemote){
            Logger.debug("Side.Client!");
        }
        else{
            Logger.debug("Side.Server!");
        }
    }

    //-----------------------------------------------Events:------------------------------------------------

}