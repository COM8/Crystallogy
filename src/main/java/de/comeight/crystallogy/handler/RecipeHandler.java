package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.blocks.EnumCrystalColor;
import de.comeight.crystallogy.items.crafting.RecipeBookOfKnowledge;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeAiDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeArmorCatalyst;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeArmorPlate;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeBadLuckDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeBlindDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeChargedCombinedArmorCompound;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeCrystalKnifeBlade;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeCrystalOfHolding;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeCrystallLight;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeDamDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeDrowDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeEnderDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeEntityBrainFollowPlayer;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeEntityBrainMoveToPos;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeEntityBrainPickupItems;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeEntityBrainQuarry;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeEntityCrystalKnife;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeEntityJar;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeFireCrystall;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeFireDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeGlowDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeHammer;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeHungDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeLevDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeMachineBlock;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeMagicStoneOfForgetfulness;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipePickaxe;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipePlayerCrystalKnife;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipePlayerJar;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipePoisDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipePureDust;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeRefulelVaporizer;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeSword;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeToolRod;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeVaporizer;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	//Infusion Recipes:
	public static InfusionRecipeVaporizer infusionRecipeVaporizer;
	public static InfusionRecipeCrystallLight infusionRecipeCrystallLight;
	public static InfusionRecipeFireCrystall infusionRecipeFireCrystall;
	public static InfusionRecipeRefulelVaporizer infusionRecipeRefulelVaporizer;
	public static InfusionRecipeToolRod infusionRecipeToolRod;
	public static InfusionRecipePureDust infusionRecipePureDust;
	public static InfusionRecipeHammer infusionRecipeHammer;
	public static InfusionRecipePickaxe infusionRecipePickaxe;
	public static InfusionRecipeSword infusionRecipeSword;
	public static InfusionRecipeHungDust infusionRecipeHungDust;
	public static InfusionRecipeDamDust infusionRecipeDamDust;
	public static InfusionRecipeFireDust infusionRecipeFireDust;
	public static InfusionRecipeDrowDust infusionRecipeDrowDust;
	public static InfusionRecipePoisDust infusionRecipePoisDust;
	public static InfusionRecipeBadLuckDust infusionRecipeBadLuckDust;
	public static InfusionRecipeBlindDust infusionRecipeBlindDust;
	public static InfusionRecipeEnderDust infusionRecipeEnderDust;
	public static InfusionRecipeGlowDust infusionRecipeGlowDust;
	public static InfusionRecipeLevDust infusionRecipeLevDust;
	public static InfusionRecipePlayerCrystalKnife infusionRecipePlayerCrystalKnife;
	public static InfusionRecipeEntityCrystalKnife infusionRecipeEntityCrystalKnife;
	public static InfusionRecipeCrystalKnifeBlade infusionRecipeCrystalKnifeBlade;
	public static InfusionRecipePlayerJar infusionRecipePlayerJar;
	public static InfusionRecipeEntityJar infusionRecipeEntityJar;
	public static InfusionRecipeArmorPlate infusionRecipeArmorPlate;
	public static InfusionRecipeCrystalOfHolding infusionRecipeCrystalOfHolding;
	public static InfusionRecipeMachineBlock infusionRecipeMachineBlock;
	public static InfusionRecipeArmorCatalyst infusionRecipeArmorCatalyst;
	public static InfusionRecipeChargedCombinedArmorCompound infusionRecipeChargedCombinedArmorCompound;
	public static InfusionRecipeEntityBrainQuarry infusionRecipeEntityBrainQuarry;
	public static InfusionRecipeEntityBrainPickupItems infusionRecipeEntityBrainPickupItems;
	public static InfusionRecipeEntityBrainMoveToPos infusionRecipeEntityBrainMoveToPos;
	public static InfusionRecipeEntityBrainFollowPlayer infusionRecipeEntityBrainFollowPlayer;
	public static InfusionRecipeMagicStoneOfForgetfulness infusionRecipeMagicStoneOfForgetfulness;
	public static InfusionRecipeAiDust infusionRecipeAiDust;
	
	//-----------------------------------------------Constructor:-------------------------------------------


	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerInfusionRecipes(){
		infusionRecipeVaporizer = new InfusionRecipeVaporizer();
		infusionRecipeCrystallLight = new InfusionRecipeCrystallLight();
		infusionRecipeFireCrystall = new InfusionRecipeFireCrystall();
		infusionRecipeRefulelVaporizer = new InfusionRecipeRefulelVaporizer();
		infusionRecipeToolRod = new InfusionRecipeToolRod();
		infusionRecipePureDust = new InfusionRecipePureDust();
		infusionRecipeHammer = new InfusionRecipeHammer();
		infusionRecipeSword = new InfusionRecipeSword();
		infusionRecipeHungDust = new InfusionRecipeHungDust();
		infusionRecipeDamDust = new InfusionRecipeDamDust();
		infusionRecipeFireDust = new InfusionRecipeFireDust();
		infusionRecipeDrowDust = new InfusionRecipeDrowDust();
		infusionRecipePoisDust = new InfusionRecipePoisDust();
		infusionRecipeBadLuckDust = new InfusionRecipeBadLuckDust();
		infusionRecipeBlindDust = new InfusionRecipeBlindDust();
		infusionRecipeEnderDust = new InfusionRecipeEnderDust();
		infusionRecipeGlowDust = new InfusionRecipeGlowDust();
		infusionRecipeLevDust = new InfusionRecipeLevDust();
		infusionRecipePlayerCrystalKnife = new InfusionRecipePlayerCrystalKnife();
		infusionRecipeEntityCrystalKnife = new InfusionRecipeEntityCrystalKnife();
		infusionRecipeCrystalKnifeBlade = new InfusionRecipeCrystalKnifeBlade();
		infusionRecipePlayerJar = new InfusionRecipePlayerJar();
		infusionRecipeArmorPlate = new InfusionRecipeArmorPlate();
		infusionRecipeEntityJar = new InfusionRecipeEntityJar();
		infusionRecipePickaxe = new InfusionRecipePickaxe();
		infusionRecipeCrystalOfHolding = new InfusionRecipeCrystalOfHolding();
		infusionRecipeMachineBlock = new InfusionRecipeMachineBlock();
		infusionRecipeArmorCatalyst = new InfusionRecipeArmorCatalyst();
		infusionRecipeChargedCombinedArmorCompound = new InfusionRecipeChargedCombinedArmorCompound();
		infusionRecipeEntityBrainQuarry = new InfusionRecipeEntityBrainQuarry();
		infusionRecipeEntityBrainPickupItems = new InfusionRecipeEntityBrainPickupItems();
		infusionRecipeEntityBrainMoveToPos = new InfusionRecipeEntityBrainMoveToPos();
		infusionRecipeEntityBrainFollowPlayer = new InfusionRecipeEntityBrainFollowPlayer();
		infusionRecipeMagicStoneOfForgetfulness = new InfusionRecipeMagicStoneOfForgetfulness();
		infusionRecipeAiDust = new InfusionRecipeAiDust();
		
		InfusionRecipeHandler.addRecipe(infusionRecipeAiDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeMagicStoneOfForgetfulness);
		InfusionRecipeHandler.addRecipe(infusionRecipeEntityBrainFollowPlayer);
		InfusionRecipeHandler.addRecipe(infusionRecipeEntityBrainMoveToPos);
		InfusionRecipeHandler.addRecipe(infusionRecipeEntityBrainPickupItems);
		InfusionRecipeHandler.addRecipe(infusionRecipeEntityBrainQuarry);
		InfusionRecipeHandler.addRecipe(infusionRecipeChargedCombinedArmorCompound);
		InfusionRecipeHandler.addRecipe(infusionRecipeArmorCatalyst);
		InfusionRecipeHandler.addRecipe(infusionRecipeMachineBlock);
		InfusionRecipeHandler.addRecipe(infusionRecipeCrystalOfHolding);
		InfusionRecipeHandler.addRecipe(infusionRecipeVaporizer);
		InfusionRecipeHandler.addRecipe(infusionRecipeCrystallLight);
		InfusionRecipeHandler.addRecipe(infusionRecipeFireCrystall);
		InfusionRecipeHandler.addRecipe(infusionRecipeRefulelVaporizer);
		InfusionRecipeHandler.addRecipe(infusionRecipePureDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeSword);
		InfusionRecipeHandler.addRecipe(infusionRecipeHammer);
		InfusionRecipeHandler.addRecipe(infusionRecipeToolRod);
		InfusionRecipeHandler.addRecipe(infusionRecipeDamDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeDrowDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeFireDust);
		InfusionRecipeHandler.addRecipe(infusionRecipePoisDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeHungDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeBadLuckDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeBlindDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeEnderDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeGlowDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeLevDust);
		InfusionRecipeHandler.addRecipe(infusionRecipePlayerCrystalKnife);
		InfusionRecipeHandler.addRecipe(infusionRecipeEntityCrystalKnife);
		InfusionRecipeHandler.addRecipe(infusionRecipeCrystalKnifeBlade);
		InfusionRecipeHandler.addRecipe(infusionRecipePlayerJar);
		InfusionRecipeHandler.addRecipe(infusionRecipeEntityJar);
		InfusionRecipeHandler.addRecipe(infusionRecipeArmorPlate);
		InfusionRecipeHandler.addRecipe(infusionRecipePickaxe);
	}

	private void registerCraftingRecipes(){
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.dissectingTable), new Object[]{
				"BSG",
				"PSP",
				"P_P",
				
				'B', Items.BOOK,
				'G', ItemHandler.crystallDust_green,
				'P', "plankWood",
				'S', "slabWood",
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemHandler.vaporizer.getItemStack(), new Object[]{
				"III",
				"PI_",
				"_G_",
				
				'I', Items.IRON_INGOT,
				'P', "plankWood",
				'G', "blockGlassLime",
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockHandler.infuserBlock, new Object[]{
				"CIC",
				"_C_",
				"WWW",
				
				'C', "cobblestone",
				'I', "workbench",
				'W', Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE,
		}));
		
		addCrystalHammerHeadRecipe(new ItemStack(BlockHandler.crystall_red), EnumCrystalColor.RED.getStack(new ItemStack(ItemHandler.crystallHammerHead)));
		addCrystalHammerHeadRecipe(new ItemStack(BlockHandler.crystall_blue), EnumCrystalColor.BLUE.getStack(new ItemStack(ItemHandler.crystallHammerHead)));
		addCrystalHammerHeadRecipe(new ItemStack(BlockHandler.crystall_green), EnumCrystalColor.GREEN.getStack(new ItemStack(ItemHandler.crystallHammerHead)));
		addCrystalHammerHeadRecipe(new ItemStack(BlockHandler.crystall_yellow), EnumCrystalColor.YELLOW.getStack(new ItemStack(ItemHandler.crystallHammerHead)));
		
		addCrystalSwordBladeRecipe(new ItemStack(BlockHandler.crystall_red), EnumCrystalColor.RED.getStack(new ItemStack(ItemHandler.crystallSwordBlade)));
		addCrystalSwordBladeRecipe(new ItemStack(BlockHandler.crystall_blue), EnumCrystalColor.BLUE.getStack(new ItemStack(ItemHandler.crystallSwordBlade)));
		addCrystalSwordBladeRecipe(new ItemStack(BlockHandler.crystall_green), EnumCrystalColor.GREEN.getStack(new ItemStack(ItemHandler.crystallSwordBlade)));
		addCrystalSwordBladeRecipe(new ItemStack(BlockHandler.crystall_yellow), EnumCrystalColor.YELLOW.getStack(new ItemStack(ItemHandler.crystallSwordBlade)));

		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.armorPlate, 4, 4), "RG",
                "BY",

                'R', BlockHandler.crystall_red,
                'G', BlockHandler.crystall_green,
                'B', BlockHandler.crystall_blue,
                'Y', BlockHandler.crystall_yellow);
		
		//Armor Red:
		addArmorRecipe(new ItemStack(ItemHandler.armorPlate, 1, 0), new ItemStack(ItemHandler.armorHelmet_red),
																	new ItemStack(ItemHandler.armorChestplate_red),
																	new ItemStack(ItemHandler.armorLeggins_red),
																	new ItemStack(ItemHandler.armorBoots_red));
		
		//Armor Green:
		addArmorRecipe(new ItemStack(ItemHandler.armorPlate, 1, 2), new ItemStack(ItemHandler.armorHelmet_green),
																	new ItemStack(ItemHandler.armorChestplate_green),
																	new ItemStack(ItemHandler.armorLeggins_green),
																	new ItemStack(ItemHandler.armorBoots_green));
		
		//Armor Blue:
		addArmorRecipe(new ItemStack(ItemHandler.armorPlate, 1, 1), new ItemStack(ItemHandler.armorHelmet_blue),
																	new ItemStack(ItemHandler.armorChestplate_blue),
																	new ItemStack(ItemHandler.armorLeggins_blue),
																	new ItemStack(ItemHandler.armorBoots_blue));
		
		//Armor Yellow:
		addArmorRecipe(new ItemStack(ItemHandler.armorPlate, 1, 3), new ItemStack(ItemHandler.armorHelmet_yellow),
																	new ItemStack(ItemHandler.armorChestplate_yellow),
																	new ItemStack(ItemHandler.armorLeggins_yellow),
																	new ItemStack(ItemHandler.armorBoots_yellow));
		
		//Armor Combined:
		addArmorRecipe(new ItemStack(ItemHandler.combinedArmorCompound), new ItemStack(ItemHandler.armorHelmet_combined),
				new ItemStack(ItemHandler.armorChestplate_combined),
				new ItemStack(ItemHandler.armorLeggins_combined),
				new ItemStack(ItemHandler.armorBoots_combined));
		
		//Armor Hunter:
		addArmorRecipe(new ItemStack(ItemHandler.hunterArmorCompound), new ItemStack(ItemHandler.armorHelmet_hunter),
				new ItemStack(ItemHandler.armorChestplate_hunter),
				new ItemStack(ItemHandler.armorLeggins_hunter),
				new ItemStack(ItemHandler.armorBoots_hunter));
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.energyDust), "RRR",
                "RCR",
                "RRR",
                'C', ItemHandler.crystallDust_red,
                'R', Items.REDSTONE);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.armorCombiner), "YDR",
                "BAG",
                "IMI",
                'M', BlockHandler.machineBlock,
                'R', BlockHandler.crystall_red,
                'B', BlockHandler.crystall_blue,
                'G', BlockHandler.crystall_green,
                'Y', BlockHandler.crystall_yellow,
                'A', ItemHandler.armorChestplate_combined,
                'D', Blocks.DIAMOND_BLOCK,
                'I', ItemHandler.armorCatalys);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.compressor), "OCO",
                "ORO",
                "OMO",
                'O', Blocks.OBSIDIAN,
                'R', Items.REDSTONE,
                'M', BlockHandler.machineBlock,
                'C', BlockHandler.crystall_blue);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.crystallCrusher), "BBB",
                "I_I",
                "IMI",
                'B', Blocks.BRICK_BLOCK,
                'I', Items.IRON_INGOT,
                'M', Blocks.IRON_BLOCK);
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.charger), "OCO",
                "ORO",
                "OMO",
                'O', Blocks.OBSIDIAN,
                'R', Items.REDSTONE,
                'C', new ItemStack(ItemHandler.energyCrystal, 1, 12000),
                'M', BlockHandler.machineBlock);
		
		addCrystalGlasRecipe(new ItemStack(ItemHandler.crystallDust_red), new ItemStack(BlockHandler.crystalGlas, 7, 0));
		addCrystalGlasRecipe(new ItemStack(ItemHandler.crystallDust_blue), new ItemStack(BlockHandler.crystalGlas, 7, 1));
		addCrystalGlasRecipe(new ItemStack(ItemHandler.crystallDust_green), new ItemStack(BlockHandler.crystalGlas, 7, 2));
		addCrystalGlasRecipe(new ItemStack(ItemHandler.crystallDust_yellow), new ItemStack(BlockHandler.crystalGlas, 7, 3));
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.fertilizerPotato, 3), "YPC",
                "BPB",
                "CPY",
                'B', new ItemStack(Items.DYE, 2, 15),
                'P', Items.POISONOUS_POTATO,
                'C', ItemHandler.crystallDust_green,
                'Y', BlockHandler.crystorya);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.farmersGreen, 1, 1), new Object[] {
				"IPI",
				"G_G",
				"GGG",
				'I', Items.IRON_INGOT,
				'P', "plankWood",
				'G', new ItemStack(BlockHandler.crystalGlas, 1, 2)
		}));
		
		addCrystalPickaxeHeadPartRecipe(new ItemStack(BlockHandler.crystall_red), new ItemStack(ItemHandler.crystalPickaxeHead, 1, 0));
		addCrystalPickaxeHeadPartRecipe(new ItemStack(BlockHandler.crystall_blue), new ItemStack(ItemHandler.crystalPickaxeHead, 1, 1));
		addCrystalPickaxeHeadPartRecipe(new ItemStack(BlockHandler.crystall_green), new ItemStack(ItemHandler.crystalPickaxeHead, 1, 2));
		addCrystalPickaxeHeadPartRecipe(new ItemStack(BlockHandler.crystall_yellow), new ItemStack(ItemHandler.crystalPickaxeHead, 1, 3));
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.entityGrabber), "IB_",
                "BIY",
                "__I",
                'I', Items.IRON_INGOT,
                'B', new ItemStack(BlockHandler.crystalGlas, 1, 1),
                'Y', new ItemStack(ItemHandler.armorPlate, 1, 3));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemHandler.hunterArmorMesh, new Object[]{
				"GPG",
				"PDP",
				"GPG",
				
				'G', "nuggetGold",
				'P', new ItemStack(ItemHandler.armorPlate, 4, 4),
				'D', "gemDiamond"
		}));
		
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.combinedArmorMesh), "CPG",
                "PMP",
                "GPC",

                'G', new ItemStack(BlockHandler.crystalGlas, 1, 1),
                'P', new ItemStack(ItemHandler.armorPlate, 4, 4),
                'M', ItemHandler.hunterArmorMesh,
                'C', ItemHandler.armorCatalys);
		
		GameRegistry.addRecipe(new RecipeBookOfKnowledge());
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.areaPicker), "Q_Q",
                "CCC",
                "_T_",
                'T', ItemHandler.toolRod,
                'C', BlockHandler.crystall_yellow,
                'Q', Items.QUARTZ);
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.spotPicker), "Q_Q",
                "CCC",
                "_T_",
                'T', ItemHandler.toolRod,
                'C', BlockHandler.crystall_green,
                'Q', Items.QUARTZ);
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.entityBrain,  1, 0), "BBB",
                "BSB",
                "BBB",
                'B', new ItemStack(ItemHandler.entityBrain,  1, 1),
                'S', Items.SLIME_BALL);
		
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.entityBrain,  1, 1), "BBB",
                "BSB",
                "BBB",
                'B', new ItemStack(ItemHandler.entityBrain,  1, 2),
                'S', Items.SLIME_BALL);
	}
	
	private void addCrystalHammerHeadRecipe(ItemStack crystal, ItemStack out){
		GameRegistry.addShapedRecipe(out, "CCC",
                "CCC",
                'C', crystal);
	}
	
	private void addCrystalSwordBladeRecipe(ItemStack crystal, ItemStack out){
		GameRegistry.addShapedRecipe(out, "C",
                "C",
                'C', crystal);
	}
	
	private void addCrystalPickaxeHeadPartRecipe(ItemStack crystal, ItemStack out){
		GameRegistry.addShapedRecipe(out, "CCC",
                'C', crystal);
	}
	
	private void addCrystalGlasRecipe(ItemStack dust, ItemStack glas){
		GameRegistry.addRecipe(new ShapedOreRecipe(glas, new Object[]{
				"GGG",
				"DGD",
				"GGG",
				
				'G', "blockGlass",
				'D', dust,
		}));
	}
	
	private void addArmorRecipe(ItemStack s, ItemStack helmet, ItemStack chestplate, ItemStack leggins, ItemStack boots){
		GameRegistry.addShapedRecipe(helmet, "SSS",
                "S_S",

                'S', s);
		
		GameRegistry.addShapedRecipe(chestplate, "S_S",
                "SSS",
                "SSS",

                'S', s);
		
		GameRegistry.addShapedRecipe(leggins, "SSS",
                "S_S",
                "S_S",

                'S', s);
		
		GameRegistry.addShapedRecipe(boots, "S_S",
                "S_S",

                'S', s);
	}
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(){
	}

	//-----------------------------------------------Init:--------------------------------------------------
	public void init(){
		registerInfusionRecipes();
		registerCraftingRecipes();
		
		Utilities.addConsoleText("All recipes are registered.");
	}

	//-----------------------------------------------Post-Init:---------------------------------------------
	public void postInit(){
	}
}
