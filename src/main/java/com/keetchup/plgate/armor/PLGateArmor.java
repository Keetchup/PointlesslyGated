package com.keetchup.plgate.armor;

import com.keetchup.plgate.PLGate;
import com.keetchup.plgate.items.PLGateItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class PLGateArmor {

    public static final Item EMERALD_CHESTPLATE = new ArmorItem(ModArmorMaterials.EMERALD, EquipmentSlot.CHEST, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item EMERALD_HELMET = new ArmorItem(ModArmorMaterials.EMERALD, EquipmentSlot.HEAD, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item EMERALD_LEGGINGS = new ArmorItem(ModArmorMaterials.EMERALD, EquipmentSlot.LEGS, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item EMERALD_BOOTS = new ArmorItem(ModArmorMaterials.EMERALD, EquipmentSlot.FEET, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    private static final Item DENSE_GOLDEN_HELMET = new ArmorItem(ModArmorMaterials.DENSE_GOLD, EquipmentSlot.HEAD, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item DENSE_GOLDEN_CHESTPLATE = new ArmorItem(ModArmorMaterials.DENSE_GOLD, EquipmentSlot.CHEST, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item DENSE_GOLDEN_LEGGINGS = new ArmorItem(ModArmorMaterials.DENSE_GOLD, EquipmentSlot.LEGS, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item DENSE_GOLDEN_BOOTS = new ArmorItem(ModArmorMaterials.DENSE_GOLD, EquipmentSlot.FEET, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    private static final Item INFUSED_STAR_HELMET = new ArmorItem(ModArmorMaterials.INFUSED_STAR, EquipmentSlot.HEAD, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));
    private static final Item INFUSED_STAR_CHESTPLATE = new ArmorItem(ModArmorMaterials.INFUSED_STAR, EquipmentSlot.CHEST, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));
    private static final Item INFUSED_STAR_LEGGINGS = new ArmorItem(ModArmorMaterials.INFUSED_STAR, EquipmentSlot.LEGS, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));
    private static final Item INFUSED_STAR_BOOTS = new ArmorItem(ModArmorMaterials.INFUSED_STAR, EquipmentSlot.FEET, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_helmet"), EMERALD_HELMET);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_chestplate"), EMERALD_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_leggings"), EMERALD_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_boots"), EMERALD_BOOTS);

        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_helmet"), DENSE_GOLDEN_HELMET);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_chestplate"), DENSE_GOLDEN_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_leggings"), DENSE_GOLDEN_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_boots"), DENSE_GOLDEN_BOOTS);

        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_helmet"), INFUSED_STAR_HELMET);
        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_chestplate"), INFUSED_STAR_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_leggings"), INFUSED_STAR_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_boots"), INFUSED_STAR_BOOTS);
    }

    private enum ModArmorMaterials implements ArmorMaterial {
        DENSE_GOLD("dense_gold", 22, new int[]{3, 5, 6, 3}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
            return Ingredient.ofItems(Items.GOLD_INGOT);
        }),
        EMERALD("emerald", 30, new int[]{3, 6, 7, 3}, 19, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 0.0F, () -> {
            return Ingredient.ofItems(Items.EMERALD);
        }),
        INFUSED_STAR("infused_star", 40, new int[]{4, 7, 9, 4}, 7, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
            return Ingredient.ofItems(PLGateItems.ENDER_INFUSED_STAR);
        });

        private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
        private final String name;
        private final int durabilityMultiplier;
        private final int[] protectionAmounts;
        private final int enchantability;
        private final SoundEvent equipSound;
        private final float toughness;
        private final float knockbackResistance;
        private final Lazy<Ingredient> repairIngredientSupplier;

        ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> supplier) {
            this.name = name;
            this.durabilityMultiplier = durabilityMultiplier;
            this.protectionAmounts = protectionAmounts;
            this.enchantability = enchantability;
            this.equipSound = equipSound;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairIngredientSupplier = new Lazy(supplier);
        }

        public int getDurability(EquipmentSlot slot) {
            return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
        }

        public int getProtectionAmount(EquipmentSlot slot) {
            return this.protectionAmounts[slot.getEntitySlotId()];
        }

        public int getEnchantability() {
            return this.enchantability;
        }

        public SoundEvent getEquipSound() {
            return this.equipSound;
        }

        public Ingredient getRepairIngredient() {
            return this.repairIngredientSupplier.get();
        }

        @Environment(EnvType.CLIENT)
        public String getName() {
            return this.name;
        }

        public float getToughness() {
            return this.toughness;
        }

        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    }
}
