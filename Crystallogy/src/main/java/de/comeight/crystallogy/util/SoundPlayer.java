package de.comeight.crystallogy.util;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundPlayer {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static SoundEvent CRUSHER = new SoundEvent(new ResourceLocation(CrystallogyBase.MODID + ":crusher"));
	
	//-----------------------------------------------Constructor:-------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void playSoundForAll(Entity entity, SoundEvent sound, float volume, float pitch) {
		entity.worldObj.playSound(null, entity.getPosition(), sound, entity.getSoundCategory(), volume, pitch);
	}

	public static void PlaySoundForPlayer(Entity entity, SoundEvent sound, float volume, float pitch) {
		if (entity instanceof EntityPlayerMP) {
			//((EntityPlayerMP) entity).playerNetServerHandler.sendPacket(new SPacketSoundEffect(sound, entity.getSoundCategory(), entity.posX, entity.posY, entity.posZ, volume, pitch)); //TODO Reimplement
		}
	}

}
