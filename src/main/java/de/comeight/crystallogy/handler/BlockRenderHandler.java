package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class BlockRenderHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------


	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void registerBlockRenderer() {
		registerBlockRenderer(BlockHandler.crystall_blue);
		registerBlockRenderer(BlockHandler.crystall_green);
		registerBlockRenderer(BlockHandler.crystall_red);
		registerBlockRenderer(BlockHandler.crystall_yellow);
		registerBlockRenderer(BlockHandler.crystallCrusher);
		registerBlockRenderer(BlockHandler.infuserBlock);
		registerBlockRenderer(BlockHandler.playerJar);
    }
	
	public static void registerBlockRenderer(Block block) {
		Item item = Item.getItemFromBlock(block);
		ModelResourceLocation iMRL = new ModelResourceLocation(CrystallogyBase.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory");
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, iMRL);
		System.out.println("Crystallogy: \"" + block.getUnlocalizedName().substring(5) + "\" wurde gerendert.");
	}
}
