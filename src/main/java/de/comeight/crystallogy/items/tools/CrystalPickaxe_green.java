package de.comeight.crystallogy.items.tools;

public class CrystalPickaxe_green extends BaseCrystalPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalPickaxe_green";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalPickaxe_green() {
        super(CustomToolMaterials.CRYSTAL_GREEN, ID);
        setMaxDamage(getMaxDamage() * 3);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}