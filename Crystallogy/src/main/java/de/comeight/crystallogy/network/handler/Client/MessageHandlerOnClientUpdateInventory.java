package de.comeight.crystallogy.network.handler.Client;

import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.tileEntitys.TileEntityInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageHandlerOnClientUpdateInventory implements IMessageHandler<NetworkPacketUpdateInventory, IMessage> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnClientUpdateInventory() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public IMessage onMessage(final NetworkPacketUpdateInventory message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Client.");
		if (ctx.side != Side.CLIENT) {
			System.err.println("NetworkPacketUpdateInventory received on wrong side: " + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketUpdateInventory was invalid" + message.toString());
			return null;
		}
		final Minecraft minecraft = Minecraft.getMinecraft();
		minecraft.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, minecraft);
			}
		});
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	private void processMessage(NetworkPacketUpdateInventory message, Minecraft minecraft) {
		WorldClient worldClient = minecraft.theWorld;
		BlockPos pos = message.getTilePos();
		TileEntity tE = worldClient.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
		if(tE instanceof TileEntityInventory){
			TileEntityInventory t =  (TileEntityInventory) tE;
			t.setInventorySlotContents(message.getSlotIndex(), message.getStack());
		}
		else{
			System.out.println("No/wrong TileEntity in MessageHandlerOnClientUpdateInventory!");
		}
	}
	
}