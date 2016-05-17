package net.minecraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityAttach;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTracker
{
    private static final Logger logger = LogManager.getLogger();
    private final WorldServer theWorld;
    private Set<EntityTrackerEntry> trackedEntities = Sets.<EntityTrackerEntry>newHashSet();
    private IntHashMap<EntityTrackerEntry> trackedEntityHashTable = new IntHashMap();
    private int maxTrackingDistanceThreshold;

    public EntityTracker(WorldServer theWorldIn)
    {
        this.theWorld = theWorldIn;
        this.maxTrackingDistanceThreshold = theWorldIn.getMinecraftServer().getPlayerList().getEntityViewDistance();
    }

    public static long func_187253_a(double p_187253_0_)
    {
        return MathHelper.floor_double_long(p_187253_0_ * 4096.0D);
    }

    @SideOnly(Side.CLIENT)
    public static void func_187254_a(Entity p_187254_0_, double p_187254_1_, double p_187254_3_, double p_187254_5_)
    {
        p_187254_0_.serverPosX = func_187253_a(p_187254_1_);
        p_187254_0_.serverPosY = func_187253_a(p_187254_3_);
        p_187254_0_.serverPosZ = func_187253_a(p_187254_5_);
    }

    public void trackEntity(Entity entityIn)
    {
        if (net.minecraftforge.fml.common.registry.EntityRegistry.instance().tryTrackingEntity(this, entityIn)) return;

        if (entityIn instanceof EntityPlayerMP)
        {
            this.trackEntity(entityIn, 512, 2);
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entityIn;

            for (EntityTrackerEntry entitytrackerentry : this.trackedEntities)
            {
                if (entitytrackerentry.getTrackedEntity() != entityplayermp)
                {
                    entitytrackerentry.updatePlayerEntity(entityplayermp);
                }
            }
        }
        else if (entityIn instanceof EntityFishHook)
        {
            this.addEntityToTracker(entityIn, 64, 5, true);
        }
        else if (entityIn instanceof EntityArrow)
        {
            this.addEntityToTracker(entityIn, 64, 20, false);
        }
        else if (entityIn instanceof EntitySmallFireball)
        {
            this.addEntityToTracker(entityIn, 64, 10, false);
        }
        else if (entityIn instanceof EntityFireball)
        {
            this.addEntityToTracker(entityIn, 64, 10, false);
        }
        else if (entityIn instanceof EntitySnowball)
        {
            this.addEntityToTracker(entityIn, 64, 10, true);
        }
        else if (entityIn instanceof EntityEnderPearl)
        {
            this.addEntityToTracker(entityIn, 64, 10, true);
        }
        else if (entityIn instanceof EntityEnderEye)
        {
            this.addEntityToTracker(entityIn, 64, 4, true);
        }
        else if (entityIn instanceof EntityEgg)
        {
            this.addEntityToTracker(entityIn, 64, 10, true);
        }
        else if (entityIn instanceof EntityPotion)
        {
            this.addEntityToTracker(entityIn, 64, 10, true);
        }
        else if (entityIn instanceof EntityExpBottle)
        {
            this.addEntityToTracker(entityIn, 64, 10, true);
        }
        else if (entityIn instanceof EntityFireworkRocket)
        {
            this.addEntityToTracker(entityIn, 64, 10, true);
        }
        else if (entityIn instanceof EntityItem)
        {
            this.addEntityToTracker(entityIn, 64, 20, true);
        }
        else if (entityIn instanceof EntityMinecart)
        {
            this.addEntityToTracker(entityIn, 80, 3, true);
        }
        else if (entityIn instanceof EntityBoat)
        {
            this.addEntityToTracker(entityIn, 80, 3, true);
        }
        else if (entityIn instanceof EntitySquid)
        {
            this.addEntityToTracker(entityIn, 64, 3, true);
        }
        else if (entityIn instanceof EntityWither)
        {
            this.addEntityToTracker(entityIn, 80, 3, false);
        }
        else if (entityIn instanceof EntityShulkerBullet)
        {
            this.addEntityToTracker(entityIn, 80, 3, true);
        }
        else if (entityIn instanceof EntityBat)
        {
            this.addEntityToTracker(entityIn, 80, 3, false);
        }
        else if (entityIn instanceof EntityDragon)
        {
            this.addEntityToTracker(entityIn, 160, 3, true);
        }
        else if (entityIn instanceof IAnimals)
        {
            this.addEntityToTracker(entityIn, 80, 3, true);
        }
        else if (entityIn instanceof EntityTNTPrimed)
        {
            this.addEntityToTracker(entityIn, 160, 10, true);
        }
        else if (entityIn instanceof EntityFallingBlock)
        {
            this.addEntityToTracker(entityIn, 160, 20, true);
        }
        else if (entityIn instanceof EntityHanging)
        {
            this.addEntityToTracker(entityIn, 160, Integer.MAX_VALUE, false);
        }
        else if (entityIn instanceof EntityArmorStand)
        {
            this.addEntityToTracker(entityIn, 160, 3, true);
        }
        else if (entityIn instanceof EntityXPOrb)
        {
            this.addEntityToTracker(entityIn, 160, 20, true);
        }
        else if (entityIn instanceof EntityAreaEffectCloud)
        {
            this.addEntityToTracker(entityIn, 160, Integer.MAX_VALUE, true);
        }
        else if (entityIn instanceof EntityEnderCrystal)
        {
            this.addEntityToTracker(entityIn, 256, Integer.MAX_VALUE, false);
        }
    }

    public void trackEntity(Entity entityIn, int trackingRange, int updateFrequency)
    {
        this.addEntityToTracker(entityIn, trackingRange, updateFrequency, false);
    }

    /**
     * Args : Entity, trackingRange, updateFrequency, sendVelocityUpdates
     */
    public void addEntityToTracker(Entity entityIn, int trackingRange, final int updateFrequency, boolean sendVelocityUpdates)
    {
        try
        {
            if (this.trackedEntityHashTable.containsItem(entityIn.getEntityId()))
            {
                throw new IllegalStateException("Entity is already tracked!");
            }

            EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entityIn, trackingRange, this.maxTrackingDistanceThreshold, updateFrequency, sendVelocityUpdates);
            this.trackedEntities.add(entitytrackerentry);
            this.trackedEntityHashTable.addKey(entityIn.getEntityId(), entitytrackerentry);
            entitytrackerentry.updatePlayerEntities(this.theWorld.playerEntities);
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding entity to track");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Entity To Track");
            crashreportcategory.addCrashSection("Tracking range", trackingRange + " blocks");
            crashreportcategory.addCrashSectionCallable("Update interval", new Callable<String>()
            {
                public String call() throws Exception
                {
                    String s = "Once per " + updateFrequency + " ticks";

                    if (updateFrequency == Integer.MAX_VALUE)
                    {
                        s = "Maximum (" + s + ")";
                    }

                    return s;
                }
            });
            entityIn.addEntityCrashInfo(crashreportcategory);
            ((EntityTrackerEntry)this.trackedEntityHashTable.lookup(entityIn.getEntityId())).getTrackedEntity().addEntityCrashInfo(crashreport.makeCategory("Entity That Is Already Tracked"));

            try
            {
                throw new ReportedException(crashreport);
            }
            catch (ReportedException reportedexception)
            {
                logger.error((String)"\"Silently\" catching entity tracking error.", (Throwable)reportedexception);
            }
        }
    }

    public void untrackEntity(Entity entityIn)
    {
        if (entityIn instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entityIn;

            for (EntityTrackerEntry entitytrackerentry : this.trackedEntities)
            {
                entitytrackerentry.removeFromTrackedPlayers(entityplayermp);
            }
        }

        EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)this.trackedEntityHashTable.removeObject(entityIn.getEntityId());

        if (entitytrackerentry1 != null)
        {
            this.trackedEntities.remove(entitytrackerentry1);
            entitytrackerentry1.sendDestroyEntityPacketToTrackedPlayers();
        }
    }

    public void updateTrackedEntities()
    {
        List<EntityPlayerMP> list = Lists.<EntityPlayerMP>newArrayList();

        for (EntityTrackerEntry entitytrackerentry : this.trackedEntities)
        {
            entitytrackerentry.updatePlayerList(this.theWorld.playerEntities);

            if (entitytrackerentry.playerEntitiesUpdated)
            {
                Entity entity = entitytrackerentry.getTrackedEntity();

                if (entity instanceof EntityPlayerMP)
                {
                    list.add((EntityPlayerMP)entity);
                }
            }
        }

        for (int i = 0; i < ((List)list).size(); ++i)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)list.get(i);

            for (EntityTrackerEntry entitytrackerentry1 : this.trackedEntities)
            {
                if (entitytrackerentry1.getTrackedEntity() != entityplayermp)
                {
                    entitytrackerentry1.updatePlayerEntity(entityplayermp);
                }
            }
        }
    }

    public void func_180245_a(EntityPlayerMP p_180245_1_)
    {
        for (EntityTrackerEntry entitytrackerentry : this.trackedEntities)
        {
            if (entitytrackerentry.getTrackedEntity() == p_180245_1_)
            {
                entitytrackerentry.updatePlayerEntities(this.theWorld.playerEntities);
            }
            else
            {
                entitytrackerentry.updatePlayerEntity(p_180245_1_);
            }
        }
    }

    public void sendToAllTrackingEntity(Entity entityIn, Packet<?> p_151247_2_)
    {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(entityIn.getEntityId());

        if (entitytrackerentry != null)
        {
            entitytrackerentry.sendPacketToTrackedPlayers(p_151247_2_);
        }
    }

    /* ======================================== FORGE START =====================================*/

    // don't expose the EntityTrackerEntry directly so mods can't mess with the data in there as easily
    /**
     * Get all players tracking the given Entity. The Entity must be part of the World that this Tracker belongs to.
     * @param entity the Entity
     * @return all players tracking the Entity
     */
    public Set<? extends net.minecraft.entity.player.EntityPlayer> getTrackingPlayers(Entity entity)
    {
        EntityTrackerEntry entry = (EntityTrackerEntry) trackedEntityHashTable.lookup(entity.getEntityId());
        if (entry == null)
            return java.util.Collections.emptySet();
        else
            return java.util.Collections.unmodifiableSet(entry.trackingPlayers);
    }

    /* ======================================== FORGE END   =====================================*/

    public void func_151248_b(Entity entityIn, Packet<?> p_151248_2_)
    {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(entityIn.getEntityId());

        if (entitytrackerentry != null)
        {
            entitytrackerentry.func_151261_b(p_151248_2_);
        }
    }

    public void removePlayerFromTrackers(EntityPlayerMP p_72787_1_)
    {
        for (EntityTrackerEntry entitytrackerentry : this.trackedEntities)
        {
            entitytrackerentry.removeTrackedPlayerSymmetric(p_72787_1_);
        }
    }

    /**
     * Send packets to player for every tracked entity in this chunk that is either leashed to something or someone, or
     * has passengers
     */
    public void sendLeashedEntitiesInChunk(EntityPlayerMP player, Chunk chunkIn)
    {
        List<Entity> list = Lists.<Entity>newArrayList();
        List<Entity> list1 = Lists.<Entity>newArrayList();

        for (EntityTrackerEntry entitytrackerentry : this.trackedEntities)
        {
            Entity entity = entitytrackerentry.getTrackedEntity();

            if (entity != player && entity.chunkCoordX == chunkIn.xPosition && entity.chunkCoordZ == chunkIn.zPosition)
            {
                entitytrackerentry.updatePlayerEntity(player);

                if (entity instanceof EntityLiving && ((EntityLiving)entity).getLeashedToEntity() != null)
                {
                    list.add(entity);
                }

                if (!entity.getPassengers().isEmpty())
                {
                    list1.add(entity);
                }
            }
        }

        if (!list.isEmpty())
        {
            for (Entity entity1 : list)
            {
                player.playerNetServerHandler.sendPacket(new SPacketEntityAttach(entity1, ((EntityLiving)entity1).getLeashedToEntity()));
            }
        }

        if (!list1.isEmpty())
        {
            for (Entity entity2 : list1)
            {
                player.playerNetServerHandler.sendPacket(new SPacketSetPassengers(entity2));
            }
        }
    }

    public void func_187252_a(int p_187252_1_)
    {
        this.maxTrackingDistanceThreshold = (p_187252_1_ - 1) * 16;

        for (EntityTrackerEntry entitytrackerentry : this.trackedEntities)
        {
            entitytrackerentry.func_187259_a(this.maxTrackingDistanceThreshold);
        }
    }
}