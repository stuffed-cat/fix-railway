package com.example.fixrailway.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

/**
 * 配置类 - 处理模组的各种配置选项
 * 这些选项允许玩家和服务器管理员微调修复功能的行为
 */
public class Config {
    public static final ForgeConfigSpec COMMON_SPEC;
    
    // Create模组火车速度修正因子
    public static final ForgeConfigSpec.DoubleValue CREATE_TRAIN_SPEED_FIX;
    
    // 启用自动修复铁路功能
    public static final ForgeConfigSpec.BooleanValue ENABLE_AUTO_RAIL_REPAIR;
    
    // Create信号灯修复
    public static final ForgeConfigSpec.BooleanValue FIX_CREATE_SIGNALS;
    
    // 同步间隔（刻）
    public static final ForgeConfigSpec.IntValue SYNC_INTERVAL;
    
    // 启用调试模式
    public static final ForgeConfigSpec.BooleanValue DEBUG_MODE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.comment("FixRailway Mod Configuration - Arclight服务端Create模组铁路修复")
               .push("general");
        
        CREATE_TRAIN_SPEED_FIX = builder
                .comment("修正Create模组列车在Arclight服务端上的速度问题的修正因子")
                .defineInRange("createTrainSpeedFix", 1.0, 0.1, 5.0);
        
        ENABLE_AUTO_RAIL_REPAIR = builder
                .comment("启用自动修复损坏或不同步的Create铁轨功能")
                .define("enableAutoRailRepair", true);
        
        FIX_CREATE_SIGNALS = builder
                .comment("修复Create模组信号灯在Arclight服务端上的同步问题")
                .define("fixCreateSignals", true);
        
        SYNC_INTERVAL = builder
                .comment("客户端与服务端铁路数据同步间隔（以游戏刻为单位)")
                .defineInRange("syncInterval", 20, 1, 200);
        
        DEBUG_MODE = builder
                .comment("启用调试模式，在日志中输出更多关于铁路修复的信息")
                .define("debugMode", false);
        
        builder.pop();
        
        COMMON_SPEC = builder.build();
    }
    
    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
    }
}