package com.example.fixrailway.util;

import com.example.fixrailway.FixRailway;
import com.example.fixrailway.config.Config;
import com.example.fixrailway.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 提供各种工具方法的实用工具类。
 */
public class ModUtils {
    /**
     * 检查指定位置是否有任何铁路方块。
     * 
     * @param level 游戏世界
     * @param pos 要检查的位置
     * @return 如果位置是铁路方块则返回true
     */
    public static boolean isRailwayBlock(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        
        // 检查原版铁轨
        if (state.getBlock() == Blocks.RAIL || 
            state.getBlock() == Blocks.POWERED_RAIL || 
            state.getBlock() == Blocks.DETECTOR_RAIL || 
            state.getBlock() == Blocks.ACTIVATOR_RAIL) {
            return true;
        }
        
        // 检查我们的自定义铁轨
        if (state.getBlock() == BlockInit.HIGH_SPEED_RAIL.get() ||
            state.getBlock() == BlockInit.RAIL_CROSSING.get() ||
            state.getBlock() == BlockInit.RAIL_SIGNAL.get()) {
            return true;
        }
        
        // 这里可以添加其他模组铁轨的兼容检查
        
        return false;
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