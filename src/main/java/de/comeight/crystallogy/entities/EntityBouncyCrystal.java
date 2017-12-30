package de.comeight.crystallogy.entities;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.client.particles.SquareParticle;
import de.comeight.crystallogy.util.Util;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBouncyCrystal extends EntityThrowable {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String NAME = ":entityBouncyCrystal";
    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(Crystallogy.MOD_ID + ":bouncyCrystal");
    private int collisionsLeft;

    //-----------------------------------------------Constructor:-------------------------------------------
    public EntityBouncyCrystal(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
        this.collisionsLeft = 20;
        setNoGravity(true);
        setGlowing(true);
    }

    public EntityBouncyCrystal(World worldIn) {
        super(worldIn);
        this.collisionsLeft = 20;
        setNoGravity(true);
        setGlowing(true);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    protected void onImpact(RayTraceResult result) {
        BlockPos pos = result.getBlockPos();
        if(isDead || pos == null) {
            return;
        }
        if(!world.isRemote) {
            if(!collided) {
                collisionsLeft--;
            }
            if(collisionsLeft <= 0) {
                world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_ARMORSTAND_HIT, SoundCategory.NEUTRAL, 0.5F, 0.4F + Util.RANDOM.nextFloat() * 0.5F);
                setDead();
                return;
            }
        }
        else {
            IBlockState state = world.getBlockState(pos);
            if(state != null){
                world.destroyBlock(result.getBlockPos(), true);
                spawnParticle(result.getBlockPos());
            }
        }

        if(Util.RANDOM.nextInt(3) == 0) {
            motionX *= -(1 + Util.RANDOM.nextFloat() * 0.2);
            motionY *= -(1 + Util.RANDOM.nextFloat() * 0.2);
            motionZ *= -(1 + Util.RANDOM.nextFloat() * 0.2);
        }
        motionX *= -1;
        motionY *= -1;
        motionZ *= -1;
    }

    private void spawnParticle(BlockPos pos) {
        if(!world.isRemote) {
            return;
        }

        for (int i = 0; i < 1000; i++) {
            Vec3d pPos = new Vec3d(pos.getX() + Util.RANDOM.nextFloat(), pos.getY() + 0.5, pos.getZ() + Util.RANDOM.nextFloat());
            Minecraft.getMinecraft().effectRenderer.addEffect(new SquareParticle(world, pPos));
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        motionX *= 1.01;
        motionY *= 1.01;
        motionZ *= 1.01;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = super.serializeNBT();
        nbt.setInteger("collisionsLeft", collisionsLeft);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        super.deserializeNBT(nbt);
        collisionsLeft = nbt.getInteger("collisionsLeft");
    }

    //-----------------------------------------------Events:------------------------------------------------

}