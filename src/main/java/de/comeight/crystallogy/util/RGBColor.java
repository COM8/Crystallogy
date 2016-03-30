package de.comeight.crystallogy.util;

import java.awt.Color;

public class RGBColor {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public float r;
	public float g;
	public float b;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public RGBColor() {
	}
	
	public RGBColor(int rgbColor) {
		Color c = hex2Rgb(Integer.toHexString(rgbColor));
		this.r = c.getRed() / 255.0F;
		this.g = c.getGreen() / 255.0F;
		this.b = c.getBlue() / 255.0F;
	}
	
	public RGBColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public void setRGB(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	/**
	 * 
	 * @param colorStr e.g. "#FFFFFF"
	 * @return 
	 */
	public static Color hex2Rgb(String colorStr) {
	    if(colorStr.length() < 6){
	    	for (int i = 0 ; i < 6 - colorStr.length(); i++){
	    		colorStr = "0" + colorStr;
	    	}
	    }
	    colorStr = "0x" + colorStr;
		return Color.decode(colorStr);
	}

}
