package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseInfusionPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookThreatDusts11 extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookThreatDusts11() {
		super(new ItemStack(ItemHandler.poisDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting f = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.SPIDER_EYE), null);
		f.disableFrame();
		BookButtonCrafting g = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_green), PageRegistry.CRYSTAL_DUST_PAGE);
		g.disableFrame();
		BookButtonCrafting y = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_yellow), PageRegistry.CRYSTAL_DUST_PAGE);
		y.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{f, y, g, g, g};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.poisDust), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Gives the target the Poison Potion Effect for 15 seconds.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
	
}
