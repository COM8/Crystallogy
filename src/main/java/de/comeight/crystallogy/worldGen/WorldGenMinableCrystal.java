package de.comeight.crystallogy.worldGen;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class WorldGenMinableCrystal extends WorldGenMinable {
    //-----------------------------------------------Attributes:--------------------------------------------
    private final IBlockState STATE;
    /** The number of blocks to generate. */
    private final int MAX_SPAWN_SIZE;
    private final Predicate<IBlockState> PREDICATE;

    //-----------------------------------------------Constructor:-------------------------------------------
    public WorldGenMinableCrystal(IBlockState state, int maxSpawnSize) {
        super(state, maxSpawnSize);

        this.STATE = state;
        this.MAX_SPAWN_SIZE = maxSpawnSize;
        this.PREDICATE = new StonePredicate();
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int spawned = 0;
        /*for (int x = 0; x < 10; x += 2) {
            for (int y = 0; y < 10; y += 2) {
                for (int z = 0; z < 10; z += 2) {
                    BlockPos blockpos = position.add(x, y, z);

                    IBlockState state = worldIn.getBlockState(blockpos);
                    if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, PREDICATE))
                    {
                        worldIn.setBlockState(blockpos, STATE, 2);
                        if(++spawned >= MAX_SPAWN_SIZE) {
                            return true;
                        }
                    }
                }
            }
        }*/
        return MAX_SPAWN_SIZE <= spawned;
    }

    //-----------------------------------------------Events:------------------------------------------------


    static class StonePredicate implements Predicate<IBlockState>
    {
        private StonePredicate()
        {
        }

        public boolean apply(IBlockState state)
        {
            if (state != null && state.getBlock() == Blocks.STONE)
            {
                BlockStone.EnumType enumType = state.getValue(BlockStone.VARIANT);
                return enumType.isNatural();
            }
            else
            {
                return false;
            }
        }
    }
}