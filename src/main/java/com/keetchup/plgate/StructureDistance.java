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

    public double getDistanceFromStructure(PlayerEntity player, BlockPos structure) {
        return Math.sqrt(Math.pow(player.getX() - structure.getX(), 2) + Math.pow(player.getZ() - structure.getZ(), 2));
    }

    public static BlockPos nearestStructurePos(World world, PlayerEntity playerEntity, int structureListPos) {
        BlockPos blockPos = playerEntity.getBlockPos();
        if (world instanceof ServerWorld) {
            switch (structureListPos) {
                case 0:
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.DESERT_PYRAMID, playerEntity.getBlockPos(), 100, false);
                    break;
                case 1:
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.IGLOO, playerEntity.getBlockPos(), 100, false);
                    break;
                case 2:
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.JUNGLE_PYRAMID, playerEntity.getBlockPos(), 100, false);
                    break;
                case 3:
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.MANSION, playerEntity.getBlockPos(), 100, false);
                    break;
                case 4:
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.MONUMENT, playerEntity.getBlockPos(), 100, false);
                    break;
                case 5:
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.PILLAGER_OUTPOST, playerEntity.getBlockPos(), 100, false);
                    break;
                case 6:
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.SWAMP_HUT, playerEntity.getBlockPos(), 100, false);
                    break;
                default:

            }

        }
        return blockPos;
    }

}
