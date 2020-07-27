package com.keetchup.plgate;

import com.keetchup.plgate.armor.PLGateModArmorItems;
import com.keetchup.plgate.items.PLGateModItems;
import com.keetchup.plgate.tools.PLGateModToolItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class PLGate implements ModInitializer {

    public static final ItemGroup PLGATE_GROUP = FabricItemGroupBuilder.build(new Identifier("plgate", "moditemgroup"),() -> new ItemStack(PLGateModArmorItems.EMERALD_CHESTPLATE));

    @Override
    public void onInitialize() {

        PLGateModItems.init();
        PLGateModToolItems.init();
        PLGateModArmorItems.init();
        StructureDistance.addToArray();

    }
}
