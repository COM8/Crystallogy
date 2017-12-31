package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.client.particles.SquareParticle;
import de.comeight.crystallogy.items.BaseItem;
import de.comeight.crystallogy.network.NetworkMessageParticle;
import de.comeight.crystallogy.network.ParticleContainer;
import de.comeight.crystallogy.util.NetworkUtils;
import de.comeight.crystallogy.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DebugItem extends BaseItem {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "debugItem";
    private SquareParticle particle;

    //-----------------------------------------------Constructor:-------------------------------------------
    public DebugItem() {
        super(ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(worldIn.isRemote) {
            if(playerIn.isSneaking() && particle != null) {
                particle.setExpired();
                particle = null;
            }
            else if(particle == null) {
                BlockPos pos = playerIn.getPosition();
                Vec3d pPos = new Vec3d(pos.getX(), pos.getY() + 1.5, pos.getZ());
                particle = new SquareParticle(worldIn, pPos);
                Minecraft.getMinecraft().effectRenderer.addEffect(particle);
            }
            else {
                spawnParticles(playerIn.getPosition().add(2,0,0), worldIn);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void spawnParticles(BlockPos pos, World worldIn) {
        /*for (int i = 0; i < 10000; i++) {
            Vec3d pPos = new Vec3d(pos.getX() - 0.25+ Util.RANDOM.nextFloat() * 0.5, pos.getY() + 2, pos.getZ() - 0.25+ Util.RANDOM.nextFloat() * 0.5);
            particle = new SquareParticle(worldIn, pPos);
            Minecraft.getMinecraft().effectRenderer.addEffect(particle);
        }*/
        SquareParticle p = new SquareParticle(worldIn, new Vec3d(pos));
        ParticleContainer pC = p.toParticleContainer();
        pC.area = new Vec3d(1, 0.5, 1);
        pC.randomColor = true;
        pC.particleCount = 10000;
        NetworkUtils.sendToServer(new NetworkMessageParticle(pC));
    }


    //-----------------------------------------------Events:------------------------------------------------

}