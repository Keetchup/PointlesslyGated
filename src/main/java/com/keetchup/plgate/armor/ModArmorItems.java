package com.keetchup.plgate.armor;

import com.keetchup.plgate.PLGate;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModArmorItems {

    public static final Item EMERALD_HELMET = new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlot.HEAD, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_CHESTPLATE = new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlot.CHEST, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_LEGGINGS = new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlot.LEGS, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_BOOTS = new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlot.FEET, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    public static final Item DENSE_GOLDEN_HELMET = new ArmorItem(ModArmorMaterial.DENSE_GOLD, EquipmentSlot.HEAD, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_CHESTPLATE = new ArmorItem(ModArmorMaterial.DENSE_GOLD, EquipmentSlot.CHEST, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_LEGGINGS = new ArmorItem(ModArmorMaterial.DENSE_GOLD, EquipmentSlot.LEGS, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_BOOTS = new ArmorItem(ModArmorMaterial.DENSE_GOLD, EquipmentSlot.FEET, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    public static void init(){
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_helmet"), EMERALD_HELMET);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_chestplate"), EMERALD_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_leggings"), EMERALD_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_boots"), EMERALD_BOOTS);

        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_helmet"), DENSE_GOLDEN_HELMET);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_chestplate"), DENSE_GOLDEN_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_leggings"), DENSE_GOLDEN_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_boots"), DENSE_GOLDEN_BOOTS);
    }
}
