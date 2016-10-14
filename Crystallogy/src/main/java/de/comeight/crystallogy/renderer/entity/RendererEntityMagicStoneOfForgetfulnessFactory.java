package de.comeight.crystallogy.renderer.entity;

import de.comeight.crystallogy.entity.EntityMagicStoneOfForgetfulness;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RendererEntityMagicStoneOfForgetfulnessFactory implements IRenderFactory<EntityMagicStoneOfForgetfulness> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public RendererEntityMagicStoneOfForgetfulnessFactory() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public Render<? super EntityMagicStoneOfForgetfulness> createRenderFor(RenderManager manager) {
		return new RenderSnowball<EntityMagicStoneOfForgetfulness>(manager, ItemHandler.magicStoneOfForgetfulness, Minecraft.getMinecraft().getRenderItem());
	}
	
}
