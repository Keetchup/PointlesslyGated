package com.keetchup.plgate.mixin;

import com.keetchup.plgate.blocks.PLGateBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.keetchup.plgate.blocks.InfusedEggBlock.BREATH_STACK;
import static com.keetchup.plgate.blocks.InfusedEggBlock.STAR_STACKS;

@Mixin(DragonEggBlock.class)
public class MixinDragonEggBlock{
    @Inject(method = "onUse", at = @At("HEAD"))
    private ActionResult useWithNetherStarConvert(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable callbackInfo) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        if (!world.isClient()) {
            if (itemStack.getItem() == Items.NETHER_STAR) {
                world.setBlockState(blockPos, (BlockState) PLGateBlocks.INFUSED_DRAGON_EGG.getDefaultState().with(STAR_STACKS, 1), 2);
                if (!playerEntity.abilities.creativeMode) {
                    itemStack.decrement(1);
                }
            } else if (itemStack.getItem() == Items.DRAGON_BREATH){
                world.setBlockState(blockPos, (BlockState) PLGateBlocks.INFUSED_DRAGON_EGG.getDefaultState().with(BREATH_STACK, 1), 2);
                if (!playerEntity.abilities.creativeMode) {
                    itemStack.decrement(1);
                }
            }
        }
        return ActionResult.success(world.isClient);
    }

    @Inject(method = "teleport", at = @At("HEAD"), cancellable = true)
    private void avoidTeleport(BlockState blockState, World world, BlockPos blockPos, CallbackInfo callbackInfo) {
        if (world.getBlockState(blockPos).getBlock() != Blocks.DRAGON_EGG){
            callbackInfo.cancel();
        }
    }
}
