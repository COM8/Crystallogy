package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.ArmorPlate;
import de.comeight.crystallogy.items.CrystalKnifeBlade;
import de.comeight.crystallogy.items.CrystallDust_blue;
import de.comeight.crystallogy.items.CrystallDust_green;
import de.comeight.crystallogy.items.CrystallDust_red;
import de.comeight.crystallogy.items.CrystallDust_yellow;
import de.comeight.crystallogy.items.CrystallHammerHead;
import de.comeight.crystallogy.items.CrystallSwordBlade;
import de.comeight.crystallogy.items.EnergyCrystal;
import de.comeight.crystallogy.items.EnergyDust;
import de.comeight.crystallogy.items.PureCrystallDust;
import de.comeight.crystallogy.items.ToolRod;
import de.comeight.crystallogy.items.armor.Armor_blue;
import de.comeight.crystallogy.items.armor.Armor_combined;
import de.comeight.crystallogy.items.armor.Armor_green;
import de.comeight.crystallogy.items.armor.Armor_red;
import de.comeight.crystallogy.items.armor.Armor_yellow;
import de.comeight.crystallogy.items.threatDusts.DamDust;
import de.comeight.crystallogy.items.threatDusts.DrowDust;
import de.comeight.crystallogy.items.threatDusts.FireDust;
import de.comeight.crystallogy.items.threatDusts.HungDust;
import de.comeight.crystallogy.items.threatDusts.PoisDust;
import de.comeight.crystallogy.items.tools.CrystalSword_blue;
import de.comeight.crystallogy.items.tools.CrystalSword_green;
import de.comeight.crystallogy.items.tools.CrystalSword_red;
import de.comeight.crystallogy.items.tools.CrystalSword_yellow;
import de.comeight.crystallogy.items.tools.CrystallHammer_blue;
import de.comeight.crystallogy.items.tools.CrystallHammer_green;
import de.comeight.crystallogy.items.tools.CrystallHammer_red;
import de.comeight.crystallogy.items.tools.CrystallHammer_yellow;
import de.comeight.crystallogy.items.tools.EntityCrystalKnife;
import de.comeight.crystallogy.items.tools.PlayerCrystalKnife;
import de.comeight.crystallogy.items.tools.Vaporizer;
import de.comeight.crystallogy.items.tools.VaporizerDirection;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	//Items:
	public static CrystallDust_red crystallDust_red;
	public static CrystallDust_blue crystallDust_blue;
	public static CrystallDust_yellow crystallDust_yellow;
	public static CrystallDust_green crystallDust_green;
	public static PureCrystallDust pureCrystallDust;
	public static Vaporizer vaporizer;
	public static VaporizerDirection vaporizerDirection;
	public static PlayerCrystalKnife playerCrystalKnife;
	public static EntityCrystalKnife entityCrystalKnife;
	public static DamDust damDust;
	public static DrowDust drowDust;
	public static FireDust fireDust;
	public static PoisDust poisDust;
	public static HungDust hungDust;
	public static CrystallHammer_blue crystallHammer_blue;
	public static CrystallHammer_red crystallHammer_red;
	public static CrystallHammer_yellow crystallHammer_yellow;
	public static CrystallHammer_green crystallHammer_green;
	public static CrystalSword_blue crystalSword_blue;
	public static CrystalSword_red crystalSword_red;
	public static CrystalSword_green crystalSword_green;
	public static CrystalSword_yellow crystalSword_yellow;
	public static ToolRod toolRod;
	public static CrystallHammerHead crystallHammerHead;
	public static CrystallSwordBlade crystallSwordBlade;
	public static CrystalKnifeBlade crystalKnifeBlade;
	public static ArmorPlate armorPlate;
	public static EnergyCrystal energyCrystal;
	public static EnergyDust energyDust;
	
	//Armor:
	public static Armor_red armorHelmet_red;
	public static Armor_red armorChestplate_red;
	public static Armor_red armorLeggins_red;
	public static Armor_red armorBoots_red;
	public static Armor_blue armorHelmet_blue;
	public static Armor_blue armorChestplate_blue;
	public static Armor_blue armorLeggins_blue;
	public static Armor_blue armorBoots_blue;
	public static Armor_green armorHelmet_green;
	public static Armor_green armorChestplate_green;
	public static Armor_green armorLeggins_green;
	public static Armor_green armorBoots_green;
	public static Armor_yellow armorHelmet_yellow;
	public static Armor_yellow armorChestplate_yellow;
	public static Armor_yellow armorLeggins_yellow;
	public static Armor_yellow armorBoots_yellow;
	public static Armor_combined armorHelmet_combined;
	public static Armor_combined armorChestplate_combined;
	public static Armor_combined armorLeggins_combined;
	public static Armor_combined armorBoots_combined;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemHandler() {
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerItems() {
		crystallDust_red = new CrystallDust_red();
		crystallDust_green = new CrystallDust_green();
		crystallDust_blue = new CrystallDust_blue();
		crystallDust_yellow = new CrystallDust_yellow();
		pureCrystallDust = new PureCrystallDust();
		vaporizer = new Vaporizer();
		vaporizerDirection = new VaporizerDirection();
		playerCrystalKnife = new PlayerCrystalKnife();
		entityCrystalKnife = new EntityCrystalKnife();
		damDust = new DamDust();
		drowDust = new DrowDust();
		fireDust = new FireDust();
		poisDust = new PoisDust();
		hungDust = new HungDust();
		crystallHammer_blue = new CrystallHammer_blue();
		crystallHammer_green = new CrystallHammer_green();
		crystallHammer_red = new CrystallHammer_red();
		crystallHammer_yellow = new CrystallHammer_yellow();
		crystalSword_blue = new CrystalSword_blue();
		crystalSword_green = new CrystalSword_green();
		crystalSword_red = new CrystalSword_red();
		crystalSword_yellow = new CrystalSword_yellow();
		toolRod = new ToolRod();
		crystallHammerHead = new CrystallHammerHead();
		crystallSwordBlade = new CrystallSwordBlade();
		crystalKnifeBlade = new CrystalKnifeBlade();
		armorHelmet_red = new Armor_red(1, EntityEquipmentSlot.HEAD);
		armorChestplate_red = new Armor_red(1, EntityEquipmentSlot.CHEST);
		armorLeggins_red = new Armor_red(1, EntityEquipmentSlot.LEGS);
		armorBoots_red = new Armor_red(1, EntityEquipmentSlot.FEET);
		armorHelmet_blue = new Armor_blue(1, EntityEquipmentSlot.HEAD);
		armorChestplate_blue = new Armor_blue(1, EntityEquipmentSlot.CHEST);
		armorLeggins_blue = new Armor_blue(1, EntityEquipmentSlot.LEGS);
		armorBoots_blue = new Armor_blue(1, EntityEquipmentSlot.FEET);
		armorHelmet_green = new Armor_green(1, EntityEquipmentSlot.HEAD);
		armorChestplate_green = new Armor_green(1, EntityEquipmentSlot.CHEST);
		armorLeggins_green = new Armor_green(1, EntityEquipmentSlot.LEGS);
		armorBoots_green = new Armor_green(1, EntityEquipmentSlot.FEET);
		armorHelmet_yellow = new Armor_yellow(1, EntityEquipmentSlot.HEAD);
		armorChestplate_yellow = new Armor_yellow(1, EntityEquipmentSlot.CHEST);
		armorLeggins_yellow = new Armor_yellow(1, EntityEquipmentSlot.LEGS);
		armorBoots_yellow = new Armor_yellow(1, EntityEquipmentSlot.FEET);
		armorPlate = new ArmorPlate();
		armorHelmet_combined = new Armor_combined(1, EntityEquipmentSlot.HEAD);
		armorChestplate_combined = new Armor_combined(1, EntityEquipmentSlot.CHEST);
		armorLeggins_combined = new Armor_combined(1, EntityEquipmentSlot.LEGS);
		armorBoots_combined = new Armor_combined(1, EntityEquipmentSlot.FEET);
		energyCrystal = new EnergyCrystal();
		energyDust = new EnergyDust();
		
		GameRegistry.register(crystallDust_red);
		GameRegistry.register(crystallDust_green);
		GameRegistry.register(crystallDust_blue);
		GameRegistry.register(crystallDust_yellow);
		GameRegistry.register(pureCrystallDust);
		GameRegistry.register(vaporizer);
		GameRegistry.register(vaporizerDirection);
		GameRegistry.register(playerCrystalKnife);
		GameRegistry.register(entityCrystalKnife);
		GameRegistry.register(damDust);
		GameRegistry.register(drowDust);
		GameRegistry.register(fireDust);
		GameRegistry.register(poisDust);
		GameRegistry.register(hungDust);
		GameRegistry.register(crystallHammer_blue);
		GameRegistry.register(crystallHammer_green);
		GameRegistry.register(crystallHammer_red);
		GameRegistry.register(crystallHammer_yellow);
		GameRegistry.register(crystalSword_blue);
		GameRegistry.register(crystalSword_green);
		GameRegistry.register(crystalSword_red);
		GameRegistry.register(crystalSword_yellow);
		GameRegistry.register(toolRod);
		GameRegistry.register(crystallHammerHead);
		GameRegistry.register(crystallSwordBlade);
		GameRegistry.register(crystalKnifeBlade);
		GameRegistry.register(armorHelmet_red);
		GameRegistry.register(armorChestplate_red);
		GameRegistry.register(armorLeggins_red);
		GameRegistry.register(armorBoots_red);
		GameRegistry.register(armorHelmet_blue);
		GameRegistry.register(armorChestplate_blue);
		GameRegistry.register(armorLeggins_blue);
		GameRegistry.register(armorBoots_blue);
		GameRegistry.register(armorHelmet_green);
		GameRegistry.register(armorChestplate_green);
		GameRegistry.register(armorLeggins_green);
		GameRegistry.register(armorBoots_green);
		GameRegistry.register(armorHelmet_yellow);
		GameRegistry.register(armorChestplate_yellow);
		GameRegistry.register(armorLeggins_yellow);
		GameRegistry.register(armorBoots_yellow);
		GameRegistry.register(armorPlate);
		GameRegistry.register(armorHelmet_combined);
		GameRegistry.register(armorChestplate_combined);
		GameRegistry.register(armorLeggins_combined);
		GameRegistry.register(armorBoots_combined);
		GameRegistry.register(energyCrystal);
		GameRegistry.register(energyDust);
		
		Utilities.addConsoleText("All items are registered.");
	}
	
	private void registerItemBlocks() {
		
	}
	
	// -----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit() {
		registerItems();
		registerItemBlocks();
	}

	// -----------------------------------------------Init:--------------------------------------------------
	public void init() {

	}

	// -----------------------------------------------Post-Init:---------------------------------------------
	public void postInit() {

	}

}