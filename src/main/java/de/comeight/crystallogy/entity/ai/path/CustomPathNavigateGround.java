package de.comeight.crystallogy.entity.ai.path;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.world.World;

public class CustomPathNavigateGround extends PathNavigateClimber {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CustomPathNavigateGround(EntityLiving entitylivingIn, World worldIn) {
		super(entitylivingIn, worldIn);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public float getPathSearchRange() {
		return 64;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
	
}
