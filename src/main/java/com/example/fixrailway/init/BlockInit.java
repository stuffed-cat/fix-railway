package com.example.fixrailway.init;

import com.example.fixrailway.FixRailway;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FixRailway.MODID);

    // 添加一个示例铁路方块
    public static final RegistryObject<Block> EXAMPLE_RAILWAY_BLOCK = registerBlock("example_railway_block", 
        () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
            .strength(3.0f, 6.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
        ));
    
    // 添加高速铁路方块
    public static final RegistryObject<Block> HIGH_SPEED_RAIL = registerBlock("high_speed_rail",
        () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
            .strength(3.5f, 7.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
        ));
        
    // 添加铁路交叉方块
    public static final RegistryObject<Block> RAIL_CROSSING = registerBlock("rail_crossing",
        () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
            .strength(3.0f, 6.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
        ));
    
    // 铁路信号方块
    public static final RegistryObject<Block> RAIL_SIGNAL = registerBlock("rail_signal",
        () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
            .strength(2.5f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
            .lightLevel(state -> 7) // 提供光照
        ));
        
    // 助手方法 - 注册方块并同时自动注册对应的物品
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    
    // 助手方法 - 为方块注册一个物品
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(FixRailway.RAILWAY_TAB)));
    }
}