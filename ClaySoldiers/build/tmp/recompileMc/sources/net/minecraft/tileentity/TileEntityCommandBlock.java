package net.minecraft.tileentity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCommandBlock extends TileEntity
{
    private boolean powered;
    private boolean auto;
    private boolean conditionMet;
    private boolean field_184262_h;
    private final CommandBlockBaseLogic commandBlockLogic = new CommandBlockBaseLogic()
    {
        /**
         * Get the position in the world. <b>{@code null} is not allowed!</b> If you are not an entity in the world,
         * return the coordinates 0, 0, 0
         */
        public BlockPos getPosition()
        {
            return TileEntityCommandBlock.this.pos;
        }
        /**
         * Get the position vector. <b>{@code null} is not allowed!</b> If you are not an entity in the world, return
         * 0.0D, 0.0D, 0.0D
         */
        public Vec3d getPositionVector()
        {
            return new Vec3d((double)TileEntityCommandBlock.this.pos.getX() + 0.5D, (double)TileEntityCommandBlock.this.pos.getY() + 0.5D, (double)TileEntityCommandBlock.this.pos.getZ() + 0.5D);
        }
        /**
         * Get the world, if available. <b>{@code null} is not allowed!</b> If you are not an entity in the world,
         * return the overworld
         */
        public World getEntityWorld()
        {
            return TileEntityCommandBlock.this.getWorld();
        }
        /**
         * Sets the command.
         */
        public void setCommand(String command)
        {
            super.setCommand(command);
            TileEntityCommandBlock.this.markDirty();
        }
        public void updateCommand()
        {
            IBlockState iblockstate = TileEntityCommandBlock.this.worldObj.getBlockState(TileEntityCommandBlock.this.pos);
            TileEntityCommandBlock.this.getWorld().notifyBlockUpdate(TileEntityCommandBlock.this.pos, iblockstate, iblockstate, 3);
        }
        @SideOnly(Side.CLIENT)
        public int func_145751_f()
        {
            return 0;
        }
        @SideOnly(Side.CLIENT)
        public void func_145757_a(ByteBuf buf)
        {
            buf.writeInt(TileEntityCommandBlock.this.pos.getX());
            buf.writeInt(TileEntityCommandBlock.this.pos.getY());
            buf.writeInt(TileEntityCommandBlock.this.pos.getZ());
        }
        /**
         * Returns the entity associated with the command sender. MAY BE NULL!
         */
        public Entity getCommandSenderEntity()
        {
            return null;
        }
        /**
         * Get the Minecraft server instance
         */
        public MinecraftServer getServer()
        {
            return TileEntityCommandBlock.this.worldObj.getMinecraftServer();
        }
    };

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        this.commandBlockLogic.writeDataToNBT(compound);
        compound.setBoolean("powered", this.isPowered());
        compound.setBoolean("conditionMet", this.isConditionMet());
        compound.setBoolean("auto", this.isAuto());
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.commandBlockLogic.readDataFromNBT(compound);
        this.setPowered(compound.getBoolean("powered"));
        this.setConditionMet(compound.getBoolean("conditionMet"));
        this.setAuto(compound.getBoolean("auto"));
    }

    public Packet<?> getDescriptionPacket()
    {
        if (this.func_184257_h())
        {
            this.func_184252_d(false);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            this.writeToNBT(nbttagcompound);
            return new SPacketUpdateTileEntity(this.pos, 2, nbttagcompound);
        }
        else
        {
            return null;
        }
    }

    public boolean func_183000_F()
    {
        return true;
    }

    public CommandBlockBaseLogic getCommandBlockLogic()
    {
        return this.commandBlockLogic;
    }

    public CommandResultStats getCommandResultStats()
    {
        return this.commandBlockLogic.getCommandResultStats();
    }

    public void setPowered(boolean poweredIn)
    {
        this.powered = poweredIn;
    }

    public boolean isPowered()
    {
        return this.powered;
    }

    public boolean isAuto()
    {
        return this.auto;
    }

    public void setAuto(boolean autoIn)
    {
        boolean flag = this.auto;
        this.auto = autoIn;

        if (!flag && autoIn && !this.powered && this.worldObj != null && this.func_184251_i() != TileEntityCommandBlock.Mode.SEQUENCE)
        {
            Block block = this.getBlockType();

            if (block instanceof BlockCommandBlock)
            {
                BlockPos blockpos = this.getPos();
                BlockCommandBlock blockcommandblock = (BlockCommandBlock)block;
                this.conditionMet = !this.func_184258_j() || blockcommandblock.isNextToSuccessfulCommandBlock(this.worldObj, blockpos, this.worldObj.getBlockState(blockpos));
                this.worldObj.scheduleUpdate(blockpos, block, block.tickRate(this.worldObj));

                if (this.conditionMet)
                {
                    blockcommandblock.propagateUpdate(this.worldObj, blockpos);
                }
            }
        }
    }

    public boolean isConditionMet()
    {
        return this.conditionMet;
    }

    public void setConditionMet(boolean conditionMetIn)
    {
        this.conditionMet = conditionMetIn;
    }

    public boolean func_184257_h()
    {
        return this.field_184262_h;
    }

    public void func_184252_d(boolean p_184252_1_)
    {
        this.field_184262_h = p_184252_1_;
    }

    public TileEntityCommandBlock.Mode func_184251_i()
    {
        Block block = this.getBlockType();
        return block == Blocks.command_block ? TileEntityCommandBlock.Mode.REDSTONE : (block == Blocks.repeating_command_block ? TileEntityCommandBlock.Mode.AUTO : (block == Blocks.chain_command_block ? TileEntityCommandBlock.Mode.SEQUENCE : TileEntityCommandBlock.Mode.REDSTONE));
    }

    public boolean func_184258_j()
    {
        IBlockState iblockstate = this.worldObj.getBlockState(this.getPos());
        return iblockstate.getBlock() instanceof BlockCommandBlock ? ((Boolean)iblockstate.getValue(BlockCommandBlock.CONDITIONAL)).booleanValue() : false;
    }

    /**
     * validates a tile entity
     */
    public void validate()
    {
        this.blockType = null;
        super.validate();
    }

    public static enum Mode
    {
        SEQUENCE,
        AUTO,
        REDSTONE;
    }
}