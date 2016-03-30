package de.comeight.crystallogy.tileEntitys;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.entity.PlayerClientDummy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityPlayerJar extends TileEntity {

	//-----------------------------------------------Variabeln:---------------------------------------------
	private PlayerClientDummy player;

	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityPlayerJar(World world) {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public PlayerClientDummy getPlayer() {
		return player;
	}

	public void setPlayer(PlayerClientDummy player) {
		this.player = player;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
