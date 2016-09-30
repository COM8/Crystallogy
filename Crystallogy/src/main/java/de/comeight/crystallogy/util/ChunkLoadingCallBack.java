package de.comeight.crystallogy.util;

import java.util.List;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class ChunkLoadingCallBack implements ForgeChunkManager.OrderedLoadingCallback{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ChunkLoadingCallBack() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {
		for (Ticket ticket : tickets) {
			int targetPosX = ticket.getModData().getInteger("posX");
			int targetPosY = ticket.getModData().getInteger("posY");
			int targetPosZ = ticket.getModData().getInteger("posZ");
			BlockPos pos = new BlockPos(targetPosX, targetPosY, targetPosZ);
			
			loadChunk(ticket, pos);
		}
	}

	@Override
	public List<Ticket> ticketsLoaded(List<Ticket> tickets, World world, int maxTicketCount) {
		return tickets;
	}
	
	public static void requestLoadChunk(World world, BlockPos pos){
		if(world.isRemote){
			return;
		}
		Ticket chunkTicket = ForgeChunkManager.requestTicket(CrystallogyBase.INSTANCE, world, Type.NORMAL);
		
		if(chunkTicket == null){
			return;
		}
		
		chunkTicket.getModData().setInteger("posX", pos.getX());
		chunkTicket.getModData().setInteger("posY", pos.getY());
		chunkTicket.getModData().setInteger("posZ", pos.getZ());
		
		ForgeChunkManager.forceChunk(chunkTicket, new ChunkPos(pos));
	}
	
	private void loadChunk(Ticket chunkTicket, BlockPos pos){
		ForgeChunkManager.forceChunk(chunkTicket, new ChunkPos(pos));
	}
	
}
