package de.comeight.crystallogy.itemBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public abstract class BaseItemBlock extends ItemBlock{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItemBlock(Block block, String id){
        super(block);

        setUnlocalizedName(id);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}