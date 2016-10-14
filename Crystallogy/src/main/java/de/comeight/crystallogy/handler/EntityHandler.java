package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.entity.EntityMagicStoneOfForgetfulness;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static int id = 0;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityHandler() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerEntities(){
		EntityRegistry.registerModEntity(EntityMagicStoneOfForgetfulness.class, "entityMagicStoneOfForgetfulness", id++, CrystallogyBase.INSTANCE, 64, 1, true);
	}
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(){
		
	}

	//-----------------------------------------------Init:--------------------------------------------------
	public void init(){
		registerEntities();
	}

	//-----------------------------------------------Post-Init:---------------------------------------------
	public void postInit(){
		
	}
}
