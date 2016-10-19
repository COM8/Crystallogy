package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseInfusionPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookThreatDustsBlindDust extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookThreatDustsBlindDust() {
		super(new ItemStack(ItemHandler.blindDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting h = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.IRON_HELMET), null);
		h.disableFrame();
		BookButtonCrafting o = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.OBSIDIAN), null);
		o.disableFrame();
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_blue), PageRegistry.CRYSTAL_DUST_PAGE);
		b.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{h, o, b, b, b};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.blindDust), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Gives the target the Blindness Potion Effect for 15 seconds.";
	}
	
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.THREAT_DUSTS_DAM_DUST_PAGE;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
