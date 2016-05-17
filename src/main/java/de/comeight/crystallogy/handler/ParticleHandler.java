package de.comeight.crystallogy.handler;

import java.util.Map;

import com.google.common.collect.Maps;

import de.comeight.crystallogy.particles.BaseParticle;
import de.comeight.crystallogy.particles.EnumCrystallogyParticleTypes;
import de.comeight.crystallogy.particles.InfuserBlockActiveParticle;
import de.comeight.crystallogy.particles.InfusionParticle;
import de.comeight.crystallogy.particles.JumpParticleBetweenCrystalls;
import de.comeight.crystallogy.particles.LightParticle;
import de.comeight.crystallogy.particles.ParticleA;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.particles.ParticleC;
import de.comeight.crystallogy.particles.ParticleD;
import de.comeight.crystallogy.particles.ParticleE;
import de.comeight.crystallogy.particles.ParticleF;
import de.comeight.crystallogy.particles.ParticleNColor;
import de.comeight.crystallogy.particles.ParticleNColor.Factory;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private Map<Integer, IParticleFactory> particleTypes = Maps.<Integer, IParticleFactory>newHashMap(); 
	private EffectRenderer effectRenderer;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleHandler() {
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------	
	public IParticleFactory getParticleFactory(int id){
		return particleTypes.get(id);
	}
	
	public static BaseParticle getBaseParticleFromType(String type){
		return getBaseParticleFromType(type, null);
	}
	
	public static BaseParticle getBaseParticleFromType(String type, String s){
		BaseParticle particle = new BaseParticle();
		if(type.equals(ParticleA.NAME)){
			particle = new ParticleA(s);
		}
		else if(type.equals(ParticleB.NAME)){
			particle = new ParticleB(s);
		}
		else if(type.equals(ParticleC.NAME)){
			particle = new ParticleC(s);
		}
		else if(type.equals(ParticleD.NAME)){
			particle = new ParticleD(s);
		}
		else if(type.equals(ParticleE.NAME)){
			particle = new ParticleE(s);
		}
		else if(type.equals(ParticleF.NAME)){
			particle = new ParticleF(s);
		}
		else if(type.equals(InfusionParticle.NAME)){
			particle = new InfusionParticle(s);
		}
		else if(type.equals(JumpParticleBetweenCrystalls.NAME)){
			particle = new JumpParticleBetweenCrystalls(s);
		}
		else if(type.equals(ParticleNColor.NAME)){
			particle = new ParticleNColor(s);
		}
		else if(type.equals(InfuserBlockActiveParticle.NAME)){
			particle = new InfuserBlockActiveParticle(s);
		}
		else{
			Utilities.addConsoleText("Unknown Particle Type!");
		}
		return particle;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void registerParticle(int id, IParticleFactory particleFactory)
    {
        this.particleTypes.put(Integer.valueOf(id), particleFactory);
    }
	
	@SubscribeEvent
    public void stitcherEventPre(TextureStitchEvent.Pre event) {
		//ParticleA:
		for (int i = 0; i < ParticleA.rL.length; i++) {
			event.getMap().registerSprite(ParticleA.rL[i]);
		}

		//ParticleB:
		for (int i = 0; i < ParticleB.rL.length; i++) {
			event.getMap().registerSprite(ParticleB.rL[i]);
		}
		
		//ParticleC:
		for (int i = 0; i < ParticleC.rL.length; i++) {
			event.getMap().registerSprite(ParticleC.rL[i]);
		}
		
		//ParticleD:
		for (int i = 0; i < ParticleD.rL.length; i++) {
			event.getMap().registerSprite(ParticleD.rL[i]);
		}
		
		//ParticleE:
		for (int i = 0; i < ParticleE.rL.length; i++) {
			event.getMap().registerSprite(ParticleE.rL[i]);
		}
		
		//ParticleF:
		for (int i = 0; i < ParticleF.rL.length; i++) {
			event.getMap().registerSprite(ParticleF.rL[i]);
		}
		
		//ParticleN_COLOR:
		for (int i = 0; i < ParticleNColor.rL.length; i++) {
			event.getMap().registerSprite(ParticleNColor.rL[i]);
		}
		
		//InfusionParticle:
		for (int i = 0; i < InfusionParticle.rL.length; i++) {
			event.getMap().registerSprite(InfusionParticle.rL[i]);
		}
		
		//JumpBetweenCrystalls:
		for (int i = 0; i < JumpParticleBetweenCrystalls.rL.length; i++) {
			event.getMap().registerSprite(JumpParticleBetweenCrystalls.rL[i]);
		}
		
		//InfuserBlockActiveParticle:
		for (int i = 0; i < InfuserBlockActiveParticle.rL.length; i++) {
			event.getMap().registerSprite(InfuserBlockActiveParticle.rL[i]);
		}
		
		//LightParticle:
		for (int i = 0; i < LightParticle.rL.length; i++) {
			event.getMap().registerSprite(LightParticle.rL[i]);
		}
		
		registerParticles();
		
		Utilities.addConsoleText("All particles are registered.");
	}
	
	private void registerParticles(){
		this.registerParticle(EnumCrystallogyParticleTypes.PARTICLE_A.getParticleID(), new ParticleA.Factory());
		this.registerParticle(EnumCrystallogyParticleTypes.PARTICLE_B.getParticleID(), new ParticleB.Factory());
		this.registerParticle(EnumCrystallogyParticleTypes.PARTICLE_C.getParticleID(), new ParticleC.Factory());
		this.registerParticle(EnumCrystallogyParticleTypes.PARTICLE_D.getParticleID(), new ParticleD.Factory());
		this.registerParticle(EnumCrystallogyParticleTypes.PARTICLE_E.getParticleID(), new ParticleE.Factory());
		this.registerParticle(EnumCrystallogyParticleTypes.PARTICLE_F.getParticleID(), new ParticleF.Factory());
		
		this.registerParticle(EnumCrystallogyParticleTypes.PARTICLE_N_COLOR.getParticleID(), new ParticleNColor.Factory());
		
		this.registerParticle(EnumCrystallogyParticleTypes.INFUSION_PARTICLE.getParticleID(), new InfusionParticle.Factory());
		this.registerParticle(EnumCrystallogyParticleTypes.JUMP_PARTICLE_BETWEEN_CRYSTALLS.getParticleID(), new JumpParticleBetweenCrystalls.Factory());
		this.registerParticle(EnumCrystallogyParticleTypes.LIGHT_PARTICLE.getParticleID(), new InfuserBlockActiveParticle.Factory());
		//this.registerParticle(EnumCrystallogyParticleTypes.INFUSER_BLOCK_ACTIVE_PARTICLE.getParticleID(), new LightParticle.Factory());
	}
	
}
