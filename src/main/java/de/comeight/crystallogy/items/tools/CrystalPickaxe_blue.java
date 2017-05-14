package de.comeight.crystallogy.items.tools;

public class CrystalPickaxe_blue extends BaseCrystalPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalPickaxe_blue";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalPickaxe_blue() {
        super(CustomToolMaterials.CRYSTAL_BLUE, ID);
        setMaxDamage(getMaxDamage() * 3);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}