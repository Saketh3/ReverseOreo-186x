package com.oerOesreveR.cheese.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static com.oerOesreveR.cheese.cheese.MOD_ID;

public class SoundsHandler {
    public static SoundEvent ENTITY_FRIEND_AMBIENT;
    public static SoundEvent ENTITY_FRIEND_HURT;
    public static SoundEvent ENTITY_FRIEND_DEATH;

    public static void registerSounds()
    {
        ENTITY_FRIEND_AMBIENT = registerSound("entity.friend.ambient");
        ENTITY_FRIEND_HURT = registerSound("entity.friend.hurt");
        ENTITY_FRIEND_DEATH = registerSound("entity.friend.death");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
