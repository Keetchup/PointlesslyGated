package com.keetchup.plgate.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class AxeSubclass extends AxeItem {
    protected AxeSubclass(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}