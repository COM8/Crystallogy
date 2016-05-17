package net.minecraft.entity.boss.dragon.phase;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.gen.feature.WorldGenEndPodium;

public class PhaseTakeoff extends PhaseBase
{
    private boolean field_188697_b;
    private PathEntity field_188698_c;
    private Vec3d field_188699_d;

    public PhaseTakeoff(EntityDragon dragonIn)
    {
        super(dragonIn);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        if (this.field_188697_b)
        {
            this.field_188697_b = false;
            this.func_188695_j();
        }
        else
        {
            BlockPos blockpos = this.dragon.worldObj.getTopSolidOrLiquidBlock(WorldGenEndPodium.field_186139_a);
            double d0 = this.dragon.getDistanceSqToCenter(blockpos);

            if (d0 > 100.0D)
            {
                this.dragon.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
            }
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.field_188697_b = true;
        this.field_188698_c = null;
        this.field_188699_d = null;
    }

    private void func_188695_j()
    {
        int i = this.dragon.initPathPoints();
        Vec3d vec3d = this.dragon.func_184665_a(1.0F);
        int j = this.dragon.getNearestPpIdx(-vec3d.xCoord * 40.0D, 105.0D, -vec3d.zCoord * 40.0D);

        if (this.dragon.getFightManager() != null && this.dragon.getFightManager().getNumAliveCrystals() >= 0)
        {
            j = j % 12;

            if (j < 0)
            {
                j += 12;
            }
        }
        else
        {
            j = j - 12;
            j = j & 7;
            j = j + 12;
        }

        this.field_188698_c = this.dragon.findPath(i, j, (PathPoint)null);

        if (this.field_188698_c != null)
        {
            this.field_188698_c.incrementPathIndex();
            this.func_188696_k();
        }
    }

    private void func_188696_k()
    {
        Vec3d vec3d = this.field_188698_c.func_186310_f();
        this.field_188698_c.incrementPathIndex();
        double d0;

        while (true)
        {
            d0 = vec3d.yCoord + (double)(this.dragon.getRNG().nextFloat() * 20.0F);

            if (d0 >= vec3d.yCoord)
            {
                break;
            }
        }

        this.field_188699_d = new Vec3d(vec3d.xCoord, d0, vec3d.zCoord);
    }

    /**
     * Returns the location the dragon is flying toward
     */
    public Vec3d getTargetLocation()
    {
        return this.field_188699_d;
    }

    public PhaseList<PhaseTakeoff> getPhaseList()
    {
        return PhaseList.TAKEOFF;
    }
}