package de.comeight.crystallogy.entity;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerClientDummy extends AbstractClientPlayer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public PlayerClientDummy(World world, GameProfile playerProfile) {
		super(world, playerProfile);
		//setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ItemHandler.vaporizer));
		//setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.shield));
		setRotationYawHead(0.0F);
		EntityPlayer eP = new EntityPlayer(world, playerProfile) {
			
			@Override
			public boolean isSpectator() {
				return false;
			}
			
			@Override
			public boolean isCreative() {
				return false;
			}
		};
		
	}
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
