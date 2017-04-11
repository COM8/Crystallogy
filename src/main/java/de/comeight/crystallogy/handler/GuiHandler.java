package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.blocks.container.ContainerArmorCombiner;
import de.comeight.crystallogy.blocks.container.ContainerCharger;
import de.comeight.crystallogy.blocks.container.ContainerCompressor;
import de.comeight.crystallogy.blocks.container.ContainerCrystallCrusher;
import de.comeight.crystallogy.blocks.container.ContainerDissectingTable;
import de.comeight.crystallogy.gui.GuiArmorCombiner;
import de.comeight.crystallogy.gui.GuiBookOfKnowledge;
import de.comeight.crystallogy.gui.GuiCharger;
import de.comeight.crystallogy.gui.GuiCompressor;
import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import de.comeight.crystallogy.gui.GuiDissectingTable;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityArmorCombiner;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCharger;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCompressor;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCrystallCrusher;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityDissectingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static GuiHandler INSTANCE = new GuiHandler();
	
	//Gui ID's:
	public static final int ARMOR_COMBINER_ID = 0;
	public static final int COMPRESSOR_ID = 1;
	public static final int CRYSTAL_CRUSHER_ID = 2;
	public static final int CHARGER_ID = 3;
	public static final int BOOK_OF_KNOWLEDGE_ID = 4;
	public static final int DISSECTING_TABLE_ID = 5;

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
		case CRYSTAL_CRUSHER_ID: //Crusher
			if(t instanceof TileEntityCrystallCrusher){
				return new ContainerCrystallCrusher(player.inventory, (TileEntityCrystallCrusher) t);
			}
			break;
			
		case COMPRESSOR_ID:  //Compressor
			if(t instanceof TileEntityCompressor){
				return new ContainerCompressor(player.inventory, (TileEntityCompressor) t);
			}
			break;

		case CHARGER_ID:  //Charger
			if(t instanceof TileEntityCharger){
				return new ContainerCharger(player.inventory, (TileEntityCharger) t);
			}
			break;
			
		case ARMOR_COMBINER_ID: //Armor Combiner
			if(t instanceof TileEntityArmorCombiner){
				return new ContainerArmorCombiner(player.inventory, (TileEntityArmorCombiner) t);
			}
			break;
			
		case DISSECTING_TABLE_ID: //Dissecing Table
			if(t instanceof TileEntityDissectingTable){
				return new ContainerDissectingTable(player.inventory, (TileEntityDissectingTable) t);
			}
			break;
			
		default:
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//System.out.println("Client");
		TileEntity t = world.getTileEntity(new BlockPos(x,y,z));
		switch(ID){
		case CRYSTAL_CRUSHER_ID:  //Crusher
			if(t instanceof TileEntityCrystallCrusher){
				return new GuiCrystallCrusher(player.inventory, (TileEntityCrystallCrusher) t);
			}
			break;
			
		case COMPRESSOR_ID:  //Compressor
			if(t instanceof TileEntityCompressor){
				return new GuiCompressor(player.inventory, (TileEntityCompressor) t);
			}
			break;
			
		case CHARGER_ID:  //Charger
			if(t instanceof TileEntityCharger){
				return new GuiCharger(player.inventory, (TileEntityCharger) t);
			}
			break;
			
		case ARMOR_COMBINER_ID: //Armor Combiner
			if(t instanceof TileEntityArmorCombiner){
				return new GuiArmorCombiner(player.inventory, (TileEntityArmorCombiner) t);
			}
			break;

		case BOOK_OF_KNOWLEDGE_ID: //Book of Knowledge
			return new GuiBookOfKnowledge();
			
		case DISSECTING_TABLE_ID: //Dissecting Table
			if(t instanceof TileEntityDissectingTable){
				return new GuiDissectingTable(player.inventory, (TileEntityDissectingTable) t);
			}
			break;
			
		default:
			break;
		}
		return null;
	}
	
}
