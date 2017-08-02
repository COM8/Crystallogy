package de.comeight.crystallogy.worldGen;

import de.comeight.crystallogy.blocks.crystals.CrystalOre;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCrystalCave {
    //-----------------------------------------------Attributes:--------------------------------------------
    protected final int MAX_CRYSTALS_PER_CAVE;
    protected int crystalsLeft;

    protected World world;

    protected IBlockState cobble;
    protected IBlockState mossyCobble;

    protected IBlockState fluid;
    protected IBlockState crystal1;
    protected IBlockState crystal2;

    //-----------------------------------------------Constructor:-------------------------------------------
    public WorldGenCrystalCave(int maxCrystalsPerCave) {
        this.MAX_CRYSTALS_PER_CAVE = maxCrystalsPerCave;
        this.cobble = Blocks.COBBLESTONE.getDefaultState();
        this.mossyCobble = Blocks.MOSSY_COBBLESTONE.getDefaultState();
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    public void generateCrystalCave(Random random, BlockPos pos, World world) {
        this.world = world;
        crystalsLeft = 1 + random.nextInt(MAX_CRYSTALS_PER_CAVE - 1);
        selectFluidAndCrystals(random, pos);
        generateRoom(random, pos);
    }

    protected void selectFluidAndCrystals(Random random, BlockPos pos) {
        if(getFluidIndex(random, pos) == 0) {
            fluid = Blocks.WATER.getDefaultState();
        }
        else {
            fluid = Blocks.LAVA.getDefaultState();
        }

        crystal1 = getNextCrystal(random, pos);
        crystal2 = getNextCrystal(random, pos);
    }

    protected IBlockState getNextCrystal(Random random, BlockPos pos) {
        switch (getCrystalIndex(random, pos)) {
            case 0:
                return BlockHandler.CRYSTAL_ORE_RED.getDefaultState();
            case 1:
                return BlockHandler.CRYSTAL_ORE_YELLOW.getDefaultState();
            case 2:
                return BlockHandler.CRYSTAL_ORE_GREEN.getDefaultState();
            case 3:
                return BlockHandler.CRYSTAL_ORE_BLUE.getDefaultState();
            default:
                return BlockHandler.CRYSTAL_ORE_WHITE.getDefaultState();
        }
    }

    protected int getFluidIndex(Random random, BlockPos pos) {
        return random.nextInt(3) % 2;
    }

    protected int getCrystalIndex(Random random, BlockPos pos) {
        return random.nextInt(5);
    }

    protected void generateRoom(Random random, BlockPos pos) {
        for (int x = 0; x < 7; x++) {
            for (int z = 0; z < 7; z++) {
                for (int y = 0; y < 4; y++) {
                    world.setBlockToAir(pos.add(x, y, z));
                }
            }
        }
        generateFloor(random, pos);
        generateWalls(random, pos);
    }

    protected void generateWalls(Random random, BlockPos pos) {
        int maxTries = crystalsLeft * 2;
        while (crystalsLeft > 0 && maxTries-- > 0) {
            int side = 1 + random.nextInt(5);
            int xSide = random.nextInt(7);
            int ySide = 1 + random.nextInt(3);

            BlockPos crystalPos;
            IBlockState crystalState;
            IBlockState holderState;

            switch (side) {
                case 1:
                    crystalPos = pos.add(xSide, 3, ySide + random.nextInt(4));
                    crystalState = world.getBlockState(crystalPos);
                    holderState = world.getBlockState(crystalPos.add(0, 1, 0));
                    if(crystalState.getMaterial() == Material.AIR && holderState.getMaterial() != Material.AIR) {
                        placeCrystal(crystalPos, EnumFacing.DOWN, random);
                    }
                    break;
                case 2:
                    crystalPos = pos.add(xSide, ySide, 0);
                    crystalState = world.getBlockState(crystalPos);
                    holderState = world.getBlockState(crystalPos.add(0, 0, -1));
                    if(crystalState.getMaterial() == Material.AIR && holderState.getMaterial() != Material.AIR) {
                        placeCrystal(crystalPos, EnumFacing.SOUTH, random);
                    }
                    break;
                case 3:
                    crystalPos = pos.add(xSide, ySide, 6);
                    crystalState = world.getBlockState(crystalPos);
                    holderState = world.getBlockState(crystalPos.add(0, 0, 5));
                    if(crystalState.getMaterial() == Material.AIR && holderState.getMaterial() != Material.AIR) {
                        placeCrystal(crystalPos, EnumFacing.NORTH, random);
                    }
                    break;
                case 4:
                    crystalPos = pos.add(0, ySide, xSide);
                    crystalState = world.getBlockState(crystalPos);
                    holderState = world.getBlockState(crystalPos.add(-1, 0, 0));
                    if(crystalState.getMaterial() == Material.AIR && holderState.getMaterial() != Material.AIR) {
                        placeCrystal(crystalPos, EnumFacing.EAST, random);
                    }
                    break;
                default:
                    crystalPos = pos.add(6, ySide, xSide);
                    crystalState = world.getBlockState(crystalPos);
                    holderState = world.getBlockState(crystalPos.add(5, 0, 0));
                    if(crystalState.getMaterial() == Material.AIR && holderState.getMaterial() != Material.AIR) {
                        placeCrystal(crystalPos, EnumFacing.WEST, random);
                    }
                    break;
            }
        }
    }

    protected void generateFloor(Random random, BlockPos pos) {
        for (int x = 0; x < 7; x++) {
            for (int z = 0; z < 7; z++) {
                BlockPos posBellow = pos.add(x, -1, z);
                IBlockState state = world.getBlockState(posBellow);
                if(!state.getBlock().isAir(state, world, posBellow)) {
                    int r = random.nextInt(20);
                    if(r % 2 == 0) {
                        world.setBlockState(posBellow, cobble);
                    }
                    else if(r == 0) {
                        world.setBlockState(posBellow, mossyCobble);
                    }

                    BlockPos waterLevel = posBellow.add(0, 1, 0);
                    boolean placedSolidBlock = false;
                    if(r % 5 == 0) {
                        world.setBlockState(waterLevel, state);
                        placedSolidBlock = true;
                    }
                    else if(r % 8 == 0) {
                        world.setBlockState(waterLevel, cobble);
                        placedSolidBlock = true;
                    }
                    else if(r % 12 == 0) {
                        world.setBlockState(waterLevel, mossyCobble);
                        placedSolidBlock = true;
                    }
                    else {
                        world.setBlockState(waterLevel, fluid);
                    }

                    if(placedSolidBlock) {
                        waterLevel = waterLevel.add(0, 1, 0);
                        if(r % 4 == 0) {
                            placeCrystal(waterLevel, random);
                        }
                    }
                }
            }
        }
    }

    protected void placeCrystal(BlockPos pos, Random random) {
        placeCrystal(pos, EnumFacing.UP, random);
    }

    protected void placeCrystal(BlockPos pos, EnumFacing facing, Random random) {
        if(crystalsLeft <= 0) {
            return;
        }
        crystalsLeft--;

        if(random.nextBoolean()) {
            world.setBlockState(pos, crystal1.withProperty(CrystalOre.FACING, facing));
        }
        else {
            world.setBlockState(pos, crystal2.withProperty(CrystalOre.FACING, facing));
        }
    }

    //-----------------------------------------------Events:------------------------------------------------

}