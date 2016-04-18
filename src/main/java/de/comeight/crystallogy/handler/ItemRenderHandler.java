package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.blocks.EnumCrystalColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRenderHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------


	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void registerItemRenderer() {
		registerBasicItemRender(ItemHandler.crystallDust_blue);
		registerBasicItemRender(ItemHandler.crystallDust_green);
		registerBasicItemRender(ItemHandler.crystallDust_red);
		registerBasicItemRender(ItemHandler.crystallDust_yellow);
		registerBasicItemRender(ItemHandler.pureCrystallDust);
		registerBasicItemRender(ItemHandler.vaporizer);
		registerBasicItemRender(ItemHandler.vaporizerDirection);
		registerBasicItemRender(ItemHandler.crystallKnif);
		registerBasicItemRender(ItemHandler.damDust);
		registerBasicItemRender(ItemHandler.drowDust);
		registerBasicItemRender(ItemHandler.fireDust);
		registerBasicItemRender(ItemHandler.poisDust);
		registerBasicItemRender(ItemHandler.hungDust);
		registerBasicItemRender(ItemHandler.crystallHammer_blue);
		registerBasicItemRender(ItemHandler.crystallHammer_green);
		registerBasicItemRender(ItemHandler.crystallHammer_red);
		registerBasicItemRender(ItemHandler.crystallHammer_yellow);
		registerBasicItemRender(ItemHandler.crystalSword_blue);
		registerBasicItemRender(ItemHandler.crystalSword_green);
		registerBasicItemRender(ItemHandler.crystalSword_red);
		registerBasicItemRender(ItemHandler.crystalSword_yellow);
		registerBasicItemRender(ItemHandler.toolRod);
		
		registerItemVariantsRenderer(ItemHandler.crystallHammerHead, 0, "crystallHammerHead_red");
		registerItemVariantsRenderer(ItemHandler.crystallHammerHead, 1, "crystallHammerHead_blue");
		registerItemVariantsRenderer(ItemHandler.crystallHammerHead, 2, "crystallHammerHead_green");
		registerItemVariantsRenderer(ItemHandler.crystallHammerHead, 3, "crystallHammerHead_yellow");
		registerItemVariantsRenderer(ItemHandler.crystallSwordBlade, 0, "crystallSwordBlade_red");
		registerItemVariantsRenderer(ItemHandler.crystallSwordBlade, 1, "crystallSwordBlade_blue");
		registerItemVariantsRenderer(ItemHandler.crystallSwordBlade, 2, "crystallSwordBlade_green");
		registerItemVariantsRenderer(ItemHandler.crystallSwordBlade, 3, "crystallSwordBlade_yellow");
		
		Utilities.addConsoleText("All itemrenderer are registered.");
    }
	
	public static void registerBasicItemRender(Item item) {
		registerBasicItemRender(item, 0 , item.getUnlocalizedName().substring(5));
	}
	
	private static void registerBasicItemRender(Item item, int meta, String filePath){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(CrystallogyBase.MODID + ":" + filePath, "inventory"));
	}
	
	private static void registerItemVariantsRenderer(Item item, int meta, String filePath){
		registerBasicItemRender(item, meta, filePath);
		ModelBakery.registerItemVariants(item, new ResourceLocation(CrystallogyBase.MODID + ":" + filePath));
	}

}
