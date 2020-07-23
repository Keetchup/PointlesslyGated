package com.keetchup.plgate.tools;

import com.keetchup.plgate.PLGate;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModToolItems {

    public static final Item EMERALD_SWORD = new SwordItem(ModToolMaterials.EMERALD, 2, -2.4F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_PICKAXE = new PickaxeSubclass(ModToolMaterials.EMERALD, 0, -2.8F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_AXE = new AxeSubclass(ModToolMaterials.EMERALD, 5, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_SHOVEL = new ShovelItem(ModToolMaterials.EMERALD, 1.5F, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item EMERALD_HOE = new HoeSubclass(ModToolMaterials.EMERALD, -3, -1.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

    public static final Item DENSE_GOLDEN_SWORD = new SwordItem(ModToolMaterials.DENSE_GOLD, 3, -2.4F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_PICKAXE = new PickaxeSubclass(ModToolMaterials.DENSE_GOLD, 1, -2.8F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_AXE = new AxeSubclass(ModToolMaterials.DENSE_GOLD, 7, -3.1F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_SHOVEL = new ShovelItem(ModToolMaterials.DENSE_GOLD, 1.5F, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));
    public static final Item DENSE_GOLDEN_HOE = new HoeSubclass(ModToolMaterials.DENSE_GOLD, -2, -3.0F, (new Item.Settings().group(PLGate.PLGATE_GROUP)));

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
