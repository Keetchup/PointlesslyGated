package com.keetchup.plgate.tools;

import com.keetchup.plgate.PLGate;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PLGateModToolItems {

    public static final Item EMERALD_SWORD = new SwordItem(PLGateModToolMaterials.EMERALD, 2, -2.4F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_PICKAXE = new PickaxeSubclass(PLGateModToolMaterials.EMERALD, 0, -2.8F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_AXE = new AxeSubclass(PLGateModToolMaterials.EMERALD, 5, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_SHOVEL = new ShovelItem(PLGateModToolMaterials.EMERALD, 1.5F, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_HOE = new HoeSubclass(PLGateModToolMaterials.EMERALD, -3, -1.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    public static final Item DENSE_GOLDEN_SWORD = new SwordItem(PLGateModToolMaterials.DENSE_GOLD, 3, -2.4F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_PICKAXE = new PickaxeSubclass(PLGateModToolMaterials.DENSE_GOLD, 1, -2.8F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_AXE = new AxeSubclass(PLGateModToolMaterials.DENSE_GOLD, 7, -3.1F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_SHOVEL = new ShovelItem(PLGateModToolMaterials.DENSE_GOLD, 1.5F, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_HOE = new HoeSubclass(PLGateModToolMaterials.DENSE_GOLD, -2, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    public static void init(){

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


    }
}
