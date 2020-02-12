package com.oerOesreveR.cheese;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static com.oerOesreveR.cheese.cheese.MOD_ID;

/**
 * This class has the register event handler for all custom items.
 * This class uses @Mod.EventBusSubscriber so the event handler has to be static
 * This class uses @ObjectHolder to get a reference to the items
 */
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(MOD_ID)
public class cheeses {

   // public static final Food swiss = (new Food.Builder()).hunger(4).saturation(0.3F).build();
   // public static final Items swill = null;

    /**
     * The actual event handler that registers the custom items.
     *
     * @param event The event this event handler handles
     */
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //In here you pass in all item instances you want to register.
        //Make sure you always set the registry name.
        event.getRegistry().registerAll(
                //new Item((new Item.Properties().group(ItemGroup.FOOD).maxStackSize(32).food(swiss))).setRegistryName(MOD_ID, "swiss"),
                new Item(new Item.Properties()).setRegistryName(MOD_ID, "swill")
        );
    }
}
