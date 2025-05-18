package com.example.fixrailway.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemGroup;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = "fixrailway")
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "fixrailway");

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    @SubscribeEvent
    public static void addCreativeTabs(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ItemGroup.TAB_MISC) {
            event.accept(EXAMPLE_ITEM);
        }
    }
}