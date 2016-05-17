package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderServer implements IChunkProvider
{
    private static final Logger logger = LogManager.getLogger();
    private final Set<Long> droppedChunksSet = Collections.<Long>newSetFromMap(new ConcurrentHashMap());
    public final IChunkGenerator chunkGenerator;
    public final IChunkLoader chunkLoader;
    public final LongHashMap<Chunk> id2ChunkMap = new LongHashMap();
    public final List<Chunk> loadedChunks = Lists.<Chunk>newArrayList();
    public final WorldServer worldObj;
    private Set<Long> loadingChunks = com.google.common.collect.Sets.newHashSet();

    public ChunkProviderServer(WorldServer worldObjIn, IChunkLoader chunkLoaderIn, IChunkGenerator chunkGeneratorIn)
    {
        this.worldObj = worldObjIn;
        this.chunkLoader = chunkLoaderIn;
        this.chunkGenerator = chunkGeneratorIn;
    }

    public List<Chunk> getLoadedChunks()
    {
        return this.loadedChunks;
    }

    public void dropChunk(int x, int z)
    {
        if (this.worldObj.provider.canDropChunk(x, z))
        {
            this.droppedChunksSet.add(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(x, z)));
        }
    }

    /**
     * marks all chunks for unload, ignoring those near the spawn
     */
    public void unloadAllChunks()
    {
        for (Chunk chunk : this.loadedChunks)
        {
            this.dropChunk(chunk.xPosition, chunk.zPosition);
        }
    }

    public Chunk getLoadedChunk(int x, int z)
    {
        long i = ChunkCoordIntPair.chunkXZ2Int(x, z);
        Chunk chunk = (Chunk)this.id2ChunkMap.getValueByKey(i);
        this.droppedChunksSet.remove(Long.valueOf(i));
        return chunk;
    }

    public Chunk loadChunk(int x, int z)
    {
        Chunk chunk = this.getLoadedChunk(x, z);

        if (chunk == null)
        {
            chunk = this.loadChunkFromFile(x, z);

            if (chunk != null)
            {
                this.id2ChunkMap.add(ChunkCoordIntPair.chunkXZ2Int(x, z), chunk);
                this.loadedChunks.add(chunk);
                chunk.onChunkLoad();
                chunk.populateChunk(this, this.chunkGenerator);
            }
        }

        return chunk;
    }

    public Chunk provideChunk(int x, int z)
    {
        return loadChunk(x, z, null);
    }

    public Chunk loadChunk(int X, int Z, Runnable runnable)
    {
        long pos = ChunkCoordIntPair.chunkXZ2Int(X, Z);
        this.droppedChunksSet.remove(Long.valueOf(pos));
        Chunk chunk = this.id2ChunkMap.getValueByKey(pos);
        net.minecraft.world.chunk.storage.AnvilChunkLoader loader = null;

        if (this.chunkLoader instanceof net.minecraft.world.chunk.storage.AnvilChunkLoader)
        {
            loader = (net.minecraft.world.chunk.storage.AnvilChunkLoader) this.chunkLoader;
        }

        // We can only use the queue for already generated chunks
        if (chunk == null && loader != null && loader.chunkExists(this.worldObj, X, Z))
        {
            if (runnable != null)
            {
                net.minecraftforge.common.chunkio.ChunkIOExecutor.queueChunkLoad(this.worldObj, loader, this, X, Z, runnable);
                return null;
            }
            else
            {
                chunk = net.minecraftforge.common.chunkio.ChunkIOExecutor.syncChunkLoad(this.worldObj, loader, this, X, Z);
            }
        }
        else if (chunk == null)
        {
            chunk = this.originalLoadChunk(X, Z);
        }

        // If we didn't load the chunk async and have a callback run it now
        if (runnable != null)
        {
            runnable.run();
        }

        return chunk;
    }

    public Chunk originalLoadChunk(int x, int z)
    {
        Chunk chunk = this.loadChunk(x, z);

        if (chunk == null)
        {
            long i = ChunkCoordIntPair.chunkXZ2Int(x, z);
            if (!loadingChunks.add(i))
            {
                net.minecraftforge.fml.common.FMLLog.bigWarning("There is an attempt to load a chunk (%d,%d) in di    >mension %d that is already being loaded. This will cause weird chunk breakages.", x, z, worldObj.provider.getDimension());
            }
            chunk = net.minecraftforge.common.ForgeChunkManager.fetchDormantChunk(i, this.worldObj);

            if (chunk == null)
                chunk = this.loadChunkFromFile(x, z);

            if (chunk == null)
            {
                try
                {
                    chunk = this.chunkGenerator.provideChunk(x, z);
                }
                catch (Throwable throwable)
                {
                    CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Exception generating new chunk");
                    CrashReportCategory crashreportcategory = crashreport.makeCategory("Chunk to be generated");
                    crashreportcategory.addCrashSection("Location", String.format("%d,%d", new Object[] {Integer.valueOf(x), Integer.valueOf(z)}));
                    crashreportcategory.addCrashSection("Position hash", Long.valueOf(i));
                    crashreportcategory.addCrashSection("Generator", this.chunkGenerator);
                    throw new ReportedException(crashreport);
                }
            }

            this.id2ChunkMap.add(i, chunk);
            this.loadedChunks.add(chunk);
            loadingChunks.remove(i);
            chunk.onChunkLoad();
            chunk.populateChunk(this, this.chunkGenerator);
        }

        return chunk;
    }

    private Chunk loadChunkFromFile(int x, int z)
    {
        try
        {
            Chunk chunk = this.chunkLoader.loadChunk(this.worldObj, x, z);

            if (chunk != null)
            {
                chunk.setLastSaveTime(this.worldObj.getTotalWorldTime());
                this.chunkGenerator.recreateStructures(chunk, x, z);
            }

            return chunk;
        }
        catch (Exception exception)
        {
            logger.error((String)"Couldn\'t load chunk", (Throwable)exception);
            return null;
        }
    }

    private void saveChunkExtraData(Chunk chunkIn)
    {
        try
        {
            this.chunkLoader.saveExtraChunkData(this.worldObj, chunkIn);
        }
        catch (Exception exception)
        {
            logger.error((String)"Couldn\'t save entities", (Throwable)exception);
        }
    }

    private void saveChunkData(Chunk chunkIn)
    {
        try
        {
            chunkIn.setLastSaveTime(this.worldObj.getTotalWorldTime());
            this.chunkLoader.saveChunk(this.worldObj, chunkIn);
        }
        catch (IOException ioexception)
        {
            logger.error((String)"Couldn\'t save chunk", (Throwable)ioexception);
        }
        catch (MinecraftException minecraftexception)
        {
            logger.error((String)"Couldn\'t save chunk; already in use by another instance of Minecraft?", (Throwable)minecraftexception);
        }
    }

    public boolean saveChunks(boolean p_186027_1_)
    {
        int i = 0;
        List<Chunk> list = Lists.newArrayList(this.loadedChunks);

        for (int j = 0; j < ((List)list).size(); ++j)
        {
            Chunk chunk = (Chunk)list.get(j);

            if (p_186027_1_)
            {
                this.saveChunkExtraData(chunk);
            }

            if (chunk.needsSaving(p_186027_1_))
            {
                this.saveChunkData(chunk);
                chunk.setModified(false);
                ++i;

                if (i == 24 && !p_186027_1_)
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData()
    {
        this.chunkLoader.saveExtraData();
    }

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        if (!this.worldObj.disableLevelSaving)
        {
            for (ChunkCoordIntPair forced : this.worldObj.getPersistentChunks().keySet())
            {
                this.droppedChunksSet.remove(ChunkCoordIntPair.chunkXZ2Int(forced.chunkXPos, forced.chunkZPos));
            }

            for (int i = 0; i < 100; ++i)
            {
                if (!this.droppedChunksSet.isEmpty())
                {
                    Long olong = (Long)this.droppedChunksSet.iterator().next();
                    Chunk chunk = (Chunk)this.id2ChunkMap.getValueByKey(olong.longValue());

                    if (chunk != null)
                    {
                        chunk.onChunkUnload();
                        this.saveChunkData(chunk);
                        this.saveChunkExtraData(chunk);
                        this.id2ChunkMap.remove(olong.longValue());
                        this.loadedChunks.remove(chunk);
                        net.minecraftforge.common.ForgeChunkManager.putDormantChunk(ChunkCoordIntPair.chunkXZ2Int(chunk.xPosition, chunk.zPosition), chunk);
                        if(loadedChunks.size() == 0 && net.minecraftforge.common.ForgeChunkManager.getPersistentChunksFor(this.worldObj).size() == 0 && !this.worldObj.provider.getDimensionType().shouldLoadSpawn()){
                            net.minecraftforge.common.DimensionManager.unloadWorld(this.worldObj.provider.getDimension());
                            return false;
                        }

                    }

                    this.droppedChunksSet.remove(olong);
                }
            }

            this.chunkLoader.chunkTick();
        }

        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return !this.worldObj.disableLevelSaving;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "ServerChunkCache: " + this.id2ChunkMap.getNumHashElements() + " Drop: " + this.droppedChunksSet.size();
    }

    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        return this.chunkGenerator.getPossibleCreatures(creatureType, pos);
    }

    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position)
    {
        return this.chunkGenerator.getStrongholdGen(worldIn, structureName, position);
    }

    public int getLoadedChunkCount()
    {
        return this.id2ChunkMap.getNumHashElements();
    }

    /**
     * Checks to see if a chunk exists at x, z
     */
    public boolean chunkExists(int x, int z)
    {
        return this.id2ChunkMap.containsItem(ChunkCoordIntPair.chunkXZ2Int(x, z));
    }
}