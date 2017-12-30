package de.comeight.crystallogy.client.renderer.entities;

import de.comeight.crystallogy.entities.EntityBouncyCrystal;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RendererEntityBouncyCrystalFactory implements IRenderFactory<EntityBouncyCrystal> {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public Render<? super EntityBouncyCrystal> createRenderFor(RenderManager manager) {
        return new RenderSnowball<>(manager, ItemHandler.BOUNCY_CRYSTAL, Minecraft.getMinecraft().getRenderItem());
    }

    //-----------------------------------------------Events:------------------------------------------------

}