package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ItemRenderHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------


	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void registerItemRenderer() {
		registerItemRender(ItemHandler.crystallDust_blue);
		registerItemRender(ItemHandler.crystallDust_green);
		registerItemRender(ItemHandler.crystallDust_red);
		registerItemRender(ItemHandler.crystallDust_yellow);
		registerItemRender(ItemHandler.pureCrystallDust);
		registerItemRender(ItemHandler.vaporizer);
		registerItemRender(ItemHandler.vaporizerDirection);
		registerItemRender(ItemHandler.crystallKnif);
		
		Utilities.addConsoleText("All itemrenderer are registered.");
    }
	
	public static void registerItemBlockRenderer() {

	}
	
	public static void registerItemRender(Item item) {
		ModelResourceLocation iMRL = new ModelResourceLocation(CrystallogyBase.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory");
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, iMRL);
	}

}
