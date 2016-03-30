package de.comeight.crystallogy.items;

import java.util.Set;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.ItemTool;

public class BaseItemTool extends ItemTool {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemTool(float attackDamage, float attackSpeed, ToolMaterial material, Set<Block> effectiveBlocks, String ID) {
		super(attackDamage, attackDamage, material, effectiveBlocks);
		this.setCreativeTab(CommonProxy.crystallogyMainTab);
		this.setUnlocalizedName(ID);
		
		System.out.println("\"" + this.getUnlocalizedName() + "\" wurde initialisiert.");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
