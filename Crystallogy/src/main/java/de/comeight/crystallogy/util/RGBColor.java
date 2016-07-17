package de.comeight.crystallogy.util;

import java.awt.Color;

import net.minecraft.nbt.NBTTagCompound;
import scala.collection.generic.BitOperations.Int;

public class RGBColor {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public float r;
	public float g;
	public float b;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public RGBColor() {
	}
	
	/**
	 * Creates a new {@link RGBColor}.
	 * 
	 * @param rgbColor a color based on a {@link Int}
	 */
	public RGBColor(int rgbColor) {
		fromInt(rgbColor);
	}
	
	/**
	 * Creates a new {@link RGBColor}.
	 * All values have to be between 0.0F and 1.0F!
	 * 
	 * @param r red
	 * @param g green
	 * @param b blue
	 */
	public RGBColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	/**
	 * Setter for the RGB Color.
	 * All values have to be between 0.0F and 1.0F!
	 * 
	 * @param r red
	 * @param g green
	 * @param b blue
	 */
	public void setRGB(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	/**
	 * Reads a RGB Color from a {@link NBTTagCompound}.
	 * 
	 * @param compound the {@link NBTTagCompound} the color should get read from
	 */
	public void fromNBTTagCompound(NBTTagCompound compound, String key){
		r = compound.getFloat(key + 'R');
		g = compound.getFloat(key + 'G');
		b = compound.getFloat(key + 'B');
	}
	
	/**
	 * Adds this RGB Color to a given {@link NBTTagCompound}.
	 * 
	 * @param compound the {@link NBTTagCompound} the color should get added to
	 */
	public void toNBTTagCompound(NBTTagCompound compound, String key){
		compound.setFloat(key + 'R', r);
		compound.setFloat(key + 'G', g);
		compound.setFloat(key + 'B', b);
	}
	
	/**
	 * Converts a color string to a RGB Color.
	 * 
	 * @param colorStr e.g. "#FFFFFF"
	 */
	public static Color hex2Rgb(String colorStr) {
		if(colorStr.length() > 6){
			colorStr = colorStr.substring(0, 6);
		}
		if(colorStr.length() < 6){
	    	for (int i = 0 ; i < 6 - colorStr.length(); i++){
	    		colorStr = "0" + colorStr;
	    	}
	    }
	    colorStr = "0x" + colorStr;
		return Color.decode(colorStr);
	}
	
	/**
	 * Converts a color to a RGB Color with a {@link Int} base.
	 */
	public int toInt(){
		int rgb = (int) (r*255);
		rgb = (rgb << 8) + (int) (g * 255);
		rgb = (rgb << 8) + (int) (b * 255);
		
		return rgb;
	}
	
	/**
	 * Reades the {@link Int} value of a rgb color and translates it.
	 * 
	 * @param rgb a color based on a {@link Int}
	 */
	public void fromInt(int rgb){
		Color c = hex2Rgb(Integer.toHexString(rgb));
		this.r = c.getRed() / 255.0F;
		this.g = c.getGreen() / 255.0F;
		this.b = c.getBlue() / 255.0F;
	}

}
