package com.keetchup.plgate;

import com.keetchup.plgate.armor.ModArmorItems;
import com.keetchup.plgate.items.ModItems;
import com.keetchup.plgate.tools.ModToolItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class PLGate implements ModInitializer {

    public static final ItemGroup PLGATE_GROUP = FabricItemGroupBuilder.build(new Identifier("plgate", "moditemgroup"),() -> new ItemStack(ModArmorItems.EMERALD_CHESTPLATE));

    @Override
    public void onInitialize() {

        ModItems.init();
        ModToolItems.init();
        ModArmorItems.init();
        StructureDistance.addToArray();

    }
}
