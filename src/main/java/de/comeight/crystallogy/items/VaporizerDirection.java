package de.comeight.crystallogy.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class VaporizerDirection extends Vaporizer {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "vaporizerDirection";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public VaporizerDirection() {
		super(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void spawnParticles(Vec3d coords, ItemStack stack, World worldIn) {
		
	}
	
	
}
