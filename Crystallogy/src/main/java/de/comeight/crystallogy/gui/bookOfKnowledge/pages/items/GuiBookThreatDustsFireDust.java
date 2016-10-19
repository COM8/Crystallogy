package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseInfusionPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookThreatDustsFireDust extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookThreatDustsFireDust() {
		super(new ItemStack(ItemHandler.fireDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.BLAZE_POWDER), null);
		b.disableFrame();
		BookButtonCrafting r = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red), PageRegistry.CRYSTAL_DUST_PAGE);
		r.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{b, r, r, r, r};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.fireDust), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Let's the target burn for 7 seconds.";
	}
	
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.THREAT_DUSTS_GLOW_DUST_PAGE;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
