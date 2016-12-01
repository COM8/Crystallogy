package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemsScrollBarList extends ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemsScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		list.addAll(getEntrys());
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static LinkedList<BookButtonCategory> getEntrys(){
		LinkedList<BookButtonCategory> list = new LinkedList<BookButtonCategory>();
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.armorCatalys), PageRegistry.ARMOR_CATALYST_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0),
																									new ItemStack(ItemHandler.armorPlate, 1, 1),
																									new ItemStack(ItemHandler.armorPlate, 1, 2),
																									new ItemStack(ItemHandler.armorPlate, 1, 3),
																									new ItemStack(ItemHandler.armorPlate, 1, 4),}, 1000, PageRegistry.ARMOR_PLATE_PAGE_1));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.badLuckDust), 
																									new ItemStack(ItemHandler.blindDust), 
																									new ItemStack(ItemHandler.damDust), 
																									new ItemStack(ItemHandler.drowDust), 
																									new ItemStack(ItemHandler.enderDust), 
																									new ItemStack(ItemHandler.fireDust), 
																									new ItemStack(ItemHandler.glowDust), 
																									new ItemStack(ItemHandler.hungDust), 
																									new ItemStack(ItemHandler.levDust), 
																									new ItemStack(ItemHandler.poisDust)}, 1000, PageRegistry.THREAT_DUSTS_MAIN_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.chargedCombinedArmorMesh), PageRegistry.CHARGED_COMBINED_ARMOR_MESH_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																									new ItemStack(ItemHandler.crystallDust_blue), 
																									new ItemStack(ItemHandler.crystallDust_green), 
																									new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorCompound), PageRegistry.COMBINED_ARMOR_COMPOUND_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorMesh), PageRegistry.COMBINED_ARMOR_MESH_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.crystalKnifeBlade), PageRegistry.CRYSTAL_KNIFE_BLADE_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallHammerHead, 1, 0),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 1),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 2),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 3),}, 1000, PageRegistry.CRYSTAL_HAMMER_HEAD_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallSwordBlade, 1, 0),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 1),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 2),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 3),}, 1000, PageRegistry.CRYSTAL_SWORD_BLADE_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxeHead, 1, 0),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 1),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 2),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 3),}, 1000, PageRegistry.CRYSTAL_PICKAXE_HEAD_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.entityBrain, 1, 0),
																									new ItemStack(ItemHandler.entityBrain, 1, 1),
																									new ItemStack(ItemHandler.entityBrain, 1, 2),
																									new ItemStack(ItemHandler.entityBrain, 1, 3),}, 1000, PageRegistry.ENTITY_BRAIN_PAGE));
		
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.enderonCrystal), PageRegistry.ENDERON_CRYSTAL_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyCrystal), PageRegistry.ENERGY_CRYSTAL_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyDust), PageRegistry.ENERGY_DUST_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.fertilizerPotato), PageRegistry.FERTILIZER_POTATO_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.hunterArmorCompound), PageRegistry.HUNTERS_ARMOR_COMPOUND_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.hunterArmorMesh), PageRegistry.HUNTERS_ARMOR_MESH_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.magicStoneOfForgetfulness), PageRegistry.MAGIC_STONE_OF_FORGETFULNESS));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE));
		list.add(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.toolRod), PageRegistry.TOOL_ROD_PAGE));
		
		return list;
	}
	
}
