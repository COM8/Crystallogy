package net.minecraft.world.gen.structure.template;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class Template
{
    private final List<Template.BlockInfo> blocks = Lists.<Template.BlockInfo>newArrayList();
    private final List<Template.EntityInfo> entities = Lists.<Template.EntityInfo>newArrayList();
    /** size of the structure */
    private BlockPos size = BlockPos.ORIGIN;
    /** The author of this template. */
    private String author = "?";

    public BlockPos getSize()
    {
        return this.size;
    }

    public void setAuthor(String p_186252_1_)
    {
        this.author = p_186252_1_;
    }

    public String getAuthor()
    {
        return this.author;
    }

    /**
     * takes blocks from the world and puts the data them into this template
     */
    public void takeBlocksFromWorld(World worldIn, BlockPos p_186254_2_, BlockPos p_186254_3_, boolean p_186254_4_, Block p_186254_5_)
    {
        if (p_186254_3_.getX() >= 1 && p_186254_3_.getY() >= 1 && p_186254_3_.getZ() >= 1)
        {
            BlockPos blockpos = p_186254_2_.add(p_186254_3_).add(-1, -1, -1);
            List<Template.BlockInfo> list = Lists.<Template.BlockInfo>newArrayList();
            List<Template.BlockInfo> list1 = Lists.<Template.BlockInfo>newArrayList();
            List<Template.BlockInfo> list2 = Lists.<Template.BlockInfo>newArrayList();
            BlockPos blockpos1 = new BlockPos(Math.min(p_186254_2_.getX(), blockpos.getX()), Math.min(p_186254_2_.getY(), blockpos.getY()), Math.min(p_186254_2_.getZ(), blockpos.getZ()));
            BlockPos blockpos2 = new BlockPos(Math.max(p_186254_2_.getX(), blockpos.getX()), Math.max(p_186254_2_.getY(), blockpos.getY()), Math.max(p_186254_2_.getZ(), blockpos.getZ()));
            this.size = p_186254_3_;

            for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(blockpos1, blockpos2))
            {
                BlockPos blockpos3 = blockpos$mutableblockpos.subtract(blockpos1);
                IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);

                if (p_186254_5_ == null || p_186254_5_ != iblockstate.getBlock())
                {
                    TileEntity tileentity = worldIn.getTileEntity(blockpos$mutableblockpos);

                    if (tileentity != null)
                    {
                        NBTTagCompound nbttagcompound = new NBTTagCompound();
                        tileentity.writeToNBT(nbttagcompound);
                        nbttagcompound.removeTag("x");
                        nbttagcompound.removeTag("y");
                        nbttagcompound.removeTag("z");
                        list1.add(new Template.BlockInfo(blockpos3, iblockstate, nbttagcompound));
                    }
                    else if (!iblockstate.isFullBlock() && !iblockstate.isFullCube())
                    {
                        list2.add(new Template.BlockInfo(blockpos3, iblockstate, (NBTTagCompound)null));
                    }
                    else
                    {
                        list.add(new Template.BlockInfo(blockpos3, iblockstate, (NBTTagCompound)null));
                    }
                }
            }

            this.blocks.clear();
            this.blocks.addAll(list);
            this.blocks.addAll(list1);
            this.blocks.addAll(list2);

            if (p_186254_4_)
            {
                this.takeEntitiesFromWorld(worldIn, blockpos1, blockpos2.add(1, 1, 1));
            }
            else
            {
                this.entities.clear();
            }
        }
    }

    /**
     * takes blocks from the world and puts the data them into this template
     */
    private void takeEntitiesFromWorld(World worldIn, BlockPos p_186255_2_, BlockPos p_186255_3_)
    {
        List<Entity> list = worldIn.<Entity>getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(p_186255_2_, p_186255_3_), new Predicate<Entity>()
        {
            public boolean apply(Entity p_apply_1_)
            {
                return !(p_apply_1_ instanceof EntityPlayer);
            }
        });
        this.entities.clear();

        for (Entity entity : list)
        {
            Vec3d vec3d = new Vec3d(entity.posX - (double)p_186255_2_.getX(), entity.posY - (double)p_186255_2_.getY(), entity.posZ - (double)p_186255_2_.getZ());
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            entity.writeToNBTOptional(nbttagcompound);
            BlockPos blockpos;

            if (entity instanceof EntityPainting)
            {
                blockpos = ((EntityPainting)entity).getHangingPosition().subtract(p_186255_2_);
            }
            else
            {
                blockpos = new BlockPos(vec3d);
            }

            this.entities.add(new Template.EntityInfo(vec3d, blockpos, nbttagcompound));
        }
    }

    public Map<BlockPos, String> func_186258_a(BlockPos p_186258_1_, PlacementSettings p_186258_2_)
    {
        Map<BlockPos, String> map = Maps.<BlockPos, String>newHashMap();
        StructureBoundingBox structureboundingbox = p_186258_2_.getBoundingBox();

        for (Template.BlockInfo template$blockinfo : this.blocks)
        {
            BlockPos blockpos = transformedBlockPos(p_186258_2_, template$blockinfo.pos).add(p_186258_1_);

            if (structureboundingbox == null || structureboundingbox.isVecInside(blockpos))
            {
                IBlockState iblockstate = template$blockinfo.blockState;

                if (iblockstate.getBlock() == Blocks.structure_block && template$blockinfo.tileentityData != null)
                {
                    TileEntityStructure.Mode tileentitystructure$mode = TileEntityStructure.Mode.valueOf(template$blockinfo.tileentityData.getString("mode"));

                    if (tileentitystructure$mode == TileEntityStructure.Mode.DATA)
                    {
                        map.put(blockpos, template$blockinfo.tileentityData.getString("metadata"));
                    }
                }
            }
        }

        return map;
    }

    public BlockPos func_186262_a(PlacementSettings p_186262_1_, BlockPos p_186262_2_, PlacementSettings p_186262_3_, BlockPos p_186262_4_)
    {
        BlockPos blockpos = transformedBlockPos(p_186262_1_, p_186262_2_);
        BlockPos blockpos1 = transformedBlockPos(p_186262_3_, p_186262_4_);
        return blockpos.subtract(blockpos1);
    }

    public static BlockPos transformedBlockPos(PlacementSettings p_186266_0_, BlockPos p_186266_1_)
    {
        return transformedBlockPos(p_186266_1_, p_186266_0_.getMirror(), p_186266_0_.getRotation());
    }

    public void func_186260_a(World worldIn, BlockPos p_186260_2_, PlacementSettings p_186260_3_)
    {
        p_186260_3_.setBoundingBoxFromChunk();
        this.addBlocksToWorld(worldIn, p_186260_2_, p_186260_3_);
    }

    /**
     * This takes the data stored in this instance and puts them into the world.
     */
    public void addBlocksToWorld(World worldIn, BlockPos p_186253_2_, PlacementSettings p_186253_3_)
    {
        if (!this.blocks.isEmpty() && this.size.getX() >= 1 && this.size.getY() >= 1 && this.size.getZ() >= 1)
        {
            Block block = p_186253_3_.getReplacedBlock();
            StructureBoundingBox structureboundingbox = p_186253_3_.getBoundingBox();

            for (Template.BlockInfo template$blockinfo : this.blocks)
            {
                Block block1 = template$blockinfo.blockState.getBlock();

                if ((block == null || block != block1) && (!p_186253_3_.getIgnoreStructureBlock() || block1 != Blocks.structure_block))
                {
                    BlockPos blockpos = transformedBlockPos(p_186253_3_, template$blockinfo.pos).add(p_186253_2_);

                    if (structureboundingbox == null || structureboundingbox.isVecInside(blockpos))
                    {
                        IBlockState iblockstate = template$blockinfo.blockState.withMirror(p_186253_3_.getMirror());
                        IBlockState iblockstate1 = iblockstate.withRotation(p_186253_3_.getRotation());

                        if (template$blockinfo.tileentityData != null)
                        {
                            TileEntity tileentity = worldIn.getTileEntity(blockpos);

                            if (tileentity != null)
                            {
                                if (tileentity instanceof IInventory)
                                {
                                    ((IInventory)tileentity).clear();
                                }

                                worldIn.setBlockState(blockpos, Blocks.barrier.getDefaultState(), 4);
                            }
                        }

                        if (worldIn.setBlockState(blockpos, iblockstate1, 2) && template$blockinfo.tileentityData != null)
                        {
                            TileEntity tileentity2 = worldIn.getTileEntity(blockpos);

                            if (tileentity2 != null)
                            {
                                template$blockinfo.tileentityData.setInteger("x", blockpos.getX());
                                template$blockinfo.tileentityData.setInteger("y", blockpos.getY());
                                template$blockinfo.tileentityData.setInteger("z", blockpos.getZ());
                                tileentity2.readFromNBT(template$blockinfo.tileentityData);
                            }
                        }
                    }
                }
            }

            for (Template.BlockInfo template$blockinfo1 : this.blocks)
            {
                if (block == null || block != template$blockinfo1.blockState.getBlock())
                {
                    BlockPos blockpos1 = transformedBlockPos(p_186253_3_, template$blockinfo1.pos).add(p_186253_2_);

                    if (structureboundingbox == null || structureboundingbox.isVecInside(blockpos1))
                    {
                        worldIn.notifyNeighborsRespectDebug(blockpos1, template$blockinfo1.blockState.getBlock());

                        if (template$blockinfo1.tileentityData != null)
                        {
                            TileEntity tileentity1 = worldIn.getTileEntity(blockpos1);

                            if (tileentity1 != null)
                            {
                                tileentity1.markDirty();
                            }
                        }
                    }
                }
            }

            if (!p_186253_3_.getIgnoreEntities())
            {
                this.addEntitiesToWorld(worldIn, p_186253_2_, p_186253_3_.getMirror(), p_186253_3_.getRotation(), structureboundingbox);
            }
        }
    }

    private void addEntitiesToWorld(World worldIn, BlockPos p_186263_2_, Mirror p_186263_3_, Rotation p_186263_4_, StructureBoundingBox p_186263_5_)
    {
        for (Template.EntityInfo template$entityinfo : this.entities)
        {
            BlockPos blockpos = transformedBlockPos(template$entityinfo.blockPos, p_186263_3_, p_186263_4_).add(p_186263_2_);

            if (p_186263_5_ == null || p_186263_5_.isVecInside(blockpos))
            {
                NBTTagCompound nbttagcompound = template$entityinfo.entityData;
                Vec3d vec3d = transformedVec3d(template$entityinfo.pos, p_186263_3_, p_186263_4_);
                Vec3d vec3d1 = vec3d.addVector((double)p_186263_2_.getX(), (double)p_186263_2_.getY(), (double)p_186263_2_.getZ());
                NBTTagList nbttaglist = new NBTTagList();
                nbttaglist.appendTag(new NBTTagDouble(vec3d1.xCoord));
                nbttaglist.appendTag(new NBTTagDouble(vec3d1.yCoord));
                nbttaglist.appendTag(new NBTTagDouble(vec3d1.zCoord));
                nbttagcompound.setTag("Pos", nbttaglist);
                nbttagcompound.setUniqueId("UUID", UUID.randomUUID());
                Entity entity;

                try
                {
                    entity = EntityList.createEntityFromNBT(nbttagcompound, worldIn);
                }
                catch (Exception var15)
                {
                    entity = null;
                }

                if (entity != null)
                {
                    if (entity instanceof EntityPainting)
                    {
                        entity.getMirroredYaw(p_186263_3_);
                        entity.getRotatedYaw(p_186263_4_);
                        entity.setPosition((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
                        entity.setLocationAndAngles(vec3d1.xCoord, vec3d1.yCoord, vec3d1.zCoord, entity.rotationYaw, entity.rotationPitch);
                    }
                    else
                    {
                        float f = entity.getMirroredYaw(p_186263_3_);
                        f = f + (entity.rotationYaw - entity.getRotatedYaw(p_186263_4_));
                        entity.setLocationAndAngles(vec3d1.xCoord, vec3d1.yCoord, vec3d1.zCoord, f, entity.rotationPitch);
                    }

                    worldIn.spawnEntityInWorld(entity);
                }
            }
        }
    }

    public BlockPos transformedSize(Rotation rotationIn)
    {
        switch (rotationIn)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                return new BlockPos(this.size.getZ(), this.size.getY(), this.size.getX());
            default:
                return this.size;
        }
    }

    private static BlockPos transformedBlockPos(BlockPos p_186268_0_, Mirror p_186268_1_, Rotation p_186268_2_)
    {
        int i = p_186268_0_.getX();
        int j = p_186268_0_.getY();
        int k = p_186268_0_.getZ();
        boolean flag = true;

        switch (p_186268_1_)
        {
            case LEFT_RIGHT:
                k = -k;
                break;
            case FRONT_BACK:
                i = -i;
                break;
            default:
                flag = false;
        }

        switch (p_186268_2_)
        {
            case COUNTERCLOCKWISE_90:
                return new BlockPos(k, j, -i);
            case CLOCKWISE_90:
                return new BlockPos(-k, j, i);
            case CLOCKWISE_180:
                return new BlockPos(-i, j, -k);
            default:
                return flag ? new BlockPos(i, j, k) : p_186268_0_;
        }
    }

    private static Vec3d transformedVec3d(Vec3d p_186269_0_, Mirror p_186269_1_, Rotation p_186269_2_)
    {
        double d0 = p_186269_0_.xCoord;
        double d1 = p_186269_0_.yCoord;
        double d2 = p_186269_0_.zCoord;
        boolean flag = true;

        switch (p_186269_1_)
        {
            case LEFT_RIGHT:
                d2 = 1.0D - d2;
                break;
            case FRONT_BACK:
                d0 = 1.0D - d0;
                break;
            default:
                flag = false;
        }

        switch (p_186269_2_)
        {
            case COUNTERCLOCKWISE_90:
                return new Vec3d(d2, d1, 1.0D - d0);
            case CLOCKWISE_90:
                return new Vec3d(1.0D - d2, d1, d0);
            case CLOCKWISE_180:
                return new Vec3d(1.0D - d0, d1, 1.0D - d2);
            default:
                return flag ? new Vec3d(d0, d1, d2) : p_186269_0_;
        }
    }

    public void write(NBTTagCompound compound)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (Template.BlockInfo template$blockinfo : this.blocks)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setTag("pos", this.writeInts(new int[] {template$blockinfo.pos.getX(), template$blockinfo.pos.getY(), template$blockinfo.pos.getZ()}));
            nbttagcompound.setInteger("state", Block.getStateId(template$blockinfo.blockState));

            if (template$blockinfo.tileentityData != null)
            {
                nbttagcompound.setTag("nbt", template$blockinfo.tileentityData);
            }

            nbttaglist.appendTag(nbttagcompound);
        }

        NBTTagList nbttaglist1 = new NBTTagList();

        for (Template.EntityInfo template$entityinfo : this.entities)
        {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setTag("pos", this.writeDoubles(new double[] {template$entityinfo.pos.xCoord, template$entityinfo.pos.yCoord, template$entityinfo.pos.zCoord}));
            nbttagcompound1.setTag("blockPos", this.writeInts(new int[] {template$entityinfo.blockPos.getX(), template$entityinfo.blockPos.getY(), template$entityinfo.blockPos.getZ()}));

            if (template$entityinfo.entityData != null)
            {
                nbttagcompound1.setTag("nbt", template$entityinfo.entityData);
            }

            nbttaglist1.appendTag(nbttagcompound1);
        }

        compound.setTag("blocks", nbttaglist);
        compound.setTag("entities", nbttaglist1);
        compound.setTag("size", this.writeInts(new int[] {this.size.getX(), this.size.getY(), this.size.getZ()}));
        compound.setInteger("version", 1);
        compound.setString("author", this.author);
    }

    public void read(NBTTagCompound compound)
    {
        this.blocks.clear();
        this.entities.clear();
        NBTTagList nbttaglist = compound.getTagList("size", 3);
        this.size = new BlockPos(nbttaglist.getIntAt(0), nbttaglist.getIntAt(1), nbttaglist.getIntAt(2));
        this.author = compound.getString("author");
        NBTTagList nbttaglist1 = compound.getTagList("blocks", 10);

        for (int i = 0; i < nbttaglist1.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist1.getCompoundTagAt(i);
            NBTTagList nbttaglist2 = nbttagcompound.getTagList("pos", 3);
            BlockPos blockpos = new BlockPos(nbttaglist2.getIntAt(0), nbttaglist2.getIntAt(1), nbttaglist2.getIntAt(2));
            int j = nbttagcompound.getInteger("state");
            IBlockState iblockstate = Block.getStateById(j);
            NBTTagCompound nbttagcompound1;

            if (nbttagcompound.hasKey("nbt"))
            {
                nbttagcompound1 = nbttagcompound.getCompoundTag("nbt");
            }
            else
            {
                nbttagcompound1 = null;
            }

            this.blocks.add(new Template.BlockInfo(blockpos, iblockstate, nbttagcompound1));
        }

        NBTTagList nbttaglist3 = compound.getTagList("entities", 10);

        for (int k = 0; k < nbttaglist3.tagCount(); ++k)
        {
            NBTTagCompound nbttagcompound3 = nbttaglist3.getCompoundTagAt(k);
            NBTTagList nbttaglist4 = nbttagcompound3.getTagList("pos", 6);
            Vec3d vec3d = new Vec3d(nbttaglist4.getDoubleAt(0), nbttaglist4.getDoubleAt(1), nbttaglist4.getDoubleAt(2));
            NBTTagList nbttaglist5 = nbttagcompound3.getTagList("blockPos", 3);
            BlockPos blockpos1 = new BlockPos(nbttaglist5.getIntAt(0), nbttaglist5.getIntAt(1), nbttaglist5.getIntAt(2));

            if (nbttagcompound3.hasKey("nbt"))
            {
                NBTTagCompound nbttagcompound2 = nbttagcompound3.getCompoundTag("nbt");
                this.entities.add(new Template.EntityInfo(vec3d, blockpos1, nbttagcompound2));
            }
        }
    }

    private NBTTagList writeInts(int... p_186267_1_)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i : p_186267_1_)
        {
            nbttaglist.appendTag(new NBTTagInt(i));
        }

        return nbttaglist;
    }

    private NBTTagList writeDoubles(double... p_186264_1_)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (double d0 : p_186264_1_)
        {
            nbttaglist.appendTag(new NBTTagDouble(d0));
        }

        return nbttaglist;
    }

    static class BlockInfo
        {
            /** the position the block is to be generated to */
            public final BlockPos pos;
            /** The type of block in this particular spot in the structure. */
            public final IBlockState blockState;
            /** NBT data for the tileentity */
            public final NBTTagCompound tileentityData;

            private BlockInfo(BlockPos p_i47042_1_, IBlockState p_i47042_2_, NBTTagCompound p_i47042_3_)
            {
                this.pos = p_i47042_1_;
                this.blockState = p_i47042_2_;
                this.tileentityData = p_i47042_3_;
            }
        }

    static class EntityInfo
        {
            /** the position the entity is will be generated to */
            public final Vec3d pos;
            /** field_186248_b */
            public final BlockPos blockPos;
            /** the serialized NBT data of the entity in the structure */
            public final NBTTagCompound entityData;

            private EntityInfo(Vec3d p_i47101_1_, BlockPos p_i47101_2_, NBTTagCompound p_i47101_3_)
            {
                this.pos = p_i47101_1_;
                this.blockPos = p_i47101_2_;
                this.entityData = p_i47101_3_;
            }
        }
}