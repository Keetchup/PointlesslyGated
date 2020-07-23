package com.keetchup.plgate.items;

import com.keetchup.plgate.StructureDistance;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;

public class SpiderSoupItem extends Item {
    public SpiderSoupItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack itemStack, World world, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity && world instanceof ServerWorld) {
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;
            HitResult hitResult = rayTrace(playerEntity.getEntityWorld(), playerEntity, RayTraceContext.FluidHandling.SOURCE_ONLY);

            BlockPos structureBlockPos = ((ServerWorld) world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld) world, StructureFeature.JUNGLE_PYRAMID, playerEntity.getBlockPos(), 8, false);
            double distanceStructure = new StructureDistance().getDistanceFromStructure(playerEntity, structureBlockPos);

            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos playerBlockPos = playerEntity.getBlockPos();
            BlockPos rayBlockPos = blockHitResult.getBlockPos();
            BlockPos blockPos = new BlockPos((playerBlockPos.getX() + rayBlockPos.getX())/2, rayBlockPos.getY(), (playerBlockPos.getZ() + rayBlockPos.getZ())/2);

            if (structureBlockPos != null && distanceStructure <= 25) {
                if (hitResult.getType() == HitResult.Type.BLOCK && !(world.getBlockState(blockPos).getBlock() instanceof FluidBlock)) {

                    spawnSpiderBoss(world, blockPos);
                    playerEntity.sendMessage(new TranslatableText("item.plgate.spider_soup.summon"), true);
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));


                    if (!playerEntity.abilities.creativeMode) {
                        itemStack.decrement(1);
                    }

                }
            }

        }
        return itemStack;
    }

    protected void spawnSpiderBoss(World world, BlockPos blockPos) {
        CaveSpiderEntity SpiderBoss = new CaveSpiderEntity(EntityType.CAVE_SPIDER, world);

        SpiderBoss.setCustomName(Text.method_30163("Spider Queen"));
        SpiderBoss.setAbsorptionAmount(80);
        SpiderBoss.setPersistent();
        SpiderBoss.setLeftHanded(true);
        SpiderBoss.applyStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE));

        world.spawnEntity(SpiderBoss);
        SpiderBoss.teleport(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5);
    }
}
