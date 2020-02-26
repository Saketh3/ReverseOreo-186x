package com.oerOesreveR.cheese;

import com.oerOesreveR.cheese.client.renders.TutorialRenderRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

import static com.oerOesreveR.cheese.TutorialEntities.TUTORIAL_ENTITY;
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
    public static final Item swill = null;

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
                new Item((new Item.Properties().group(ItemGroup.FOOD).maxStackSize(33).food( (new Food.Builder()).hunger(4).saturation(0.3F).build()))).setRegistryName(MOD_ID, "swiss"),
                new Item(new Item.Properties()).setRegistryName(MOD_ID, "swill"),
                new SpawnEggItem(TUTORIAL_ENTITY, 0x32a846, 0x32a847, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(MOD_ID, "damn_daniel")
        );

        TutorialEntities.registerEntitySpawnEggs(event);
    }


    public static Item tutorial_entity_egg;

    public static void registerBlocks(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                TUTORIAL_ENTITY
        );
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
    private void clientRegistries(final FMLClientSetupEvent event)
    {
        TutorialRenderRegistry.registryEntityRenders();
    }
}