package de.comeight.crystallogy.items.tools;

public class BaseItemHammer extends BaseItemPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItemHammer(ToolMaterial material, String id) {
        super(material, id);

        setMaxDamage(getMaxDamage() * 15);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}