package de.comeight.crystallogy.gui.bookOfKnowledge.pages.mechanisms;

import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookMechanisms extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookMechanisms() {
		super("Mechanisms:");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.SEARCH_PAGE;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		float buttonScale = 1.0F;
		
		//Infusion Crafting:
		BookButtonCategory infusion = new BookButtonCategory(GuiBookPage.getNextButtonId(), BORDER_LEFT, 40, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE_1);
		infusion.setCustomDescription("Infusion Crafting");
		infusion.setScale(buttonScale);
		buttonList.add(infusion);
		
		//Custom Entity Ai:
		BookButtonCategory ai = new BookButtonCategory(GuiBookPage.getNextButtonId(), BORDER_LEFT, 80, null, new ItemStack(ItemHandler.entityBrain), PageRegistry.BLUE_CRYSTAL_ARMOR_PAGE_1);
		ai.setCustomDescription("Custom Entity Ai");
		ai.setScale(buttonScale);
		buttonList.add(ai);
	}
	
}
