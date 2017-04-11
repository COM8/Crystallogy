package de.comeight.crystallogy;

import de.comeight.crystallogy.handler.AiHandler;
import de.comeight.crystallogy.util.Utilities;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy {
	// -----------------------------------------------Variabeln:---------------------------------------------
	public static AiHandler AI_HANDLER = new AiHandler();
	
	// -----------------------------------------------Constructor:-------------------------------------------
	
	
	// -----------------------------------------------Set-, Get-Methoden:------------------------------------

		
	// -----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerEventHandlerServer(){
    	Utilities.addConsoleText("All server event Handler are registered.");
    }
	
	// -----------------------------------------------Pre-Init:----------------------------------------------
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		registerEventHandlerServer();
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
