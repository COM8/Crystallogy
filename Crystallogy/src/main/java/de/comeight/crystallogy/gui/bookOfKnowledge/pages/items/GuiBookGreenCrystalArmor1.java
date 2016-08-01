package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookGreenCrystalArmor1 extends GuiBookBaseArmorPage1 {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/items/armor_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookGreenCrystalArmor1() {
		super("Green Crystal Armor:");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.GREEN_CRYSTAL_ARMOR_PAGE_2;
	}
	
	@Override
	protected ItemArmor getHelmet() {
		return ItemHandler.armorHelmet_green;
	}

	@Override
	protected ItemArmor getChestplate() {
		return ItemHandler.armorChestplate_green;
	}

	@Override
	protected ItemArmor getLeggins() {
		return ItemHandler.armorLeggins_green;
	}

	@Override
	protected ItemArmor getBoots() {
		return ItemHandler.armorBoots_green;
	}

	@Override
	protected String getDescription() {
		return "The Green Crystal Armor, if completely equipped, gives you a 20% Speed Boost.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void drawImages(){
		float scale = 0.65F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, 0);
		GlStateManager.scale(scale, scale / 0.8F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 0, 250, 250, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
}
