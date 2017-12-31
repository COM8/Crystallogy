package de.comeight.crystallogy.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class NBTUtils {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    public static void setVec3d(Vec3d v, NBTTagCompound nbt, String key){
        nbt.setDouble(key + "_x", v.x);
        nbt.setDouble(key + "_y", v.y);
        nbt.setDouble(key + "_z", v.z);
    }

    public static Vec3d getVec3d(NBTTagCompound nbt, String key){
        return new Vec3d(nbt.getDouble(key + "_x"), nbt.getDouble(key + "_y"), nbt.getDouble(key + "_z"));
    }

    public static void setRGBColor(RGBColor c, NBTTagCompound nbt, String key){
        nbt.setFloat(key + "_r", c.R);
        nbt.setFloat(key + "_g", c.G);
        nbt.setFloat(key + "_b", c.B);
    }

    public static RGBColor getRGBColor(NBTTagCompound nbt, String key){
        return new RGBColor(nbt.getFloat(key + "_r"), nbt.getFloat(key + "_g"), nbt.getFloat(key + "_b"));
    }

    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}