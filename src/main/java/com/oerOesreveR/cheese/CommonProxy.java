package com.oerOesreveR.cheese;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
        new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(cheeses.swill)
    }
}
