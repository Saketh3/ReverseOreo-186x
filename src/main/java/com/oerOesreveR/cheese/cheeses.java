package com.oerOesreveR.cheese;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
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
    public static EntityType<?> Friend = EntityType.Builder.create(FriendEntity::new, EntityClassification.CREATURE).build(MOD_ID + ":not_a_pig").setRegistryName(cheeses.location("not_a_pig"));
    public static Item swill;
    public static Item daniel_egg;
    public static Item swiss;


    /**
     * The actual event handler that registers the custom items.
     *
     * @param event The event this event handler handles
     */
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                swiss = new Item((new Item.Properties().group(ItemGroup.FOOD).maxStackSize(33).food( (new Food.Builder()).hunger(4).saturation(0.3F).build()))).setRegistryName(MOD_ID, "swiss"),
                swill = new Item(new Item.Properties()).setRegistryName(MOD_ID, "swill"),
                daniel_egg = new SpawnEggItem(Friend, 0xffffff, 0xffffff, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(MOD_ID, "daniel_egg")
        );
    }

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(

        );
    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                        Friend
                );
    }
    public static ResourceLocation location(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}