package de.comeight.crystallogy.items.tools;

public class CrystalPickaxe_red extends BaseCrystalPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalPickaxe_red";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalPickaxe_red() {
        super(CustomToolMaterials.CRYSTAL_RED, ID);
        setMaxDamage(getMaxDamage() * 3);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}