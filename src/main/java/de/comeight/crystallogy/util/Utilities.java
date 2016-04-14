package de.comeight.crystallogy.util;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Utilities {
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------


	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static int getRandInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
	
	public static double getRandDouble(double min, double max) {
        Random rand = new Random();
        double randomValue = min + (max - min) * rand.nextDouble();
        return randomValue;
    }
	
	public static float getRandFloat(float min, float max) {
        Random rand = new Random();
        float randomValue = min + (max - min) * rand.nextFloat();
        return randomValue;
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
			addConsoleText("Side.Client!");
		}
		else{
			addConsoleText("Side.Server!");
		}
	}
}
