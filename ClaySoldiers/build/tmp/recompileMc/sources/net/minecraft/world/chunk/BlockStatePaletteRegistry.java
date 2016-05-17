package net.minecraft.world.chunk;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStatePaletteRegistry implements IBlockStatePalette
{
    public int func_186041_a(IBlockState state)
    {
        int i = Block.BLOCK_STATE_IDS.get(state);
        return i == -1 ? 0 : i;
    }

    /**
     * Gets the block state by the palette id.
     */
    public IBlockState getBlockState(int indexKey)
    {
        IBlockState iblockstate = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(indexKey);
        return iblockstate == null ? Blocks.air.getDefaultState() : iblockstate;
    }

    @SideOnly(Side.CLIENT)
    public void read(PacketBuffer buf)
    {
        buf.readVarIntFromBuffer();
    }

    public void write(PacketBuffer buf)
    {
        buf.writeVarIntToBuffer(0);
    }

    public int func_186040_a()
    {
        return PacketBuffer.getVarIntSize(0);
    }
}