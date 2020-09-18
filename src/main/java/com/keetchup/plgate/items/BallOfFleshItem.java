package com.keetchup.plgate.items;

import com.keetchup.plgate.StructureDistance;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Objects;

public class BallOfFleshItem extends Item {

    public BallOfFleshItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext itemUsageContext) {
        World world = itemUsageContext.getWorld();
        if (!world.isClient) {
            PlayerEntity playerEntity = itemUsageContext.getPlayer();
            ItemStack itemStack = itemUsageContext.getStack();
            BlockPos blockPos = itemUsageContext.getBlockPos();
            Direction direction = itemUsageContext.getSide();
            BlockState blockState = world.getBlockState(blockPos);
            BlockPos blockPosChange;
            if (blockState.getCollisionShape(world, blockPos).isEmpty()) {
                blockPosChange = blockPos;
            } else {
                blockPosChange = blockPos.offset(direction);
            }

            spawnHuskBoss(world, blockPosChange, itemStack, playerEntity, !Objects.equals(blockPos, blockPosChange) && direction == Direction.UP);

            if (!playerEntity.abilities.creativeMode) {
                itemStack.decrement(1);
            }
        }
        return ActionResult.CONSUME;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        HitResult hitResult = raycast(playerEntity.getEntityWorld(), playerEntity, RaycastContext.FluidHandling.SOURCE_ONLY);

        if (!world.isClient) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos blockPos = blockHitResult.getBlockPos();
            if (hitResult.getType() != HitResult.Type.BLOCK) {
                return TypedActionResult.pass(itemStack);
            } else {
                if (!(world.getBlockState(blockPos).getBlock() instanceof FluidBlock)) {
                    spawnHuskBoss(world, blockPos, itemStack, playerEntity, false);
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.pass(itemStack);
                } else if ((world.canPlayerModifyAt(playerEntity, blockPos)) && (playerEntity.canPlaceOn(blockPos, blockHitResult.getSide(), itemStack))) {
                    if (!playerEntity.abilities.creativeMode) {
                        itemStack.decrement(1);
                    }
                    return TypedActionResult.consume(itemStack);
                } else {
                    return TypedActionResult.fail(itemStack);
                }
            }
        }
        return TypedActionResult.consume(itemStack);
    }

    private void spawnHuskBoss(World world, BlockPos blockPos, ItemStack itemStack, PlayerEntity playerEntity, boolean invertY) {
        BlockPos structureBlockPos = new StructureDistance().nearestStructurePos(world, playerEntity, "desert_pyramid");
        double distanceStructure = new StructureDistance().getDistanceFromStructure(playerEntity.getBlockPos(), structureBlockPos);

        if ((structureBlockPos != null) && (distanceStructure <= 25)) {
            playerEntity.sendMessage(new TranslatableText("item.plgate.ball_of_flesh.summon"), true);

            HuskEntity huskBoss = (HuskEntity) EntityType.HUSK.spawnFromItemStack((ServerWorld) world, itemStack, playerEntity, blockPos, SpawnReason.MOB_SUMMONED, false, invertY);
            huskBoss.setCustomName(new TranslatableText("boss.plgate.husk_boss"));
            huskBoss.setAbsorptionAmount(50);
            huskBoss.setLeftHanded(true);
            huskBoss.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10));

            world.spawnEntity(huskBoss);
        }
    }
}
