package net.minecraft.entity.boss.dragon.phase;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.gen.feature.WorldGenEndPodium;

public class PhaseHoldingPattern extends PhaseBase
{
    private PathEntity field_188677_b;
    private Vec3d field_188678_c;
    private boolean field_188679_d;

    public PhaseHoldingPattern(EntityDragon dragonIn)
    {
        super(dragonIn);
    }

    public PhaseList<PhaseHoldingPattern> getPhaseList()
    {
        return PhaseList.HOLDING_PATTERN;
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        double d0 = this.field_188678_c == null ? 0.0D : this.field_188678_c.squareDistanceTo(this.dragon.posX, this.dragon.posY, this.dragon.posZ);

        if (d0 < 100.0D || d0 > 22500.0D || this.dragon.isCollidedHorizontally || this.dragon.isCollidedVertically)
        {
            this.func_188675_j();
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.field_188677_b = null;
        this.field_188678_c = null;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    public Vec3d getTargetLocation()
    {
        return this.field_188678_c;
    }

    private void func_188675_j()
    {
        if (this.field_188677_b != null && this.field_188677_b.isFinished())
        {
            BlockPos blockpos = this.dragon.worldObj.getTopSolidOrLiquidBlock(new BlockPos(WorldGenEndPodium.field_186139_a));
            int i = this.dragon.getFightManager() == null ? 0 : this.dragon.getFightManager().getNumAliveCrystals();

            if (this.dragon.getRNG().nextInt(i + 3) == 0)
            {
                this.dragon.getPhaseManager().setPhase(PhaseList.LANDING_APPROACH);
                return;
            }

            double d0 = 64.0D;
            EntityPlayer entityplayer = this.dragon.worldObj.func_184139_a(blockpos, d0, d0);

            if (entityplayer != null)
            {
                d0 = entityplayer.getDistanceSqToCenter(blockpos) / 512.0D;
            }

            if (entityplayer != null && (this.dragon.getRNG().nextInt(MathHelper.abs_int((int)d0) + 2) == 0 || this.dragon.getRNG().nextInt(i + 2) == 0))
            {
                this.func_188674_a(entityplayer);
                return;
            }
        }

        if (this.field_188677_b == null || this.field_188677_b.isFinished())
        {
            int j = this.dragon.initPathPoints();
            int k = j;

            if (this.dragon.getRNG().nextInt(8) == 0)
            {
                this.field_188679_d = !this.field_188679_d;
                k = j + 6;
            }

            if (this.field_188679_d)
            {
                ++k;
            }
            else
            {
                --k;
            }

            if (this.dragon.getFightManager() != null && this.dragon.getFightManager().getNumAliveCrystals() >= 0)
            {
                k = k % 12;

                if (k < 0)
                {
                    k += 12;
                }
            }
            else
            {
                k = k - 12;
                k = k & 7;
                k = k + 12;
            }

            this.field_188677_b = this.dragon.findPath(j, k, (PathPoint)null);

            if (this.field_188677_b != null)
            {
                this.field_188677_b.incrementPathIndex();
            }
        }

        this.func_188676_k();
    }

    private void func_188674_a(EntityPlayer p_188674_1_)
    {
        this.dragon.getPhaseManager().setPhase(PhaseList.STRAFE_PLAYER);
        ((PhaseStrafePlayer)this.dragon.getPhaseManager().getPhase(PhaseList.STRAFE_PLAYER)).func_188686_a(p_188674_1_);
    }

    private void func_188676_k()
    {
        if (this.field_188677_b != null && !this.field_188677_b.isFinished())
        {
            Vec3d vec3d = this.field_188677_b.func_186310_f();
            this.field_188677_b.incrementPathIndex();
            double d0 = vec3d.xCoord;
            double d1 = vec3d.zCoord;
            double d2;

            while (true)
            {
                d2 = vec3d.yCoord + (double)(this.dragon.getRNG().nextFloat() * 20.0F);

                if (d2 >= vec3d.yCoord)
                {
                    break;
                }
            }

            this.field_188678_c = new Vec3d(d0, d2, d1);
        }
    }

    public void onCrystalDestroyed(EntityEnderCrystal crystal, BlockPos pos, DamageSource dmgSrc, EntityPlayer plyr)
    {
        if (plyr != null)
        {
            this.func_188674_a(plyr);
        }
    }
}