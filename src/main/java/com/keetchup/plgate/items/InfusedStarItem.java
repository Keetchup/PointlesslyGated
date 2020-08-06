package com.keetchup.plgate.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusedStarItem extends Item {
    public InfusedStarItem(Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
