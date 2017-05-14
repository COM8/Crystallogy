package de.comeight.crystallogy.items.tools;

public class CrystalPickaxe_yellow extends BaseCrystalPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalPickaxe_yellow";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalPickaxe_yellow() {
        super(CustomToolMaterials.CRYSTAL_YELLOW, ID);
        setMaxDamage(getMaxDamage() * 3);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}