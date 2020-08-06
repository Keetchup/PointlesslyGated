package com.keetchup.plgate.items;

import com.keetchup.plgate.StructureDistance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.keetchup.plgate.StructureDistance.STRUCTURE_LIST;

public class StructureLocatorItem extends Item {

    public StructureLocatorItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        CompoundTag itemTag = itemStack.getTag();
        if (world instanceof ServerWorld) {
            if (!itemStack.hasTag()) {
                createStructureTag(itemStack, playerEntity);
            } else {
                int listPos = STRUCTURE_LIST.indexOf(itemTag.getString("structure"));
                if (playerEntity.isSneaking()) {
                    listPos = listPos == STRUCTURE_LIST.size() - 1 ? 0 : listPos + 1;
                    itemTag.remove("structure");
                    itemTag.putString("structure", STRUCTURE_LIST.get(listPos));
                    playerEntity.sendMessage(new TranslatableText("item.plgate.structure_locator." + STRUCTURE_LIST.get(listPos)), true);

                } else {
                    playerEntity.getItemCooldownManager().set(this, 1200);
                    searchForStructure(world, playerEntity, itemTag.getString("structure"), itemStack);
                }
            }
        }
        return TypedActionResult.pass(itemStack);
    }

    private void createStructureTag(ItemStack itemStack, PlayerEntity playerEntity) {
        CompoundTag baseTag = new CompoundTag();
        baseTag.putString("structure", STRUCTURE_LIST.get(0));
        itemStack.setTag(baseTag);
        playerEntity.sendMessage(new TranslatableText("item.plgate.structure_locator." + STRUCTURE_LIST.get(0)), true);
    }

    private void searchForStructure(World world, PlayerEntity playerEntity, String structureName, ItemStack itemStack) {
        BlockPos structurePos = StructureDistance.nearestStructurePos(world, playerEntity, structureName);
        if (structurePos != null) {
            String coords = structureName.replace("_", " ").toUpperCase() + " - X " + structurePos.getX() + " Z " + structurePos.getZ();
            playerEntity.sendMessage(new TranslatableText(coords), false);
        }
        else {
            playerEntity.sendMessage(new TranslatableText("item.plgate.structure_locator.fail"), false);
        }
    }
}
