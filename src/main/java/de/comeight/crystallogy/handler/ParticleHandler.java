package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.client.particles.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class ParticleHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    private static ArrayList<BaseParticle> particleList = new ArrayList<>();

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    public static BaseParticle getParticleById(String id){
        for (BaseParticle baseParticle : particleList) {
            if(baseParticle.ID.equals(id)){
                return baseParticle;
            }
        }
        return null;
    }

    //-----------------------------------------------Misc Methods:------------------------------------------
    public void registerParticleTextures(TextureStitchEvent.Pre event){
        ResourceLocation rL;
        for (BaseParticle baseParticle : particleList) {
            rL = baseParticle.RL;
            if(rL != null){
                event.getMap().registerSprite(rL);
            }
        }
    }

    public void registerParticle(@Nonnull BaseParticle p){
        particleList.add(p);
    }

    public void registerParticles(){
        registerParticle(new CrystalParticle());
        registerParticle(new SquareParticle());
    }

    //-----------------------------------------------Events:------------------------------------------------
    @SubscribeEvent
    public void stitcherEventPre(TextureStitchEvent.Pre e) {
        registerParticleTextures(e);
    }

    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        registerParticles();
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }
}