package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookMain;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookArmorCombiner1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookArmorCombiner2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookBlocks;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCompressor;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystalCharger;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystalCrusher;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystalGlass;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystalLight;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystalOfHolding1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystalOfHolding2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystals;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookCrystorya;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookEntityJar1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookEntityJar2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookFarmersGreen1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookFarmersGreen2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookFireCrystal;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookInfuserBlock1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookInfuserBlock2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookMachineBlock;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookMachines;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookPlayerJar1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookPlayerJar2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.credits.GuiBookCredits;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.infusion.GuiBookInfusionCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmor;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmorCatalyst;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmorPlates1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmorPlates2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalDusts;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookItems;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts10;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts11;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts3;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts4;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts5;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts6;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts7;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts8;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDusts9;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookTools;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.search.GuiBookSearch;
import de.comeight.crystallogy.util.Log;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
	public static GuiBookCrystalGlass CRYSTAL_GLASS_PAGE = new GuiBookCrystalGlass();
	public static GuiBookCrystalLight CRYSTAL_LIGHT_PAGE = new GuiBookCrystalLight();
	public static GuiBookCrystalOfHolding1 CRYSTAL_OF_HOLDING_PAGE_1 = new GuiBookCrystalOfHolding1();
	public static GuiBookCrystalOfHolding2 CRYSTAL_OF_HOLDING_PAGE_2 = new GuiBookCrystalOfHolding2();
	public static GuiBookCrystorya CRYSTORYA_PAGE = new GuiBookCrystorya();
	public static GuiBookEntityJar1 ENTITY_JAR_PAGE_1 = new GuiBookEntityJar1();
	public static GuiBookEntityJar2 ENTITY_JAR_PAGE_2 = new GuiBookEntityJar2();
	public static GuiBookFarmersGreen1 FARMERS_GREEN_PAGE_1 = new GuiBookFarmersGreen1();
	public static GuiBookFarmersGreen2 FARMERS_GREEN_PAGE_2 = new GuiBookFarmersGreen2();
	public static GuiBookFireCrystal FIRE_CRYSTAL_PAGE = new GuiBookFireCrystal();
	public static GuiBookInfuserBlock1 INFUSRER_BLOCK_PAGE_1 = new GuiBookInfuserBlock1();
	public static GuiBookInfuserBlock2 INFUSRER_BLOCK_PAGE_2 = new GuiBookInfuserBlock2();
	public static GuiBookMachineBlock MACHINE_BLOCK_PAGE = new GuiBookMachineBlock();
	public static GuiBookPlayerJar1 PLAYER_JAR_PAGE_1 = new GuiBookPlayerJar1();
	public static GuiBookPlayerJar2 PLAYER_JAR_PAGE_2 = new GuiBookPlayerJar2();
	
	public static GuiBookArmorCombiner1 ARMOR_COMBINER_PAGE_1 = new GuiBookArmorCombiner1();
	public static GuiBookArmorCombiner2 ARMOR_COMBINER_PAGE_2 = new GuiBookArmorCombiner2();
	public static GuiBookCrystalCharger CRYSTAL_CHARGER_PAGE = new GuiBookCrystalCharger();
	public static GuiBookCompressor COMPRESSOR_PAGE = new GuiBookCompressor();
	public static GuiBookCrystalCrusher CRYSTAL_CRUSHER_PAGE = new GuiBookCrystalCrusher();
	
	public static GuiBookArmorCatalyst ARMOR_CATALYST_PAGE = new GuiBookArmorCatalyst();
	public static GuiBookArmorPlates1 ARMOR_PLATE_PAGE_1 = new GuiBookArmorPlates1();;
	public static GuiBookArmorPlates2 ARMOR_PLATE_PAGE_2 = new GuiBookArmorPlates2();
	public static GuiBookThreatDusts1 THREAT_DUSTS_PAGE_1 = new GuiBookThreatDusts1();
	public static GuiBookThreatDusts2 THREAT_DUSTS_PAGE_2 = new GuiBookThreatDusts2();
	public static GuiBookThreatDusts3 THREAT_DUSTS_PAGE_3 = new GuiBookThreatDusts3();
	public static GuiBookThreatDusts4 THREAT_DUSTS_PAGE_4 = new GuiBookThreatDusts4();
	public static GuiBookThreatDusts5 THREAT_DUSTS_PAGE_5 = new GuiBookThreatDusts5();
	public static GuiBookThreatDusts6 THREAT_DUSTS_PAGE_6 = new GuiBookThreatDusts6();
	public static GuiBookThreatDusts7 THREAT_DUSTS_PAGE_7 = new GuiBookThreatDusts7();
	public static GuiBookThreatDusts8 THREAT_DUSTS_PAGE_8 = new GuiBookThreatDusts8();
	public static GuiBookThreatDusts9 THREAT_DUSTS_PAGE_9 = new GuiBookThreatDusts9();
	public static GuiBookThreatDusts10 THREAT_DUSTS_PAGE_10 = new GuiBookThreatDusts10();
	public static GuiBookThreatDusts11 THREAT_DUSTS_PAGE_11 = new GuiBookThreatDusts11();
	
	public static GuiBookCrystalOfHolding1 ENERGY_DUST_PAGE;
	public static GuiBookCrystalOfHolding1 ENERGY_CRYSTAL_PAGE;
	public static GuiBookCrystalOfHolding1 ARMOR_COMBINED_PAGE;
	public static GuiBookCrystalOfHolding1 ENTITY_GRABBER_PAGE;
	public static GuiBookCrystalOfHolding1 FERTILIZER_POTATO_PAGE;
	public static GuiBookCrystalOfHolding1 ENTITY_CRYSTAL_KNIFE_PAGE;
	public static GuiBookCrystalOfHolding1 PLAYER_CRYSTAL_KNIFE_PAGE;
	public static GuiBookCrystalOfHolding1 PURE_CRYTAL_DUST_PAGE;
	
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
		Log.info("Started loading Book of Knowledge...");
		
		//Chapters:
		registerPage(MAIN_PAGE);
		registerPage(BLOCKS_PAGE);
		registerPage(ITEMS_PAGE);
		registerPage(MACHINES_PAGE);
		registerPage(TOOLS_PAGE);
		registerPage(ARMOR_PAGE);
		registerPage(SEARCH_PAGE);
		registerPage(CREDITS_PAGE);
		registerPage(INFUSION_CRAFTING_PAGE);
		registerPage(CRYSTORYA_PAGE);
		
		//Blocks:
		registerPage(CRYSTALS_PAGE);
		registerPage(CRYSTAL_DUST_PAGE);
		registerPage(CRYSTAL_GLASS_PAGE);
		registerPage(CRYSTAL_LIGHT_PAGE);
		registerPage(CRYSTAL_OF_HOLDING_PAGE_1);
		registerPage(CRYSTAL_OF_HOLDING_PAGE_2);
		registerPage(CRYSTORYA_PAGE);
		registerPage(ENTITY_JAR_PAGE_1);
		registerPage(ENTITY_JAR_PAGE_2);
		registerPage(FARMERS_GREEN_PAGE_1);
		registerPage(FARMERS_GREEN_PAGE_2);
		registerPage(FIRE_CRYSTAL_PAGE);
		registerPage(INFUSRER_BLOCK_PAGE_1);
		registerPage(INFUSRER_BLOCK_PAGE_2);
		registerPage(MACHINE_BLOCK_PAGE);
		registerPage(PLAYER_JAR_PAGE_1);
		registerPage(PLAYER_JAR_PAGE_2);
		
		//Machines:
		registerPage(ARMOR_COMBINER_PAGE_1);
		registerPage(ARMOR_COMBINER_PAGE_2);
		registerPage(CRYSTAL_CHARGER_PAGE);
		registerPage(COMPRESSOR_PAGE);
		registerPage(CRYSTAL_CRUSHER_PAGE);
		
		//Items:
		registerPage(ARMOR_CATALYST_PAGE);
		registerPage(ARMOR_PLATE_PAGE_1);
		registerPage(ARMOR_PLATE_PAGE_2);
		registerPage(THREAT_DUSTS_PAGE_1);
		registerPage(THREAT_DUSTS_PAGE_2);
		registerPage(THREAT_DUSTS_PAGE_3);
		registerPage(THREAT_DUSTS_PAGE_4);
		registerPage(THREAT_DUSTS_PAGE_5);
		registerPage(THREAT_DUSTS_PAGE_6);
		registerPage(THREAT_DUSTS_PAGE_7);
		registerPage(THREAT_DUSTS_PAGE_8);
		registerPage(THREAT_DUSTS_PAGE_9);
		registerPage(THREAT_DUSTS_PAGE_10);
		registerPage(THREAT_DUSTS_PAGE_11);
		
		Log.info("Finished loading Book of Knowledge");
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
