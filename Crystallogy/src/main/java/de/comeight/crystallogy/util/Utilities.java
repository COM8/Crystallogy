package de.comeight.crystallogy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.org.apache.xml.internal.security.utils.I18n;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class Utilities {
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------


	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static int getRandInt(int min, int max) {
        return getRandInt(min, max, new Random());
    }
	
	public static int getRandInt(int min, int max, Random random){
        int bound = (max - min) + 1;
        if(bound < 0){
        	return random.nextInt(bound * -1) * -1 + min;
        }
        else{
        	return random.nextInt(bound) + min;
        }
	}
	
	public static double getRandDouble(double min, double max) {
        return getRandDouble(min, max, new Random());
    }
	
	public static double getRandDouble(double min, double max, Random random) {
        double randomValue = min + (max - min) * random.nextDouble();
        return randomValue;
    }
	
	public static float getRandFloat(float min, float max) {
		return getRandFloat(min, max, new Random());
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
		return I18n.translate(text);
	}
}
