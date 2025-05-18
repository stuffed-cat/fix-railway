package com.example.fixrailway;

import com.example.fixrailway.config.Config;
import com.example.fixrailway.init.BlockInit;
import com.example.fixrailway.init.ItemInit;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FixRailway - 修复Arclight服务端中机械动力（Create）模组铁路系统的兼容性问题
 * 
 * 此模组专门解决在Arclight服务端环境中运行Create模组时，
 * 铁路和列车系统可能出现的各种问题，确保它们能够正常工作。
 */
@Mod(FixRailway.MODID)
public class FixRailway {
    public static final String MODID = "fixrailway";
    public static final Logger LOGGER = LogManager.getLogger();

    public FixRailway() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onCreativeModeTabRegister);

        // Register our mod's config
        Config.register();

        // Register the registries
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        LOGGER.info("FixRailway mod initialization complete - 准备修复Arclight中的Create铁路问题");
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        // Common setup code
        LOGGER.info("COMMON SETUP");
        
        // Any initialization that needs to happen after registry events
        event.enqueueWork(() -> {
            // 需要在主线程上执行的初始化逻辑
        });
    }

    private void onCreativeModeTabRegister(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.BUILDING_BLOCKS) {
            // Add railway blocks to the building blocks tab
            event.accept(BlockInit.EXAMPLE_RAILWAY_BLOCK);
            event.accept(BlockInit.HIGH_SPEED_RAIL);
            event.accept(BlockInit.RAIL_CROSSING);
            event.accept(BlockInit.RAIL_SIGNAL);
        }
        
        if (event.getTab() == CreativeModeTabs.REDSTONE_BLOCKS) {
            // Add railway items to the redstone tab
            event.accept(ItemInit.EXAMPLE_RAILWAY_ITEM);
            event.accept(ItemInit.RAILWAY_WRENCH);
            event.accept(ItemInit.HIGH_SPEED_RAIL_COMPONENT);
            event.accept(ItemInit.SIGNAL_CONTROLLER);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Server starting code
        LOGGER.info("SERVER STARTING");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Client setup code
            LOGGER.info("CLIENT SETUP");
        }
    }
}