package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.CrystalSwordBlade_blue;
import de.comeight.crystallogy.items.CrystalSwordBlade_green;
import de.comeight.crystallogy.items.CrystalSwordBlade_red;
import de.comeight.crystallogy.items.CrystalSwordBlade_yellow;
import de.comeight.crystallogy.items.CrystalSword_blue;
import de.comeight.crystallogy.items.CrystalSword_green;
import de.comeight.crystallogy.items.CrystalSword_red;
import de.comeight.crystallogy.items.CrystalSword_yellow;
import de.comeight.crystallogy.items.CrystallDust_blue;
import de.comeight.crystallogy.items.CrystallDust_green;
import de.comeight.crystallogy.items.CrystallDust_red;
import de.comeight.crystallogy.items.CrystallDust_yellow;
import de.comeight.crystallogy.items.CrystallHammerHead_blue;
import de.comeight.crystallogy.items.CrystallHammerHead_green;
import de.comeight.crystallogy.items.CrystallHammerHead_red;
import de.comeight.crystallogy.items.CrystallHammerHead_yellow;
import de.comeight.crystallogy.items.CrystallHammer_blue;
import de.comeight.crystallogy.items.CrystallHammer_green;
import de.comeight.crystallogy.items.CrystallHammer_red;
import de.comeight.crystallogy.items.CrystallHammer_yellow;
import de.comeight.crystallogy.items.CrystallKnif;
import de.comeight.crystallogy.items.DamDust;
import de.comeight.crystallogy.items.DrowDust;
import de.comeight.crystallogy.items.FireDust;
import de.comeight.crystallogy.items.HungDust;
import de.comeight.crystallogy.items.PoisDust;
import de.comeight.crystallogy.items.PureCrystallDust;
import de.comeight.crystallogy.items.ToolRod;
import de.comeight.crystallogy.items.Vaporizer;
import de.comeight.crystallogy.items.VaporizerDirection;
import de.comeight.crystallogy.util.Utilities;
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
	public static CrystallKnif crystallKnif;
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
	public static CrystallHammerHead_red crystallHammerHead_red;
	public static CrystallHammerHead_blue crystallHammerHead_blue;
	public static CrystallHammerHead_green crystallHammerHead_green;
	public static CrystallHammerHead_yellow crystallHammerHead_yellow;
	public static CrystalSwordBlade_red crystalSwordBlade_red;
	public static CrystalSwordBlade_blue crystalSwordBlade_blue;
	public static CrystalSwordBlade_green crystalSwordBlade_green;
	public static CrystalSwordBlade_yellow crystalSwordBlade_yellow;
	
	//ItemBlocks
	
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
		crystallKnif = new CrystallKnif();
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
		crystallHammerHead_blue = new CrystallHammerHead_blue();
		crystallHammerHead_green = new CrystallHammerHead_green();
		crystallHammerHead_red = new CrystallHammerHead_red();
		crystallHammerHead_yellow = new CrystallHammerHead_yellow();
		crystalSwordBlade_blue = new CrystalSwordBlade_blue();
		crystalSwordBlade_green = new CrystalSwordBlade_green();
		crystalSwordBlade_red = new CrystalSwordBlade_red();
		crystalSwordBlade_yellow = new CrystalSwordBlade_yellow();
		
		GameRegistry.register(crystallDust_red);
		GameRegistry.register(crystallDust_green);
		GameRegistry.register(crystallDust_blue);
		GameRegistry.register(crystallDust_yellow);
		GameRegistry.register(pureCrystallDust);
		GameRegistry.register(vaporizer);
		GameRegistry.register(vaporizerDirection);
		GameRegistry.register(crystallKnif);
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
		GameRegistry.register(crystallHammerHead_blue);
		GameRegistry.register(crystallHammerHead_green);
		GameRegistry.register(crystallHammerHead_red);
		GameRegistry.register(crystallHammerHead_yellow);
		GameRegistry.register(crystalSwordBlade_blue);
		GameRegistry.register(crystalSwordBlade_green);
		GameRegistry.register(crystalSwordBlade_red);
		GameRegistry.register(crystalSwordBlade_yellow);
		
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
