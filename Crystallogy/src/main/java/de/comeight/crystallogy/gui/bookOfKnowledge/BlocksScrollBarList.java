package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlocksScrollBarList extends ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BlocksScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		addAllEntrys();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void addAllEntrys(){
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystall_red), 
																									new ItemStack(BlockHandler.crystall_blue),
																									new ItemStack(BlockHandler.crystall_green),
																									new ItemStack(BlockHandler.crystall_yellow)}, 1000, PageRegistry.CRYSTALS_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystalGlas, 1, 0),
																									new ItemStack(BlockHandler.crystalGlas, 1, 1),
																									new ItemStack(BlockHandler.crystalGlas, 1, 2),
																									new ItemStack(BlockHandler.crystalGlas, 1, 3),}, 1000, PageRegistry.CRYSTAL_GLASS_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystalLight), PageRegistry.CRYSTAL_LIGHT_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystalOfHolding), PageRegistry.CRYSTAL_OF_HOLDING_PAGE_1));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystorya), PageRegistry.CRYSTORYA_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.entityJar), PageRegistry.ENTITY_JAR_PAGE_1));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.farmersGreen), PageRegistry.FARMERS_GREEN_PAGE_1));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.fireCrystall), PageRegistry.FIRE_CRYSTAL_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSRER_BLOCK_PAGE_1));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.machineBlock), PageRegistry.MACHINE_BLOCK_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.playerJar), PageRegistry.PLAYER_JAR_PAGE_1));
	}
	
}
