package net.minecraft.world.gen.structure.template;

import net.minecraft.block.Block;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class PlacementSettings
{
    private Mirror mirror;
    private Rotation rotation;
    private boolean ignoreEntities;
    /** the type of block in the world that will get replaced by the structure */
    private Block replacedBlock;
    /** the chunk the structure is within */
    private ChunkCoordIntPair chunk;
    /** the bounds the structure is contained within */
    private StructureBoundingBox boundingBox;
    private boolean ignoreStructureBlock;

    public PlacementSettings()
    {
        this(Mirror.NONE, Rotation.NONE, false, (Block)null, (StructureBoundingBox)null);
    }

    public PlacementSettings(Mirror mirrorIn, Rotation rotationIn, boolean ignoreEntitiesIn, Block replacedBlockIn, StructureBoundingBox boundingBoxIn)
    {
        this.rotation = rotationIn;
        this.mirror = mirrorIn;
        this.ignoreEntities = ignoreEntitiesIn;
        this.replacedBlock = replacedBlockIn;
        this.chunk = null;
        this.boundingBox = boundingBoxIn;
        this.ignoreStructureBlock = true;
    }

    public PlacementSettings copy()
    {
        return (new PlacementSettings(this.mirror, this.rotation, this.ignoreEntities, this.replacedBlock, this.boundingBox)).setChunk(this.chunk).setIgnoreStructureBlock(this.ignoreStructureBlock);
    }

    public PlacementSettings setMirror(Mirror mirrorIn)
    {
        this.mirror = mirrorIn;
        return this;
    }

    public PlacementSettings setRotation(Rotation rotationIn)
    {
        this.rotation = rotationIn;
        return this;
    }

    public PlacementSettings setIgnoreEntities(boolean ignoreEntitiesIn)
    {
        this.ignoreEntities = ignoreEntitiesIn;
        return this;
    }

    public PlacementSettings setReplacedBlock(Block replacedBlockIn)
    {
        this.replacedBlock = replacedBlockIn;
        return this;
    }

    public PlacementSettings setChunk(ChunkCoordIntPair chunkPosIn)
    {
        this.chunk = chunkPosIn;
        return this;
    }

    public PlacementSettings setBoundingBox(StructureBoundingBox boundingBoxIn)
    {
        this.boundingBox = boundingBoxIn;
        return this;
    }

    public Mirror getMirror()
    {
        return this.mirror;
    }

    public PlacementSettings setIgnoreStructureBlock(boolean ignoreStructureBlockIn)
    {
        this.ignoreStructureBlock = ignoreStructureBlockIn;
        return this;
    }

    public Rotation getRotation()
    {
        return this.rotation;
    }

    public boolean getIgnoreEntities()
    {
        return this.ignoreEntities;
    }

    public Block getReplacedBlock()
    {
        return this.replacedBlock;
    }

    public StructureBoundingBox getBoundingBox()
    {
        if (this.boundingBox == null && this.chunk != null)
        {
            this.setBoundingBoxFromChunk();
        }

        return this.boundingBox;
    }

    public boolean getIgnoreStructureBlock()
    {
        return this.ignoreStructureBlock;
    }

    void setBoundingBoxFromChunk()
    {
        this.boundingBox = this.getBoundingBoxFromChunk(this.chunk);
    }

    private StructureBoundingBox getBoundingBoxFromChunk(ChunkCoordIntPair p_186216_1_)
    {
        if (p_186216_1_ == null)
        {
            return null;
        }
        else
        {
            int i = p_186216_1_.chunkXPos * 16;
            int j = p_186216_1_.chunkZPos * 16;
            return new StructureBoundingBox(i, 0, j, i + 16 - 1, 255, j + 16 - 1);
        }
    }
}