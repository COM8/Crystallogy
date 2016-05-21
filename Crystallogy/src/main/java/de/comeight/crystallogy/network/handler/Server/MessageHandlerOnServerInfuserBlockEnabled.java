package de.comeight.crystallogy.network.handler.Server;

import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.util.NetworkUtilitis;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnServerInfuserBlockEnabled implements IMessageHandler<NetworkPacketInfuserBlockEnabled, IMessage>{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnServerInfuserBlockEnabled() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void processMessage(NetworkPacketInfuserBlockEnabled message, EntityPlayerMP sendingPlayer) {
		NetworkUtilitis.sendToAllPlayers(message, sendingPlayer);
	}

	@Override
	public IMessage onMessage(final NetworkPacketInfuserBlockEnabled message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Server.");
		if (ctx.side != Side.SERVER) {
			System.err.println("NetworkPacketInfuserBlockEnabled received on wrong side:" + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketInfuserBlockEnabled was invalid " + message.toString());
			return null;
		}
		final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
		if (sendingPlayer == null) {
			System.err.println("EntityPlayerMP was null when NetworkPacketInfuserBlockEnabled was received");
			return null;
		}

		final WorldServer playerWorldServer = sendingPlayer.getServerWorld();
		playerWorldServer.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, sendingPlayer);
			}
		});
		return null;
	}
}
