package de.comeight.crystallogy;

import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerInfuserBlockEnabled;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerInfusionRecipeStatus;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerParticle;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerUpdateInventory;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ServerProxy extends CommonProxy {
	// -----------------------------------------------Variabeln:---------------------------------------------
	
	
	// -----------------------------------------------Constructor:-------------------------------------------
	
	
	// -----------------------------------------------Set-, Get-Methoden:------------------------------------

		
	// -----------------------------------------------Sonstige Methoden:-------------------------------------
	
	
	// -----------------------------------------------Pre-Init:----------------------------------------------
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	// -----------------------------------------------Init:--------------------------------------------------
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	// -----------------------------------------------Post-Init:----------------------------------------------
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
	
}
