package com.keetchup.plgate.items;

import com.keetchup.plgate.StructureDistance;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class SpiderSoupItem extends Item {
    public SpiderSoupItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack itemStack, World world, LivingEntity livingEntity) {
        if ((livingEntity instanceof PlayerEntity) && (world instanceof ServerWorld)) {
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;
            BlockHitResult blockHitResult = rayTrace(playerEntity.getEntityWorld(), playerEntity, RayTraceContext.FluidHandling.SOURCE_ONLY);

            BlockPos playerBlockPos = playerEntity.getBlockPos();
            BlockPos rayBlockPos = blockHitResult.getBlockPos();

            BlockPos blockPos = new BlockPos(MathHelper.floor((playerBlockPos.getX() + rayBlockPos.getX()) / 2.0), MathHelper.floor(rayBlockPos.getY()), MathHelper.floor(playerBlockPos.getZ() + rayBlockPos.getZ()) / 2.0);

            if (!(world.getBlockState(blockPos).getBlock() instanceof FluidBlock)) {

                spawnSpiderBoss(world, blockPos, playerEntity);
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

                if (!playerEntity.abilities.creativeMode) {
                    itemStack.decrement(1);
                }

            }

        }
        return itemStack;
    }

    protected void spawnSpiderBoss(World world, BlockPos blockPos, PlayerEntity playerEntity) {
        BlockPos structureBlockPos = new StructureDistance().nearestStructurePos(world, playerEntity, "jungle_pyramid");
        double distanceStructure = new StructureDistance().getDistanceFromStructure(playerEntity.getBlockPos(), structureBlockPos);

        if ((structureBlockPos != null) && (distanceStructure <= 25)) {
            playerEntity.sendMessage(new TranslatableText("item.plgate.spider_soup.summon"), true);

            CaveSpiderEntity spiderBoss = (CaveSpiderEntity) EntityType.CAVE_SPIDER.create(world, null, new TranslatableText("boss.plgate.spider_boss"), playerEntity, blockPos, SpawnReason.MOB_SUMMONED, false, false);
            spiderBoss.setAbsorptionAmount(80);
            spiderBoss.setLeftHanded(true);
            spiderBoss.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10));
            spiderBoss.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE));

            world.spawnEntity(spiderBoss);
        }
    }
}
