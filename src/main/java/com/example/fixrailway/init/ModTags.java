package com.example.fixrailway.init;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.resources.ResourceLocation;

public class ModTags {
    public static final TagKey<Block> RAILWAY_BLOCKS = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("fixrailway", "railway_blocks"));
    public static final TagKey<Item> RAILWAY_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("fixrailway", "railway_items"));

    // Additional tags can be defined here
}