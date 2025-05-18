package com.example.fixrailway.init;

import com.example.fixrailway.FixRailway;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FixRailway.MODID);

    // 示例铁路物品
    public static final RegistryObject<Item> EXAMPLE_RAILWAY_ITEM = ITEMS.register("example_railway_item", 
        () -> new Item(new Item.Properties().tab(FixRailway.RAILWAY_TAB)));
    
    // 铁路工具
    public static final RegistryObject<Item> RAILWAY_WRENCH = ITEMS.register("railway_wrench", 
        () -> new Item(new Item.Properties().durability(250).tab(FixRailway.RAILWAY_TAB)));
    
    // 高速铁路组件
    public static final RegistryObject<Item> HIGH_SPEED_RAIL_COMPONENT = ITEMS.register("high_speed_rail_component", 
        () -> new Item(new Item.Properties().tab(FixRailway.RAILWAY_TAB)));
    
    // 铁路信号控制器
    public static final RegistryObject<Item> SIGNAL_CONTROLLER = ITEMS.register("signal_controller", 
        () -> new Item(new Item.Properties().stacksTo(1).tab(FixRailway.RAILWAY_TAB)));
}