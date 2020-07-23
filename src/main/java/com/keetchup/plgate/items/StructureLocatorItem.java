package com.keetchup.plgate.items;

import com.keetchup.plgate.StructureDistance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.keetchup.plgate.StructureDistance.structureArrayList;

public class StructureLocatorItem extends Item {

    public StructureLocatorItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        CompoundTag itemTag = itemStack.getTag();
        int listPos = structureArrayList.indexOf(itemTag.getString("structure"));
        if (world instanceof ServerWorld) {
            if (!itemStack.hasTag()) {
                createStructureTag(itemStack, playerEntity);
            } else {
                if (playerEntity.isSneaking()) {
                    listPos = listPos == structureArrayList.size() - 1 ? -1 : listPos;
                    itemTag.remove("structure");
                    listPos++;
                    itemTag.putString("structure", structureArrayList.get(listPos));
                    String actionBarText = "item.plgate.structure_locator." + structureArrayList.get(listPos);
                    playerEntity.sendMessage(new TranslatableText(actionBarText), true);

                } else {
                    playerEntity.getItemCooldownManager().set(this, 400);
                    searchForStructure(world, playerEntity, listPos);
                }
            }
        }
        return TypedActionResult.pass(itemStack);
    }

    private void createStructureTag(ItemStack itemStack, PlayerEntity playerEntity) {
        CompoundTag baseTag = new CompoundTag();
        baseTag.putString("structure", "desert_pyramid");
        itemStack.setTag(baseTag);
        playerEntity.sendMessage(new TranslatableText("item.plgate.structure_locator.desert_pyramid"), false);
    }

    private void searchForStructure(World world, PlayerEntity playerEntity, int listPos) {
        BlockPos structurePos = StructureDistance.nearestStructurePos(world, playerEntity, listPos);
        if (!structurePos.equals(null)) {
            String coords = "X " + structurePos.getX() + " Z " + structurePos.getZ();
            playerEntity.sendMessage(new TranslatableText(coords), true);

        }
    }
}
