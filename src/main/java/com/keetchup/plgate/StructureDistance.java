package com.keetchup.plgate;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.List;

public class StructureDistance {

    public static final List<String> STRUCTURE_LIST = new ArrayList<>();

    protected static void addToArray() {
        STRUCTURE_LIST.add("desert_pyramid");
        STRUCTURE_LIST.add("igloo");
        STRUCTURE_LIST.add("jungle_pyramid");
        STRUCTURE_LIST.add("mansion");
        STRUCTURE_LIST.add("monument");
        STRUCTURE_LIST.add("pillager_outpost");
        STRUCTURE_LIST.add("swamp_hut");
        STRUCTURE_LIST.add("bastion_remnant");
    }

    public static BlockPos nearestStructurePos(World world, Entity entity, String structureName) {
        BlockPos blockPos = entity.getBlockPos();
        if (world instanceof ServerWorld) {
            switch (structureName) {
                case "desert_pyramid":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.DESERT_PYRAMID, entity.getBlockPos(), 100, false);
                    break;
                case "igloo":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.IGLOO, entity.getBlockPos(), 100, false);
                    break;
                case "jungle_pyramid":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.JUNGLE_PYRAMID, entity.getBlockPos(), 100, false);
                    break;
                case "mansion":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.MANSION, entity.getBlockPos(), 100, false);
                    break;
                case "monument":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.MONUMENT, entity.getBlockPos(), 100, false);
                    break;
                case "pillager_outpost":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.PILLAGER_OUTPOST, entity.getBlockPos(), 100, false);
                    break;
                case "swamp_hut":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.SWAMP_HUT, entity.getBlockPos(), 100, false);
                    break;
                case "bastion_remnant":
                    blockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.BASTION_REMNANT, entity.getBlockPos(), 100, false);
                    break;
                default:
                    blockPos = null;
                    break;
            }

        }
        return blockPos;
    }

    public double getDistanceFromStructure(BlockPos compareBlockPos, BlockPos structureBlockPos) {
        if (compareBlockPos != null && structureBlockPos != null) {
            return Math.sqrt(Math.pow(compareBlockPos.getX() - structureBlockPos.getX(), 2) + Math.pow(compareBlockPos.getZ() - structureBlockPos.getZ(), 2));
        }
        return Double.POSITIVE_INFINITY;
    }

}
