package com.example.fixrailway.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.IntValue EXAMPLE_OPTION;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        EXAMPLE_OPTION = builder
                .comment("An example configuration option")
                .defineInRange("exampleOption", 10, 0, 100);
        
        SPEC = builder.build();
    }
}