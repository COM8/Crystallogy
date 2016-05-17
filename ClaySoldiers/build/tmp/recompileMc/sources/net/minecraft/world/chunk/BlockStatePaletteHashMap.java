package net.minecraft.world.chunk;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IntIdentityHashBiMap;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStatePaletteHashMap implements IBlockStatePalette
{
    private final IntIdentityHashBiMap<IBlockState> statePaletteMap;
    private final IBlockStatePaletteResizer paletteResizer;
    private final int field_186048_c;

    public BlockStatePaletteHashMap(int p_i47089_1_, IBlockStatePaletteResizer p_i47089_2_)
    {
        this.field_186048_c = p_i47089_1_;
        this.paletteResizer = p_i47089_2_;
        this.statePaletteMap = new IntIdentityHashBiMap(1 << p_i47089_1_);
    }

    public int func_186041_a(IBlockState state)
    {
        int i = this.statePaletteMap.getId(state);

        if (i == -1)
        {
            i = this.statePaletteMap.add(state);

            if (i >= 1 << this.field_186048_c)
            {
                i = this.paletteResizer.func_186008_a(this.field_186048_c + 1, state);
            }
        }

        return i;
    }

    /**
     * Gets the block state by the palette id.
     */
    public IBlockState getBlockState(int indexKey)
    {
        return (IBlockState)this.statePaletteMap.get(indexKey);
    }

    @SideOnly(Side.CLIENT)
    public void read(PacketBuffer buf)
    {
        this.statePaletteMap.func_186812_a();
        int i = buf.readVarIntFromBuffer();

        for (int j = 0; j < i; ++j)
        {
            this.statePaletteMap.add(Block.BLOCK_STATE_IDS.getByValue(buf.readVarIntFromBuffer()));
        }
    }

    public void write(PacketBuffer buf)
    {
        int i = this.statePaletteMap.size();
        buf.writeVarIntToBuffer(i);

        for (int j = 0; j < i; ++j)
        {
            buf.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.statePaletteMap.get(j)));
        }
    }

    public int func_186040_a()
    {
        int i = PacketBuffer.getVarIntSize(this.statePaletteMap.size());

        for (int j = 0; j < this.statePaletteMap.size(); ++j)
        {
            i += PacketBuffer.getVarIntSize(Block.BLOCK_STATE_IDS.get(this.statePaletteMap.get(j)));
        }

        return i;
    }
}