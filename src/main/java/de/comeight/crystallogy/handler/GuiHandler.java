package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystallCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiHandler() {
	}	

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GuiCrystallCrusher.ID:
			TileEntity t = world.getTileEntity(new BlockPos(x,y,z));
			if(t instanceof TileEntityCrystallCrusher){
				return new GuiCrystallCrusher(player.inventory, (TileEntityCrystallCrusher) t);
			}
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GuiCrystallCrusher.ID:
			TileEntity t = world.getTileEntity(new BlockPos(x,y,z));
			if(t instanceof TileEntityCrystallCrusher){
				return new GuiCrystallCrusher(player.inventory, (TileEntityCrystallCrusher) t);
			}
		default:
			return null;
		}
	}
	
}
