package de.comeight.crystallogy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CrystallogyBase.MODID, version = CrystallogyBase.VERSION, name = CrystallogyBase.MODNAME)
public class CrystallogyBase {
	
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String MODID = "crystallogy";
	public static final String MODNAME = "Crystallogy";
	public static final String VERSION = "1.1.3";
	
	@SidedProxy(clientSide="de.comeight.crystallogy.ClientProxy", serverSide= "de.comeight.crystallogy.ServerProxy")
	public static CommonProxy proxy;
	
	@Instance(MODID)
	public static CrystallogyBase INSTANCE;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallogyBase(){
	}
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent){
		proxy.preInit(preEvent);
	}

	//-----------------------------------------------Init:--------------------------------------------------
	@EventHandler
	public void init(FMLInitializationEvent initEvent){
		proxy.init(initEvent);
		
	}
	
	//-----------------------------------------------Post-Init:---------------------------------------------
	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent){
		proxy.postInit(postEvent);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
