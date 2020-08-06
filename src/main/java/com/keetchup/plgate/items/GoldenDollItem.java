package com.keetchup.plgate.items;

import com.keetchup.plgate.StructureDistance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoldenDollItem extends Item {
    public GoldenDollItem(Settings settings) {
        super(settings);
    }

    public static void spawnPiglinBoss(World world, BlockPos blockPos, Entity entity){
        BlockPos structureBlockPos = new StructureDistance().nearestStructurePos(world, entity, "bastion_remnant");
        double distanceStructure = new StructureDistance().getDistanceFromStructure(entity.getBlockPos(), structureBlockPos);
        if ((structureBlockPos != null) && (distanceStructure <= 64)) {
            PiglinEntity PiglinBoss = (PiglinEntity) EntityType.PIGLIN.create(world, null, new TranslatableText("boss.plgate.piglin_boss"), null, blockPos, SpawnReason.MOB_SUMMONED, false, false);

            PiglinBoss.setAbsorptionAmount(150);
            PiglinBoss.setLeftHanded(true);
            PiglinBoss.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10));
            PiglinBoss.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, Integer.MAX_VALUE));

            world.spawnEntity(PiglinBoss);
        }

    }
}
