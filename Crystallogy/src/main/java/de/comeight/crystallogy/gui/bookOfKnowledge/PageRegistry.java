package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookMain;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
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
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmor;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmorCatalyst;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmorPlates1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookArmorPlates2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookBlueCrystalArmor1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookBlueCrystalArmor2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookChargedCombinedArmorMesh;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCombinedArmor1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCombinedArmor2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCombinedArmorCompound;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCombinedArmorMesh;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalDusts;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalHammer1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalHammer2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalHammerHead;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalKnifeBlade;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalPickaxe1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalPickaxe2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalPickaxeHead;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalSword1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalSword2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookCrystalSwordBlade;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookDebugTool;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookEnderonCrystal;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookEnergyCrystal;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookEnergyDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookEntityCrystalKnife1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookEntityCrystalKnife2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookEntityGrabber;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookFertilizerPotato;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookGreenCrystalArmor1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookGreenCrystalArmor2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookHunterArmorCompound;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookHuntersArmorMesh;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookHuntersDreamArmor1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookHuntersDreamArmor2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookItems;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookMagicStoneOfForgetfulness;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookPlayerCrystalKnife1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookPlayerCrystalKnife2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookPureCrystalDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookRedCrystalArmor1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookRedCrystalArmor2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsAiDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsBadLuck;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsBlindDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsDamDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsDrowDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsEnderDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsFireDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsGlowDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsHungDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsLevDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsMainPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookThreatDustsPoisDust;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookToolRod;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookTools;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookYellowCrystalArmor1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookYellowCrystalArmor2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.mechanisms.GuiBookInfusionCrafting1;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.mechanisms.GuiBookInfusionCrafting2;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.mechanisms.GuiBookMechanisms;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.search.GuiBookSearch;
import de.comeight.crystallogy.util.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	
	private static ItemStack book;
	
	//Pages:
	public static GuiBookMain MAIN_PAGE = new GuiBookMain();
	public static GuiBookBlocks BLOCKS_PAGE = new GuiBookBlocks();
	public static GuiBookItems ITEMS_PAGE = new GuiBookItems();
	public static GuiBookMachines MACHINES_PAGE = new GuiBookMachines();
	public static GuiBookTools TOOLS_PAGE = new GuiBookTools();
	public static GuiBookArmor ARMOR_PAGE = new GuiBookArmor();
	public static GuiBookSearch SEARCH_PAGE = new GuiBookSearch();
	public static GuiBookCredits CREDITS_PAGE = new GuiBookCredits();
	public static GuiBookMechanisms MECHANISMS_PAGE = new GuiBookMechanisms();
	
	public static GuiBookInfusionCrafting1 INFUSION_CRAFTING_PAGE_1 = new GuiBookInfusionCrafting1();
	public static GuiBookInfusionCrafting2 INFUSION_CRAFTING_PAGE_2 = new GuiBookInfusionCrafting2();
	
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
	public static GuiBookThreatDustsMainPage THREAT_DUSTS_MAIN_PAGE = new GuiBookThreatDustsMainPage();
	public static GuiBookThreatDustsAiDust THREAT_DUSTS_AI_DUST_PAGE = new GuiBookThreatDustsAiDust();
	public static GuiBookThreatDustsBadLuck THREAT_DUSTS_BAD_LUCK_PAGE = new GuiBookThreatDustsBadLuck();
	public static GuiBookThreatDustsBlindDust THREAT_DUSTS_BLIND_DUST_PAGE = new GuiBookThreatDustsBlindDust();
	public static GuiBookThreatDustsDamDust THREAT_DUSTS_DAM_DUST_PAGE = new GuiBookThreatDustsDamDust();
	public static GuiBookThreatDustsDrowDust THREAT_DUSTS_DROW_DUST_PAGE = new GuiBookThreatDustsDrowDust();
	public static GuiBookThreatDustsEnderDust THREAT_DUSTS_ENDER_DUST_PAGE = new GuiBookThreatDustsEnderDust();
	public static GuiBookThreatDustsFireDust THREAT_DUSTS_FIRE_DUST_PAGE = new GuiBookThreatDustsFireDust();
	public static GuiBookThreatDustsGlowDust THREAT_DUSTS_GLOW_DUST_PAGE = new GuiBookThreatDustsGlowDust();
	public static GuiBookThreatDustsHungDust THREAT_DUSTS_HUNG_DUST_PAGE = new GuiBookThreatDustsHungDust();
	public static GuiBookThreatDustsLevDust THREAT_DUSTS_LEV_DUST_PAGE = new GuiBookThreatDustsLevDust();
	public static GuiBookThreatDustsPoisDust THREAT_DUSTS_POIS_DUST_PAGE = new GuiBookThreatDustsPoisDust();
	public static GuiBookChargedCombinedArmorMesh CHARGED_COMBINED_ARMOR_MESH_PAGE = new GuiBookChargedCombinedArmorMesh();
	public static GuiBookCrystalDusts CRYSTAL_DUST_PAGE = new GuiBookCrystalDusts();
	public static GuiBookCombinedArmorCompound COMBINED_ARMOR_COMPOUND_PAGE = new GuiBookCombinedArmorCompound();
	public static GuiBookCombinedArmorMesh COMBINED_ARMOR_MESH_PAGE = new GuiBookCombinedArmorMesh();
	public static GuiBookCrystalKnifeBlade CRYSTAL_KNIFE_BLADE_PAGE = new GuiBookCrystalKnifeBlade();
	public static GuiBookCrystalHammerHead CRYSTAL_HAMMER_HEAD_PAGE = new GuiBookCrystalHammerHead();
	public static GuiBookCrystalSwordBlade CRYSTAL_SWORD_BLADE_PAGE = new GuiBookCrystalSwordBlade();
	public static GuiBookCrystalPickaxeHead CRYSTAL_PICKAXE_HEAD_PAGE = new GuiBookCrystalPickaxeHead();
	public static GuiBookEnderonCrystal ENDERON_CRYSTAL_PAGE = new GuiBookEnderonCrystal();
	public static GuiBookEnergyCrystal ENERGY_CRYSTAL_PAGE = new GuiBookEnergyCrystal();
	public static GuiBookEnergyDust ENERGY_DUST_PAGE = new GuiBookEnergyDust();
	public static GuiBookFertilizerPotato FERTILIZER_POTATO_PAGE = new GuiBookFertilizerPotato();
	public static GuiBookHunterArmorCompound HUNTERS_ARMOR_COMPOUND_PAGE = new GuiBookHunterArmorCompound();
	public static GuiBookHuntersArmorMesh HUNTERS_ARMOR_MESH_PAGE = new GuiBookHuntersArmorMesh();
	public static GuiBookMagicStoneOfForgetfulness MAGIC_STONE_OF_FORGETFULNESS = new GuiBookMagicStoneOfForgetfulness();
	public static GuiBookPureCrystalDust PURE_CRYSTAL_DUST_PAGE = new GuiBookPureCrystalDust();
	public static GuiBookToolRod TOOL_ROD_PAGE = new GuiBookToolRod();
	
	public static GuiBookEntityCrystalKnife1 ENTITY_CRYSTAL_KNIFE_PAGE_1 = new GuiBookEntityCrystalKnife1();
	public static GuiBookEntityCrystalKnife2 ENTITY_CRYSTAL_KNIFE_PAGE_2 = new GuiBookEntityCrystalKnife2();
	public static GuiBookEntityGrabber ENTITY_GRABBER_PAGE = new GuiBookEntityGrabber();
	public static GuiBookCrystalHammer1 CRYSTAL_HAMMER_PAGE_1 = new GuiBookCrystalHammer1();
	public static GuiBookCrystalHammer2 CRYSTAL_HAMMER_PAGE_2 = new GuiBookCrystalHammer2();
	public static GuiBookCrystalPickaxe1 CRYSTAL_PICKAXE_PAGE_1 = new GuiBookCrystalPickaxe1();
	public static GuiBookCrystalPickaxe2 CRYSTAL_PICKAXE_PAGE_2 = new GuiBookCrystalPickaxe2();
	public static GuiBookCrystalSword1 CRYSTAL_SWORD_PAGE_1 = new GuiBookCrystalSword1();
	public static GuiBookCrystalSword2 CRYSTAL_SWORD_PAGE_2 = new GuiBookCrystalSword2();
	public static GuiBookDebugTool DEBUG_TOOL_PAGE = new GuiBookDebugTool();
	public static GuiBookPlayerCrystalKnife1 PLAYER_CRYSTAL_KNIFE_PAGE_1 = new GuiBookPlayerCrystalKnife1();
	public static GuiBookPlayerCrystalKnife2 PLAYER_CRYSTAL_KNIFE_PAGE_2 = new GuiBookPlayerCrystalKnife2();
	
	public static GuiBookBlueCrystalArmor1 BLUE_CRYSTAL_ARMOR_PAGE_1 = new GuiBookBlueCrystalArmor1();
	public static GuiBookBlueCrystalArmor2 BLUE_CRYSTAL_ARMOR_PAGE_2 = new GuiBookBlueCrystalArmor2();
	public static GuiBookRedCrystalArmor1 RED_CRYSTAL_ARMOR_PAGE_1 = new GuiBookRedCrystalArmor1();
	public static GuiBookRedCrystalArmor2 RED_CRYSTAL_ARMOR_PAGE_2 = new GuiBookRedCrystalArmor2();
	public static GuiBookGreenCrystalArmor1 GREEN_CRYSTAL_ARMOR_PAGE_1 = new GuiBookGreenCrystalArmor1();
	public static GuiBookGreenCrystalArmor2 GREEN_CRYSTAL_ARMOR_PAGE_2 = new GuiBookGreenCrystalArmor2();
	public static GuiBookYellowCrystalArmor1 YELLOW_CRYSTAL_ARMOR_PAGE_1 = new GuiBookYellowCrystalArmor1();
	public static GuiBookYellowCrystalArmor2 YELLOW_CRYSTAL_ARMOR_PAGE_2 = new GuiBookYellowCrystalArmor2();
	
	public static GuiBookCombinedArmor1 COMBINED_ARMOR_PAGE_1 = new GuiBookCombinedArmor1();
	public static GuiBookCombinedArmor2 COMBINED_ARMOR_PAGE_2 = new GuiBookCombinedArmor2();
	public static GuiBookHuntersDreamArmor1 HUNTERS_ARMOR_PAGE_1 = new GuiBookHuntersDreamArmor1();
	public static GuiBookHuntersDreamArmor2 HUNTERS_ARMOR_PAGE_2 = new GuiBookHuntersDreamArmor2();
	
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
	
	public static void setBook(ItemStack stack){
		book = stack;
	}
	
	public static void removeBook(){
		setBook(null);
	}
	
	public static GuiBookPage getClipedPage(){
		GuiBookPage page = null;
		if(book != null && book.hasTagCompound()){
			NBTTagCompound compound = book.getTagCompound();
			if(compound.hasKey("clipedPageId")){
				int id = compound.getInteger("clipedPageId");
				compound.removeTag("clipedPageId");
				page = list.get(id).PAGE;
			}
		}
		return page;
	}
	
	public static int getIdFormPage(GuiBookPage page){
		for (PageRegistryEntry pageRegistryEntry : list) {
			if(page.getClass() == pageRegistryEntry.PAGE.getClass()){
				return pageRegistryEntry.ID;
			}
		}
		
		return -1;
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
		registerPage(MECHANISMS_PAGE);
		
		//Blocks:
		registerPage(CRYSTALS_PAGE);
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
		registerPage(THREAT_DUSTS_MAIN_PAGE);
		registerPage(THREAT_DUSTS_AI_DUST_PAGE);
		registerPage(THREAT_DUSTS_BAD_LUCK_PAGE);
		registerPage(THREAT_DUSTS_BLIND_DUST_PAGE);
		registerPage(THREAT_DUSTS_DAM_DUST_PAGE);
		registerPage(THREAT_DUSTS_DROW_DUST_PAGE);
		registerPage(THREAT_DUSTS_ENDER_DUST_PAGE);
		registerPage(THREAT_DUSTS_FIRE_DUST_PAGE);
		registerPage(THREAT_DUSTS_GLOW_DUST_PAGE);
		registerPage(THREAT_DUSTS_HUNG_DUST_PAGE);
		registerPage(THREAT_DUSTS_LEV_DUST_PAGE);
		registerPage(THREAT_DUSTS_POIS_DUST_PAGE);
		registerPage(CHARGED_COMBINED_ARMOR_MESH_PAGE);
		registerPage(CRYSTAL_DUST_PAGE);
		registerPage(COMBINED_ARMOR_COMPOUND_PAGE);
		registerPage(COMBINED_ARMOR_MESH_PAGE);
		registerPage(CRYSTAL_KNIFE_BLADE_PAGE);
		registerPage(CRYSTAL_HAMMER_HEAD_PAGE);
		registerPage(CRYSTAL_SWORD_BLADE_PAGE);
		registerPage(CRYSTAL_PICKAXE_HEAD_PAGE);
		registerPage(ENDERON_CRYSTAL_PAGE);
		registerPage(ENERGY_CRYSTAL_PAGE);
		registerPage(ENERGY_DUST_PAGE);
		registerPage(FERTILIZER_POTATO_PAGE);
		registerPage(HUNTERS_ARMOR_COMPOUND_PAGE);
		registerPage(HUNTERS_ARMOR_MESH_PAGE);
		registerPage(MAGIC_STONE_OF_FORGETFULNESS);
		registerPage(PURE_CRYSTAL_DUST_PAGE);
		registerPage(TOOL_ROD_PAGE);
		
		//Tools:
		registerPage(ENTITY_CRYSTAL_KNIFE_PAGE_1);
		registerPage(ENTITY_CRYSTAL_KNIFE_PAGE_2);
		registerPage(ENTITY_GRABBER_PAGE);
		registerPage(CRYSTAL_HAMMER_PAGE_1);
		registerPage(CRYSTAL_HAMMER_PAGE_2);
		registerPage(CRYSTAL_PICKAXE_PAGE_1);
		registerPage(CRYSTAL_PICKAXE_PAGE_2);
		registerPage(CRYSTAL_SWORD_PAGE_1);
		registerPage(CRYSTAL_SWORD_PAGE_2);
		
		registerPage(DEBUG_TOOL_PAGE);
		registerPage(PLAYER_CRYSTAL_KNIFE_PAGE_1);
		registerPage(PLAYER_CRYSTAL_KNIFE_PAGE_2);
		
		//Armor:
		registerPage(BLUE_CRYSTAL_ARMOR_PAGE_1);
		registerPage(BLUE_CRYSTAL_ARMOR_PAGE_2);
		registerPage(RED_CRYSTAL_ARMOR_PAGE_1);
		registerPage(RED_CRYSTAL_ARMOR_PAGE_2);
		registerPage(GREEN_CRYSTAL_ARMOR_PAGE_1);
		registerPage(GREEN_CRYSTAL_ARMOR_PAGE_2);
		registerPage(YELLOW_CRYSTAL_ARMOR_PAGE_1);
		registerPage(YELLOW_CRYSTAL_ARMOR_PAGE_2);
		registerPage(COMBINED_ARMOR_PAGE_1);
		registerPage(COMBINED_ARMOR_PAGE_2);
		registerPage(HUNTERS_ARMOR_PAGE_1);
		registerPage(HUNTERS_ARMOR_PAGE_2);
		
		//Mechanisms:
		registerPage(INFUSION_CRAFTING_PAGE_1);
		registerPage(INFUSION_CRAFTING_PAGE_2);
		
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
		
		int id = getIdFormPage(page);
		if(id != -1){
			course.add(lastVisited);
			lastVisited = id;
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
	
	public static void clipToBook(GuiBookPage page){
		if(book != null){
			if(!book.hasTagCompound()){
				book.setTagCompound(new NBTTagCompound());
			}
			int id = getIdFormPage(page);
			if(id != -1){
				book.getTagCompound().setInteger("clipedPageId", id);
			}
		}
	}
	
}
