package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookMain;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookBlocks;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystals;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookMachines;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.credits.GuiBookCredits;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.infusion.GuiBookInfusionCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmor;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalDusts;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookItems;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookTools;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.search.GuiBookSearch;
import net.minecraft.client.Minecraft;

public class PageRegistry {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static LinkedList<PageRegistryEntry> list = new LinkedList<PageRegistryEntry>();
	private static LinkedList<Integer> course = new LinkedList<Integer>();
	private static int lastVisited = 0;
	private static GuiBookPage currentPage;
	
	private static int ID = 0;
	
	//Pages:
	public static GuiBookMain MAIN_PAGE = new GuiBookMain();
	public static GuiBookBlocks BLOCKS_PAGE = new GuiBookBlocks();
	public static GuiBookItems ITEMS_PAGE = new GuiBookItems();
	public static GuiBookMachines MACHINES_PAGE = new GuiBookMachines();
	public static GuiBookTools TOOLS_PAGE = new GuiBookTools();
	public static GuiBookArmor ARMOR_PAGE = new GuiBookArmor();
	public static GuiBookSearch SEARCH_PAGE = new GuiBookSearch();
	public static GuiBookCredits CREDITS_PAGE = new GuiBookCredits();
	public static GuiBookInfusionCrafting INFUSION_CRAFTING_PAGE = new GuiBookInfusionCrafting();
	
	public static GuiBookCrystalDusts CRYSTAL_DUST_PAGE = new GuiBookCrystalDusts();
	public static GuiBookCrystals CRYSTALS_PAGE = new GuiBookCrystals();
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static GuiBookPage getLastVisited(){
		if(!canGoBack()){
			return null;
		}
		int i = lastVisited;
		lastVisited = course.removeLast();
		return list.get(i).PAGE;
	}
	
	public static void setCurrentPage(GuiBookPage currentPage) {
		PageRegistry.currentPage = currentPage;
	}
	
	public static GuiBookPage getCurrentPage() {
		return currentPage;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void registerAllPages(){
		registerPage(MAIN_PAGE);
		registerPage(BLOCKS_PAGE);
		registerPage(ITEMS_PAGE);
		registerPage(MACHINES_PAGE);
		registerPage(TOOLS_PAGE);
		registerPage(ARMOR_PAGE);
		registerPage(SEARCH_PAGE);
		registerPage(CREDITS_PAGE);
		registerPage(INFUSION_CRAFTING_PAGE);
		registerPage(CRYSTALS_PAGE);
		registerPage(CRYSTAL_DUST_PAGE);
	}
	
	private static void registerPage(GuiBookPage page){
		list.add(new PageRegistryEntry(page, ID));
		ID++;
	}
	
	public static void addCourse(GuiBookPage page){
		if(page == null){
			return;
		}
		for (PageRegistryEntry pageRegistryEntry : list) {
			if(page.getClass() == pageRegistryEntry.PAGE.getClass()){
				course.add(lastVisited);
				lastVisited = pageRegistryEntry.ID;
				return;
			}
		}
	}
	
	public static boolean canGoBack(){
		return course.size() > 0;
	}
	
	public static void openPage(Minecraft mc, GuiBookPage fromPage, GuiBookPage toPage){
		PageRegistry.addCourse(fromPage);
		mc.displayGuiScreen(toPage);
		toPage.onGuiOpened();
	}
	
}
