package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static SoundEvent CRUSHER;
	public static SoundEvent COMPRESSOR;
	public static SoundEvent LIGHT_WOOSH;
	public static SoundEvent MACHINE_BROKEN;
	public static SoundEvent EVIL_LAUGH_MALE_1;
	public static SoundEvent EVIL_LAUGH_MALE_2;
	public static SoundEvent ARMORCOMBINER;
	public static SoundEvent BOOKOPEN;
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerAllSound(){
		//CRUSHER:
		ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID, "bookOpen");
		BOOKOPEN = new SoundEvent(rL);
		GameRegistry.register(BOOKOPEN, rL);
		
		//CRUSHER:
		rL = new ResourceLocation(CrystallogyBase.MODID, "crusher");
		CRUSHER = new SoundEvent(rL);
		GameRegistry.register(CRUSHER, rL);
		
		//COMPRESSOR:
		rL = new ResourceLocation(CrystallogyBase.MODID, "compressor");
		COMPRESSOR = new SoundEvent(rL);
		GameRegistry.register(COMPRESSOR, rL);

		//LIGHT_WOOSH:
		rL = new ResourceLocation(CrystallogyBase.MODID, "light_woosh");
		LIGHT_WOOSH = new SoundEvent(rL);
		GameRegistry.register(LIGHT_WOOSH, rL);

		//MACHINE_BROKEN:
		rL = new ResourceLocation(CrystallogyBase.MODID, "machine_broken");
		MACHINE_BROKEN = new SoundEvent(rL);
		GameRegistry.register(MACHINE_BROKEN, rL);
				
		//EVIL_LAUGH_MALE_1:
		rL = new ResourceLocation(CrystallogyBase.MODID, "evil_laugh_male_1");
		EVIL_LAUGH_MALE_1 = new SoundEvent(rL);
		GameRegistry.register(EVIL_LAUGH_MALE_1, rL);
		
		//EVIL_LAUGH_MALE_2:
		rL = new ResourceLocation(CrystallogyBase.MODID, "evil_laugh_male_2");
		EVIL_LAUGH_MALE_2 = new SoundEvent(rL);
		GameRegistry.register(EVIL_LAUGH_MALE_2, rL);
		
		//ARMORCOMBINER:
		rL = new ResourceLocation(CrystallogyBase.MODID, "armorCombiner");
		ARMORCOMBINER = new SoundEvent(rL);
		GameRegistry.register(ARMORCOMBINER, rL);
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
