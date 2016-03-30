package de.comeight.crystallogy.itemBlocks;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class BaseItemBlock extends ItemBlock {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemBlock(Block block, String id) {
		super(block);
		
		this.setUnlocalizedName(id);
		this.setCreativeTab(CommonProxy.crystallogyMainTab);
		
		System.out.println("\"" + this.getUnlocalizedName() + "\" wurde initialisiert.");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

}
