package com.keetchup.plgate.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class InfusedEggBlock extends DragonEggBlock {

    public static final IntProperty STAR_STACKS = IntProperty.of("star_stacks", 0, 4);
    public static final IntProperty BREATH_STACK = IntProperty.of("breath_stack", 0, 1);

    public InfusedEggBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(STAR_STACKS, 0));
        setDefaultState(getStateManager().getDefaultState().with(BREATH_STACK, 0));
    }

    private static void addStarStack(World world, BlockPos blockPos, BlockState blockState) {
        world.setBlockState(blockPos, (BlockState) blockState.with(STAR_STACKS, (Integer) blockState.get(STAR_STACKS) + 1), 4);
    }

    private static void addBreathStack(World world, BlockPos blockPos, BlockState blockState) {
        world.setBlockState(blockPos, (BlockState) blockState.with(BREATH_STACK, (Integer) blockState.get(BREATH_STACK) + 1), 4);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(STAR_STACKS);
        stateManager.add(BREATH_STACK);
    }

    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        if (itemStack.getItem() == Items.NETHER_STAR) {
            if (getStarStacks(blockState) < 4) {
                addStarStack(world, blockPos, blockState);
                if (!playerEntity.abilities.creativeMode) {
                    itemStack.decrement(1);
                }
            }
        } else if (itemStack.getItem() == Items.DRAGON_BREATH) {
            if (getBreathStacks(blockState) < 1){
                addBreathStack(world, blockPos, blockState);
                if (!playerEntity.abilities.creativeMode) {
                    itemStack.decrement(1);
                }
            }
        } else {
            this.teleport(blockState, world, blockPos);
        }
        return ActionResult.success(world.isClient);
    }

    private int getStarStacks(BlockState blockState) {
        return blockState.get(STAR_STACKS);
    }

    private int getBreathStacks(BlockState blockState) {
        return blockState.get(BREATH_STACK);
    }

    public void onBlockBreakStart(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity) {
        this.teleport(blockState, world, blockPos);
    }

    private void teleport(BlockState blockState, World world, BlockPos pos) {
        for (int i = 0; i < 1000; ++i) {
            BlockPos blockPos = pos.add(world.random.nextInt(16) - world.random.nextInt(16), world.random.nextInt(8) - world.random.nextInt(8), world.random.nextInt(16) - world.random.nextInt(16));
            if (world.getBlockState(blockPos).isAir()) {
                if (world.isClient) {
                    for (int j = 0; j < 128; ++j) {
                        double d = world.random.nextDouble();
                        float f = (world.random.nextFloat() - 0.5F) * 0.2F;
                        float g = (world.random.nextFloat() - 0.5F) * 0.2F;
                        float h = (world.random.nextFloat() - 0.5F) * 0.2F;
                        double e = MathHelper.lerp(d, (double) blockPos.getX(), (double) pos.getX()) + (world.random.nextDouble() - 0.5D) + 0.5D;
                        double k = MathHelper.lerp(d, (double) blockPos.getY(), (double) pos.getY()) + world.random.nextDouble() - 0.5D;
                        double l = MathHelper.lerp(d, (double) blockPos.getZ(), (double) pos.getZ()) + (world.random.nextDouble() - 0.5D) + 0.5D;
                        world.addParticle(ParticleTypes.PORTAL, e, k, l, (double) f, (double) g, (double) h);
                    }
                } else {
                    world.setBlockState(blockPos, blockState, 2);
                    world.removeBlock(pos, false);
                }

                return;
            }
        }

    }
}
