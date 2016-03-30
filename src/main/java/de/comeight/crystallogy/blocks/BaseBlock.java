package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block{

	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseBlock(Material material, String id){
		super(material);
		
		this.setUnlocalizedName(id);
		this.setCreativeTab(CommonProxy.crystallogyMainTab);
		
		System.out.println("\"" + this.getUnlocalizedName() + "\" wurde initialisiert.");
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------

}
