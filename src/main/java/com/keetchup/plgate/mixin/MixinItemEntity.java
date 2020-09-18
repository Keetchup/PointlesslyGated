package com.keetchup.plgate.mixin;

import com.keetchup.plgate.items.GoldenDollItem;
import com.keetchup.plgate.items.PLGateItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {

    @Shadow
    private static TrackedData<ItemStack> STACK;

    @Shadow
    private UUID thrower;

    public MixinItemEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo callbackInfo) {
        if (!world.isClient()) {
            if (this.getStack().getItem() == PLGateItems.GOLDEN_DOLL) {
                if ((this.isSubmergedIn(FluidTags.LAVA)) && (this.getThrower() != null)) {
                    GoldenDollItem.spawnPiglinBoss(this.world, this.getBlockPos(), this);
                    this.remove();
                }
            }
        }
    }

    public ItemStack getStack() {
        return (ItemStack) this.getDataTracker().get(STACK);
    }

    public UUID getThrower() {
        return this.thrower;
    }
}
