package com.keetchup.plgate.items;

import com.keetchup.plgate.entities.HeavySnowballEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class HeavySnowballItem extends Item {
    public HeavySnowballItem(Settings settings) {
        super(settings);
    }

    protected static void init(){
        DispenserBlock.registerBehavior(PLGateModItems.HEAVY_SNOWBALL, new ProjectileDispenserBehavior() {
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                return (ProjectileEntity) Util.make(new HeavySnowballEntity(world, position.getX(), position.getY(), position.getZ()), (heavySnowballEntity) -> {
                    heavySnowballEntity.setItem(stack);
                });
            }
        });
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            HeavySnowballEntity heavySnowballEntity = new HeavySnowballEntity(world, playerEntity);
            heavySnowballEntity.setItem(itemStack);
            heavySnowballEntity.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(heavySnowballEntity);
        }

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!playerEntity.abilities.creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.method_29237(itemStack, world.isClient());
    }
}
