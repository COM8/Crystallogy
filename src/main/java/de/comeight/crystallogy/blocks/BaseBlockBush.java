package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.block.BlockBush;

public abstract class BaseBlockBush extends BlockBush {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseBlockBush(String id){
		super();
		
		setUnlocalizedName(id);
		setRegistryName(id);
		setCreativeTab(CommonProxy.crystallogyMainTab);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
