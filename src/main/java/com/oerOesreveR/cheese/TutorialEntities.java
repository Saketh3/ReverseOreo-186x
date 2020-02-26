package com.oerOesreveR.cheese;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;

import static com.oerOesreveR.cheese.cheese.MOD_ID;

public class TutorialEntities {
    public static EntityType<?> TUTORIAL_ENTITY = EntityType.Builder.create(TutorialEntity::new, EntityClassification.CREATURE).build(MOD_ID + ":tutorial_entity").setRegistryName(cheeses.location("tutorial_entity"));

    public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                    cheeses.tutorial_entity_egg = registerEntitySpawnEgg(TUTORIAL_ENTITY, 0x2f5882, 0xff00ff, "tutorial_entity_egg")
                );
    }

    public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name) {
        SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(ItemGroup.MISC));
        item.setRegistryName(MOD_ID, name);
        return item;
    }
}
