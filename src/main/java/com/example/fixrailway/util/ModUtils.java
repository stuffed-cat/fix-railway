package com.example.fixrailway.util;

import com.example.fixrailway.FixRailway;
import com.example.fixrailway.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 提供修复Create模组在Arclight服务端上铁路问题的工具方法
 */
public class ModUtils {
    // Create模组的类名常量，用于反射检测
    private static final String CREATE_TRAIN_CLASS = "com.simibubi.create.content.logistics.trains.entity.Train";
    private static final String CREATE_TRACK_CLASS = "com.simibubi.create.content.logistics.trains.track.TrackBlock";
    private static final String CREATE_SIGNAL_CLASS = "com.simibubi.create.content.logistics.trains.track.TrackSignalBlock";
    
    /**
     * 检查指定方块是否是Create模组的轨道方块
     * 
     * @param state 要检查的方块状态
     * @return 如果是Create的轨道方块则返回true
     */
    public static boolean isCreateTrackBlock(BlockState state) {
        // 尝试通过反射安全地检查方块类型
        try {
            Class<?> trackClass = Class.forName(CREATE_TRACK_CLASS);
            return trackClass.isInstance(state.getBlock());
        } catch (ClassNotFoundException e) {
            // Create模组未加载或类名已更改
            if (Config.DEBUG_MODE.get()) {
                FixRailway.LOGGER.debug("Create轨道类未找到: {}", e.getMessage());
            }
            return false;
        }
    }
    
    /**
     * 检查指定方块是否是Create模组的信号灯方块
     * 
     * @param state 要检查的方块状态
     * @return 如果是Create的信号灯方块则返回true
     */
    public static boolean isCreateSignalBlock(BlockState state) {
        try {
            Class<?> signalClass = Class.forName(CREATE_SIGNAL_CLASS);
            return signalClass.isInstance(state.getBlock());
        } catch (ClassNotFoundException e) {
            if (Config.DEBUG_MODE.get()) {
                FixRailway.LOGGER.debug("Create信号灯类未找到: {}", e.getMessage());
            }
            return false;
        }
    }
    
    /**
     * 修复Create模组列车在Arclight服务端上的速度问题
     * 
     * @param entity 要检查和修复的实体
     * @return 如果实体是Create列车并进行了修复则返回true
     */
    public static boolean fixCreateTrainSpeed(Entity entity) {
        if (!isCreateTrainEntity(entity)) {
            return false;
        }
        
        try {
            // 获取速度修正因子
            double speedFix = Config.CREATE_TRAIN_SPEED_FIX.get();
            
            // 只有在需要修正时才应用
            if (Math.abs(speedFix - 1.0) > 0.001) {
                // 应用速度修正
                entity.setDeltaMovement(
                    entity.getDeltaMovement().x * speedFix,
                    entity.getDeltaMovement().y,
                    entity.getDeltaMovement().z * speedFix
                );
                
                if (Config.DEBUG_MODE.get()) {
                    FixRailway.LOGGER.debug("已修正Create列车速度，实体: {}, 修正因子: {}", 
                            entity.getId(), speedFix);
                }
                return true;
            }
        } catch (Exception e) {
            FixRailway.LOGGER.error("修复Create列车速度时出错: {}", e.getMessage());
        }
        
        return false;
    }
    
    /**
     * 检查实体是否是Create模组的列车实体
     */
    public static boolean isCreateTrainEntity(Entity entity) {
        if (entity == null) {
            return false;
        }
        
        try {
            Class<?> trainClass = Class.forName(CREATE_TRAIN_CLASS);
            return trainClass.isInstance(entity);
        } catch (ClassNotFoundException e) {
            // Create模组未加载或类名已更改
            return false;
        }
    }
    
    /**
     * 修复不同步的Create铁路信号
     * 
     * @param level 游戏世界
     * @param pos 信号灯位置
     * @return 如果进行了修复则返回true
     */
    public static boolean fixCreateSignal(Level level, BlockPos pos) {
        if (!Config.FIX_CREATE_SIGNALS.get()) {
            return false;
        }
        
        BlockState state = level.getBlockState(pos);
        if (!isCreateSignalBlock(state)) {
            return false;
        }
        
        try {
            // 这里是信号灯修复逻辑的占位符
            // 实际实现需要根据Create模组的具体问题进行
            
            // 向客户端发送方块更新
            level.sendBlockUpdated(pos, state, state, 3);
            
            if (Config.DEBUG_MODE.get()) {
                FixRailway.LOGGER.debug("已修复位于 {} 的Create信号灯", pos);
            }
            
            return true;
        } catch (Exception e) {
            FixRailway.LOGGER.error("修复Create信号灯时出错: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 同步客户端与服务端之间的Create铁路数据
     * 这个方法应该在服务端定期调用
     * 
     * @param level 游戏世界
     * @return 同步的铁路组件数量
     */
    public static int syncCreateRailwayData(Level level) {
        // 这里是铁路数据同步逻辑的占位符
        // 实际实现需要根据Create模组的具体问题进行
        
        if (Config.DEBUG_MODE.get()) {
            FixRailway.LOGGER.debug("执行Create铁路数据同步");
        }
        
        // 返回一个示例值
        return 0;
    }
}
    
    /**
     * 修复周围的铁路方块（如果有问题）。
     *
     * @param level 游戏世界
     * @param pos 中心位置
     * @param radius 搜索半径
     * @return 修复的铁路方块数量
     */
    public static int repairRailways(Level level, BlockPos pos, int radius) {
        // 只在自动修复功能启用时运行
        if (!Config.ENABLE_AUTO_RAIL_REPAIR.get()) {
            return 0;
        }
        
        int repaired = 0;
        
        // 在指定半径内搜索并修复铁路
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = pos.offset(x, y, z);
                    
                    // 检查这个位置是不是应该有铁路但现在损坏了
                    // 这里是一个简化的示例，实际逻辑可能更复杂
                    if (shouldHaveRail(level, checkPos) && !isRailwayBlock(level, checkPos)) {
                        // 放置一个适当的铁轨类型
                        level.setBlock(checkPos, Blocks.RAIL.defaultBlockState(), 3);
                        repaired++;
                        
                        FixRailway.LOGGER.info("Repaired railway at {}", checkPos);
                    }
                }
            }
        }
        
        return repaired;
    }
    
    /**
     * 检查一个位置是否应该有铁轨（基于周围的铁轨模式）
     */
    private static boolean shouldHaveRail(Level level, BlockPos pos) {
        // 简化的检测逻辑 - 如果周围有铁轨，这个位置可能应该有铁轨
        // 在实际实现中，这个逻辑需要更复杂的铁路拓扑检查
        
        int railsAround = 0;
        
        // 检查六个方向
        if (isRailwayBlock(level, pos.north())) railsAround++;
        if (isRailwayBlock(level, pos.south())) railsAround++;
        if (isRailwayBlock(level, pos.east())) railsAround++;
        if (isRailwayBlock(level, pos.west())) railsAround++;
        if (isRailwayBlock(level, pos.above())) railsAround++;
        if (isRailwayBlock(level, pos.below())) railsAround++;
        
        // 如果周围至少有两个铁轨，这个位置可能应该有铁轨
        return railsAround >= 2;
    }
    
    /**
     * 应用高速铁路效果到实体
     */
    public static void applyHighSpeedEffect(Entity entity) {
        // 获取配置中的速度倍增值
        double speedMultiplier = Config.HIGH_SPEED_RAIL_MULTIPLIER.get();
        
        // 应用速度增益
        entity.setDeltaMovement(
            entity.getDeltaMovement().x * speedMultiplier,
            entity.getDeltaMovement().y,
            entity.getDeltaMovement().z * speedMultiplier
        );
    }
}