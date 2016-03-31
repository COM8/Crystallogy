package de.comeight.crystallogy.network.handler.Client;

import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnClientInfusionRecipeStatus implements IMessageHandler<NetworkPacketInfusionRecipeStatus, IMessage> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnClientInfusionRecipeStatus() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public IMessage onMessage(final NetworkPacketInfusionRecipeStatus message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Client.");
		if (ctx.side != Side.CLIENT) {
			System.err.println("NetworkPacketInfusionRecipeStatus received on wrong side: " + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketInfusionRecipeStatus was invalid" + message.toString());
			return null;
		}
		Minecraft minecraft = Minecraft.getMinecraft();
		final WorldClient worldClient = minecraft.theWorld;
		minecraft.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, worldClient);
			}
		});
		return null;
	}
	
	private void processMessage(NetworkPacketInfusionRecipeStatus message, WorldClient worldClient) {
		Vec3d pos = message.getTilePos();
		TileEntity tE = worldClient.getTileEntity(new BlockPos(pos.xCoord, pos.yCoord, pos.zCoord));
		if(tE instanceof TileEnityInfuserBlock){
			TileEnityInfuserBlock t =  (TileEnityInfuserBlock) tE;
			t.changeRecipeStatus(message.getStatus(), worldClient);
		}
		else{
			System.out.println("No/wrong TileEntity in MessageHandlerOnClientInfusionRecipeStatus!");
		}
	}
	
}
