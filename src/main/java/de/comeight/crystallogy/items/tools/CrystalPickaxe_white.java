package de.comeight.crystallogy.items.tools;

public class CrystalPickaxe_white extends BaseCrystalPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalPickaxe_white";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalPickaxe_white() {
        super(CustomToolMaterials.CRYSTAL_WHITE, ID);
        setMaxDamage(getMaxDamage() * 3);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}