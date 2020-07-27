package com.keetchup.plgate.entities;

import com.keetchup.plgate.StructureDistance;
import com.keetchup.plgate.items.PLGateModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HeavySnowballEntity extends ThrownItemEntity {
    public HeavySnowballEntity(EntityType<? extends HeavySnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    public HeavySnowballEntity(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public HeavySnowballEntity(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return PLGateModItems.HEAVY_SNOWBALL;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect) (itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 3f);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            if (this.getOwner() instanceof PlayerEntity && !(hitResult instanceof EntityHitResult)) {
                BlockPos structureBlockPos = new StructureDistance().nearestStructurePos(world, ((PlayerEntity) this.getOwner()), "igloo");
                double structureDistance = new StructureDistance().getDistanceFromStructure(((BlockHitResult) hitResult).getBlockPos(), structureBlockPos);
                if (structureBlockPos != null && structureDistance <= 7) {
                    BlockPos blockPos = new BlockPos(this.getX(), this.getY(), this.getZ());
                    spawnStrayBoss(blockPos);
                }
            }
            this.world.sendEntityStatus(this, (byte) 3);
            this.remove();
        }

    }

    private void spawnStrayBoss(BlockPos blockPos) {
        StrayEntity strayBoss = (StrayEntity) EntityType.STRAY.create(world, null, new TranslatableText("boss.plgate.stray_boss"), (PlayerEntity) this.getOwner(), blockPos, SpawnReason.MOB_SUMMONED, false, false);

        strayBoss.setAbsorptionAmount(160);
        strayBoss.setLeftHanded(true);
        strayBoss.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, Integer.MAX_VALUE));
        strayBoss.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, Integer.MAX_VALUE));

        world.spawnEntity(strayBoss);
    }
}
