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
public class GuiBookThreatDusts6 extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookThreatDusts6() {
		super(new ItemStack(ItemHandler.enderDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting e = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.ENDER_EYE), null);
		e.disableFrame();
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.ENDER_PEARL), null);
		p.disableFrame();
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_blue), PageRegistry.CRYSTAL_DUST_PAGE);
		b.disableFrame();
		BookButtonCrafting r = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red), PageRegistry.CRYSTAL_DUST_PAGE);
		r.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{e, p, r, b, b};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.enderDust), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Teleports the target 5 times a couple blocks away.";
	}
	
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.THREAT_DUSTS_PAGE_7;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
