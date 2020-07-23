package com.keetchup.plgate.tools;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class HoeSubclass extends HoeItem {

    protected HoeSubclass(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
