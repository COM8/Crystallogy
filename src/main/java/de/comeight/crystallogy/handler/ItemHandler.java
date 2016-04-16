package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.CrystallDust_blue;
import de.comeight.crystallogy.items.CrystallDust_green;
import de.comeight.crystallogy.items.CrystallDust_red;
import de.comeight.crystallogy.items.CrystallDust_yellow;
import de.comeight.crystallogy.items.CrystallKnif;
import de.comeight.crystallogy.items.DamDust;
import de.comeight.crystallogy.items.DrowDust;
import de.comeight.crystallogy.items.FireDust;
import de.comeight.crystallogy.items.HungDust;
import de.comeight.crystallogy.items.PoisDust;
import de.comeight.crystallogy.items.PureCrystallDust;
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
