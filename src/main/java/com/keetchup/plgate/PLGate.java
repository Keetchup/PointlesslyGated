package com.keetchup.plgate;

import com.keetchup.plgate.armor.PLGateArmor;
import com.keetchup.plgate.blocks.PLGateBlocks;
import com.keetchup.plgate.items.PLGateItems;
import com.keetchup.plgate.tools.PLGateTools;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class PLGate implements ModInitializer {

    public static final ItemGroup PLGATE_GROUP = FabricItemGroupBuilder.build(new Identifier("plgate", "moditemgroup"),() -> new ItemStack(PLGateArmor.EMERALD_CHESTPLATE));

    @Override
    public void onInitialize() {

        PLGateBlocks.init();
        PLGateItems.init();
        PLGateTools.init();
        PLGateArmor.init();
        StructureDistance.addToArray();

    }
}
