package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBlockRenderHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemBlockRenderHandler() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerItemBlockRenderer(){
		registerRenderer(BlockHandler.playerJar.ID);
	}
	
	private void registerRenderer(String id){
		Item item = GameRegistry.findItem(CrystallogyBase.MODID, id);
		ModelResourceLocation mRL = new ModelResourceLocation(CrystallogyBase.MODID + ":" + id, "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, mRL);
	}
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(){
		
	}

	//-----------------------------------------------Init:--------------------------------------------------
	public void init(){
		registerItemBlockRenderer();
	}

	//-----------------------------------------------Post-Init:---------------------------------------------
	public void postInit(){
		
	}
		
}
