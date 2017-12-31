package de.comeight.crystallogy.entities;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.client.particles.SquareParticle;
import de.comeight.crystallogy.network.NetworkMessageParticle;
import de.comeight.crystallogy.network.ParticleContainer;
import de.comeight.crystallogy.util.NetworkUtils;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
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
    private int noCollisionTicks;

    //-----------------------------------------------Constructor:-------------------------------------------
    public EntityBouncyCrystal(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
        this.collisionsLeft = 20;
        this.noCollisionTicks = 0;
        setNoGravity(true);
        setGlowing(true);
        setAlwaysRenderNameTag(true);
    }

    public EntityBouncyCrystal(World worldIn) {
        super(worldIn);
        this.collisionsLeft = 20;
        this.noCollisionTicks = 0;
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
        IBlockState state = world.getBlockState(pos);
        if(state == null || state.getBlock() instanceof BlockLiquid){
            return;
        }
        if(!world.isRemote) {
            if(!collided) {
                collisionsLeft--;
            }
            if(collisionsLeft <= 0) {
                world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_ARMORSTAND_HIT, SoundCategory.NEUTRAL, 0.5F, 0.4F + world.rand.nextFloat() * 0.5F);
                setDead();
                return;
            }
            else {
                if(state != null){
                    world.destroyBlock(result.getBlockPos(), true);
                    spawnParticle(result.getBlockPos());
                }
            }
        }

        noCollisionTicks = 0;
        motionX = -motionX;
        motionY = -motionY;
        motionZ = -motionZ;
    }

    private void spawnParticle(BlockPos pos) {
        if(world.isRemote) {
            return;
        }
        SquareParticle p = new SquareParticle(world, new Vec3d(pos).addVector(0, 0.45, 0));
        ParticleContainer pC = p.toParticleContainer();
        pC.area = new Vec3d(1, 0.5, 1);
        pC.randomColor = true;
        pC.particleCount = 1000;
        NetworkUtils.sendToServer(new NetworkMessageParticle(pC));
    }

    @Override
    public void onUpdate() {
        double mX = motionX;
        double mY = motionY;
        double mZ = motionZ;
        super.onUpdate();
        if(noCollisionTicks > 0){
            motionX = mX;
            motionY = mY;
            motionZ = mZ;
        }
        else{
            motionX *= 1.02;
            motionY *= 1.02;
            motionZ *= 1.02;
        }
        noCollisionTicks++;
        if(noCollisionTicks >= 1200){
            setDead();
        }
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