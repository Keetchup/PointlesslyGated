package com.keetchup.plgate;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.List;

public class StructureDistance {

    public static final List<String> structureArrayList = new ArrayList<>();

    protected static void addToArray() {
        structureArrayList.add("desert_pyramid");
        structureArrayList.add("igloo");
        structureArrayList.add("jungle_pyramid");
        structureArrayList.add("mansion");
        structureArrayList.add("monument");
        structureArrayList.add("pillager_outpost");
        structureArrayList.add("swamp_hut");
    }

    public static BlockPos nearestStructurePos(World world, PlayerEntity playerEntity, String structureName) {
        BlockPos blockPos = playerEntity.getBlockPos();
        if (world instanceof ServerWorld) {
            switch (structureName) {
                case "desert_pyramid":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.DESERT_PYRAMID, playerEntity.getBlockPos(), 100, false);
                    break;
                case "igloo":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.IGLOO, playerEntity.getBlockPos(), 100, false);
                    break;
                case "jungle_pyramid":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.JUNGLE_PYRAMID, playerEntity.getBlockPos(), 100, false);
                    break;
                case "mansion":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.MANSION, playerEntity.getBlockPos(), 100, false);
                    break;
                case "monument":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.MONUMENT, playerEntity.getBlockPos(), 100, false);
                    break;
                case "pillager_outpost":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.PILLAGER_OUTPOST, playerEntity.getBlockPos(), 100, false);
                    break;
                case "swamp_hut":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.SWAMP_HUT, playerEntity.getBlockPos(), 100, false);
                    break;
                default:

            }

        }
        return blockPos;
    }

    public double getDistanceFromStructure(BlockPos compareBlockPos, BlockPos structureBlockPos) {
        return Math.sqrt(Math.pow(compareBlockPos.getX() - structureBlockPos.getX(), 2) + Math.pow(compareBlockPos.getZ() - structureBlockPos.getZ(), 2));
    }

}
