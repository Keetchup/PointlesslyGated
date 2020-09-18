package com.keetchup.plgate.items;

import com.keetchup.plgate.PLGate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class PLGateItems {
    public static final Item ENDER_INFUSED_STAR = new InfusedStarItem(new Item.Settings().group(PLGate.PLGATE_GROUP).fireproof().rarity(Rarity.EPIC));
    private static final Item BALL_OF_FLESH = new BallOfFleshItem(new Item.Settings().group(PLGate.PLGATE_GROUP).maxCount(16));
    public static final Item HEAVY_SNOWBALL = new HeavySnowballItem(new Item.Settings().group(PLGate.PLGATE_GROUP).maxCount(16));
    private static final Item SPIDER_SOUP = new SpiderSoupItem(new Item.Settings().group(PLGate.PLGATE_GROUP).maxCount(1).food(new FoodComponent.Builder().hunger(8).saturationModifier(6f).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 12000), 1f).build()));
    public static final Item GOLDEN_DOLL = new GoldenDollItem(new Item.Settings().group(PLGate.PLGATE_GROUP).maxCount(1).fireproof().rarity(Rarity.UNCOMMON));
    private static final Item STRUCTURE_LOCATOR = new StructureLocatorItem(new Item.Settings().group(PLGate.PLGATE_GROUP).maxCount(1));

    public static void init(){
        Registry.register(Registry.ITEM, new Identifier("plgate", "ball_of_flesh"), BALL_OF_FLESH);
        Registry.register(Registry.ITEM, new Identifier("plgate", "spider_soup"), SPIDER_SOUP);
        Registry.register(Registry.ITEM, new Identifier("plgate", "heavy_snowball"), HEAVY_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("plgate", "golden_doll"), GOLDEN_DOLL);
        Registry.register(Registry.ITEM, new Identifier("plgate","ender_infused_star"), ENDER_INFUSED_STAR);
        Registry.register(Registry.ITEM, new Identifier("plgate","structure_locator"), STRUCTURE_LOCATOR);

        HeavySnowballItem.addToDispenser();
    }
}
