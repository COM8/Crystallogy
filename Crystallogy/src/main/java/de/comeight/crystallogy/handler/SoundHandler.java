package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static SoundEvent CRUSHER;
	public static SoundEvent EVIL_LAUGH_MALE_1;
	public static SoundEvent EVIL_LAUGH_MALE_2;
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerAllSound(){
		//CRUSHER:
		ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID, "crusher");
		CRUSHER = new SoundEvent(rL);
		GameRegistry.register(CRUSHER, rL);
		
		//EVIL_LAUGH_MALE_1:
		rL = new ResourceLocation(CrystallogyBase.MODID, "evil_laugh_male_1");
		EVIL_LAUGH_MALE_1 = new SoundEvent(rL);
		GameRegistry.register(EVIL_LAUGH_MALE_1, rL);
		
		//EVIL_LAUGH_MALE_2:
		rL = new ResourceLocation(CrystallogyBase.MODID, "evil_laugh_male_2");
		EVIL_LAUGH_MALE_2 = new SoundEvent(rL);
		GameRegistry.register(EVIL_LAUGH_MALE_2, rL);
	}
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(){
		registerAllSound();
	}

	//-----------------------------------------------Init:--------------------------------------------------
	public void init(){
		
	}

	//-----------------------------------------------Post-Init:---------------------------------------------
	public void postInit(){
		
	}
	
}
