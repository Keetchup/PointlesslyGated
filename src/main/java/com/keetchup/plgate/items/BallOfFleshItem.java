package com.keetchup.plgate.items;

import com.keetchup.plgate.StructureDistance;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;

public class BallOfFleshItem extends Item {

    protected BallOfFleshItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        HitResult hitResult = rayTrace(playerEntity.getEntityWorld(), playerEntity, RayTraceContext.FluidHandling.SOURCE_ONLY);
        if (world instanceof ServerWorld) {
            BlockPos structureBlockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.DESERT_PYRAMID, playerEntity.getBlockPos(), 8, false);
            double distanceStructure = new StructureDistance().getDistanceFromStructure(playerEntity, structureBlockPos);

            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos blockPos = blockHitResult.getBlockPos();
            if (structureBlockPos != null && distanceStructure <= 25) {
                if (hitResult.getType() != HitResult.Type.BLOCK) {
                    return TypedActionResult.pass(itemStack);
                } else if (world.isClient) {
                    return TypedActionResult.success(itemStack);
                } else {
                    if (!(world.getBlockState(blockPos).getBlock() instanceof FluidBlock)) {
                        spawnHuskBoss(world, blockPos);
                        playerEntity.sendMessage(new TranslatableText("item.plgate.ball_of_flesh.summon"), true);
                        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                        return TypedActionResult.pass(itemStack);
                    } else if (world.canPlayerModifyAt(playerEntity, blockPos) && playerEntity.canPlaceOn(blockPos, blockHitResult.getSide(), itemStack)) {
                        if (!playerEntity.abilities.creativeMode) {
                            itemStack.decrement(1);
                        }
                        return TypedActionResult.consume(itemStack);
                    } else {
                        return TypedActionResult.fail(itemStack);
                    }
                }
            }

        }
        return TypedActionResult.consume(itemStack);
    }

    protected void spawnHuskBoss(World world, BlockPos blockPos) {
        HuskEntity HuskBoss = new HuskEntity(EntityType.HUSK, world);

        HuskBoss.setCustomName(Text.method_30163("Dead Pharaoh"));
        HuskBoss.setAbsorptionAmount(50);
        HuskBoss.setPersistent();
        HuskBoss.setLeftHanded(true);

        world.spawnEntity(HuskBoss);
        HuskBoss.teleport(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5);
    }


}
