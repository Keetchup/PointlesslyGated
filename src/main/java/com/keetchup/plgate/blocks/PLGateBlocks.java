package com.keetchup.plgate.blocks;

import com.keetchup.plgate.PLGate;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class PLGateBlocks {

    public static final InfusedEggBlock INFUSED_DRAGON_EGG = new InfusedEggBlock(FabricBlockSettings.of(Material.EGG));

    public static void init(){
        Registry.register(Registry.BLOCK, new Identifier("plgate", "infused_dragon_egg"), INFUSED_DRAGON_EGG);
        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_dragon_egg"), new BlockItem(INFUSED_DRAGON_EGG, new Item.Settings().group(PLGate.PLGATE_GROUP).rarity(Rarity.EPIC)));
    }
}
