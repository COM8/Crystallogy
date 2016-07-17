package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.blocks.container.ContainerArmorCombiner;
import de.comeight.crystallogy.blocks.container.ContainerCharger;
import de.comeight.crystallogy.blocks.container.ContainerCompressor;
import de.comeight.crystallogy.blocks.container.ContainerCrystallCrusher;
import de.comeight.crystallogy.gui.GuiArmorCombiner;
import de.comeight.crystallogy.gui.GuiCharger;
import de.comeight.crystallogy.gui.GuiCompressor;
import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookMain;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityArmorCombiner;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCharger;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCompressor;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCrystallCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{ //TODO Convert to a list
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static GuiHandler INSTANCE = new GuiHandler();

	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiHandler() {
	}	

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//System.out.println("Server");
		TileEntity t = world.getTileEntity(new BlockPos(x,y,z));
		switch(ID){
		case GuiCrystallCrusher.ID: //Crusher
			if(t instanceof TileEntityCrystallCrusher){
				return new ContainerCrystallCrusher(player.inventory, (TileEntityCrystallCrusher) t);
			}
			else{
				return null;
			}
		case GuiCompressor.ID:  //Compressor
			if(t instanceof TileEntityCompressor){
				return new ContainerCompressor(player.inventory, (TileEntityCompressor) t);
			}
			else{
				return null;
			}
		case GuiCharger.ID:  //Charger
			if(t instanceof TileEntityCharger){
				return new ContainerCharger(player.inventory, (TileEntityCharger) t);
			}
			else{
				return null;
			}
		case GuiArmorCombiner.ID: //Armor Combiner
			if(t instanceof TileEntityArmorCombiner){
				return new ContainerArmorCombiner(player.inventory, (TileEntityArmorCombiner) t);
			}
			else{
				return null;
			}
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//System.out.println("Client");
		TileEntity t = world.getTileEntity(new BlockPos(x,y,z));
		switch(ID){
		case GuiCrystallCrusher.ID:  //Crusher
			if(t instanceof TileEntityCrystallCrusher){
				return new GuiCrystallCrusher(player.inventory, (TileEntityCrystallCrusher) t);
			}
			else{
				return null;
			}
		case GuiCompressor.ID:  //Compressor
			if(t instanceof TileEntityCompressor){
				return new GuiCompressor(player.inventory, (TileEntityCompressor) t);
			}
			else{
				return null;
			}
		case GuiCharger.ID:  //Charger
			if(t instanceof TileEntityCharger){
				return new GuiCharger(player.inventory, (TileEntityCharger) t);
			}
			else{
				return null;
			}
		case GuiArmorCombiner.ID: //Armor Combiner
			if(t instanceof TileEntityArmorCombiner){
				return new GuiArmorCombiner(player.inventory, (TileEntityArmorCombiner) t);
			}
			else{
				return null;
			}
		case GuiBookMain.ID: //Book of Knowledge:
			return new GuiBookMain();
		default:
			return null;
		}
	}
	
}
