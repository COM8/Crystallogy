package de.comeight.crystallogy.entity;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
