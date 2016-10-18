package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ConfigHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AllScrollBarList extends ScrollBarListSearch {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public AllScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		addAllEntrys();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void addAllEntrys(){
		//Blocks:
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
		
		//Machines:
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.armorCombiner), PageRegistry.ARMOR_COMBINER_PAGE_1));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.charger), PageRegistry.CRYSTAL_CHARGER_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.compressor), PageRegistry.COMPRESSOR_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystallCrusher), PageRegistry.CRYSTAL_CRUSHER_PAGE));
	
		//Items:
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.armorCatalys), PageRegistry.ARMOR_CATALYST_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0),
																									new ItemStack(ItemHandler.armorPlate, 1, 1),
																									new ItemStack(ItemHandler.armorPlate, 1, 2),
																									new ItemStack(ItemHandler.armorPlate, 1, 3),
																									new ItemStack(ItemHandler.armorPlate, 1, 4),}, 1000, PageRegistry.ARMOR_PLATE_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.badLuckDust), 
																									new ItemStack(ItemHandler.blindDust), 
																									new ItemStack(ItemHandler.damDust), 
																									new ItemStack(ItemHandler.drowDust), 
																									new ItemStack(ItemHandler.enderDust), 
																									new ItemStack(ItemHandler.fireDust), 
																									new ItemStack(ItemHandler.glowDust), 
																									new ItemStack(ItemHandler.hungDust), 
																									new ItemStack(ItemHandler.levDust), 
																									new ItemStack(ItemHandler.poisDust)}, 1000, PageRegistry.THREAT_DUSTS_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.chargedCombinedArmorMesh), PageRegistry.CHARGED_COMBINED_ARMOR_MESH_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																									new ItemStack(ItemHandler.crystallDust_blue), 
																									new ItemStack(ItemHandler.crystallDust_green), 
																									new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorCompound), PageRegistry.COMBINED_ARMOR_COMPOUND_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorMesh), PageRegistry.COMBINED_ARMOR_MESH_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.crystalKnifeBlade), PageRegistry.CRYSTAL_KNIFE_BLADE_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallHammerHead, 1, 0),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 1),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 2),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 3),}, 1000, PageRegistry.CRYSTAL_HAMMER_HEAD_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallSwordBlade, 1, 0),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 1),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 2),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 3),}, 1000, PageRegistry.CRYSTAL_SWORD_BLADE_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxeHead, 1, 0),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 1),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 2),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 3),}, 1000, PageRegistry.CRYSTAL_PICKAXE_HEAD_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.enderonCrystal), PageRegistry.ENDERON_CRYSTAL_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyCrystal), PageRegistry.ENERGY_CRYSTAL_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyDust), PageRegistry.ENERGY_DUST_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.fertilizerPotato), PageRegistry.FERTILIZER_POTATO_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.hunterArmorCompound), PageRegistry.HUNTERS_ARMOR_COMPOUND_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.hunterArmorMesh), PageRegistry.HUNTERS_ARMOR_MESH_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.toolRod), PageRegistry.TOOL_ROD_PAGE));

		//Tools:
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.entityCrystalKnife), PageRegistry.ENTITY_CRYSTAL_KNIFE_PAGE_1));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.entityGrabber), PageRegistry.ENTITY_GRABBER_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallHammer_red), 
																									new ItemStack(ItemHandler.crystallHammer_blue), 
																									new ItemStack(ItemHandler.crystallHammer_green), 
																									new ItemStack(ItemHandler.crystallHammer_yellow)}, 1000, PageRegistry.CRYSTAL_HAMMER_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalSword_red), 
																									new ItemStack(ItemHandler.crystalSword_blue), 
																									new ItemStack(ItemHandler.crystalSword_green), 
																									new ItemStack(ItemHandler.crystalSword_yellow)}, 1000, PageRegistry.CRYSTAL_SWORD_PAGE_1));

		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxe_red), 
																									new ItemStack(ItemHandler.crystalPickaxe_blue), 
																									new ItemStack(ItemHandler.crystalPickaxe_green), 
																									new ItemStack(ItemHandler.crystalPickaxe_yellow)}, 1000, PageRegistry.CRYSTAL_PICKAXE_PAGE_1));
		
		if(ConfigHandler.enableDebugTool){
			addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.debugTool), PageRegistry.DEBUG_TOOL_PAGE));
		}
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.playerCrystalKnife), PageRegistry.PLAYER_CRYSTAL_KNIFE_PAGE_1));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.vaporizer), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.vaporizerDirection), null));
		
		//Armor:
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_blue), 
																									new ItemStack(ItemHandler.armorChestplate_blue), 
																									new ItemStack(ItemHandler.armorLeggins_blue), 
																									new ItemStack(ItemHandler.armorBoots_blue)}, 1000, PageRegistry.BLUE_CRYSTAL_ARMOR_PAGE_1));

		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_red), 
																									new ItemStack(ItemHandler.armorChestplate_red), 
																									new ItemStack(ItemHandler.armorLeggins_red), 
																									new ItemStack(ItemHandler.armorBoots_red)}, 1000, PageRegistry.RED_CRYSTAL_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_green), 
																									new ItemStack(ItemHandler.armorChestplate_green), 
																									new ItemStack(ItemHandler.armorLeggins_green), 
																									new ItemStack(ItemHandler.armorBoots_green)}, 1000, PageRegistry.GREEN_CRYSTAL_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_yellow), 
																									new ItemStack(ItemHandler.armorChestplate_yellow), 
																									new ItemStack(ItemHandler.armorLeggins_yellow), 
																									new ItemStack(ItemHandler.armorBoots_yellow)}, 1000, PageRegistry.YELLOW_CRYSTAL_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_combined), 
																									new ItemStack(ItemHandler.armorChestplate_combined), 
																									new ItemStack(ItemHandler.armorLeggins_combined), 
																									new ItemStack(ItemHandler.armorBoots_combined)}, 1000, PageRegistry.COMBINED_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_hunter), 
																									new ItemStack(ItemHandler.armorChestplate_hunter), 
																									new ItemStack(ItemHandler.armorLeggins_hunter), 
																									new ItemStack(ItemHandler.armorBoots_hunter)}, 1000, PageRegistry.HUNTERS_ARMOR_PAGE_1));
	}
	
}
