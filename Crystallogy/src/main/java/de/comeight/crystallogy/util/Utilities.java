package de.comeight.crystallogy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class Utilities {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static Random rand = new Random();

	//-----------------------------------------------Constructor:-------------------------------------------


	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static int getRandInt(int min, int max) {
        return getRandInt(min, max, rand);
    }
	
	public static int getRandInt(int min, int max, Random random){
        int bound = max - min;
        if(bound < 0){
        	return random.nextInt((bound) * -1) * -1 + min;
        }
        else{
        	return random.nextInt(bound) + min;
        }
	}
	
	public static double getRandDouble(double min, double max) {
        return getRandDouble(min, max, rand);
    }
	
	public static double getRandDouble(double min, double max, Random random) {
        double randomValue = min + (max - min) * random.nextDouble();
        return randomValue;
    }
	
	public static float getRandFloat(float min, float max) {
		return getRandFloat(min, max, rand);
    }
	
	public static float getRandFloat(float min, float max, Random random) {
        float randomValue = min + (max - min) * random.nextFloat();
        return randomValue;
    }
	
	public static List<ItemStack> getOresFrom(String name)
    {
		List<ItemStack> ores = OreDictionary.getOres(name);
		List<ItemStack> ores1 = new ArrayList<ItemStack>(); 
		
		for (ItemStack ore : ores) {
			if(ore.getItemDamage() == OreDictionary.WILDCARD_VALUE){
				ore.getItem().getSubItems(ore.getItem(), null, ores1);
			}
			else{
				ores1.add(ore);
			}
		}
        return ores1;
    }

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static double calcNewColorPercentage(double color, double modifire){
		color += modifire;
		color = (double) Utilities.round((float) color, 2);
		if(color > 1.0){
			color = 1.0;
		}
		if(color < 0.0){
			color = 0.0;
		}
		
		return color;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static void addConsoleText(String text){
		Log.info(text);
	}
	
	public static Vec3d vec3FormString(String s){
		String s1 = s.substring(0, s.indexOf(")"));
		//X:
		double posX= Double.parseDouble(s1.substring(1, s1.indexOf(",")));
		s1 = s1.substring(s1.indexOf(" ") + 1);
		
		//Y:		
		double posY = Double.parseDouble(s1.substring(0, s1.indexOf(",")));
		s1 = s1.substring(s1.indexOf(" ") + 1);
		
		//Z:
		double posZ = Double.parseDouble(s1);
		
		return new Vec3d(posX, posY, posZ);
	}
	
	public static void printWorldSide(World worldObj){
		if(worldObj.isRemote){
			Log.debug("Side.Client!");
		}
		else{
			Log.debug("Side.Server!");
		}
	}
	
	public static String localizeText(String text){
		return I18n.format(text, new Object[1]);
	}
	
	public static void saveBlockPosToNBT(NBTTagCompound compound, BlockPos pos, String key){
		if(pos == null){
			return;
		}
		compound.setInteger(key + "_X", pos.getX());
		compound.setInteger(key + "_Y", pos.getY());
		compound.setInteger(key + "_Z", pos.getZ());
	}
	
	public static BlockPos readBlockPosFromNBT(NBTTagCompound compound, String key){
		if(compound.hasKey(key + "_X")){
			return new BlockPos(compound.getInteger(key + "_X"), compound.getInteger(key + "_Y"), compound.getInteger(key + "_Z"));
		}
		else{
			return null;
		}
	}
	
	public static void saveVec3dToNBT(NBTTagCompound compound, Vec3d vec, String key){
		if(vec == null){
			return;
		}
		compound.setDouble(key + "_X", vec.xCoord);
		compound.setDouble(key + "_Y", vec.yCoord);
		compound.setDouble(key + "_Z", vec.zCoord);
	}
	
	public static Vec3d readVec3dFromNBT(NBTTagCompound compound, String key){
		if(compound.hasKey(key + "_X")){
			return new Vec3d(compound.getDouble(key + "_X"), compound.getDouble(key + "_Y"), compound.getDouble(key + "_Z"));
		}
		else{
			return null;
		}
	}
}
