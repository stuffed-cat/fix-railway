package com.example.fixrailway.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
    public static final ForgeConfigSpec COMMON_SPEC;
    
    // 高速铁路速度倍增器
    public static final ForgeConfigSpec.DoubleValue HIGH_SPEED_RAIL_MULTIPLIER;
    
    // 启用自动修复铁路功能
    public static final ForgeConfigSpec.BooleanValue ENABLE_AUTO_RAIL_REPAIR;
    
    // 信号灯范围（方块数）
    public static final ForgeConfigSpec.IntValue SIGNAL_RANGE;
    
    // 铁路能耗（能量单位/方块）
    public static final ForgeConfigSpec.IntValue RAIL_ENERGY_USAGE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.comment("FixRailway Mod Configuration")
               .push("general");
        
        HIGH_SPEED_RAIL_MULTIPLIER = builder
                .comment("Speed multiplier for entities traveling on high speed rails")
                .defineInRange("highSpeedRailMultiplier", 2.0, 1.0, 10.0);
        
        ENABLE_AUTO_RAIL_REPAIR = builder
                .comment("Enable automatic repair of broken rails")
                .define("enableAutoRailRepair", true);
        
        SIGNAL_RANGE = builder
                .comment("The range in blocks that rail signals can detect entities")
                .defineInRange("signalRange", 16, 1, 64);
        
        RAIL_ENERGY_USAGE = builder
                .comment("Energy usage per block for powered railways (in FE/RF)")
                .defineInRange("railEnergyUsage", 5, 0, 1000);
        
        builder.pop();
        
        COMMON_SPEC = builder.build();
    }
    
    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
    }
}