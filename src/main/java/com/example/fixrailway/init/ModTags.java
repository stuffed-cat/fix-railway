package com.example.fixrailway.init;

import com.example.fixrailway.FixRailway;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTags {
    public static class Blocks {
        // 创建一个铁路方块标签
        public static final TagKey<Block> RAILWAY_BLOCKS = tag("railway_blocks");
        
        // 创建一个高速铁路方块标签
        public static final TagKey<Block> HIGH_SPEED_RAILS = tag("high_speed_rails");
        
        // 创建一个铁路信号方块标签
        public static final TagKey<Block> RAIL_SIGNALS = tag("rail_signals");
        
        // 创建铁路方块的标签（助手方法）
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(FixRailway.MODID, name));
        }
    }
    
    public static class Items {
        // 创建一个铁路物品标签
        public static final TagKey<Item> RAILWAY_ITEMS = tag("railway_items");
        
        // 创建一个铁路工具标签
        public static final TagKey<Item> RAILWAY_TOOLS = tag("railway_tools");
        
        // 创建铁路物品的标签（助手方法）
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(FixRailway.MODID, name));
        }
    }
}