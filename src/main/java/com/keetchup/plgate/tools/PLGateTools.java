package com.keetchup.plgate.tools;

import com.keetchup.plgate.PLGate;
import com.keetchup.plgate.items.PLGateItems;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class PLGateTools {

    private static final Item EMERALD_SWORD = new SwordItem(ModToolMaterials.EMERALD, 2, -2.4F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item EMERALD_PICKAXE = new PickaxeSubclass(ModToolMaterials.EMERALD, 0, -2.8F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item EMERALD_AXE = new AxeSubclass(ModToolMaterials.EMERALD, 5, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item EMERALD_SHOVEL = new ShovelItem(ModToolMaterials.EMERALD, 1.5F, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item EMERALD_HOE = new HoeSubclass(ModToolMaterials.EMERALD, -3, -1.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    private static final Item DENSE_GOLDEN_SWORD = new SwordItem(ModToolMaterials.DENSE_GOLD, 3, -2.4F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item DENSE_GOLDEN_PICKAXE = new PickaxeSubclass(ModToolMaterials.DENSE_GOLD, 1, -2.8F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item DENSE_GOLDEN_AXE = new AxeSubclass(ModToolMaterials.DENSE_GOLD, 7, -3.1F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item DENSE_GOLDEN_SHOVEL = new ShovelItem(ModToolMaterials.DENSE_GOLD, 1.5F, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    private static final Item DENSE_GOLDEN_HOE = new HoeSubclass(ModToolMaterials.DENSE_GOLD, -2, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    private static final Item INFUSED_STAR_SWORD = new SwordItem(ModToolMaterials.INFUSED_STAR, 5, -2.4F, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));
    private static final Item INFUSED_STAR_PICKAXE = new PickaxeSubclass(ModToolMaterials.INFUSED_STAR, 1, -2.8F, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));
    private static final Item INFUSED_STAR_AXE = new AxeSubclass(ModToolMaterials.INFUSED_STAR, 7, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));
    private static final Item INFUSED_STAR_SHOVEL = new ShovelItem(ModToolMaterials.INFUSED_STAR, 1.5F, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));
    private static final Item INFUSED_STAR_HOE = new HoeSubclass(ModToolMaterials.INFUSED_STAR, -2, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)).rarity(Rarity.EPIC));

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_sword"), EMERALD_SWORD);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_shovel"), EMERALD_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_pickaxe"), EMERALD_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_axe"), EMERALD_AXE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "emerald_hoe"), EMERALD_HOE);

        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_sword"), DENSE_GOLDEN_SWORD);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_shovel"), DENSE_GOLDEN_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_pickaxe"), DENSE_GOLDEN_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_axe"), DENSE_GOLDEN_AXE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "dense_golden_hoe"), DENSE_GOLDEN_HOE);

        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_sword"), INFUSED_STAR_SWORD);
        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_shovel"), INFUSED_STAR_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_pickaxe"), INFUSED_STAR_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_axe"), INFUSED_STAR_AXE);
        Registry.register(Registry.ITEM, new Identifier("plgate", "infused_star_hoe"), INFUSED_STAR_HOE);
    }

    private enum ModToolMaterials implements ToolMaterial {
        DENSE_GOLD(2, 425, 12.0F, 2.0F, 22, () -> {
            return Ingredient.ofItems(Items.GOLD_INGOT);
        }),
        EMERALD(3, 1753, 7.0F, 3.0F, 12, () -> {
            return Ingredient.ofItems(Items.EMERALD);
        }),
        INFUSED_STAR(4, 3246, 11.0F, 5.0F, 7, () -> {
            return Ingredient.ofItems(PLGateItems.ENDER_INFUSED_STAR);
        });

        private final int miningLevel;
        private final int itemDurability;
        private final float miningSpeed;
        private final float attackDamage;
        private final int enchantability;
        private final Lazy<Ingredient> repairIngredient;

        ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantibility, Supplier<Ingredient> repairIngredient) {
            this.miningLevel = miningLevel;
            this.itemDurability = itemDurability;
            this.miningSpeed = miningSpeed;
            this.attackDamage = attackDamage;
            this.enchantability = enchantibility;
            this.repairIngredient = new Lazy(repairIngredient);
        }

        public int getDurability() {
            return this.itemDurability;
        }

        public float getMiningSpeedMultiplier() {
            return this.miningSpeed;
        }

        public float getAttackDamage() {
            return this.attackDamage;
        }

        public int getMiningLevel() {
            return this.miningLevel;
        }

        public int getEnchantability() {
            return this.enchantability;
        }

        public Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }
    }

    private static class PickaxeSubclass extends PickaxeItem {

        private PickaxeSubclass(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }

    private static class AxeSubclass extends AxeItem {

        private AxeSubclass(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }

    private static class HoeSubclass extends HoeItem {

        private HoeSubclass(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }
}
