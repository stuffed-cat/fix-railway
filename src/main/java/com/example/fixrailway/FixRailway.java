package com.example.fixrailway;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(FixRailway.MODID)
public class FixRailway {
    public static final String MODID = "fixrailway";

    public FixRailway() {
        // Mod initialization logic
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        // Common setup code
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        // Client setup code
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Server starting code
    }

    @SubscribeEvent
    public void onCreativeModeTabEvent(CreativeModeTabEvent.BuildContents event) {
        // Code to add items to creative mode tab
    }
}