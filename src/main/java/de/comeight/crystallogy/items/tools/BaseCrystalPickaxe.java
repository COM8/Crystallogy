package de.comeight.crystallogy.items.tools;

public class BaseCrystalPickaxe extends BaseItemPickaxe{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseCrystalPickaxe(ToolMaterial material, String id) {
        super(material, id);
        setMaxDamage(getMaxDamage() * 10);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}