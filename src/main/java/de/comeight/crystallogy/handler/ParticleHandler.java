package de.comeight.crystallogy.handler;

import java.util.LinkedList;

import javax.annotation.Nonnull;

import de.comeight.crystallogy.particles.BaseParticle;
import de.comeight.crystallogy.particles.ParticleA;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.particles.ParticleC;
import de.comeight.crystallogy.particles.ParticleD;
import de.comeight.crystallogy.particles.ParticleDebug;
import de.comeight.crystallogy.particles.ParticleE;
import de.comeight.crystallogy.particles.ParticleEnderon;
import de.comeight.crystallogy.particles.ParticleF;
import de.comeight.crystallogy.particles.ParticleInfuserBlockStatus;
import de.comeight.crystallogy.particles.ParticleInfusion;
import de.comeight.crystallogy.particles.ParticleLight;
import de.comeight.crystallogy.particles.ParticleN_Color;
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
