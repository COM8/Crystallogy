package de.comeight.crystallogy.network.handler.Client;

import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
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
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageHandlerOnClientInfuserBlockEnabled implements IMessageHandler<NetworkPacketInfuserBlockEnabled, IMessage> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnClientInfuserBlockEnabled() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public IMessage onMessage(final NetworkPacketInfuserBlockEnabled message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Client.");
		if (ctx.side != Side.CLIENT) {
			System.err.println("NetworkPacketInfuserBlockEnabled received on wrong side: " + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketInfuserBlockEnabled was invalid" + message.toString());
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
	private void processMessage(NetworkPacketInfuserBlockEnabled message, Minecraft minecraft) {
		WorldClient worldClient = minecraft.theWorld;
		Vec3d pos = message.getTilePos();
		
		TileEntity tE = worldClient.getTileEntity(new BlockPos(pos.xCoord, pos.yCoord, pos.zCoord));
		if(tE instanceof TileEnityInfuserBlock){
			TileEnityInfuserBlock t =  (TileEnityInfuserBlock) tE;
			t.changeParticleActive(message.getStatus());
		}
		else{
			//System.out.println("No/wrong TileEntity in MessageHandlerOnClientInfuserBlockEnabled! " + message.toString());
		}
	}
	
}
