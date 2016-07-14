package de.comeight.crystallogy.particles;

import java.util.LinkedList;

import javax.annotation.Nonnull;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static LinkedList<BaseParticle> particleList = new LinkedList<BaseParticle>();
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@SubscribeEvent
	  public void stitcherEventPre(TextureStitchEvent.Pre e) {
	    registerParticleTextures(e);
	  }
	
	public void registerParticles(){
		registerParticle(new ParticleA());
		registerParticle(new ParticleB());
		registerParticle(new ParticleC());
		registerParticle(new ParticleD());
		registerParticle(new ParticleE());
		registerParticle(new ParticleF());
		
		registerParticle(new ParticleN_Color());
		
		registerParticle(new ParticleLight());
		registerParticle(new ParticleInfusion());
		registerParticle(new ParticleInfuserBlockStatus());
		registerParticle(new ParticleEnderon());
		registerParticle(new ParticleDebug());
	}
	
	public void registerParticleTextures(TextureStitchEvent.Pre event){
		ResourceLocation rL;
		for (BaseParticle baseParticle : particleList) {
			rL = baseParticle.rL;
			if(rL != null){
				event.getMap().registerSprite(rL);
			}
		}
	}
	
	public static void registerParticle(@Nonnull BaseParticle p){
		particleList.add(p);
	}
	
	public static BaseParticle findParticle(String id){
		for (BaseParticle baseParticle : particleList) {
			if(baseParticle.id.equals(id)){
				return baseParticle;
			}
		}
		return null;
	}
	
	// -----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit() {
		registerParticles();
	}

	// -----------------------------------------------Init:--------------------------------------------------
	public void init() {

	}

	// -----------------------------------------------Post-Init:---------------------------------------------
	public void postInit() {

	}
	
}
