package de.comeight.crystallogy.entity;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.world.World;

public class PlayerClientDummy extends AbstractClientPlayer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public PlayerClientDummy(World world, GameProfile playerProfile) {
		super(world, playerProfile);
		//setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ItemHandler.vaporizer));
		//setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.shield));
		setRotationYawHead(0.0F);
	}
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
