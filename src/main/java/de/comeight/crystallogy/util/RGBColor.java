package de.comeight.crystallogy.util;

import java.util.Random;

public class RGBColor {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final RGBColor WHITE = new RGBColor(1.0F, 1.0F, 1.0F);
    public static final RGBColor BLACK = new RGBColor(0.0F, 0.0F, 0.0F);
    public static final RGBColor RED = new RGBColor(1.0F, 0.0F, 0.0F);
    public static final RGBColor BLUE = new RGBColor(0.0F, 0.0F, 1.0F);
    public static final RGBColor GREEN = new RGBColor(0.0F, 1.0F, 0.0F);

    public final float R;
    public final float G;
    public final float B;

    //-----------------------------------------------Constructor:-------------------------------------------
    public RGBColor(float r, float g, float b) {
        this.R = r;
        this.G = g;
        this.B = b;
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    public static RGBColor random(Random r){
        return new RGBColor(r.nextFloat(), r.nextFloat(), r.nextFloat());
    }

    //-----------------------------------------------Events:------------------------------------------------

}